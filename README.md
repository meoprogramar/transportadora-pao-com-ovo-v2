# transportadora-pao-com-ovo-v2

### Descrição
&nbsp;

Projeto da disciplina de Programação WEB, professor Thiago Santana Batista.

> Continuação desse projeto [Versão 1 utilizando Spring Boot + Thymeleaf + JPA + Hibernate](https://github.com/meoprogramar/transportadora-pao-com-ovo-v1).
&nbsp;

O projeto foi planejado para ser desenvolvido em 4 sprints. Da 1° até a 3° sprint foi usado Spring Boot + Thymeleaf em multiple-page application, na última sprint metade dos casos de usos deveriam ser desenvolvidos usando Spring Boot como API + Node.js + Vue.js.
&nbsp;

> Na sprint final o professor realizou um sorteio para que os grupo trocassem de projeto, por isso o código desse projeto em Spring Boot está diferente da [Versão 1 utilizando Spring Boot + Thymeleaf + JPA + Hibernate](https://github.com/meoprogramar/transportadora-pao-com-ovo-v1).
&nbsp;

Tecnologias utilizadas:
- Spring Boot 2
- Node.js
- Vue.js
- JPA
- Hibernate
- MySQL
&nbsp;

Ferramentas utilizadas:
- Eclipse Java EE IDE for Web Developers
- MySQL Workbench
- Visual Studio Code
&nbsp;

### Documentação passo-a-passo
&nbsp;

1. Clone o projeto na sua máquina e siga os passos para cada pasta, tanto no back-end quanto no front-end.

#### Back-end

2. No Eclipse vá em "File -> Import -> Maven -> Existing maven projects", aperte em "Next" e selecione onde está a pasta que foi extraida no passo anterior.

3. Na parte de "Project" selecione o arquivo "pom.xml...:jar" aperte em "Finish" e aguarde a IDE importar as dependências.

4. Com o projeto importado vá em "src/main/resources -> application.properties", altere seu nome de usuário e senha para que o projeto possa instanciar o banco de dados.
```
spring.datasource.username=<usuário do mysql>
spring.datasource.password=<senha do mysql>
```
5. Salve as mudanças, em seguida vá em "src/main/java -> br.com.brunocarol.transpco -> TranspcoApplication.java" clique com botão direito e siga "Run As" -> "Java Application", agora aguarde todo o processo de build do sistema.

#### Front-end 

2. Instale o Node.js

3. Selecione a pasta transpco-web no Visual Studio Code.

4. Abre o terminal e digite os comandos para instalação de pacotes e dependências:
```
npm install
npm install vue
npm install -g @vue/cli
```

5. Em seguida ainda no terminal rode o servidor executando:
```
npm run serve -- --port 8081
```

6. Depois do servidor inicializar, abra seu navegador em [http://localhost:8081/](http://localhost:8081/).

### Demostração
&nbsp;

![1](https://user-images.githubusercontent.com/34866806/70369044-0831e180-1892-11ea-91b5-76926d6122d0.PNG)
&nbsp;
![4](https://user-images.githubusercontent.com/34866806/70369047-0831e180-1892-11ea-8240-75ccbc10af35.PNG)
&nbsp;
![2](https://user-images.githubusercontent.com/34866806/70369045-0831e180-1892-11ea-9f6e-6e9dace17531.PNG)
&nbsp;
![3](https://user-images.githubusercontent.com/34866806/70369046-0831e180-1892-11ea-814f-9ba2984a5e5b.PNG)

### [Versão 1 utilizando Spring Boot + Thymeleaf + JPA + Hibernate](https://github.com/meoprogramar/transportadora-pao-com-ovo-v1)
