/**
 *
 */
package de.stl.saar.core;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author miede
 *
 */
@Getter
@AllArgsConstructor
public class Abschreibungsjahr {

	private final int aktuellesJahr;
	private final double abschreibungssatz;
	private final double abschreibungsbetrag;
	private final double zeitwert;
	private final boolean wechseljahr;

	/**
	 *
	 */
	public String toString() {
		String result = "";
		String aktuellesJahrTemp = Integer.toString(aktuellesJahr);

		// das Wechseljahr soll hervorgehoben werden
		if (wechseljahr) {
			aktuellesJahrTemp = aktuellesJahrTemp + "*";
		}

		result = aktuellesJahrTemp + "\t" + abschreibungssatz + "\t\t" + abschreibungsbetrag + "\t\t\t" + zeitwert;

		return result;
	}

}
