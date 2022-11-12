package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Arbitro;
import model.ArbitroDAO;
import model.Funcoes;
import model.GrupoPrimeiraFase;
import model.Jogador;
import model.Partida;
import model.PartidaDAO;

/** 
 *  Fun��o complementar de partida
 *  **/
public class MainPartida {

	/** 
	 *  Fun��o que inicializa o processo da forma��o das partidas do grupo.
	 *  **/
	public static void criarPrimeiraFase() {
		GrupoPrimeiraFase.organizadorTodasPartidas();
		GrupoPrimeiraFase.listarTodosGrupos();

	}

	/** 
	 *  Fun��o para fazer a coleta de dados para a edi��o de gols
	 *  **/
	public static void editarGols(Partida partida) {
		PartidaDAO.alteracaoDeStatusPartida(true, partida);
		Map<Jogador, Integer> jogadorGolsMap = new HashMap<>();
		int golSelecao1 = Funcoes
				.entradaIntRanger("Digite o n�mero de gols da Sele��o |" + partida.getSelecao1() + "| : ", 0);
		if (golSelecao1 > 0) {
			int cont = 1;
			for (Jogador jogador : partida.getSelecao1().getJogadores()) {
				System.out.println("[" + cont + "]- " + jogador);
				cont++;
			}
		}
		for (int i = 0; i < golSelecao1; i++) {
			int numJogador = Funcoes.entradaIntRanger("Ditige o n�mero do jogador que marcou o " + (i + 1) + "� gol: ",
					1, 11);
			Jogador modeloJogador = partida.getSelecao1().getJogadores().get(numJogador - 1);
			if (jogadorGolsMap.containsKey(modeloJogador)) {
				int golJogador = jogadorGolsMap.get(modeloJogador);
				jogadorGolsMap.put(modeloJogador, golJogador + 1);
			} else {
				jogadorGolsMap.put(modeloJogador, 1);
			}
		}
		PartidaDAO.editarGol(partida, jogadorGolsMap, 1);

		Map<Jogador, Integer> jogadorGolsMap2 = new HashMap<>();
		int golSelecao2 = Funcoes
				.entradaIntRanger("Digite o n�mero de gols da Sele��o |" + partida.getSelecao2() + "| : ", 0);
		if (golSelecao2 > 0) {
			int cont2 = 1;
			for (Jogador jogador : partida.getSelecao2().getJogadores()) {
				System.out.println("[" + cont2 + "]- " + jogador);
				cont2++;
			}
		}
		for (int i = 0; i < golSelecao2; i++) {
			int numJogador = Funcoes.entradaIntRanger("Ditige o n�mero do jogador que marcou o " + (i + 1) + "� gol: ",
					1, 11);
			Jogador modeloJogador = partida.getSelecao2().getJogadores().get(numJogador - 1);
			if (jogadorGolsMap2.containsKey(modeloJogador)) {
				int golJogador = jogadorGolsMap2.get(modeloJogador);
				jogadorGolsMap2.put(modeloJogador, golJogador + 1);
			} else {
				jogadorGolsMap2.put(modeloJogador, 1);
			}
		}
		PartidaDAO.editarGol(partida, jogadorGolsMap2, 2);

		GrupoPrimeiraFase.definirPontos(partida, true);

	}

