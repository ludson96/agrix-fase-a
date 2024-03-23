# Repositório do projeto `Agrix - Fase A` 🚜

Repositório possuí projeto desenvolvido abordando conceitos
de `List`, `Set`, `Streams`, `JPA`, `Spring Data`, `Hibernate` e `Relacionamento de tabelas`.

## Informações de aprendizados

- Primeiro projeto usando `List`, `Set`, `Streams`, `JPA`, `Spring Data`, `Hibernate`
- Segundo projeto usando `Spring e seus complementos`;
- Segundo projeto em `Java` usando `API REST`;
- Segundo projeto em `Java` usando `Docker`.

## Linguagens e ferramentas usadas

[![Git][Git-logo]][Git-url]
[![Java][Java-logo]][Java-url]
[![Apache Maven][Apache Maven-logo]][Apache Maven-url]
[![Docker][Docker-logo]][Docker-url]
[![Spring][Spring-logo]][Spring-url]
[![Spring Boot][Spring boot-logo]][Spring boot-url]
[![Hibernate][Hibernate-logo]][Hibernate-url]

## O que foi desenvolvido
   
Neste projeto desenvolvi uma aplicação Spring Boot com algumas funcionalidades iniciais para uma empresa fictícia, a AgroTech, uma empresa especializada em tecnologias para melhorar a eficiência no cultivo de plantações. Isso visa reduzir o desperdício de recursos em geral e de alimentos em específico, fazendo um uso mais responsável da terra disponível para plantio.

O primeiro produto dessa empresa será o Agrix, um sistema que permitirá a gestão e o monitoramento das fazendas participantes. Esse produto será desenvolvido em fases.

## Habilidades trabalhadas

- Aplicar o conhecimento do ecossistema Spring para criar rotas da API.
- Aplicar a injeção de dependência para conectar as camadas de controle, serviço e persistência.
- Utilizar o Spring Data JPA para implementar entidades e repositórios para a persistência em banco de dados.
- Implementar gerenciamento de erros no Spring Web.
- Criar o Dockerfile para configurar a aplicação para execução no Docker.

## Instruções para instalar e rodar

<details>

1. Clone o repositório (recomendado usar em SSH) e entre na pasta:

    ```bash
    git clone git@github.com:ludson96/agrix-fase-a.git
    cd agrix-fase-a
    ```

1. Instale as dependências:

    ```bash
    mvn install
    ```

1. Caso não tenha jdk ou maven instalados, basta executar o `Docker` com o comando abaixo:

   ```bash
   #Comando para gerar imagem.
   docker build . -t multi-stage-image
   
   #Comando para executar o container usando a imagem gerada anteriormente. Irá executar o servidor Spring automaticamente e podendo ignorar o passo abaixo.
   docker run -p 8080:8080 --name multi-stage-container multi-stage-image
   ```
1. Para executar o servidor spring:

    ```bash
   mvn clean package
   java -jar target/agrix-1.0-SNAPSHOT.jar
    ```

</details>

## Detalhamento de execução

<details>

  <summary><strong>Farm</strong></summary>

### Endpoints

### 1. `POST /farms`

<details>
  <summary>Cria uma nova fazenda</summary><br />

Funciona da seguinte forma:

- `/farms` (`POST`)
   - deve receber via corpo do POST os dados de uma fazenda. 
     - Exemplo de requisição:
        ```json
        {
          "name": "Fazendinha",
          "size": 5
        }
        ```
   - em caso de sucesso:
      - retornar o status HTTP 201 (CREATED)
      - retornar os dados da fazenda criada. O `id` da fazenda esta incluso na resposta. 
        - Exemplo de resposta:

        ```json
        {
          "id": 1,
          "name": "Fazendinha",
          "size": 5
        }
        ```

</details>


### 2. `GET /farms`

<details>
  <summary>Retorna todas as fazendas cadastradas </summary><br />

Funciona da seguinte forma:

- `/farms` (`GET`)
   - retorna uma lista de todas as fazendas. O `id` da fazenda esta
     incluso na resposta. 
     - Exemplo de resposta:

        ```json
        [
          {
            "id": 1,
            "name": "Fazendinha",
            "size": 5.0
          },
          {
            "id": 2,
            "name": "Fazenda do Júlio",
            "size": 2.5
          }
        ]
        ```

</details>

### 3. `GET /farms/{id}`

<details>
  <summary>Retorna informações de uma fazenda especifica</summary><br />

Funciona da seguinte forma:

- `/farms/{id}` (`GET`):
   - recebe um `id` pelo caminho da rota e retorna a fazenda com esse `id`. O `id` da
     fazenda esta incluso na resposta. 
     - Exemplo de resposta para a rota `/farms/3` (supondo que exista uma fazenda com `id = 3`):

        ```json
        {
          "id": 3,
          "name": "My Cabbages!",
          "size": 3.49
        }
        ```
   - caso não exista uma fazenda com esse `id`, a rota retorna o status HTTP 404 com a
     mensagem `Fazenda não encontrada!` no corpo da resposta.

