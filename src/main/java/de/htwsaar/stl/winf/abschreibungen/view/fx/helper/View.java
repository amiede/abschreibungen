
package de.htwsaar.stl.winf.abschreibungen.view.fx.helper;

import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 *
 * @author oliver
 */
public class View {
    
    
    
    
        /**
     * @param choseButton Ausgewaehlte Abschreibungsmethode
     * @return Gewaehlte Abschreibungsmethode Formatiert
     */
    private String getChosenMethod(RadioButton choseButton) {
        String trennzeichen="\n";
        String ersetungszeichen="_";
        String result = String.valueOf(choseButton.getText()).toUpperCase();
        result = result.replaceAll(trennzeichen, ersetungszeichen);
        return result;
    }
    
        /**
     * @return ausgewaehlte Abschreibungsmethode aus der togglegroup.
     */
    private RadioButton rdbIsSelected(ToggleGroup group) {
        RadioButton selectetRadioButton = (RadioButton) group.getSelectedToggle();
        return selectetRadioButton;
    }
    
    
    public String selectetMethod(ToggleGroup group){        
        return String.valueOf(getChosenMethod(rdbIsSelected(group)));
    }
    
    
    
    
    
    /**
     * @return Nutzungsdauer
     */
    public int inputYear(TextField txtJahre) {
        final int eingabe = Integer.parseInt(txtJahre.getText());
        return eingabe;
    }

    /**
     * @return Anschaffungspreis
     */
    public double inputAmount(TextField txtAnschaffungspreis) {
        final int eingabe = Integer.parseInt(txtAnschaffungspreis.getText());
        return eingabe;
    }
}
