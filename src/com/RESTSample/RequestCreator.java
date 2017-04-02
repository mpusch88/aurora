package com.RESTSample;

import java.io.InputStream;
import java.util.List;
import java.util.Objects;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import requestTypes.ImagesRequest;
import requestTypes.RequestObject;

public class RequestCreator {

	public RequestObject getResponse(RequestObject request) {
		if(request == null){
			return null;
		}
		try{
			switch(request.getType()){
			case "locations": request.setResponse(getLocations(request)); break;
			case "ace": request.setResponse(getAce(request)); break;
			case "archive": request.setResponse(getArchive(request)); break;
			case "embed": request.setResponse(getEmbed(request)); break;
			case "images": request.setResponse(getImages(request)); break;
			case "weather": request.setResponse(getWeather(request)); break;
			case "all": request.setResponse(getAll(request)); break;
			case "map": request.setResponse(getMap(request)); break;
			}
		}catch(Exception e){
			
		}
		
		
		return request;
	}
	
	@Produces("application/json")
	public Response getLocations(RequestObject request) throws JSONException, UnirestException {
		HttpResponse<JsonNode> response = 
				Unirest.get(request.getUri())
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
	
	@Produces("application/json")
	public Response getAce(RequestObject request) throws JSONException, UnirestException {
		
		JSONObject jsonObject = new JSONObject();
		
		HttpResponse<JsonNode> response = 
				Unirest.get(request.getUri())
					.header("cookie", "PHPSESSID=MW2MMg7reEHx0vQPXaKen0")
					.asJson();
	
		jsonObject = response.getBody().getObject();
		
		String att = "Powered by Auroras.live";
		jsonObject.put("Attribution", att);
		
		return Response.status(200).entity(response.getBody().toString()).build();
	}
	

	@Produces("application/json")
	public Response getArchive(RequestObject request) throws JSONException, UnirestException {
		
		JSONObject jsonObject = new JSONObject();
		
		HttpResponse<JsonNode> response = 
				Unirest.get(request.getUri())
					.header("cookie", "PHPSESSID=MW2MMg7reEHx0vQPXaKen0")
					.asJson();
	
		jsonObject = response.getBody().getObject();
		
		String att = "Powered by Auroras.live";
		jsonObject.put("Attribution", att);
		
		return Response.status(200).entity(response.getBody().toString()).build();
	}

	@Produces("image/jpeg")
	public Response getEmbed(RequestObject request) throws JSONException, UnirestException {
		
		JSONObject jsonObject = new JSONObject();
		HttpResponse<InputStream> response = 
				Unirest.get(request.getUri())
					.header("cookie", "PHPSESSID=MW2MMg7reEHx0vQPXaKen0")
					.asBinary();
	
		Object object = response.getBody();
		
		String att = "Powered by Auroras.live";
		jsonObject.put("Attribution", att);
		
		return Response.status(200).entity(object).header("Content-Type", "image/jpeg").build();
	}
	
	public Response getImages(RequestObject request) throws JSONException, UnirestException {
		
		if(((ImagesRequest)request).getImage() != null){
			
			HttpResponse<InputStream> response = 
				Unirest.get(request.getUri())
				.header("cookie", "PHPSESSID=MW2MMg7reEHx0vQPXaKen0").asBinary();
			
			if(response.getStatus() != 200){
				HttpResponse<JsonNode> responseX = 
						Unirest.get(request.getUri())
							.header("cookie", "PHPSESSID=MW2MMg7reEHx0vQPXaKen0")
							.asJson();
				
				JSONObject jsonObject = new JSONObject();
				jsonObject = responseX.getBody().getObject();
				
				String att = "Powered by Auroras.live";
				jsonObject.put("Attribution", att);
				
				return Response.status(200).entity(responseX.getBody().toString()).build();
			}
		
			Object object = response.getBody();		
			return Response.status(200).entity(object).header("Content-Type", "image/jpeg").build();
		}
		
		HttpResponse<JsonNode> response = 
				Unirest.get(request.getUri())
					.header("cookie", "PHPSESSID=MW2MMg7reEHx0vQPXaKen0")
					.asJson();
		JSONObject jsonObject = new JSONObject();
		jsonObject = response.getBody().getObject();
		
		String att = "Powered by Auroras.live";
		jsonObject.put("Attribution", att);
		
		
		return Response.status(200).entity(response.getBody().toString()).build();
	}
	
	@Produces("application/json")
	public Response getWeather(RequestObject request) throws JSONException, UnirestException {
		
		JSONObject jsonObject = new JSONObject();
		HttpResponse<JsonNode> response = 
				Unirest.get(request.getUri())
					.header("cookie", "PHPSESSID=MW2MMg7reEHx0vQPXaKen0")
					.asJson();
	
		jsonObject = response.getBody().getObject();
		
		String att = "Powered by Auroras.live";
		jsonObject.put("Attribution", att);
		
		return Response.status(200).entity(response.getBody().toString()).build();
	}
	
	@Produces("application/json")
	public Response getAll(RequestObject request) throws JSONException, UnirestException {
		
		JSONObject jsonObject = new JSONObject();
		HttpResponse<JsonNode> response = 
				Unirest.get(request.getUri())
					.header("cookie", "PHPSESSID=MW2MMg7reEHx0vQPXaKen0")
					.asJson();
	
		jsonObject = response.getBody().getObject();
		
		String att = "Powered by Auroras.live";
		jsonObject.put("Attribution", att);
		
		return Response.status(200).entity(response.getBody().toString()).build();
	}
		
	private final String APIKEY = "AIzaSyDAIN1a6W6Hz1DdusqJtuVq1PLnYNn3WV8";
	public Response getMap(RequestObject request) throws UnirestException {
        //Getting jsonArray from auroras.live (location) api
        JSONArray json = createJSONArrayfromTextForMaps();

        //Try to find ID tag user is looking for weather in.
        JSONObject jsonObject = null;
        boolean found = false;
        for (int i = 0; i < json.length(); i++){
            JSONObject j = json.getJSONObject(i);
            if (Objects.equals(j.getString("id"), request.getUri())){
                found = true;
                jsonObject = j;
            }
        }
        if (!found){
            jsonObject = new JSONObject();
            jsonObject.put("Message", "request not in auroras.live repo");
            
            return Response.status(200).entity(jsonObject.toString()).build();
        }else{
            //ID Tag found, let us make the call to google maps api
            String longCoor = jsonObject.getString("long");
            String latCoor = jsonObject.getString("lat");

            String address = "https://maps.googleapis.com/maps/api/staticmap?center="+latCoor+","+longCoor+"&zoom=13&size=600x300&format=png&key=" + APIKEY;
            HttpResponse response = Unirest.get(address)
                    .header("accept", "application/json")
                    .asBinary();
            //Contains raw binary data.
            //InputStream is = mapResponse.getRawBody();
            Object object = response.getBody();    
            
            return Response.status(200).entity(object).header("Content-Type", "image/jpeg").build();
        }
	}
	
	//Given a valid address creates a JSON Array from request.
    private JSONArray createJSONArrayfromTextForMaps(){
        try {
            HttpResponse<JsonNode> jsonResponse = Unirest.get("http://api.auroras.live/v1/?type=locations")
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
}