</details>

### 4. `POST /farms/{farmId}/crops`

<details>
  <summary>Cria uma nova plantação</summary><br />

Funciona da seguinte forma:

- `/farms/{farmId}/crops` (`POST`)
   - recebe o `id` da fazenda pelo caminho da rota (representado aqui por `farmId` apenas para diferenciar da plantação)
   - recebe via corpo do POST os dados da plantação e salva a nova plantação a partir dos dados recebidos, associada à fazenda com o ID
     - Exemplo de requisição na rota `/farms/1/crops` (supondo que exista uma fazenda com `id = 1`):

        ```json
        {
          "name": "Couve-flor",
          "plantedArea": 5.43
        }
        ``` 
   - em caso de sucesso:
      - retorna o status HTTP 201 (CREATED)
      - retorna os dados da plantação criada. A resposta inclui o `id` da plantação e
        o `id` da fazenda.
   - caso não exista uma fazenda com o `id` passado, retorna o status HTTP 404 com a
     mensagem `Fazenda não encontrada!` no corpo da resposta.
     - Exemplo de resposta:

        ```json
        {
          "id": 1,
          "name": "Couve-flor",
          "plantedArea": 5.43,
          "farmId": 1
        }
        ```

</details>

### 5. `GET /farms/{farmId}/crops`

<details>
  <summary>Lista as plantações de uma fazenda específica</summary><br />

Funciona da seguinte forma:
- `/farms/{farmId}/crops` (`GET`):
   - recebe o `id` de uma fazenda pelo caminho
   - retorna uma lista com todas as plantações associadas à fazenda
     - Exemplo de resposta para a rota `/farms/1/crops` (supondo que exista uma fazenda com `id = 1`):

        ```json
        [
          {
            "id": 1,
            "name": "Couve-flor",
            "plantedArea": 5.43,
            "farmId": 1
          },
          {
            "id": 2,
            "name": "Alface",
            "plantedArea": 21.3,
            "farmId": 1
          }
        ]
        ```
   - caso não exista uma fazenda com esse `id`, retorna o status HTTP 404 com a
     mensagem `Fazenda não encontrada!` no corpo da resposta.

</details>

### 6. `GET /crops`

<details>
  <summary>Lista todas as plantações cadastradas</summary><br />

Funciona da seguinte forma:
- `/crops` (`GET`)
   - retorna uma lista de todas as plantações cadastradas. A resposta inclui o `id` de
     cada plantação e o `id` da fazenda associada.
     - Exemplo de resposta:

        ```json
        [
          {
            "id": 1,
            "name": "Couve-flor",
            "plantedArea": 5.43,
            "farmId": 1
          },
          {
            "id": 2,
            "name": "Alface",
            "plantedArea": 21.3,
            "farmId": 1
          },
          {
            "id": 3,
            "name": "Tomate",
            "plantedArea": 1.9,
            "farmId": 2
          }
        ]
        ```

</details>

### 7. `GET /crops/{id}`

<details>
  <summary>Retorna as informações de plantações de uma fazenda especifica</summary><br />

Funciona da seguinte forma:
- `/crops/{id}` (`GET`):
   - recebe o `id` de uma plantação pelo caminho da rota
   - caso exista a plantação com o `id` recebido, retorna os dados da plantação. A resposta
     inclui o `id` de cada plantação e o `id` da fazenda associada.
     - Exemplo de resposta para a rota `/crops/3` (supondo que exista uma plantação com `id = 3`:

        ```json
        {
          "id": 3,
          "name": "Tomate",
          "plantedArea": 1.9,
          "farmId": 2
        }
        ```
   - caso não exista uma plantação com o `id` passado, retorna o status HTTP 404 com a
     mensagem `Plantação não encontrada!` no corpo da resposta.

</details>

</details>

[Git-logo]: https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white
[Git-url]: https://git-scm.com

[Java-logo]: https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white
[Java-url]: https://www.java.com/pt-BR/

[Apache Maven-logo]: https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white
[Apache Maven-url]: https://maven.apache.org/

[Docker-logo]: https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white
[Docker-url]: https://www.docker.com

[Spring-logo]: https://img.shields.io/badge/Spring-6DB33F.svg?style=for-the-badge&logo=Spring&logoColor=white
[Spring-url]: https://spring.io/

[Spring boot-logo]:https://img.shields.io/badge/Spring%20Boot-6DB33F.svg?style=for-the-badge&logo=Spring-Boot&logoColor=white
[Spring boot-url]: https://spring.io/projects/spring-boot

[Hibernate-logo]: https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white

[Hibernate-url]: https://hibernate.org/
