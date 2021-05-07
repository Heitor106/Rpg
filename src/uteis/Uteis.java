package uteis;

import java.util.Scanner;

public class Uteis {

	private Uteis() {

	}

	private static Scanner scan = new Scanner(System.in);

	public static int escaneador(int max) {

		int action = scan.nextInt();
		while (action < 1 || action > max) {
			System.out.println("Ação invalida:(");
			System.out.println("Escolha outra Ação!");
			action = scan.nextInt();
		}
		return action;
	}

}
