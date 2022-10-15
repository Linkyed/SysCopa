package model;

public class Pesquisa {

	static public String buscarSelecao() {
		String nomeBusca = Funcoes.entradaString("Digite o nome da sele��o que deseja procurar no banco de dados: ", true);
		Selecao selecaoBuscada = SelecaoDAO.getSelecaoNome(nomeBusca);
		if (selecaoBuscada == null) {
			return "\nSele��o n�o encontrada, tente outro nome!\n";
		}
		String jogadores = "";
		for(Jogador jogador: selecaoBuscada.getJogadores()) {
			jogadores += jogador.getNome() + "\n";
		}
		return "\nA sele��o " + selecaoBuscada + " foi encontrada e essas s�o suas informa��es:" +
		"\nNome: " + selecaoBuscada.getNome() + 
		"\nTecnico: " + selecaoBuscada.getTecnico() +
		"\nJogadores: " + jogadores +
		"\nPartidas: ";
	}
	
}
