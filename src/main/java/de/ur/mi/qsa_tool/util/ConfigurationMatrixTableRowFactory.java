package de.ur.mi.qsa_tool.util;


import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.util.Callback;

public class ConfigurationMatrixTableRowFactory implements Callback<TableView<String[]>, TableRow<String[]>>{

	private ObservableList<String[]> tableRows;
	
	@Override
	public TableRow<String[]> call(TableView<String[]> arg0) {
		final TableRow<String[]> row = new TableRow<String[]>() {

			@Override
			public void updateItem(String[] item, boolean empty) {
				super.updateItem(item, empty);
			}
		};

		tableRows.addListener(new ListChangeListener<String[]>() {

			@Override
			public void onChanged(Change<? extends String[]> change) {
				String[] item = row.getItem();
			}

		});

		return row;
	}

}
