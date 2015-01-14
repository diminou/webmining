package interfaceGraphique;

import java.util.List;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

public class TableauRes extends AbstractTableModel {
	private String[] entetes = { "NomDoc" };
	private List<String> listeDoc;

	public List<String> getListeDoc() {
		return listeDoc;
	}

	public void setListeDoc(List<String> listeDoc) {
		this.listeDoc = listeDoc;
	}

	public TableauRes(List<String> listeDoc) {
		super();
		this.listeDoc = listeDoc;
	}

	@Override
	public int getColumnCount() {
		return entetes.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return listeDoc.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub

		return listeDoc.get(rowIndex);

	}

}
