package br.mpmt.mp.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.mpmt.mp.app.entidade.Chamado;
import br.mpmt.mp.app.util.enumerado.Status;

@Path("chamados")
public class ChamadoController {
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/")
	public List<Chamado> listChamados() {
		Chamado c1 = new Chamado();
		
		c1.setId(1L);
		c1.setAssunto("Assunto 1");
		c1.setMensagem("Mensagem 1");
		c1.setStatus(Status.NOVO);
		
		Chamado c2 = new Chamado();
		
		c2.setId(2L);
		c2.setAssunto("Assunto 2");
		c2.setMensagem("Mensagem 2");
		c2.setStatus(Status.NOVO);
		
		Chamado c3 = new Chamado();
		
		c3.setId(3L);
		c3.setAssunto("Assunto 3");
		c3.setMensagem("Mensagem 3");
		c3.setStatus(Status.FECHADO);
		
		List<Chamado> chamados = new ArrayList<>();
		
		chamados.add(c1);
		chamados.add(c2);
		chamados.add(c3);
		
		return chamados;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/")
	public Chamado getChamado(@PathParam("id") long id) {
		Chamado c1 = new Chamado();
		
		c1.setId(id);
		c1.setAssunto("Assunto " + id);
		c1.setMensagem("Mensagem " + id);
		c1.setStatus(Status.NOVO);
		
		return c1;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response create(Chamado chamado) {
		System.out.println(chamado.toString());
		
		return Response.status(Response.Status.OK).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response update(Chamado chamado) {
		System.out.println(chamado.toString());
		
		return Response.status(Response.Status.OK).build();
	}
	
	@DELETE
	@Path("{id}/")
	public Response delete(@PathParam("id") long id) {
		System.out.println("Deletando ID: " + id);
		
		return Response.status(Response.Status.OK).build();
	}
}
