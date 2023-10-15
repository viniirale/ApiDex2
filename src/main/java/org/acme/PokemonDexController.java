package org.acme;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.File;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/pokemon")
public class PokemonDexController {
    @GET
    @Path("info/{id}")
    @Produces(MediaType.APPLICATION_JSON) // Define o tipo de conteúdo da resposta como texto plano
    public Response getPokemonDex(@PathParam("id") int id) {
        try {
            // Crie o caminho para pegar o arquivo JSON contendo as informações do pokemon por ID
            File pokemonFile = new File("src/main/resources/pokedex.json");
            // Verifique se o arquivo existe
           
            if (pokemonFile.exists()) {
                System.out.println("BANAAAAAAAAAAAAAAAAAAAAAA\n"); 
                //Cria uma lista a partir do arquivo JSON
                List<Pokemon> pokemonList = new ObjectMapper().readValue(pokemonFile, new TypeReference<List<Pokemon>>(){});
                Optional<Pokemon> pokemon = pokemonList.stream()
                .filter(p -> p.getId() == id)
                .findFirst();
                if (pokemon.isPresent()) {
                    return Response.ok(pokemon.get()).build();
                } else {
                    return Response.status(Response.Status.NOT_FOUND).build();
                }
            } else {
                // Se o arquivo não existir, retorne uma resposta 404 (não encontrado)
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            
        } catch (Exception e) {
            // Retorna a mensagem de erro do debug
            System.out.println(e.getMessage());

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
