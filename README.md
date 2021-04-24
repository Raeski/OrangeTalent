# OrangeTalent

## SOBRE O PROJETO
Criar uma API REST utilizando Spring + Hibernate

## DESAFIO:
* Criar uma API REST com 3 endpoints
* Cadastrar usuário;
* Cadastrar endereço, esse endereço tem que estar vinculado com o usuário;
* Retornar o usuário com a lista de endereços que ele cadastrou em seu CPF; 


### Tecnologias utilizadas:
* Java
* JPA
* Spring Boot
* Sprin Cloud Feign
* Hibernate
* Lombok
* MapStruct
* MySQL

## Iniciando o Projeto

1. Como IDLE principal irei utilizar o INTELLIJ para inicializar, o intellij tem uma opção de criar um projeto em Spring e já adicionar suas depêndencias:
![image](https://user-images.githubusercontent.com/46768410/115967989-e3ccb880-a50b-11eb-979d-50704374dec1.png)
  Depêndencias adicionadas: 
      * Spring Boot DevTools - Ele faz com que seja rápido e fácil inicializar e restartar a aplicação.
      * Lombok - Gera os Getter e Setters de uma classe, Construtores, Builders.
      * Spring Web - É ele que faz a mágica acontecer em nossa aplicação APIRESTful, fazendo as requisições com os métodos HTTPS.
      * Spring Data JPA - Ele vai fazer a implementação de Spring Data e Hibernate, deixando simples as persistencias e retornos dos objetos cadastrados no Banco.
      * MySql Driver - Para conectar a nossa aplicação com o banco MySQL.

2. Projeto criado agora primeira coisa que faremos é alterar arquivo dentro de resourcer > application.properties para application.yml 
3. Adicionar as configurações para que possa ser possível fazer conexão com o MySQL.
![image](https://user-images.githubusercontent.com/46768410/115968179-ec71be80-a50c-11eb-85f8-4d16463a0fef.png)

4. Próximo passo é criar as classes que serão persistidas no Banco.

#### CLASSE ENDEREÇO:
![image](https://user-images.githubusercontent.com/46768410/115968197-0ca17d80-a50d-11eb-9eb7-d193c59473a2.png)
    * Dentro da classe endereço adicionamos todos os atributos necessários e criamos uma relação @ManyToOne com a classe Usuário

#### CLASSE USUÁRIO: 
![image](https://user-images.githubusercontent.com/46768410/115968214-1cb95d00-a50d-11eb-84b0-e5e91149798b.png)
    * Criamos todos os atributos, adicionamos as anotações do Lombok e do Hibernate (Precisamos adicionar a anotação @Entity e algum atributo com @Id)

5. Dentro da classe Application (nosso main) adicionamos a anotação @EnableFeignClients (ela será usada mais adiante), agora podemos executar nossa classe main, dessa forma com a conexão com o MySQL e nossas classes devidamentes criadas e configuradar o próprio JPA vai se encarregar de criar o schema e suas tabelas no banco. 
![image](https://user-images.githubusercontent.com/46768410/115968339-dd3f4080-a50d-11eb-957d-3c8f1f45e840.png)

![image](https://user-images.githubusercontent.com/46768410/115968354-fb0ca580-a50d-11eb-950b-7737e26a25cc.png)

6. Agora iremos criar um pacote chamado controller com uma classe CadastroController, ela será responsável por receber a requisição e enviar as requisições HTTPS.
![image](https://user-images.githubusercontent.com/46768410/115972135-07e7c400-a523-11eb-93a7-eaceaaf78ab0.png)
  * Nela utilizaremos as anotações @RestController (Aqui informamos que sera uma APIRESTful), @RequestMapping(a URI para qual será feito as requisições) e o        @RequiredArgsConstructor(É a anotação do Lombok para adicionar todos os contrutores que precise)
  * 2 Endpoints do tipo POST, são com eles que iremos criar nossos respectivos usuários e endereços
  * 1 Endpoint do tipo GET, ele irá retornar um usuário e seus respectivos endereçõs.

<!-- GETTING STARTED -->
## Instalação

### Pré requisitos

* Insomnia/Postman ( Para testar os endpoints ) 

  
* Alguma IDE que rode Java  como Eclipse, Intellij... 

* Mysql
  
* Docker  


### Instalação

1. Pegue o link do repositório https://github.com/Raeski/crud-place.git
2. Clone o repo
   ```sh
   git clone https://github.com/Raeski/crud-place.git
   ```
3. Abra em sua IDE de prefêrencia

4. No terminal execute docker-compose up

5. Na IDE execute o arquivo GamesApplication

6. No insomnia teste os endpoins no localhost:8080

```
    Exemplo de JSON :
    {
    "name": "Fifa 21",
    "producer": "EA",
    "releaseYear": 2020
    }
 ```

   ```JS
   POST /games - para criar um jogo
   
   GET /games - Retorna uma lista com todos os jogos
   
   GET /games/{id} - Retorna o Game que foi passado o id
   
   DELETE /games/{id} - Deleta o Game que foi passado o id
   
   PUT /games - Atualiza o Game, precisa passar o id do game dentro do JSON
   ```

<!-- CONTACT -->
## Contato


<p>Feito por <b>Gustavo Raeski</b>  :octocat: | - gustavoraeski@outlook.com

<a href="https://www.linkedin.com/in/gustavo-raeski/">Entre em contato</a></p>

