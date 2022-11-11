package application;

import model.Funcoes;
import model.GrupoPrimeiraFase;
import model.Partida;
import model.PartidaDAO;
import model.Pesquisa;
import model.SelecaoDAO;

public class MenuFaseGrupo {

	private static void definirPartida(Partida partida) {
		if (PartidaDAO.statusAtuaisPartidas(partida) == false) {
			PartidaDAO.alteracaoDeStatusPartida(true, partida);
			System.out.println("\n| " + partida + " |\n");
			MainPartida.editarLugar(partida);
			System.out.println();
			MainPartida.editarData(partida);
			System.out.println();
			MainPartida.editarHoraMinuto(partida);
			System.out.println();
			MainPartida.editarGols(partida);
			System.out.println();
			MainPartida.editarCartaoAmarelo(partida);
			System.out.println();
			MainPartida.editarCartaoVermelho(partida);
			System.out.println();
			MainPartida.editarArbitros(partida);
			System.out.println();
			System.out.println("Partida realizada com Sucesso!");
		} else {
			System.out.println("Erro! A partida j� foi feita.\n");
		}
	}

	private static void editarGols(Partida partida) {
		System.out.println("\n| " + partida + " |\n");
		GrupoPrimeiraFase.definirPontos(partida, false);
		MainPartida.editarGols(partida);
		System.out.println();
	}

	private static void editarCartaoAm(Partida partida) {
		System.out.println("\n| " + partida + " |\n");
		MainPartida.editarCartaoAmarelo(partida);
		System.out.println();
	}

	private static void editarCartVer(Partida partida) {
		System.out.println("\n| " + partida + " |\n");
		MainPartida.editarCartaoVermelho(partida);
		System.out.println();
	}

	private static void editarArbi(Partida partida) {
		System.out.println("\n| " + partida + " |\n");
		MainPartida.editarArbitros(partida);
		System.out.println();
	}

	private static void editarHorarios(Partida partida) {
		System.out.println("\n| " + partida + " |\n");
		MainPartida.editarHoraMinuto(partida);
		System.out.println();
	}

	private static void editarData(Partida partida) {
		System.out.println("\n| " + partida + " |\n");
		MainPartida.editarData(partida);
		System.out.println();
	}

	private static void editarLugar(Partida partida) {
		System.out.println("\n| " + partida + " |\n");
		MainPartida.editarLugar(partida);
		System.out.println();
	}

	private static void sistemaDePesquisa() {
		int escolha = Funcoes.entradaIntRanger(
				"[1] Pesquisar por uma sele��o\n[2] Pesquisar por uma pessoa\n[3] Pesquisar por uma partida\n"
				+ "Digite o numero relacionado uma forma de pesquisa: ",
				1, 3);
		if (escolha == 1) {
			System.out.println(Pesquisa.buscarSelecao());
		} else if (escolha == 2) {
			System.out.println(Pesquisa.buscarPessoa());
		} else if (escolha == 3) {
			System.out.println(Pesquisa.buscarPartida());
		}
	}
	
	public static void MenuPrincipal() {
		System.out.println("\n=-=-=-=-=--| Bem Vindo a Copa do mundo |=-=-=-=-=--\n");
		MainPartida.criarPrimeiraFase();
		while (PartidaDAO.quantidadePartidasNaoRealizada() != 0) {
			int escolha = Funcoes.entradaIntRanger("Digite:\n[1]- Para Cadastrar dados de uma partida\n"
					+ "[2]- Para exibir a pontua��o de um grupo\n" + "[3]- Para exibir todos os grupos\n"
					+ "[4]- Para exibir os dados dos jogadores de uma sele��o\n"
					+ "[5]- Menu de edi��es de partidas realizadas\n[6]- Excluir os dados de uma partida\n[7]- Pesquisar Partida\n"
					+ "[8] Sair do Programa\nEscolha: ",
					1, 7);
			if (escolha == 1) {
				Partida partida = PartidaDAO.partidaSemRealizar();
				if (!partida.equals(null)) {
					definirPartida(partida);
				} else {
					System.out.println("Todas as partidas j� foram realizadas.\n");
				}
			} else if (escolha == 2) {
				String letraGrupo = Funcoes.entradaLetraGrupo("Digite a Letra do grupo que a sele��o pertence [A-H]: ",
						true);
				GrupoPrimeiraFase.listaGrupoString(letraGrupo);
				;
			} else if (escolha == 3) {
				GrupoPrimeiraFase.listarTodosGrupos();
			} else if (escolha == 4) {
				SelecaoDAO.listar();
				int numSelecao = Funcoes.entradaIntRanger("Digite o n�mero da sele��o: ", 0,
						SelecaoDAO.quantidadeSelecoes() - 1);
				SelecaoDAO.imprimirCaracteristicas(numSelecao);
			} else if (escolha == 5) {
				if (PartidaDAO.quantidadePartidasRealizada() > 0) {
					GrupoPrimeiraFase.listarTodosGrupos();
					String letraGrupo = Funcoes
							.entradaLetraGrupo("Digite a Letra do grupo que a sele��o pertence [A-H]: ", true);
					PartidaDAO.listarGrupo(letraGrupo);
					int numPartida = Funcoes.entradaIntRanger("Ditige o n�mero da partida para edita-la [1-6]: ", 1, 6);
					Partida modeloPartida = PartidaDAO.procurarPartida(letraGrupo, numPartida);
					if (modeloPartida.getStatus() == true) {
						int escolhaEditar = Funcoes.entradaIntRanger("Digite:\n[1]- Para editar o local\n"
								+ "[2]- Para editar o horario\n" + "[3]- Para editar a data\n"
								+ "[4]- Para editar os gols da partida\n"
								+ "[5]- Para editar os cart�es Amarelo\n[6]- Para editar os cart�es Vermelho\n[7]- Para editar os Arbitros\nEscolha: ",
								1, 7);
						if (escolhaEditar == 1)
							editarLugar(modeloPartida);
						else if (escolhaEditar == 2)
							editarHorarios(modeloPartida);
						else if (escolhaEditar == 3)
							editarData(modeloPartida);
						else if (escolhaEditar == 4)
							editarGols(modeloPartida);
						else if (escolhaEditar == 5)
							editarCartaoAm(modeloPartida);
						else if (escolhaEditar == 6)
							editarCartVer(modeloPartida);
						else if (escolhaEditar == 7)
							editarArbi(modeloPartida);
						else
							System.out.println("N�mero fora do range!");
					} else {
						System.out.println("Erro! Essa partida n�o foi Realizada ainda.");
					}

				} else {
					System.out.println("Erro!Nenhuma partida foi realizada.\nRealize uma partida para poder edita-la.");
				}

			} else if (escolha == 6) {
				MainPartida.excluirPartida();
			} else if (escolha == 7) {
				sistemaDePesquisa();
			} else if (escolha == 8){
				System.out.println("Fim do Programa!");
				break;
			} else {
				System.out.println("N�mero fora do range!");				
			}
		}

	}

}
