package factory;

import java.util.HashMap;
import java.util.Map;

public class UsuarioFactory {

    private static String gerarEmailUnico() {
        return "rai.teste." + System.currentTimeMillis() + "@qa.com";
    }

    public static Map<String, Object> usuarioValido() {
        Map<String, Object> usuario = new HashMap<>();
        usuario.put("nome", "Rai Teste");
        usuario.put("email", gerarEmailUnico());
        usuario.put("password", "123456");
        usuario.put("administrador", "true");
        return usuario;
    }

    public static Map<String, Object> usuarioComEmailInvalidoSemArroba() {
        Map<String, Object> usuario = usuarioValido();
        usuario.put("email", "raiteste.qa.com");
        return usuario;
    }

    public static Map<String, Object> usuarioComEmailVazio() {
        Map<String, Object> usuario = usuarioValido();
        usuario.put("email", "");
        return usuario;
    }

    public static Map<String, Object> usuarioComSenhaVazia() {
        Map<String, Object> usuario = usuarioValido();
        usuario.put("password", "");
        return usuario;
    }

    public static Map<String, Object> usuarioComNomeVazio() {
        Map<String, Object> usuario = usuarioValido();
        usuario.put("nome", "");
        return usuario;
    }

    public static Map<String, Object> usuarioComAdministradorVazio() {
        Map<String, Object> usuario = usuarioValido();
        usuario.put("administrador", "");
        return usuario;
    }

    public static Map<String, Object> usuarioComTodosCamposVazios() {
        Map<String, Object> usuario = new HashMap<>();
        usuario.put("nome", "");
        usuario.put("email", "");
        usuario.put("password", "");
        usuario.put("administrador", "");
        return usuario;
    }

    public static Map<String, Object> usuarioComEmailJaCadastrado(String email) {
        Map<String, Object> usuario = usuarioValido();
        usuario.put("email", email);
        return usuario;
    }

    public static Map<String, Object> usuarioParaAlteracaoValido() {
        Map<String, Object> usuario = new HashMap<>();
        usuario.put("nome", "Rai Alterado");
        usuario.put("email", gerarEmailUnico());
        usuario.put("password", "654321");
        usuario.put("administrador", "false");
        return usuario;
    }

    public static Map<String, Object> usuarioParaAlteracaoComNomeVazio() {
        Map<String, Object> usuario = usuarioParaAlteracaoValido();
        usuario.put("nome", "");
        return usuario;
    }

    public static Map<String, Object> usuarioParaAlteracaoComEmailVazio() {
        Map<String, Object> usuario = usuarioParaAlteracaoValido();
        usuario.put("email", "");
        return usuario;
    }

    public static Map<String, Object> usuarioParaAlteracaoComPasswordVazio() {
        Map<String, Object> usuario = usuarioParaAlteracaoValido();
        usuario.put("password", "");
        return usuario;
    }

    public static Map<String, Object> usuarioParaAlteracaoComAdministradorVazio() {
        Map<String, Object> usuario = usuarioParaAlteracaoValido();
        usuario.put("administrador", "");
        return usuario;
    }
}
