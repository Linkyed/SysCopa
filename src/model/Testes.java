package model;
public class Testes {
	
	public static void main(String[] args) {
		//TESTE DO TECNICO
		TecnicoDAO tecnicoDAO = new TecnicoDAO();
		Selecao a = new Selecao("brasil");
		Selecao b = new Selecao("Argentina");
		tecnicoDAO.inserir(new Tecnico("Jos", a));
		tecnicoDAO.inserir(new Tecnico("Vestapen", b));
		tecnicoDAO.listar();
		System.out.println("--------------");
		System.out.println("Exclus�o: "+tecnicoDAO.excluir(new Tecnico("Jos", a)));
		System.out.println("--------------");
		tecnicoDAO.listar();
		System.out.println("--------------");
		System.out.println("Edi��o: "+tecnicoDAO.editar(tecnicoDAO.getOneTecnico(0), "Jo�o"));
		System.out.println("--------------");
		tecnicoDAO.listar();
		
		//TESTE DO ARBITRO
		ArbitroDAO arbitroDAO = new ArbitroDAO();
		System.out.println("--------------");
		System.out.println("Inser��o: "+arbitroDAO.inserir(new Arbitro("Josias")));
		System.out.println("Inser��o: "+arbitroDAO.inserir(new Arbitro("Rafael")));
		System.out.println("--------------");
		arbitroDAO.listar();
		System.out.println("--------------");
		System.out.println("Edi��o: "+arbitroDAO.editar(arbitroDAO.getOneArbitro(0), "Jo�o"));
		System.out.println("--------------");
		arbitroDAO.listar();
		System.out.println("--------------");
		System.out.println("Exclus�o: "+arbitroDAO.excluir(new Arbitro("Jo�o")));
		System.out.println("--------------");
		arbitroDAO.listar();
		
		Jogador zezinho = new Jogador("joao", null, a);
		System.out.println(zezinho.setNome("Joao"));
	}
}
