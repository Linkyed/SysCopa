package application;

//import javafx.application.Application;
//import javafx.stage.Stage;
import model.*;
//import javafx.scene.Scene;
//import javafx.scene.layout.BorderPane;


public class Main{
	/*public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}*/

	public static void main(String[] args) {
		int escolha = 0;
		int escolha_inserir = 0;
		int escolha_excluir = 0;
		int escolha_editar = 0;
		int escolha_listar = 0;
		
		while (escolha != 5) {
			System.out.println("[1] Inserir um jogador, tecnico, arbitro ou sele��o.\n"
					+ "[2] Editar um jogador, tecnico, arbitro ou sele��o.\n"
					+ "[3] Excluir um jogador, tecnico, arbitro ou sele��o.\n"
					+ "[4] Listar os jogadores, tecnicos, arbitros ou sele��es.\n"
					+ "[5] Sair do programa.\n");
			
			escolha = Funcoes.entradaInt();
			
			//MENU GERAL
			switch (escolha) {
				//INSER��O
				case 1:
					Funcoes.mostrarOpcoes();
					escolha_inserir = Funcoes.entradaInt();
					
					switch (escolha_inserir) {
						//INSER��O SELE��O
						case 1:
							System.out.print("Digite o nome da Sele��o: ");
							SelecaoDAO.inserir(new Selecao(Funcoes.entradaString()));
							break;
					
						//INSER��O ARBITRO
						case 2:
							System.out.print("Digite o nome do arbitro: ");
							ArbitroDAO.inserir(new Arbitro(Funcoes.entradaString()));
							break;
					}
					break;
				
				//EDI��O
				case 2:
					Funcoes.mostrarOpcoes();
					escolha_editar = Funcoes.entradaInt();
					
					
					
					switch (escolha_editar) {
						//EDI��O SELE��O
						case 1:
							SelecaoDAO.listar();
							System.out.print("Digite o numero da Sele��o a ser editada: ");
							int numSelecao = Funcoes.entradaInt();
							System.out.println("\n[0] Editar o nome da sele��o\n[1] Editar o tecnico da sele��o");
							int opcaoEditar = Funcoes.entradaInt();
							if (opcaoEditar == 0) {
								System.out.println("Digite o novo nome da sele��o: ");
								String novoNome = Funcoes.entradaString();
								if (SelecaoDAO.editar(SelecaoDAO.getOneSelecao(numSelecao), novoNome) == true) {
									System.out.println("\nA opera��o foi um sucesso!\n");
								} else {
									System.out.println("\nA opera��o foi uma falha!\n");
								}
							} else if (opcaoEditar == 1) {
								if (SelecaoDAO.getOneSelecao(numSelecao).getTecnico() == null) {
									System.out.println("\nSele��o ainda n�o possui tecnico para ser editado!\n");
								} else {
									System.out.println("Digite o novo nome do tecnico: ");
									String novoNomeTecnico = Funcoes.entradaString();
									if (TecnicoDAO.editar(TecnicoDAO.getTecnicoIndes(SelecaoDAO.getOneSelecao(numSelecao).getTecnico()), novoNomeTecnico) == true) {
										System.out.println("\nA opera��o foi um sucesso!\n");
									}else {
										System.out.println("\nA opera��o foi uma falha!\n");
									}
								}
							}
							break;
						//EDI��O ARBITRO
						case 2:
							ArbitroDAO.listar();
							System.out.println("Lembre-se de digitar o numero do arbitro a ser editado!");
							int numArbitro = Funcoes.entradaInt();
							System.out.print("Digite o novo nome do arbitro: ");
							String nomeArbitro = Funcoes.entradaString();
							if (ArbitroDAO.editar(ArbitroDAO.getOneArbitro(numArbitro), nomeArbitro) == true) {
								System.out.println("\nA opera��o foi um sucesso!\n");
							}else{
								System.out.println("\nA opera��o foi uma falha!\n");
							};
					}
					break;
				
				//EXCLUS�O
				case 3:
					Funcoes.mostrarOpcoes();
					escolha_excluir = Funcoes.entradaInt();
					switch (escolha_excluir) {
					//EXCLUS�O SELE��O
					case 1:
						SelecaoDAO.listar();
						System.out.print("Digite o numero da Sele��o a ser excluida: ");
						if (SelecaoDAO.excluir(Funcoes.entradaInt()) == true) {
							System.out.println("\nA opera��o foi um sucesso!\n");
						}else{
							System.out.println("\nA opera��o foi uma falha!\n");
						}
						break;
					
					//EXCLUS�O ARBITRO
					case 2:
						ArbitroDAO.listar();
						System.out.print("Digite o numero do arbitro a ser excluido: ");
						if (ArbitroDAO.excluir(Funcoes.entradaInt()) == true) {
							System.out.println("\nA opera��o foi um sucesso!\n");
						}else{
							System.out.println("\nA opera��o foi uma falha!\n");
						};
						break;
				}
					break;
				
				//LISTAR
				case 4:
					Funcoes.mostrarOpcoes();
					escolha_listar = Funcoes.entradaInt();
					switch (escolha_listar) {
						//LISTAR SELE��O
						case 1:
							SelecaoDAO.listar();
							break;
						//LISTAR ARBITRO
						case 2:
							ArbitroDAO.listar();
							break;
						//LISTAR TECNICO
						case 3:
							TecnicoDAO.listar();
							break;
						//LISTAR JOGADOR
						case 4:
							JogadorDAO.listar();
							break;
						}
					break;
				
				//SAIDA	
				case 5:
					System.out.println("\nSaindo do programa!");
					break;
				default:
					System.out.println("\nLembre-se de digitar apenas os numeros relacionados a op��es.\n");

			}
		}
				
	}
}
