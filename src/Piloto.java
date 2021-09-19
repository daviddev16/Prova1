
public class Piloto extends Pessoa {

	private int habilitacao;
	
	public Piloto(String nome, String cpf) {
		super(nome, cpf);
	}

	public String toString() {
		return "Nome: " + getNome() + ", CPF: " + getCpf() + ", Tipo: Piloto";
	}

	public int getHabilitacao() {
		return habilitacao;
	}

	public void setHabilitacao(int habilitacao) {
		this.habilitacao = habilitacao;
	}
}
