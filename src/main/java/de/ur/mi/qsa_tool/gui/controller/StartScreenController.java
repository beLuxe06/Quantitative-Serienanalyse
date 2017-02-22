package de.ur.mi.qsa_tool.gui.controller;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import de.ur.mi.qsa_tool.Main;
import de.ur.mi.qsa_tool.util.FileInputChecker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class StartScreenController {
	
	private Stage prevStage;
	
	@FXML
	private BorderPane pane;
	
	@FXML
	private HBox start_screen_list_view_container;
	
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
    private CheckBox start_screen_checkbox_analyse_szenes;

    @FXML
    private Menu start_screen_menu_edit;

    @FXML
    private CheckBox start_screen_checkbox_extract_persons;

    @FXML
    private CheckBox start_screen_checkbox_count_words;
    
    private ListViewController importFileListViewController;
	ObservableList<String> observableImportList = FXCollections.observableArrayList();
    
    private Main main;
    private ArrayList<String> filepaths = new ArrayList<>();
    
    private FileInputChecker fileInputChecker;
    
    public void setMain(Main main) {
		this.main = main;
	}
    
    @FXML
	public void initialize() {
        validateUIFields();
    }
	
	private void updateObservableImportList(){
		observableImportList.setAll(filepaths);
		System.out.println("observableList: " + observableImportList.toString());
	}

	private void validateUIFields() {
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
        assert start_screen_checkbox_analyse_szenes != null : "fx:id=\"start_screen_checkbox_analyse_szenes\" was not injected: check your FXML file 'StartScreen.fxml'.";
        assert start_screen_menu_edit != null : "fx:id=\"start_screen_menu_edit\" was not injected: check your FXML file 'StartScreen.fxml'.";
        assert start_screen_checkbox_extract_persons != null : "fx:id=\"start_screen_checkbox_extract_persons\" was not injected: check your FXML file 'StartScreen.fxml'.";
        assert start_screen_checkbox_count_words != null : "fx:id=\"start_screen_checkbox_count_words\" was not injected: check your FXML file 'StartScreen.fxml'.";
        initListView();
        initFileInputChecker();
			}

	private void initFileInputChecker() {
		fileInputChecker = new FileInputChecker();
	}

	private void initListView() {
		importFileListViewController = new ListViewController(observableImportList, start_screen_list_view_container);
		importFileListViewController.updateListViewContent();
	}
	
	

	@FXML
    void handleInputFileDropped(DragEvent event) {
		System.out.println(event.getDragboard().getFiles());
    	start_screen_insert_filepath_edit.setText(fileInputChecker.getAllPathsAsSingleString(getFilePaths(event.getDragboard().getFiles())));
    }

    
    private void fillListViewWithImportFiles() {
    	ArrayList<String> actualInputFiles = new ArrayList<>();
    	actualInputFiles.addAll(importFileListViewController.getItems());
    	fileInputChecker.updateArrayListFromFilePathsAsString(start_screen_insert_filepath_edit.getText(), actualInputFiles);
    	System.out.println("Files to add to ListView: " + actualInputFiles);
    	observableImportList.setAll(actualInputFiles);
    	start_screen_insert_filepath_edit.clear();
	}

	private ArrayList<String> getFilePaths(List<File> files) {
    	ArrayList<String> filepaths = new ArrayList<>();
		for(File file: files){
    		if(!filepaths.contains(file.getAbsolutePath())){
    			filepaths.add(file.getAbsolutePath());
    		}
		}
		System.out.println("FileList received: " + filepaths.toString());
		return filepaths;
	}

	@FXML
    void handleInputFileDragOver(DragEvent event) {
    	if(event.getDragboard().hasFiles()){
    		event.acceptTransferModes(TransferMode.ANY);
    	} 	
    }
    
    @FXML
    void openFileFromSystem(MouseEvent event) {
    	start_screen_insert_filepath_edit.setText(fileInputChecker.getAllPathsAsSingleString(getFilePathsFromSystemExplorer()));
    }

    private ArrayList<String> getFilePathsFromSystemExplorer() {
    	FileChooser fileChooser = new FileChooser();
    	configureFileChooser(fileChooser);
    	List<File> fileList = fileChooser.showOpenMultipleDialog(getPrevStage());
    	ArrayList<String> actualInputFiles = new ArrayList<>();
        if (fileList != null) {
                for (File file : fileList) {
                    actualInputFiles.add(file.getAbsolutePath());
                }
            }
        return actualInputFiles;
	}

	private void configureFileChooser(FileChooser fileChooser) {
		fileChooser.setTitle("Import Files");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));                 
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("txt", "*.txt"));
	}

	@FXML
    void onAddButtonClicked(MouseEvent event){
    	fillListViewWithImportFiles();
    }
    
    @FXML
    void onStartScreenFilepathInserted(ActionEvent event) {
    	fillListViewWithImportFiles();
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
