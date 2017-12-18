package de.htw.stl.util.service;

import java.util.List;
import de.htwsaar.stl.winf.abschreibungen.core.VermoegensgegenstandImpl;
import de.htwsaar.stl.winf.abschreibungen.core.Vermoegensgegenstand;

/**
 *
 * @author oliver
 */
public class Verarbeiten {

    private String namen;
    private String[] splitString;

    private final Berechnungen kontrollen;
    private final AbschreibungList list;

    public Verarbeiten() {
        list = new AbschreibungList();
        kontrollen = new Berechnungen();
    }

    public void verarbeiten(final int jahr, final double vermoegen, final String methode) {
        initialisierung(jahr, vermoegen, methode);
        spliting();
        edit();
        addToLists();
    }

    private void initialisierung(int jahr, double vermoegen, String methode) {
        double vermoegenswert = vermoegen;
        int jahre = jahr;

        if (vermoegenswert != 0.0 && jahre != 0) {
            Vermoegensgegenstand maschine = new VermoegensgegenstandImpl(vermoegen, jahr);
            selectedMethod(methode, maschine);
            maschine.abschreiben();
            splitString = maschine.toString().split("\t");
        } else {
            Vermoegensgegenstand maschine = new VermoegensgegenstandImpl(1000.0, 5);
            selectedMethod(methode, maschine);
            maschine.abschreiben();
            splitString = maschine.toString().split("\t");
        }
    }

    private void selectedMethod(String methode, Vermoegensgegenstand maschine) {
        if (isMethodLinear(methode)) {
            setLinear(maschine);
        } else if (isMethodGeoDeg(methode)) {
            setDegresiv(maschine);
        } else if (isMethodGeoDegLinear(methode)) {
            setGeometrischDegresivLinear(maschine);
        } else {
            setLinear(maschine);
        }
    }

    private void spliting() {
        for (int i = 0; i < splitString.length; i++) {
            if (splitString[i].length() != 0) {
                splitString[i] = splitString[i].trim();
                splitString[i] = splitString[i].replace("\n", "--");
            }
        }
    }

    private void edit() {
        for (String split : splitString) {
            if (split.length() != 0) {
                if (!split.contains("--")) {

                    list.addTempList(split.trim());

                } else if (split.contains("--")) {
                    int begin = 0;
                    String temp;
                    String end;
                    temp = split.substring(begin, split.indexOf("-"));
                    end = split.substring(split.lastIndexOf("-") + 1, split.length());                    
                    // Entfernt Markierung fuer Geometrisch degressiv lineares Jahr.
//                    end = end.replace("*", "");
                    list.addTempList(temp.trim());
                    list.addTempList(end.trim());
                }
            }
        }
    }

    protected void addToLists() {
        String content;

        for (int i = 0; i < list.getTempList().size(); i++) {

            content = list.getTempList(i);
            // Entfernt die Textstellen aus der Liste.
            if (content.contains("Zeitwert (EUR)")
                || content.contains("Jahr") 
                    || content.contains("AfA-Satz (%)")
                        || content.contains("AfA-Betrag (EUR)") ){        
            } else if (kontrollen.isJahr(i)) {
                list.addListeJahr(content);
            } else if (kontrollen.isAfaSatz(i)) {
                list.addListeAfaSatz(content);
            } else if (kontrollen.isZeitwert(i)) {
                list.addListeZeitwert(content);
            } else if (kontrollen.isAfaBetrag(i)) {
                list.addListeAfaBetrag(content);
            }
        }
    }

    public String getNamen() {
        return namen;
    }

    private boolean isMethodLinear(String auswahl) {
        String methode = "LINEAR";
        boolean result = auswahl == "methode" && auswahl.length() == methode.length();
        return result;
    }

    private boolean isMethodGeoDeg(String auswahl) {
        String methode = "GEOMETRISCH_DEGRESSIV";
        boolean result = auswahl.contains(methode) && auswahl.length() == methode.length();
        return result;
    }

    private boolean isMethodGeoDegLinear(String auswahl) {
        String methode = "GEOMETRISCH_DEGRESSIV_LINEAR";
        boolean result = auswahl.contains(methode) && auswahl.length() == methode.length();
        return result;
    }

    private void setLinear(Vermoegensgegenstand maschine) {
        maschine.setAbschreibungsverfahren(Vermoegensgegenstand.Abschreibungsverfahren.LINEAR);
    }

    private void setDegresiv(Vermoegensgegenstand maschine) {
        maschine.setAbschreibungsverfahren(Vermoegensgegenstand.Abschreibungsverfahren.GEOMETRISCH_DEGRESSIV);
    }

    private void setGeometrischDegresivLinear(Vermoegensgegenstand maschine) {
        maschine.setAbschreibungsverfahren(Vermoegensgegenstand.Abschreibungsverfahren.GEOMETRISCH_DEGRESSIV_LINEAR);
    }

    public List<String> getListeJahr() {
        return list.getListeJahr();
    }

    public List<String> getListeAfaSatz() {
        return list.getListeAfaSatz();
    }

    public List<String> getListeAfaBetrag() {
        return list.getListeAfaBetrag();
    }

    public List<String> getListeZeitwert() {
        return list.getListeZeitwert();
    }

}
