package de.htwsaar.stl.winf.abschreibungen.view.fx.main;

import de.htwsaar.stl.winf.abschreibungen.view.fx.controller.FXProperty;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Oliver Avarello
 */
public class MainApp extends Application {

    private Stage primaryStage;
    private AnchorPane rootLayout;

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        initRootLayout();
        showLabelOverView();
    }

    /**
     * Initialisiert das Root Fenster.
     *
     * @throws IOException
     */
    private void initRootLayout() throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(this.getClass().getResource(FXProperty.ROOT_LAYOUT));

            rootLayout = (AnchorPane) loader.load();

            Scene scene = new Scene(rootLayout);

            primaryStage.setTitle(FXProperty.APPLICATION_NAME);
         

            primaryStage.setScene(scene);

            primaryStage.show();

        } catch (IOException ioFail) {            
            ioFail.printStackTrace();
        }
    }

    /**
     *
     * @throws IOException
     */
    private void showLabelOverView() throws IOException {

        FXMLLoader loader = new FXMLLoader();       
        
        loader.setLocation(this.getClass().getResource(FXProperty.MAIN_LAYOUT));  

        GridPane gridpane = (GridPane) loader.load();

        this.rootLayout.setTopAnchor(gridpane,30.0);
        this.rootLayout.setLeftAnchor(gridpane, 15.0);
        this.rootLayout.getChildren().add(gridpane);
    }

    public static void main(String[] args) {
        launch(args);
    }
    

}
