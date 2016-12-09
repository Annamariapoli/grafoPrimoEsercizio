package application;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import bean.Parola;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SampleController {
	
	private Model model = new Model();
	
	public void setModel(Model model){
		this.model=model;
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtNumeroLettere;

    @FXML
    private TextField txtParola;

    @FXML
    private Button btnGenera;

    @FXML
    private Button btnVicini;

    @FXML
    private Button btnConnessi;

    @FXML
    private TextArea txtResult;

    @FXML
    private Button btnReset;

    @FXML
    void doConnessi(ActionEvent event) {
    	txtResult.clear();

    }

    @FXML
    void doGenera(ActionEvent event) throws SQLException {
    	int numeroLettere = Integer.parseInt(txtNumeroLettere.getText());
    	if(numeroLettere==0){
    		txtResult.appendText("Inserire un numero maggiore di 0! \n ");
    		return;
    	}
    	//List<Parola> paroleLunghezzaUguale = model.getDammiParoleLunghezza(numeroLettere);   //lista di parole la cui lunghezza è scelta da utente  
    	//txtResult.appendText(paroleLunghezzaUguale.toString());   //funziona

    	SimpleGraph<String, DefaultEdge> grafo = model.generaGrafoDiQuestaLunghezza(numeroLettere);
    	txtResult.appendText(grafo.toString());
    	btnVicini.setDisable(false);
    	btnConnessi.setDisable(false);
    }

    @FXML
    void doReset(ActionEvent event) {
    	txtParola.clear();
    	txtNumeroLettere.clear();
    	txtResult.clear();
    	btnConnessi.setDisable(true);
    	btnVicini.setDisable(true);
    	txtParola.setDisable(true);

    }

    @FXML
    void doVicini(ActionEvent event) throws SQLException {
    	txtResult.clear();
    	
        String nomeParola  = txtParola.getText();                    //utente inserisce il nome della parola ( quindi una stringa)
      
        int lunghezzaParolaNuova = nomeParola.length();
        int numeroLettere = Integer.parseInt(txtNumeroLettere.getText());
        if(model.parolaPresente(nomeParola) && numeroLettere == lunghezzaParolaNuova ){        //se la parola è presente nel db ed è di lungh =
            List <String> vicini = model.getTrovaVicini(nomeParola);                          //trova i suoi vicini
            txtResult.appendText(vicini.toString());                                         //stampo i nomi dei vicini
        }
        
    }

    @FXML
    void initialize() {
        assert txtNumeroLettere != null : "fx:id=\"txtNumeroLettere\" was not injected: check your FXML file 'Sample.fxml'.";
        assert txtParola != null : "fx:id=\"txtParola\" was not injected: check your FXML file 'Sample.fxml'.";
        assert btnGenera != null : "fx:id=\"btnGenera\" was not injected: check your FXML file 'Sample.fxml'.";
        assert btnVicini != null : "fx:id=\"btnVicini\" was not injected: check your FXML file 'Sample.fxml'.";
        assert btnConnessi != null : "fx:id=\"btnConnessi\" was not injected: check your FXML file 'Sample.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Sample.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Sample.fxml'.";

        btnConnessi.setDisable(true);
    	btnVicini.setDisable(true);
    	txtParola.setDisable(true);
    }
}
