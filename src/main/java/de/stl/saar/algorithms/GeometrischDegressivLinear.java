package de.stl.saar.algorithms;

import de.stl.saar.core.Abschreibungsjahr;
import de.stl.saar.core.Vermoegensgegenstand;
import de.stl.saar.utils.Utilities;
import lombok.Getter;

import javax.inject.Inject;
import javax.inject.Singleton;

import static de.stl.saar.core.Vermoegensgegenstand.FACTOR_LINEAR_GEOMETRISCH_DEGRESSIV;
import static de.stl.saar.core.Vermoegensgegenstand.MAX_GEOMETRISCH_DEGRESSIV;

@Singleton
@Getter
public class GeometrischDegressivLinear implements Algorithm {
	@Inject
	Utilities utilities;
	private final Vermoegensgegenstand.Abschreibungsverfahren type =
			Vermoegensgegenstand.Abschreibungsverfahren.GEOMETRISCH_DEGRESSIV_LINEAR;

	@Override
	public void calculate(final Vermoegensgegenstand vermoegensgegenstand) {
		double afaSatz;
		double afaBetrag;
		double zeitwert;
		int wechseljahr = -1;
		boolean wechseljahrB = false;

		afaSatz = Math.min(MAX_GEOMETRISCH_DEGRESSIV,
				FACTOR_LINEAR_GEOMETRISCH_DEGRESSIV * (100d / (double) vermoegensgegenstand.getNutzungsdauer()));
		afaSatz = utilities.round(afaSatz);
		//System.out.println(afaSatz); // DEBUG
		zeitwert = vermoegensgegenstand.getAnschaffungskosten();

		// Falls wir von geometrisch-degressiv zu linear wechseln wollen, benötigen wir ein geeignetes Wechseljahr
		if (vermoegensgegenstand.getAbschreibungsverfahren() ==
				Vermoegensgegenstand.Abschreibungsverfahren.GEOMETRISCH_DEGRESSIV_LINEAR) {

			wechseljahr = (int) ((vermoegensgegenstand.getNutzungsdauer() + 1) - (100 / afaSatz)); // gemäß "Faustregel"
		}

		// wir berechnen für die gesamte Nutzungsdauer jeweils Abschreibungssatz, Abschreibungsbetrag und den Zeitwert
		for (int jahr = 1; jahr <= vermoegensgegenstand.getNutzungsdauer(); jahr++) {

			// Achtung, wenn wir das Wechseljahr erreicht haben, schreiben wir nur noch linear ab
			if (jahr == wechseljahr) {

				wechseljahrB = true;
				afaSatz = utilities.round(100. / (vermoegensgegenstand.getNutzungsdauer() - wechseljahr + 1));
				afaBetrag = utilities.round(zeitwert * afaSatz / 100d);

				// die verbleibenden (linear abzuschreibenden) Jahre berechnen
				for (int restjahr = jahr; restjahr <= vermoegensgegenstand.getNutzungsdauer(); restjahr++) {

					zeitwert = utilities.round(zeitwert - afaBetrag);

					Abschreibungsjahr
							aktuellesJahr = new Abschreibungsjahr(restjahr, afaSatz, afaBetrag, zeitwert, wechseljahrB);
					vermoegensgegenstand.getAbschreibungsJahre().add(aktuellesJahr);
					wechseljahrB = false; // there can be only one...wechseljahr ;-)
				}

				break; // restliche for-Schleife unten wird nicht mehr ausgeführt
			}

			afaBetrag = utilities.round(afaSatz * zeitwert / 100d);
			zeitwert = utilities.round(zeitwert - afaBetrag);
			Abschreibungsjahr aktuellesJahr = new Abschreibungsjahr(jahr, afaSatz, afaBetrag, zeitwert, wechseljahrB);
			vermoegensgegenstand.getAbschreibungsJahre().add(aktuellesJahr);
		}
	}
}
