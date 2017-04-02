package requestTypes;

public class ArchiveRequest extends RequestObject{
	private String action;
	private String start;
	private String end;
	
	public ArchiveRequest(String action, String start, String end) {
		super("archive");
		this.action = action;
		this.start = start;
		this.end = end;
		
		String URI = "http://api.auroras.live/v1/?type=archive";
		if(action != null){
			URI += "&action=" + action;
		}
		if(start != null || end != null){
			URI += "&start=" + start + "&end=" + end;
		}
		this.setUri(URI);
	}
}
