package de.ur.mi.qsa_tool;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
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
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("StartScreen.fxml"));
			AnchorPane pane = loader.load();

			StartScreenController startScreenController = loader.getController();
			startScreenController.setMain(this);

			Scene scene = new Scene(pane);
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