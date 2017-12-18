package de.htwsaar.stl.winf.abschreibungen.view.fx.service;

import de.htw.stl.util.service.Verarbeiten;
import de.htwsaar.stl.winf.abschreibungen.view.fx.model.ViewList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Oliver Avarello
 */
public class ServiceButton {

    private Verarbeiten auswertung;

    public void setEingaben(final int jahr, final double betrag, final ViewList lists, final String methode) {
        auswertung = new Verarbeiten();
        auswertung.verarbeiten(jahr, betrag, methode);
        lists.addListViewJahr(obListener(auswertung.getListeJahr()));
        lists.addListViewAfaSatz(obListener(auswertung.getListeAfaSatz()));
        lists.addListViewBetrag(obListener(auswertung.getListeAfaBetrag()));
        lists.addListViewZeitwert(obListener(auswertung.getListeZeitwert()));
    }

    private ObservableList<String> obListener(List<String> listeJahr) {
        ObservableList<String> listener = FXCollections.observableArrayList();
        listener.addAll(listeJahr);
        return listener;
    }

}
