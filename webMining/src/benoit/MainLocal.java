package benoit;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import quentin.GestionRequete;
import fileSysUtils.TreeRepresentation;

public class MainLocal {

	public static void main(String[] args) throws IOException {
		Calendar cal = Calendar.getInstance();
    	cal.getTime();
    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    	System.out.println( sdf.format(cal.getTime()) );
    	TreeRepresentation root=new TreeRepresentation("propre", null);
		TreeRepresentation propre=TravailFichier.createIndex("proprietaire/",root);
		
		Calendar cal1 = Calendar.getInstance();
    	cal1.getTime();
    	SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
    	System.out.println( sdf1.format(cal1.getTime()) );
		
    	//System.out.println(propre.lookup("propre"));
    	System.out.println(propre.lookupDv("propre").getStats().toString());
    	
    	GestionRequete.printMap(GestionRequete.classerDocument(GestionRequete.CalculAllScore("propre", propre)));
    	
	}

}
