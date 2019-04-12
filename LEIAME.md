# CK

[![Build Status](https://travis-ci.org/mauricioaniche/ck.svg?branch=master)](https://travis-ci.org/mauricioaniche/ck)

O CK calcula as métricas de código em nível de classe e nível de métrica em projetos Java por meio de
de análise estática (ou seja, não há necessidade de código compilado). Atualmente, contém
um grande conjunto de métricas, incluindo o famoso CK:

- * CBO (Coupling between objects) *: Conta o número de dependências de uma classe.
As ferramentas verificam qualquer tipo usado em toda a classe (declaração de campo, método
tipos de retorno, declarações de variáveis, etc). Ele ignora as dependências do próprio Java
(por exemplo, java.lang.String).

- * DIT (Depth Inheritance Tree) *: Conta o número de "pais" que uma classe possui.
Todas as classes têm DIT pelo menos 1 (todo mundo herda java.lang.Object).
Para que isso aconteça, as classes devem existir no projeto (ou seja, se uma
depende de X que depende de um arquivo jar / dependência, e X depende de outro
classes, o DIT é contado como 2).

- * Número de campos *: conta o número de campos. Números específicos para
número total de campos, estático, público, privado, protegido, padrão, final e campos sincronizados.

- * Número de métodos *: conta o número de métodos. Números específicos para
número total de métodos, estático, público, abstrato, privado, protegido, padrão, final e métodos sincronizados.

- * NOSI (Número de invocações estáticas) *: Conta o número de invocações
para métodos estáticos. Só pode contar os que podem ser resolvidos pelo
JDT.

- * RFC (Response for a Class) *: Conta o número do método exclusivo
invocações em uma classe. Como as invocações são resolvidas por meio de análise estática,
esta implementação falha quando um método tem sobrecargas com o mesmo número de parâmetros,
mas tipos diferentes.

- * WMC (Weight Method Class) * ou * complexidade de McCabe *. Conta o número
de instruções de filial em uma classe.

- * LOC (Linhas de código) *: Conta as linhas de contagem, ignorando
linhas vazias.

- * LCOM (Falta de Coesão dos Métodos) *: Calcula a métrica de LCOM. Este é o primeiro
versão da métrica, que não é confiável. O LCOM-HS pode ser melhor (espero que você
envie-nos um pedido pull).

- * Quantidade de devoluções *: o número de instruções de "retorno".

- * Quantidade de loops *: o número de loops (por exemplo, while, while, enhanced for).

- * Quantidade de comparações *: o número de comparações (ou seja, ==).

- * Quantidade de try / catches *: o número de try / catches

- * Quantidade de expressões com parênteses *: o número de expressões dentro de parênteses.

- * Literais de string *: o número de literais de string (por exemplo, `" John Doe "`). Seqüências repetidas contam quantas vezes elas aparecerem.

- * Quantidade de Número *: O número de números (isto é, int, long, double, float) literais.

- * Quantidade de Operações Matemáticas *: O número de operações matemáticas (horas, dividir, resto, mais, menos, esquerda, direita).

- * Quantidade de Variáveis ​​*: Número de variáveis ​​declaradas.

- * Máximo de blocos aninhados *: O maior número de blocos aninhados juntos.

- * Quantidade de classes anônimas, subclasses e expressões lambda *. O nome diz tudo.

- * Número de palavras únicas *. Número de palavras únicas no código-fonte. Veja a classe `WordCounter` para detalhes sobre o
implementação.

- * Uso de cada variável *. Quanto cada variável foi usada dentro de cada método.

- * Uso de cada campo *. Quanto cada campo foi usado dentro de cada método.

(Em uma versão anterior, calculou NOC (Number of Children), mas não faz mais isso,
como requer muita memória.)

Modo de uso: README.md
