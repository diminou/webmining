/**
 * 
 */
package fileSysUtils;

import java.io.IOException;

import benoit.HashMapWrapDouble;
import benoit.HashMapWrapper;
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
						HashMapWrapper newHmw = new HashMapWrapper();
						HashMapWrapDouble newHmwd = new HashMapWrapDouble();
						TravailFichier.createIndexStemming("./corpus/", newIndex);
						newHmw.setHM(TravailFichier.createIndexNumberDocs("./corpus/"));
						newHmwd.setHM(TravailFichier.createIndexDocs("./corpus/").getMapDocs());
						
						MainForestier.index = newIndex;
						MainForestier.hmw = newHmw;
						MainForestier.hmwd = newHmwd;
						System.err.println("index updated");
						MainForestier.index.serializeRoot();
						MainForestier.hmw.serializeHM();
						MainForestier.hmwd.serializeHM();
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
