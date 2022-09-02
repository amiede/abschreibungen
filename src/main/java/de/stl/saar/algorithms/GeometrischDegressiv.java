package de.stl.saar.algorithms;

import de.stl.saar.core.Vermoegensgegenstand;
import lombok.Getter;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
@Getter
public class GeometrischDegressiv implements Algorithm {
	@Inject
	GeometrischDegressivLinear geometrischDegressivLinear;
	private final Vermoegensgegenstand.Abschreibungsverfahren type =
			Vermoegensgegenstand.Abschreibungsverfahren.GEOMETRISCH_DEGRESSIV;
	@Override
	public void calculate(final Vermoegensgegenstand vermoegensgegenstand) {
		geometrischDegressivLinear.calculate(vermoegensgegenstand);
	}
}
