package de.ur.mi.qsa_tool;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import de.ur.mi.qsa_tool.gui.controller.StartScreenController;

public class Main extends Application {

	private Stage primaryStage;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		mainWindow();
	}

	private void mainWindow() {
		try {
						
			FXMLLoader myLoader = new FXMLLoader(getClass().getResource("StartScreen.fxml"));
			Parent root = (Parent) myLoader.load();
			primaryStage.setTitle("StartScreen");

			StartScreenController controller = (StartScreenController) myLoader.getController();

			if (controller.getPrevStage() == null) {
				controller.setPrevStage(primaryStage);
			}
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add("css/qsa_tool.css");
			primaryStage.setTitle("Quantitative Serienanalyse");
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}