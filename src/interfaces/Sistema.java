package interfaces;

import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexao.*;
import componentes.Componentes;
import utils.*;

import modelos.*;

public class Sistema {

    ImageIcon avatar = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/assets/avatar.png")));
    ImageIcon moneyCheck = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/assets/money-check-alt.png")));
    ImageIcon chevronUp = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/assets/chevron-up.png")));
    ImageIcon chevronDown = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/assets/chevron-down.png")));
    ImageIcon plusGreen = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/assets/plus-green.png")));
    ImageIcon navigation = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/assets/navigation.png")));
    ImageIcon lightbulb = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/assets/lightbulb.png")));
    ImageIcon star = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/assets/star.png")));
    ImageIcon plusBlue = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/assets/plus-blue.png")));
    ImageIcon history = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/assets/history.png")));
    ImageIcon minus = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/assets/minus.png")));

    Funcoes funcoes = new Funcoes();
    Componentes componentes = new Componentes();
    Utils utils = new Utils();

    TabelaMovimentacao tMovimentacao = new TabelaMovimentacao();
    TabelaGastos tGastos = new TabelaGastos();
    TabelaMetas tMetas = new TabelaMetas();

    java.util.List<Float> gastosContas = new ArrayList<>();

    public void show(DadosUsuario dadosUsuario) {
        tMovimentacao.clearList();
        tMetas.clearList();

        JFrame frame = new JFrame("Tela principal");
        frame.setSize(900, 730);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/assets/coffee.png")));

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(35, 35, 35));

        JButton btnSair = new JButton("*Sair da conta");
        btnSair.setBounds(5, 5, 90, 20);
        btnSair.setForeground(new Color(225, 225, 225));
        btnSair.setBackground(new Color(35, 35, 35));
        btnSair.setFont(new Font("Arial", 500, 12));
        btnSair.setBorder(null);
        btnSair.addActionListener(e -> {
            Entrar entrar = new Entrar();
            frame.setVisible(false);
            entrar.show(dadosUsuario);
        });

        JLabel lblAvatar = new JLabel(avatar);
        lblAvatar.setBounds(20, 30, 50, 50);

        JLabel lblNome = new JLabel(dadosUsuario.getNome());
        lblNome.setBounds(72, 45, 253, 19);
        lblNome.setForeground(new Color(225, 225, 225));
        lblNome.setFont(new Font("Arial", 500, 20));

        JLabel lblSaldoConta = new JLabel("");
        lblSaldoConta.setText("Saldo disponível: R$ " + dadosUsuario.getSaldoConta());
        lblSaldoConta.setBounds(620, 41, 300, 28);
        lblSaldoConta.setForeground(new Color(0, 211, 0));
        lblSaldoConta.setFont(new Font("Arial", 800, 20));
        lblSaldoConta.setBorder(null);

        JSeparator lblDivisao = new JSeparator();
        lblDivisao.setBounds(0, 90, 900, 1);
        lblDivisao.setForeground(new Color(30, 30, 30));

        JLabel lblMoneyCheck = new JLabel(moneyCheck);
        lblMoneyCheck.setBounds(20, 105, 18, 18);

        String tipoContaConvertido = dadosUsuario.getTipoConta().equals("p") ? "Poupança" : "Corrente";
        JLabel lblTituloConta = new JLabel("Conta " + tipoContaConvertido + ": " + dadosUsuario.getNumeroConta());
        lblTituloConta.setBounds(45, 105, 300, 19);
        lblTituloConta.setForeground(new Color(225, 225, 225));
        lblTituloConta.setFont(new Font("Arial", 500, 20));

