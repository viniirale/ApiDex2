package org.acme;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import java.io.File;
import jakarta.annotation.security.RolesAllowed;


@Path("/pokemon")
public class PokemonSpriteController {
    @GET
    @RolesAllowed({"User", "Admin"})
    @Path("sprite/{id}")
    @Produces("image/png") // Define o tipo de conteúdo da resposta como imagem png
    public Response getPokemonSprite(@PathParam("id") String id) {
        try {
            // Crie o caminho para a imagem com base no ID fornecido
            File spriteFile = new File("src/main/resources/pokemon-sprites/" + id + "MS.png");
            // Verifique se a imagem existe
            if (spriteFile.exists()) {
                // Retorna a imagem como uma resposta
                return Response.ok(spriteFile).build();
            } else {
                // Se a imagem não existir, retorne uma resposta 404 (não encontrado)
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            
        } catch (Exception e) {
            // Lida com erros e retorna uma resposta de erro
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
