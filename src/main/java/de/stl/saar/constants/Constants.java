package de.stl.saar.constants;

import de.stl.saar.core.Vermoegensgegenstand;
import de.stl.saar.core.VermoegensgegenstandImpl;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import javax.inject.Singleton;

@Singleton
@Setter
@Getter
public class Constants {
	public final String LINEAR = "Linear";
	public final String GEOMETRISCH_DEGRESSIV = "Geometrisch-degressiv";
	public final String LINEAR_GEOMETRISCH_DEGRESSIV = "Wandelnd";
	public final String FILEPATH = "texts";

	public Stage stage;

	public Vermoegensgegenstand currentItem = new VermoegensgegenstandImpl(0, 0);
}
