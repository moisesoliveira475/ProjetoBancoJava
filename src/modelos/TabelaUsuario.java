package modelos;

import java.util.*;
import javax.swing.table.AbstractTableModel;

public class TabelaUsuario extends AbstractTableModel {

    private List<Usuario> dados = new ArrayList<>();
    private String[] colunas = {"ID", "Nome", "Email", "Senha", "CPF", "DataNascimento", "Renda Mensal", "ID Conta", "Numero", "Senha Conta", "Tipo", "Status", "Saldo"};

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
                return dados.get(line).getNome();
            case 2:
                return dados.get(line).getEmail();
            case 3:
                return dados.get(line).getSenha();
            case 4:
                return dados.get(line).getCpf();
            case 5:
                return dados.get(line).getDataNascimento();
            case 6:
                return dados.get(line).getRendaMensal();
            case 7:
                return dados.get(line).getIdConta();
            case 8:
                return dados.get(line).getNumeroConta();
            case 9:
                return dados.get(line).getSenhaConta();
            case 10:
                return dados.get(line).getTipoConta();
            case 11:
                return dados.get(line).getStatusConta();
            case 12:
                return dados.get(line).getSaldoConta(); 
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
                dados.get(line).setNome((String) valor);
                break;
            case 2:
                dados.get(line).setEmail((String) valor);
                break;
            case 3:
                dados.get(line).setSenha((String) valor);
                break;
            case 4:
                dados.get(line).setCpf((String) valor);
                break;
            case 5:
                dados.get(line).setDataNascimento((Date) valor);
                break;
            case 6:
                dados.get(line).setRendaMensal(Float.parseFloat((String) valor));
                break;
            case 7:
                dados.get(line).setIdConta(Integer.parseInt((String) valor));
                break;
            case 8:
                dados.get(line).setNumeroConta((String) valor);
                break;
            case 9:
                dados.get(line).setSenhaConta((String) valor);
                break;
            case 10:
                dados.get(line).setTipoConta((String) valor);
                break;
            case 11:
                dados.get(line).setStatusConta((String) valor);
                break;
            case 12:
                dados.get(line).setSaldoConta(Float.parseFloat((String) valor));
                break;
        }
        
        this.fireTableRowsUpdated(line, line);
    }
    
    public void addRow(Usuario p) {
        this.dados.add(p);
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
