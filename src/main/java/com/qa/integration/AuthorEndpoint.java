package com.qa.integration;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.qa.service.business.AuthorService;

@Path("/author")
public class AuthorEndpoint {

	@Inject
	private AuthorService service;

	@Path("/json")
	@GET
	@Produces({ "application/json" })
	public String getAllAuthors() {
		return service.getAllAuthors();
	}

	@Path("/json")
	@POST
	@Produces({ "application/json" })
	public String addAuthor(String author) {
		return service.addAuthor(author);
	}

	@Path("/json/{id}")
	@PUT
	@Produces({ "application/json" })
	public String updateAuthor(@PathParam("id") Long id, String author) {
		return service.updateAuthor(id, author);
	}

	@Path("/json/{id}")
	@DELETE
	@Produces({ "application/json" })
	public String deleteAuthor(@PathParam("id") Long id) {
		return service.deleteAuthor(id);

	}

	public void setService(AuthorService service) {
		this.service = service;
	}

}
