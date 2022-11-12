package application;


import model.Funcoes;
import model.GrupoPrimeiraFase;
import model.Selecao;
import model.SelecaoDAO;

public class MainSelecao {
	
	/** Metodo para inserir uma sele��o **/
	public static Selecao inserirSelecao() {
		while (true) {
			String nome = Funcoes.captilizeString(Funcoes.entradaString("Digite o nome da sele��o que ser� inserida: ", true));
			Selecao selecao = new Selecao(nome);
			if (SelecaoDAO.inserir(selecao)) {
				System.out.println("\nSele��o foi inserida com sucesso!\n");
				return selecao;
			} else {
				System.out.println("\nA sele��o n�o pode ser inserida! Essa sele��o j� existe na lista!\n");
			}
		}
	}	
	
	/** Metodo para editar uma sele��o **/
	public static void editarSelecao() {
		SelecaoDAO.listar();
		if (SelecaoDAO.quantidadeSelecoes() > 0) {			
			while (true) {
				int escolhaSelecao = Funcoes.entradaIntRanger("Digite o numero correspondete a uma sele��o para ser editada: ", 0, SelecaoDAO.quantidadeSelecoes()-1);
				if (SelecaoDAO.editar(SelecaoDAO.getOneSelecao(escolhaSelecao), Funcoes.entradaString("Digite o novo nome da sele��o: ", true))) {
					System.out.println("\nNome da sele��o foi editado com sucesso!\n");
					break;
				} else {
					System.out.println("\nO nome da sele��o n�o pode ser editado! O novo nome j� pode existir em outra sele��o ou nada foi digitado!\n");
				}
			}
		} else {
			System.out.println("\nN�o existe nenhuma sele��o para ser editada!\n");
		}
	}
	
	/** Metodo para excluir uma sele��o **/
	public static String excluirSelecao() {
		SelecaoDAO.listar();
		String letra = "";
		if (SelecaoDAO.quantidadeSelecoes() > 0) {
			int escolhaSelecao = Funcoes.entradaIntRanger("Digite o numero correspondete a uma sele��o para ser excluido: ", 0, SelecaoDAO.quantidadeSelecoes()-1);
			letra = GrupoPrimeiraFase.grupoSelecao(SelecaoDAO.getOneSelecao(escolhaSelecao));
			if (SelecaoDAO.excluir(escolhaSelecao)) {
				System.out.println("\nSele��o foi excluida com sucesso!\n");
			} else {
				System.out.println("\nA sele��o n�o pode ser excluida, tente novamente!\n");
			}			
		} else {
			System.out.println("\nN�o existe nenhuma sele��o para ser excluida!\n");
		}
		return letra;
	}
	
	
}
