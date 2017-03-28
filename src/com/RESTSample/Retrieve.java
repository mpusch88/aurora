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

@Path("/")
public class Retrieve {
	
	@Path("{path}")
	@GET
	@Produces({"application/json", "image/jpeg"})
	public Response parse(
			@PathParam("path") String path, 
			@QueryParam("type") String type,
			@QueryParam("data") List<String> data,
			@QueryParam("lat") String lat,
			@QueryParam("long") String lng,
			@QueryParam("action") String action,
			@QueryParam("start") String start,
			@QueryParam("end") String end,
			@QueryParam("image") String image,
			@QueryParam("ace") String ace,
			@QueryParam("archive") String archive,
			@QueryParam("forecast") String forecast,
			@QueryParam("images") String images,
			@QueryParam("probability") String probability,
			@QueryParam("threeday") String threeday,
			@QueryParam("twentysevenday") String twentysevenday,
			@QueryParam("weather") String weather,
			@QueryParam("id") String id) throws JSONException, UnirestException{
		System.out.println(type);
		
		switch(type){
		case "locations": return getLocations();
		case "ace": return getAce(data, lat, lng);
		case "archive": return getArchive(action, start, end);
		case "embed": return getEmbed(image, lat, lng);
		case "images": return getImages(action, image);
		case "weather": return getWeather(lat, lng, forecast);
		case "all": return getAll(lat, lng, ace, archive, forecast, images, probability, threeday, twentysevenday, weather);
		case "map": return getMap(id);
		default: return getError(type);
		}
	}

