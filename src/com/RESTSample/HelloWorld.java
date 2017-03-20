package com.RESTSample;

import java.io.InputStream;
import java.util.Objects;

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
	// NECESSARY??
    private final String APIKEY = "AIzaSyDAIN1a6W6Hz1DdusqJtuVq1PLnYNn3WV8";

    // BROKEN!!
    private JSONArray createJSONArrayfromText(String action)
    {
        try 
        {
            HttpResponse<JsonNode> response = 
            		Unirest.get("https://api.auroras.live/v1/" + action)
                    .header("accept", "application/json")
                    .asJson();
            if (jsonResponse.getBody().isArray()){
                return jsonResponse.getBody().getArray();
            }else{
                throw new UnirestException("Should not be object");
            }

        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return null;
    }
    
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
	
	
	
	// IMAGES CURRENTLY FAIL TEST 22 - MUST BE ABLE TO RETURN JSON
	
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
	
	
	
	// ARCHIVE FAILS TEST 17 - (POSSIBLY IGNORING % IN URL)
	
	@Path("type=archive{action}")
	@GET
	@Produces("application/json")
	public Response getArchive(@PathParam("action") String action) throws JSONException, UnirestException
	{
			JSONObject jsonObject = new JSONObject();
			HttpResponse<JsonNode> response = 
					Unirest.get("http://api.auroras.live/v1/?type=archive" + action)
					.header("cookie", "PHPSESSID=MW2MMg7reEHx0vQPXaKen0").asJson();
			
			jsonObject = response.getBody().getObject();
			String att = "Powered by Aurora.live";
			jsonObject.put("Attribution", att);
			return Response.status(200).entity(response.getBody().toString()).build();
	}
	
	@Path("type=weather{action}")
	@GET
	@Produces("application/json")
	public Response getWeather(@PathParam("action") String action) throws JSONException, UnirestException
	{
		JSONObject jsonObject = new JSONObject();
		HttpResponse<JsonNode> response = 
			Unirest.get("http://api.auroras.live/v1/?type=weather" + action)
			.header("cookie", "PHPSESSID=MW2MMg7reEHx0vQPXaKen0").asJson();
		
		jsonObject = response.getBody().getObject();
		String att = "Powered by Aurora.live";
		jsonObject.put("Attribution", att);
		return Response.status(200).entity(response.getBody().toString()).build();
	}
	
	@Path("type=all{action}")
	@GET
	@Produces("application/json")
	public Response getAll(@PathParam("action") String action) throws JSONException, UnirestException
	{
			JSONObject jsonObject = new JSONObject();
			HttpResponse<JsonNode> response = 
					Unirest.get("http://api.auroras.live/v1/?type=all" + action)
					.header("cookie", "PHPSESSID=MW2MMg7reEHx0vQPXaKen0").asJson();
			
			jsonObject = response.getBody().getObject();
			String att = "Powered by Aurora.live";
			jsonObject.put("Attribution", att);
			return Response.status(200).entity(response.getBody().toString()).build();
	}
    
	
	
	// BROKEN!!
	
	@Path("type=map{action}")
	@GET
	@Produces()
	public Response getMap(@PathParam("action") String action) throws JSONException, UnirestException
	{
		JSONArray json = createJSONArrayfromText();
		JSONObject jsonObject = new JSONObject();
		boolean found = false;
		
        for (int i = 0; i < json.length(); i++)
        {
            JSONObject j = json.getJSONObject(i);
            if (Objects.equals(j.getString("id"), action)) // ???
            {
                found = true;
                jsonObject = j;
            }
        }
        
        if (!found){
            System.out.println("request not in auroras.live repo");
        }
        
        else	//ID Tag found, let us make the call to google maps api
        {
            String request = "";
            String longCoor = jsonObject.getString("long");
            String latCoor = jsonObject.getString("lat");

            try { //Call google maps api (Currently returning png, can change to whatever works best.
                String address = "https://maps.googleapis.com/maps/api/staticmap?center="+latCoor+","+longCoor+"&zoom=13&size=600x300&format=png&key=" + APIKEY;
                HttpResponse mapResponse = Unirest.get(address)
                        .header("accept", "application/json")
                        .asBinary();
                //Contains raw binary data.
                InputStream is = mapResponse.getRawBody();
                //Currently storing as picture, return as picture (Similar to parseImage or parseEmbed)
                File f = new File("test.png");
                Files.copy(is, f.toPath());
            } catch (UnirestException | IOException e) {
                e.printStackTrace();
            }
        }
        
		/*
		HttpResponse<JsonNode> response = 
				Unirest.get("http://api.auroras.live/v1/?" + action)
				.header("cookie", "PHPSESSID=MW2MMg7reEHx0vQPXaKen0").asJson();
		
		jsonObject = response.getBody().getObject();
		String att = "Powered by Aurora.live";
		jsonObject.put("Attribution", att);
		return Response.status(200).entity(response.getBody().toString()).build();
		*/
	}
	
	@Path("{failed}")
	@GET
	@Produces("application/json")
	public Response failedRequest(@PathParam("failed") String failed) throws JSONException, UnirestException
	{
		JSONObject jsonObject = new JSONObject();
		HttpResponse<JsonNode> response = 
				Unirest.get("http://api.auroras.live/v1/?" + failed)
				.header("cookie", "PHPSESSID=MW2MMg7reEHx0vQPXaKen0").asJson();
		
		jsonObject = response.getBody().getObject();
		String att = "Powered by Aurora.live";
		jsonObject.put("Attribution", att);
		return Response.status(200).entity(response.getBody().toString()).build();
	}
}