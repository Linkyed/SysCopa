package model;

import java.util.Scanner;

public class Funcoes {
	public final static String entradaString () {
		String entradaUsuario = "";
		Scanner entrada = new Scanner(System.in);
		entradaUsuario = entrada.nextLine();
		return entradaUsuario;
	}
	
	public final static int entradaInt() {
		int entradaUsuario = 0;
		Scanner entrada = new Scanner(System.in);
		System.out.print("Digite o numero relacionado a uma op��o acima:");
		try {
			entradaUsuario = Integer.parseInt(entrada.nextLine());
		}
		catch (java.lang.NumberFormatException e) {
			System.out.println("\nS� digite numeros que sejam relacionados as op��es!\n");
		}
		return entradaUsuario;
	}
	public final static void mostrarOpcoes() {
		int escolhaSecundaria = 0;
		System.out.println("[1] Sele��o.\n"
				+ "[2] Arbitro.\n"
				+ "[3] Tecnico.\n"
				+ "[4] Jogador.\n");
		escolhaSecundaria = entradaInt();
		// Passivel de troca possivelmente em cada funcionlaidade
		switch (escolhaSecundaria) {
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			default:
				System.out.println("\nLembre-se de digitar apenas os numeros relacionados a op��es.\n");
		}
	}
	public final static String captilizeString(String texto) {
		String[] c = texto.split(" ");
		String n = "";
		for (String p: c) {
			n += p.substring(0, 1).toUpperCase() + p.substring(1).toLowerCase() + " ";
		}
		return n.strip();
	}
	
}
