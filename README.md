# Testes automatizados - ServeRest API

Projeto de automação criado como parte de um desafio técnico.

A proposta foi validar os endpoints da API ServeRest
(https://serverest.dev), com foco no recurso de usuários, cobrindo
operações de cadastro, consulta, alteração e exclusão.

A estrutura foi pensada de forma simples, mas organizada, seguindo o
padrão que utilizo no dia a dia, priorizando clareza, reaproveitamento e
fácil manutenção.

------------------------------------------------------------------------

## Clonar o repositório

Para clonar o projeto:

``` bash
git clone https://github.com/Raimundog4/carrefour-api-automacao.git
```

Acesse a pasta do projeto:

``` bash
cd carrefour-api-automacao
```

------------------------------------------------------------------------

## Tecnologias utilizadas

-   Java 17
-   Maven
-   Cucumber (BDD)
-   RestAssured
-   JUnit
-   Jackson (serialização JSON)
-   GitHub Actions (CI)

------------------------------------------------------------------------

## Estrutura do projeto

Mantive a estrutura simples, separando responsabilidades principais:

-   `config` → definição de endpoints e base URI
-   `factory` → criação de dados dinâmicos para os testes
-   `services` → centralização das chamadas HTTP
-   `steps` → implementação dos passos BDD
-   `runners` → execução dos testes
-   `features` → cenários em Gherkin

Essa divisão ajuda a evitar duplicação de código e facilita a
manutenção.

------------------------------------------------------------------------

## Cenários cobertos

Foram implementados cenários cobrindo o CRUD completo de usuários:

-   Cadastro de usuário com dados válidos
-   Cadastro com dados inválidos (email inválido, campos vazios, etc.)
-   Cadastro com email já existente
-   Listagem de usuários
-   Consulta por ID
-   Consulta com filtros (email, nome)
-   Alteração de usuário
-   Exclusão de usuário

Também foram incluídos cenários negativos importantes:

-   Campos obrigatórios não informados
-   Validação de erro por campo
-   ID inválido (tamanho diferente de 16 caracteres)
-   Exclusão de usuário inexistente
-   Exclusão de usuário já removido

------------------------------------------------------------------------

## Como executar

Para rodar os testes:

``` bash
mvn clean test
```

Para execução completa com geração de relatório:

``` bash
mvn clean verify
```

Ou executar diretamente a classe runner:

    RunServeRestTests

------------------------------------------------------------------------

## Relatório

Após a execução, o relatório pode ser acessado em:

    target/cucumber-reports/cucumber-basic.html

------------------------------------------------------------------------

## Integração contínua (CI)

O projeto está configurado com GitHub Actions.

A cada push, os testes são executados automaticamente, garantindo
validação contínua.

Os relatórios ficam disponíveis como artefato da execução da pipeline.

------------------------------------------------------------------------

## Observações

O foco do projeto foi demonstrar:

-   Organização de código
-   Uso de boas práticas em automação de API
-   Estrutura reutilizável
-   Clareza na escrita dos cenários BDD

A ideia foi manter o projeto direto ao ponto, sem complexidade
desnecessária, mas com qualidade suficiente para representar um cenário
real de trabalho.

------------------------------------------------------------------------

## Autor

José Raimundo
