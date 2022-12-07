package app.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import app.model.GrupoPrimeiraFase;
import app.model.Selecao;
import app.model.SelecaoDAO;
import app.model.exceptions.CaracterInvalidoException;
import app.model.exceptions.ListaCheiaException;
import app.model.exceptions.ObjetoJaExisteException;
import app.model.exceptions.StringVaziaException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CriarSelecao {

	//Variaveis publicas para realizar controles logicos e de interface
	
	//Variavel que � atualizada toda vez que uma nova sele��o � criada
	public static Selecao selecaoAtual;
	//Variavel que guarda a quantidade de jogadores que falta para uma sele��o estar completa
	public static int quantidadeJoadores;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnProximo;

    @FXML
    private Label errorShow;

    @FXML
    private TextField nomeSelecao;

    @FXML
    private ChoiceBox<String> grupoSelecao;

    
    @FXML
    /**A��o do bot�o de proximo onde com base no que foi digitado pelo usuario, o DAO da sele��o ira fazer suas devidas verifica��es
     * e caso seja aceita a sele��o sera criada, caso n�o sej� aceita o erro encontrado ser� reportado para o usuario para que ele
     * possa concertar**/
    void btnProximoAction(ActionEvent event) throws IOException {
    	try {
    		//Inserindo a sele��o
    		Selecao selecao = new Selecao(nomeSelecao.getText().strip());
    		SelecaoDAO.inserir(selecao);
    		GrupoPrimeiraFase.adicionarSelecao(grupoSelecao.getValue().toString(), selecao);
    		GrupoPrimeiraFase.listarTodosGrupos();
    		System.out.println("Aceito!");
    		InsercaoSelecao.quantidadeSelecoes += 1;
    		selecaoAtual = selecao;
    		System.out.println(selecao);
    		//Fechando tela da sele��o
    		Stage window = (Stage)btnProximo.getScene().getWindow();
    		window.close();
    	} catch (CaracterInvalidoException e) {
			System.out.println(e.getMessage());
			errorShow.setText(e.getMessage());
		} catch (ObjetoJaExisteException e) {
			System.out.println(e.getMessage());
			errorShow.setText(e.getMessage());
		} catch (ListaCheiaException e) {
			System.out.println(e.getMessage());
			errorShow.setText(e.getMessage());
		} catch (StringVaziaException e) {
			System.out.println(e.getMessage());
			errorShow.setText(e.getMessage());
		} catch (StringIndexOutOfBoundsException e) {
			errorShow.setText("O Nome esta vazio!");
		}
    }

    @FXML
    /**Atalho ENTER para que n�o seja necessario o click no bot�o de proximo**/
    void enterPressionado(KeyEvent event) throws IOException {
    	if (event.getCode().toString().equals("ENTER")) {
    		btnProximoAction(new ActionEvent());
    	}
    }
    
    @FXML
    void initialize() {
        assert btnProximo != null : "fx:id=\"btnProximo\" was not injected: check your FXML file 'CriarSelecao.fxml'.";
        assert errorShow != null : "fx:id=\"errorShow\" was not injected: check your FXML file 'CriarSelecao.fxml'.";
        assert nomeSelecao != null : "fx:id=\"nomeSelecao\" was not injected: check your FXML file 'CriarSelecao.fxml'.";
        
        //Parte onde ser� disponibilizado para o usuario os grupos que possuem espa�o para a sele��o ser inserida
        List<String> gruposVazios = GrupoPrimeiraFase.gruposVazios();
        
        for (String grupo: gruposVazios) {
        	grupoSelecao.getItems().add(grupo);
        }
        
        grupoSelecao.setValue(gruposVazios.get(0));
    }

}
