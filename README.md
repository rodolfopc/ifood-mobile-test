# Arquitetura
Utilizei a arquitetura MVP para desenvolver a App.
Aliada ao Dagger 2 para realizar a injeção de dependência.
Alguns padrões com enums para definir alguns valores fixos, um Builder para criar a request do Google Natural Language, algumas views customizadas(Toast e Loader);

Foram criados 2 módulos:

* Twitter Login

Com a activity de início para o login com o Twitter.
O Presenter responsável pela comunicação com a view e o Interactor.
E o Interactor que realiza o processo de login através da SDK do Twitter.

* Tweets List

A activity possui um campo de texto onde o usuário pode inserir o nome do usuário que deseja buscar.
O Presenter responsável pela comunicação com a view e os Interactors de listagem de Tweets e o Interactor que comunica com o Google Natural Language, e o Interactor do Twitter para logoff.
O Interactor de listagem de Tweets recupera os Tweets através de uma chamada no servidor utilizando o Alamofire.
E o Interactor do Google Natural Language que utiliza a SDK do google para obter a análise de sentimentos.

## Bibliotecas
* Dagger 2 - Para injeção de dependência;
* Twitter - SDK para chamadas de login;
* Butter Knife - Para bind nas views;
* Google Services Language - Para análise de sentimentos;
* Retrofit - Para chamada no servidor do Twitter;



