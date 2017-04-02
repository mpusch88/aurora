package requestTypes;

public class WeatherRequest extends RequestObject {
	// required params
	private Float lat, lng;
	// optional params
	private String forecast;
	
	public WeatherRequest(Float lat, Float lng, String forecast) {
		super("weather");
		this.lat = lat;
		this.lng = lng;
		this.forecast = forecast;
		String url = "http://api.auroras.live/v1/?type=weather";
		if(lat != null || lng != null){
			url += "&lat=" + lat.toString() + "&long=" + lng.toString();
		}
		if(forecast != null){
			url += "&forecast=" + forecast;
		}
	}
}
