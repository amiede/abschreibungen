package de.htwsaar.stl.winf.abschreibungen.view.fx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import de.htwsaar.stl.winf.abschreibungen.core.*;

public class AbschreibungenSimpelJavaFX extends Application {
    
	public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
    	
    	double anschaffungskosten = 10000.0;
    	int nutzungsdauer = 10;
    	
    	Vermoegensgegenstand maschine = new VermoegensgegenstandImpl(anschaffungskosten, nutzungsdauer);
		
		// LINEAR, GEOMETRISCH_DEGRESSIV, GEOMETRISCH_DEGRESSIV_LINEAR
		maschine.setAbschreibungsverfahren(Vermoegensgegenstand.Abschreibungsverfahren.LINEAR);
		maschine.abschreiben();
		
        primaryStage.setTitle("Abschreibung JavaFX (simpel)");
        Button btn = new Button();
        btn.setText("Abschreiben (" + anschaffungskosten + " EUR, " + nutzungsdauer + " Jahre)" );
		
        StackPane root = new StackPane();
        TextArea textArea = new TextArea();
        root.getChildren().add(textArea);
        root.getChildren().add(btn);
    	

        btn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                //System.out.println("Hello World!");
            	textArea.appendText(maschine.toString());
            }
        });
       
        primaryStage.setScene(new Scene(root, 400, 450));
        primaryStage.show();
    }
}