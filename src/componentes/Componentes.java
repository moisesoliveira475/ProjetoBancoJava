package componentes;

import java.util.Arrays;
import javax.swing.*;
import utils.*;

public class Componentes {

    public String ShowInputPassword(String title) {
        JPasswordField input = new JPasswordField(5);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        panel.add(new JLabel(title + ": "));
        panel.add(input);
        panel.add(Box.createVerticalStrut(10)); // a spacer

        int result = JOptionPane.showConfirmDialog(null, panel,
                "Entre com os valores", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            if (new String(input.getPassword()).equals("")) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos");
            }
        }

        return new String(input.getPassword());
    };
    
    // componente com dois inputs em um só alerta;
    public String[] ShowTwoInputs(String oneTitle, String twoTitle) {
        JTextField oneInput = new JTextField(5);
        JTextField twoInput = new JTextField(5);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        panel.add(new JLabel(oneTitle + ": "));
        panel.add(oneInput);
        panel.add(Box.createVerticalStrut(10)); // a spacer
        panel.add(new JLabel(twoTitle + ": "));
        panel.add(twoInput);

        int result = JOptionPane.showConfirmDialog(null, panel,
                "Entre com os valores", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            if (oneInput.getText().equals("") || twoInput.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos");
            } else {
                System.out.println("oneInput: " + oneInput.getText());
                System.out.println("twoInput: " + twoInput.getText());
            }
        }

        String[] results = {oneInput.getText(), twoInput.getText()};
        return results;
    }

    // componente com dois inputs e uma data em um alerta;
    public String[] ShowTwoInputsAndDate(String oneTitle, String twoTitle) {  
        JTextField oneInput = new JTextField();
        JTextField twoInput = new JTextField();
        JTextField data = new JTextField();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(new JLabel(oneTitle + ": "));
        panel.add(oneInput);
        panel.add(Box.createVerticalStrut(10)); // a spacer
        panel.add(new JLabel(twoTitle + ": "));
        panel.add(twoInput);
        panel.add(Box.createVerticalStrut(10)); // a spacer
        panel.add(new JLabel("data separada por /: "));
        panel.add(data);


        int result = JOptionPane.showConfirmDialog(null, panel,
                "Entre com os valores", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            if (oneInput.getText().equals("") || twoInput.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos");
            }
        }

        String[] results = {oneInput.getText(), twoInput.getText(), data.getText()};
        return results;
    }
    
    // componetes com inputs para criação de conta;
    public String[] ShowInpusForCreateAccount() {
        Utils utils = new Utils();
        
        JTextField numero = new JTextField();
        JPasswordField senha = new JPasswordField();
        String[] tipo = { "Poupança", "Corrente" };
        JComboBox boxTipo = new JComboBox(tipo);
        String[] status = { "Ativada", "Desativada" };
        JComboBox boxStatus = new JComboBox(status);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        String numeroGerado = utils.gerarNumeroConta();
        
        numero.setText(numeroGerado);
        numero.setEditable(false);

        panel.add(new JLabel("Numero da conta: "));
        panel.add(numero);
        panel.add(Box.createVerticalStrut(10)); // a spacer
        panel.add(new JLabel("Senha: "));
        panel.add(senha);
        panel.add(Box.createVerticalStrut(10)); // a spacer
        panel.add(new JLabel("Tipo: "));
        panel.add(boxTipo);
        panel.add(Box.createVerticalStrut(10)); // a spacer
        panel.add(new JLabel("Status: "));
        panel.add(boxStatus);

        int result = JOptionPane.showConfirmDialog(null, panel,
                "Entre com os valores", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            if (senha.getPassword().equals("") || new String(senha.getPassword()).equals("")) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos");
            }
        } else if(result == JOptionPane.CANCEL_OPTION || result == JOptionPane.CLOSED_OPTION) {
            String[] results = {
                "1"
            };
            return results;
        }

        String[] results = {
            numero.getText(), 
            new String(senha.getPassword()), 
            boxTipo.getSelectedItem().toString().equals("Poupança") ? "p" : "c", 
            boxStatus.getSelectedItem().toString().equals("Ativada")  ? "1" : "0"
        };
        return results;
    }
    
    // inputs para adicionar meta
    public String[] ShowInpusForAddGoal() {
        
        JTextField titulo = new JTextField();
        JTextField dataFinal = new JTextField();
        dataFinal.setText("exemplo: 10/10/1010");
        JTextField valorAportado = new JTextField();
        JTextField valorEstipulado = new JTextField();
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(new JLabel("Titulo da meta: "));
        panel.add(titulo);
        panel.add(Box.createVerticalStrut(10)); // a spacer
        panel.add(new JLabel("Prazo para a meta: "));
        panel.add(dataFinal);
        panel.add(Box.createVerticalStrut(10)); // a spacer
        panel.add(new JLabel("Valor Aportado: "));
        panel.add(valorAportado);
        panel.add(Box.createVerticalStrut(10)); // a spacer
        panel.add(new JLabel("Valor Estipulado: "));
        panel.add(valorEstipulado);

        int result = JOptionPane.showConfirmDialog(null, panel,
                "Entre com os valores", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            if (titulo.getText().equals("") || dataFinal.getText().equals("exemplo: 10/10/1010") 
                    || dataFinal.getText().equals("") || valorAportado.getText().equals("") || valorEstipulado.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos");
                return null;
            }
        }

        String[] results = {
            titulo.getText(), 
            dataFinal.getText(), 
            valorAportado.getText(),
            valorEstipulado.getText()
        };
        
        return results;
    }
    
    // input de confirmação 
    public boolean confirmarAcao(String title) {
        boolean results = false;
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        panel.add(new JLabel(title + ": "));
        panel.add(Box.createVerticalStrut(10)); // a spacer

        int result = JOptionPane.showConfirmDialog(null, panel,
                "Entre com os valores", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            results = true;
        }

        return results;
    }
}