	/** 
	 *  Fun��o para fazer a coleta de dados para a edi��o de cart�es Amarelo
	 *  **/
	public static void editarCartaoAmarelo(Partida partida) {
		PartidaDAO.alteracaoDeStatusPartida(true, partida);
		Map<Jogador, Integer> jogadorCartaoAmarelo1 = new HashMap<>();
		int cartAmareloSelecao1 = Funcoes.entradaIntRanger(
				"Digite o n�mero de Cart�es Amarelo da Sele��o |" + partida.getSelecao1() + "| : ", 0);
		if (cartAmareloSelecao1 > 0) {
			int cont = 1;
			for (Jogador jogador : partida.getSelecao1().getJogadores()) {
				System.out.println("[" + cont + "]- " + jogador);
				cont++;
			}
		}
		for (int i = 0; i < cartAmareloSelecao1; i++) {
			int numJogador = Funcoes.entradaIntRanger(
					"Ditige o n�mero do jogador que recebeu o " + (i + 1) + "� cart�o Amarelo: ", 1, 11);
			Jogador modeloJogador = partida.getSelecao1().getJogadores().get(numJogador - 1);
			if (jogadorCartaoAmarelo1.containsKey(modeloJogador)) {
				int cartAmareloJogador = jogadorCartaoAmarelo1.get(modeloJogador);
				jogadorCartaoAmarelo1.put(modeloJogador, cartAmareloJogador + 1);
			} else {
				jogadorCartaoAmarelo1.put(modeloJogador, 1);
			}
		}
		PartidaDAO.editarCartAmarelo(partida, jogadorCartaoAmarelo1, 1);

		Map<Jogador, Integer> jogadorCartaoAmarelo2 = new HashMap<>();
		int cartAmareloSelecao2 = Funcoes.entradaIntRanger(
				"Digite o n�mero de Cart�es Amarelo da Sele��o |" + partida.getSelecao2() + "| : ", 0);
		if (cartAmareloSelecao2 > 0) {
			int cont2 = 1;
			for (Jogador jogador : partida.getSelecao2().getJogadores()) {
				System.out.println("[" + cont2 + "]- " + jogador);
				cont2++;
			}
		}
		for (int i = 0; i < cartAmareloSelecao2; i++) {
			int numJogador = Funcoes.entradaIntRanger(
					"Ditige o n�mero do jogador que recebeu o " + (i + 1) + "� cart�o Amarelo: ", 1, 11);
			Jogador modeloJogador = partida.getSelecao2().getJogadores().get(numJogador - 1);
			if (jogadorCartaoAmarelo2.containsKey(modeloJogador)) {
				int cartAmareloJogador = jogadorCartaoAmarelo2.get(modeloJogador);
				jogadorCartaoAmarelo2.put(modeloJogador, cartAmareloJogador + 1);
			} else {
				jogadorCartaoAmarelo2.put(modeloJogador, 1);
			}
		}
		PartidaDAO.editarCartAmarelo(partida, jogadorCartaoAmarelo2, 2);

	}

	/** 
	 *  Fun��o para fazer a coleta de dados para a edi��o de cart�es Vermelho
	 *  **/
	public static void editarCartaoVermelho(Partida partida) {
		PartidaDAO.alteracaoDeStatusPartida(true, partida);
		Map<Jogador, Integer> jogadorCartaoVermelho1 = new HashMap<>();
		int cartVermelhoSelecao1 = Funcoes.entradaIntRanger(
				"Digite o n�mero de Cart�es Vermelho da Sele��o |" + partida.getSelecao1() + "| : ", 0);
		if (cartVermelhoSelecao1 > 0) {
			int cont = 1;
			for (Jogador jogador : partida.getSelecao1().getJogadores()) {
				System.out.println("[" + cont + "]- " + jogador);
				cont++;
			}
		}
		for (int i = 0; i < cartVermelhoSelecao1; i++) {
			int numJogador = Funcoes.entradaIntRanger(
					"Ditige o n�mero do jogador que recebeu o " + (i + 1) + "� cart�o vermelho: ", 1, 11);
			Jogador modeloJogador = partida.getSelecao1().getJogadores().get(numJogador - 1);
			if (jogadorCartaoVermelho1.containsKey(modeloJogador)) {
				int cartVermelhoJogador = jogadorCartaoVermelho1.get(modeloJogador);
				jogadorCartaoVermelho1.put(modeloJogador, cartVermelhoJogador + 1);
			} else {
				jogadorCartaoVermelho1.put(modeloJogador, 1);
			}
		}
		PartidaDAO.editarCartVermelho(partida, jogadorCartaoVermelho1, 1);

		Map<Jogador, Integer> jogadorCartaoVermelho2 = new HashMap<>();

		int cartVermelhoSelecao2 = Funcoes.entradaIntRanger(
				"Digite o n�mero de Cart�es Vermelho da Sele��o |" + partida.getSelecao2() + "| : ", 0);
		if (cartVermelhoSelecao2 > 0) {
			int cont2 = 1;
			for (Jogador jogador : partida.getSelecao2().getJogadores()) {
				System.out.println("[" + cont2 + "]- " + jogador);
				cont2++;
			}
		}
		
		for (int i = 0; i < cartVermelhoSelecao2; i++) {
			int numJogador = Funcoes.entradaIntRanger(
					"Ditige o n�mero do jogador que recebeu o " + (i + 1) + "� cart�o vermelho: ", 1, 11);
			Jogador modeloJogador = partida.getSelecao2().getJogadores().get(numJogador - 1);
			if (jogadorCartaoVermelho2.containsKey(modeloJogador)) {
				int cartVermelhoJogador = jogadorCartaoVermelho2.get(modeloJogador);
				jogadorCartaoVermelho2.put(modeloJogador, cartVermelhoJogador + 1);
			} else {
				jogadorCartaoVermelho2.put(modeloJogador, 1);
			}
		}
		PartidaDAO.editarCartVermelho(partida, jogadorCartaoVermelho2, 2);

	}

