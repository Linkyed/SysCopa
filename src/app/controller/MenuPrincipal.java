package app.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import app.model.ArbitroDAO;
import app.model.GrupoPrimeiraFase;
import app.model.JogadorDAO;
import app.model.PartidaDAO;
import app.model.SelecaoDAO;
import app.model.Tecnico;
import app.model.TecnicoDAO;
import app.model.Teste;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MenuPrincipal extends JanelaJAVAFX{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCriarCopa;
    
    @FXML
    private Button btnUsarDados;

    
    @FXML
    /**A��o do bot�o no menu principal onde o ususario come�a a criar a copa do zero, inserindo sele��o por sele��o at� completar as 32**/
    void btnCriarCopaAction(ActionEvent event) throws IOException {
    	SelecaoDAO.resetarLista();
    	TecnicoDAO.resetarLista();
    	JogadorDAO.resetarLista();
    	ArbitroDAO.resetarLista();
    	GrupoPrimeiraFase.resetarGrupos();	
		Stage window = (Stage)btnCriarCopa.getScene().getWindow();
		alertBoxSoAviso("Iniciando", "Primeiro insira os 20 arbitros que far�o parte da copa");
		for (int i = 0; i < 20; i++) {
			CriarArbitro.continuar = true;
			abrirJanela("/app/view/criarCopa/CriarArbitro.fxml", 250, 200, true, false);
			if (!CriarArbitro.continuar) {
				ArbitroDAO.resetarLista();
				break;
			}
		}
		if(ArbitroDAO.quantidadeArbitro() == 20) {
			trocarJanela("/app/view/criarCopa/InsercaoSelecao.fxml", 700, 500, window);			
		}
    }
    
    @FXML
    /**A��o do bot�o no menu principal onde o ususario pode escolher usar dados predefinidos, 
     * onde as 32 sele��es j� estar�o criadas**/
    void btnUsarDadosPreCarregados(ActionEvent event) throws IOException {
    	SelecaoDAO.resetarLista();
    	TecnicoDAO.resetarLista();
    	JogadorDAO.resetarLista();
    	ArbitroDAO.resetarLista();
    	GrupoPrimeiraFase.resetarGrupos();
    	Teste.preDefinicao();
		Stage window = (Stage)btnUsarDados.getScene().getWindow();
		trocarJanela("/app/view/criarCopa/InsercaoSelecao.fxml", 700, 500, window);
		
		CriarArbitro.arbitrosCriados = 20;
    }
    
    @Override
    /**Polimorfismo na fun��o de impedirFechamento pois nesse menu � necessario que essa fun��o mostre uma outra mensagem para o usuario**/
    void impedirFechamento(Stage window, String titulo, String mensagem) {
    	window.setOnCloseRequest(new EventHandler<WindowEvent>() {
        	@Override
        	public void handle(WindowEvent event) {
        		alertBox("Cuidado", "Se voc� fechar a janela todo o progresso sera perdido!");
        		if (!CriarArbitro.continuar) {
        			window.close();
        		} else {
        			event.consume();        			
        		}
        	}
        });
	   }

    
    /**Fun��o para mostrar uma alertbox para o usuario, bem parecido com a que existe na classe m�e**/
    void alertBoxSoAviso(String titulo, String mensagem) {
    	Stage window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(titulo);
		window.setMinWidth(250);
		window.setMinHeight(100);
		
		Label label = new Label();
		label.setText(mensagem);
		label.setFont(Font.font("System", FontWeight.BOLD, 12));
		if (mensagem.length() > 30) {
			label.setMinWidth(200);
			label.setMaxWidth(200);
			label.setPrefWidth(200);
			label.setTextAlignment(TextAlignment.CENTER);			
		}
		label.setWrapText(true);
		Button btnCloseAlertBox = new Button("Fechar alerta");
		btnCloseAlertBox.setOnAction(e -> window.close());
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, btnCloseAlertBox);
		layout.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
    	
    }
    
    @Override
    /**Polimorfismo na alertBox da classe m�e para mudar o que � exibido no alerta, para que adiciona uma funcionalidade a mais na hora
     * de inserir os arbitros**/
    void alertBox(String titulo, String mensagem) {
    	Stage window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(titulo);
		window.setMinWidth(250);
		window.setMinHeight(200);
		
		Label label = new Label();
		label.setText(mensagem);
		label.setFont(Font.font("System", FontWeight.BOLD, 12));
		if (mensagem.length() > 30) {
			label.setMinWidth(200);
			label.setMaxWidth(200);
			label.setPrefWidth(200);
			label.setTextAlignment(TextAlignment.CENTER);			
		}
		label.setWrapText(true);
		Button btnFechar = new Button("Parar Inser��o");
		btnFechar.setMinWidth(150);
		btnFechar.setOnAction(e -> pararInsercao(window));
		Button btnContinuar = new Button("Continuar");
		btnContinuar.setMinWidth(150);
		btnContinuar.setOnAction(e -> window.close());
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, btnFechar, btnContinuar);
		layout.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(layout);
		window.setScene(scene);
		impedirFechamento(window, "Cuidado!", "Se voc� fechar a janela todo o progresso sera perdido!");
		window.showAndWait();
		
    }
  

    @FXML
    void initialize() {
        assert btnCriarCopa != null : "fx:id=\"btnCriarCopa\" was not injected: check your FXML file 'MenuPrincipal.fxml'.";
        assert btnUsarDados != null : "fx:id=\"btnCriarCopa\" was not injected: check your FXML file 'MenuPrincipal.fxml'.";

    }
    
    /**Fun��o que muda uma variavel na inser��o dos arbitros e fecha a janela de cria��o dos arbitros**/
    private void pararInsercao(Stage window) {
    	CriarArbitro.continuar = false;
    	window.close();
    }

}
