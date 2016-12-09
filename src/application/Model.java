package application;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import bean.Parola;
import db.Dao;

public class Model {
	
	Dao dao = new Dao();
	SimpleGraph<String, DefaultEdge> grafo = new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);

	
	
	public List<Parola> tutteLeParole () throws SQLException{   //mi da tutte le parole del db
		List<Parola> tutte = dao.getTutte();
		return tutte;
	}
	
	
	
	
	//genera un grafo con tutte le parole del db:
	
	public void generaGrafo() throws SQLException{
		for(Parola p : dao.getTutte()){             //aggiungi  i vertici 
			grafo.addVertex(p.getNome());          //prendo il nome della parola, non l'id
		}	
		//metto gli archi:
		for(Parola p1 : dao.getTutte()){                                //x tutte le parole     
			for(Parola p2 : dao.getTutte())                            //guardo ogni coppia 
		        if(differiscono(p1, p2))                              //se è vero che differiscono per una sola lettera
		        	grafo.addEdge(p1.getNome(), p2.getNome());       //allora le collego	
			}
	}
	
	
	
	
	
	public boolean differiscono(Parola p1, Parola p2){     //differiscono x una sola lettera
		String nomeUno = p1.getNome();
		String nomeDue = p2.getNome();
		int lunghezza1 = nomeUno.length();
		int lunghezza2 = nomeDue.length();
			if(lunghezza1==lunghezza2){                    //--> controllo inutile
				int contatore=0;                                                   //tiene conto di quanto non siano uguali
				for(int i=0; i<nomeUno.length() && i<nomeDue.length(); i++){
					if(nomeUno.charAt(i)!=(nomeDue.charAt(i))){
						contatore++;
					}
				}
				if(contatore==1){
					return true;
				}
				return  false;
			}
			return false;			
     }
	
	
	
	
	public List<Parola> getDammiParoleLunghezza(int numeroLettere) throws SQLException{
		List<Parola> paroleDiLunghezza = dao.getDammiParoleDiQuestaLunghezza(numeroLettere);
		return paroleDiLunghezza;
	}
	

	
	
	
	
	//genera un grafo con le parole che hanno quella lunghezza:
	
	public SimpleGraph<String, DefaultEdge> generaGrafoDiQuestaLunghezza(int lung) throws SQLException{
		for(Parola p : dao.getDammiParoleDiQuestaLunghezza(lung)){                        //aggiungi  i vertici (che sono le parole che hanno quella determinata lunghezza)
			grafo.addVertex(p.getNome());                                                //prendo il nome della parola, non l'id
		}		
		//metto gli archi:
		for(Parola p1 : dao.getDammiParoleDiQuestaLunghezza(lung)){                              //x tutte le parole     
			for(Parola p2 : dao.getDammiParoleDiQuestaLunghezza(lung)) {                        //guardo ogni coppia 
		        if(differiscono(p1, p2))                                                       //se è vero che differiscono per una sola lettera
		        	grafo.addEdge(p1.getNome(), p2.getNome());                                //allora le collego	
			  }		
	     }
		System.out.println(grafo.toString());
		return grafo;
   }
	

	
	
	public boolean parolaPresente(String nome) throws SQLException{    //parola presente nel db
		return dao.isParolaPresente(nome);
	}
	
	
	
	
	//list di string xke considero i nomi delle parole:
	
	public List<String> getTrovaVicini(String nome) throws SQLException{
		List<String> vicini = Graphs.neighborListOf(grafo, nome);
		return vicini;
		
	}
	
	
	
	
	
	
	
	
	
//	public static void main(String [] args) throws SQLException{
//		Model model = new Model();
//		model.generaGrafoDiQuestaLunghezza(2);
//	}
	
}