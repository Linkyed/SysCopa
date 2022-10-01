package application;

//import javafx.application.Application;
//import javafx.stage.Stage;
import model.*;
//import javafx.scene.Scene;
//import javafx.scene.layout.BorderPane;

public class Main {
	/*
	 * public void start(Stage primaryStage) { try { BorderPane root = new
	 * BorderPane(); Scene scene = new Scene(root,400,400);
	 * scene.getStylesheets().add(getClass().getResource("application.css").
	 * toExternalForm()); primaryStage.setScene(scene); primaryStage.show(); }
	 * catch(Exception e) { e.printStackTrace(); } }
	 */

	public static void main(String[] args) {
		// Variaveis para desiginar a escolha do usuario perante as a��es que o progama
		// pode fazer
		int escolha = 0;
		int escolha_inserir = 0;
		int escolha_excluir = 0;
		int escolha_editar = 0;
		int escolha_listar = 0;

		// Loop principal onde todas as funcionalidades foram implementadas
		while (escolha != 5) {
			// Print para mostrar o usuario as op��es que ele pode escolher
			System.out.println("[1] Inserir um jogador, tecnico, arbitro ou sele��o.\n"
					+ "[2] Editar um jogador, tecnico, arbitro ou sele��o.\n"
					+ "[3] Excluir um jogador, tecnico, arbitro ou sele��o.\n"
					+ "[4] Listar os jogadores, tecnicos, arbitros ou sele��es.\n" + "[5] Sair do programa.\n");

			// Coletando a escolha do usuario atraves de uma fun��o criada
			escolha = Funcoes.entradaInt("Digite o numero relacionado a uma op��o acima:");

			// MENU GERAL
			switch (escolha) {
			// INSER��O de todos os objetos
			case 1:
				// Fun��o para mostrar as op��es que o usuario pode escolher e a fun��o que
				// coleta a escolha do usuario
				Funcoes.mostrarOpcoes();

				escolha_inserir = Funcoes.entradaIntRanger("Digite o numero relacionado a uma op��o acima:", 1, 5);

				// Inser��o dividida entre sele��o, aribtro, tecnico e jogador
				switch (escolha_inserir) {
				// INSER��O SELE��O
				case 1:
					// Apenas cria a sele��o com o nome que o usuario escolher
					SelecaoDAO.inserir(new Selecao(Funcoes.entradaString("Digite o nome da Sele��o: ")));

					break;

				// INSER��O ARBITRO
				case 2:
					// Apenas cria o arbitro com o nome que o usuario escolher
					ArbitroDAO.inserir(new Arbitro(Funcoes.entradaString("Digite o nome do arbitro: ")));
					break;
				// INSER��O TECNICO
				case 3:
					// So aceita a cria��o de um arbitro se existir uma sele��o para ele poder fazer
					// parte
					if (SelecaoDAO.selecoesSemTecnico() > 0) {
						SelecaoDAO.imprimirSelecaoSemTecnico();
						int numSelecao = Funcoes.entradaInt("Digite o numero da sele��o que o tecnico faz parte: ");
						String nomeTecnico = Funcoes.entradaString("Digite o nome do Tecnico: ");

						// Caso o usuario escolha um numero que n�o refere a nenhuma sele��o ele n�o ira
						// criar o tecnico
						if (SelecaoDAO.getOneSelecao(numSelecao) == null) {
							break;
						} else {

							TecnicoDAO.inserir(new Tecnico(nomeTecnico, SelecaoDAO.getOneSelecao(numSelecao)));
							System.out.println();
						}
					} else {
						System.out.println(
								"\nAntes de criar um tecnico, � preciso criar uma sele��o ou ter uma sele��o sem tecnico alocado!\n");
					}
					break;
				case 4:

					if (SelecaoDAO.quantidadeSelecoes() > 0) {
						SelecaoDAO.imprimirSelecao();
						int numSelecao = Funcoes.entradaIntRanger(
								"Digite o numero da sele��o que o jogador faz parte: ", 0,
								SelecaoDAO.quantidadeSelecoes() - 1);
						String nomeJogador = Funcoes.entradaString("Digite o nome do Jogador: ");
						int quant_Cart_Amarelo = Funcoes.entradaIntRanger("Digite a quantidade de Cart�es Amarelos: ",
								0);
						int quant_Cart_Vermelho = Funcoes.entradaIntRanger("Digite a quantidade de Cart�es Vermelhos: ",
								0);
						int gol_marcado = Funcoes
								.entradaIntRanger("Digite a quantidade de Gols marcados pelo jogador: ", 0);
						Selecao modelo_Selecao = SelecaoDAO.getOneSelecao(numSelecao);
						Jogador modelo_Jogador = new Jogador(nomeJogador, modelo_Selecao, quant_Cart_Vermelho,
								quant_Cart_Amarelo, gol_marcado);
						Boolean respostaBoolean = JogadorDAO.inserir(modelo_Jogador, modelo_Selecao);
						if (respostaBoolean) {
							System.out.println("\nJogador adicionado com Sucesso!\n");
						} else {
							System.out.println("\nErro! n�o � possivel adicionar mais de 26 jogadores na sele��o.\n");
						}

					} else {
						System.out.println("\nAntes de criar um Jogador, � preciso criar uma sele��o!\n");
					}
					break;
				}

				break;

			// EDI��O
			case 2:
				Funcoes.mostrarOpcoes();
				escolha_editar = Funcoes.entradaIntRanger("Digite o numero relacionado a uma op��o acima:", 1, 5);
				;

				switch (escolha_editar) {
				// EDI��O SELE��O onde pode ser mudado o nome, o tecnico (se pa lista de
				// jogadores)
				case 1:
					SelecaoDAO.listar();
					int numSelecao = Funcoes.entradaInt("Digite o numero da Sele��o a ser editada: ");
					System.out.println("\n[0] Editar o nome da sele��o\n[1] Editar o tecnico da sele��o");
					// Parte onde o usuario vai decidir o que deve ser mudado na sele��o
					int opcaoEditar = Funcoes
							.entradaInt("\n[0] Editar o nome da sele��o\n[1] Editar o tecnico da sele��o\n"
									+ "Digite o numero relacionado a uma op��o acima:");

					// Primeira op��o onde s� edita o nome da sele��o
					if (opcaoEditar == 0) {

						String novoNome = Funcoes.entradaString("Digite o novo nome da sele��o: ");
						if (SelecaoDAO.editar(SelecaoDAO.getOneSelecao(numSelecao), novoNome) == true) {
							System.out.println("\nA opera��o foi um sucesso!\n");
						} else {
							System.out.println("\nA opera��o foi uma falha!\n");
						}
						// Segunda op��o onde s� edita o tecnico da sele��o
					} else if (opcaoEditar == 1) {
						// Teste de erro para caso a sele��o n�o possua um tecnico
						try {
							String novoNomeTecnico = Funcoes.entradaString("Digite o novo nome do tecnico: ");
							if (TecnicoDAO.editar(
									TecnicoDAO.getTecnicoIndes(SelecaoDAO.getOneSelecao(numSelecao).getTecnico()),
									novoNomeTecnico) == true) {
								System.out.println("\nA opera��o foi um sucesso!\n");
							} else {
								System.out.println("\nA opera��o foi uma falha!\n");
							}
						} catch (Exception e) {
							System.out.println("\nSele��o ainda n�o possui tecnico ou n�o existe\n");
						}

					} else {
						System.out.println("\nOp��o digitada invalida!\n");
					}
					break;

				// EDI��O ARBITRO onde s� pode ter seu nome mudado
				case 2:
					ArbitroDAO.listar();
					int numArbitro = Funcoes.entradaInt("Digite o numero do arbitro a ser editado: ");
					String nomeArbitro = Funcoes.entradaString("Digite o novo nome do arbitro: ");
					// Caso receba um novo nome invalido, o nome n�o ser� alterado
					if (ArbitroDAO.editar(ArbitroDAO.getOneArbitro(numArbitro), nomeArbitro) == true) {
						System.out.println("\nA opera��o foi um sucesso!\n");
					} else {
						System.out.println("\nA opera��o foi uma falha!\n");
					}
					
					break;
				// EDI��O TECNICO onde poder� mudar seu nome sua sele��o
				case 3:
					TecnicoDAO.listar();
					int numTecnico = Funcoes.entradaInt("Digite o numero do tecnico a ser editado: ");
					System.out.println("\n[0] Editar o nome\n[1] Editar a sele��o\n");
					// Parte onde o usuario ira decidir o que mudar no tecnico
					int escolhaEdicaoTecnico = Funcoes.entradaInt(
							"\n[0] Editar o nome\n[1] Editar a sele��o\n" + "Digite o que deseja editar no tecnico: ");
					// Primeira op��o so mudando o nome do tecnico
					if (escolhaEdicaoTecnico == 0) {
						String nomeTecnico = Funcoes.entradaString("Digite o novo nome do tecnico: ");
						if (TecnicoDAO.editar(TecnicoDAO.getOneTecnico(numTecnico), nomeTecnico) == true) {
							System.out.println("\nA opera��o foi um sucesso!\n");
						} else {
							System.out.println("\nA opera��o foi uma falha!\n");
						}
						// Segunda op��o onde muda a sele��o, porem � necessario a existencia de uma
						// sele��o que ainda n�o possua tecnico
					} else if (escolhaEdicaoTecnico == 1) {
						// Caso n�o exista sele��es sem tecnico, a edi��o n�o pode ser concluida
						if (SelecaoDAO.selecoesSemTecnico() == 0) {
							System.out.println(
									"Todas sele��es j� possuem tecnicos, se quiser editar a sele��o de un tecnico � preciso exister uma sele��o\n"
											+ "sem tecnico");
						} else {
							int numNovaSelecao = Funcoes.entradaInt("Digite o numero da sele��o nova do tecnico: ");
							try {

								SelecaoDAO.getOneSelecao(numNovaSelecao)
										.setTecnico(TecnicoDAO.getOneTecnico(numTecnico));
								TecnicoDAO.getOneTecnico(numTecnico).getSelecao().setTecnico(null);
								TecnicoDAO.getOneTecnico(numTecnico)
										.setSelecao(SelecaoDAO.getOneSelecao(numNovaSelecao));
							} catch (Exception e) {
								System.out.println("Tente novamente!\n");
							}

						}
					} else {
						System.out.println("\nOp��o digitada invalida!\n");
					}
				case 4:
					JogadorDAO.listar();
					
					break;
				}
				break;

			// EXCLUS�O
			case 3:
				Funcoes.mostrarOpcoes();
				escolha_excluir = Funcoes.entradaIntRanger("Digite o numero relacionado a uma op��o acima:", 1, 5);
				;
				switch (escolha_excluir) {
				// EXCLUS�O SELE��O
				case 1:
					// Mostra ao usuario as sele��es existente e apos isso recebe o numero digitado
					// pelo mesmo, e s� ira excluir caso o numero digitado se
					// refira a uma sele��o, caso n�o nenhuma sele��o ser� excluida
					SelecaoDAO.listar();
					if (SelecaoDAO.excluir(Funcoes.entradaInt("Digite o numero da Sele��o a ser excluida: ")) == true) {
						System.out.println("\nA opera��o foi um sucesso!\n");
					} else {
						System.out.println("\nA opera��o foi uma falha!\n");
					}
					break;

				// EXCLUS�O ARBITRO
				case 2:
					ArbitroDAO.listar();
					if (ArbitroDAO.excluir(Funcoes.entradaInt("Digite o numero do arbitro a ser excluido: ")) == true) {
						System.out.println("\nA opera��o foi um sucesso!\n");
					} else {
						System.out.println("\nA opera��o foi uma falha!\n");
					}
					;
					break;
				// EXCLUS�O TECNICO
				case 3:
					TecnicoDAO.listar();
					if (TecnicoDAO.excluir(Funcoes.entradaInt("Digite o numero do tecnico a ser exlcuido: ")) == true) {
						System.out.println("\nA opera��o foi um sucesso!\n");
					} else {
						System.out.println("\nA opera��o foi uma falha!\n");
					}
					
					break;
				// EXCLUS�O JOGADOR
				case 4:
					JogadorDAO.listar();
					if (JogadorDAO.excluir(Funcoes.entradaInt("Digite o numero do Jogador a ser exlcuido: ")) == true) {
						System.out.println("\nA opera��o foi um sucesso!\n");
					} else {
						System.out.println("\nA opera��o foi uma falha!\n");
					}
					break;
				}
				break;

			// LISTAR
			case 4:
				Funcoes.mostrarOpcoes();
				escolha_listar = Funcoes.entradaIntRanger("Digite o numero relacionado a uma op��o acima:", 1, 5);
				switch (escolha_listar) {
				// LISTAR SELE��O
				case 1:
					SelecaoDAO.listar();
					break;
				// LISTAR ARBITRO
				case 2:
					ArbitroDAO.listar();
					break;
				// LISTAR TECNICO
				case 3:
					TecnicoDAO.listar();
					break;
				// LISTAR JOGADOR
				case 4:
					JogadorDAO.listar();
					break;
				}
				break;

			// SAIDA
			case 5:
				System.out.println("\nSaindo do programa!");
				break;
			default:
				System.out.println("\nLembre-se de digitar apenas os numeros relacionados a op��es.\n");

			}
		}

	}
}
