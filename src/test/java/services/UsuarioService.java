package services;

import static io.restassured.RestAssured.given;

import java.util.Map;

import config.EndpointsProperties;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UsuarioService {

    public static Response cadastrarUsuario(Map<String, Object> payload) {
        return given()
                .contentType(ContentType.JSON)
                .body(payload)
        .when()
                .post(EndpointsProperties.USUARIOS_PATH);
    }

    public static Response listarUsuarios() {
        return given()
                .contentType(ContentType.JSON)
        .when()
                .get(EndpointsProperties.USUARIOS_PATH);
    }

    public static Response listarUsuariosComFiltros(Map<String, Object> filtros) {
        return given()
                .contentType(ContentType.JSON)
                .queryParams(filtros)
        .when()
                .get(EndpointsProperties.USUARIOS_PATH);
    }

    public static Response buscarUsuarioPorId(String idUsuario) {
        return given()
                .contentType(ContentType.JSON)
                .pathParam("id", idUsuario)
        .when()
                .get(EndpointsProperties.USUARIOS_ID_PATH);
    }

    public static Response alterarUsuarioPorId(String idUsuario, Map<String, Object> payload) {
        return given()
                .contentType(ContentType.JSON)
                .pathParam("id", idUsuario)
                .body(payload)
        .when()
                .put(EndpointsProperties.USUARIOS_ID_PATH);
    }

    public static Response excluirUsuarioPorId(String idUsuario) {
        return given()
                .contentType(ContentType.JSON)
                .pathParam("id", idUsuario)
        .when()
                .delete(EndpointsProperties.USUARIOS_ID_PATH);
    }
}