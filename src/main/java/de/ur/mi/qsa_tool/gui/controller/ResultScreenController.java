package de.ur.mi.qsa_tool.gui.controller;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import de.ur.mi.qsa_tool.model.Corpus;
import de.ur.mi.qsa_tool.model.Data;
import de.ur.mi.qsa_tool.model.Stats;
import de.ur.mi.qsa_tool.service.RawDataGeneratorService;
import de.ur.mi.qsa_tool.service.StatsGeneratorService;
import de.ur.mi.qsa_tool.service.FileImportService;
import de.ur.mi.qsa_tool.service.FineDataGeneratorService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class ResultScreenController {

	@FXML
	private AnchorPane result_screen_tab_anchor_pane;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Tab result_screen_tab_person_constellations;
    
    @FXML
    private AnchorPane result_screen_anchor_person_constellations;
    
    @FXML
    private MenuItem result_screen_menu_file_submenu_close;

    @FXML
    private Menu result_screen_menu_help;

    @FXML
    private Menu result_screen_menu_edit;

    @FXML
    private Tab result_screen_tab_configuration_matrix;
    
    @FXML
    private AnchorPane result_screen_anchor_configuration_matrix;

    @FXML
    private MenuItem result_screen_menu_help_submenu_about;

    @FXML
    private Tab result_screen_tab_word_counts;
    
    @FXML
    private AnchorPane result_screen_anchor_word_counts;

    @FXML
    private Tab result_screen_tab_timeline;
    
    @FXML
    private AnchorPane result_screen_anchor_time_line;

    @FXML
    private MenuItem result_screen_menu_edit_submenu_delete;

    @FXML
    private TabPane result_screen_tab_pane;

    @FXML
    private Menu result_screen_menu_file;
    
    private ObservableList<String> observableConfigurationMatrixDataSubtitles = FXCollections.observableArrayList();
    private ObservableList<String> observableWordCountsDataSubtitles = FXCollections.observableArrayList();
    private ObservableList<String> observablePersonConstellationsDataSubtitles = FXCollections.observableArrayList();
    private ObservableList<String> observableTimeLineDataSubtitles = FXCollections.observableArrayList();
    private ObservableList<String> fileNames = FXCollections.observableArrayList();

    private Tab actualSelectedTab;
    private HashMap<String, String> filepathAndContentMap = new HashMap<>();
    private Corpus corpus;
    private Data data;
    private Stats stats;


    @FXML
    void initialize() {
    	validateUIFields();
    }

	
	
	private void setData(){
		data = new Data();
		data.setCorpus(corpus);
		fileNames.addAll(corpus.getFileNames());
		processMissingData();
    }
	
	public void setCorpus(Corpus corpus) {
		this.corpus = corpus;
		setData();
	}
	
	private void processMissingData() {
		startRawDataGeneratorTask();
		
	}

	private void startRawDataGeneratorTask() {
		//Service<NewData> rawDataGeneratorService = new RawDataGeneratorService(data);
		Service<Data> rawDataGeneratorService = new RawDataGeneratorService(data);
		rawDataGeneratorService.start();
		System.out.println("raw data processing started!");
		rawDataGeneratorService.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				System.out.println("raw data processed!");
				data = rawDataGeneratorService.getValue();
				System.out.println(data.toString());
				startFineDataGeneratorTask();
			}					
		});
		
		rawDataGeneratorService.setOnFailed(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				System.out.println("raw data processing failed!");
				System.out.println(data.toString());
			}					
		});
		rawDataGeneratorService.setOnCancelled(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				System.out.println("raw data processing cancelled!");
				System.out.println(data.toString());
			}					
		});
	}

	private void startFineDataGeneratorTask(){
		Service<Data> fineDataGeneratorService = new FineDataGeneratorService(data);
		fineDataGeneratorService.start();
		System.out.println("fine data processing started!");
		fineDataGeneratorService.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				System.out.println("fine data processed!");
				data = fineDataGeneratorService.getValue();
				System.out.println(data.toString());
				startStatsGeneratorTask();
			}					
		});
		
		fineDataGeneratorService.setOnFailed(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				System.out.println("fine data processing failed!");
				System.out.println(data.toString());
			}					
		});
		fineDataGeneratorService.setOnCancelled(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				System.out.println("fine data processing cancelled!");
				System.out.println(data.toString());
			}					
		});
	}
	

	private void startStatsGeneratorTask() {
		Service<Stats> statsGeneratorService = new StatsGeneratorService(data);
		statsGeneratorService.start();
		System.out.println("stats processing started!");
		statsGeneratorService.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				System.out.println("stats processed!");
				stats = statsGeneratorService.getValue();
				System.out.println(stats.toString());
				//updateUIwithStats();
			}					
		});
		
		statsGeneratorService.setOnFailed(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				System.out.println("stats processing failed!");
				System.out.println(stats.toString());
			}					
		});
		statsGeneratorService.setOnCancelled(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				System.out.println("stats processing cancelled!");
				System.out.println(stats.toString());
			}					
		});
		
	}


	private void validateUIFields() {
		assert result_screen_tab_person_constellations != null : "fx:id=\"result_screen_tab_person_constellations\" was not injected: check your FXML file 'ResultScreen.fxml'.";
        assert result_screen_tab_anchor_pane != null : "fx:id=\"result_screen_tab_anchor_pane\" was not injected: check your FXML file 'ResultScreen.fxml'.";
        assert result_screen_menu_file_submenu_close != null : "fx:id=\"result_screen_menu_file_submenu_close\" was not injected: check your FXML file 'ResultScreen.fxml'.";
        assert result_screen_anchor_word_counts != null : "fx:id=\"result_screen_anchor_word_counts\" was not injected: check your FXML file 'ResultScreen.fxml'.";
        assert result_screen_menu_help != null : "fx:id=\"result_screen_menu_help\" was not injected: check your FXML file 'ResultScreen.fxml'.";
        assert result_screen_menu_edit != null : "fx:id=\"result_screen_menu_edit\" was not injected: check your FXML file 'ResultScreen.fxml'.";
        assert result_screen_tab_configuration_matrix != null : "fx:id=\"result_screen_tab_configuration_matrix\" was not injected: check your FXML file 'ResultScreen.fxml'.";
        assert result_screen_anchor_time_line != null : "fx:id=\"result_screen_anchor_time_line\" was not injected: check your FXML file 'ResultScreen.fxml'.";
        assert result_screen_anchor_person_constellations != null : "fx:id=\"result_screen_anchor_person_constellations\" was not injected: check your FXML file 'ResultScreen.fxml'.";
        assert result_screen_menu_help_submenu_about != null : "fx:id=\"result_screen_menu_help_submenu_about\" was not injected: check your FXML file 'ResultScreen.fxml'.";
        assert result_screen_tab_word_counts != null : "fx:id=\"result_screen_tab_word_counts\" was not injected: check your FXML file 'ResultScreen.fxml'.";
        assert result_screen_menu_edit_submenu_delete != null : "fx:id=\"result_screen_menu_edit_submenu_delete\" was not injected: check your FXML file 'ResultScreen.fxml'.";
        assert result_screen_tab_pane != null : "fx:id=\"result_screen_tab_pane\" was not injected: check your FXML file 'ResultScreen.fxml'.";
        assert result_screen_tab_timeline != null : "fx:id=\"result_screen_tab_timeline\" was not injected: check your FXML file 'ResultScreen.fxml'.";
        assert result_screen_menu_file != null : "fx:id=\"result_screen_menu_file\" was not injected: check your FXML file 'ResultScreen.fxml'.";
        assert result_screen_anchor_configuration_matrix != null : "fx:id=\"result_screen_anchor_configuration_matrix\" was not injected: check your FXML file 'ResultScreen.fxml'.";
	}
}
