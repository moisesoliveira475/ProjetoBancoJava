package interfaces;

import java.awt.*;
import javax.swing.*;
import java.awt.Toolkit;

public class Cadastrar {
    Entrar entrar = new Entrar();
    
    public void show() {
        JFrame frame = new JFrame("Cadastrar");
        frame.setSize(400,480);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/assets/coffee.png")));

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.darkGray);
        
        JLabel lblTitulo = new JLabel("Informações pessoais");
        lblTitulo.setText("ABRIR UMA NOVA CONTA");
        lblTitulo.setBounds(70, 15, 350, 21);
        lblTitulo.setForeground(new Color(225, 225, 225));
        lblTitulo.setFont(new Font("Arial", 500, 20));

        JSeparator lblDivisao = new JSeparator();
        lblDivisao.setBounds(0, 51, 400, 1);
        lblDivisao.setForeground(new Color(30, 30, 30));
        
        JLabel lblSubtitulo = new JLabel("Informações pessoais");
        lblSubtitulo.setBounds(14, 66, 176, 21);
        lblSubtitulo.setForeground(new Color(225, 225, 225));
        lblSubtitulo.setFont(new Font("Arial", 500, 18));
        
        JLabel lblNome = new JLabel("Nome");
        lblNome.setBounds(25, 95, 40, 17);
        lblNome.setForeground(new Color(225, 225, 225));
        
        JTextField txtNome = new JTextField();
        txtNome.setBounds(20, 113, 340, 30);
        txtNome.setBackground(new Color(82,82,82));
        txtNome.setBorder(null);
        
        JLabel lblCpf = new JLabel("CPF(Apenas números)");
        lblCpf.setBounds(25, 153, 147, 17);
        lblCpf.setForeground(new Color(225, 225, 225));
        
        JTextField txtCpf = new JTextField();
        txtCpf.setBounds(20, 171, 340, 30);
        txtCpf.setBackground(new Color(82,82,82));
        txtCpf.setBorder(null);
        
        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(25, 211, 36, 17);
        lblEmail.setForeground(new Color(225, 225, 225));
        
        JTextField txtEmail = new JTextField();
        txtEmail.setBounds(20, 229, 340, 30);
        txtEmail.setBackground(new Color(82,82,82));
        txtEmail.setBorder(null);
        
        JLabel lblSenha = new JLabel("Senha");
        lblSenha.setBounds(25, 269, 42, 17);
        lblSenha.setForeground(new Color(225, 225, 225));
        
        JTextField txtSenha = new JPasswordField();
        txtSenha.setBounds(20, 287, 340, 30);
        txtSenha.setBackground(new Color(82,82,82));
        txtSenha.setBorder(null);
        
        JLabel lblDataNascimento = new JLabel("Data de nacimento");
        lblDataNascimento.setBounds(53, 327, 133, 17);
        lblDataNascimento.setForeground(new Color(225, 225, 225));
        
        JTextField txtDataNascimento = new JTextField();
        txtDataNascimento.setBounds(44, 345, 151, 27);
        txtDataNascimento.setBackground(new Color(82,82,82));
        txtDataNascimento.setBorder(null);
        
        JLabel lblRendaMensal = new JLabel("Renda Mensal");
        lblRendaMensal.setBounds(234, 327, 93, 17);
        lblRendaMensal.setForeground(new Color(225, 225, 225));
        
        JTextField txtRendaMensal = new JTextField();
        txtRendaMensal.setBounds(205, 345, 151, 27);
        txtRendaMensal.setBackground(new Color(82,82,82));
        txtRendaMensal.setBorder(null);
        
        JButton btnCancelar = new JButton();
        btnCancelar.setText("CANCELAR");
        btnCancelar.setBounds(62, 392, 107, 33);
        btnCancelar.setBorder(null);
        btnCancelar.setBackground(new Color(82,82,82));
        btnCancelar.setForeground(new Color(225,225,225));
        btnCancelar.addActionListener(e -> {
            frame.setVisible(false);
            entrar.show();
        });
        
        JButton btnContinuar = new JButton();
        btnContinuar.setText("CONTINUAR");
        btnContinuar.setBounds(194, 392, 144, 33);
        btnContinuar.setBorder(null);
        btnContinuar.setBackground(new Color(6, 104, 6));
        btnContinuar.setForeground(new Color(225,225,225));
        btnContinuar.addActionListener(e -> { });
        
        panel.add(lblTitulo);
        panel.add(lblDivisao);
        panel.add(lblSubtitulo);
        panel.add(lblNome);
        panel.add(txtNome);
        panel.add(lblCpf);
        panel.add(txtCpf);
        panel.add(lblEmail);
        panel.add(txtEmail);
        panel.add(lblSenha);
        panel.add(txtSenha);
        panel.add(lblDataNascimento);
        panel.add(txtDataNascimento);
        panel.add(lblRendaMensal);
        panel.add(txtRendaMensal);
        panel.add(btnCancelar);
        panel.add(btnContinuar);
        
        frame.add(panel);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
