/**
 *
 */
package de.stl.saar.core;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

/**
 * @author miede
 *
 */
@Getter
@Setter
public class VermoegensgegenstandImpl implements Vermoegensgegenstand {

	// Kernattribute, bei anderen Abschreibungsverfahren w�rden hier noch mehr Angaben ben�tigt werden 
	private final double anschaffungskosten;
	private final int nutzungsdauer;
	private Abschreibungsverfahren abschreibungsverfahren;

	// Die einzelnen Abschreibungsjahre und entsprechenden Werte verwalten wir separat 
	private List<Abschreibungsjahr> abschreibungsJahre;

	/**
	 * @param anschaffungskosten
	 * @param nutzungsdauer
	 */
	public VermoegensgegenstandImpl(double anschaffungskosten, int nutzungsdauer) {
		// ...oder direkt unsigned Datentypen verwenden
		this.anschaffungskosten = Math.abs(anschaffungskosten); // nur positive Werte machen Sinn 
		this.nutzungsdauer = Math.abs(nutzungsdauer); // nur positive Werte machen Sinn

		abschreibungsverfahren = Abschreibungsverfahren.LINEAR; // Standardverfahren

		abschreibungsJahre = new LinkedList<Abschreibungsjahr>();
		// Wir f�gen das Anschaffungsjahr in die Liste ein
		Abschreibungsjahr erstesJahr = new Abschreibungsjahr(0, 0, 0, this.anschaffungskosten, false);
		abschreibungsJahre.add(erstesJahr);
	}

	// Da wir mit Geldwerten rechnen, runden wir die Betr�ge immer kaufm�nnisch
	private static double runden(double unrund) {
		double rund = Math.round(unrund * 100d) / 100d; // dirty work-around ;-)
		return rund;
	}

	@Override
	public String toString() {

		String outputString = "";

		outputString += "Jahr \tAfA-Satz (%) \tAfA-Betrag (EUR) \tZeitwert (EUR)";

		for (Abschreibungsjahr jahr : abschreibungsJahre) {
			outputString += "\n" + jahr;
		}

		return outputString;
	}

	@Override
	public Abschreibungsverfahren getAbschreibungsverfahren() {
		return this.abschreibungsverfahren;
	}

	@Override
	public void setAbschreibungsverfahren(Abschreibungsverfahren abschreibungsverfahren) {
		this.abschreibungsverfahren = abschreibungsverfahren;

	}

}