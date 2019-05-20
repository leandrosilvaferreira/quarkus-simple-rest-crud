
package org.acme.quarkus.sample;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

@Path("/pessoa")
public class PessoasResource {

    static Set<String> pessoas = new HashSet<>();

    @POST
    @Path("/{nome}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response persist(@PathParam(value = "nome") String nome) {

        pessoas.add(nome);

        return Response.ok(pessoas).build();
    }

    @PUT
    @Path("/{nome}/{novonome}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response update(@PathParam(value = "nome") String nome, @PathParam(value = "novonome") String novonome) {

        Optional<String> first = pessoas.stream().filter(pessoa -> pessoa.equals(nome)).findFirst();

        if (first.isPresent()) {
            pessoas.remove(nome);
            pessoas.add(novonome);
            return Response.ok(pessoas).build();
        }

        return Response.status(Status.NOT_FOUND).entity("Pessoa não encontrada").build();
    }

    @DELETE
    @Path("/{nome}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response delete(@PathParam(value = "nome") String nome) {

        Optional<String> first = pessoas.stream().filter(pessoa -> pessoa.equals(nome)).findFirst();

        if (first.isPresent()) {
            pessoas.remove(nome);
            return Response.ok(pessoas).build();
        }

        return Response.status(Status.NOT_FOUND).entity("Pessoa não encontrada").build();
    }

    @GET
    @Path("/{nome}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response findByName(@PathParam("nome") String nome) {

        Optional<String> first = pessoas.stream().filter(pessoa -> pessoa.equals(nome)).findFirst();

        return first.isPresent() ? Response.ok(first.get()).build() : Response.status(Status.NOT_FOUND).entity("Pessoa não encontrada").build();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response find(@Parameter(name = "nome", required = false) @QueryParam("nome") String nome) {

        if (nome != null && !nome.isEmpty()) {
            return findByName(nome);
        }

        return Response.ok(pessoas).build();
    }

}
