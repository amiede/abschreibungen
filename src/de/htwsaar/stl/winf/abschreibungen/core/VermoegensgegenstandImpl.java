/**
 * 
 */
package de.htwsaar.stl.winf.abschreibungen.core;

import java.util.LinkedList;

/**
 * @author miede
 *
 */
public class VermoegensgegenstandImpl implements Vermoegensgegenstand {

	// Kernattribute, bei anderen Abschreibungsverfahren würden hier noch mehr Angaben benötigt werden 
	private double anschaffungskosten;
	private int nutzungsdauer;
	private Abschreibungsverfahren abschreibungsverfahren;
	
	// Die einzelnen Abschreibungsjahre und entsprechenden Werte verwalten wir separat 
	private LinkedList<Abschreibungsjahr> abschreibungsjahre;
	
	/**
	 * @param anschaffungskosten
	 * @param nutzungsdauer
	 */
	public VermoegensgegenstandImpl(double anschaffungskosten, int nutzungsdauer) {
		// ToDo: Ggf. auf Plausibilität prüfen und sonst Exception werfen
		// ...oder direkt unsigned Datentypen verwenden
		this.anschaffungskosten = Math.abs(anschaffungskosten); // nur positive Werte machen Sinn 
		this.nutzungsdauer = Math.abs(nutzungsdauer); // nur positive Werte machen Sinn
		
		abschreibungsverfahren = Abschreibungsverfahren.LINEAR; // Standardverfahren
		
		abschreibungsjahre = new LinkedList<Abschreibungsjahr>();
		// Wir fügen das Anschaffungsjahr in die Liste ein
		Abschreibungsjahr erstesJahr = new Abschreibungsjahr(0,0,0,this.anschaffungskosten,false);
		abschreibungsjahre.add(erstesJahr);	
	}

	// Wir schreiben ab -- abhängig vom Abschreibungsverfahren
	// TODO: In mehrere private Methoden aufteilen:
	// https://sourcemaking.com/refactoring/smells/long-method
	// ToDo: Hier auch noch ein paar JavaDoc-Kommentare einfügen
	public void abschreiben() {
		
		// Absetzung für Abnutzungen (kurz AfA) 
		// ToDo: Überprüfen, ob ein anderer Name nicht mehr Sinn macht (verständlicher ist)
		double afaSatz;
		double afaBetrag;
		double zeitwert;
		int wechseljahr = -1;
		boolean wechseljahrB = false;
		
		switch (abschreibungsverfahren) {

		// Die beiden Verfahren sind fast gleich, unterscheiden sich jedoch durch die Verfahrensänderung nach dem Wechseljahr
		case GEOMETRISCH_DEGRESSIV:
		case GEOMETRISCH_DEGRESSIV_LINEAR:
			afaSatz = Math.min(MAX_GEOMETRISCH_DEGRESSIV, FACTOR_LINEAR_GEOMETRISCH_DEGRESSIV*(100d/(double)nutzungsdauer));
			afaSatz = runden(afaSatz); 
			//System.out.println(afaSatz); // DEBUG
			zeitwert = anschaffungskosten;
			
			// Falls wir von geometrisch-degressiv zu linear wechseln wollen, benötigen wir ein geeignetes Wechseljahr
			if (abschreibungsverfahren == Abschreibungsverfahren.GEOMETRISCH_DEGRESSIV_LINEAR) {
				
				wechseljahr = (int) ((nutzungsdauer + 1) - (100/afaSatz)); // gemäß "Faustregel"
				//System.out.println(wechseljahr); // DEBUG
				
			}
			
			// wir berechnen für die gesamte Nutzungsdauer jeweils Abschreibungssatz, Abschreibungsbetrag und den Zeitwert
			for (int jahr = 1; jahr <= nutzungsdauer; jahr++) {
				
				// Achtung, wenn wir das Wechseljahr erreicht haben, schreiben wir nur noch linear ab
				if (jahr == wechseljahr) {
					
					wechseljahrB = true;
					afaSatz = runden(100 / (nutzungsdauer - wechseljahr +1));
					afaBetrag = runden(zeitwert * afaSatz / 100d);
					
					// die verbleibenden (linear abzuschreibenden) Jahre berechnen
					for (int restjahr = jahr; restjahr <= nutzungsdauer; restjahr++) {
						
						zeitwert = runden(zeitwert - afaBetrag);
						
						Abschreibungsjahr aktuellesJahr = new Abschreibungsjahr(restjahr, afaSatz, afaBetrag, zeitwert, wechseljahrB);
						abschreibungsjahre.add(aktuellesJahr);
						wechseljahrB = false; // there can be only one...wechseljahr ;-)
					}
					
					break; // restliche for-Schleife unten wird nicht mehr ausgeführt
				}
				
				afaBetrag = runden(afaSatz * zeitwert/100d);
				//System.out.println(afaBetrag); // DEBUG
				zeitwert = runden(zeitwert - afaBetrag);
				Abschreibungsjahr aktuellesJahr = new Abschreibungsjahr(jahr, afaSatz, afaBetrag, zeitwert, wechseljahrB);
				abschreibungsjahre.add(aktuellesJahr);
			}
			
			break;
			
		case LINEAR:			
		default:				
			afaSatz = runden((double) 100d/nutzungsdauer);
			afaBetrag = runden((double) anschaffungskosten/nutzungsdauer);
			zeitwert = anschaffungskosten;
			
			// int aktuellesJahr, double abschreibungssatz, double abschreibungsbetrag, double zeitwert)
			for (int jahr = 1; jahr <= nutzungsdauer; jahr++) {
				zeitwert = zeitwert - afaBetrag;
				Abschreibungsjahr aktuellesJahr = new Abschreibungsjahr(jahr, afaSatz, afaBetrag, zeitwert, wechseljahrB);
				abschreibungsjahre.add(aktuellesJahr);
			}
			break;					
		}
		
	}
	
		
	@Override
	public String toString() {
		
		String outputString = new String();
		
		outputString += "Jahr \tAfA-Satz (%) \tAfA-Betrag (EUR) \tZeitwert (EUR)";
		
		for (Abschreibungsjahr jahr: abschreibungsjahre) {
			outputString += "\n" + jahr;
		}	
		
		return outputString;
	}
		
	/**
	 * @return the abschreibungsjahre
	 */
	public LinkedList<Abschreibungsjahr> getAbschreibungsjahre() {
		return abschreibungsjahre;
	}

	/**
	 * @param abschreibungsjahre the abschreibungsjahre to set
	 */
	public void setAbschreibungsjahre(LinkedList<Abschreibungsjahr> abschreibungsjahre) {
		this.abschreibungsjahre = abschreibungsjahre;
	}
	
	@Override
	public void setAbschreibungsverfahren(Abschreibungsverfahren abschreibungsverfahren) {
		this.abschreibungsverfahren = abschreibungsverfahren;
		
	}

	@Override
	public Abschreibungsverfahren getAbschreibungsverfahren() {
		return this.abschreibungsverfahren;
	}

	// Da wir mit Geldwerten rechnen, runden wir die Beträge immer kaufmännisch
	private static double runden(double unrund) {
		double rund = Math.round(unrund *100d )/100d; // dirty work-around ;-)
		return rund;
	}
	
}
