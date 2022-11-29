package interfaces;

import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.*;

import conexao.Conexao;
import conexao.DadosUsuario;

public class Entrar {
    
    Conexao conexao = new Conexao();
    
    public void show(DadosUsuario dadosUsuario) {
        JFrame frame = new JFrame("Entrar");
        frame.setSize(400,387);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/assets/coffee.png")));
       
        Color backInput = new Color(82,82,82);
        Color textWhite = new Color(225,225,225);
        Color btnBackground = new Color(6, 104, 6);
        
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.darkGray);
        
        ImageIcon icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/assets/coffee.png")));
        JLabel imageIcon = new JLabel(icon);
        imageIcon.setBounds(140, 25, 100, 100);
        
        JLabel lblUsuario = new JLabel("Email/CPF");
        lblUsuario.setBounds(25, 140, 68, 17);
        lblUsuario.setForeground(textWhite);
        
        JTextField txtUsuario = new JTextField();
        txtUsuario.setBounds(20, 158, 340, 30);
        txtUsuario.setBackground(backInput);
        txtUsuario.setBorder(null);
        
        JLabel lblSenha = new JLabel("Senha");
        lblSenha.setBounds(25, 198, 42, 17);
        lblSenha.setForeground(textWhite);
        
        JPasswordField txtSenha = new JPasswordField();
        txtSenha.setBounds(20, 216, 340, 30);
        txtSenha.setBackground(backInput);
        txtSenha.setBorder(null);
        
        JButton btnEntrar = new JButton();
        btnEntrar.setText("ENTRAR");
        btnEntrar.setBounds(135, 271, 112, 33);
        btnEntrar.setBorder(null);
        btnEntrar.setBackground(btnBackground);
        btnEntrar.setForeground(textWhite);
        btnEntrar.addActionListener(e -> {
            boolean result = conexao.Entrar(dadosUsuario, txtUsuario.getText(), new String(txtSenha.getPassword()));
            if(result) {
                Sistema sistema = new Sistema();
                frame.setVisible(false);
                sistema.show(dadosUsuario);
            }
        });
        
        JButton btnCadastrar = new JButton();
        btnCadastrar.setText("abrir uma nova conta");
        btnCadastrar.setBounds(125, 309, 130, 16);
        btnCadastrar.setBorder(null);
        btnCadastrar.setBackground(Color.darkGray);
        btnCadastrar.setForeground(textWhite);
        btnCadastrar.addActionListener(e -> {
            Cadastrar cadastrar = new Cadastrar();
            frame.setVisible(false);
            cadastrar.show(dadosUsuario);
        });
        
        panel.add(imageIcon);
        panel.add(lblUsuario);
        panel.add(txtUsuario);
        panel.add(lblSenha);
        panel.add(txtSenha);
        panel.add(btnEntrar);
        panel.add(btnCadastrar);
        
        frame.add(panel);
        frame.setResizable(false);
        frame.getRootPane().setDefaultButton(btnEntrar);
        frame.setVisible(true);
    }
}
