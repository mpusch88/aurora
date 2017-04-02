package requestTypes;

public class LocationsRequest extends RequestObject{

	public LocationsRequest() {
		super("locations");
		this.setUri("http://api.auroras.live/v1/?type=locations");
	}

}
