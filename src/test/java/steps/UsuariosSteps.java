package steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import config.EndpointsProperties;
import factory.UsuarioFactory;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import services.UsuarioService;

public class UsuariosSteps {

    private Response response;
    private String endpoint = "";
    private String metodo = "";
    private String idUsuario = "";
    private String emailUsuario = "";
    private String nomeUsuario = "";
    private String passwordUsuario = "";
    private String administradorUsuario = "";
    private Map<String, Object> payload;

    @Dado("que acessei a API de usuários {string}")
    public void queAcesseiAAPIDeUsuários(String endpoint) {
        this.endpoint = endpoint;

        RestAssured.reset();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(EndpointsProperties.BASE_URI)
                .build();
    }

    @Dado("sendo método HTTP {string} API de usuários")
    public void sendoMétodoHTTPAPIDeUsuários(String metodo) {
        this.metodo = metodo;
    }

    @Dado("informo um payload válido de usuário")
    public void informoUmPayloadVálidoDeUsuário() {
        this.payload = UsuarioFactory.usuarioValido();
    }

    @Quando("é efetuada a requisição do serviço de usuários")
    public void éEfetuadaARequisiçãoDoServiçoDeUsuários() {

        switch (this.endpoint) {
            case "Cadastrar Usuário":
                if (this.metodo.equalsIgnoreCase("POST")) {
                    response = UsuarioService.cadastrarUsuario(payload);
                } else {
                    throw new IllegalArgumentException("Método HTTP não suportado para o endpoint Cadastrar Usuário.");
                }
                break;

            case "Listar Usuários":
                if (this.metodo.equalsIgnoreCase("GET")) {
                    if ((emailUsuario != null && !emailUsuario.isEmpty())
                            || (nomeUsuario != null && !nomeUsuario.isEmpty())
                            || (passwordUsuario != null && !passwordUsuario.isEmpty())
                            || (administradorUsuario != null && !administradorUsuario.isEmpty())) {

                        Map<String, Object> filtros = new HashMap<>();

                        if (emailUsuario != null && !emailUsuario.isEmpty()) {
                            filtros.put("email", emailUsuario);
                        }
                        if (nomeUsuario != null && !nomeUsuario.isEmpty()) {
                            filtros.put("nome", nomeUsuario);
                        }
                        if (passwordUsuario != null && !passwordUsuario.isEmpty()) {
                            filtros.put("password", passwordUsuario);
                        }
                        if (administradorUsuario != null && !administradorUsuario.isEmpty()) {
                            filtros.put("administrador", administradorUsuario);
                        }

                        response = UsuarioService.listarUsuariosComFiltros(filtros);
                    } else {
                        response = UsuarioService.listarUsuarios();
                    }
                } else {
                    throw new IllegalArgumentException("Método HTTP não suportado para o endpoint Listar Usuários.");
                }
                break;

            case "Listar Usuário por ID":
                if (this.metodo.equalsIgnoreCase("GET")) {
                    response = UsuarioService.buscarUsuarioPorId(idUsuario);
                } else {
                    throw new IllegalArgumentException("Método HTTP não suportado para o endpoint Listar Usuário por ID.");
                }
                break;

            case "Alterar Usuário":
                if (this.metodo.equalsIgnoreCase("PUT")) {
                    response = UsuarioService.alterarUsuarioPorId(idUsuario, payload);
                } else {
                    throw new IllegalArgumentException("Método HTTP não suportado para o endpoint Alterar Usuário.");
                }
                break;

            case "Excluir Usuário":
                if (this.metodo.equalsIgnoreCase("DELETE")) {
                    response = UsuarioService.excluirUsuarioPorId(idUsuario);
                } else {
                    throw new IllegalArgumentException("Método HTTP não suportado para o endpoint Excluir Usuário.");
                }
                break;

            default:
                throw new IllegalArgumentException("Endpoint informado não é válido para a API de usuários.");
        }
    }

    @Então("é validado o status de retorno do serviço de usuários {string}")
    public void éValidadoOStatusDeRetornoDoServiçoDeUsuários(String status) {
        assertEquals("Status code diferente do esperado.",
                Integer.parseInt(status), response.getStatusCode());
    }

    @Então("deve retornar a mensagem {string}")
    public void deveRetornarAMensagem(String mensagemEsperada) {
        assertEquals("Mensagem diferente do esperado.",
                mensagemEsperada, response.jsonPath().getString("message"));
    }

    @Dado("informo um payload de usuário com email inválido sem arroba")
    public void informoUmPayloadDeUsuárioComEmailInválidoSemArroba() {
        this.payload = UsuarioFactory.usuarioComEmailInvalidoSemArroba();
    }

