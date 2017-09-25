/**
 * 
 */
package de.htwsaar.stl.winf.abschreibungen.view;

import de.htwsaar.stl.winf.abschreibungen.core.*;

/**
 * @author miede
 *
 */
public class AbschreibungenBeispielKonsole {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Vermoegensgegenstand maschine = new VermoegensgegenstandImpl(10000.0,10);
		
		// LINEAR, GEOMETRISCH_DEGRESSIV, GEOMETRISCH_DEGRESSIV_LINEAR
		maschine.setAbschreibungsverfahren(Vermoegensgegenstand.Abschreibungsverfahren.LINEAR);
		maschine.abschreiben();
		String outputString = maschine.toString();
		System.out.println(outputString);
		
	}

}
