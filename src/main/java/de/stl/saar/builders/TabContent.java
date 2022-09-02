package de.stl.saar.builders;

import de.stl.saar.algorithms.Algorithm;
import de.stl.saar.constants.Constants;
import de.stl.saar.core.Abschreibungsjahr;
import de.stl.saar.core.VermoegensgegenstandImpl;
import de.stl.saar.utils.JavaFXUtils;
import de.stl.saar.utils.Utilities;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class TabContent implements TabBuilder {
	@Inject
	Utilities utilities;
	@Inject
	Constants constants;
	@Inject
	JavaFXUtils javaFXUtils;

	@Override
	public Node build(final Algorithm algorithm) {
		VBox vBox = new VBox();
		GridPane gridPane = new GridPane();
		final Button button = new Button("Abschreiben");

		final TextField anschaffungskostenField = new TextField();
		final TextField nutzungsdauerField = new TextField();
		javaFXUtils.makeTextFieldToOnlyAllowNumbers(anschaffungskostenField);
		javaFXUtils.makeTextFieldToOnlyAllowNumbers(nutzungsdauerField);


		gridPane.add(anschaffungskostenField, 0, 0, 1, 1);
		gridPane.add(nutzungsdauerField, 1, 0, 1, 1);
		gridPane.add(button, 2, 0, 1, 1);
		gridPane.setHgap(10);
		gridPane.setVgap(20);
		VBox.setMargin(gridPane, new Insets(20, 0, 20, 20));
		vBox.getChildren().add(gridPane);

		final TableView<Abschreibungsjahr> tableView = new TableView<>();
		TableColumn<Abschreibungsjahr, Integer> currentYearCol = new TableColumn<>("Aktuelles Jahr");
		TableColumn<Abschreibungsjahr, Double> abschreibungsSatzCol = new TableColumn<>("Abschreibungssatz");
		TableColumn<Abschreibungsjahr, Double> zeitwertCol = new TableColumn<>("Zeitwert");
		TableColumn<Abschreibungsjahr, Double> abschreibungsBetragCol = new TableColumn<>("Abschreibungsbetrag");
		TableColumn<Abschreibungsjahr, String> wechselJahrCol = new TableColumn<>("Wechseljahr?");

		tableView.getColumns()
		         .addAll(currentYearCol, abschreibungsSatzCol, zeitwertCol, abschreibungsBetragCol, wechselJahrCol);

		initColumns(tableView, currentYearCol, abschreibungsSatzCol, zeitwertCol, abschreibungsBetragCol,
				wechselJahrCol);

		tableView.setItems(FXCollections.observableList(constants.getCurrentItem().getAbschreibungsJahre()));
		vBox.getChildren().add(tableView);

		initButton(algorithm, button, anschaffungskostenField, nutzungsdauerField, tableView);

		return vBox;
	}

	private void initColumns(final TableView<Abschreibungsjahr> tableView,
	                         final TableColumn<Abschreibungsjahr, Integer> currentYear,
	                         final TableColumn<Abschreibungsjahr, Double> abschreibungsSatz,
	                         final TableColumn<Abschreibungsjahr, Double> zeitwert,
	                         final TableColumn<Abschreibungsjahr, Double> abschreibungsCol,
	                         final TableColumn<Abschreibungsjahr, String> wechselJahrCol) {
		currentYear.prefWidthProperty().bind(tableView.widthProperty().divide(5).subtract(1.5));
		abschreibungsSatz.prefWidthProperty().bind(tableView.widthProperty().divide(5).subtract(1.5));
		zeitwert.prefWidthProperty().bind(tableView.widthProperty().divide(5).subtract(1.5));
		abschreibungsCol.prefWidthProperty().bind(tableView.widthProperty().divide(5).subtract(1.5));
		wechselJahrCol.prefWidthProperty().bind(tableView.widthProperty().divide(5).subtract(1.5));

		currentYear.setCellValueFactory(utilities.createTableCellValueFactory(Abschreibungsjahr::getAktuellesJahr));
		abschreibungsSatz.setCellValueFactory(
				utilities.createTableCellValueFactory(Abschreibungsjahr::getAbschreibungssatz));
		zeitwert.setCellValueFactory(utilities.createTableCellValueFactory(Abschreibungsjahr::getZeitwert));
		abschreibungsCol.setCellValueFactory(
				utilities.createTableCellValueFactory(Abschreibungsjahr::getAbschreibungsbetrag));

		wechselJahrCol.setCellValueFactory(utilities.createTableCellValueFactory(
				(abschreibungsjahr) -> abschreibungsjahr.isWechseljahr() ? "Ja" : "Nein"));

	}

	private void initButton(final Algorithm algorithm, final Button button, final TextField anschaffungskostenField,
	                        final TextField nutzungsdauerField, final TableView<Abschreibungsjahr> tableView) {
		button.setPadding(new Insets(5));
		button.setOnAction((actionEvent -> {
			constants.currentItem = new VermoegensgegenstandImpl(Double.parseDouble(anschaffungskostenField.getText()),
					Integer.parseInt(nutzungsdauerField.getText()));
			constants.currentItem.setAbschreibungsverfahren(algorithm.getType());
			algorithm.calculate(constants.currentItem);

			tableView.setItems(FXCollections.observableList(constants.getCurrentItem().getAbschreibungsJahre()));
		}));
	}
}
