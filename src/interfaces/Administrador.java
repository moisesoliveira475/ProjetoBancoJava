package interfaces;

import com.toedter.calendar.JDateChooser;

import modelos.TabelaUsuario;
import modelos.Usuario;

import conexao.*;
import componentes.*;

import java.awt.Color;
import java.awt.Toolkit;
import java.util.Date;
import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Administrador {

    TabelaUsuario tUsuario = new TabelaUsuario();

    Componentes componentes = new Componentes();
    Funcoes funcoes = new Funcoes();
    Partida main = new Partida();

    boolean isEditRow = false;
    boolean isAddUser = false;

    public void show() {
        JFrame frame = new JFrame("Administrador");
        frame.setSize(915, 530);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/assets/coffee.png")));

        JButton btnSair = new JButton();
        btnSair.setText("sair do painel administrativo");
        btnSair.setBounds(3, 3, 175, 14);
        btnSair.setBorder(null);
        btnSair.setBorderPainted(false);
        btnSair.setFocusPainted(false);
        btnSair.setBackground(new Color(35, 35, 35));
        btnSair.setForeground(new Color(220, 220, 220));
        btnSair.addActionListener(e -> {
            frame.setVisible(false);
            main.executeMain();
        });

        JTable jTUsuarios = new JTable();
        jTUsuarios.setModel(tUsuario);
        jTUsuarios.setEnabled(true);

        JScrollPane scroll = new JScrollPane();
        scroll.setViewportView(jTUsuarios);
        scroll.setBounds(0, 170, 900, 320);

        JLabel lblNome = new JLabel("Nome");
        lblNome.setBounds(25, 20, 40, 17);
        lblNome.setForeground(new Color(225, 225, 225));

        JTextField txtNome = new JTextField();
        txtNome.setBounds(20, 40, 205, 26);
        txtNome.setBackground(new Color(48, 48, 48));
        txtNome.setForeground(new Color(225, 225, 225));
        txtNome.setBorder(null);

        JLabel lblCpf = new JLabel("CPF(Apenas números)");
        lblCpf.setBounds(245, 20, 150, 17);
        lblCpf.setForeground(new Color(225, 225, 225));

        JTextField txtCpf = new JTextField();
        txtCpf.setBounds(240, 40, 205, 26);
        txtCpf.setBackground(new Color(48, 48, 48));
        txtCpf.setForeground(new Color(225, 225, 225));
        txtCpf.setBorder(null);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(465, 20, 36, 17);
        lblEmail.setForeground(new Color(225, 225, 225));

        JTextField txtEmail = new JTextField();
        txtEmail.setBounds(460, 40, 205, 26);
        txtEmail.setBackground(new Color(48, 48, 48));
        txtEmail.setForeground(new Color(225, 225, 225));
        txtEmail.setBorder(null);

        JLabel lblSenha = new JLabel("Senha, MAX: 12");
        lblSenha.setBounds(685, 20, 100, 17);
        lblSenha.setForeground(new Color(225, 225, 225));

        JTextField txtSenha = new JTextField();
        txtSenha.setBounds(680, 40, 205, 26);
        txtSenha.setBackground(new Color(48, 48, 48));
        txtSenha.setForeground(new Color(225, 225, 225));
        txtSenha.setBorder(null);

        JLabel lblRendaMensal = new JLabel("Renda Mensal");
        lblRendaMensal.setBounds(58, 70, 93, 17);
        lblRendaMensal.setForeground(new Color(225, 225, 225));

        JTextField txtRendaMensal = new JTextField();
        txtRendaMensal.setBounds(20, 89, 170, 26);
        txtRendaMensal.setBackground(new Color(48, 48, 48));
        txtRendaMensal.setForeground(new Color(225, 225, 225));
        txtRendaMensal.setBorder(null);

        JLabel lblDataNascimento = new JLabel("Data de nascimento");
        lblDataNascimento.setBounds(225, 70, 120, 17);
        lblDataNascimento.setForeground(new Color(225, 225, 225));
        lblDataNascimento.setBorder(null);

        JDateChooser calDataNascimento = new JDateChooser();
        calDataNascimento.setBounds(200, 89, 170, 26);

        JLabel lblSenhaConta = new JLabel("Senha da conta, MAX: 6");
        lblSenhaConta.setBounds(396, 70, 140, 17);
        lblSenhaConta.setForeground(new Color(225, 225, 225));
        lblSenhaConta.setVisible(false);

        JTextField txtSenhaConta = new JTextField();
        txtSenhaConta.setBounds(380, 89, 150, 26);
        txtSenhaConta.setBackground(new Color(48, 48, 48));
        txtSenhaConta.setForeground(new Color(225, 225, 225));
        txtSenhaConta.setBorder(null);
        txtSenhaConta.setVisible(false);

        JLabel lblTipoConta = new JLabel("Tipo conta");
        lblTipoConta.setBounds(560, 70, 93, 17);
        lblTipoConta.setForeground(new Color(225, 225, 225));
        lblTipoConta.setVisible(false);

        String[] tipoConta = {"Poupança", "Corrente"};
        JComboBox boxTipoConta = new JComboBox(tipoConta);
        boxTipoConta.setBounds(540, 89, 100, 26);
        boxTipoConta.setBackground(new Color(48, 48, 48));
        boxTipoConta.setForeground(new Color(225, 225, 225));
        boxTipoConta.setBorder(null);
        boxTipoConta.setVisible(false);

        JLabel lblStatusConta = new JLabel("Status");
        lblStatusConta.setBounds(670, 70, 93, 17);
        lblStatusConta.setForeground(new Color(225, 225, 225));
        lblStatusConta.setVisible(false);

        String[] statusConta = {"Desativada", "Ativada"};
        JComboBox boxStatusConta = new JComboBox(statusConta);
        boxStatusConta.setBounds(650, 89, 100, 26);
        boxStatusConta.setBackground(new Color(48, 48, 48));
        boxStatusConta.setForeground(new Color(225, 225, 225));
        boxStatusConta.setBorder(null);
        boxStatusConta.setVisible(false);

        JButton btnAdicionar = new JButton();
        btnAdicionar.setText("Adicionar");
        btnAdicionar.setBounds(20, 124, 90, 33);
        btnAdicionar.setBorder(null);
        btnAdicionar.setBackground(new Color(48, 48, 48));
        btnAdicionar.setForeground(new Color(225, 225, 225));
        btnAdicionar.addActionListener(e -> {
            if (txtNome.getText().isEmpty() || txtCpf.getText().isEmpty() || txtEmail.getText().isEmpty() || txtSenha.getText().isEmpty() || calDataNascimento.getDate() == null) {
                return;
            }
            if (isEditRow == true) {
                return;
            }
            isAddUser = true;

            try {
                String[] dadosConta = componentes.ShowInpusForCreateAccount();
                if ("1".equals(dadosConta[0])) {
                    isAddUser = false;
                    txtNome.setText("");
                    txtEmail.setText("");
                    txtSenha.setText("");
                    txtCpf.setText("");
                    calDataNascimento.setDate(null);
                    txtRendaMensal.setText("");
                    return;
                }

                Conexao conexao = new Conexao();
                boolean result = conexao.Cadastrar(
                        null,
                        txtNome.getText(),
                        txtCpf.getText(),
                        txtEmail.getText(),
                        txtSenha.getText(),
                        calDataNascimento.getDate(),
                        Float.parseFloat(txtRendaMensal.getText()),
                        dadosConta[0],
                        dadosConta[1],
                        dadosConta[2],
                        dadosConta[3]
                );

                if (result) {
                    atualizarLista();
                }

                isAddUser = false;
                txtNome.setText("");
                txtEmail.setText("");
                txtSenha.setText("");
                txtCpf.setText("");
                calDataNascimento.setDate(null);
                txtRendaMensal.setText("");
            } catch (SQLException ex) {
                Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
                isAddUser = false;
                txtNome.setText("");
                txtEmail.setText("");
                txtSenha.setText("");
                txtCpf.setText("");
                calDataNascimento.setDate(null);
                txtRendaMensal.setText("");
            }
        });

        JButton btnRemover = new JButton();
        btnRemover.setText("Remover");
        btnRemover.setBounds(120, 124, 90, 33);
        btnRemover.setBorder(null);
        btnRemover.setBackground(new Color(48, 48, 48));
        btnRemover.setForeground(new Color(225, 225, 225));
        btnRemover.addActionListener(e -> {
            int index = jTUsuarios.getSelectedRow();
            if (index == -1) {
                return;
            }

            boolean confirmarAcao = componentes.confirmarAcao("Realmente deseja apagar o usuário?");

            if (confirmarAcao) {
                try {
                    Conexao connection = new Conexao();
                    PreparedStatement psAcao;

                    String removerUsuario = "delete from usuarios where id_usuario=?;";
                    psAcao = connection.Conexao().prepareStatement(removerUsuario);
                    psAcao.setInt(1, (int) tUsuario.getValueAt(index, 0));

                    int rsUpdate = psAcao.executeUpdate();

                    if (rsUpdate == 1) {
                        String removerConta = "delete from contas where id_conta=?;";
                        psAcao = connection.Conexao().prepareStatement(removerConta);
                        psAcao.setInt(1, (int) tUsuario.getValueAt(index, 7));

                        rsUpdate = psAcao.executeUpdate();

                        if (rsUpdate == 1) {
                            tUsuario.removeRow(jTUsuarios.getSelectedRow());

                            txtNome.setText("");
                            txtEmail.setText("");
                            txtSenha.setText("");
                            txtCpf.setText("");
                            calDataNascimento.setDate(null);
                            txtRendaMensal.setText("");
                            boxTipoConta.setSelectedItem(0);
                            boxStatusConta.setSelectedIndex(0);

                            isEditRow = false;
                        }
                    }
                } catch (SQLException error) {
                    JOptionPane.showMessageDialog(null, "Erro ao apagar usuário: " + error.getMessage());
                }

            }
        });

        JButton btnEditar = new JButton();
        btnEditar.setText("Alterar");
        btnEditar.setBounds(220, 124, 90, 33);
        btnEditar.setBorder(null);
        btnEditar.setBackground(new Color(48, 48, 48));
        btnEditar.setForeground(new Color(225, 225, 225));
        btnEditar.addActionListener(e -> {
            int selectedLine = jTUsuarios.getSelectedRow();

            if (selectedLine != -1) {
                lblSenhaConta.setVisible(true);
                txtSenhaConta.setVisible(true);
                lblTipoConta.setVisible(true);
                boxTipoConta.setVisible(true);
                lblStatusConta.setVisible(true);
                boxStatusConta.setVisible(true);

                txtNome.setText((String) tUsuario.getValueAt(selectedLine, 1));
                txtEmail.setText((String) tUsuario.getValueAt(selectedLine, 2));
                txtSenha.setText((String) tUsuario.getValueAt(selectedLine, 3));
                txtCpf.setText((String) tUsuario.getValueAt(selectedLine, 4));
                calDataNascimento.setDate((Date) tUsuario.getValueAt(selectedLine, 5));
                txtRendaMensal.setText(tUsuario.getValueAt(selectedLine, 6).toString());
                txtSenhaConta.setText(tUsuario.getValueAt(selectedLine, 9).toString());
                int indexTipoConta = tUsuario.getValueAt(selectedLine, 10).toString().equals("p") ? 0 : 1;
                boxTipoConta.setSelectedIndex(indexTipoConta);
                boxStatusConta.setSelectedIndex(Integer.parseInt(tUsuario.getValueAt(selectedLine, 11).toString()));
                isEditRow = true;
            }
        });

        JButton btnSalvar = new JButton();
        btnSalvar.setText("Salvar");
        btnSalvar.setBounds(320, 124, 90, 33);
        btnSalvar.setBorder(null);
        btnSalvar.setBackground(new Color(48, 48, 48));
        btnSalvar.setForeground(new Color(225, 225, 225));
        btnSalvar.addActionListener(e -> {
            if (isEditRow == false || txtNome.getText().isEmpty() || txtCpf.getText().isEmpty() || txtEmail.getText().isEmpty()
                    || txtSenha.getText().isEmpty() || calDataNascimento.getDate() == null
                    || txtRendaMensal.getText().isEmpty() || txtSenhaConta.getText().isEmpty()) {
                return;
            } else if (isAddUser == true) {
                return;
            }

            int selectedLine = jTUsuarios.getSelectedRow();
            String selectedTipoConta = boxTipoConta.getSelectedIndex() == 0 ? "p" : "c";

            boolean result = funcoes.atualizarDadosUsuario(
                    txtNome.getText(), txtEmail.getText(), txtSenha.getText(), txtCpf.getText(),
                    calDataNascimento.getDate(), txtRendaMensal.getText(),
                    Integer.parseInt(tUsuario.getValueAt(selectedLine, 0).toString()),
                    txtSenhaConta.getText(), selectedTipoConta,
                    Integer.toString(boxStatusConta.getSelectedIndex()),
                    Integer.parseInt(tUsuario.getValueAt(selectedLine, 7).toString())
            );

            if (result) {
                tUsuario.setValueAt(txtNome.getText(), selectedLine, 1);
                tUsuario.setValueAt(txtEmail.getText(), selectedLine, 2);
                tUsuario.setValueAt(txtSenha.getText(), selectedLine, 3);
                tUsuario.setValueAt(txtCpf.getText(), selectedLine, 4);
                tUsuario.setValueAt(calDataNascimento.getDate(), selectedLine, 5);
                tUsuario.setValueAt(txtRendaMensal.getText(), selectedLine, 6);
                tUsuario.setValueAt(txtSenhaConta.getText(), selectedLine, 9);
                tUsuario.setValueAt(selectedTipoConta, selectedLine, 10);
                tUsuario.setValueAt(Integer.toString(boxStatusConta.getSelectedIndex()), selectedLine, 11);

                txtNome.setText("");
                txtEmail.setText("");
                txtSenha.setText("");
                txtCpf.setText("");
                calDataNascimento.setDate(null);
                txtRendaMensal.setText("");
                txtSenhaConta.setText("");
                boxTipoConta.setSelectedItem(0);
                boxStatusConta.setSelectedIndex(0);

                lblSenhaConta.setVisible(false);
                txtSenhaConta.setVisible(false);
                lblTipoConta.setVisible(false);
                boxTipoConta.setVisible(false);
                lblStatusConta.setVisible(false);
                boxStatusConta.setVisible(false);

                isEditRow = false;
            }
        });

        JButton btnAtualizar = new JButton();
        btnAtualizar.setText("Atualizar");
        btnAtualizar.setBounds(420, 124, 90, 33);
        btnAtualizar.setBorder(null);
        btnAtualizar.setBackground(new Color(48, 48, 48));
        btnAtualizar.setForeground(new Color(225, 225, 225));
        btnAtualizar.addActionListener(e -> {
            atualizarLista();
        });

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(35, 35, 35));

        panel.add(btnSair);
        panel.add(lblNome);
        panel.add(txtNome);
        panel.add(lblCpf);
        panel.add(txtCpf);
        panel.add(lblEmail);
        panel.add(txtEmail);
        panel.add(lblSenha);
        panel.add(txtSenha);
        panel.add(lblRendaMensal);
        panel.add(txtRendaMensal);
        panel.add(lblDataNascimento);
        panel.add(calDataNascimento);
        panel.add(lblSenhaConta);
        panel.add(txtSenhaConta);
        panel.add(lblTipoConta);
        panel.add(boxTipoConta);
        panel.add(lblStatusConta);
        panel.add(boxStatusConta);
        panel.add(btnAdicionar);
        panel.add(btnRemover);
        panel.add(btnEditar);
        panel.add(btnSalvar);
        panel.add(btnAtualizar);
        panel.add(scroll);

        frame.add(panel);
        frame.setResizable(false);
        frame.setVisible(true);

        atualizarLista();
    }

    private void atualizarLista() {
        tUsuario.clearList();

        Conexao connection = new Conexao();
        PreparedStatement psAcao;

        List<Integer> qtdUsuarios = new ArrayList<>();

        try {
            String buscarQtdUsuarios = "select id_usuario from usuarios order by id_usuario;";
            psAcao = connection.Conexao().prepareStatement(buscarQtdUsuarios);

            ResultSet rs;
            rs = psAcao.executeQuery();

            while (rs.next()) {
                qtdUsuarios.add(rs.getInt("id_usuario"));
            }
            for (Integer idUsuario : qtdUsuarios) {
                String buscarDados = "select u.id_usuario, u.nome, u.email, u.senha, u.cpf, u.data_nascimento, u.data_nascimento, "
                        + "u.renda_mensal, u.id_conta, c.numero, c.senha_conta, c.tipo, c.status, c.saldo from contas as c join "
                        + "usuarios as u where c.id_conta=u.id_conta and u.id_usuario=?;";
                psAcao = connection.Conexao().prepareStatement(buscarDados);
                psAcao.setInt(1, idUsuario);

                rs = psAcao.executeQuery();

                if (rs.next()) {
                    Usuario p = new Usuario();
                    p.setId(rs.getInt("id_usuario"));
                    p.setNome(rs.getString("nome"));
                    p.setEmail(rs.getString("email"));
                    p.setSenha(rs.getString("senha"));
                    p.setCpf(rs.getString("cpf"));
                    java.util.Date dataNConvertido = new java.sql.Date(rs.getDate("data_nascimento").getTime());
                    p.setDataNascimento(dataNConvertido);
                    p.setRendaMensal(rs.getFloat("renda_mensal"));
                    p.setIdConta(rs.getInt("id_conta"));

                    p.setNumeroConta(rs.getString("numero"));
                    p.setSenhaConta(rs.getString("senha_conta"));
                    p.setTipoConta(rs.getString("tipo"));
                    p.setStatusConta(rs.getString("status"));
                    p.setSaldoConta(rs.getFloat("saldo"));

                    tUsuario.addRow(p);
                }
            }

        } catch (SQLException er) {
            JOptionPane.showMessageDialog(null, "Algo deu errado ao atualizar lista");
        }
    }
}