	/** 
	 *  Fun��o para fazer a coleta de dados para a edi��o da Data - Ano/mes/dia
	 *  **/
	public static void editarData(Partida partida) {
		int ano = Funcoes.entradaIntRanger("Digite o Ano da Copa do mundo: ", 0);
		PartidaDAO.editarAno(ano);
		int mes = Funcoes.entradaIntRanger("Digite o n�mero do m�s [1-12]: ", 1, 12);
		PartidaDAO.editarMes(mes, partida);
		int inicio = 1;
		int fim = 0;
		if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) {
			fim = 31;
		} else if (mes == 2) {
			fim = 29;
		} else {
			fim = 30;
		}
		int dia = Funcoes.entradaIntRanger("Digite o n�mero do dia: ", inicio, fim);
		PartidaDAO.editarDia(dia, partida);

	}
	
	/** 
	 *  Fun��o para fazer a coleta de dados para a edi��o do hor�rio - Hora/min
	 *  **/
	public static void editarHoraMinuto(Partida partida) {
		int hora = Funcoes.entradaIntRanger("Digite a hora da partida: ", 0, 23);
		int minuto = Funcoes.entradaIntRanger("Digite o minuto da partida", 0, 59);
		PartidaDAO.editarHorario(hora, minuto, partida);
	}

	/** 
	 *  Fun��o para fazer a coleta de dados para a edi��o do lugar da partida
	 *  **/
	public static void editarLugar(Partida partida) {
		String lugarString = Funcoes.entradaString("Digite o nome do Local da partida: ", true);
		PartidaDAO.editarLocal(lugarString, partida);
	}

	/** 
	 *  Fun��o para fazer a coleta de dados para a edi��o da lista de Arbitros da partida
	 *  **/
	public static void editarArbitros(Partida partida) {
		List<Arbitro> arbitros = new ArrayList<>();
		int numArbitroPartida = Funcoes.entradaIntRanger("Digite o n�mero de arbitros na partida: ", 1,
				ArbitroDAO.quantidadeArbitro());
		ArbitroDAO.listar();

		for (int i = 0; i < numArbitroPartida; i++) {
			int quantArb = Funcoes.entradaIntRanger("Digite o n�mero do arbitro " + (i + 1) + "� arbitro: ", 0,
					ArbitroDAO.quantidadeArbitro() - 1);
			arbitros.add(ArbitroDAO.getOneArbitro(quantArb));
		}
		PartidaDAO.editarArbitros(arbitros, partida);
	}

	/** 
	 *  Fun��o para fazer a coleta de dados para a exclus�o dos dados da partida
	 *  **/
	public static void excluirPartida() {
		GrupoPrimeiraFase.listarTodosGrupos();
		String letraGrupo = Funcoes.entradaLetraGrupo("Digite a Letra do grupo que a sele��o pertence [A-H]: ", true);
		PartidaDAO.listarGrupo(letraGrupo);
		int escolha = Funcoes.entradaIntRanger("Ditige o n�mero da partida para excui-la [1-6]: ", 1, 6);
		Partida modeloPartida = PartidaDAO.procurarPartida(letraGrupo, escolha);
		if (PartidaDAO.excluir(modeloPartida)) {
			System.out.println("Todos os dados dessa Partida foram excu�dos.");
			System.out.println(modeloPartida);
		} else {
			System.out.println("Erro! Partida N�o encontrada!");
		}
	}

}
