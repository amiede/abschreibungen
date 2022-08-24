package de.stl.saar.builders;

import atlantafx.base.theme.PrimerDark;
import atlantafx.base.theme.PrimerLight;
import de.stl.saar.constants.Constants;
import de.stl.saar.utils.Utilities;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
@RequiredArgsConstructor
public class CreateCenterBuilder implements Builder {
	private final Utilities utilities;
	private final StringBuilder license = new StringBuilder();
	private final StringBuilder readme = new StringBuilder();
	private final StringBuilder linearErklaerung = new StringBuilder();
	private final StringBuilder geometrischDegressivErklaerung = new StringBuilder();
	private final StringBuilder linearGeometrischDegressivErklaerung = new StringBuilder();
	private final StringBuilder abschreibungErklaerung = new StringBuilder();
	@Inject
	Constants constants;

	@Override
	public Node build() {
		//MenuBar erstellen
		MenuBar menuBar = new MenuBar();

		// einzelne Menus
		Menu m1 = new Menu("Abschreibung");
		Menu m2 = new Menu("Abschreibungsverfahren");
		Menu m3 = new Menu("About");
		Menu m4 = new Menu("Theme");

		//Abschreibungsverfahren
		MenuItem abschreibungen = new MenuItem("Abschreibung");
		MenuItem linear = new MenuItem("Linear");
		MenuItem geometrischDegressiv = new MenuItem("Geometrisch-Degressiv");
		MenuItem linearGeometrischDegressiv = new MenuItem("Wandelnd");

		//About Items
		MenuItem licensingMenuItem = new MenuItem("Licensing");
		MenuItem readMeMenuItem = new MenuItem("Readme");


		//Theming Mode
		MenuItem lightMode = new MenuItem("Light");
		MenuItem darkMode = new MenuItem("Dark");

		darkMode.setOnAction((e) -> Application.setUserAgentStylesheet(new PrimerDark().getUserAgentStylesheet()));
		lightMode.setOnAction((e) -> Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet()));

		//Menues zu Bar hinzufügen
		menuBar.getMenus().add(m1);
		menuBar.getMenus().add(m2);
		menuBar.getMenus().add(m3);
		menuBar.getMenus().add(m4);

		//Menuitems zum Menuepunkt "Abschreibungsverfahren" hinzufügen
		m2.getItems().add(linear);
		m2.getItems().add(geometrischDegressiv);
		m2.getItems().add(linearGeometrischDegressiv);
		m1.getItems().add(abschreibungen);

		//Menueitems zum Menuepunkt "About" hinzufuegen
		m3.getItems().add(licensingMenuItem);
		m3.getItems().add(readMeMenuItem);

		//MenuItems zum Menuepunkt "Theme" hinzufügen
		m4.getItems().add(lightMode);
		m4.getItems().add(darkMode);

		//Listeners zu den MenuItems
		licensingMenuItem.setOnAction(e -> utilities.createWindow(utilities.loadStringFileContent("LICENSE.txt",
				license), constants.stage));
		readMeMenuItem.setOnAction(e -> utilities.createWindow(utilities.loadStringFileContent("README.md", readme),
				constants.stage));

		linear.setOnAction(e -> utilities.createWindow(utilities.loadStringFileContent("Linear.txt", linearErklaerung),
				constants.stage));
		geometrischDegressiv.setOnAction(e -> utilities.createWindow(utilities.loadStringFileContent(
				"GeometrischDegressiv.txt",
				geometrischDegressivErklaerung), constants.stage));
		linearGeometrischDegressiv.setOnAction(e -> utilities.createWindow(utilities.loadStringFileContent("Wandelnd" +
						".txt",
				linearGeometrischDegressivErklaerung), constants.stage));
		linearGeometrischDegressiv.setOnAction(e -> utilities.createWindow(utilities.loadStringFileContent(
				"GeometrischDegressiv.txt",
				abschreibungErklaerung), constants.stage));

		abschreibungen.setOnAction(e -> utilities.createWindow(utilities.loadStringFileContent("Abschreibungen.txt",
						linearErklaerung),
				constants.stage));
		return menuBar;
	}
}
