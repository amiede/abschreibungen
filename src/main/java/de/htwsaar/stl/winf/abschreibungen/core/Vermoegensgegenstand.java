/**
 * 
 */
package de.htwsaar.stl.winf.abschreibungen.core;

/**
 * @author miede
 *
 */
public interface Vermoegensgegenstand {

	// Moegliche Abschreibungsverfahren 
	public enum Abschreibungsverfahren {
	    LINEAR, GEOMETRISCH_DEGRESSIV, GEOMETRISCH_DEGRESSIV_LINEAR
	}
		
	// Annahmen bzw. steuerrechtliche Vorgaben	  
	public static final double FACTOR_LINEAR_GEOMETRISCH_DEGRESSIV = 2.0; // geometrisch-degressiver Abschreibungssatz ist der doppelte lineare Abschreibungssatz (aber s.u.)
	public static final double MAX_GEOMETRISCH_DEGRESSIV = 20.0; // geometrisch-degressiver Abschreibungssatz darf max. 20% betragen
		
	//
	public void abschreiben();
	
	//
	public String toString();
	
	//
	public void setAbschreibungsverfahren(Abschreibungsverfahren abschreibungsverfahren);
	
	//
	public Abschreibungsverfahren getAbschreibungsverfahren();
	
}
