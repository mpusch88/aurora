package com.RESTSample;

import java.io.InputStream;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@Path("/")
public class HelloWorld 
{	
	@Path("type=locations")
	@GET
	@Produces("application/json")
	public Response getLocations() throws JSONException, UnirestException
	{
		HttpResponse<JsonNode> response = 
				Unirest.get("http://api.auroras.live/v1/?type=locations")
                   	.header("cookie", "PHPSESSID=MW2MMg7reEHx0vQPXaKen0")
		            .asJson();
		
        JSONArray jsonArray = new JSONArray();
        jsonArray = response.getBody().getArray();
        JSONObject jsonAtt = new JSONObject();
        String att = "Powered by Auroras.live";
		jsonAtt.put("Attribution", att);
        jsonArray.put(jsonAtt);
		return Response.status(200).entity(jsonArray.toString()).build();
	}
	
	@Path("type=ace{data}")
	@GET
	@Produces("application/json")
	public Response getACE(@PathParam("data") String data) throws JSONException, UnirestException 
	{
		JSONObject jsonObject = new JSONObject();
		HttpResponse<JsonNode> response = 
				Unirest.get("http://api.auroras.live/v1/?type=ace" + data)
					.header("cookie", "PHPSESSID=MW2MMg7reEHx0vQPXaKen0")
					.asJson();
	
		jsonObject = response.getBody().getObject();
		String att = "Powered by Auroras.live";
		jsonObject.put("Attribution", att);
		return Response.status(200).entity(response.getBody().toString()).build();
	}
	
	@Path("type=embed{data}")
	@GET
	@Produces("image/jpeg")
	public Response getEmbeded(@PathParam("data") String data) throws JSONException, UnirestException {
		
		JSONObject jsonObject = new JSONObject();
		HttpResponse<InputStream> response = 
				Unirest.get("http://api.auroras.live/v1/?type=embed" + data)
				.header("cookie", "PHPSESSID=MW2MMg7reEHx0vQPXaKen0").asBinary();
	
		Object object = response.getBody();
		String att = "Powered by Auroras.live";
		jsonObject.put("Attribution", att);
		return Response.status(200).entity(object).build();
	}
	
	@Path("type=images&image={img}")
	@GET
	@Produces("image/jpeg")
	public Response getFullImage(@PathParam("img") String img) throws JSONException, UnirestException
	{
		HttpResponse<InputStream> response = 
				Unirest.get("http://api.auroras.live/v1/?type=images&image=" + img)
				.header("cookie", "PHPSESSID=MW2MMg7reEHx0vQPXaKen0").asBinary();
		
		Object object = response.getBody();		
		return Response.status(200).entity(object).build();
	}
	
	@Path("type=images&action={action}")
	@GET
	@Produces("application/json")
	public Response getImageList(@PathParam("action") String action) throws JSONException, UnirestException
	{
			JSONObject jsonObject = new JSONObject();
			HttpResponse<JsonNode> response = 
					Unirest.get("http://api.auroras.live/v1/?type=images&action=" + action)
					.header("cookie", "PHPSESSID=MW2MMg7reEHx0vQPXaKen0").asJson();
			
			jsonObject = response.getBody().getObject();
			String att = "Powered by Aurora.live";
			jsonObject.put("Attribution", att);
			return Response.status(200).entity(response.getBody().toString()).build();
	}
}