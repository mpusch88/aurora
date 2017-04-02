package requestTypes;

public class MapRequest extends RequestObject{
	private String id;
	
	public MapRequest(String id) {
		super("map");
		this.id = id;
		this.setUri(id);
	}
}
