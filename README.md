<!-- omit from toc -->
# FIAP - SOAT1 - GRUPO 18

## Fase 4 - Microsserviço - Pagamento
Microsserviço de Pagamento criado durante a fase 4 da Pós Tech Software Architecture da FIAP

![](https://github.com/pabloldias/soat1-grupo18/actions/workflows/build-and-test.yml/badge.svg)

- [Tech Challenge](#tech-challenge)
  - [Alunos](#alunos)
  - [Tecnologias utilizadas](#tecnologias-utilizadas)
  - [Como rodar a aplicação via docker-compose?](#como-rodar-a-aplicação-via-docker-compose)
  - [Como rodar a aplicação via kubernetes?](#como-rodar-a-aplicação-via-kubernetes)
  - [Swagger](#swagger)
  - [Insomnia](#insomnia)
  - [Event Storming](#event-storming)


## Tech Challenge

Projeto realizado durante o quarto módulo da Pós Tech Software Architecture da FIAP.

### Alunos

|                                           Nome |     RM     |
|-----------------------------------------------:| :--------: |
|                   Elvis Freitas Lopes Herllain | `rm349139` |
|                           Gisele Mara Leonardi | `rm349242` |
|                  Leandro Gonçalves de Oliveira | `rm348615` |
|                       Marcos Venâncio de Souza | `rm349251` |
|                                Pablo Lima Dias | `rm349149` |

### Tecnologias utilizadas

- Spring Boot 3.1
- DynamoDB
- Java 17
- Cucumber
- Junit
- Docker
- Github Actions
  - Para validar o docker-compose.yaml e fazer alguns testes e2e

### Como rodar a aplicação via docker-compose?

Para levantar a API junto com o banco de dados Postgres, execute na pasta raiz:

```bash
docker compose up
```

### Como rodar a aplicação via kubernetes?

Para levantar a API junto com o banco de dados Postgres, execute na pasta raiz:

```bash
kubectl apply -f kubernetes/
```

> O kubernetes vai rodar a imagem `olegon/soat1-grupo18-api:1.1.1`.

> Se tiver algum problema com o Service do tipo LoadBalancer, o comando `kubectl port-forward services/food-api 8080:80` pode ser útil.

### Swagger

Para acessar o Swagger da aplicação, acesse o caminho [/swagger-ui/index.html](http://localhost:8083/swagger-ui/index.html) da aplicação.

### Insomnia

Há um arquivo [insomnia-collection.yml](insomnia-collection.yml) na raiz do repositório.

### Event Storming

Nosso *event storming* está disponível em um [board do Miro](https://miro.com/app/board/uXjVMHS5nec=/).