    @Então("deve retornar erro no campo {string} com a mensagem {string}")
    public void deveRetornarErroNoCampoComMensagem(String campo, String mensagemEsperada) {

        String mensagemRetornada = response.jsonPath().getString(campo);

        assertNotNull("Erro não retornado para o campo: " + campo, mensagemRetornada);

        assertEquals("Mensagem diferente para o campo: " + campo,
                mensagemEsperada, mensagemRetornada);
    }

    @Dado("informo um payload de usuário com campo email vazio")
    public void informoUmPayloadDeUsuárioComCampoEmailVazio() {
        this.payload = UsuarioFactory.usuarioComEmailVazio();
    }

    @Dado("informo um payload de usuário com campo PASSWORD vazio")
    public void informoUmPayloadDeUsuárioComCampoPASSWORDVazio() {
        this.payload = UsuarioFactory.usuarioComSenhaVazia();
    }

    @Dado("informo um payload de usuário com campo nome vazio")
    public void informoUmPayloadDeUsuárioComCampoNomeVazio() {
        this.payload = UsuarioFactory.usuarioComNomeVazio();
    }

    @Dado("informo um payload de usuário com campo administrador vazio")
    public void informoUmPayloadDeUsuárioComCampoAdministradorVazio() {
        this.payload = UsuarioFactory.usuarioComAdministradorVazio();
    }

    @Dado("informo um payload de usuário com todos os campos vazios")
    public void informoUmPayloadDeUsuárioComTodosOsCamposVazios() {
        this.payload = UsuarioFactory.usuarioComTodosCamposVazios();
    }

    @Dado("informo um payload de usuário com email já cadastrado")
    public void informoUmPayloadDeUsuárioComEmailJáCadastrado() {
        Map<String, Object> usuarioBase = UsuarioFactory.usuarioValido();
        Response respostaCriacao = UsuarioService.cadastrarUsuario(usuarioBase);

        assertEquals("Falha ao preparar massa para email já cadastrado.",
                201, respostaCriacao.getStatusCode());

        String emailExistente = usuarioBase.get("email").toString();
        this.payload = UsuarioFactory.usuarioComEmailJaCadastrado(emailExistente);
    }

    @Então("deve retornar a lista de usuários preenchida")
    public void deveRetornarAListaDeUsuáriosPreenchida() {
        List<Map<String, Object>> usuarios = response.jsonPath().getList("usuarios");

        assertNotNull("A lista de usuários não foi retornada.", usuarios);
        assertFalse("A lista de usuários retornou vazia.", usuarios.isEmpty());
    }

    @Dado("informo um id de usuário válido para consulta")
    public void informoUmIdDeUsuárioVálidoParaConsulta() {
        Map<String, Object> usuario = UsuarioFactory.usuarioValido();
        Response respostaCriacao = UsuarioService.cadastrarUsuario(usuario);

        assertEquals("Falha ao preparar massa para consulta por ID.",
                201, respostaCriacao.getStatusCode());

        this.idUsuario = respostaCriacao.jsonPath().getString("_id");
    }

    @Então("deve retornar os dados do usuário consultado")
    public void deveRetornarOsDadosDoUsuárioConsultado() {
        String idRetornado = response.jsonPath().getString("_id");
        String nomeRetornado = response.jsonPath().getString("nome");
        String emailRetornado = response.jsonPath().getString("email");

        assertNotNull("O id do usuário não foi retornado.", idRetornado);
        assertFalse("O id do usuário retornou vazio.", idRetornado.trim().isEmpty());

        assertNotNull("O nome do usuário não foi retornado.", nomeRetornado);
        assertFalse("O nome do usuário retornou vazio.", nomeRetornado.trim().isEmpty());

        assertNotNull("O email do usuário não foi retornado.", emailRetornado);
        assertFalse("O email do usuário retornou vazio.", emailRetornado.trim().isEmpty());
    }

    @Dado("informo o email de um usuário válido para consulta")
    public void informoOEmailDeUmUsuárioVálidoParaConsulta() {
        Map<String, Object> usuario = UsuarioFactory.usuarioValido();
        Response respostaCriacao = UsuarioService.cadastrarUsuario(usuario);

        assertEquals("Falha ao preparar massa para consulta por email.",
                201, respostaCriacao.getStatusCode());

        this.emailUsuario = usuario.get("email").toString();
    }

    @Então("deve retornar usuário\\(s) conforme filtro informado")
    public void deveRetornarUsuárioSConformeFiltroInformado() {
        List<Map<String, Object>> usuarios = response.jsonPath().getList("usuarios");

        assertNotNull("A lista de usuários não foi retornada.", usuarios);
        assertFalse("Nenhum usuário foi retornado para o filtro informado.", usuarios.isEmpty());
    }

