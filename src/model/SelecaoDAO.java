package model;

import java.util.ArrayList;
import java.util.List;


/**
 * Classe DAO da Selecao. 
 * Ela inseri a sele��o na lista de sele��es. 
 * Ela edita alguns atributos da sele��o. 
 * Ela excluir a sele��o. 
 * Ela lista tados as sele��es.
 * 
 * @author Nalbert Santos Araujo
 * @author Pedro Henrique
 *
 */
public class SelecaoDAO implements SelecaoDAOInterface {
	int a = 0;
	/**Lista para guarda as sele��es**/
	static private List<Selecao> selecoes = new ArrayList<>();
	static private int tamanhoLista = 0;

	/**Metodo para inserir uma sele��o j� criada no banco de dados**/
	static public boolean inserir(Selecao selecao) {
		if (tamanhoLista < 32 && !selecoes.contains(selecao)) {
			tamanhoLista++;
			selecoes.add(selecao);
			return true;
		} else {
			return false;
		}
	}
	/**Metodo para editar uma sele��o que j� existente no banco de dados**/
	static public boolean editar(Selecao selecao, String nome) {
		if (nome.isEmpty() == true || selecao == null) {
			return false;
		} else {
			selecao.setNome(nome);
			return true;
		}
	}
	/**Metodo para excluir uma sele��o existente no banco de dados**/
	static public boolean excluir(int num) {
		if (num <= tamanhoLista && num >= 0) {
			List<Jogador> jogadores = getOneSelecao(num).getJogadores();
			for (Jogador jogador : jogadores) {
				JogadorDAO.excluirJogadorParcial(jogador);
			}
			selecoes.remove(num);
			return true;
		} else {
			return false;
		}
	}

	/**Metodo para mostrar todas as sele��es que est�o no banco de dados**/
	static public void listar() {

		System.out.println("\nSELE��ES:");
		int contador = 0;
		for (Selecao selecao : selecoes) {
			System.out.println("[" + contador + "] " + selecao);
			contador++;
		}
		System.out.println();
	}
	
	/**Metodo para mostrar todos os jogadores das sele��es**/
	static public void listarJogadors() {
		for (Selecao selecao : selecoes) {
			for (Jogador jogador : selecao.getJogadores()) {
				System.out.println(jogador);
			}
		}
	}
	
	/**Metodo para retornar uma sele��o com base em um numero dado para procurar no banco de dados**/
	static public Selecao getOneSelecao(int num) {
		if (num > tamanhoLista - 1 || num < 0) {
			System.out.println("\nO numero esta fora da lista!\n");
			return null;
		} else {
			return selecoes.get(num);
		}

	}

	/**Metodo para retornar a existencia ou n�o de uma sele��o no banco de dados**/
	static public boolean existeSelecao(Selecao selecao) {
		return selecoes.contains(selecao);
	}

	/**Metodo para retornar uma sele��o do banco de dados com base no index de uma sele��o**/
	static public Selecao indexSelecao(Selecao selecao) {
		return selecoes.get(selecoes.indexOf(selecao));
	}

	/**Metodo para retornar a quantidade de sele��es presentes no banco de dados**/
	static public int quantidadeSelecoes() {
		return selecoes.size();
	}

	/**Metodo para mostrar as sele��es que ainda n�o possuem tecnicos**/
	static public void imprimirSelecaoSemTecnico() {
		System.out.println("\nSELE��ES:");
		int contador = 0;
		for (Selecao selecao : selecoes) {
			if (selecao.getTecnico() == null) {
				System.out.println("[" + selecoes.indexOf(selecao) + "] " + selecao);
				contador++;
			}
		}
		if (contador == 0) {
			System.out.println("N�o existe sele��o sem tecnico.");
		}
	}
	
	/**Metodo para mostrar todas as sele��es que est�o no banco de dados**/
	static public void imprimirSelecao() {
		System.out.println("\nSELE��ES:");
		for (Selecao selecao : selecoes) {
			System.out.println("[" + selecoes.indexOf(selecao) + "] " + selecao);
		}
	}

	/**Metodo para retornar a quantidade de sele��es sem tecnico**/
	static public int selecoesSemTecnico() {
		int contador = 0;
		for (Selecao selecao : selecoes) {
			if (selecao.getTecnico() == null) {
				contador++;
			}
		}
		return contador;
	}
}
