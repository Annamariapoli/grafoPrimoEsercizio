package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import bean.Parola;

public class Dao {
	
	public List<Parola> getTutte() throws SQLException{     //mi da tutte le parole del db
		Connection conn = DBConnect.getConnection();
		String query = "SELECT * FROM parola";
		try{
			PreparedStatement st = conn.prepareStatement(query);
			List<Parola> tutte = new LinkedList<Parola>();
			ResultSet res = st.executeQuery();
			while(res.next()){
				Parola p = new Parola(res.getInt("id"), res.getString("nome"));
				tutte.add(p);
			}
			conn.close();
			return tutte;
		}catch (SQLException e ){
			e.printStackTrace();
			throw new RuntimeException("Errore nel db! ", e);
		}
	}
	
	public List<Parola> getDammiParoleDiQuestaLunghezza(int lun) throws SQLException{     //mi da tutte le parole di questa lunghezza
		Connection conn = DBConnect.getConnection();
		String query = "select * from parola where length (nome) = ?;";
		try{
			PreparedStatement st = conn.prepareStatement(query);
			List<Parola> queste = new LinkedList<Parola>();
			st.setInt(1, lun);
			ResultSet res = st.executeQuery();
			while(res.next()){
				Parola p = new Parola(res.getInt("id"), res.getString("nome"));
				queste.add(p);
			}
			conn.close();
			return queste;
		}catch(SQLException e ){
			e.printStackTrace();
			throw new RuntimeException("Errore nella connessione al db! ", e);
		}
	}
	
	
	public boolean isParolaPresente(String nome) throws SQLException{    //controlla se la parola è presente nel db
		Connection conn = DBConnect.getConnection();
		String query = "select * from parola where nome=? ; ";
		try{
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, nome);
			ResultSet res = st.executeQuery();
			if(res.next()){
				Parola p = new Parola(res.getInt("id"), res.getString("nome"));
				return true;
			}
			else{ 
				   return false;
			    }
		}catch(SQLException e ){
			e.printStackTrace();
			throw new RuntimeException("Errore nella connessione al db! ", e);
		}
	}
}
