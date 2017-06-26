package br.mpmt.mp.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
		
		c1.setId(2L);
		c2.setAssunto("Assunto 2");
		c2.setMensagem("Mensagem 2");
		c2.setStatus(Status.NOVO);
		
		Chamado c3 = new Chamado();
		
		c1.setId(3L);
		c3.setAssunto("Assunto 3");
		c3.setMensagem("Mensagem 3");
		c3.setStatus(Status.FECHADO);
		
		List<Chamado> chamados = new ArrayList<>();
		
		chamados.add(c1);
		chamados.add(c2);
		chamados.add(c3);
		
		return chamados;
	}
}
