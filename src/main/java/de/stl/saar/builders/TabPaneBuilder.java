package de.stl.saar.builders;

import de.stl.saar.algorithms.GeometrischDegressiv;
import de.stl.saar.algorithms.GeometrischDegressivLinear;
import de.stl.saar.algorithms.Linear;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class TabPaneBuilder {
	@Inject
	TabContent tabContent;
	@Inject
	GeometrischDegressiv geometrischDegressiv;
	@Inject
	GeometrischDegressivLinear geometrischDegressivLinear;
	@Inject
	Linear linear;

	public Node build(final VBox borderPane) {
		Tab geometrischTab = new Tab("Geometrisch");
		Tab linearTab = new Tab("Linear");
		Tab linearGeometrischTab = new Tab("Linear Geometrisch");

		geometrischTab.setContent(tabContent.build(geometrischDegressiv));
		linearGeometrischTab.setContent(tabContent.build(geometrischDegressivLinear));
		linearTab.setContent(tabContent.build(linear));

		final TabPane tabPane = new TabPane(geometrischTab, linearTab, linearGeometrischTab);
		tabPane.setSide(Side.LEFT);
		tabPane.tabMinWidthProperty().bind(borderPane.heightProperty().divide(tabPane.getTabs().size()).subtract(27));

		return tabPane;
	}
}
