import os
import subprocess
import requests
import shutil
import stat

# Configurações
GITHUB_API_URL = 'https://api.github.com/graphql'
GITHUB_TOKEN = ''
CK_JAR_PATH = r'C:\Users\giova\OneDrive\Documentos\Puc\6 periodo\Analyze-java-repositories\ck\target\ck-0.7.1-SNAPSHOT-jar-with-dependencies.jar' #Mudar aqui
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
def analyze_with_ck(repo_dir, output_dir, name):
    # Executa o CK e espera até que ele termine
    process = subprocess.Popen(
        ["java", "-jar", CK_JAR_PATH, repo_dir],
        stdout=subprocess.PIPE, stderr=subprocess.PIPE
    )
    stdout, stderr = process.communicate()  # Espera o comando terminar
    if process.returncode != 0:
        print(f"Erro ao executar CK para {repo_dir}: {stderr.decode('utf-8')}")
    else:
        print(f"Análise concluída para {repo_dir}")
    
    # Verifica se os arquivos .csv foram gerados na pasta raiz do projeto
    for file_name in ["class.csv", "method.csv", "variable.csv", "field.csv"]:
        src_file = os.path.join(os.getcwd(), file_name)  # Arquivos gerados na pasta raiz
        dst_file = os.path.join(output_dir, file_name)  # Destino correto no diretório de saída
        
        if os.path.exists(src_file):
            shutil.move(src_file, dst_file)  # Mover os arquivos da raiz para o diretório de saída
        else:
            print(f"Erro: {file_name} não foi encontrado no diretório {os.getcwd()}.")


# Função para alterar permissões de arquivos e diretórios antes de removê-los
def handle_remove_readonly(func, path, exc):
    exc_type, exc_value, exc_tb = exc
    if exc_type is PermissionError:
        # Tenta alterar as permissões do arquivo e remover novamente
        os.chmod(path, stat.S_IWRITE)
        func(path)
    else:
        raise

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

        # Criar diretório de saída específico para cada repositório
        repo_output_dir = os.path.join(OUTPUT_DIR, name)
        if not os.path.exists(repo_output_dir):
            os.makedirs(repo_output_dir)

        print(f"Analisando repositório {owner}/{name} com CK...")
        analyze_with_ck(repo_dir, repo_output_dir, name)
        print(f"Análise concluída para {owner}/{name}, resultados salvos em {repo_output_dir}.")

        # Verificar se os arquivos .csv foram movidos antes de excluir o repositório
        csv_files = ["class.csv", "method.csv", "variable.csv", "field.csv"]
        all_csv_moved = all(os.path.exists(os.path.join(repo_output_dir, file)) for file in csv_files)
        
        if all_csv_moved:
            # Remover o repositório clonado após a análise
            print(f"Removendo clone do repositório {owner}/{name}...")
            shutil.rmtree(repo_dir, onerror=handle_remove_readonly)
        else:
            print(f"Erro: Nem todos os arquivos CSV foram movidos corretamente para {repo_output_dir}. Repositório {name} não será excluído.")

if __name__ == "__main__":
    main()
