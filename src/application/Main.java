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
					//EDI��O ARBITRO
					switch (escolha_editar) {
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