	@Produces("application/json")
	public Response getLocations() throws JSONException, UnirestException {
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
	
	@GET
	@Produces("application/json")
	public Response getAce(
			@QueryParam("data") List<String> data,
			@QueryParam("lat") String lat,
			@QueryParam("long") String lng) throws JSONException, UnirestException {
		
		JSONObject jsonObject = new JSONObject();
		
		String URI = "http://api.auroras.live/v1/?type=ace";
		for(int i = 0; i < data.size(); i++){
			URI += "&data=" + data.get(i);
		}
		if(lat != null || lng != null){
			URI += "&lat=" + lat + "&long=" + lng;
		}
		
		HttpResponse<JsonNode> response = 
				Unirest.get(URI)
					.header("cookie", "PHPSESSID=MW2MMg7reEHx0vQPXaKen0")
					.asJson();
	
		jsonObject = response.getBody().getObject();
		
		String att = "Powered by Auroras.live";
		jsonObject.put("Attribution", att);
		
		System.out.println(URI);
		
		return Response.status(200).entity(response.getBody().toString()).build();
	}
	
	@Produces("application/json")
	public Response getArchive(
			@QueryParam("action") String action,
			@QueryParam("start") String start,
			@QueryParam("end") String end) throws JSONException, UnirestException {
		
		JSONObject jsonObject = new JSONObject();
		
		String URI = "http://api.auroras.live/v1/?type=archive";
		if(action != null){
			URI += "&action=" + action;
		}
		if(start != null || end != null){
			
			URI += "&start=" + start + "&end=" + end;
		}
		
		HttpResponse<JsonNode> response = 
				Unirest.get(URI)
					.header("cookie", "PHPSESSID=MW2MMg7reEHx0vQPXaKen0")
					.asJson();
	
		jsonObject = response.getBody().getObject();
		
		String att = "Powered by Auroras.live";
		jsonObject.put("Attribution", att);
		
		System.out.println(URI);
		
		return Response.status(200).entity(response.getBody().toString()).build();
	}

	@Produces("image/jpeg")
	public Response getEmbed(
			@QueryParam("image") String image,
			@QueryParam("lat") String lat,
			@QueryParam("long") String lng) throws JSONException, UnirestException {
		
		String URI = "http://api.auroras.live/v1/?type=embed";
		if(image != null){
			URI += "&image=" + image;
		}
		if(lat != null || lng != null){
			URI += "&lat=" + lat + "&long=" + lng;
		}
		
		JSONObject jsonObject = new JSONObject();
		HttpResponse<InputStream> response = 
				Unirest.get(URI)
					.header("cookie", "PHPSESSID=MW2MMg7reEHx0vQPXaKen0")
					.asBinary();
	
		Object object = response.getBody();
		
		String att = "Powered by Auroras.live";
		jsonObject.put("Attribution", att);
		
		return Response.status(200).entity(object).header("Content-Type", "image/jpeg").build();
	}
	
	public Response getImages(
			@QueryParam("action") String action,
			@QueryParam("image") String image) throws JSONException, UnirestException {
		
		String URI = "http://api.auroras.live/v1/?type=images";
		
		if(action != null){
			URI += "&action=" + action;
		}
		else if(image != null){
			URI += "&image=" + image;
			
			HttpResponse<InputStream> response = 
				Unirest.get(URI)
				.header("cookie", "PHPSESSID=MW2MMg7reEHx0vQPXaKen0").asBinary();
			
			if(response.getStatus() != 200){
				HttpResponse<JsonNode> responseX = 
						Unirest.get(URI)
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
				Unirest.get(URI)
					.header("cookie", "PHPSESSID=MW2MMg7reEHx0vQPXaKen0")
					.asJson();
		JSONObject jsonObject = new JSONObject();
		jsonObject = response.getBody().getObject();
		
		String att = "Powered by Auroras.live";
		jsonObject.put("Attribution", att);
		
		System.out.println(URI);
		
		return Response.status(200).entity(response.getBody().toString()).build();
	}
	
	@Produces("application/json")
	public Response getWeather(
			@QueryParam("lat") String lat,
			@QueryParam("long") String lng,
			@QueryParam("forecast") String forecast) throws JSONException, UnirestException {
		
		String URI = "http://api.auroras.live/v1/?type=weather";
		if(lat != null || lng != null){
			URI += "&lat=" + lat + "&long=" + lng;
		}
		if(forecast != null){
			URI += "&forecast=" + forecast;
		}
		
		JSONObject jsonObject = new JSONObject();
		HttpResponse<JsonNode> response = 
				Unirest.get(URI)
					.header("cookie", "PHPSESSID=MW2MMg7reEHx0vQPXaKen0")
					.asJson();
	
		jsonObject = response.getBody().getObject();
		
		String att = "Powered by Auroras.live";
		jsonObject.put("Attribution", att);
		
		return Response.status(200).entity(response.getBody().toString()).build();
	}
	
	@Produces("application/json")
	public Response getAll(
			@QueryParam("lat") String lat,
			@QueryParam("long") String lng,
			@QueryParam("ace") String ace,
			@QueryParam("archive") String archive,
			@QueryParam("forecast") String forecast,
			@QueryParam("images") String images,
			@QueryParam("probability") String probability,
			@QueryParam("threeday") String threeday,
			@QueryParam("twentysevenday") String twentysevenday,
			@QueryParam("weather") String weather) throws JSONException, UnirestException {
		
		String URI = "http://api.auroras.live/v1/?type=all";
		
		if(lat != null || lng != null){
			URI += "&lat=" + lat + "&long=" + lng;
		}
		if(ace != null){
			URI += "&ace=" + ace;
		}
		if(archive != null){
			URI += "&archive=" + archive;
		}
		if(forecast != null){
			URI += "&forecast=" + forecast;
		}
		if(images != null){
			URI += "&images=" + images;
		}
		if(probability != null){
			URI += "&probability=" + probability;
		}
		if(threeday != null){
			URI += "&threeday=" + threeday;
		}
		if(twentysevenday != null){
			URI += "&twentysevenday=" + twentysevenday;
		}
		if(weather != null){
			URI += "&weather=" + weather;
		}
		
		JSONObject jsonObject = new JSONObject();
		HttpResponse<JsonNode> response = 
				Unirest.get(URI)
					.header("cookie", "PHPSESSID=MW2MMg7reEHx0vQPXaKen0")
					.asJson();
	
		jsonObject = response.getBody().getObject();
		
		String att = "Powered by Auroras.live";
		jsonObject.put("Attribution", att);
		
		return Response.status(200).entity(response.getBody().toString()).build();
	}
		
	private final String APIKEY = "AIzaSyDAIN1a6W6Hz1DdusqJtuVq1PLnYNn3WV8";
	
	public Response getMap(String id) throws UnirestException {
        //Getting jsonArray from auroras.live (location) api
        JSONArray json = createJSONArrayfromTextForMaps();

        //Try to find ID tag user is looking for weather in.
        JSONObject jsonObject = null;
        boolean found = false;
        for (int i = 0; i < json.length(); i++){
            JSONObject j = json.getJSONObject(i);
            if (Objects.equals(j.getString("id"), id)){
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
    
	@Produces("application/json")
	public Response getError(
			@QueryParam("type") String type) throws JSONException, UnirestException {
		
		JSONObject jsonObject = new JSONObject();
		
		String URI = "http://api.auroras.live/v1/?type=" + type;
		
		HttpResponse<JsonNode> response = 
				Unirest.get(URI)
					.header("cookie", "PHPSESSID=MW2MMg7reEHx0vQPXaKen0")
					.asJson();
	
		jsonObject = response.getBody().getObject();
		
		String att = "Powered by Auroras.live";
		jsonObject.put("Attribution", att);
		
		System.out.println(URI);
		
		return Response.status(200).entity(response.getBody().toString()).build();
	}
}