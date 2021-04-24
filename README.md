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
  * Dentro dos métodos teremos as anotações @RequestBody(Informando qual o tipo de JSON que receberemos), @PathVariable (Aqui dizemos que receberemos um CPF na URL), @Valid(Dentro da classe usuários adicionamos uma anotação @NotEmpty para essa anotação funcionar corretamente precisamos colocar o @Valid dentro do método no controller que recebe ela)
  * 2 Endpoints do tipo POST @PostMapping, são com eles que iremos criar nossos respectivos usuários e endereços
  * 1 Endpoint do tipo GET, ele irá retornar um usuário e seus respectivos endereçõs.

7. Controller criado iremos para onde ficará toda regra de negócios da aplicação, o Service: 
   ![image](https://user-images.githubusercontent.com/46768410/115972599-bbea4e80-a525-11eb-9db1-754662115a1f.png)
    *Aqui temos o método saveUsuario
      
     ```JAVA  
      Optional<Usuario> byId = usuarioRepository.findById(usuario.getCpf());
      List<Usuario> byEmail = usuarioRepository.findByEmail(usuario.getEmail());
     ```
     *Ele inicialmente busca no banco se existe algum cpf ou email cadastrado igual ao que foi passado, caso já existe ele retorna para o usuário um status 400 e que o CPF ou E-mail já foi cadastrado.
     
     *saveEndereco ele faz uma valicação utilizando o validateCep(endereco), esse método validateCep faz uma requisição do tipo GET para a API viaCep e verifica se o CEP que foi passado esta correto: 
      ``` JAVA
          public ViaCep validateCep(EnderecoView endereco) {
            try {
                return this.viaCEPClient.buscaEndereco(endereco.getCEP());
            } catch (BadRequestException badRequestException) {
                throw new BadRequestException("CEP inválido");
            }
        }
      ```
      *Lá na nossa Application eu adicionei a anotação @EnableFeignClients, foi por causa dessa requisição com a API ViaCEP. Para conseguirmos utilizar ela de forma correta precisáremos criar duas classes, a uma interface em que será feito as configurações e feito a requisição e um model de resposta.
      #### Classe ViaCEP:
      ![image](https://user-images.githubusercontent.com/46768410/115972882-7c246680-a527-11eb-9905-cecf26c31c0e.png)

      #### Interface ViaCEP:
      ![image](https://user-images.githubusercontent.com/46768410/115972884-87779200-a527-11eb-86e7-c4cc8639e25d.png)
        *Aqui fazemos o modelo de como será feito a requisição para a API, e só precismos chamá-la no nosso Service
        
     *Voltando a nossa classe service, teremos apenas mais dois métodos, que são responsáveis para tratar o retorno quando buscarmos a lista de endereços de um usuário 
     ![image](https://user-images.githubusercontent.com/46768410/115972964-108ec900-a528-11eb-8c42-20f280f8c973.png)

8. Criar as repository, são elas que persistiram os objetos no banco e buscaram quando necessário. 
    #### Usuário Repository
    ![image](https://user-images.githubusercontent.com/46768410/115972986-3916c300-a528-11eb-87d2-141afbc388a0.png)
      *Adicionamoes o extends JpaRepository para conseguirmos nos comunicar com o banco utilizando o JPA
      
    #### Endereço Repository
    ![image](https://user-images.githubusercontent.com/46768410/115972992-42a02b00-a528-11eb-9a3d-f136fe7cdee2.png) 
    
9. Por último adicionaremos dentro da nossa aplicação a lib MapStruct ela serve para mapearmos uma classe para outra, o ideal dentro de um projeto é não retornar a classe com anotação Entity, então criaremos mais duas classes, EnderecoView e UserView;
    ![image](https://user-images.githubusercontent.com/46768410/115973675-46827c00-a52d-11eb-9fa3-03555624ab73.png)
    ![image](https://user-images.githubusercontent.com/46768410/115973677-4a160300-a52d-11eb-9c66-64d1c7700668.png)


### Execução
 * Com todas as classes devidamente criadas novamente executaremos a aplicação 

1. No insomnia teste os endpoins no localhost:8080

```
    Exemplo de JSON para criação de Usuário:
    {
      "nome": "Gustavo",
      "email": "teste@gmail.com",
      "cpf": 1111111111,
      "dtNascimento": "09/12/1999"
    }
    
    Exemplo de JSON para criação de Endereço:
   {
    "logradouro": "Rua maria dirce Ribeiro",
    "numero": 2911,
    "complemento": "apto 201",
    "bairro": "Santa monica",
    "cidade": "Uberlandia",
    "estado": "Minas gerais",
    "cep": "38408194",
    "cpfUsuario":{
      "cpf" :  1111111111
    }
  }
 ```


   ```JS
   POST /cadastro/usuario - para cadastrar um usúario
   
   POST /cadastro/endereco - Cadastrar um endereço
   
   GET /cadastro/{cpf} - Retornar uma lista com os endereços do usúario
   ```

<!-- CONTACT -->
## Contato


<p>Feito por <b>Gustavo Raeski</b>  :octocat: | - gustavoraeski@outlook.com

<a href="https://www.linkedin.com/in/gustavo-raeski/">Entre em contato</a></p>
<a href="https://github.com/Raeski/OrangeTalent">Link do repositório para conferir baixar a API</a></p>


