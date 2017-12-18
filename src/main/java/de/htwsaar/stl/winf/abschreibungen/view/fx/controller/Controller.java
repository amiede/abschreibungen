package de.htwsaar.stl.winf.abschreibungen.view.fx.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Label;
import de.htwsaar.stl.winf.abschreibungen.view.fx.model.ViewList;
import de.htwsaar.stl.winf.abschreibungen.view.fx.configuration.DEConfig;
import de.htwsaar.stl.winf.abschreibungen.view.fx.service.ServiceButton;
import de.htwsaar.stl.winf.abschreibungen.view.fx.helper.View;

/**
 * @author Oliver Avarello
 */
public class Controller {

    private DEConfig config;

    private ServiceButton service;
    private View helperView;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lblJahr;

    @FXML
    private Label lblAfaSatz;

    @FXML
    private Label lblAfaBetrag;

    @FXML
    private Label lblZeitwert;
    @FXML
    private ListView<String> lsvJahr;
    @FXML
    private ListView<String> lsvAfaSatz;
    @FXML
    private ListView<String> lsvAfaBetrag;
    @FXML
    private ListView<String> lsvZeitwert;

    @FXML
    private TextField txtAnschaffungspreis;
    @FXML
    private TextField txtJahre;
    @FXML
    private Label lblJahre;
    @FXML
    private Label lblAnschaffungspreis;

    @FXML
    private Label lblAbschreibungsmethoden;

    @FXML
    private ToggleGroup tgAbschribungsgroup;
    @FXML
    private RadioButton rabLinear;
    @FXML
    private RadioButton rabGeoDeggresiv;
    @FXML
    private RadioButton rabGeoDegLinear;

    @FXML
    private Button btnReset;
    @FXML
    private Button btnBerechnen;

    private int jahr;
    private double betrag;
    private String methode;

    /**
     *
     * @param event loest das Ereignis aus fuer die Ansicht der ListView.
     */
    @FXML
    private void berechnen(ActionEvent event) {
        service = new ServiceButton();
        helperView = new View();
        ViewList list = initzialisiereViewList();
        this.jahr = helperView.inputYear(this.txtJahre);
        this.betrag = helperView.inputAmount(this.txtAnschaffungspreis);
        methode = helperView.selectetMethod(tgAbschribungsgroup);
        service.setEingaben(jahr, betrag, list, methode);
    }

    /**
     * @param event Entfernt alle Eintraege aus der ListView
     */
    @FXML
    private void reset(ActionEvent event) {
        this.lsvJahr.getItems().clear();
        this.lsvAfaSatz.getItems().clear();
        this.lsvZeitwert.getItems().clear();
        this.lsvAfaBetrag.getItems().clear();
    }

    /**
     * @return referenzen zu den ListViews
     */
    private ViewList initzialisiereViewList() {
        ViewList list = new ViewList();
        list.setListViewJahr(this.lsvJahr);
        list.setListViewAfaSatz(this.lsvAfaSatz);
        list.setListViewAfaBetrag(this.lsvAfaBetrag);
        list.setListViewZeitwert(this.lsvZeitwert);
        return list;
    }

    /**
     * Initialisierungsmethode
     */
    @FXML
    private void initialize() {
        setLabeltext();
        assert lsvJahr != null : "fx:id=\"lsvJahr\" was not injected: check your FXML file 'View.fxml'.";
        assert lsvAfaSatz != null : "fx:id=\"lsvAfaSatz\" was not injected: check your FXML file 'View.fxml'.";
        assert lsvAfaBetrag != null : "fx:id=\"lsvAfaBetrag\" was not injected: check your FXML file 'View.fxml'.";
        assert lsvZeitwert != null : "fx:id=\"lsvZeitwert\" was not injected: check your FXML file 'View.fxml'.";
        assert txtAnschaffungspreis != null : "fx:id=\"txtAnschaffungspreis\" was not injected: check your FXML file 'View.fxml'.";
        assert txtJahre != null : "fx:id=\"txtJahre\" was not injected: check your FXML file 'View.fxml'.";
        assert rabLinear != null : "fx:id=\"rabLinear\" was not injected: check your FXML file 'View.fxml'.";
        assert tgAbschribungsgroup != null : "fx:id=\"tgAbschribungsgroup\" was not injected: check your FXML file 'View.fxml'.";
        assert rabGeoDeggresiv != null : "fx:id=\"rabGeoDeggresiv\" was not injected: check your FXML file 'View.fxml'.";
        assert rabGeoDegLinear != null : "fx:id=\"rabGeoDegLinear\" was not injected: check your FXML file 'View.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'View.fxml'.";
        assert btnBerechnen != null : "fx:id=\"btnBerechnen\" was not injected: check your FXML file 'View.fxml'.";
    }

    /**
     * Setzt die Label Texte
     */
    private void setLabeltext() {
        lblJahre.setText(DEConfig.LABEL_JAHR_INPUT);
        lblAnschaffungspreis.setText(DEConfig.LABEL_ANSCHAFUNGSP);
        lblAbschreibungsmethoden.setText(DEConfig.LABEL_ABSCHREIBUNGSMETH);
        lblJahr.setText(DEConfig.LABEL_JAHR_LIST);
        lblAfaSatz.setText(DEConfig.LABEL_AFA_SATZ);
        lblAfaBetrag.setText(DEConfig.LABEL_AFA_BETRAG);
        lblZeitwert.setText(DEConfig.LABEL_ZEITWERT);
        rabLinear.setText(DEConfig.LABEL_LINEAR);
        rabGeoDeggresiv.setText(DEConfig.LABEL_GEOMETRISCH_DEGRESSIV);
        rabGeoDegLinear.setText(DEConfig.LABEL_GEOMETRISCH_DEG_LINEAR);
        rabGeoDegLinear.setWrapText(true);
        rabGeoDeggresiv.setWrapText(true);
    }

}
