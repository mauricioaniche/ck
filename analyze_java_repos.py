import os
import subprocess
import requests

# Configurações
GITHUB_API_URL = 'https://api.github.com/graphql'
GITHUB_TOKEN = ''
CK_JAR_PATH = r'D:\Pedro\puc\6Semestre\Laboratorio\Analyze-java-repositories\ck\target\ck-0.7.1-SNAPSHOT-jar-with-dependencies.jar'
CLONE_DIR = r'repos'
OUTPUT_DIR = r'output'

# Nova consulta GraphQL
QUERY = """
query ($cursor: String) {
  search(query: "language:Java stars:>100", type: REPOSITORY, first: 100, after: $cursor) {
    pageInfo {
      endCursor
      hasNextPage
    }
    edges {
      node {
        ... on Repository {
          name
          owner {
            login
          }
          stargazerCount
          url
        }
      }
    }
  }
}
"""

# Função para buscar repositórios
def fetch_repositories():
    repositories = []
    cursor = None
    has_next_page = True

    while has_next_page and len(repositories) < 1000:
        response = requests.post(
            GITHUB_API_URL,
            headers={'Authorization': f'Bearer {GITHUB_TOKEN}'},
            json={'query': QUERY, 'variables': {'cursor': cursor}}
        )

        # Verificar se a resposta tem erro
        if response.status_code != 200:
            print(f"Erro na resposta da API: {response.status_code} {response.text}")
            break

        response_json = response.json()

        if 'errors' in response_json:
            print(f"Erros retornados pela API: {response_json['errors']}")
            break

        if 'data' not in response_json:
            print(f"Resposta inesperada: {response_json}")
            break

        data = response_json['data']['search']
        repositories.extend(data['edges'])
        cursor = data['pageInfo']['endCursor']
        has_next_page = data['pageInfo']['hasNextPage']

    return repositories[:1000]

# Função para clonar repositórios
def clone_repository(owner, repo_name):
    repo_url = f"https://github.com/{owner}/{repo_name}.git"
    repo_dir = os.path.join(CLONE_DIR, repo_name)
    if not os.path.exists(repo_dir):
        subprocess.run(["git", "clone", repo_url, repo_dir])
    return repo_dir

# Função para analisar repositórios com CK
def analyze_with_ck(repo_dir, output_file):
    subprocess.run([
        "java", "-jar", CK_JAR_PATH, repo_dir
    ])

# Função principal para coordenar o processo
def main():
    if not os.path.exists(CLONE_DIR):
        os.makedirs(CLONE_DIR)
    if not os.path.exists(OUTPUT_DIR):
        os.makedirs(OUTPUT_DIR)

    # Passo 1: Buscar os repositórios
    repos = fetch_repositories()

    # Passo 2: Clonar e analisar cada repositório
    for repo in repos:
        owner = repo['node']['owner']['login']
        name = repo['node']['name']
        print(f"Clonando repositório {owner}/{name}...")
        repo_dir = clone_repository(owner, name)
        output_file = os.path.join(OUTPUT_DIR, f"{name}.csv")
        print(f"Analisando repositório {owner}/{name} com CK...")
        analyze_with_ck(repo_dir, output_file)
        print(f"Análise concluída para {owner}/{name}, resultados salvos em {output_file}.")

if __name__ == "__main__":
    main()
