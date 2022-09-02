/**
 *
 */
package de.stl.saar.core;

import java.util.List;

/**
 * @author miede
 *
 */
public interface Vermoegensgegenstand {

	// Annahmen bzw. steuerrechtliche Vorgaben
	double FACTOR_LINEAR_GEOMETRISCH_DEGRESSIV = 2.0;
			// geometrisch-degressiver Abschreibungssatz ist der doppelte lineare Abschreibungssatz (aber s.u.)
	double MAX_GEOMETRISCH_DEGRESSIV = 20.0; // geometrisch-degressiver Abschreibungssatz darf max. 20% betragen

	int getNutzungsdauer();

	double getAnschaffungskosten();

	List<Abschreibungsjahr> getAbschreibungsJahre();

	void setAbschreibungsJahre(List<Abschreibungsjahr> abschreibungsJahre);

	//
	String toString();

	//
	Abschreibungsverfahren getAbschreibungsverfahren();

	//
	void setAbschreibungsverfahren(Abschreibungsverfahren abschreibungsverfahren);

	enum Abschreibungsverfahren {
		LINEAR, GEOMETRISCH_DEGRESSIV, GEOMETRISCH_DEGRESSIV_LINEAR
	}

}