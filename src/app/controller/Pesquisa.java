package app.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import app.model.Arbitro;
import app.model.ArbitroDAO;
import app.model.Jogador;
import app.model.JogadorDAO;
import app.model.Selecao;
import app.model.SelecaoDAO;
import app.model.Tecnico;
import app.model.TecnicoDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Label;


public class Pesquisa extends JanelaJAVAFX{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnPesquisar;

    @FXML
    private ChoiceBox<String> escolhaPesquisa;

    @FXML
    private TextField nomePesquisa;

    @FXML
    void btnPesquisar(ActionEvent event) {
    	Stage window = new Stage();
    	window.initModality(Modality.APPLICATION_MODAL);
    	
    	VBox layout = new VBox();
    	layout.setAlignment(Pos.CENTER);
    	
    	Label label = new Label();
    	label.setText("Esses s�o os resultados da pesquisa de '%s' em %s".formatted(nomePesquisa.getText(), escolhaPesquisa.getValue().toString()));
    	
    	ListView<String> pesquisaView = new ListView<>();
    	layout.getChildren().addAll(label, pesquisaView);
    	
    	Scene scene = new Scene(layout);

    	if (escolhaPesquisa.getValue().toString().equals("Sele��o")) {
    		List<Selecao> lista = SelecaoDAO.getSelecaoNome(nomePesquisa.getText());
    		
    		if (lista.size() > 0) {
    			for (Selecao selecao: lista) {
    				pesquisaView.getItems().add(selecao.getNome());
    			}    			
    			window.setScene(scene);
    			window.showAndWait();
    		} else {
    			alertBox("Pesquisa", "N�o existe nenhuma sele��o que possua a sequencia de caracters que foi digitada!");
    		}
    		  		
    	} else if (escolhaPesquisa.getValue().toString().equals("Tecnico")) {
    		List<Tecnico> lista = TecnicoDAO.getTecnicoNome(nomePesquisa.getText());
    		
    		if (lista.size() > 0) {
    			for (Tecnico tecnico: lista) {
    				pesquisaView.getItems().add(tecnico.getNome());
    			}    			
    			window.setScene(scene);
    			window.showAndWait();
    		} else {
    			alertBox("Pesquisa", "N�o existe nenhum tecnico que possua a sequencia de caracters que foi digitada!");
    		}
    		
    	} else if (escolhaPesquisa.getValue().toString().equals("Jogador")) {
    		List<Jogador> lista = JogadorDAO.getJogadorNome(nomePesquisa.getText());
    		
    		if (lista.size() > 0) {
    			for (Jogador jogador: lista) {
    				pesquisaView.getItems().add(jogador.getNome());
    			}    			
    			window.setScene(scene);
    			window.showAndWait();
    		} else {
    			alertBox("Pesquisa", "N�o existe nenhum jogador que possua a sequencia de caracters que foi digitada!");
    		}
    		
    	} else if (escolhaPesquisa.getValue().toString().equals("Arbitro")) {
    		List<Arbitro> lista = ArbitroDAO.getArbitroNome(nomePesquisa.getText());
    		
    		if (lista.size() > 0) {
    			for (Arbitro arbitro: lista) {
    				pesquisaView.getItems().add(arbitro.getNome());
    			}    			
    			window.setScene(scene);
    			window.showAndWait();
    		} else {
    			alertBox("Pesquisa", "N�o existe nenhum arbitro que possua a sequencia de caracters que foi digitada!");
    		}
    		
    	}
    
    }

    @FXML
    void initialize() {
    	escolhaPesquisa.getItems().addAll("Sele��o", "Tecnico", "Jogador", "Arbitro");
    	escolhaPesquisa.setValue("Sele��o");
    	
        assert btnPesquisar != null : "fx:id=\"btnPesquisar\" was not injected: check your FXML file 'Pesquisa.fxml'.";
        assert escolhaPesquisa != null : "fx:id=\"escolhaPesquisa\" was not injected: check your FXML file 'Pesquisa.fxml'.";
        assert nomePesquisa != null : "fx:id=\"nomePesquisa\" was not injected: check your FXML file 'Pesquisa.fxml'.";

    }

}
