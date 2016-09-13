package br.com.jadercampos.service;

import br.com.jadercampos.entityManager.JpaEntityManager;
import br.com.jadercampos.model.ClienteModel;

import javax.persistence.EntityManager;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/cliente")
public class ClienteService {
    private JpaEntityManager JPAEM = new JpaEntityManager();
    private EntityManager objEM = JPAEM.getEntityManager();

    @GET
    @Path("/hello")
    public String Hello(){
        return  "Olá Enfermeira!";
    }

    @GET
    @Path("/listar")
    @Produces("application/json")
    public List<ClienteModel> listar(){

        try {
            String query = "select c from ClienteModel c";
            List<ClienteModel> clientes = objEM.createNativeQuery(query, ClienteModel.class).getResultList();
            objEM.close();
            return clientes;
        } catch (Exception e) {
            throw new WebApplicationException(500);
        }
    }

    @GET
    @Path("/buscar/{id_cliente}")
    @Produces("application/json")
    public ClienteModel buscar(@PathParam("id_cliente") int id_cliente){
        try {
            ClienteModel cliente = objEM.find(ClienteModel.class, id_cliente);
            objEM.close();
            return cliente;
        } catch (Exception e) {
            throw new WebApplicationException(500);
        }
    }

    @POST
    @Path("/cadastrar")
    @Consumes("application/json")
    public Response cadastrar(ClienteModel objClinte){
        try {
            objEM.getTransaction().begin();
            objEM.persist(objClinte);
            objEM.getTransaction().commit();
            objEM.close();
            return Response.status(200).entity("cadastro realizado.").build();
        } catch (Exception e) {
            throw new WebApplicationException(500);
        }
    }

    @PUT
    @Path("/alterar")
    @Consumes("application/json")
    public Response alterar(ClienteModel objClinte){
        try {
            objEM.getTransaction().begin();
            objEM.merge(objClinte);
            objEM.getTransaction().commit();
            objEM.close();
            return Response.status(200).entity("cadastro alterado.").build();
        } catch (Exception e) {
            throw new WebApplicationException(500);
        }
    }

    @DELETE
    @Path("/excluir/{id_cliente}")
    public Response excluir(@PathParam("id_cliente") int id_cliente){
        try {
            ClienteModel objClinte = objEM.find(ClienteModel.class, id_cliente);

            objEM.getTransaction().begin();
            objEM.remove(objClinte);
            objEM.getTransaction().commit();
            objEM.close();

            return Response.status(200).entity("cadastro excluído.").build();
        } catch (Exception e) {
            throw new WebApplicationException(500);
        }
    }
}