    @Dado("informo o nome de um usuário válido para consulta")
    public void informoONomeDeUmUsuárioVálidoParaConsulta() {
        Map<String, Object> usuario = UsuarioFactory.usuarioValido();
        Response respostaCriacao = UsuarioService.cadastrarUsuario(usuario);

        assertEquals("Falha ao preparar massa para consulta por nome.",
                201, respostaCriacao.getStatusCode());

        this.nomeUsuario = usuario.get("nome").toString();
    }

    @Dado("informo a password de um usuário válido para consulta")
    public void informoAPasswordDeUmUsuárioVálidoParaConsulta() {
        Map<String, Object> usuario = UsuarioFactory.usuarioValido();
        Response respostaCriacao = UsuarioService.cadastrarUsuario(usuario);

        assertEquals("Falha ao preparar massa para consulta por password.",
                201, respostaCriacao.getStatusCode());

        this.passwordUsuario = usuario.get("password").toString();
    }

    @Dado("informo o valor do administrador de um usuário para consulta")
    public void informoOValorDoAdministradorDeUmUsuárioParaConsulta() {
        Map<String, Object> usuario = UsuarioFactory.usuarioValido();
        Response respostaCriacao = UsuarioService.cadastrarUsuario(usuario);

        assertEquals("Falha ao preparar massa para consulta por administrador.",
                201, respostaCriacao.getStatusCode());

        this.administradorUsuario = usuario.get("administrador").toString();
    }

    @Dado("informo um id de usuário inexistente para consulta")
    public void informoUmIdDeUsuárioInexistenteParaConsulta() {
        this.idUsuario = "1234567891234567";
    }
    
    @Dado("informo um id inválido com tamanho diferente de 16 caracteres")
    public void informoUmIdInvalidoComTamanhoDiferenteDe16Caracteres() {
        this.idUsuario = "123";
    }

    @Dado("informo um id de usuário válido para alteração")
    public void informoUmIdDeUsuárioVálidoParaAlteração() {
        Map<String, Object> usuario = UsuarioFactory.usuarioValido();
        Response respostaCriacao = UsuarioService.cadastrarUsuario(usuario);

        assertEquals("Falha ao preparar massa para alteração.",
                201, respostaCriacao.getStatusCode());

        this.idUsuario = respostaCriacao.jsonPath().getString("_id");
    }

    @Dado("informo um payload válido para alteração de usuário")
    public void informoUmPayloadVálidoParaAlteraçãoDeUsuário() {
        this.payload = UsuarioFactory.usuarioParaAlteracaoValido();
    }

    @Dado("informo um payload de alteração de usuário com campo nome vazio")
    public void informoUmPayloadDeAlteraçãoDeUsuárioComCampoNomeVazio() {
        this.payload = UsuarioFactory.usuarioParaAlteracaoComNomeVazio();
    }

    @Dado("informo um payload de alteração de usuário com campo email vazio")
    public void informoUmPayloadDeAlteraçãoDeUsuárioComCampoEmailVazio() {
        this.payload = UsuarioFactory.usuarioParaAlteracaoComEmailVazio();
    }

    @Dado("informo um payload de alteração de usuário com campo password vazio")
    public void informoUmPayloadDeAlteraçãoDeUsuárioComCampoPasswordVazio() {
        this.payload = UsuarioFactory.usuarioParaAlteracaoComPasswordVazio();
    }

    @Dado("informo um payload de alteração de usuário com campo administrador vazio")
    public void informoUmPayloadDeAlteraçãoDeUsuárioComCampoAdministradorVazio() {
        this.payload = UsuarioFactory.usuarioParaAlteracaoComAdministradorVazio();
    }

    @Dado("informo um id de usuário inexistente para exclusão")
    public void informoUmIdDeUsuárioInexistenteParaExclusão() {
        this.idUsuario = "idUsuarioInexistente";
    }

    @Dado("informo um id de usuário válido para exclusão")
    public void informoUmIdDeUsuárioVálidoParaExclusão() {
        Map<String, Object> usuario = UsuarioFactory.usuarioValido();
        Response respostaCriacao = UsuarioService.cadastrarUsuario(usuario);

        assertEquals("Falha ao preparar massa para exclusão.",
                201, respostaCriacao.getStatusCode());

        this.idUsuario = respostaCriacao.jsonPath().getString("_id");
    }

    @Dado("informo um id de usuário previamente excluído")
    public void informoUmIdDeUsuárioPreviamenteExcluído() {
        Map<String, Object> usuario = UsuarioFactory.usuarioValido();
        Response respostaCriacao = UsuarioService.cadastrarUsuario(usuario);

        assertEquals("Falha ao preparar massa para exclusão prévia.",
                201, respostaCriacao.getStatusCode());

        this.idUsuario = respostaCriacao.jsonPath().getString("_id");

        Response respostaExclusao = UsuarioService.excluirUsuarioPorId(this.idUsuario);
        assertEquals("Falha ao excluir previamente o usuário.",
                200, respostaExclusao.getStatusCode());
    }
}