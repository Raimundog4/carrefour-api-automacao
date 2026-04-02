#language: pt
@UsuariosAPI
Funcionalidade: Validação dos endpoints de cadastro, consulta, edição e exclusão de usuários

  Cenário: Cadastrar novo usuário com dados válidos com sucesso
    Dado que acessei a API de usuários "Cadastrar Usuário"
    E sendo método HTTP "POST" API de usuários
    E informo um payload válido de usuário
    Quando é efetuada a requisição do serviço de usuários
    Então é validado o status de retorno do serviço de usuários "201"
    E deve retornar a mensagem "Cadastro realizado com sucesso"

  Cenário: Cadastrar novo usuário com Email inválido sem @
    Dado que acessei a API de usuários "Cadastrar Usuário"
    E sendo método HTTP "POST" API de usuários
    E informo um payload de usuário com email inválido sem arroba
    Quando é efetuada a requisição do serviço de usuários
    Então é validado o status de retorno do serviço de usuários "400"
    E deve retornar erro no campo "email" com a mensagem "email deve ser um email válido"

  Cenário: Cadastrar novo usuário com campo EMAIL vazio
    Dado que acessei a API de usuários "Cadastrar Usuário"
    E sendo método HTTP "POST" API de usuários
    E informo um payload de usuário com campo email vazio
    Quando é efetuada a requisição do serviço de usuários
    Então é validado o status de retorno do serviço de usuários "400"
    E deve retornar erro no campo "email" com a mensagem "email não pode ficar em branco"

  Cenário: Cadastrar novo usuário com campo PASSWORD vazio
    Dado que acessei a API de usuários "Cadastrar Usuário"
    E sendo método HTTP "POST" API de usuários
    E informo um payload de usuário com campo PASSWORD vazio
    Quando é efetuada a requisição do serviço de usuários
    Então é validado o status de retorno do serviço de usuários "400"
    E deve retornar erro no campo "password" com a mensagem "password não pode ficar em branco"

  Cenário: Cadastrar novo usuário com campo NOME vazio
    Dado que acessei a API de usuários "Cadastrar Usuário"
    E sendo método HTTP "POST" API de usuários
    E informo um payload de usuário com campo nome vazio
    Quando é efetuada a requisição do serviço de usuários
    Então é validado o status de retorno do serviço de usuários "400"
    E deve retornar erro no campo "nome" com a mensagem "nome não pode ficar em branco"

  Cenário: Cadastrar novo usuário com campo ADMINISTRADOR vazio
    Dado que acessei a API de usuários "Cadastrar Usuário"
    E sendo método HTTP "POST" API de usuários
    E informo um payload de usuário com campo administrador vazio
    Quando é efetuada a requisição do serviço de usuários
    Então é validado o status de retorno do serviço de usuários "400"
    E deve retornar erro no campo "administrador" com a mensagem "administrador deve ser 'true' ou 'false'"

  Cenário: Cadastrar novo usuário com todos os campos vazios
    Dado que acessei a API de usuários "Cadastrar Usuário"
    E sendo método HTTP "POST" API de usuários
    E informo um payload de usuário com todos os campos vazios
    Quando é efetuada a requisição do serviço de usuários
    Então é validado o status de retorno do serviço de usuários "400"
    E deve retornar erro no campo "nome" com a mensagem "nome não pode ficar em branco"
    E deve retornar erro no campo "password" com a mensagem "password não pode ficar em branco"
    E deve retornar erro no campo "email" com a mensagem "email não pode ficar em branco"
    E deve retornar erro no campo "administrador" com a mensagem "administrador deve ser 'true' ou 'false'"

  Cenário: Cadastrar novo usuário com EMAIL já cadastrado
    Dado que acessei a API de usuários "Cadastrar Usuário"
    E sendo método HTTP "POST" API de usuários
    E informo um payload de usuário com email já cadastrado
    Quando é efetuada a requisição do serviço de usuários
    Então é validado o status de retorno do serviço de usuários "400"
    E deve retornar a mensagem "Este email já está sendo usado"

  Cenário: Listar usuários cadastrados
    Dado que acessei a API de usuários "Listar Usuários"
    E sendo método HTTP "GET" API de usuários
    Quando é efetuada a requisição do serviço de usuários
    Então é validado o status de retorno do serviço de usuários "200"
    E deve retornar a lista de usuários preenchida

  Cenário: Listar usuário pelo ID
    Dado que acessei a API de usuários "Listar Usuário por ID"
    E sendo método HTTP "GET" API de usuários
    E informo um id de usuário válido para consulta
    Quando é efetuada a requisição do serviço de usuários
    Então é validado o status de retorno do serviço de usuários "200"
    E deve retornar os dados do usuário consultado

  Cenário: Listar usuários pelo EMAIL
    Dado que acessei a API de usuários "Listar Usuários"
    E sendo método HTTP "GET" API de usuários
    E informo o email de um usuário válido para consulta
    Quando é efetuada a requisição do serviço de usuários
    Então é validado o status de retorno do serviço de usuários "200"
    E deve retornar usuário(s) conforme filtro informado

  Cenário: Listar usuários pelo NOME
    Dado que acessei a API de usuários "Listar Usuários"
    E sendo método HTTP "GET" API de usuários
    E informo o nome de um usuário válido para consulta
    Quando é efetuada a requisição do serviço de usuários
    Então é validado o status de retorno do serviço de usuários "200"
    E deve retornar usuário(s) conforme filtro informado

  Cenário: Listar usuários pelo PASSWORD
    Dado que acessei a API de usuários "Listar Usuários"
    E sendo método HTTP "GET" API de usuários
    E informo a password de um usuário válido para consulta
    Quando é efetuada a requisição do serviço de usuários
    Então é validado o status de retorno do serviço de usuários "200"
    E deve retornar usuário(s) conforme filtro informado

  Cenário: Listar usuários pelo ADMINISTRADOR
    Dado que acessei a API de usuários "Listar Usuários"
    E sendo método HTTP "GET" API de usuários
    E informo o valor do administrador de um usuário para consulta
    Quando é efetuada a requisição do serviço de usuários
    Então é validado o status de retorno do serviço de usuários "200"
    E deve retornar usuário(s) conforme filtro informado

  Cenário: Validar usuário não encontrado
    Dado que acessei a API de usuários "Listar Usuário por ID"
    E sendo método HTTP "GET" API de usuários
    E informo um id de usuário inexistente para consulta
    Quando é efetuada a requisição do serviço de usuários
    Então é validado o status de retorno do serviço de usuários "400"
    E deve retornar a mensagem "Usuário não encontrado"
    
    Cenário: Buscar usuário com ID inválido (tamanho diferente de 16 caracteres)
  Dado que acessei a API de usuários "Listar Usuário por ID"
  E sendo método HTTP "GET" API de usuários
  E informo um id inválido com tamanho diferente de 16 caracteres
  Quando é efetuada a requisição do serviço de usuários
  Então é validado o status de retorno do serviço de usuários "400"
  E deve retornar erro no campo "id" com a mensagem "id deve ter exatamente 16 caracteres alfanuméricos"
    
    Cenário: Alterar dados de um usuário com o ID
    Dado que acessei a API de usuários "Alterar Usuário"
    E sendo método HTTP "PUT" API de usuários
    E informo um id de usuário válido para alteração
    E informo um payload válido para alteração de usuário
    Quando é efetuada a requisição do serviço de usuários
    Então é validado o status de retorno do serviço de usuários "200"
    E deve retornar a mensagem "Registro alterado com sucesso"

  Cenário: Alterar dados de um usuário com campo NOME vazio
    Dado que acessei a API de usuários "Alterar Usuário"
    E sendo método HTTP "PUT" API de usuários
    E informo um id de usuário válido para alteração
    E informo um payload de alteração de usuário com campo nome vazio
    Quando é efetuada a requisição do serviço de usuários
    Então é validado o status de retorno do serviço de usuários "400"
    E deve retornar erro no campo "nome" com a mensagem "nome não pode ficar em branco"

  Cenário: Alterar dados de um usuário com campo EMAIL vazio
    Dado que acessei a API de usuários "Alterar Usuário"
    E sendo método HTTP "PUT" API de usuários
    E informo um id de usuário válido para alteração
    E informo um payload de alteração de usuário com campo email vazio
    Quando é efetuada a requisição do serviço de usuários
    Então é validado o status de retorno do serviço de usuários "400"
    E deve retornar erro no campo "email" com a mensagem "email não pode ficar em branco"

  Cenário: Alterar dados de um usuário com campo PASSWORD vazio
    Dado que acessei a API de usuários "Alterar Usuário"
    E sendo método HTTP "PUT" API de usuários
    E informo um id de usuário válido para alteração
    E informo um payload de alteração de usuário com campo password vazio
    Quando é efetuada a requisição do serviço de usuários
    Então é validado o status de retorno do serviço de usuários "400"
    E deve retornar erro no campo "password" com a mensagem "password não pode ficar em branco"

  Cenário: Alterar dados de um usuário com campo ADMINISTRADOR vazio
    Dado que acessei a API de usuários "Alterar Usuário"
    E sendo método HTTP "PUT" API de usuários
    E informo um id de usuário válido para alteração
    E informo um payload de alteração de usuário com campo administrador vazio
    Quando é efetuada a requisição do serviço de usuários
    Então é validado o status de retorno do serviço de usuários "400"
    E deve retornar erro no campo "administrador" com a mensagem "administrador deve ser 'true' ou 'false'"

  Cenário: Excluir um usuário com ID inexistente
    Dado que acessei a API de usuários "Excluir Usuário"
    E sendo método HTTP "DELETE" API de usuários
    E informo um id de usuário inexistente para exclusão
    Quando é efetuada a requisição do serviço de usuários
    Então é validado o status de retorno do serviço de usuários "200"
    E deve retornar a mensagem "Nenhum registro excluído"

  Cenário: Excluir um usuário com ID existente
    Dado que acessei a API de usuários "Excluir Usuário"
    E sendo método HTTP "DELETE" API de usuários
    E informo um id de usuário válido para exclusão
    Quando é efetuada a requisição do serviço de usuários
    Então é validado o status de retorno do serviço de usuários "200"
    E deve retornar a mensagem "Registro excluído com sucesso"

  Cenário: Excluir um usuário previamente excluído
    Dado que acessei a API de usuários "Excluir Usuário"
    E sendo método HTTP "DELETE" API de usuários
    E informo um id de usuário previamente excluído
    Quando é efetuada a requisição do serviço de usuários
    Então é validado o status de retorno do serviço de usuários "200"
    E deve retornar a mensagem "Nenhum registro excluído"