        JButton btnDeposito = new JButton();
        btnDeposito.setBounds(24, 130, 148, 30);
        btnDeposito.setBackground(new Color(48, 48, 48));
        btnDeposito.setForeground(new Color(255, 255, 255));
        btnDeposito.setIcon(chevronUp);
        btnDeposito.setText("Fazer depósito");
        btnDeposito.setIconTextGap(5);
        btnDeposito.setBorder(null);
        btnDeposito.setMargin(new Insets(0, 8, 0, 12));
        btnDeposito.addActionListener(btnDepositoE -> {
            try {
                if (dadosUsuario.getStatusConta().equals("0")) {
                    JOptionPane.showMessageDialog(null, "Sua conta está bloqueada para essa ação\ncontate o administrador para mais informações!");
                    return;
                }

                String entradaDeSenha = componentes.ShowInputPassword("Digite a senha de acesso");
                if (entradaDeSenha != null && entradaDeSenha.equals(dadosUsuario.getSenha())) {
                    String entradaDeValor = JOptionPane.showInputDialog(null, "Digite o valor a depositar");
                    float valorConvertido = Float.parseFloat(entradaDeValor);

                    if (entradaDeValor.isEmpty() || valorConvertido == 0) {
                        return;
                    }

                    boolean isSucess = funcoes.FazerDeposito(
                            dadosUsuario.getId_conta(), dadosUsuario.getId(), dadosUsuario.getSaldoConta() + valorConvertido,
                            valorConvertido);
                    if (isSucess) {
                        dadosUsuario.setSaldoConta(dadosUsuario.getSaldoConta() + valorConvertido);
                        lblSaldoConta.setText("Saldo disponível: R$ " + dadosUsuario.getSaldoConta());
                        atualizarMovimentacoes(dadosUsuario.getId());
                    }
                }
            } catch (HeadlessException | NumberFormatException e) {
            }
        });

        JButton btnSaque = new JButton();
        btnSaque.setBounds(180, 130, 148, 30);
        btnSaque.setBackground(new Color(48, 48, 48));
        btnSaque.setForeground(new Color(255, 255, 255));
        btnSaque.setIcon(chevronDown);
        btnSaque.setText("Fazer saque");
        btnSaque.setIconTextGap(5);
        btnSaque.setBorder(null);
        btnSaque.setMargin(new Insets(0, 8, 0, 12));
        btnSaque.addActionListener(btnSaqueE -> {
            try {
                String entradaDeSenha = componentes.ShowInputPassword("Digite a senha de acesso");
                if (entradaDeSenha != null && entradaDeSenha.equals(dadosUsuario.getSenha())) {
                    String entradaDeValor = JOptionPane.showInputDialog(null, "Digite o valor a sacar");
                    float valorConvertido = Float.parseFloat(entradaDeValor);

                    if (entradaDeValor.isEmpty() || valorConvertido == 0) {
                        return;
                    }

                    if (dadosUsuario.getSaldoConta() < valorConvertido) {
                        JOptionPane.showMessageDialog(null, "Você não tem saldo suficiente!");
                        return;
                    }

                    boolean isSucess = funcoes.FazerSaque(dadosUsuario.getId_conta(), dadosUsuario.getId(), (dadosUsuario.getSaldoConta() - valorConvertido));
                    if (isSucess) {
                        dadosUsuario.setSaldoConta((dadosUsuario.getSaldoConta() - valorConvertido));
                        lblSaldoConta.setText("Saldo disponível: R$ " + dadosUsuario.getSaldoConta());
                        JOptionPane.showMessageDialog(null, "Saque realizado com sucesso!");
                        atualizarMovimentacoes(dadosUsuario.getId());
                    }
                }
            } catch (HeadlessException | NumberFormatException e) {
            }
        });

