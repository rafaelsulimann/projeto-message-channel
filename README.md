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

![foto1.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/ffff9fd4-5054-4e32-9a04-f49268c8a4ff/foto1.png)

- Agora vamos entrar no **POSTMAN** e **IMPORTAR** esta **COLEÇÃO**.

![foto2.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/a1af2dc5-57d0-46e1-b0f7-28a2c95ae5de/foto2.png)

- E depois selecionar a **COLEÇÃO** do projeto e então você já terá **ACESSO** a todos os **ENDPOINTS** disponíveis deste projeto.

## 1) PRIMEIROS PASSOS

- Este programa foi projetado para conseguir enviar mensagens apenas de **USUÁRIOS EXISTENTES**, então já disponibilizamos **2 USUÁRIOS** já cadastrados para teste, porém também é possível criar seus **PRÓPRIOS USUÁRIOS** de uma forma bem simples. Vejamos.

### 1.1) CRIAR USUÁRIO

- Para fazer isso, basta abrir a **PASTA** da **COLEÇÃO** do **POSTMAN** chamada **“AUTH”**, e dentro dele terá uma **REQUISIÇÃO** do tipo **POST**.

![foto3.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/f9223ac4-ea8d-4f45-9519-da7b58cea0b8/foto3.png)

- Clicando nela, **AUTOMATICAMENTE** o **POSTMAN** irá nos **REDIRECIONAR** para o **ENDPOINT** específico para **CRIAR USUÁRIOS**.
- Após isto, precisamos ir em **BODY → RAW,** e aqui já terá um **MOLDE** pronto para você **ADICIONAR** um **USUÁRIO**, podendo apenas alterar as **INFORMAÇÕES** dos **ATRIBUTOS** que estiverem aparecendo na tela do **RAW**.

![foto4.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/7140ac91-0bd3-42ba-83e8-65d712da8c50/foto4.png)

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

![foto5.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/24fda30b-6a39-4bf8-9a81-29381e53331a/foto5.png)

### 2.1) CHAT CHANNEL

![foto6.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/d3684484-7b01-4e3b-974b-004f449499bb/foto6.png)

### 2.2) EMAIL CHANNEL

![foto7.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/0d909ee3-da17-4976-82ce-2cc48c8211c8/foto7.png)

### 2.3) VOICE CHANNEL

![foto8.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/ef3910e7-61c0-4c8f-b564-c9f27f9e47fa/foto8.png)

## 3) ENVIAR MENSAGEM PARA UM USUÁRIO JÁ CONECTADO POR ALGUM DOS CANAIS

- Após termos então **CRIADO** uma **CONEXÃO** com outro **USUÁRIO** existente por **ALGUM** dos **TRÊS** canais de **COMUNICAÇÃO**, vamos então enviar uma **MENSAGEM** para ele.
- Para isso, vamos precisar do **ID** da **CONEXÃO** feita com este **USUÁRIO** dos **CANAIS**.
- Então vamos dar um **GET ALL** no **CANAL** específico da **CONEXÃO**.

### 3.1) BUSCAR “CHANNEL ID” COM “GET ALL”

- Suponhamos então que **CRIAMOS** um **CONEXÃO** com o **USUÁRIO** via **CHAT CHANNEL**, vamos então dar um **GET ALL** neste canal para descobrirmos o **ID** da conexão.

![foto9.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/a4e7708f-afa0-446a-ad92-dfee6fbd4963/foto9.png)

![foto10.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/b7fec021-2b84-40be-abc2-2f33fef4a476/foto10.png)

- Neste caso, como já havíamos comentado, já deixamos alguns **OBJETOS** prontos no **BANCO DE DADOS** para **FINS DE TESTE**, mas você também pode **CRIAR** o seu, através dos **ENDPOINTS.**
    - Repare que nos **PARAMS** da **REQUISIÇÃO**, já temos **DISPONÍVEIS** os **FILTROS** que podemos ser **UTILIZADOS** para a **CONSULTA.**
    - Por exemplo, podemos **ORDENAR** da **MENSAGEM** mais **RECENTE** para a mais **ANTIGA**, basta clicarmos em **SORT** e modificarmos o **VALUE** para **DESC**.
    
    ![foto11.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/72141e52-2803-4562-b86f-061a92c70a5a/foto11.png)
    
- Então vamos **COPIAR** o **CHAT CHANNEL ID**, para que possamos **ENVIAR** uma **MENSAGEM** para este **USUÁRIO**.
- Feito isto, vamos então entrar na **PASTA** da **COLEÇÃO** do **POSTMAN** chamada de **CHAT MESSAGE**, e clicar na **REQUISIÇÃO** do tipo **POST - CREATE**.

![foto12.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/165633e4-ccf2-428f-88ec-ca3ff66b1c98/foto12.png)

- E agora fica mais claro do **PORQUÊ** precisamos **COPIAR** o **CHAT CHANNEL ID**...]
- Pois precisamos **INSERIR** este **ID** na **URL** da **REQUISIÇÃO** para que seja possível **CONECTAR** e enviar a **MENSAGEM** para o **CANAL** e o **USUÁRIO CORRETO**.

![foto13.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/4c743913-abc5-4287-bf46-55212e6b713f/foto13.png)

- Depois de **INSERIDO** a **URL** correta, devemos então **ENVIAR** a **MENSAGEM** via **BODY** da **REQUISIÇÃO** e enviar a **MENSAGEM** desejada.
- Quando nós **ENVIARMOS** esta **MENSAGEM**, ela **AUTOMATICAMENTE** irá **CRIAR** um novo **CANAL** de **COMUNICAÇÃO**, porém agora para o **USUÁRIO DESTINOS** com o **USUÁRIO** que enviou a **MENSAGEM**, porém, caso ele **JÁ TENHA** um **CANAL** de **COMUNICAÇÃO** criado com este **USUÁRIO**, ele irá apenas **INSERIR** a **MENSAGEM** neste **CANAL** existente.
- No caso, no exemplo acima, nós enviamos uma **MENSAGEM** para o **USUÁRIO** chamado **“henriquesulimann”**, podemos então buscar no **CANAL DE CAOMUNICAÇÃO** de **CHAT**, para verificar se de fato foi **CRIADO** ou **ADICIONADO** a **MENSAGEM** para ele.
- Para isso, vamos **NOVAMENTE** dar um **GET ALL** em **CHAT CHANNEL**, porém, agora alterando a **URL** para direcionar para o **“henriquesulimann”**.

![foto14.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/078e5b1b-5996-45a2-aa21-ad7313c8d015/foto14.png)

- Então como podemos ver, de fato foi criado um **NOVO CANAL** com o **rafaelsulimann** que foi o **USUÁRIO** que enviou a **MENSAGEM**.
- Porém agora, ajustando as **INFORMAÇÕES** para a visão do **henriquesulimann**, como no caso do **ATRIBUTO** de **toUser**.

# CONCLUSÃO

- Então como vimos, podemos fazer o **MESMO** que fizemos com o **rafaelsulimann** para **OUTROS** usuários, e também para **DIFERENTES CANAIS**, basta **CRIARMOS** uma **CONEXÃO** com o **USUÁRIO** específico através do **CANAL** desejado, depois **COPIARMOS** o **ID** do **CANAL**, e depois **ENVIARMOS** a **MENSAGEM** desejada, através da **URL** correta.