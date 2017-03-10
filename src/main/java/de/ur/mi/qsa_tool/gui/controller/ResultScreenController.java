package de.ur.mi.qsa_tool.gui.controller;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import de.iteratec.holiday.ressources.NumberValues;
import de.iteratec.holiday.ressources.StringValues;
import de.ur.mi.qsa_tool.gui.model.PersonUI;
import de.ur.mi.qsa_tool.model.Corpus;
import de.ur.mi.qsa_tool.model.Data;
import de.ur.mi.qsa_tool.model.Stats;
import de.ur.mi.qsa_tool.model.StringIntegerPair;
import de.ur.mi.qsa_tool.task.FineDataGeneratorTask;
import de.ur.mi.qsa_tool.task.RawDataGeneratorTask;
import de.ur.mi.qsa_tool.task.StatsGeneratorTask;
import de.ur.mi.qsa_tool.util.CSVWriter;
import de.ur.mi.qsa_tool.util.NumberAsStringComparator;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ResultScreenController {

	@FXML
    private MenuItem result_screen_menu_file_submenu_close;

    @FXML
    private MenuItem export_configuration_matrix_episodes;

    @FXML
    private Menu result_screen_menu_help;

    @FXML
    private MenuItem export_word_counts_important;

    @FXML
    private Menu result_screen_menu_edit;

    @FXML
    private MenuItem result_screen_menu_help_submenu_about;

    @FXML
    private MenuItem export_configuration_matrix_seasons;

    @FXML
    private MenuItem result_screen_menu_edit_submenu_delete;

    @FXML
    private Menu result_screen_menu_file;

    @FXML
    private MenuItem export_person_stats_all;

    @FXML
    private MenuItem export_reply_lengths_important;

    @FXML
    private MenuItem export_word_counts_all;

    @FXML
    private MenuItem export_person_stats_important;

    @FXML
    private MenuItem export_configuration_matrix_scenes;

    @FXML
    private MenuItem export_word_counts_whole;

    @FXML
    private MenuItem export_reply_lengths_all;

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
    private Text title_time_line;

    @FXML
    private ScrollPane result_content_scroll_pane;

    @FXML
    private TableView<String[]> table_configuration_matrix;

    @FXML
    private TableView<PersonUI> table_overall_stats;

    @FXML
    private TableView<?> table_reply_length;

    @FXML
    private Text title_reply_length;

    @FXML
    private Text title_overall_stats;
    
    @FXML
    private NumberAxis line_chart_reply_lengths_x_axis;
    
    @FXML
    private NumberAxis line_chart_reply_lengths_y_axis;
    
    @FXML
    private LineChart<Integer, Integer> line_chart_reply_lengths;
    
    @FXML
    private TableView<String[]> table_word_counts;
    
//    @FXML
//    private CategoryAxis bar_chart_word_counts_x_axis;
//    
//    @FXML
//    private NumberAxis bar_chart_word_counts_y_axis;
//    
//    @FXML
//    private BarChart<String, Integer> bar_chart_word_counts;
    
    
    
    private ObservableList<String> fileNames = FXCollections.observableArrayList();
    private ObservableList<String[]> configurationMatrixContent = FXCollections.observableArrayList();
    private ObservableList<PersonUI> personsOverviewContent = FXCollections.observableArrayList();
    private ObservableList<String[]> wordCountsTableContent = FXCollections.observableArrayList();
    private ArrayList<String> personNames = new ArrayList<>();

    private Corpus corpus;
    private Data data;
    private Stats stats;
    private Stage prevStage;
    private Stage nextStage;
    private Scene startScene;
    private CSVWriter csvWriter;

    @FXML
    void initialize() {
    	validateUIFields();
    	csvWriter = new CSVWriter();
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
		updateReplyLengthLineChart();
		updateWordCountsTable();
		//updateTimeLineChart();
		//updateWordCountsBarChart();
	}
	
//	private void updateWordCountsBarChart() {
//		ArrayList<ArrayList<StringIntegerPair>> importantWords = stats.getMostWordCountsForMostImportantPersons();
//		ArrayList<String> mostImportantPersonNames = stats.getMostImportantPersonsNames();
//		System.out.println("mostImportantPersons content size: " + mostImportantPersonNames.size());
//		for(int i = 0; i<mostImportantPersonNames.size(); i++){
//			XYChart.Series<String, Integer> series = new XYChart.Series<>();
//			for(int j = 0; j<importantWords.size(); j++){
//				series.getData().add(new XYChart.Data<String, Integer>(importantWords.get(i).get(j).getString(), importantWords.get(i).get(j).getInteger()));
//			}
//			series.setName(mostImportantPersonNames.get(i));
//			bar_chart_word_counts.getData().add(series);
//		}
//	}



//	private void updateTimeLineChart() {
//		ArrayList<Integer> timeLine = stats.getTimeLine();
//		System.out.println("timeLine content size: " + timeLine.size());
//		for(int i = 0; i<timeLine.size(); i++){
//			XYChart.Series<Integer, Integer> series = new XYChart.Series<>();
//			series.getData().add(new XYChart.Data<Integer, Integer>(i, timeLine.get(i)));
//			series.setName("Erzählstruktur");
//			line_chart_time_line.getStyleClass().add("line-chart");
//			line_chart_time_line.getData().add(series);
//		}
//	}



	private void updateWordCountsTable() {
		table_word_counts.getItems().clear();
		wordCountsTableContent.addAll(stats.getMostWordCountsForMostImportantPersons());
		System.out.println("word count content size: " + wordCountsTableContent.size());
		for(int i = 0; i<wordCountsTableContent.get(0).length-1; i++){
			String columnTitle = stats.getMostImportantPersonsNames().get(i);
			TableColumn<String[], String> column = new TableColumn<String[], String>(columnTitle);
			final int columnIndex = i;
			column.setCellValueFactory(new Callback<CellDataFeatures<String[], String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(CellDataFeatures<String[], String> p) {
                	String value = p.getValue()[columnIndex];
                	if(value != null){
                		return new SimpleStringProperty(value);
                	}
                	else {
                		value = "";
                		return new SimpleStringProperty(value);
                	}
                }
            });
			table_word_counts.getColumns().add(column);
		}
		table_word_counts.setItems(wordCountsTableContent);
		table_word_counts.refresh();
	}



	private void updateReplyLengthLineChart() {
		ArrayList<HashMap<Integer, Integer>> replyLengthData = stats.getReplyLengths();
		ArrayList<String> mostImportantPersonNames = stats.getMostImportantPersonsNames();
		System.out.println("mostImportantPersons content size: " + mostImportantPersonNames.size());
		for(int i = 0; i<mostImportantPersonNames.size(); i++){
			XYChart.Series<Integer, Integer> series = new XYChart.Series<>();
			for(int j = 0; j<50; j++){
				if(replyLengthData.get(i).containsKey(j)){
					series.getData().add(new XYChart.Data<Integer, Integer>(j, replyLengthData.get(i).get(j)));
				}
				
			}
			series.setName(mostImportantPersonNames.get(i));
			line_chart_reply_lengths.getStyleClass().add("line-chart");
			line_chart_reply_lengths.getData().add(series);
		}
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
		configurationMatrixContent.addAll(stats.getConfigurationEpisodeMatrixList());
		System.out.println("configuration matrix content size: " + configurationMatrixContent.size());
		System.out.println("configuration matrix length: " + configurationMatrixContent.get(0).length);
		configurationMatrixContent.remove(0);
		for(int i = 0; i<configurationMatrixContent.get(0).length-1; i++){
			String columnTitle = stats.getConfigurationEpisodeMatrixList().get(0)[i];
			TableColumn<String[], String> column = new TableColumn<String[], String>(columnTitle);
			final int columnIndex = i;
			column.setCellValueFactory(new Callback<CellDataFeatures<String[], String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(CellDataFeatures<String[], String> p) {
                	String value = p.getValue()[columnIndex];
                	if(value != null){
                		return new SimpleStringProperty(value);
                	}
                	else {
                		value = "";
                		return new SimpleStringProperty(value);
                	}
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

	@FXML
	void exportConfigurationMatrixSeason(ActionEvent event){
		String fileContent = csvWriter.getCSVStringFromArraysInList(stats.getConfigurationSeasonMatrixList());
		saveFileFromFileChooser(fileContent);
	}
	
	@FXML
    void exportConfigurationMatrixEpisode(ActionEvent event) {
		String fileContent = csvWriter.getCSVStringFromArraysInList(stats.getConfigurationEpisodeMatrixList());
		saveFileFromFileChooser(fileContent);
    }

    @FXML
    void exportConfigurationMatrixScene(ActionEvent event) {
    	String fileContent = csvWriter.getCSVStringFromArraysInList(stats.getConfigurationSceneMatrixList());
		saveFileFromFileChooser(fileContent);
    }

    @FXML
    void exportPersonStatsAll(ActionEvent event) {
    	String fileContent = csvWriter.getCSVStringFromPersonUI(stats.getPersonOverviewStats());
		saveFileFromFileChooser(fileContent);
    }

    @FXML
    void exportPersonStatsImportant(ActionEvent event) {
    	String fileContent = csvWriter.getCSVStringFromPersonUI(stats.getMostImportantPersons());
		saveFileFromFileChooser(fileContent);
    }

    @FXML
    void exportReplyLengthsAll(ActionEvent event) {
    	String fileContent = csvWriter.getCSVStringFromReplyLengths(stats.getReplyLengths(), personNames);
		saveFileFromFileChooser(fileContent);
    }

    @FXML
    void exportReplyLengthsImportant(ActionEvent event) {
    	String fileContent = csvWriter.getCSVStringFromReplyLengths(stats.getReplyLengthsMostImportant(), stats.getMostImportantPersonsNames());
		saveFileFromFileChooser(fileContent);
    }

    @FXML
    void exportWordCountsWhole(ActionEvent event) {
    	
    }

    @FXML
    void exportWordCountsAll(ActionEvent event) {
    	String fileContent = csvWriter.getCSVStringFromReplyLengths(stats.getWordsForPersons(), personNames);
		saveFileFromFileChooser(fileContent);
    }

    @FXML
    void exportWordCountsImportant(ActionEvent event) {
    	String fileContent = csvWriter.getCSVStringFromReplyLengths(stats.getMostWordCountsForMostImportantPersons(), stats.getMostImportantPersonsNames());
		saveFileFromFileChooser(fileContent);
    }

    @FXML
    void closeApp(ActionEvent event) {
    	Platform.exit();
    	System.exit(0);
    }

    @FXML
    void deleteInput(ActionEvent event) {
    	showAlertDialog();
    }
    
    public void showAlertDialog() {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Auswertung löschen?");
			alert.setHeaderText("Möchten Sie die Auswertung beenden?");
			alert.setContentText("Alle berechneten Daten werden gelöscht und Sie kehren zurück zum Startbildschirm.");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				clearData();
		    	showStartScreen();
			}
	}

    
    

    private void clearData() {
		data.clear();
		fileNames.clear();
	    configurationMatrixContent.clear();
	    personsOverviewContent.clear();
	    wordCountsTableContent.clear();
	    personNames.clear();
	    corpus.clear();
	    stats.clear();
		
	}



	private void showStartScreen() {
    	nextStage = new Stage();
		nextStage.setTitle("Quantitative Serienanalyse");
		try {
			FXMLLoader myLoader = new FXMLLoader(getClass().getResource("StartScreen.fxml"));
			Pane root =  myLoader.load();
			StartScreenController startController = (StartScreenController) myLoader.getController();
			startScene = new Scene(root);
			startScene.getStylesheets().add("css/qsa_tool.css");
		} catch (Exception e) {
			e.printStackTrace();
		}

		nextStage.setScene(startScene);
		
		if (prevStage != null){
			prevStage.close();
		}
		nextStage.show();
	}



	@FXML
    void showInfos(ActionEvent event) {

    }
    
    private void saveFileFromFileChooser(String content) {
    	FileChooser fileChooser = new FileChooser();
    	configureFileChooser(fileChooser);
    	File file = fileChooser.showSaveDialog(prevStage);
        if (file != null) {
        	try{
        		csvWriter.writeCSV(file, content);
        	} catch(Exception e){
        		e.printStackTrace();
        	}
        }
	}

	private void configureFileChooser(FileChooser fileChooser) {
		fileChooser.setTitle("Datei speichern");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));                 
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("csv", "*.csv"));
	}
	
	
	 public void setPrevStage(Stage prevStage) {
			this.prevStage = prevStage;
		}

		public Stage getPrevStage() {
			return this.prevStage;
		}

	@FXML
    void validateUIFields() {
		assert result_screen_menu_file_submenu_close != null : "fx:id=\"result_screen_menu_file_submenu_close\" was not injected: check your FXML file 'ResultScreen.fxml'.";
        assert export_configuration_matrix_episodes != null : "fx:id=\"export_configuration_matrix_episodes\" was not injected: check your FXML file 'ResultScreen.fxml'.";
        assert title_overall_stats != null : "fx:id=\"title_overall_stats\" was not injected: check your FXML file 'ResultScreen.fxml'.";
        assert result_screen_menu_help != null : "fx:id=\"result_screen_menu_help\" was not injected: check your FXML file 'ResultScreen.fxml'.";
        assert result_content_scroll_pane != null : "fx:id=\"result_content_scroll_pane\" was not injected: check your FXML file 'ResultScreen.fxml'.";
        assert table_configuration_matrix != null : "fx:id=\"table_configuration_matrix\" was not injected: check your FXML file 'ResultScreen.fxml'.";
        assert export_word_counts_important != null : "fx:id=\"export_word_counts_important\" was not injected: check your FXML file 'ResultScreen.fxml'.";
        assert result_screen_menu_edit != null : "fx:id=\"result_screen_menu_edit\" was not injected: check your FXML file 'ResultScreen.fxml'.";
        assert result_screen_menu_help_submenu_about != null : "fx:id=\"result_screen_menu_help_submenu_about\" was not injected: check your FXML file 'ResultScreen.fxml'.";
        assert line_chart_reply_lengths_y_axis != null : "fx:id=\"line_chart_reply_lengths_y_axis\" was not injected: check your FXML file 'ResultScreen.fxml'.";
        assert export_configuration_matrix_seasons != null : "fx:id=\"export_configuration_matrix_seasons\" was not injected: check your FXML file 'ResultScreen.fxml'.";
        assert result_screen_menu_edit_submenu_delete != null : "fx:id=\"result_screen_menu_edit_submenu_delete\" was not injected: check your FXML file 'ResultScreen.fxml'.";
        assert table_word_counts != null : "fx:id=\"table_word_counts\" was not injected: check your FXML file 'ResultScreen.fxml'.";
        assert result_screen_menu_file != null : "fx:id=\"result_screen_menu_file\" was not injected: check your FXML file 'ResultScreen.fxml'.";
        assert export_person_stats_all != null : "fx:id=\"export_person_stats_all\" was not injected: check your FXML file 'ResultScreen.fxml'.";
        assert title_configuration_matrix != null : "fx:id=\"title_configuration_matrix\" was not injected: check your FXML file 'ResultScreen.fxml'.";
        assert result_screen_tab_anchor_pane != null : "fx:id=\"result_screen_tab_anchor_pane\" was not injected: check your FXML file 'ResultScreen.fxml'.";
        assert export_reply_lengths_important != null : "fx:id=\"export_reply_lengths_important\" was not injected: check your FXML file 'ResultScreen.fxml'.";
        assert title_time_line != null : "fx:id=\"title_time_line\" was not injected: check your FXML file 'ResultScreen.fxml'.";
        assert export_word_counts_all != null : "fx:id=\"export_word_counts_all\" was not injected: check your FXML file 'ResultScreen.fxml'.";
        assert line_chart_reply_lengths != null : "fx:id=\"line_chart_reply_lengths\" was not injected: check your FXML file 'ResultScreen.fxml'.";
        assert export_person_stats_important != null : "fx:id=\"export_person_stats_important\" was not injected: check your FXML file 'ResultScreen.fxml'.";
        assert export_configuration_matrix_scenes != null : "fx:id=\"export_configuration_matrix_scenes\" was not injected: check your FXML file 'ResultScreen.fxml'.";
        assert export_word_counts_whole != null : "fx:id=\"export_word_counts_whole\" was not injected: check your FXML file 'ResultScreen.fxml'.";
        assert line_chart_reply_lengths_x_axis != null : "fx:id=\"line_chart_reply_lengths_x_axis\" was not injected: check your FXML file 'ResultScreen.fxml'.";
        assert title_reply_length != null : "fx:id=\"title_reply_length\" was not injected: check your FXML file 'ResultScreen.fxml'.";
        assert export_reply_lengths_all != null : "fx:id=\"export_reply_lengths_all\" was not injected: check your FXML file 'ResultScreen.fxml'.";
        assert table_overall_stats != null : "fx:id=\"table_overall_stats\" was not injected: check your FXML file 'ResultScreen.fxml'.";


    }
}
