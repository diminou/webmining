package benoit;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainLocal {

	public static void main(String[] args) throws IOException {
		Calendar cal = Calendar.getInstance();
    	cal.getTime();
    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    	System.out.println( sdf.format(cal.getTime()) );
		System.out.println(TravailFichier.listeMotsClesFolder("newCorpus2/").size());
		Calendar cal1 = Calendar.getInstance();
    	cal1.getTime();
    	SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
    	System.out.println( sdf1.format(cal1.getTime()) );
		
	}

}
