package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class ClienteVO extends PersonaVO {
	
	public ClienteVO() {
		super();
	}
	
	@JsonProperty(value="inmuebles")
	private List<Long> inmuebles;

}
