package de.stl.saar.view;

import atlantafx.base.theme.PrimerDark;
import atlantafx.base.theme.PrimerLight;
import de.stl.saar.builders.CreateCenterBuilder;
import de.stl.saar.builders.TabPaneBuilder;
import de.stl.saar.conf.StartupScene;
import de.stl.saar.constants.Constants;
import de.stl.saar.utils.PropertiesUtils;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.io.IOException;

@Dependent
@RequiredArgsConstructor
public class App {
	private final VBox root = new VBox();
	private final Scene scene = new Scene(root, 850, 550);
	@Inject
	Constants constants;
	@Inject
	PropertiesUtils propertiesUtils;
	@Inject
	CreateCenterBuilder createCenterBuilder;
	@Inject
	TabPaneBuilder tabPaneBuilder;

	public void start(@Observes @StartupScene Stage stage) throws IOException {
		propertiesUtils.loadProperties();
		constants.setStage(stage);
		root.setMinHeight(scene.getHeight());
		root.setMinWidth(scene.getWidth());
		stage.setTitle("Abschreibung JavaFX (simpel)");
		adaptTheme();

		final Node node = createCenterBuilder.build();
		root.getChildren().add(node);
		root.getChildren().add(tabPaneBuilder.build(root));
		stage.setScene(scene);
		stage.show();
	}


	private void adaptTheme() {
		if (propertiesUtils.getTheme().equals("dark")) {
			Application.setUserAgentStylesheet(new PrimerDark().getUserAgentStylesheet());
		}
		else {
			Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());
		}
	}
}
