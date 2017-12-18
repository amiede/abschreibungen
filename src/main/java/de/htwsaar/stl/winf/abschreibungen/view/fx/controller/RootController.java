package de.htwsaar.stl.winf.abschreibungen.view.fx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

/**
 * @author Oliver Avarello
 */
public class RootController {
    
    @FXML
    private MenuItem Close;

    @FXML
    private MenuItem Help;
    
    @FXML
    private void closeWindow(ActionEvent event) {
        windowExit();
    }
    
    /**
     * windowExit Schliesst die fenster
     */
    private void windowExit(){
        System.exit(0);
    }
    
}
