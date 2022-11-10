package application;

import model.ArbitroDAO;
import model.Funcoes;
import model.Selecao;
import model.SelecaoDAO;

public class MainSelecao {

	public static Selecao inserirSelecao() {
		String nome = Funcoes.captilizeString(Funcoes.entradaString("Digite o nome da sele��o que ser� inserida: ", true));
		Selecao selecao = new Selecao(nome);
		if (SelecaoDAO.inserir(selecao)) {
			System.out.println("\nSele��o foi inserida com sucesso!\n");
		} else {
			System.out.println("\nA sele��o n�o pode ser inserida, a lista de sele��es est� cheia ou esse sele��o j� existe na lista!\n");
		}
		return selecao;
	}	
	
	public static void editarSelecao() {
		SelecaoDAO.listar();
		if (SelecaoDAO.quantidadeSelecoes() > 0) {			
			int escolhaSelecao = Funcoes.entradaIntRanger("Digite o numero correspondete a uma sele��o para ser editada: ", 0, SelecaoDAO.quantidadeSelecoes()-1);
			if (SelecaoDAO.editar(SelecaoDAO.getOneSelecao(escolhaSelecao), Funcoes.entradaString("Digite o novo nome da sele��o: ", true))) {
				System.out.println("\nNome da sele��o foi editado com sucesso!\n");
			} else {
				System.out.println("\nO nome da sele��o n�o pode ser editado, o novo nome j� pode existir em outra sele��o ou nada foi digitado!\n");
			}
		} else {
			System.out.println("\nN�o existe nenhuma sele��o para ser editada!\n");
		}
	}
	
	public static void excluirSelecao() {
		SelecaoDAO.listar();
		if (SelecaoDAO.quantidadeSelecoes() > 0) {
			int escolhaSelecao = Funcoes.entradaIntRanger("Digite o numero correspondete a uma sele��o para ser excluido: ", 0, SelecaoDAO.quantidadeSelecoes()-1);
			if (SelecaoDAO.excluir(escolhaSelecao)) {
				System.out.println("\nSele��o foi excluida com sucesso!\n");
			} else {
				System.out.println("\nA sele��o n�o pode ser excluida, tente novamente!\n");
			}			
		} else {
			System.out.println("\nN�o existe nenhuma sele��o para ser excluida!\n");
		}
	}
	
	
}
