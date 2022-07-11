
# Back-end - Gerenciador-de-projetos

Gerencia quanto tempo vc passa em um projeto, quais tecnologias utilizou e outras funcionalidades.

## Tecnologias

Este projeto foi desenvolvido com as seguintes Tecnologias

- Java
- Spring Boot
- MySQL
- Mavem
- Postman
- FlyWay

## Instruções para execução

Acesse o arquivo `application.properties` e mude o nome de usuario e a senha para os dados do seu banco de dados MySQL
 

Rotas: 
```sh
    localhost:8080/users
    localhost:8080/projects 
```
## API Reference

### Users 

#### Listar todos os Usuários cadastrados

```http
  GET /users
```

| Parametros | Tipo     | Descrição                |
| :-------- | :------- | :------------------------- |
|  | | Este endpoint retorna todos os usuários cadastrados no sitema |

#### Response

| Code | Descrição                     |
| :--------  | :-------------------------------- |
| `200`       | Uma lista (Array) de usuários |

#### Listar usuário por id

```http
  GET /users/{id}
```

| Parametros | Tipo     | Descrição                     |
| :-------- | :------- | :-------------------------------- |
| `id`      | `Integer` | Este endpoint retorna apenas o usuário a qual foi informado o id |

#### Response

| Code | Descrição                     |
| :--------  | :-------------------------------- |
| `200`       | Usuário encontrado |
| `404`       | Usuário não encontrado |


#### Cadastrar um usuário no sistema

```http
  POST /users/
```

| Parametros | Tipo     | Descrição                     |
| :-------- | :------- | :-------------------------------- |
| `user`      | `Object` | Este endpoint cadastra um usuário no sistema |

#### Response

| Code | Descrição                     |
| :--------  | :-------------------------------- |
| `201`       | Usuário cadastrado com sucesso |
| `400`       | Um ou mais campos estão inválidos |

#### Atualizar usuário no sistema

```http
  PUT /users/{id}
```

| Parametros | Tipo     | Descrição                     |
| :-------- | :------- | :-------------------------------- |
| `id`      | `Integer` | Este endpoint atualiza um usuário no sistema |

#### Response

| Code | Descrição                     |
| :--------  | :-------------------------------- |
| `200`       | Usuário atualizado com sucesso |
| `400`       | Um ou mais campos estão inválidos |
| `404`       | Usuário não encontrado |

#### Deletar usuário no sistema

```http
  DELETE /users/{id}
```

| Parametros | Tipo     | Descrição                     |
| :-------- | :------- | :-------------------------------- |
| `id`      | `Integer` | Este endpoint deleta um usuário no sistema |

#### Response

| Code | Descrição                     |
| :--------  | :-------------------------------- |
| `204`       | Usuário deletado com sucesso |
| `404`       | Usuário não encontrado |

### Projects

#### Listar todos os Projetos cadastrados

```http
  GET /projects
```

| Parametros | Tipo     | Descrição                |
| :-------- | :------- | :------------------------- |
|  | | Este endpoint retorna todos os projetos cadastrados no sitema |

#### Response

| Code | Descrição                     |
| :--------  | :-------------------------------- |
| `200`       | Uma lista (Array) de projetos |

#### Listar todos os projetos cadastrados de um determinado usuário

```http
  GET /projects/user/{idUser}
```

| Parametros | Tipo     | Descrição                |
| :-------- | :------- | :------------------------- |
| `id` | `Integer`| Este endpoint retorna todos os projetos cadastrados no sitema pelo usuário do id informado |

#### Response

| Code | Descrição                     |
| :--------  | :-------------------------------- |
| `200`       | Uma lista (Array) de projetos |

#### Listar projeto por id

```http
  GET /projects/{id}
```

| Parametros | Tipo     | Descrição                     |
| :-------- | :------- | :-------------------------------- |
| `id`      | `Integer` | Este endpoint retorna apenas o projeto a qual foi informado o id |

#### Response

| Code | Descrição                     |
| :--------  | :-------------------------------- |
| `200`       | Projeto encontrado |
| `404`       | Projeto não encontrado |


#### Cadastrar um projeto no sistema

```http
  POST /projects/
```

| Parametros | Tipo     | Descrição                     |
| :-------- | :------- | :-------------------------------- |
| `project`      | `Object` | Este endpoint cadastra um projeto no sistema |

#### Response

| Code | Descrição                     |
| :--------  | :-------------------------------- |
| `201`       | Projeto cadastrado com sucesso |
| `400`       | Um ou mais campos estão inválidos |


#### Deletar projeto no sistema

```http
  DELETE /projects/{id}
```

| Parametros | Tipo     | Descrição                     |
| :-------- | :------- | :-------------------------------- |
| `id`      | `Integer` | Este endpoint deleta um projeto do sistema |

#### Response

| Code | Descrição                     |
| :--------  | :-------------------------------- |
| `204`       | Projeto deletado com sucesso |
| `404`       | Projeto não encontrado |

#### Inicializar projeto

```http
    GET /projects/inicializar/{id}
```

| Parametros | Tipo     | Descrição                     |
| :-------- | :------- | :-------------------------------- |
| `id`      | `Integer` | Este endpoint inicializa um projeto do sistema  |

#### Response

| Code | Descrição                     |
| :--------  | :-------------------------------- |
| `200`       | Projeto inicializado com sucesso |

#### Finalizar projeto

```http
    GET /projects/finalizar/{id}
```

| Parametros | Tipo     | Descrição                     |
| :-------- | :------- | :-------------------------------- |
| `id`      | `Integer` | Este endpoint finaliza um projeto do sistema  |

#### Response

| Code | Descrição                     |
| :--------  | :-------------------------------- |
| `200`       | Projeto finalizado com sucesso |

### Technologies

#### Cadastrar uma tecnologia em um projeto do sistema

```http
  POST /projects/{id}/technologies
```

| Parametros | Tipo     | Descrição                     |
| :-------- | :------- | :-------------------------------- |
| `technology`      | `Object` | Este endpoint cadastra uma tecnologia no projeto do id informado |

#### Response

| Code | Descrição                     |
| :--------  | :-------------------------------- |
| `201`       | Tecnologia cadastrado com sucesso |
| `400`       | Um ou mais campos estão inválidos |
| `404`       | Projeto não encontrado |

#### Listar todas as tecnologias de um determinado projeto cadastrados

```http
  GET /projects/{id}/technologies
```

| Parametros | Tipo     | Descrição                |
| :-------- | :------- | :------------------------- |
| `id`  | `Integer` | Este endpoint retorna todas as tecnologias cadastrados no projeto |

#### Response

| Code | Descrição                     |
| :--------  | :-------------------------------- |
| `200`       | Uma lista (Array) de tecnologias |

## Inspiração

https://wakatime.com/
