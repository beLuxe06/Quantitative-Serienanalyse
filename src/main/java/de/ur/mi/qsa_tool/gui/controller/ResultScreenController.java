package de.ur.mi.qsa_tool.gui.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;
import de.ur.mi.qsa_tool.gui.model.PersonUI;
import de.ur.mi.qsa_tool.model.Corpus;
import de.ur.mi.qsa_tool.model.Data;
import de.ur.mi.qsa_tool.model.Stats;
import de.ur.mi.qsa_tool.task.FineDataGeneratorTask;
import de.ur.mi.qsa_tool.task.RawDataGeneratorTask;
import de.ur.mi.qsa_tool.task.StatsGeneratorTask;
import de.ur.mi.qsa_tool.util.NumberAsStringComparator;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TableView<PersonUI> table_overall_stats;

    @FXML
    private TableView<?> table_reply_length;

    @FXML
    private MenuItem result_screen_menu_help_submenu_about;

    @FXML
    private Text title_reply_length;

    @FXML
    private MenuItem result_screen_menu_edit_submenu_delete;

    @FXML
    private Text title_overall_stats;

    @FXML
    private Menu result_screen_menu_file;
    
    private ObservableList<String> fileNames = FXCollections.observableArrayList();
    private ObservableList<String[]> configurationMatrixContent = FXCollections.observableArrayList();
    private ObservableList<PersonUI> personsOverviewContent = FXCollections.observableArrayList();
    private ArrayList<String> personNames = new ArrayList<>();

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
		updateOverviewStats();
	}
	
	private void updateOverviewStats() {
		table_overall_stats.getItems().clear();
		personsOverviewContent.addAll(stats.getPersonOverviewStats());
		System.out.println("personsOverview content size: " + personsOverviewContent.size());
		TableColumn<PersonUI, String> nameColumn = new TableColumn<PersonUI, String>("Person:");
		nameColumn.setCellValueFactory(new PropertyValueFactory<PersonUI, String> ("name"));
		table_overall_stats.getColumns().add(nameColumn);
		TableColumn<PersonUI, String> wordNumbersColumn = new TableColumn<PersonUI, String>("Wortanzahl:");
		wordNumbersColumn.setCellValueFactory(new PropertyValueFactory<PersonUI, String> ("wordNumbers"));
		wordNumbersColumn.setComparator(new NumberAsStringComparator());
		table_overall_stats.getColumns().add(wordNumbersColumn);
		TableColumn<PersonUI, String> speechNumbersColumn = new TableColumn<PersonUI, String>("Replikanzahl:");
		speechNumbersColumn.setCellValueFactory(new PropertyValueFactory<PersonUI, String> ("speechNumbers"));
		speechNumbersColumn.setComparator(new NumberAsStringComparator());
		table_overall_stats.getColumns().add(speechNumbersColumn);
		TableColumn<PersonUI, String> seasonsColumn = new TableColumn<PersonUI, String>("Auftritt in Staffeln:");
		seasonsColumn.setCellValueFactory(new PropertyValueFactory<PersonUI, String> ("seasonsPresenceSize"));
		seasonsColumn.setComparator(new NumberAsStringComparator());
		table_overall_stats.getColumns().add(seasonsColumn);
		TableColumn<PersonUI, String> seasonsShareColumn = new TableColumn<PersonUI, String>("Auftrittanteil in Staffeln:");
		seasonsShareColumn.setCellValueFactory(new PropertyValueFactory<PersonUI, String> ("appereanceSeasonsShare"));
		seasonsShareColumn.setComparator(new NumberAsStringComparator());
		table_overall_stats.getColumns().add(seasonsShareColumn);
		TableColumn<PersonUI, String> episodesColumn = new TableColumn<PersonUI, String>("Auftritt in Episoden:");
		episodesColumn.setCellValueFactory(new PropertyValueFactory<PersonUI, String> ("episodesPresenceSize"));
		episodesColumn.setComparator(new NumberAsStringComparator());
		table_overall_stats.getColumns().add(episodesColumn);
		TableColumn<PersonUI, String> episodesShareColumn = new TableColumn<PersonUI, String>("Auftrittanteil in Episode:");
		episodesShareColumn.setCellValueFactory(new PropertyValueFactory<PersonUI, String> ("appereanceEpisodesShare"));
		episodesShareColumn.setComparator(new NumberAsStringComparator());
		table_overall_stats.getColumns().add(episodesShareColumn);
		TableColumn<PersonUI, String> scenesColumn = new TableColumn<PersonUI, String>("Auftritt in Szenen:");
		scenesColumn.setCellValueFactory(new PropertyValueFactory<PersonUI, String> ("scenesPresenceSize"));
		scenesColumn.setComparator(new NumberAsStringComparator());
		table_overall_stats.getColumns().add(scenesColumn);
		TableColumn<PersonUI, String> scenesShareColumn = new TableColumn<PersonUI, String>("Auftrittanteil in Szenen:");
		scenesShareColumn.setCellValueFactory(new PropertyValueFactory<PersonUI, String> ("appereanceScenesShare"));
		scenesShareColumn.setComparator(new NumberAsStringComparator());
		table_overall_stats.getColumns().add(scenesShareColumn);
		table_overall_stats.setItems(personsOverviewContent);
		table_overall_stats.refresh();
	}



	private void updateConfigurationMatrixTable() {
		table_configuration_matrix.getItems().clear();
		configurationMatrixContent.addAll(stats.getConfigurationSceneMatrix());
		System.out.println("configuration matrix content size: " + configurationMatrixContent.size());
		System.out.println("configuration matrix length: " + configurationMatrixContent.get(0).length);
		configurationMatrixContent.remove(0);
		System.out.println("configuration matrix content size: " + configurationMatrixContent.size());
		System.out.println("configuration matrix length: " + configurationMatrixContent.get(0).length);
		System.out.println("configuration matrix [1]: " + configurationMatrixContent.get(1).length);
		for(int i = 0; i<configurationMatrixContent.get(0).length-1; i++){
			String columnTitle = stats.getConfigurationSceneMatrix()[0][i];
			TableColumn<String[], String> column = new TableColumn<String[], String>(columnTitle);
			final int columnIndex = i;
			column.setCellValueFactory(new Callback<CellDataFeatures<String[], String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(CellDataFeatures<String[], String> p) {
                    return new SimpleStringProperty((p.getValue()[columnIndex]));
                }
            });
			table_configuration_matrix.getColumns().add(column);
		}
		table_configuration_matrix.setItems(configurationMatrixContent);
		table_configuration_matrix.refresh();
	}

	private void startRawDataGeneratorTask() {
		RawDataGeneratorTask rawDataGeneratorTask = new RawDataGeneratorTask(data);
		System.out.println("raw data processing started!");
		rawDataGeneratorTask.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				System.out.println("raw data processed!");
				data = rawDataGeneratorTask.getValue();
				System.out.println(data);
				startFineDataGeneratorTask();
			}					
		});
		
		rawDataGeneratorTask.setOnFailed(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				Throwable throwable = rawDataGeneratorTask.getException(); 
		        throwable.printStackTrace();
				System.out.println("raw data processing failed!");
				System.out.println(data.toString());
			}					
		});
		rawDataGeneratorTask.setOnCancelled(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				System.out.println("raw data processing cancelled!");
				System.out.println(data.toString());
			}					
		});
		rawDataGeneratorTask.setOnRunning(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				System.out.println("raw data processing running!");
			}					
		});
		
		Thread th = new Thread(rawDataGeneratorTask);
		th.setDaemon(true);
		th.start();
	}

	private void startFineDataGeneratorTask(){
		FineDataGeneratorTask fineDataGeneratorTask = new FineDataGeneratorTask(data);
		System.out.println("fine data processing started!");
		fineDataGeneratorTask.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				System.out.println("fine data processed!");
				data = fineDataGeneratorTask.getValue();
				getPersonNames();
				System.out.println("getting PersonNames: " + personNames.size());
				startStatsGeneratorTask();
			}					
		});
		
		fineDataGeneratorTask.setOnFailed(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				System.out.println("fine data processing failed!");
			}					
		});
		fineDataGeneratorTask.setOnCancelled(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				System.out.println("fine data processing cancelled!");
			}					
		});
		fineDataGeneratorTask.setOnRunning(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				System.out.println("fine data processing running!");
			}					
		});
		
		Thread th = new Thread(fineDataGeneratorTask);
		th.setDaemon(true);
		th.start();
	}
	

	private void startStatsGeneratorTask() {
		StatsGeneratorTask statsGeneratorTask = new StatsGeneratorTask(data);
		System.out.println("stats processing started!");
		statsGeneratorTask.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				System.out.println("stats processed!");
				stats = statsGeneratorTask.getValue();
				System.out.println("fill UI with stats: " + stats.toString());
				updateUIWithStats();
			}					
		});
		
		statsGeneratorTask.setOnFailed(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				System.out.println("stats processing failed!");
			}					
		});
		statsGeneratorTask.setOnCancelled(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				System.out.println("stats processing cancelled!");
			}					
		});
		Thread th = new Thread(statsGeneratorTask);
		th.setDaemon(true);
		th.start();
	}
	
	private void getPersonNames() {
		for(int i = 0; i < data.getPersonList().size(); i++){
			personNames.add(data.getPersonList().get(i).getPersonId().getName());
		}
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
        assert table_overall_stats != null : "fx:id=\"table_overall_stats\" was not injected: check your FXML file 'ResultScreen.fxml'.";
        assert table_reply_length != null : "fx:id=\"table_reply_length\" was not injected: check your FXML file 'ResultScreen.fxml'.";
        assert result_screen_menu_help_submenu_about != null : "fx:id=\"result_screen_menu_help_submenu_about\" was not injected: check your FXML file 'ResultScreen.fxml'.";
        assert title_reply_length != null : "fx:id=\"title_reply_length\" was not injected: check your FXML file 'ResultScreen.fxml'.";
        assert result_screen_menu_edit_submenu_delete != null : "fx:id=\"result_screen_menu_edit_submenu_delete\" was not injected: check your FXML file 'ResultScreen.fxml'.";
        assert title_overall_stats != null : "fx:id=\"title_overall_stats\" was not injected: check your FXML file 'ResultScreen.fxml'.";
        assert result_screen_menu_file != null : "fx:id=\"result_screen_menu_file\" was not injected: check your FXML file 'ResultScreen.fxml'.";

    }
}
