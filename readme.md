# CepApi

Essa API foi desenvolvida para um teste da empresa Magalu. Para seu desenvolvimento, foi utilizado Java 11. Uma das
questões do desafio era escolher entre Node ou Java. Ambas teriam um bom fit com a aplicação, portanto, a preferência
por Java deve-se ao fato de maior familiriadade do desenvolvedor com o ecosistema.

Além de Java 11, também foi utilizado o framework Spring Boot 2.4.5 e as seguintes bibliotecas:

* Spring Web - para construção da api REST.
* Spring Data - para mapeamento do banco de dados.
* Spring Actuator - para expor endpoints para checar a saúde da aplicação.
* Spring Devtools - para facilitar o desenvolvimento.
* Lombok - para diminuir a quantidade de códigos genéricos (boilerplate).
* H2 database - um banco de dados em memória para minimizar a infraestrutura necessária.
* jUnit 5 e Mockito - para testes unitários.
* Swagger - para documentar a api de forma automática.

Ao inicializar a aplicação em dev, ela ficará disponível na porta 8080 do localhost:

```
localhost:8080
```

Além disso, os endpoint do Actuator e do Swagger são, respectivamente:

```
localhost:8080/actuator
localhost:8080/swagger-ui.html
```

Para melhorar a performance da aplicação, seria interessante adicionar um sistema de cache com o banco de dados Redis.

## Estratégias

Para desenvolvimento da API REST, foi utilizado o padrão MVC. As camadas são organizadas da seguinte maneira:

* controller - camada que recebe as requisições e retorna dos dados.
* service - camada com toda a lógica de negócio.
* repository - camada de acesso ao banco.
* model - modelagem das entidades e mapeamento do banco de dados (ORM).

As camadas mais acima apenas podem acessar as camadas mais abaixo, ou seja:

```
controller => service => repository
```

Além disso, para com o objetivo de melhorar a performance da aplicação, foi criado um index decrescente para a coluna
CEP do banco de dados, a qual é utilizada na buscas por CEP.

# Question 2

Quando você digita a URL de um site (http://www.netshoes.com.br) no browser e pressiona enter, explique da forma que
preferir, o que ocorre nesse processo do protocolo HTTP entre o Client e o Server.

1. Inicialmente é feita a resolução do DNS.
    * Inicialmente, é feita uma requisição para um dos servidores ROOT, para resolver o .br;
    * O servidor ROOT delega a próxima resolução para os servidores do registro.br, que resolve .com.br para os
      servidores DNS na netshoes;
    * O servidor DNS da netshoes resolve o registro do tipo A para o ip da aplicação.
2. O browser faz uma requisição com o método GET para o ip da aplicação, na porta 80 (padrão), para o recurso '/'.
3. O servidor da aplicação retorna uma resposta HTTP. Provavelmente uma página HTML nesse caso.
