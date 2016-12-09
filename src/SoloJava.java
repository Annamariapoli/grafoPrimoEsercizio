import java.util.LinkedList;
import java.util.List;

import bean.Parola;

public class SoloJava {
	
	List<Parola> tutte = new LinkedList<Parola>();
	
	public boolean paroleDiffUna(String p1 , String p2){   //vedo se differiscono per una sola lettera  //ok
		int lunghezza1 = p1.length();
		int lunghezza2 = p2.length();
		if(lunghezza1==lunghezza2){
			int contatore=0;                                    //tiene conto di quanti caratteri  non siano uguali
			for(int i=0; i<p1.length() && i<p2.length(); i++){
				if(p1.charAt(i)!=(p2.charAt(i))){
					contatore++;
				}
			}
			if(contatore==1){
				System.out.println(true);
				return true;
			}
			System.out.println(false);
			return  false;
		}
		System.out.println(false);
		return false;
		
		
	}
	
	public static void main(String [] args){
		SoloJava java = new SoloJava();
		java.paroleDiffUna("luca", "lun");
	}

}
