package benoit;

import java.io.IOException;

public class MainLocal {

	public static void main(String[] args) throws IOException {
		System.out.println(TravailFichier.createIndexMot("corpus/").get(2));

	}

}
