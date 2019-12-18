# LaboratoryApi
Api para gerenciamento de laboratórios e exames

# AMBIENTE
- Aplicação desenvolvida em Java 13;
- IDE Intellij
- Sistema operacional Mac os
- Spring 2.2.1
- Banco de Dados PostgreSql
- Docker utilizado para subir o banco de dados

# ENDPOINTS

### EXAMES

#### json sample exame:
{"id":0,"name":"EXAM 4","type":"IMAGE","status":"ACTIVE"}

##### (POST) - /v1/exam/create 
  Endpoint para insert de um exame, objeto exame deve ser enviado no body
  
##### (POST) - /v1/exam/createInBlock
  Endpoint para inserir varios exames ao mesmo tempo, objeto lista de exame deve ser enviado no body. Todos os objetos criados serão retornados no json de retorno.
  
##### (PUT) - /v1/exam/put/{id}
  Endpoint para atualizar um exame, id deve ser enviado na url, objeto exame deve ser enviado no body
  
##### (PUT) - /v1/exam/putInBlock
  Endpoint para atualizar varios exames ao mesmo tempo, objeto lista de exame deve ser enviado no body, para update em block         não é necessário o envio do id na url, porem a lista deve conter o id do objeto a ser alterado. Todos os registros alterados serão retornados no json de retorno.
  
##### (GET) - /v1/exam/findByName/{name}
  Endpoint para buscar um exame pelo nome, o parametro nome deve ser passado na url
  
##### (GET) - /v1/exam/getAll
  EndPoint para retornar todos os exames cadastrados na base, exames ativos e inativos
  
##### (GET) - /v1/exam/getAllActive
  Endpoint para retornar todos os exames cadastrados na base com status ativo
  
##### (DELETE) - /v1/exam/delete/{id}
  Endpoint para deletar um exame pelo id, o id deve ser informado na url.
  
##### (DELETE) - /v1/exam/deleteInBlock
  Endpoint para deletar varios exames ao mesmo tempo, uma lista com id dos exames a serem deletados deve ser passada no body. Todos os registros deletados serão retornados no json de retorno.

### LABORATORIOS

#### json sample exame:
{"id":0, "name":"Tiago laboratories", "address":"avenida pereira Barreto n 1600",
 "status":"ACTIVE", "exams":[{"id":1, "name":"EXAM 4", "type":"IMAGE", "status":"ACTIVE"}]}

##### (POST) - /v1/laboratory/create 
  Endpoint para insert de um laboratorio, objeto laboratorio deve ser enviado no body

##### (POST) - /v1/laboratory/createInBlock 
  Endpoint para inserir varios exames ao mesmo tempo, objeto lista de exame deve ser enviado no body. Todos os objetos criados serão retornados no json de retorno.

##### (PUT) - /v1/laboratory/put/{id}
  Endpoint para atualizar um laboratorio, id deve ser enviado na url, objeto laboratorio deve ser enviado no body
  
##### (PUT) - /v1/laboratory/putInBlock
  Endpoint para atualizar varios laboratorios ao mesmo tempo, objeto lista de laboratorio deve ser enviado no body, para update em block não é necessário o envio do id na url, porem a lista deve conter o id do objeto a ser alterado. Todos os registros alterados serão retornados no json de retorno.

##### (GET) - /v1/laboratory/findByName/{name}
  Endpoint para buscar um laboratorio pelo nome, o parametro nome deve ser passado na url
  
##### (GET) - /v1/laboratory/getAll
  EndPoint para retornar todos os laboratorios cadastrados na base, laboratorios ativos e inativos, com todos exames associados ativos e inativos
  
##### (GET) - /v1/laboratory/getAllActive
  Endpoint para retornar todos os laboratorios cadastrados na base com status ativo, e os exames associados com o status ativo
  
##### (GET) - /v1/laboratory/getAllActiveByExamName/{examName}
  EndPoint para retornar todos os laboratorios que estao relacionados ao exame informado na url e tem o status ativo, e todos os exames ativos associados a esses laboratorios
  
##### (GET) - /v1/laboratory/getAllByExamName/{examName}
  Endpoint para retornar todos os laboratorios que estao associados ao exame informado na url, sejam eles ativos ou inativos, e todos os exames ativos e inativos associados a esses laboratorios

##### (DELETE) - /v1/laboratory/delete/{id}
  Endpoint para deletar um laboratorios pelo id, o id deve ser informado na url.
  
##### (DELETE) - /v1/laboratory/deleteInBlock
  Endpoint para deletar varios laboratorios ao mesmo tempo, uma lista com id dos laboratorios a serem deletados deve ser passada no body. Todos os registros deletados serão retornados no json de retorno.

# ARQUITETURA DO PROJETO

O projeto foi desenvolvido seguindo a [Clean Archtecture](https://static.imasters.com.br/wp-content/uploads/2018/10/07222901/conn.jpg), e foi dividido da seguinte forma:

#### USE CASES 
A regra de negócio fica nessa camada.

#### ADAPTERS
Nessa camada ficam os repositorys, o gateway que é responsável por relacionar regra de negocio com os repositorys e converter entidades em contratos e vice versa, nessa camada também fica os controllers.

#### ENTITIES
Nessa camada ficam as entidades mapeadas para uso do jpa, os contratos que é a entidade que a regra de negocio conhece e o converter que nada mais é do que a conversão de uma entidade para contrato e vice versa.

#### FRAMEWORK AND DRIVES
Nessa camada ficam todos os perifericos da aplicação, como tratamento generico de exceptions, conversor de json, etc.

# SCRIPT UTILIZADO PARA SUBIR O DOCKER PARA BD
docker pull postgres:alpine

docker run --name postgresLab -e POSTGRES_PASSWORD=tiago -d -p 5432:5432 postgres:alpine

docker exec -it postgresLab bash

psql -U postgres

create database laboratories;

# PONTOS A MELHORAR NO PROJETO

- ##### Incluir a aplicação em um container do docker
  Tive problemas para relacionar a aplicação em um container, com o banco de dados que esta em outro container, estou estudando como resolver esse problema, porém não a tempo de fazer a entrega, por isso somente o banco de dados esta em container.

- ##### Erro ao utilizar a anotação @SQLDelete(sql = "UPDATE tb_exam SET deleted = 1 WHERE id = ?")
  Eu tentei por diversas vezes utilizar essa anotação para fazer o delete logico, porém sem sucesso, obtive varios erros retornados do hibernate, e então segui por um caminho diferente(utilizar o update para fazer delete logico).
  
- ##### Tratamento genérico de erros, nem sempre captura os erros
  Foi criada a classe GlobalExceptionHandlers utilizando a anotação @ControllerAdvice porém, por um motivo que ainda desconheço, essa classe captura apenas erros de metodos ja previamente definidos nos repositorys como por exemplo o findById, outros metodos criados por mim, mesmo nao encontrado dados, ele nao captura a exceção.
  
# CONSIDERAÇÕES FINAIS
  O Projeto foi desenvolvido por mim, e tive a oportunidade de aprender muito com ele, fico muito feliz de ter participado desse processo seletivo. Se possível gostaria de pedir um feedback sobre o código para que eu possa melhorar e aprender mais.
  
  Me coloco a disposição para quaisquer dúvidas que venham a surgir, mais uma vez obrigado pela oportunidade.
  
 TIAGO CALIXTO




