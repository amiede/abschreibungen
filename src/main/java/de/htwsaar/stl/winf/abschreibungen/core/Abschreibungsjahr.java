/**
 * 
 */
package de.htwsaar.stl.winf.abschreibungen.core;

/**
 * @author miede
 * 
 */
public class Abschreibungsjahr {

	private int aktuellesJahr;
	private double abschreibungssatz;
	private double abschreibungsbetrag;
	private double zeitwert;
	private boolean wechseljahr;

	/**
	 * @param aktuellesJahr
	 * @param abschreibungssatz
	 * @param abschreibungsbetrag
	 * @param zeitwert
	 */
	public Abschreibungsjahr ( int aktuellesJahr, double abschreibungssatz, double abschreibungsbetrag, double zeitwert,
			boolean wechseljahr ) {
		// ToDo: Refactoring?
		// https://sourcemaking.com/refactoring/smells/long-parameter-list
		this.aktuellesJahr = aktuellesJahr;
		this.abschreibungssatz = abschreibungssatz;
		this.abschreibungsbetrag = abschreibungsbetrag;
		this.zeitwert = zeitwert;
		this.wechseljahr = wechseljahr;
	}

	/**
	 * 
	 */
	public String toString () {
		String result = new String ();
		String aktuellesJahrTemp = Integer.toString ( aktuellesJahr );

		// das Wechseljahr soll hervorgehoben werden
		if ( wechseljahr ) {
			aktuellesJahrTemp = aktuellesJahrTemp + "*";
		}

		result = aktuellesJahrTemp + "\t" + abschreibungssatz + "\t\t" + abschreibungsbetrag + "\t\t\t" + zeitwert;

		return result;
	}

}
