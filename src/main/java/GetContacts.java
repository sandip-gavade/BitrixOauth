package main.java;



import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/crm.contact.fields")
public class GetContacts {

	
	@GET
	@Produces(MediaType.TEXT_XML)
	public Response getFields(
		@QueryParam("scope") String abc,
		@QueryParam("auth") String auth) {

		return Response.status(200).entity("output"+auth+"abc"+abc).build();
		   

	}

}