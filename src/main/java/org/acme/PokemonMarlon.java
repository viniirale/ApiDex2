package org.acme;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import jakarta.annotation.security.RolesAllowed;

@Path("/pokemon")
public class PokemonMarlon {

    @GET
    @RolesAllowed({"User", "Admin"})
    @Path("image/{id}")
    @Produces("image/png")
    public Response getPokemonSprite(@PathParam("id") String id) {
        try {
            // URL da imagem com autenticação básica
            String imageUrl = "https://special-fiesta-w5qr5vwqq69h5vw5-8080.app.github.dev/pokemon/" + id;

            // Configuração da autenticação básica
            String username = "teste";
            String password = "teste";
            String auth = username + ":" + password;
            String authHeaderValue = "Basic " + Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));

            // Conecta à URL
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Authorization", authHeaderValue);

            // Verifica se a resposta é bem-sucedida
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return Response.status(connection.getResponseCode()).entity("Failed : HTTP error code : " + connection.getResponseCode()).build();
            }

            // Lê a imagem a partir do stream de entrada
            try (InputStream inputStream = connection.getInputStream()) {
                // Converte o stream para um array de bytes
                byte[] imageData = inputStream.readAllBytes();

                // Retorna a imagem como uma resposta
                return Response.ok(imageData).build();
            }
        } catch (IOException e) {
            // Log do erro para facilitar a depuração
            e.printStackTrace();

            // Se ocorrer algum erro ao obter a imagem, retorne uma resposta 500 (erro interno do servidor)
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
