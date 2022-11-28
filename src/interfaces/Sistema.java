package interfaces;

import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Sistema {
    public void show() {
        JFrame frame = new JFrame("Tela principal");
        frame.setSize(400,387);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/assets/coffee.png")));
        
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.darkGray);

        frame.add(panel);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
