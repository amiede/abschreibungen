package de.stl.saar.algorithms;

import de.stl.saar.core.Abschreibungsjahr;
import de.stl.saar.core.Vermoegensgegenstand;
import de.stl.saar.utils.Utilities;
import lombok.Getter;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
@Getter
public class Linear implements Algorithm {
	@Inject
	Utilities utilities;
	private final Vermoegensgegenstand.Abschreibungsverfahren type =
			Vermoegensgegenstand.Abschreibungsverfahren.LINEAR;

	@Override
	public void calculate(final Vermoegensgegenstand vermoegensgegenstand) {
		double afaSatz;
		double afaBetrag;
		double zeitwert;
		boolean wechseljahrB = false;

		afaSatz = utilities.round(100d / vermoegensgegenstand.getNutzungsdauer());
		afaBetrag = utilities.round(
				vermoegensgegenstand.getAnschaffungskosten() / vermoegensgegenstand.getNutzungsdauer());
		zeitwert = vermoegensgegenstand.getAnschaffungskosten();

		// int aktuellesJahr, double abschreibungssatz, double abschreibungsbetrag, double zeitwert)
		for (int jahr = 1; jahr <= vermoegensgegenstand.getNutzungsdauer(); jahr++) {
			zeitwert = zeitwert - afaBetrag;
			Abschreibungsjahr aktuellesJahr = new Abschreibungsjahr(jahr, afaSatz, afaBetrag, zeitwert, wechseljahrB);
			vermoegensgegenstand.getAbschreibungsJahre().add(aktuellesJahr);
		}
	}
}
