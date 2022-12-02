package app.controller;


import java.net.URL;
import java.util.ResourceBundle;

import app.model.Funcoes;
import app.model.Jogador;
import app.model.JogadorDAO;
import app.model.Selecao;
import app.model.SelecaoDAO;
import app.model.TecnicoDAO;
import app.model.exceptions.CaracterInvalidoException;
import app.model.exceptions.ObjetoJaExisteException;
import app.model.exceptions.ObjetoNaoExisteException;
import app.model.exceptions.StringVaziaException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EdicaoGeralCriarCopa {

	private boolean objetosJaCriados= false;
	private boolean textoPosicaoCriado = false;
	
	public static String nomeSelecaoEditada = "";
	
	@FXML
	private VBox VBox;
	  
	@FXML
	private GridPane gridTela;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private Label labelSelecao;

    @FXML
    private Label labelError;
    
    @FXML
    private ComboBox<String> cbEscolhaEdicao;
    
    @FXML
    private Button btnVoltar;

    @FXML
    private Button btnEditar;
    
    @FXML
    void btnVoltarAction(ActionEvent event) {
    	Stage window = (Stage)btnVoltar.getScene().getWindow();
    	window.close();
    }
    
    //Objetos usados na hora da escolha do que ser� editado
    TextField novoNome = new TextField();
    Label labelNovoNome =  new Label();
    Label labelPosicao =  new Label();
    Label labelJogador =  new Label();
    ChoiceBox<String> escolhaPosicao = new ChoiceBox<String>();
    ComboBox<String> escolhaJogador = new ComboBox<String>();
    @FXML
    void cbEscolhaEdicaoAction(ActionEvent event) {
    	Stage window = (Stage)btnVoltar.getScene().getWindow();
    	Selecao selecao = null;
		try {
			selecao = SelecaoDAO.getSelecaoPorSelecao(new Selecao(InsercaoSelecao.selecaoComboBox));
		} catch (ObjetoNaoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	String escolha = cbEscolhaEdicao.getValue().toString();
    	if (!objetosJaCriados) {
    		novoNome.setMaxWidth(150);
    		escolhaPosicao.setMinWidth(150);
    		
    		for (Jogador jogador: selecao.getJogadores()) {
    			escolhaJogador.getItems().add(jogador.getNome());
    		}
    		escolhaJogador.setValue(escolhaJogador.getItems().get(0));
    		labelPosicao.setText("Escolha a nova posi��o do Jogador");  	
    		escolhaPosicao.getItems().addAll("Goleiro", "Lateral direito", "Lateral esquerdo", "Zagueiro",
    					"Volante", "Meia Atacante" );
    		escolhaPosicao.setValue("Goleiro");
    		
    		labelJogador.setText("Escolha um dos jogadores da sele��o");
    		VBox.getChildren().remove(btnVoltar);
    		VBox.getChildren().remove(btnEditar);
    		VBox.getChildren().remove(labelError);
    		VBox.getChildren().addAll(labelNovoNome, novoNome, labelError, btnEditar, btnVoltar); 
    		objetosJaCriados = true;
    	}
    	if (escolha.equals("Sele��o")) {
    		if (textoPosicaoCriado == true) {
    			VBox.getChildren().remove(labelPosicao);
    			VBox.getChildren().remove(escolhaPosicao);
    			VBox.getChildren().remove(labelJogador);
    			VBox.getChildren().remove(escolhaJogador);
    			window.setHeight(250);
    			textoPosicaoCriado = false;
    		}
    		
    		labelNovoNome.setText("Digite o novo nome da Sele��o");
  		} else if (escolha.equals("Tecnico")) {
  			if (textoPosicaoCriado == true) {
    			VBox.getChildren().remove(labelPosicao);
    			VBox.getChildren().remove(escolhaPosicao);
    			VBox.getChildren().remove(labelJogador);
    			VBox.getChildren().remove(escolhaJogador);
    			window.setHeight(250);
    			textoPosicaoCriado = false;
    		}
  			labelNovoNome.setText("Digite o novo nome do Tecnico");
    	} else if (escolha.equals("Jogador")) {
    		labelNovoNome.setText("Digite o novo nome do Jogador");    		
			if(!textoPosicaoCriado) {
				window.setHeight(375);
				VBox.getChildren().remove(labelNovoNome);
				VBox.getChildren().remove(novoNome);
				VBox.getChildren().remove(btnVoltar);
				VBox.getChildren().remove(btnEditar);
				VBox.getChildren().remove(labelError);
				
				VBox.getChildren().addAll(labelJogador, escolhaJogador, labelNovoNome, novoNome ,labelPosicao, escolhaPosicao, labelError, btnEditar, btnVoltar);
				textoPosicaoCriado = true;			    			
			}
    		
    	}
    }
    
    @FXML
    void btnEditarAction(ActionEvent event) {
    	try {
    		if (cbEscolhaEdicao.getValue().toString().equals("Sele��o")) {
    			SelecaoDAO.editar(SelecaoDAO.getSelecaoPorSelecao(new Selecao(InsercaoSelecao.selecaoComboBox)), Funcoes.captilizeString(novoNome.getText().strip()));
    			nomeSelecaoEditada = Funcoes.captilizeString(novoNome.getText().strip());
    			InsercaoSelecao.alteracaoSelecao = true;
    		} else if (cbEscolhaEdicao.getValue().toString().equals("Tecnico")) {
    			TecnicoDAO.editar(SelecaoDAO.getSelecaoPorSelecao(new Selecao(InsercaoSelecao.selecaoComboBox)).getTecnico(), Funcoes.captilizeString(novoNome.getText().strip()));
    		} else if (cbEscolhaEdicao.getValue().toString().equals("Jogador")) {
    			Jogador jogador = JogadorDAO.getJogadorPorNome(escolhaJogador.getValue().toString().strip());
    			JogadorDAO.editarNome(jogador, Funcoes.captilizeString(novoNome.getText().strip()));
    			JogadorDAO.editarPosicao(jogador, escolhaPosicao.getValue().toString());
    		}
    		
    		Stage window = (Stage)btnVoltar.getScene().getWindow();
    		window.close();
    	} catch (ObjetoJaExisteException e) {
    		labelError.setText(e.getMessage());
    	} catch (ObjetoNaoExisteException e) {
    		labelError.setText(e.getMessage());
    	} catch (StringVaziaException e) {
    		labelError.setText(e.getMessage());
    	} catch (CaracterInvalidoException e) {
    		labelError.setText(e.getMessage());
    	} catch (StringIndexOutOfBoundsException e) {
    		labelError.setText("O nome est� vazio!");
    	}
    	
    }

    @FXML
    void initialize() {
       
        assert btnVoltar != null : "fx:id=\"btnVoltar\" was not injected: check your FXML file 'EdicaoNaInsercao.fxml'.";
        try {
			labelSelecao.setText(labelSelecao.getText().formatted(SelecaoDAO.getSelecaoPorSelecao(new Selecao(InsercaoSelecao.selecaoComboBox)).getNome()));
		} catch (ObjetoNaoExisteException e) {
			labelError.setText("Sele��o nao encontrada@");
		}
        cbEscolhaEdicao.getItems().addAll("Sele��o", "Tecnico", "Jogador");
    }

}
