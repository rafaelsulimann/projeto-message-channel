# PROJETO WEON - MESSAGE CHANNEL

## INTRODUÇÃO

Este projeto é uma API de chat que simula **TRÊS** tipos de comunicações possíveis:

- Chat
- Email
- Voz

 Realizo então o **BACKEND** para todo o funcionamento da mesma realizando o **CRUD** completo para todas entidades com implementação de: 

- Paginação
- Validações
- Filtros simples e avançados com associações
- Hateoas
- Métodos Assíncronos
- Dtos
- E muito mais...

Foi feito o **DEPLOY** da API na nuvem do **HEROKU** para que possa ser acessado online.

Pois desta forma, caso queira testar a API, não será necessário baixar nem instalar nada.

## COMO ACESSAR A API?

A API pode ser acessada através dos **ENDPOINTS** disponíveis através da URL do **HEROKU**.

A URL da API é:

- [https://projeto-weon.herokuapp.com](https://projeto-weon.herokuapp.com/)

Porém só será possível acessar a API através dos ENDPOINTS, e para isso será necessário a utilização do aplicativo **POSTMAN** onde você pode fazer as **REQUISIÇÕES** da API:

- GET (acessar as entidades)
- POST (inserir entidades)
- PUT (atualizar entidades)
- DELETE (deletar entidades)

## RESTRIÇÕES

### USER

- Quando **INCLUIRMOS** um **USER** através da requisição do tipo **POST**, não será possível adicionar um usuários caso informarmos algum dos atributos abaixo de um **USER** já existente:
    - Username
        - Para o **USERNAME** além de não pode ser igual à um usuário ja existente, ele também **NÃO PODE** ter **ESPAÇOS EM BRANCO**.
    - Email
    - Phone

# ENDPOINTS DISPONÍVEIS

- Para facilitar para o **USUÁRIO** não precisar ficar **DIGITANDO** os **ENDPOINTS** que estão expostos, na **PASTA “POSTMAN COLLECTION”** do projeto, contém um **COLEÇÃO COMPLETA** dos **ENDPOINTS** disponíveis, e vem até com os **FILTROS** disponíveis. Lembrando que os **FILTROS** podem ser **CONFIGURADOS** pelo **PROPRIO USUÁRIO** de acordo com o que ele deseja filtrar.

![foto1](https://user-images.githubusercontent.com/97992737/163560612-272eb74f-4c63-44b3-8c2e-b10a0e473e71.png)

- Agora vamos entrar no **POSTMAN** e **IMPORTAR** esta **COLEÇÃO**.

![foto2](https://user-images.githubusercontent.com/97992737/163560650-fb3756ab-021a-475e-b248-866e20e960ad.png)

- E depois selecionar a **COLEÇÃO** do projeto e então você já terá **ACESSO** a todos os **ENDPOINTS** disponíveis deste projeto.

## 1) PRIMEIROS PASSOS

- Este programa foi projetado para conseguir enviar mensagens apenas de **USUÁRIOS EXISTENTES**, então já disponibilizamos **2 USUÁRIOS** já cadastrados para teste, porém também é possível criar seus **PRÓPRIOS USUÁRIOS** de uma forma bem simples. Vejamos.

### 1.1) CRIAR USUÁRIO

- Para fazer isso, basta abrir a **PASTA** da **COLEÇÃO** do **POSTMAN** chamada **“AUTH”**, e dentro dele terá uma **REQUISIÇÃO** do tipo **POST**.

![foto3](https://user-images.githubusercontent.com/97992737/163560668-da217c54-5c95-445d-af84-752a8ffdc023.png)

- Clicando nela, **AUTOMATICAMENTE** o **POSTMAN** irá nos **REDIRECIONAR** para o **ENDPOINT** específico para **CRIAR USUÁRIOS**.
- Após isto, precisamos ir em **BODY → RAW,** e aqui já terá um **MOLDE** pronto para você **ADICIONAR** um **USUÁRIO**, podendo apenas alterar as **INFORMAÇÕES** dos **ATRIBUTOS** que estiverem aparecendo na tela do **RAW**.

![foto4](https://user-images.githubusercontent.com/97992737/163560690-94ee0182-9374-48f1-a69a-297b05d27e07.png)

## 2) CRIAR CANAIS DE COMUNICAÇÃO COM OUTRO USUÁRIO

- Para que seja possível a **COMUNICAÇÃO** entre os **USUÁRIOS**, nós devemos primeiramente **CRIAR** um **CONEXÃO** através de **UM DOS TRÊS** tipos de **CANAIS** disponíveis, inserindo apenas **UM INFORMAÇÃO**.
    - Chat
        - Inserir o **USERNAME** do usuário **DESTINO**.
    - Email
        - Inserir o **EMAIL** do usuário **DESTINO**.
    - Voz
        - Inserir o **TELEFONE** do usuário **DESTINO.**
- Outro ponto **MUITO IMPORTANTE** é que agora, precisaremos **INCLUIR** na **URL** o **USERNAME** do **USUÁRIO** que estamos atuando no momento.
- Para então **CRIAR** na **PRÁTICA**, podemos **ABRIR** a pasta da **COLEÇÃO** do **POSTMAN** relacionado ao **TIPO** de **COMUNICAÇÃO** que desejamos fazer, e clicar em **CREATE**.

![foto5](https://user-images.githubusercontent.com/97992737/163560717-633aadc5-64cb-4471-a71a-9c83f1615dc5.png)

### 2.1) CHAT CHANNEL

![foto6](https://user-images.githubusercontent.com/97992737/163560756-c6a806af-a17c-4698-bf40-440169fce2dd.png)

### 2.2) EMAIL CHANNEL

![foto7](https://user-images.githubusercontent.com/97992737/163560774-7518e15e-a878-4c9c-86de-d72ffa2ebc4e.png)

### 2.3) VOICE CHANNEL

![foto8](https://user-images.githubusercontent.com/97992737/163560796-eaf6d0e0-bece-41a2-bbca-9ba46db84d0f.png)

## 3) ENVIAR MENSAGEM PARA UM USUÁRIO JÁ CONECTADO POR ALGUM DOS CANAIS

- Após termos então **CRIADO** uma **CONEXÃO** com outro **USUÁRIO** existente por **ALGUM** dos **TRÊS** canais de **COMUNICAÇÃO**, vamos então enviar uma **MENSAGEM** para ele.
- Para isso, vamos precisar do **ID** da **CONEXÃO** feita com este **USUÁRIO** dos **CANAIS**.
- Então vamos dar um **GET ALL** no **CANAL** específico da **CONEXÃO**.

### 3.1) BUSCAR “CHANNEL ID” COM “GET ALL”

- Suponhamos então que **CRIAMOS** um **CONEXÃO** com o **USUÁRIO** via **CHAT CHANNEL**, vamos então dar um **GET ALL** neste canal para descobrirmos o **ID** da conexão.

![foto9](https://user-images.githubusercontent.com/97992737/163560840-0649386b-ab4d-4bda-af5d-6096dd4726bd.png)

![foto10](https://user-images.githubusercontent.com/97992737/163560863-3d9b847c-e799-4c7b-99dd-5d65074ec88b.png)

- Neste caso, como já havíamos comentado, já deixamos alguns **OBJETOS** prontos no **BANCO DE DADOS** para **FINS DE TESTE**, mas você também pode **CRIAR** o seu, através dos **ENDPOINTS.**
    - Repare que nos **PARAMS** da **REQUISIÇÃO**, já temos **DISPONÍVEIS** os **FILTROS** que podemos ser **UTILIZADOS** para a **CONSULTA.**
    - Por exemplo, podemos **ORDENAR** da **MENSAGEM** mais **RECENTE** para a mais **ANTIGA**, basta clicarmos em **SORT** e modificarmos o **VALUE** para **DESC**.
    
    ![foto11](https://user-images.githubusercontent.com/97992737/163560883-e4a29835-185d-4553-aa24-ca26a3013ac3.png)
    
- Então vamos **COPIAR** o **CHAT CHANNEL ID**, para que possamos **ENVIAR** uma **MENSAGEM** para este **USUÁRIO**.
- Feito isto, vamos então entrar na **PASTA** da **COLEÇÃO** do **POSTMAN** chamada de **CHAT MESSAGE**, e clicar na **REQUISIÇÃO** do tipo **POST - CREATE**.

![foto12](https://user-images.githubusercontent.com/97992737/163560902-b1102cb6-ebd2-4a20-9911-7562ae4416f1.png)

- E agora fica mais claro do **PORQUÊ** precisamos **COPIAR** o **CHAT CHANNEL ID**...]
- Pois precisamos **INSERIR** este **ID** na **URL** da **REQUISIÇÃO** para que seja possível **CONECTAR** e enviar a **MENSAGEM** para o **CANAL** e o **USUÁRIO CORRETO**.

![foto13](https://user-images.githubusercontent.com/97992737/163560927-8f88af3b-22c3-4c11-96b9-2571766bdb04.png)

- Depois de **INSERIDO** a **URL** correta, devemos então **ENVIAR** a **MENSAGEM** via **BODY** da **REQUISIÇÃO** e enviar a **MENSAGEM** desejada.
### 3.3) SINCRONIA DE DADOS
- Quando nós **ENVIARMOS** esta **MENSAGEM**, ela **AUTOMATICAMENTE** irá **CRIAR** um novo **CANAL** de **COMUNICAÇÃO**, porém agora para o **USUÁRIO DESTINOS** com o **USUÁRIO** que enviou a **MENSAGEM**, porém, caso ele **JÁ TENHA** um **CANAL** de **COMUNICAÇÃO** criado com este **USUÁRIO**, ele irá apenas **INSERIR** a **MENSAGEM** neste **CANAL** existente.
- No caso, no exemplo acima, nós enviamos uma **MENSAGEM** para o **USUÁRIO** chamado **“henriquesulimann”**, podemos então buscar no **CANAL DE CAOMUNICAÇÃO** de **CHAT**, para verificar se de fato foi **CRIADO** ou **ADICIONADO** a **MENSAGEM** para ele.
- Para isso, vamos **NOVAMENTE** dar um **GET ALL** em **CHAT CHANNEL**, porém, agora alterando a **URL** para direcionar para o **“henriquesulimann”**.

![foto14](https://user-images.githubusercontent.com/97992737/163560948-be1bb087-31a3-410e-a86c-af51751e4852.png)

- Então como podemos ver, de fato foi criado um **NOVO CANAL** com o **rafaelsulimann** que foi o **USUÁRIO** que enviou a **MENSAGEM**.
- Porém agora, ajustando as **INFORMAÇÕES** para a visão do **henriquesulimann**, como no caso do **ATRIBUTO** de **toUser**.
- Ao **CLICARMOS** no **LINK** que aparece logo abaixo do **CHAT CHANNEL**, nós seremos redirecionados para o **LINK** da **CONVERSA**, onde contém **TODAS AS MENSAGENS**, tanto as enviadas quanto as recebidas.

![foto15](https://user-images.githubusercontent.com/97992737/163663978-ec24c8a7-f06f-44c8-8162-d2cf6c247548.jpg)

- E quando **CLICARMOS** em **SEND**, iremos filtrar por todas as mensagens da conversa.

![foto16](https://user-images.githubusercontent.com/97992737/163664007-594f6cf7-6df5-497c-81d1-8e5c335e4580.jpg)

# CONCLUSÃO

- Então como vimos, podemos fazer o **MESMO** que fizemos com o **rafaelsulimann** para **OUTROS** usuários, e também para **DIFERENTES CANAIS**, basta **CRIARMOS** uma **CONEXÃO** com o **USUÁRIO** específico através do **CANAL** desejado, depois **COPIARMOS** o **ID** do **CANAL**, e depois **ENVIARMOS** a **MENSAGEM** desejada, através da **URL** correta.
