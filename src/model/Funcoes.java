package model;

import java.util.Scanner;

/**
 * Classe de Fun��es para ajudar na contru��o do cod�go.
 * 
 * @author Nalbert Santos Araujo
 * @author Pedro Henrique
 *
 */
public class Funcoes {

	static final Scanner entrada = new Scanner(System.in);
	
	/**Metodo para mostrar um texto para o usuario e salvar a sua resposta em string**/
	public final static String entradaString(String texto, boolean verificacao) {
		boolean condicao = true;
		String entradaUsuario = "";
		while (condicao) {
			System.out.println(texto);

			entradaUsuario = entrada.nextLine().strip();
			if (verificacao == true) {
				if (entradaUsuario.matches("[a-zA-Z�������������������������������\s]+")) {
					entradaUsuario = Funcoes.captilizeString(entradaUsuario).strip();
					condicao = false;
				} else {
					System.out.println("Erro! N�o digite n�meros ou caracteres.");
				}
			}
			else {
				condicao = false;
			}
			
		}
		System.out.println();
		return entradaUsuario;
	}
	
	/**Metodo para mostrar um texto para o usuario e salvar a sua resposta em int, aceitando apenas inteiros**/
	public final static int entradaInt(String texto) {
		int entradaUsuario = 0;
		boolean condicao = true;
		while (condicao) {
			try {
				System.out.print(texto);
				entradaUsuario = Integer.parseInt(entrada.nextLine());
				condicao = false;

			} catch (java.lang.NumberFormatException e) {
				System.out.println("\nS� digite numeros!\n");
			}

		}

		return entradaUsuario;
	}
	
	/**Metodo para mostrar um texto para o usuario e salvar a sua resposta em int, aceitando apenas inteiros em um certo intervalo**/
	public final static int entradaIntRanger(String texto, int inicio, int fim) {
		int entradaUsuario = 0;
		boolean condicao = true;
		while (condicao) {
			try {
				System.out.print(texto);
				entradaUsuario = Integer.parseInt(entrada.nextLine());
				if (inicio <= entradaUsuario && entradaUsuario <= fim) {
					condicao = false;
					;
				} else {
					System.out.println(
							"O n�mero est� fora do ranger[" + inicio + "-" + fim + "]" + " ! Digite novamente.");
				}
			} catch (java.lang.NumberFormatException e) {
				System.out.println("\nS� digite numeros!\n");
			}
		}

		return entradaUsuario;

	}
	
	/**Metodo para mostrar um texto para o usuario e salvar a sua resposta em int, aceitando apenas inteiros em um certo intervalo inicial**/
	public final static int entradaIntRanger(String texto, int inicio) {
		int entradaUsuario = 0;
		boolean condicao = true;
		while (condicao) {
			try {
				System.out.print(texto);
				entradaUsuario = Integer.parseInt(entrada.nextLine());
				if (inicio <= entradaUsuario) {
					condicao = false;
					;
				} else {
					System.out.println(
							"O n�mero est� fora do ranger[" + inicio + " at� infinito]" + " ! Digite novamente.");
				}
			} catch (java.lang.NumberFormatException e) {
				System.out.println("\nS� digite numeros!\n");
			}
		}

		return entradaUsuario;

	}

	/**Metodo para mostrar as op��es de jogador, tecnico, sele��o e arbitro para o usuario**/
	public final static void mostrarOpcoes() {

		System.out.println("[1] Sele��o.\n" + "[2] Arbitro.\n" + "[3] Tecnico.\n" + "[4] Jogador.\n" + "[5] Voltar.\n");
	}

	/**Metodo para fazer com que todas as letras inicias comecem com letra maiuscula**/
	public final static String captilizeString(String texto) {
		String[] c = texto.split(" ");
		String n = "";
		for (String p : c) {
			n += p.substring(0, 1).toUpperCase() + p.substring(1).toLowerCase() + " ";
		}
		return n.strip();
	}
	
	/**Metodo para fechar o scanner**/
	public static void fechar() {
		entrada.close();
	}

}
