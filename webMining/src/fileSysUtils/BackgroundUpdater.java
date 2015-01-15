/**
 * 
 */
package fileSysUtils;

import java.io.IOException;

import benoit.TravailFichier;

/**
 * @author divanov
 *
 */
public class BackgroundUpdater {

	/**
	 * 
	 */
	public BackgroundUpdater() {
		Runnable r = new Runnable() {
			public void run() {
				while (true) {
					try {
						Thread.sleep(20000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					try {
						IndexWrapper newIndex = new IndexWrapper();
						TravailFichier.createIndexStemming("./corpus/", newIndex);
						MainForestier.index = newIndex;
						System.err.println("index updated");
						MainForestier.index.serializeRoot();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		};

		new Thread(r).start();
	}

}
