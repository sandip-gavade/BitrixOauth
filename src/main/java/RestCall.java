package main.java;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;


@Path("/request")
public class RestCall {
	
	 /*public static void main(String[] args) {*/
	
	@POST
	@Produces(MediaType.APPLICATION_XML)
		 public void getLoan() {
	
	Client client = Client.create();
	StringBuilder urlSB = new StringBuilder().append("http://demofirst-1245.appspot.com/rest/crm.lead.add?auth=")
			.append("i9yyfeslnblwwzlstrwfug3lp5w3mmz4").append("&FIELDS[TITLE]=+title").append("&FIELDS[NAME]=+name")
			.append("&FIELDS[OPPORTUNITY]=+amount");

	WebResource webResource = client.resource(urlSB.toString());

	 ClientResponse response = webResource.type("application/json").post(ClientResponse.class,urlSB.toString());

	


	if (response.getStatus() != 200) {
		System.out.println("Message = " + response.getLocation());
		throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
	}

	System.out.println("Output from Server .... \n");
	String output = response.getEntity(String.class);
	//System.out.println(output)

}
	 
}
