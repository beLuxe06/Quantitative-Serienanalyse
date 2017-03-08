package de.ur.mi.qsa_tool.gui.controller;

import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ResourceBundle;

import de.ur.mi.qsa_tool.model.Corpus;
import de.ur.mi.qsa_tool.model.Data;
import de.ur.mi.qsa_tool.model.Stats;
import de.ur.mi.qsa_tool.service.FineDataGeneratorService;
import de.ur.mi.qsa_tool.service.RawDataGeneratorService;
import de.ur.mi.qsa_tool.service.StatsGeneratorService;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Callback;

public class ResultScreenController {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text title_configuration_matrix;

    @FXML
    private TableView<?> table_time_line;

    @FXML
    private AnchorPane result_screen_tab_anchor_pane;

    @FXML
    private MenuItem result_screen_menu_file_submenu_close;

    @FXML
    private Text title_time_line;

    @FXML
    private Menu result_screen_menu_help;

    @FXML
    private ScrollPane result_content_scroll_pane;

    @FXML
    private TableView<String[]> table_configuration_matrix;

    @FXML
    private Menu result_screen_menu_edit;

    @FXML
    private TableView<?> table_word_count;

    @FXML
    private TableView<?> table_reply_length;

    @FXML
    private MenuItem result_screen_menu_help_submenu_about;

    @FXML
    private Text title_reply_length;

    @FXML
    private MenuItem result_screen_menu_edit_submenu_delete;

    @FXML
    private Text title_word_count;

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
	
	private void updateUIWithStats(){
		updateConfigurationMatrixTable();
	}

	private void updateConfigurationMatrixTable() {
		ObservableList<String[]> configurationMatrixData = FXCollections.observableArrayList();
        configurationMatrixData.addAll(Arrays.asList(stats.getConfigurationMatrix()));
        configurationMatrixData.remove(0);//remove titles from data
       table_configuration_matrix = new TableView<>();
        for (int i = 0; i < stats.getConfigurationMatrix()[0].length; i++) {
        	// add titles to table
            TableColumn<String[], String> tc = new TableColumn<String[], String>(stats.getConfigurationMatrix()[0][i]);
            final int colNo = i;
            tc.setCellValueFactory(new Callback<CellDataFeatures<String[], String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(CellDataFeatures<String[], String> p) {
                    return new SimpleStringProperty((p.getValue()[colNo]));
                }
            });
            tc.setPrefWidth(90);
            table_configuration_matrix.getColumns().add(tc);
        }
        table_configuration_matrix.setItems(configurationMatrixData);
	}



	private void startRawDataGeneratorTask() {
		Service<Data> rawDataGeneratorService = new RawDataGeneratorService(data);
		try{
			rawDataGeneratorService.start();
		}
		catch(Exception e){
			e.printStackTrace();
		}
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
			}					
		});
		fineDataGeneratorService.setOnCancelled(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				System.out.println("fine data processing cancelled!");
			}					
		});
		fineDataGeneratorService.setOnRunning(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				System.out.println("fine data processing running!");
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
				updateUIWithStats();
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


    void validateUIFields() {
        assert title_configuration_matrix != null : "fx:id=\"title_configuration_matrix\" was not injected: check your FXML file 'ResultScreen.fxml'.";
        assert table_time_line != null : "fx:id=\"table_time_line\" was not injected: check your FXML file 'ResultScreen.fxml'.";
        assert result_screen_tab_anchor_pane != null : "fx:id=\"result_screen_tab_anchor_pane\" was not injected: check your FXML file 'ResultScreen.fxml'.";
        assert result_screen_menu_file_submenu_close != null : "fx:id=\"result_screen_menu_file_submenu_close\" was not injected: check your FXML file 'ResultScreen.fxml'.";
        assert title_time_line != null : "fx:id=\"title_time_line\" was not injected: check your FXML file 'ResultScreen.fxml'.";
        assert result_screen_menu_help != null : "fx:id=\"result_screen_menu_help\" was not injected: check your FXML file 'ResultScreen.fxml'.";
        assert result_content_scroll_pane != null : "fx:id=\"result_content_scroll_pane\" was not injected: check your FXML file 'ResultScreen.fxml'.";
        assert table_configuration_matrix != null : "fx:id=\"table_configuration_matrix\" was not injected: check your FXML file 'ResultScreen.fxml'.";
        assert result_screen_menu_edit != null : "fx:id=\"result_screen_menu_edit\" was not injected: check your FXML file 'ResultScreen.fxml'.";
        assert table_word_count != null : "fx:id=\"table_word_count\" was not injected: check your FXML file 'ResultScreen.fxml'.";
        assert table_reply_length != null : "fx:id=\"table_reply_length\" was not injected: check your FXML file 'ResultScreen.fxml'.";
        assert result_screen_menu_help_submenu_about != null : "fx:id=\"result_screen_menu_help_submenu_about\" was not injected: check your FXML file 'ResultScreen.fxml'.";
        assert title_reply_length != null : "fx:id=\"title_reply_length\" was not injected: check your FXML file 'ResultScreen.fxml'.";
        assert result_screen_menu_edit_submenu_delete != null : "fx:id=\"result_screen_menu_edit_submenu_delete\" was not injected: check your FXML file 'ResultScreen.fxml'.";
        assert title_word_count != null : "fx:id=\"title_word_count\" was not injected: check your FXML file 'ResultScreen.fxml'.";
        assert result_screen_menu_file != null : "fx:id=\"result_screen_menu_file\" was not injected: check your FXML file 'ResultScreen.fxml'.";

    }
}
