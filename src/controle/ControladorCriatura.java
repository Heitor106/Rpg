package controle;

import java.util.Scanner;

import contantes.ClassConstantes;
import contantes.RacaConstantes;
import model.Criatura;
import uteis.Uteis;

public class ControladorCriatura {

	private Scanner scan = new Scanner(System.in);
	private String[] clases = ClassConstantes.CLASES;
	private String[] racas = RacaConstantes.RACAS;

	public Criatura nacimento(String nome, String raca, String classe, boolean amigo) {

		Criatura padrao = new Criatura(nome, raca, classe, amigo);

		return padrao;
	}

	public Criatura novaCriatura() {

		System.out.println("Me diga seu Nome\n");

		String nome = scan.nextLine();
		int escolhido;
		String clase;
		String raca;
		boolean amigo;
		
		System.out.println("Me diga sua Classe\n");

		for (int i = 0; i < clases.length; i++) {

	System.out.println(i + 1 +" "+ clases[i]);

		}

		escolhido = Uteis.escaneador(clases.length);

		escolhido--;

		clase = clases[escolhido];
		
		System.out.println("Me diga sua Raca\n");

		for (int i = 0; i < racas.length; i++) {

			System.out.println(i + 1+" "+ racas[i]);

		}


		escolhido = Uteis.escaneador(racas.length);

		escolhido--;

		raca = racas[escolhido];
		
		System.out.println("Me diga seu Time\n1 Pais\n2 Filhos");
		
		escolhido = Uteis.escaneador(2);
		
		if(escolhido==1) {
			
			amigo=true;
		} else if(escolhido==2) {
			
			amigo=false;
		} else {
			
			amigo=false;
		}

		return nacimento(nome, raca, clase, amigo);
	}
}
