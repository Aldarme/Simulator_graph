package View;

import javax.swing.table.AbstractTableModel;

import Ant_Colony.CommonKnowledge;

public class PheroDynaDisplay extends AbstractTableModel{
	
	private Object[][] data;
	private String[] entetes = {"1", "2", "3", "4", "5", "6", "7", "8"};
	
	public PheroDynaDisplay() {
		super();
		data = CommonKnowledge.pheroMtx();
	}

	@Override
	public int getColumnCount() {
		return entetes.length;
	}

	@Override
	public int getRowCount() {
		return data.length;
	}

	@Override
	public String getColumnName(int arg0) {
		return entetes[arg0];
	}
	
	@Override
	public Object getValueAt(int arg0, int arg1) {
		return data[arg0][arg1];
	}

	public void update() {
		data = CommonKnowledge.pheroMtx();
		fireTableDataChanged();
	}
	
}
