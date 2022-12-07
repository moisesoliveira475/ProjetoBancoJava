package modelos;

import java.util.*;
import javax.swing.table.AbstractTableModel;

public class TabelaGastos extends AbstractTableModel {

    private List<Gasto> dados = new ArrayList<>();
    private String[] colunas = {"ID", "Titulo", "Valor"};

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    @Override
    public int getRowCount() {
        return dados.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int line, int column) {
        switch (column) {
            case 0:
                return dados.get(line).getId();
            case 1:
                return dados.get(line).getTitulo();
            case 2:
                return dados.get(line).getValor();
        }

        return null;
    }

    @Override
    public void setValueAt(Object valor, int line, int column) {
        switch (column) {
            case 0:
                dados.get(line).setId(Integer.parseInt((String) valor));
                break;
            case 1:
                dados.get(line).setTitulo((String) valor);
                break;
            case 2:
                dados.get(line).setValor(Float.parseFloat((String) valor));
                break;
        }

        this.fireTableRowsUpdated(line, line);
    }
    
    public void addRow(Gasto g) {
        this.dados.add(g);
        this.fireTableDataChanged();
    }
    
    public void removeRow(int line) {
        this.dados.remove(line);
        this.fireTableRowsDeleted(line, line);
    }
    
    public void clearList() {
        dados.clear();
    }
}