        JButton btnAdicionarCompra = new JButton();
        btnAdicionarCompra.setBounds(24, 165, 148, 30);
        btnAdicionarCompra.setBackground(new Color(48, 48, 48));
        btnAdicionarCompra.setForeground(new Color(255, 255, 255));
        btnAdicionarCompra.setIcon(plusGreen);
        btnAdicionarCompra.setText("Registrar compra");
        btnAdicionarCompra.setIconTextGap(5);
        btnAdicionarCompra.setBorder(null);
        btnAdicionarCompra.setMargin(new Insets(0, 8, 0, 12));
        btnAdicionarCompra.addActionListener(btnAdicionarCompraE -> {
            try {
                String entradaDeSenha = componentes.ShowInputPassword("Digite a senha de acesso");
                if (entradaDeSenha != null && entradaDeSenha.equals(dadosUsuario.getSenha())) {

                    String[] showInputs = componentes.ShowTwoInputsAndDate("Titulo do gasto", "Valor ex(1, 1.5)");

                    float valorConvertido = Float.parseFloat(showInputs[1]);

                    if (showInputs[0].isEmpty() || valorConvertido == 0) {
                        return;
                    }

                    java.util.Date data = new SimpleDateFormat("dd/MM/yyyy").parse(showInputs[2]);
                    boolean isSucess = funcoes.RegistrarCompra(dadosUsuario.getId(), data, showInputs[0], valorConvertido);
                    if (isSucess) {
                        JOptionPane.showMessageDialog(null, "compra registrada com sucesso!");
                        atualizarMovimentacoes(dadosUsuario.getId());
                    }
                }
            } catch (HeadlessException | NumberFormatException e) {
            } catch (ParseException ex) {
                Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        JButton btnTransferir = new JButton();
        btnTransferir.setBounds(200, 165, 110, 30);
        btnTransferir.setBackground(new Color(48, 48, 48));
        btnTransferir.setForeground(new Color(255, 255, 255));
        btnTransferir.setIcon(navigation);
        btnTransferir.setText(" Transferir");
        btnTransferir.setIconTextGap(5);
        btnTransferir.setBorder(null);
        btnTransferir.setMargin(new Insets(0, 8, 0, 12));
        btnTransferir.addActionListener(btnTransferirE -> {
            if (dadosUsuario.getStatusConta().equals("0")) {
                JOptionPane.showMessageDialog(null, "Sua conta está bloqueada para essa ação\ncontate o administrador para mais informações!");
                return;
            }

            try {
                String entradaDeSenha = componentes.ShowInputPassword("Digite a senha de acesso");
                if (entradaDeSenha != null && entradaDeSenha.equals(dadosUsuario.getSenha())) {
                    String[] entradaDeValor = componentes.ShowTwoInputs("Valor da tranferência:", "Número da conta:");
                    float valorConvertido = Float.parseFloat(entradaDeValor[0]);

                    if (entradaDeValor[0].isEmpty() || entradaDeValor[1].isEmpty() || valorConvertido == 0) {
                        return;
                    }

                    if (dadosUsuario.getSaldoConta() < valorConvertido) {
                        JOptionPane.showMessageDialog(null, "Você não tem saldo suficiente!");
                        return;
                    }

                    boolean isSucess = funcoes.FazerTransferencia(dadosUsuario, entradaDeValor[1], valorConvertido);
                    if (isSucess) {
                        lblSaldoConta.setText("Saldo disponível: R$ " + dadosUsuario.getSaldoConta());
                        JOptionPane.showMessageDialog(null, "Transferência enviada para a conta " + entradaDeValor[1] + " com sucesso!");
                        atualizarMovimentacoes(dadosUsuario.getId());
                    }
                }
            } catch (HeadlessException | NumberFormatException e) {
            }
        });

        JSeparator lblDivisao2 = new JSeparator();
        lblDivisao2.setBounds(0, 211, 400, 1);
        lblDivisao2.setForeground(new Color(30, 30, 30));

        JLabel lblLightBulb = new JLabel(lightbulb);
        lblLightBulb.setBounds(20, 221, 18, 18);

        JLabel lblTituloGerenciamento = new JLabel("Gerenciamento");
        lblTituloGerenciamento.setBounds(45, 221, 190, 19);
        lblTituloGerenciamento.setForeground(new Color(225, 225, 225));
        lblTituloGerenciamento.setFont(new Font("Arial", 500, 20));

        JLabel lblRendaMensal = new JLabel("Renda mensal: R$ " + dadosUsuario.getRenda_mensal());
        lblRendaMensal.setBounds(10, 10, 260, 19);
        lblRendaMensal.setForeground(new Color(225, 225, 225));
        lblRendaMensal.setFont(new Font("Arial", 500, 17));

        JButton btnMudarRendaMensal = new JButton();
        btnMudarRendaMensal.setBounds(10, 35, 170, 25);
        btnMudarRendaMensal.setBackground(new java.awt.Color(35, 35, 35));
        btnMudarRendaMensal.setForeground(new java.awt.Color(225, 225, 225));
        btnMudarRendaMensal.setText("ALTERAR RENDA");
        btnMudarRendaMensal.setIconTextGap(0);
        btnMudarRendaMensal.setBorder(null);
        btnMudarRendaMensal.setMargin(new Insets(0, 8, 0, 12));
        btnMudarRendaMensal.addActionListener(btnRendaMensalE -> {
            try {
                String entradaDeValor = (String) JOptionPane.showInputDialog(
                        null,
                        "Digite a nova renda mensal",
                        "Alterar renda",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        "0"
                );

                if (entradaDeValor.isEmpty() || entradaDeValor.equals("0")) {
                    return;
                }

                float valorConvertido = Float.parseFloat(entradaDeValor);

                boolean isSucess = funcoes.MudarRendaMensal(dadosUsuario.getId(), valorConvertido);
                if (isSucess) {
                    dadosUsuario.setRenda_mensal(valorConvertido);
                    lblRendaMensal.setText("Renda mensal: R$ " + entradaDeValor);
                }
            } catch (NullPointerException | NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "erro ao alterar renda mensal: " + e.getMessage());
            }
        });

        JPanel panelRenda = new JPanel();
        panelRenda.setLayout(null);
        panelRenda.setBounds(24, 250, 220, 70);
        panelRenda.setBackground(new Color(48, 48, 48));
        panelRenda.add(lblRendaMensal);
        panelRenda.add(btnMudarRendaMensal);

        JLabel lblEstimativaDeGastos = new JLabel("Estimativa de gastos essenciais");
        lblEstimativaDeGastos.setBounds(10, 10, 260, 19);
        lblEstimativaDeGastos.setForeground(new Color(225, 225, 225));
        lblEstimativaDeGastos.setFont(new Font("Arial", 500, 17));

        JTable tableGastosEssenciais = new JTable();
        tableGastosEssenciais.setModel(tGastos);
        tableGastosEssenciais.setEnabled(true);
        tableGastosEssenciais.setCellSelectionEnabled(false);
        tableGastosEssenciais.setFont(new Font("Arial", 500, 14));
        tableGastosEssenciais.setRowHeight(25);

        JScrollPane scrollGastosEssenciais = new JScrollPane();
        scrollGastosEssenciais.setViewportView(tableGastosEssenciais);
        scrollGastosEssenciais.setBounds(13, 30, 340, 105);
        scrollGastosEssenciais.setBackground(new Color(35, 35, 35));
        scrollGastosEssenciais.setForeground(new Color(225, 225, 225));

        JButton btnAdicionarGasto = new JButton();
        btnAdicionarGasto.setBounds(10, 140, 100, 20);
        btnAdicionarGasto.setBackground(new Color(35, 35, 35));
        btnAdicionarGasto.setForeground(new Color(225, 225, 225));
        btnAdicionarGasto.setText("adicionar gasto");
        btnAdicionarGasto.setIconTextGap(0);
        btnAdicionarGasto.setBorder(null);
        btnAdicionarGasto.setMargin(new java.awt.Insets(0, 8, 0, 12));
        btnAdicionarGasto.addActionListener(btnAdicionarGastoE -> {
            try {
                String[] showInputs = componentes.ShowTwoInputs("Titulo do gasto", "Valor ex(1, 1.5)");

                if (showInputs[0].isEmpty() || showInputs[1].isEmpty()) {
                    return;
                }

                float valorConvertido = Float.parseFloat(showInputs[1]);

                boolean isSucess = funcoes.AdicionarGastoEssencial(dadosUsuario.getId(), showInputs[0], valorConvertido);
                if (isSucess) {
                    JOptionPane.showMessageDialog(null, "gasto registrado com sucesso!");
                    atualizarGastos(dadosUsuario.getId());
                }
            } catch (HeadlessException | NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Erro ao adicionar gasto essencial: " + e.getMessage());
            }
        });

        JButton btnRemoverGasto = new JButton();
        btnRemoverGasto.setBounds(120, 140, 100, 20);
        btnRemoverGasto.setBackground(new Color(35, 35, 35));
        btnRemoverGasto.setForeground(new Color(225, 225, 225));
        btnRemoverGasto.setText("remover gasto");
        btnRemoverGasto.setIconTextGap(0);
        btnRemoverGasto.setBorder(null);
        btnRemoverGasto.setMargin(new Insets(0, 8, 0, 12));
        btnRemoverGasto.addActionListener(e -> {
            int index = tableGastosEssenciais.getSelectedRow();
            if (index == -1) {
                return;
            }

            boolean confirmarAcao = componentes.confirmarAcao("Realmente deseja apagar o gasto?");

            if (confirmarAcao) {
                try {
                    Conexao connection = new Conexao();
                    PreparedStatement psAcao;

                    String buscarGasto = "select ug.id_gasto from usuarios as u "
                            + "join gastos as g join usuarios_registram_gastos as ug "
                            + "where u.id_usuario=ug.id_usuario and ug.id_gasto=g.id_gasto and ug.id_usuarios_registram_gastos=? "
                            + "order by ug.id_usuarios_registram_gastos desc;";
                    psAcao = connection.Conexao().prepareStatement(buscarGasto);
                    psAcao.setInt(1, (int) tableGastosEssenciais.getValueAt(index, 0));

                    ResultSet rsBuscarGasto = psAcao.executeQuery();

                    if (rsBuscarGasto.next()) {
                        String removerUGasto = "delete from usuarios_registram_gastos where id_usuarios_registram_gastos=?;";
                        psAcao = connection.Conexao().prepareStatement(removerUGasto);
                        psAcao.setInt(1, (int) tableGastosEssenciais.getValueAt(index, 0));

                        int rsUGastoUpdate = psAcao.executeUpdate();

                        if (rsUGastoUpdate == 1) {
                            String removerGasto = "delete from gastos where id_gasto=?;";
                            psAcao = connection.Conexao().prepareStatement(removerGasto);
                            psAcao.setInt(1, rsBuscarGasto.getInt("id_gasto"));

                            rsUGastoUpdate = psAcao.executeUpdate();

                            if (rsUGastoUpdate == 1) {
                                JOptionPane.showMessageDialog(null, "Gasto apagado com sucesso.");
                                tGastos.removeRow(index);
                            }
                        }
                    }

                } catch (SQLException error) {
                    JOptionPane.showMessageDialog(null, "Erro ao apagar gasto: " + error.getMessage());
                }

            }
        });

        JButton btnCalcularGasto = new JButton();
        btnCalcularGasto.setBounds(230, 140, 100, 20);
        btnCalcularGasto.setBackground(new Color(35, 35, 35));
        btnCalcularGasto.setForeground(new Color(225, 225, 225));
        btnCalcularGasto.setText("calcular gastos");
        btnCalcularGasto.setIconTextGap(0);
        btnCalcularGasto.setBorder(null);
        btnCalcularGasto.setMargin(new Insets(0, 8, 0, 12));
        btnCalcularGasto.addActionListener(btnCalcularGastoE -> {
            float result = utils.CalculoGastos(gastosContas);
            JOptionPane.showMessageDialog(
                    null,
                    "A estimativa de gastos essenciais vai dar ao todo,\n--> R$ " + result + " Reais",
                    "Calculo dos gastos",
                    JOptionPane.PLAIN_MESSAGE,
                    lightbulb);
        });

        JPanel panelGastos = new JPanel();
        panelGastos.setLayout(null);
        panelGastos.setBounds(24, 325, 360, 170);
        panelGastos.setBackground(new Color(48, 48, 48));
        panelGastos.add(lblEstimativaDeGastos);
        panelGastos.add(scrollGastosEssenciais);
        panelGastos.add(btnAdicionarGasto);
        panelGastos.add(btnRemoverGasto);
        panelGastos.add(btnCalcularGasto);

        JSeparator lblDivisao3 = new JSeparator();
        lblDivisao3.setBounds(0, 500, 400, 1);
        lblDivisao3.setForeground(new Color(30, 30, 30));

        JLabel lblStar = new JLabel(star);
        lblStar.setBounds(20, 510, 18, 18);

        JLabel lblTituloMetas = new JLabel("Metas");
        lblTituloMetas.setBounds(45, 510, 100, 19);
        lblTituloMetas.setForeground(new Color(225, 225, 225));
        lblTituloMetas.setFont(new Font("Arial", 500, 20));
        
        JTable tableMetas = new JTable();
        tableMetas.setModel(tMetas);
        tableMetas.setEnabled(true);
        tableMetas.setCellSelectionEnabled(false);
        tableMetas.setFont(new Font("Arial", 500, 12));
        tableMetas.setRowHeight(25);

        JScrollPane scrollMetas = new JScrollPane();
        scrollMetas.setViewportView(tableMetas);
        scrollMetas.setBounds(10, 540, 380, 130);
        scrollMetas.setBackground(new Color(35, 35, 35));
        scrollMetas.setForeground(new Color(225, 225, 225));

        JButton btnAddMeta = new JButton();
        btnAddMeta.setBounds(110, 505, 30, 28);
        btnAddMeta.setBackground(new Color(48, 48, 48));
        btnAddMeta.setForeground(new Color(255, 255, 255));
        btnAddMeta.setIcon(plusBlue);
        btnAddMeta.setIconTextGap(5);
        btnAddMeta.setBorder(null);
        btnAddMeta.setMargin(new Insets(0, 8, 0, 12));
        btnAddMeta.addActionListener(addMetaE -> {
            String[] results = componentes.ShowInpusForAddGoal();

            if (results == null) {
                return;
            }

            try {
                boolean result;

                result = funcoes.AdicionarMeta(
                        dadosUsuario.getId(),
                        results[0], new SimpleDateFormat("dd/MM/yyyy").parse(results[1]),
                        Float.parseFloat(results[2]),
                        Float.parseFloat(results[3])
                );

                if (result) {
                    JOptionPane.showMessageDialog(null, "meta adicionada com sucesso!");
                    atualizarMetas(dadosUsuario.getId());
                }
            } catch (ParseException ex) {
                Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        JButton btnDelMeta = new JButton();
        btnDelMeta.setBounds(150, 505, 30, 28);
        btnDelMeta.setBackground(new Color(48, 48, 48));
        btnDelMeta.setForeground(new Color(255, 255, 255));
        btnDelMeta.setIcon(chevronUp);
        btnDelMeta.setIconTextGap(2);
        btnDelMeta.setBorder(null);
        btnDelMeta.setMargin(new Insets(0, 8, 0, 12));

        JButton btnEditarMeta = new JButton();
        btnEditarMeta.setBounds(190, 505, 30, 28);
        btnEditarMeta.setBackground(new Color(48, 48, 48));
        btnEditarMeta.setForeground(new Color(255, 255, 255));
        btnEditarMeta.setIcon(minus);
        btnEditarMeta.setIconTextGap(2);
        btnEditarMeta.setBorder(null);
        btnEditarMeta.setMargin(new Insets(0, 8, 0, 12));
        btnEditarMeta.addActionListener(e -> {
            int index = tableMetas.getSelectedRow();
            if (index == -1) {
                return;
            }

            boolean confirmarAcao = componentes.confirmarAcao("Realmente deseja apagar a meta?");

            if (confirmarAcao) {
                try {
                    Conexao connection = new Conexao();
                    PreparedStatement psAcao;

                    String buscarMeta = "select um.id_meta from usuarios as u join metas as m join usuarios_criam_metas as um "
                            + "where u.id_usuario=um.id_usuario and um.id_meta=m.id_meta and um.id_usuarios_criam_metas=?;";
                    psAcao = connection.Conexao().prepareStatement(buscarMeta);
                    psAcao.setInt(1, (int) tableMetas.getValueAt(index, 0));

                    ResultSet rsBuscarMeta = psAcao.executeQuery();

                    if (rsBuscarMeta.next()) {
                        String removerUGasto = "delete from usuarios_criam_metas where id_usuarios_criam_metas=?;";
                        psAcao = connection.Conexao().prepareStatement(removerUGasto);
                        psAcao.setInt(1, (int) tableMetas.getValueAt(index, 0));

                        int rsUGastoUpdate = psAcao.executeUpdate();

                        if (rsUGastoUpdate == 1) {
                            String removerMeta = "delete from metas where id_meta=?;";
                            psAcao = connection.Conexao().prepareStatement(removerMeta);
                            psAcao.setInt(1, rsBuscarMeta.getInt("id_meta"));

                            rsUGastoUpdate = psAcao.executeUpdate();

                            if (rsUGastoUpdate == 1) {
                                JOptionPane.showMessageDialog(null, "meta apagada com sucesso.");
                                tMetas.removeRow(index);
                            }
                        }
                    }

                } catch (SQLException error) {
                    JOptionPane.showMessageDialog(null, "Erro ao apagar meta: " + error.getMessage());
                }

            }
        });

        JSeparator lblDivisao4 = new JSeparator();
        lblDivisao4.setForeground(new Color(30, 30, 30));
        lblDivisao4.setBounds(400, 90, 1, 750);
        lblDivisao4.setOrientation(SwingConstants.VERTICAL);
        lblDivisao4.setMaximumSize(new Dimension(800, 750));

        JButton btnHistory = new JButton();
        btnHistory.setBounds(410, 98, 18, 18);
        btnHistory.setBackground(new Color(35, 35, 35));
        btnHistory.setForeground(new Color(255, 255, 255));
        btnHistory.setIcon(history);
        btnHistory.setIconTextGap(2);
        btnHistory.setBorder(null);
        btnHistory.setMargin(new Insets(0, 8, 0, 12));
        btnHistory.addActionListener(e -> {
            atualizarMovimentacoes(dadosUsuario.getId());
        });

        JLabel lblTituloHistorico = new JLabel("Histórico de movimentações");
        lblTituloHistorico.setBounds(440, 98, 360, 19);
        lblTituloHistorico.setForeground(new Color(225, 225, 225));
        lblTituloHistorico.setFont(new Font("Arial", 500, 20));

        JSeparator lblDivisao5 = new JSeparator();
        lblDivisao5.setForeground(new Color(30, 30, 30));
        lblDivisao5.setBounds(400, 125, 500, 1);
        lblDivisao5.setMaximumSize(new Dimension(800, 750));

        JTable tableMovimentacoes = new JTable();
        tableMovimentacoes.setModel(tMovimentacao);
        tableMovimentacoes.setEnabled(true);
        tableMovimentacoes.setCellSelectionEnabled(false);
        tableMovimentacoes.setFont(new Font("Arial", 500, 16));
        tableMovimentacoes.setRowHeight(25);

        JScrollPane scrollMovimentacoes = new JScrollPane();
        scrollMovimentacoes.setViewportView(tableMovimentacoes);
        scrollMovimentacoes.setBounds(400, 125, 500, 565);
        scrollMovimentacoes.setBackground(new Color(35, 35, 35));
        scrollMovimentacoes.setForeground(new Color(225, 225, 225));
        scrollMovimentacoes.setFont(new Font("Arial", 500, 16));

        panel.add(btnSair);
        panel.add(lblAvatar);
        panel.add(lblNome);
        panel.add(lblSaldoConta);
        panel.add(lblDivisao);
        panel.add(lblMoneyCheck);
        panel.add(lblTituloConta);
        panel.add(btnDeposito);
        panel.add(btnSaque);
        panel.add(btnAdicionarCompra);
        panel.add(btnTransferir);
        panel.add(lblDivisao2);
        panel.add(lblLightBulb);
        panel.add(lblTituloGerenciamento);
        panel.add(panelRenda);
        panel.add(panelGastos);
        panel.add(lblStar);
        panel.add(lblDivisao3);
        panel.add(lblTituloMetas);
        panel.add(btnAddMeta);
        panel.add(btnDelMeta);
        panel.add(btnEditarMeta);
        panel.add(scrollMetas);

        panel.add(lblDivisao4);
        panel.add(btnHistory);
        panel.add(lblTituloHistorico);
        panel.add(lblDivisao5);
        panel.add(scrollMovimentacoes);

        frame.add(panel);
        frame.setResizable(false);
        frame.setVisible(true);

        atualizarMovimentacoes(dadosUsuario.getId());
        atualizarGastos(dadosUsuario.getId());
        atualizarMetas(dadosUsuario.getId());
    }

    private void atualizarMovimentacoes(int IdUsuario) {
        tMovimentacao.clearList();

        Conexao connection = new Conexao();
        PreparedStatement psAcao;

        java.util.List<Integer> IdsMovimentacoes = new ArrayList<>();

        try {
            String buscarIdsMovimentacoes = "select um.id_usuarios_fazem_movimentacoes from usuarios as u "
                    + "join movimentacoes as m join usuarios_fazem_movimentacoes as um "
                    + "where u.id_usuario=um.id_usuario and um.id_movimentacao=m.id_movimentacao and um.id_usuario=? "
                    + "order by um.id_usuarios_fazem_movimentacoes desc;";
            psAcao = connection.Conexao().prepareStatement(buscarIdsMovimentacoes);
            psAcao.setInt(1, IdUsuario);

            ResultSet rs;
            rs = psAcao.executeQuery();

            while (rs.next()) {
                IdsMovimentacoes.add(rs.getInt("id_usuarios_fazem_movimentacoes"));
            }
            for (Integer idMovimentacao : IdsMovimentacoes) {
                String buscarMovimentacao = "select um.id_usuarios_fazem_movimentacoes, m.data_criacao, m.titulo, m.valor from usuarios as u "
                        + "join movimentacoes as m join usuarios_fazem_movimentacoes as um "
                        + "where u.id_usuario=um.id_usuario and um.id_movimentacao=m.id_movimentacao "
                        + "and um.id_usuarios_fazem_movimentacoes=? order by data_criacao desc;";
                psAcao = connection.Conexao().prepareStatement(buscarMovimentacao);
                psAcao.setInt(1, idMovimentacao);
                rs = psAcao.executeQuery();

                if (rs.next()) {
                    Movimentacao m = new Movimentacao();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    java.util.Date dataNConvertido = new java.sql.Date(rs.getDate("data_criacao").getTime());

                    m.setId(rs.getInt("id_usuarios_fazem_movimentacoes"));
                    m.setDataCriacao(dateFormat.format(dataNConvertido));
                    m.setTitulo(rs.getString("titulo"));
                    m.setValor(rs.getFloat("valor"));

                    tMovimentacao.addRow(m);
                }
            }

        } catch (SQLException er) {
            JOptionPane.showMessageDialog(null, "Algo deu errado ao atualizar as movimentações");
        }
    }

    private void atualizarGastos(int IdUsuario) {
        tGastos.clearList();

        Conexao connection = new Conexao();
        PreparedStatement psAcao;

        java.util.List<Integer> idsGastos = new ArrayList<>();

        try {
            String buscarIdsGastos = "select ug.id_usuarios_registram_gastos from usuarios as u "
                    + "join gastos as g join usuarios_registram_gastos as ug "
                    + "where u.id_usuario=ug.id_usuario and ug.id_gasto=g.id_gasto and ug.id_usuario=? "
                    + "order by ug.id_usuarios_registram_gastos desc;";
            psAcao = connection.Conexao().prepareStatement(buscarIdsGastos);
            psAcao.setInt(1, IdUsuario);

            ResultSet rs;
            rs = psAcao.executeQuery();

            while (rs.next()) {
                idsGastos.add(rs.getInt("id_usuarios_registram_gastos"));
            }

            for (Integer idGasto : idsGastos) {
                String buscarGasto = "select ug.id_usuarios_registram_gastos, g.titulo, g.valor from usuarios as u "
                        + "join gastos as g join usuarios_registram_gastos as ug "
                        + "where u.id_usuario=ug.id_usuario and ug.id_gasto=g.id_gasto and ug.id_usuarios_registram_gastos=? "
                        + "order by ug.id_usuarios_registram_gastos desc;";
                psAcao = connection.Conexao().prepareStatement(buscarGasto);
                psAcao.setInt(1, idGasto);
                rs = psAcao.executeQuery();

                if (rs.next()) {
                    Gasto g = new Gasto();

                    g.setId(rs.getInt("id_usuarios_registram_gastos"));
                    g.setTitulo(rs.getString("titulo"));
                    g.setValor(rs.getFloat("valor"));

                    gastosContas.add(rs.getFloat("valor"));
                    tGastos.addRow(g);
                }
            }

            utils.CalculoGastos(gastosContas);

        } catch (SQLException er) {
            JOptionPane.showMessageDialog(null, "Algo deu errado ao atualizar os gastos essenciais \n" + er.getMessage());
        }
    }
    
    private void atualizarMetas(int IdUsuario) {
        tMetas.clearList();

        Conexao connection = new Conexao();
        PreparedStatement psAcao;

        java.util.List<Integer> idsMetas = new ArrayList<>();

        try {
            String buscarIdsMetas = "select um.id_usuarios_criam_metas from usuarios as u "
                    + "join metas as m join usuarios_criam_metas as um "
                    + "where u.id_usuario=um.id_usuario and um.id_meta=m.id_meta and um.id_usuario=? "
                    + "order by um.id_usuarios_criam_metas desc;";
            psAcao = connection.Conexao().prepareStatement(buscarIdsMetas);
            psAcao.setInt(1, IdUsuario);

            ResultSet rs;
            rs = psAcao.executeQuery();

            while (rs.next()) {
                idsMetas.add(rs.getInt("id_usuarios_criam_metas"));
            }

            for (Integer idMeta : idsMetas) {
                String buscarMeta = "select um.id_usuarios_criam_metas, m.titulo, m.data_inicial, m.data_final, m.valor_aportado, m.valor_estipulado "
                        + "from usuarios as u join metas as m join usuarios_criam_metas as um "
                        + "where u.id_usuario=um.id_usuario and um.id_meta=m.id_meta and um.id_usuarios_criam_metas=? "
                        + "order by um.id_usuarios_criam_metas desc;";
                psAcao = connection.Conexao().prepareStatement(buscarMeta);
                psAcao.setInt(1, idMeta);
                rs = psAcao.executeQuery();

                if (rs.next()) {
                    Meta m = new Meta();

                    m.setId(rs.getInt("id_usuarios_criam_metas"));
                    m.setTitulo(rs.getString("titulo"));
                    m.setDataInicial(rs.getDate("data_inicial"));
                    m.setDataFinal(rs.getDate("data_final"));
                    m.setValorAportado(rs.getFloat("valor_aportado"));
                    m.setValorEstipulado(rs.getFloat("valor_estipulado"));

                    tMetas.addRow(m);
                }
            }
        } catch (SQLException er) {
            JOptionPane.showMessageDialog(null, "Algo deu errado ao atualizar os gastos essenciais \n" + er.getMessage());
        }
    }
}
