package br.mpmt.mp.app.controller;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.mpmt.mp.app.dao.Transaction;
import br.mpmt.mp.app.entidade.Chamado;
import br.mpmt.mp.app.negocio.ChamadoNegocio;
import br.mpmt.mp.app.util.enumerado.Status;

@Path("chamados")
@RequestScoped
public class ChamadoController {
	
	@Inject
	private ChamadoNegocio chamadoNegocio;
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/")
	public List<Chamado> listChamados() {
		List<Chamado> chamados;
		
		try {
			chamados = chamadoNegocio.listarTodos();
		} catch (Exception e) {
			chamados = Collections.emptyList();

			Logger.getLogger(ChamadoController.class.getName()).log(Level.SEVERE, null, e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
		
		return chamados;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/")
	public Chamado getChamado(@PathParam("id") long id) {
		try {
			return chamadoNegocio.findById(new Long(id));
		} catch (Exception e) {
			Logger.getLogger(ChamadoController.class.getName()).log(Level.SEVERE, null, e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	@Transaction
	public Response create(Chamado chamado) {
		try {
			chamado.setStatus(Status.NOVO);
			chamadoNegocio.incluir(chamado);
			
			return Response.status(Response.Status.OK).build();
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(ChamadoController.class.getName()).log(Level.SEVERE, null, e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	@Transaction
	public Response update(Chamado chamado) {
		try {
			chamadoNegocio.atualizar(chamado);
			
			return Response.status(Response.Status.OK).build();
		} catch (Exception e) {
			Logger.getLogger(ChamadoController.class.getName()).log(Level.SEVERE, null, e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DELETE
	@Path("{id}/")
	@Transaction
	public Response delete(@PathParam("id") long id) {
		try {
			chamadoNegocio.deletarPorId(new Long(id));
			
			return Response.status(Response.Status.OK).build();
		} catch (Exception e) {
			Logger.getLogger(ChamadoController.class.getName()).log(Level.SEVERE, null, e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PUT
	@Path("{id}/")
	@Transaction
	public Response concluir(@PathParam("id") long id) {
		try {
			Chamado c = new Chamado();
			c = chamadoNegocio.findById(new Long(id));
			
			c.setStatus(Status.FECHADO);
			chamadoNegocio.atualizar(c);
			
			return Response.status(Response.Status.OK).build();
		} catch (Exception e) {
			Logger.getLogger(ChamadoController.class.getName()).log(Level.SEVERE, null, e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
}
