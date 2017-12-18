/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htw.stl.util.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author oliver
 */
public class AbschreibungList {

    private final List<String> tempList;
    private final List<String> listeJahr;
    private final List<String> listeAfaSatz;
    private final List<String> listeAfaBetrag;
    private final List<String> listeZeitwert;

    public AbschreibungList() {
        tempList = new ArrayList<>();
        listeJahr = new ArrayList<>();
        listeAfaSatz = new ArrayList<>();
        listeAfaBetrag = new ArrayList<>();
        listeZeitwert = new ArrayList<>();
    }

    /**
     * getTempList
     *
     * @param position Position stelle
     * @return tempWert Der an der Position stelle steht
     */
    public String getTempList(int position) {
        return tempList.get(position);
    }

    /**
     * getTempList
     *
     * @return temporaere Liste
     */
    public List<String> getTempList() {
        return Collections.unmodifiableList(tempList);
    }

    /**
     * getListeJahr
     *
     * @return Liste mit Jahres Werten.
     */
    public List<String> getListeJahr() {
        return Collections.unmodifiableList(listeJahr);
    }

    /**
     * getListeAfaSatz
     *
     * @return Liste mit AfA Satz Werten
     */
    public List<String> getListeAfaSatz() {
        return Collections.unmodifiableList(listeAfaSatz);
    }

    /**
     * getListeAfaBetrag
     *
     * @return Liste mit AfA Betrag Werten
     */
    public List<String> getListeAfaBetrag() {
        return Collections.unmodifiableList(listeAfaBetrag);
    }

    /**
     * getListeZeitwert
     *
     * @return Liste mit Zeitwert Werten
     */
    public List<String> getListeZeitwert() {
        return Collections.unmodifiableList(listeZeitwert);
    }

    /**
     * addTempList
     *
     * @param content wert der hinzugefuegt wird zur TempListe
     */
    public void addTempList(String content) {
        this.tempList.add(content);
    }

    /**
     * addListeJahr
     *
     * @param content wert der hinzugefuegt wird zur Jahr Liste
     */
    public void addListeJahr(String content) {
        this.listeJahr.add(content);
    }

    /**
     * addListeAfaSatz
     *
     * @param content wert der hinzugefuegt wird zur AfASatz Liste
     */
    public void addListeAfaSatz(String content) {
        this.listeAfaSatz.add(content);
    }

    /**
     * addListeAfaBetrag
     *
     * @param content wert der hinzugefuegt wird zur AfA betrag Liste
     */
    public void addListeAfaBetrag(String content) {
        this.listeAfaBetrag.add(content);
    }

    /**
     * addListeZeitwert
     *
     * @param content wert der hinzugefuegt wird zur zeitwert Liste
     */
    public void addListeZeitwert(String content) {
        this.listeZeitwert.add(content);
    }

}
