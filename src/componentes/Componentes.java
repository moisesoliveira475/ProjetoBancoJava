package componentes;

import javax.swing.*;

public class Componentes {

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
}
