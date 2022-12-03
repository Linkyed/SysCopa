package app.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import app.model.Jogador;
import app.model.JogadorDAO;
import app.model.Selecao;
import app.model.SelecaoDAO;
import app.model.TecnicoDAO;
import app.model.exceptions.ObjetoNaoExisteException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ExclusaoGeralCriarCopa extends JanelaJAVAFX{

	public static boolean selecaoExcluida = false;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private VBox VBox;

    @FXML
    private Button btnExcluir;

    @FXML
    private Button btnVoltar;

    @FXML
    private ComboBox<String> cbEscolhaExclusao;

    @FXML
    private GridPane gridTela;

    @FXML
    private Label labelError;

    @FXML
    private Label labelSelecao;

    ComboBox<String> escolhaJogador = new ComboBox<String>();
    private boolean objetosJogadorCriado = false;
    @FXML
    void cbEscolhaExclusaoAction(ActionEvent event) {
    	if (cbEscolhaExclusao.getValue().toString().equals("Sele��o")) {
    		if (objetosJogadorCriado) {
    			VBox.getChildren().removeAll(escolhaJogador); 
    			objetosJogadorCriado = false;
    		}
    	} else if (cbEscolhaExclusao.getValue().toString().equals("Tecnico")) {
    		if (objetosJogadorCriado) {
    			VBox.getChildren().removeAll(escolhaJogador); 
    			objetosJogadorCriado = false;
    		}
    	} else if (cbEscolhaExclusao.getValue().toString().equals("Jogador")) {
    		if (!objetosJogadorCriado) {
    			VBox.getChildren().removeAll(btnExcluir, btnVoltar);    			
    			VBox.getChildren().addAll(escolhaJogador, btnExcluir, btnVoltar);
    			escolhaJogador.setValue(escolhaJogador.getItems().get(0));
    			objetosJogadorCriado = true;
    		}
    	}
    }
    
    @FXML
    void btnExcluirAction(ActionEvent event) throws IOException {
    	Stage window = (Stage)btnVoltar.getScene().getWindow();
    	if (cbEscolhaExclusao.getValue().toString().equals("Sele��o")) {
    		try {
    			SelecaoDAO.excluir(new Selecao(InsercaoSelecao.selecaoComboBox));
    			window.close();
    			JanelaJAVAFX.alertBox("Resultado", "Exclus�o foi realizada com sucesso! Agora insira a nova sele��o");
    			//Iniciando a inser��o de um novo arbitro para tomar o lugar
    		 	abrirJanela("/app/view/criarCopa/CriarSelecao.fxml", 250, 150, true, false);
    		 	//Iniciando o precesso de inserir um Tecnico em uma tela separada
    		 	abrirJanela("/app/view/criarCopa/CriarTecnico.fxml", 250, 150, true, false);
    		 	//Iniciando o processo de de inserir um jogador em uma tela separada
    		 	for (int i = 0; i < 11; i++) {
    		 		abrirJanela("/app/view/criarCopa/CriarJogador.fxml", 250, 150, true, false);
    		 	}
    			selecaoExcluida = true;
    		} catch (ObjetoNaoExisteException e) {
    			labelError.setText("Sele��o n�o pode ser excluida");
    		}
    	} else if (cbEscolhaExclusao.getValue().toString().equals("Tecnico")) {
    		try {
				TecnicoDAO.excluir(SelecaoDAO.getSelecaoPorSelecao(new Selecao(InsercaoSelecao.selecaoComboBox)).getTecnico());
				
				window.close();
				JanelaJAVAFX.alertBox("Resultado", "Exclus�o foi realizada com sucesso! Agora insira o novo tecnico desta sele��o");
				//Iniciando a inser��o de um novo arbitro para tomar o lugar
				abrirJanela("/app/view/criarCopa/CriarTecnico.fxml", 250, 150, true, false);
			} catch (ObjetoNaoExisteException e) {
				labelError.setText(e.getMessage());
			}
    	} else if (cbEscolhaExclusao.getValue().toString().equals("Jogador")) {
    		try {
				JogadorDAO.excluir(new Jogador(escolhaJogador.getValue().toString(), null, null));
				window.close();
				JanelaJAVAFX.alertBox("Resultado", "Exclus�o foi realizada com sucesso! Agora insira o novo jogador desta sele��o");
				//Iniciando a inser��o de um novo arbitro para tomar o lugar
				abrirJanela("/app/view/criarCopa/CriarJogador.fxml", 250, 275, true, false);
			} catch (ObjetoNaoExisteException e) {
				labelError.setText(e.getMessage());
			}
    		
    	}
    	
    }

    @FXML
    void btnVoltarAction(ActionEvent event) {
    	Stage window = (Stage)btnVoltar.getScene().getWindow();
    	window.close();
    }

   

    @FXML
    void initialize() {
    	cbEscolhaExclusao.getItems().addAll("Sele��o", "Tecnico", "Jogador");
    	cbEscolhaExclusao.setValue("Sele��o");
    	cbEscolhaExclusao.setMinWidth(150);
    	try {
			for (Jogador jogador: SelecaoDAO.getSelecaoPorSelecao(new Selecao(InsercaoSelecao.selecaoComboBox)).getJogadores()) {
				escolhaJogador.getItems().add(jogador.getNome());
			}
		} catch (ObjetoNaoExisteException e) {
			labelError.setText("Sele��o n�o encontrada!");
		}
    	try {
			labelSelecao.setText(labelSelecao.getText().formatted(SelecaoDAO.getSelecaoPorSelecao(new Selecao(InsercaoSelecao.selecaoComboBox)).getNome()));
		} catch (ObjetoNaoExisteException e) {
			labelError.setText("Sele��o nao encontrada@");
		}
		labelSelecao.setText(labelSelecao.getText().formatted(InsercaoSelecao.selecaoComboBox));
	
    	
        assert VBox != null : "fx:id=\"VBox\" was not injected: check your FXML file 'ExclusaoGeral.fxml'.";
        assert btnExcluir != null : "fx:id=\"btnExcluir\" was not injected: check your FXML file 'ExclusaoGeral.fxml'.";
        assert btnVoltar != null : "fx:id=\"btnVoltar\" was not injected: check your FXML file 'ExclusaoGeral.fxml'.";
        assert cbEscolhaExclusao != null : "fx:id=\"cbEscolhaExclusao\" was not injected: check your FXML file 'ExclusaoGeral.fxml'.";
        assert gridTela != null : "fx:id=\"gridTela\" was not injected: check your FXML file 'ExclusaoGeral.fxml'.";
        assert labelError != null : "fx:id=\"labelError\" was not injected: check your FXML file 'ExclusaoGeral.fxml'.";
        assert labelSelecao != null : "fx:id=\"labelSelecao\" was not injected: check your FXML file 'ExclusaoGeral.fxml'.";

    }
    private void impedirFechamento(Stage window, String titulo, String mensagem) {
   	 window.setOnCloseRequest(new EventHandler<WindowEvent>() {
	        	@Override
	        	public void handle(WindowEvent event) {
	        		JanelaJAVAFX.alertBox(titulo, mensagem);
	        		event.consume();
	        	}
	        });
   }

}
