package com.fuze.takehome.jaxrs.endpoint;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fuze.takehome.model.Department;
import com.fuze.takehome.service.DepartmentService;

@Path("/departments")
public class DepartmentEndpoint {
	
	@Inject
	private DepartmentService service; 

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Department create(@Valid @NotNull Department entity) {
			return service.create(entity);
	}

	@GET
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Department read(@NotNull @PathParam("id") Long id) {
		return service.read(id);
	}

	@DELETE
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Department delete(@NotNull @PathParam("id") Long id) {
		return service.delete(id);		
	}
}
