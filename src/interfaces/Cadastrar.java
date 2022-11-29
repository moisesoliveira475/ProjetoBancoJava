package interfaces;

import conexao.Conexao;
import java.awt.*;
import javax.swing.*;
import java.awt.Toolkit;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cadastrar {
    Entrar entrar = new Entrar();
    
    Conexao conexao = new Conexao();
   
    public void show() {
        JFrame frame = new JFrame("Cadastrar");
        frame.setSize(786,480);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/assets/coffee.png")));

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.darkGray);
        
        JLabel lblTitulo = new JLabel("Informações pessoais");
        lblTitulo.setText("ABRIR UMA NOVA CONTA");
        lblTitulo.setBounds(288, 15, 350, 21);
        lblTitulo.setForeground(new Color(225, 225, 225));
        lblTitulo.setFont(new Font("Arial", 500, 20));

        JSeparator lblDivisao = new JSeparator();
        lblDivisao.setBounds(0, 51, 786, 1);
        lblDivisao.setForeground(new Color(30, 30, 30));
        
        JLabel lblSubtituloPessoal = new JLabel("Informações pessoais");
        lblSubtituloPessoal.setBounds(14, 66, 176, 21);
        lblSubtituloPessoal.setForeground(new Color(225, 225, 225));
        lblSubtituloPessoal.setFont(new Font("Arial", 500, 18));
        
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
        
        JLabel lblSubtituloBancario = new JLabel("Informações Bancárias");
        lblSubtituloBancario.setBounds(380, 66, 183, 21);
        lblSubtituloBancario.setForeground(new Color(225, 225, 225));
        lblSubtituloBancario.setFont(new Font("Arial", 500, 18));
        
        JLabel lblNumeroConta = new JLabel("Nº de conta");
        lblNumeroConta.setBounds(391, 95, 79, 17);
        lblNumeroConta.setForeground(new Color(225, 225, 225));
        
        JTextField txtNumeroConta = new JTextField();
        txtNumeroConta.setBounds(380, 113, 360, 30);
        txtNumeroConta.setBackground(new Color(82,82,82));
        txtNumeroConta.setBorder(null);
        txtNumeroConta.setEditable(false);
        txtNumeroConta.setText("Gerado automaticamente");
        
        JLabel lblSenhaConta = new JLabel("Senha da conta");
        lblSenhaConta.setBounds(391, 153, 104, 17);
        lblSenhaConta.setForeground(new Color(225, 225, 225));
        
        JTextField txtSenhaConta = new JTextField();
        txtSenhaConta.setBounds(380, 171, 360, 30);
        txtSenhaConta.setBackground(new Color(82,82,82));
        txtSenhaConta.setBorder(null);

        JLabel lblTipoConta = new JLabel("Tipo de conta");
        lblTipoConta.setBounds(391, 211, 92, 17);
        lblTipoConta.setForeground(new Color(225, 225, 225));
        
        String[] tiposDeConta = { "Poupança", "Corrente" };
        JComboBox boxTipoConta = new JComboBox(tiposDeConta);
        boxTipoConta.setBounds(380, 229, 360, 30);
        boxTipoConta.setBackground(new Color(82,82,82));
        boxTipoConta.setBorder(null);
        boxTipoConta.setSelectedIndex(0);
        boxTipoConta.addActionListener(e -> {
            System.out.println(e.toString());
        });
        
        JLabel lblStatusConta = new JLabel("Status da conta");
        lblStatusConta.setBounds(391, 269, 108, 17);
        lblStatusConta.setForeground(new Color(225, 225, 225));
        
        String[] statusDeConta = { "ATIVADA", "DESATIVADA" };
        JComboBox boxStatusConta = new JComboBox(statusDeConta);
        boxStatusConta.setBounds(380, 286, 160, 30);
        boxStatusConta.setBackground(new Color(82,82,82));
        boxStatusConta.setBorder(null);
        boxStatusConta.setSelectedIndex(0);
        boxStatusConta.addActionListener(e -> {
            System.out.println(e.paramString());
        });
        
        JButton btnCancelar = new JButton();
        btnCancelar.setText("CANCELAR");
        btnCancelar.setBounds(448, 364, 107, 33);
        btnCancelar.setBorder(null);
        btnCancelar.setBackground(new Color(82,82,82));
        btnCancelar.setForeground(new Color(225,225,225));
        btnCancelar.addActionListener(e -> {
            frame.setVisible(false);
            entrar.show();
        });
        
        JButton btnContinuar = new JButton();
        btnContinuar.setText("CADASTRAR");
        btnContinuar.setBounds(580, 364, 144, 33);
        btnContinuar.setBorder(null);
        btnContinuar.setBackground(new Color(6, 104, 6));
        btnContinuar.setForeground(new Color(225,225,225));
        btnContinuar.addActionListener(e -> {
            try {
                float rM = Float.parseFloat(txtRendaMensal.getText());
                java.util.Date lDate = new java.util.Date();
                conexao.Cadastrar(txtNome.getText(), 
                        txtCpf.getText(), 
                        txtEmail.getText(), 
                        txtSenha.getText(), 
                        lDate,
                        rM,
                        txtSenhaConta.getText(), 
                        boxTipoConta.getSelectedItem().toString(), 
                        boxStatusConta.getSelectedItem().toString()); 
            } catch (NumberFormatException  error) {
                System.out.println("Erro no botão -> "+error);
            } catch (SQLException ex) {
                Logger.getLogger(Cadastrar.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        panel.add(lblTitulo);
        panel.add(lblDivisao);
        panel.add(lblSubtituloPessoal);
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
        panel.add(lblSubtituloBancario);
        panel.add(lblNumeroConta);
        panel.add(txtNumeroConta);
        panel.add(lblSenhaConta);
        panel.add(txtSenhaConta);
        panel.add(lblTipoConta);
        panel.add(boxTipoConta);
        panel.add(lblStatusConta);
        panel.add(boxStatusConta);
        panel.add(btnCancelar);
        panel.add(btnContinuar);
        
        frame.add(panel);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
