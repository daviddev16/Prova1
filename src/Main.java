import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class Main {

	public static final int ADICIONAR_PASSAGEIRO = 0;
	public static final int LISTAR_PILOTOS = 1;
	public static final int LOCALIZAR_PILOTO = 2;
	public static final int AUMENTAR_LISTA = 3;
	public static final int CONTINUAR = 5;
	public static int TERMINAR = -1;

	public static void main(String[] args) {
		
		System.out.println("| Adicionar passageiro: 0 | Listar pilotos: 1 | Localizar piloto: 2 | Aumentar lista: 3\n");
		Scanner scanner = new Scanner(System.in);

		Aeronave aeronave = new Aeronave();
		Pessoa[] pessoas = new Pessoa[5];
		int action = TERMINAR;

		do {
			System.out.println("Selecionar opção: ");
			action = scanner.nextInt();

			if(!validarOpçao(action)) {
				System.out.println("Opção invalida.");
				action = CONTINUAR;
				continue;
			}
			
			switch(action) {
			case ADICIONAR_PASSAGEIRO:
				action = adicionarPessoa(scanner, pessoas);
				break;
			case LISTAR_PILOTOS:
				action = listarPessoas(scanner, pessoas);
				break;
			case LOCALIZAR_PILOTO:
				action = localizar(scanner, pessoas);
				break;
			case AUMENTAR_LISTA:
				AtomicReference<Pessoa[]> reference = new AtomicReference<Pessoa[]>(pessoas);
				action = aumentarLista(scanner, reference);
				pessoas = reference.get();
				break;
			}
		}
		while(action != TERMINAR);
		
		int numPessoas = 0;
		for(int i = 0; i < pessoas.length; i++) {
			if(pessoas[i] != null) {
				numPessoas++;
			}
		}
		
		System.out.println("Total de pessoas " + numPessoas + "/" + pessoas.length);
		
		for(int i = 0; i < pessoas.length; i++) {
			if(pessoas[i] != null && pessoas[i] instanceof Piloto) {
				aeronave.podePilotar((Piloto)pessoas[i]);
			}
		}
		aeronave.pilotando();
		
	}
	
	public static int localizar(Scanner scanner, Pessoa[] listaPessoas) {

		System.out.println("-=-=-=-=-=-");
		System.out.println("CPF: ");
		String cpf = scanner.next();
		for(int i = 0; i < listaPessoas.length; i++) {
			if(listaPessoas[i] != null) {
				if(listaPessoas[i].getCpf().equalsIgnoreCase(cpf)) {
					System.out.println(listaPessoas[i].getNome());
					break;
				}
			}
		}
		
		return askContinuar(scanner);
		
	}

	public static int adicionarPessoa(Scanner scanner, Pessoa[] listaPessoas) {
		
		System.out.println("-=-=-=-=-=-");
		System.out.println("Nome:");
		String nome = scanner.next();
		
		
		System.out.println("CPF:");
		String cpf = scanner.next();
		
		System.out.println("Piloto? (0 = sim | 1 = não)");
		int opcPiloto = scanner.nextInt();
		if(opcPiloto == 0) {
			adicionar(listaPessoas, new Piloto(nome, cpf));
		}
		else {
			adicionar(listaPessoas, new Pessoa(nome, cpf));
		}
		
		return CONTINUAR;
	}
	
	public static int aumentarLista(Scanner scanner, AtomicReference<Pessoa[]> reference) {
		
		System.out.println("-=-=-=-=-=-");
		System.out.println("Aumentar mais:");
		int addTamanho = scanner.nextInt();
		if(addTamanho < 0) {
			System.out.println("Valores negativos não podem ser adicionados.");
			return askContinuar(scanner);
		}
		
		reference.set(incrementar(reference.get(), addTamanho));
		System.out.println("Agora sua lista de pessoas tem " + reference.get().length + " de tamanho.");
		System.out.println("-=-=-=-=-=-");

		return askContinuar(scanner);
	}

	public static int askContinuar(Scanner scanner) {
		System.out.println("Deseja continuar? (0 = sim | 1 = não)");
		if(scanner.nextInt() == 0) {
			return CONTINUAR;
		}
		else {
			return TERMINAR;
		}
	}
	
	public static int listarPessoas(Scanner scanner, Pessoa[] listaPessoas) {
		System.out.println("Pessoas:");
		for(int i = 0; i < listaPessoas.length; i++) {
			if(listaPessoas[i] != null) {
				System.out.println(listaPessoas[i].toString());
			}
		}
		return askContinuar(scanner);
	}
	
	public static void adicionar(Pessoa[] listaPessoas, Pessoa pessoa) {
		int index = next(listaPessoas);
		if(index == -1) {
			System.out.println("Não há mais espaço.");
			return;
		}
		listaPessoas[index] = pessoa;
	}
	
	public static int next(Pessoa[] listaPessoas) {
		for(int i = 0; i < listaPessoas.length; i++) {
			if(listaPessoas[i] == null) {
				return i;
			}
		}
		return -1;
	}
	
	public static Pessoa[] incrementar(Pessoa[] lista, int tamanho) {
		Pessoa[] novaLista = new Pessoa[lista.length + tamanho];
		for(int i = 0; i < lista.length; i++) {
			novaLista[i] = lista[i];
		}
		return novaLista;
	}
	
	public static boolean validarOpçao(int op) {
		return (op == ADICIONAR_PASSAGEIRO || op == LISTAR_PILOTOS 
				|| op == LOCALIZAR_PILOTO || op == AUMENTAR_LISTA);
	}
	
}
