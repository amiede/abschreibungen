package de.stl.saar.utils;

import atlantafx.base.theme.PrimerLight;
import de.stl.saar.constants.Constants;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ObservableValueBase;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;

import javax.inject.Singleton;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URISyntaxException;
import java.util.function.Function;

@RequiredArgsConstructor
@Singleton
public class Utilities {
	private final Constants constants;

	public void createWindow(String text, Stage primaryStage) {
		int i = text.indexOf("\n");
		String title = text.substring(0, i);


		Label secondLabel = new Label(text);
		secondLabel.setMinWidth(Region.USE_PREF_SIZE);
		ScrollPane root = new ScrollPane();
		root.setContent(secondLabel);

		Scene secondScene = new Scene(root, 230, 100);

		// New window (Stage)
		Stage newWindow = new Stage();
		newWindow.setTitle(title);
		newWindow.initModality(Modality.APPLICATION_MODAL);

		secondScene.getStylesheets().clear();
		secondScene.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());

		secondLabel.setWrapText(true);
		secondLabel.prefWidthProperty().bind(root.widthProperty());
		root.prefWidthProperty().bind(newWindow.widthProperty());


		// Set position of second window, related to primary window.
		newWindow.setX(constants.stage.getX() + 200);
		newWindow.setY(primaryStage.getY() + 100);
		newWindow.setWidth(450);
		newWindow.setHeight(600);

		newWindow.setScene(secondScene);
		newWindow.show();
	}

	/**
	 * Ueberprueft die Laenge des Strings
	 *
	 * @param s der StringBuilder
	 * @return boolscher Ausdruck, ob der String die Laenge 0 hat.
	 */
	public boolean checkStringLoaded(StringBuilder s) {
		return s.length() == 0;
	}

	public String readFile(String filename) throws IOException, URISyntaxException {
		File readFile = new File(getClass().getResource("/texts/" + filename.trim()).toURI().getPath());
		String data = FileUtils.readFileToString(readFile, "UTF-8");

		return data;
	}

	/**
	 * zeigt einen Java FX Alert an
	 *
	 * @param e     die Exception
	 * @param error der zusaetzliche Hinweis als String (wird vor der Exception ausgegeben)
	 */
	public void showErrorDialog(Exception e, String error) {
		double alertDialogSize = 1000;

		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Ein Fehler ist aufgetreten");

		alert.setHeaderText(e.toString());

		//CharacterStream schreibt output in StringBuffer
		StringWriter sw = new StringWriter();

		//PrintWriter: Prints formatted representations of objects to a text-output stream.
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw); //prints this throwable and its backtrace to the specified print stream

		String exceptionmsg = sw.toString();
		TextArea textArea = new TextArea();
		textArea.appendText(error + "\n" + exceptionmsg);

		textArea.setEditable(false);
		textArea.setWrapText(true);

		textArea.setMaxWidth(Double.MAX_VALUE);
		textArea.setMaxHeight(Double.MAX_VALUE);


		Label label = new Label("Der Stacktrace lautet:");

		GridPane expContent = new GridPane();
		GridPane.setVgrow(textArea, Priority.ALWAYS);
		GridPane.setHgrow(textArea, Priority.ALWAYS);

		expContent.setMinWidth(textArea.getWidth());
		expContent.add(label, 0, 0);
		expContent.add(textArea, 0, 1);

		alert.getDialogPane().setExpandableContent(expContent);
		alert.getDialogPane().setMinWidth(alertDialogSize);
		alert.showAndWait();
	}

	/**
	 * Ueberprueft, ob der String bereits geladen wurde, dann wird nur der bereits geladene String zurueckgegeben,
	 * sonst wird er aus der Datei ausgelesen und zurueckgegeben
	 *
	 * @param fileName Der Dateiname
	 * @param content  der StringBuilder der jeweiligen Datei
	 * @return der String mit dem Content
	 */
	public String loadStringFileContent(String fileName, StringBuilder content) {
		if (!checkStringLoaded(content)) {
			return content.toString();
		}
		try {
			content.append(readFile(fileName));
		}
		catch (Exception e) {
			String error = "Der Pfad wurde nicht korrekt auf das Verzeichnis Texte gesetzt: " + constants.FILEPATH;
			showErrorDialog(e, error);
		}
		return content.toString();
	}


	public double round(double unrounded) {
		return Math.round(unrounded * 100d) / 100d; // dirty work-around ;-)
	}

	public <S, T> Callback<TableColumn.CellDataFeatures<S, T>, ObservableValue<T>> createTableCellValueFactory(
			Function<S, T> mapper) {

		return cellDataFeatures -> new ObservableValueBase<>() {
			@Override
			public T getValue() {
				return mapper.apply(cellDataFeatures.getValue());
			}
		};
	}
}
