import java.util.ArrayList;
import java.util.List;

public class Aeronave {

	private String identidade;

	private List<Piloto> pilotos;
	
	public Aeronave() {
		this.pilotos = new ArrayList<Piloto>();
	}
	
	public void podePilotar(Piloto piloto) {
		pilotos.add(piloto);
	}
	
	public void pilotando() {
		pilotos.forEach(piloto -> {
			System.out.println("Está pilotando: " + piloto.getNome());
		});
	}
	
	public String getIdentidade() {
		return identidade;
	}

	public void setIdentidade(String identidade) {
		this.identidade = identidade;
	}
	
}
