package de.stl.saar.algorithms;

import de.stl.saar.core.Vermoegensgegenstand;

public interface Algorithm {
	void calculate(Vermoegensgegenstand vermoegensgegenstand);
	Vermoegensgegenstand.Abschreibungsverfahren getType();
}
