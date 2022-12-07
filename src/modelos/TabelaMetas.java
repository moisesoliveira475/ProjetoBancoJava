package modelos;

import java.util.*;
import javax.swing.table.AbstractTableModel;

public class TabelaMetas extends AbstractTableModel {

    private List<Meta> dados = new ArrayList<>();
    private String[] colunas = {"ID", "Titulo", "Data Inicial", "Data Final", "Valor Aportado", "Valor Estipulado"};

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
                return dados.get(line).getDataInicial();
            case 3:
                return dados.get(line).getDataFinal();
            case 4:
                return dados.get(line).getValorAportado();
            case 5:
                return dados.get(line).getValorEstipulado();
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
                dados.get(line).setDataInicial((Date) valor);
                break;
            case 3:
                dados.get(line).setDataFinal((Date) valor);
                break;
            case 4:
                dados.get(line).setValorAportado(Float.parseFloat((String) valor));
                break;
            case 5:
                dados.get(line).setValorEstipulado(Float.parseFloat((String) valor));
                break;
        }

        this.fireTableRowsUpdated(line, line);
    }
    
    public void addRow(Meta m) {
        this.dados.add(m);
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
