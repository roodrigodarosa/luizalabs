## _1 - Pré requisitos_

 - Java 11
 - Maven
 - Docker


 ##  _2 - Build e testes_
##### Para realizar o build feito pelo maven, utilize o comando:
```
mvn clean install
```

Obs.: Junto com o comando acima, serão rodados os testes unitários.

##### Para executar apenas testes, utilize:
```
mvn clean test
```
##### Para executar uma classe específica de teste, utilize:
```
mvn clean test -Dtest=SchedulingServiceTest
```
##### Para executar um método específico de teste, utilize:
```
mvn clean test -Dtest=SchedulingServiceTest#whenSaveSchedulingThenReturnOk
```

## _3 - Inicialização_
-  Após realizar o build do projeto, navegue até a pasta `docker` e execute:

```
docker-compose -f compose.yml up --build
```
- Serviço estará disponível em http://localhost:8080/schedulings

## _4 - Utilizando a aplicação_

##### Criação de agendamento:

   Utilizar o endpoint  `@POST /schedulings` - http://localhost:8080/schedulings
```
    {
        "scheduledDate": "2021-08-21T18:25:43-05:00",
        "recipient": "teste@luizalabs.com.br",
        "notificationType": "EMAIL",
        "message": "Teste de agendamento"
    }
```

##### Consulta de todos agendamentos:

 Utilizar o endpoint  `@GET /schedulings` - http://localhost:8080/schedulings

- Irá retornar os agendamentos cadastrados.

##### Consulta de um determinado agendamento:

Utilizar o endpoint  `@GET /schedulings/{id}` - http://localhost:8080/schedulings/{id}

- Irá retornar o agendamento solicitado.

##### Remover um determinado agendamento:

Utilizar o endpoint  `@DELETE /schedulings/{id}` - http://localhost:8080/schedulings/{id}

- Irá remover o agendamento solicitado.
