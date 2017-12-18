package de.htwsaar.stl.winf.abschreibungen.view.fx.model;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

/**
 *
 * @author oliver
 */
public class ViewList {

    private ListView listViewJahr;
    private ListView listViewAfaSatz;
    private ListView listViewAfaBetrag;
    private ListView listViewZeitwert;
    
    public void setListViewJahr(ListView ubergabe){
        this.listViewJahr = ubergabe;
    }

    public void setListViewAfaSatz(ListView ubergabe) {
        this.listViewAfaSatz = ubergabe;
    }

    public void setListViewAfaBetrag(ListView ubergabe) {
        this.listViewAfaBetrag = ubergabe;
    }

    public void setListViewZeitwert(ListView ubergabe) {
        this.listViewZeitwert = ubergabe;
    }
    

    public void addListViewJahr(ObservableList obListJahr) {
        this.listViewJahr.setItems(obListJahr);
    }

    public void addListViewAfaSatz(ObservableList obListJahr) {
        this.listViewAfaSatz.setItems(obListJahr);
    }

    public void addListViewBetrag(ObservableList obListJahr) {
        this.listViewAfaBetrag.setItems(obListJahr);
    }

    public void addListViewZeitwert(ObservableList obListJahr) {
        this.listViewZeitwert.setItems(obListJahr);
    }

    public ListView getListViewJahr() {
        return this.listViewJahr;
    }

    public ListView getListViewAfaSatz() {
        return this.listViewAfaSatz;
    }

    public ListView getListViewAfaBetrag() {
        return this.listViewAfaBetrag;
    }

    public ListView getListViewZeitwert() {
        return this.listViewZeitwert;
    }

}
