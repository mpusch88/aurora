package requestTypes;

public class AllRequest extends RequestObject{
	// required params
	private Float lat, lng;
	// optional params
	private String ace, archive, forecast, images, probability, threeday, twentysevenday, weather;
	
	public AllRequest(Float lat, Float lng, String ace, String archive, String forecast, String images,
			String probability, String threeday, String twentysevenday, String weather) {
		super("all");
		this.lat = lat;
		this.lng = lng;
		this.ace = ace;
		this.archive = archive;
		this.forecast = forecast;
		this.images = images;
		this.probability = probability;
		this.threeday = threeday;
		this.twentysevenday = twentysevenday;
		this.weather = weather;
		
		String URI = "http://api.auroras.live/v1/?type=all";
		if(lat != null || lng != null){
			URI += "&lat=" + lat.toString() + "&long=" + lng.toString();
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
		this.setUri(URI);
	}
}
