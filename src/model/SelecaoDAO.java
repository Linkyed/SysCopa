package model;

import java.util.ArrayList;
import java.util.List;

public class SelecaoDAO {
	int a = 0;
	static private List<Selecao> selecoes = new ArrayList<>();
	static private int tamanhoLista = 0;

	static public boolean inserir(Selecao selecao) {
		if (tamanhoLista < 32 && !selecoes.contains(selecao)) {
			tamanhoLista++;
			selecoes.add(selecao);
			return true;
		} else {
			return false;
		}
	}

	static public boolean editar(Selecao selecao, String nome) {
		if (nome.isEmpty() == true || selecao == null) {
			return false;
		} else {
			selecao.setNome(nome);
			return true;
		}
	}

	static public boolean excluir(int num) {
		if (num <= tamanhoLista && num >= 0) {
			List<Jogador> jogadores = getOneSelecao(num).getJogadores();
			for (Jogador jogador : jogadores) {
				JogadorDAO.excluir_Jogador_parcial(jogador);
			}
			selecoes.remove(num);
			return true;
		} else {
			return false;
		}
	}

	static public void listar() {
		
		System.out.println("\nSELE��ES:");
		int contador = 0;
		for (Selecao selecao : selecoes) {
			System.out.println("[" + contador + "] " + selecao);
			contador++;
		}
		System.out.println();
	}

	static public void listar_jogadors() {
		for (Selecao selecao : selecoes) {
			for (Jogador jogador : selecao.getJogadores()) {
				System.out.println(jogador);
			}
		}
	}


	static public Selecao getOneSelecao(int num) {
		if (num > tamanhoLista-1 || num < 0) {
			System.out.println("\nO numero esta fora da lista!\n");
			return null;
		} else {
			return selecoes.get(num);
		}
	
	}

	static public boolean existeSelecao(Selecao selecao) {
		return selecoes.contains(selecao);
	}

	static public Selecao indexSelecao(Selecao selecao) {
		return selecoes.get(selecoes.indexOf(selecao));
	}
	
	static public int quantidadeSelecoes() {
		return selecoes.size();
	}
	
	static public int selecoesSemTecnico() {
		System.out.println("\nSELE��ES:");
		int contador = 0;
		for (Selecao selecao : selecoes) {
			if (selecao.getTecnico() == null) {
				System.out.println("[" + selecoes.indexOf(selecao) + "] " + selecao);
				contador++;				
			}
		}
		System.out.println();
		return contador;
	}
}
