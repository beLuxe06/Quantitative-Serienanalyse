package de.ur.mi.qsa_tool.gui.controller;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import de.ur.mi.qsa_tool.Main;
import de.ur.mi.qsa_tool.model.Corpus;
import de.ur.mi.qsa_tool.model.Script;
import de.ur.mi.qsa_tool.task.FileImportTask;
import de.ur.mi.qsa_tool.util.FileInputChecker;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class StartScreenController {
	
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
    
    private StartScreenListViewController importFileListViewController;
	private ObservableList<String> observableImportList = FXCollections.observableArrayList();
	private HashMap<String, String> filepathAndContentMap = new HashMap<>();
    
	private static Scene resultScene;

	private Stage prevStage;
	
	private static Stage nextStage;
	
    private Main main;
    private Corpus corpus;
    
    private FileInputChecker fileInputChecker;
    
    public void setMain(Main main) {
		this.main = main;
	}
    
    public static Stage getNextStage() {
		return nextStage;
	}

	public static void setNextStage(Stage stage) {
		nextStage = stage;
	}
	
	public static Scene getResultScene() {
		return resultScene;
	}

	public static void setResultScene(Scene scene) {
		resultScene = scene;
	}
    
    @FXML
	public void initialize() {
        validateUIFields();
        initListView();
        initFileInputChecker();
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
	}

	private void initFileInputChecker() {
		fileInputChecker = new FileInputChecker();
	}

	private void initListView() {
		importFileListViewController = new StartScreenListViewController(observableImportList, start_screen_list_view_container);
		importFileListViewController.updateListViewContent();
		addChangeListenerToObservableImportList();
	}
	
	

	private void addChangeListenerToObservableImportList() {
		observableImportList.addListener(new ListChangeListener<String>() {

			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends String> c) {
				cleanFilePathAndContentMap();
			}
		});
		
	}

	@FXML
    void handleInputFileDropped(DragEvent event) {
		System.out.println(event.getDragboard().getFiles());
    	start_screen_insert_filepath_edit.setText(fileInputChecker.getAllPathsAsSingleString(getFilePaths(event.getDragboard().getFiles())));
    }

    
    private void fillListViewWithImportFiles() {
    	String inputFromText = start_screen_insert_filepath_edit.getText();
    	ArrayList<String> filesFromTextField = new ArrayList<>();
    	filesFromTextField.addAll(importFileListViewController.getItems());
    	if(!inputFromText.isEmpty()){
    		 fileInputChecker.updateArrayListFromFilePathsAsString(inputFromText, filesFromTextField);
    	}
    	observableImportList.setAll(filesFromTextField);
    	importFileListViewController.updateListViewContent();
    	processFileContentAndFillHashMap();
    	start_screen_insert_filepath_edit.clear();
	}

	private void processFileContentAndFillHashMap() {
		for(String filepath : observableImportList){
			if(filepathAndContentMap.containsKey(filepath)){
				if(filepathAndContentMap.get(filepath).isEmpty()){
					System.out.println("map contains filepath: " + filepath + " but empty content!");
					filepathAndContentMap.remove(filepath);
					startFileImportTask(filepath);
				}
				else{
					System.out.println("map contains filepath: " + filepath + " with content!");
				}
			}
			else{
				System.out.println("map misses filepath: " + filepath + " and content!");
				startFileImportTask(filepath);
			}
		}
		
	}

	private void cleanFilePathAndContentMap() {
		for(String filepath : filepathAndContentMap.keySet()){
			if(!observableImportList.contains(filepath)){
				filepathAndContentMap.remove(filepath);
			}
		}
		
	}

	private void startFileImportTask(String actualInputFile) {
		FileImportTask importTask = new FileImportTask(actualInputFile);
		System.out.println("Fileimport started of file: " + actualInputFile);
		importTask.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				System.out.println("FileimportTask succeeded of file: " + actualInputFile);
				filepathAndContentMap.put(actualInputFile, importTask.getValue());
			}					
		});
		importTask.setOnFailed(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				System.out.println("FileimportTask failed of file: " + actualInputFile);
			}					
		});
		importTask.setOnCancelled(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				System.out.println("FileimportTask cancelled of file: " + actualInputFile);
			}					
		});
		Thread th = new Thread(importTask);
		th.setDaemon(true);
		th.start();
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
		ArrayList<String> actualSortedFilepaths = new ArrayList<>();
		actualSortedFilepaths.addAll(importFileListViewController.getItems());
		createCorpusFromImportedFiles(actualSortedFilepaths);
		showResultScreen();
    }

	

	private void showResultScreen() {
		nextStage = new Stage();
		nextStage.setTitle("Quantitative Serienanalyse");
		try {
			FXMLLoader myLoader = new FXMLLoader(getClass().getResource("ResultScreen.fxml"));
			Pane root =  myLoader.load();
			ResultScreenController resultController = (ResultScreenController) myLoader.getController();
			resultScene = new Scene(root);
			resultScene.getStylesheets().add("css/qsa_tool.css");
			resultController.setCorpus(corpus);
		} catch (Exception e) {
			e.printStackTrace();
		}

		nextStage.setScene(resultScene);
		
		if (prevStage != null){
			prevStage.close();
		}
		nextStage.show();
	}

	private Corpus createCorpusFromImportedFiles(ArrayList<String> actualSortedFilepaths) {
		ArrayList<Script> scriptList = new ArrayList<>();
		for(String key : actualSortedFilepaths){
			String filePath = key;
			String fileName = getFileNameFromFilePath(filePath);
			String fileContent = filepathAndContentMap.get(key);
			Script script = new Script(fileName, filePath, fileContent);
			scriptList.add(script);
		}
		
		corpus = new Corpus(scriptList);
		return corpus;
	}

	private String getFileNameFromFilePath(String filePath) {
		String fileName = filePath.substring(filePath.lastIndexOf("\\")+1);
		return fileName;
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
