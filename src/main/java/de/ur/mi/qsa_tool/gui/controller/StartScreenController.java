package de.ur.mi.qsa_tool.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import de.ur.mi.qsa_tool.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class StartScreenController {
	
	private Stage prevStage;
	
	@FXML
	private BorderPane pane;
	
    @FXML
    private ResourceBundle resources;
    
    @FXML
    private ImageView start_screen_add_button;

    @FXML
    private URL location;

    @FXML
    private MenuItem start_screen_menu_edit_sub_delete;

    @FXML
    private CheckBox start_screen_checkbox_analyse_stage_directions;

    @FXML
    private Menu start_screen_menu_file;

    @FXML
    private ImageView start_screen_logo;

    @FXML
    private CheckBox start_screen_checkbox_calculate_configuration_matrix;

    @FXML
    private TextField start_screen_insert_filepath_edit;

    @FXML
    private MenuItem start_screen_menu_help_sub_about;

    @FXML
    private ImageView start_screen_search_file;

    @FXML
    private CheckBox start_screen_checkbox_show_person_constellations;

    @FXML
    private Button start_screen_start_analysis_button;

    @FXML
    private TitledPane start_screen_further_settings_accordion;

    @FXML
    private MenuItem start_screen_menu_file_sub_close;

    @FXML
    private ListView<?> start_screen_inserted_files_table_view;

    @FXML
    private CheckBox start_screen_checkbox_analyse_szenes;

    @FXML
    private Menu start_screen_menu_edit;

    @FXML
    private CheckBox start_screen_checkbox_extract_persons;

    @FXML
    private CheckBox start_screen_checkbox_count_words;

    private Main main;
    
    public void setMain(Main main) {
		this.main = main;
	}
    
    @FXML
    void initialize() {
        assert start_screen_menu_edit_sub_delete != null : "fx:id=\"start_screen_menu_edit_sub_delete\" was not injected: check your FXML file 'StartScreen.fxml'.";
        assert start_screen_checkbox_analyse_stage_directions != null : "fx:id=\"start_screen_checkbox_analyse_stage_directions\" was not injected: check your FXML file 'StartScreen.fxml'.";
        assert start_screen_menu_file != null : "fx:id=\"start_screen_menu_file\" was not injected: check your FXML file 'StartScreen.fxml'.";
        assert start_screen_logo != null : "fx:id=\"start_screen_logo\" was not injected: check your FXML file 'StartScreen.fxml'.";
        assert start_screen_checkbox_calculate_configuration_matrix != null : "fx:id=\"start_screen_checkbox_calculate_configuration_matrix\" was not injected: check your FXML file 'StartScreen.fxml'.";
        assert start_screen_insert_filepath_edit != null : "fx:id=\"start_screen_insert_filepath_edit\" was not injected: check your FXML file 'StartScreen.fxml'.";
        assert start_screen_menu_help_sub_about != null : "fx:id=\"start_screen_menu_help_sub_about\" was not injected: check your FXML file 'StartScreen.fxml'.";
        assert start_screen_search_file != null : "fx:id=\"start_screen_search_file\" was not injected: check your FXML file 'StartScreen.fxml'.";
        assert start_screen_checkbox_show_person_constellations != null : "fx:id=\"start_screen_checkbox_show_person_constellations\" was not injected: check your FXML file 'StartScreen.fxml'.";
        assert start_screen_start_analysis_button != null : "fx:id=\"start_screen_start_analysis_button\" was not injected: check your FXML file 'StartScreen.fxml'.";
        assert start_screen_further_settings_accordion != null : "fx:id=\"start_screen_further_settings_accordion\" was not injected: check your FXML file 'StartScreen.fxml'.";
        assert start_screen_menu_file_sub_close != null : "fx:id=\"start_screen_menu_file_sub_close\" was not injected: check your FXML file 'StartScreen.fxml'.";
        assert start_screen_inserted_files_table_view != null : "fx:id=\"start_screen_inserted_files_table_view\" was not injected: check your FXML file 'StartScreen.fxml'.";
        assert start_screen_checkbox_analyse_szenes != null : "fx:id=\"start_screen_checkbox_analyse_szenes\" was not injected: check your FXML file 'StartScreen.fxml'.";
        assert start_screen_menu_edit != null : "fx:id=\"start_screen_menu_edit\" was not injected: check your FXML file 'StartScreen.fxml'.";
        assert start_screen_checkbox_extract_persons != null : "fx:id=\"start_screen_checkbox_extract_persons\" was not injected: check your FXML file 'StartScreen.fxml'.";
        assert start_screen_checkbox_count_words != null : "fx:id=\"start_screen_checkbox_count_words\" was not injected: check your FXML file 'StartScreen.fxml'.";

    }
    
    @FXML
    void handleInputFileDropped(ActionEvent event) {
    	
    }

    @FXML
    void handleInputFileDragOver(ActionEvent event) {

    }
    
    @FXML
    void addFile(ActionEvent event) {

    }
    
    @FXML
    void openFileFromSystem(ActionEvent event) {

    }

    @FXML
    void onStartScreenFilepathInserted(ActionEvent event) {

    }

    @FXML
    void startAnalysis(ActionEvent event) {

    }

    @FXML
    void extractPersons(ActionEvent event) {

    }

    @FXML
    void analyseSzenes(ActionEvent event) {

    }

    @FXML
    void analyseStageDirections(ActionEvent event) {

    }

    @FXML
    void calculateConfigurationMatrix(ActionEvent event) {

    }

    @FXML
    void countWords(ActionEvent event) {

    }

    @FXML
    void showPersonConstellations(ActionEvent event) {

    }

    
    public void setPrevStage(Stage prevStage) {
		this.prevStage = prevStage;
	}

	public Stage getPrevStage() {
		return this.prevStage;
	}
    
}
