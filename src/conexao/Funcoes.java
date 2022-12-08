package conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

import utils.*;

public class Funcoes {

    Conexao connection = new Conexao();
    PreparedStatement psAcao;

    // função para realizar um depósito;
    public boolean FazerDeposito(int idConta, int idUsuario, float valorADepositar, float valorBruto) {
        boolean isSuccess = false;

        try {
            String inserirSaldo = "update contas set saldo = ? WHERE id_conta=?;";
            psAcao = connection.Conexao().prepareStatement(inserirSaldo);
            psAcao.setFloat(1, valorADepositar);
            psAcao.setInt(2, idConta);

            int rsUpdate = psAcao.executeUpdate();
            if (rsUpdate == 1) {
                String insertUsuarioMovimentacao = "INSERT INTO movimentacoes(id_movimentacao, titulo, valor, data_criacao) VALUES (null, ?, ?, ?);";
                psAcao = connection.Conexao().prepareStatement(insertUsuarioMovimentacao, Statement.RETURN_GENERATED_KEYS);
                psAcao.setString(1, "Depósito");
                psAcao.setFloat(2, valorBruto);
                psAcao.setDate(3, new java.sql.Date(new java.util.Date().getTime()));

                psAcao.executeUpdate();

                ResultSet rsInsert = psAcao.getGeneratedKeys();
                if (rsInsert.next()) {
                    String inserirUsuarioMovimentacao = "INSERT INTO usuarios_fazem_movimentacoes (id_usuario, id_movimentacao) VALUES (?, ?);";
                    psAcao = connection.Conexao().prepareStatement(inserirUsuarioMovimentacao);
                    psAcao.setInt(1, idUsuario);
                    psAcao.setInt(2, rsInsert.getInt(1));

                    int result = psAcao.executeUpdate();
                    if (result == 1) {
                        isSuccess = true;
                        JOptionPane.showMessageDialog(null, "Deposito realizado com sucesso!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Não foi possivel adicionar a movimentação :(");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Não foi possível adicionar o saldo :(");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro > " + e.getMessage());
        }
        return isSuccess;
    }

    // função para fazer saque;
    public boolean FazerSaque(int idConta, int idUsuario, float saldoNovo, float saldoBruto) {
        boolean isSuccess = false;

        try {
            String alterarSaldo = "UPDATE contas SET saldo = ? WHERE id_conta=?;";

            psAcao = connection.Conexao().prepareStatement(alterarSaldo);
            psAcao.setFloat(1, saldoNovo);
            psAcao.setInt(2, idConta);

            int rsUpdate = psAcao.executeUpdate();
            if (rsUpdate == 1) {
                String insertUsuarioMovimentacao = "INSERT INTO movimentacoes(id_movimentacao, titulo, valor, data_criacao) VALUES (null, ?, ?, ?);";
                psAcao = connection.Conexao().prepareStatement(insertUsuarioMovimentacao, Statement.RETURN_GENERATED_KEYS);
                psAcao.setString(1, "Saque");
                psAcao.setFloat(2, saldoBruto);
                psAcao.setDate(3, new java.sql.Date(new java.util.Date().getTime()));

                psAcao.executeUpdate();

                ResultSet rsInsert = psAcao.getGeneratedKeys();
                if (rsInsert.next()) {
                    String inserirUsuarioMovimentacao = "INSERT INTO usuarios_fazem_movimentacoes (id_usuario, id_movimentacao) VALUES (?, ?);";
                    psAcao = connection.Conexao().prepareStatement(inserirUsuarioMovimentacao);
                    psAcao.setInt(1, idUsuario);
                    psAcao.setInt(2, rsInsert.getInt(1));

                    int result = psAcao.executeUpdate();
                    if (result == 1) {
                        isSuccess = true;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Não foi possivel adicionar a movimentação :(");
                    return isSuccess;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Não foi possível realizar o saque :(");
                return isSuccess;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro > " + e.getMessage());
        }
        return isSuccess;
    }

    // função que registra uma compra nas movimentações;
    public boolean RegistrarCompra(int idUsuario, java.util.Date dataGasto, String tituloCompra, float valorCompra) {
        boolean isSuccess = false;

        try {
            String insertMovimentacao = "INSERT INTO movimentacoes(id_movimentacao, titulo, valor, data_criacao) VALUES (null, ?, ?, ?);";
            psAcao = connection.Conexao().prepareStatement(insertMovimentacao, Statement.RETURN_GENERATED_KEYS);
            psAcao.setString(1, tituloCompra);
            psAcao.setFloat(2, valorCompra);
            psAcao.setDate(3, new java.sql.Date(dataGasto.getTime()));

            psAcao.executeUpdate();

            ResultSet rsInsert = psAcao.getGeneratedKeys();
            if (rsInsert.next()) {
                String inserirUsuarioMovimentacao = "INSERT INTO usuarios_fazem_movimentacoes (id_usuario, id_movimentacao) VALUES (?, ?);";
                psAcao = connection.Conexao().prepareStatement(inserirUsuarioMovimentacao);
                psAcao.setInt(1, idUsuario);
                psAcao.setInt(2, rsInsert.getInt(1));

                int result = psAcao.executeUpdate();
                if (result == 1) {
                    isSuccess = true;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Não foi possivel registrar a compra");
                return isSuccess;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro > " + e.getMessage());
        }
        return isSuccess;
    }

    // função que adicionar um novo gasto essencial e insere nos registros de gastos;
    public boolean AdicionarGastoEssencial(int idUsuario, String tituloGasto, float valorGasto) {
        boolean isSuccess = false;

        try {
            String inserirGasto = "INSERT INTO gastos(id_gasto, titulo, valor) VALUES (null, ?, ?);";
            psAcao = connection.Conexao().prepareStatement(inserirGasto, Statement.RETURN_GENERATED_KEYS);
            psAcao.setString(1, tituloGasto);
            psAcao.setFloat(2, valorGasto);

            psAcao.executeUpdate();

            ResultSet rsInsert = psAcao.getGeneratedKeys();
            if (rsInsert.next()) {
                String inserirUsuarioGasto = "INSERT INTO usuarios_registram_gastos(id_usuario, id_gasto) VALUES (?, ?);";
                psAcao = connection.Conexao().prepareStatement(inserirUsuarioGasto);
                psAcao.setInt(1, idUsuario);
                psAcao.setInt(2, rsInsert.getInt(1));

                int result = psAcao.executeUpdate();
                if (result == 1) {
                    isSuccess = true;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Não foi possivel adicionar o gasto");
                return isSuccess;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro > " + e.getMessage());
        }
        return isSuccess;
    }
    
    // função que adicionar um novo gasto essencial e insere nos registros de gastos;
    public boolean AdicionarMeta(int idUsuario, String titulo, java.util.Date dataFinal, float valorAportado, float valorEstipulado) {
        boolean isSuccess = false;

        try {
            String inserirGasto = "insert into metas(id_meta, titulo, valor_estipulado, valor_aportado, data_inicial, data_final) VALUES (null, ?, ?, ?, ?, ?);";
            psAcao = connection.Conexao().prepareStatement(inserirGasto, Statement.RETURN_GENERATED_KEYS);
            psAcao.setString(1, titulo);
            psAcao.setFloat(2, valorEstipulado);
            psAcao.setFloat(3, valorAportado);
            psAcao.setDate(4, new java.sql.Date(new java.util.Date().getTime()));
            psAcao.setDate(5, new java.sql.Date(dataFinal.getTime()));

            psAcao.executeUpdate();

            ResultSet rsInsert = psAcao.getGeneratedKeys();
            if (rsInsert.next()) {
                String inserirUsuarioGasto = "insert into usuarios_criam_metas(id_usuarios_criam_metas, id_usuario, id_meta) VALUES (null, ?, ?);";
                psAcao = connection.Conexao().prepareStatement(inserirUsuarioGasto);
                psAcao.setInt(1, idUsuario);
                psAcao.setInt(2, rsInsert.getInt(1));

                int result = psAcao.executeUpdate();
                if (result == 1) {
                    isSuccess = true;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Não foi possivel criar a meta!");
                return isSuccess;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro > " + e.getMessage());
        }
        return isSuccess;
    }

    // função para atualizar renda mensal do usuario;
    public boolean MudarRendaMensal(int idUsuario, float novaRenda) {
        boolean isSuccess = false;

        try {
            String inserirRendaMensal = "UPDATE usuarios SET renda_mensal=? WHERE id_usuario=?;";
            psAcao = connection.Conexao().prepareStatement(inserirRendaMensal);
            psAcao.setFloat(1, novaRenda);
            psAcao.setInt(2, idUsuario);

            int rsInsert = psAcao.executeUpdate();

            if (rsInsert == 1) {
                isSuccess = true;
                JOptionPane.showMessageDialog(null, "Renda atualizada com sucesso!");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro > " + e.getMessage());
        }
        return isSuccess;
    }

    // função que faz transferência de valores entre contas;
    public boolean FazerTransferencia(DadosUsuario dadosU, String numeroContaDestino, float valorBruto) {
        Utils utils = new Utils();

        boolean isSuccess = false;

        if (utils.aContaExiste(numeroContaDestino) == false) {
            JOptionPane.showMessageDialog(null, "A conta informada não existe.");
            return isSuccess;
        }

        boolean result = setSaldoMovimentacao(
                (dadosU.getSaldoConta() - valorBruto), valorBruto,
                dadosU.getId_conta(), dadosU.getId(), "R$" + valorBruto + " para -> " + numeroContaDestino);

        if (result) {
            float dadosContaDestino[] = utils.buscarDadosConta(numeroContaDestino);

            int idUsuarioDestino = (int) dadosContaDestino[0];
            int idContaDestino = (int) dadosContaDestino[1];
            float saldoNovoContaDestino = (dadosContaDestino[2] + valorBruto);

            result = setSaldoMovimentacao(
                    saldoNovoContaDestino, valorBruto,
                    idContaDestino, idUsuarioDestino, "R$" + valorBruto + " de -> " + dadosU.getNumeroConta());

            if (result) {
                dadosU.setSaldoConta(dadosU.getSaldoConta() - valorBruto);
                isSuccess = true;
                return isSuccess;
            }
        }
        return isSuccess;
    }

    // função que atualiza o saldo na conta do usuario e insere as movimentaçãos;
    public boolean setSaldoMovimentacao(float saldoNovo, float valorBruto, int idConta, int idUsuario, String titulo) {
        boolean isSuccess = false;

        try {
            String alterarSaldo = "UPDATE contas SET saldo = ? WHERE id_conta=?;";

            psAcao = connection.Conexao().prepareStatement(alterarSaldo);
            psAcao.setFloat(1, saldoNovo);
            psAcao.setInt(2, idConta);

            int rsUpdate = psAcao.executeUpdate();
            if (rsUpdate == 1) {
                String insertUsuarioMovimentacao = "INSERT INTO movimentacoes(id_movimentacao, titulo, valor, data_criacao) VALUES (null, ?, ?, ?);";
                psAcao = connection.Conexao().prepareStatement(insertUsuarioMovimentacao, Statement.RETURN_GENERATED_KEYS);
                psAcao.setString(1, titulo);
                psAcao.setFloat(2, valorBruto);
                psAcao.setDate(3, new java.sql.Date(new java.util.Date().getTime()));

                psAcao.executeUpdate();

                ResultSet rsInsert = psAcao.getGeneratedKeys();
                if (rsInsert.next()) {
                    String inserirUsuarioMovimentacao = "INSERT INTO usuarios_fazem_movimentacoes (id_usuario, id_movimentacao) VALUES (?, ?);";
                    psAcao = connection.Conexao().prepareStatement(inserirUsuarioMovimentacao);
                    psAcao.setInt(1, idUsuario);
                    psAcao.setInt(2, rsInsert.getInt(1));

                    int result = psAcao.executeUpdate();
                    if (result == 1) {
                        isSuccess = true;
                        return isSuccess;
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro no setSaldoMovimentacao\n" + e.getMessage());
        }
        return isSuccess;
    }

    // buscar usuarios;
    public List<String> buscarUsuarios(String columnName) {
        List<String> dados = new ArrayList<>();
        try {
            String buscarDados = "select * from usuarios;";
            psAcao = connection.Conexao().prepareStatement(buscarDados);

            ResultSet rs;
            rs = psAcao.executeQuery();

            while (rs.next()) {
                dados.add(rs.getString(columnName));
            }
        } catch (SQLException er) {
            System.out.println(er);
        }

        return dados;
    }

    public boolean atualizarDadosUsuario(
            String nome, String email, String senha, String cpf,
            java.util.Date dataNascimento, String rendaMensal, int idUsuario,
            String senhaConta, String tipoConta, String StatusConta, int idConta
    ) {
        boolean isSuccess = false;

        try {
            String atualizarUsuario = "UPDATE usuarios SET nome=?, email=?, senha=?, cpf=?, data_nascimento=?, renda_mensal=? WHERE id_usuario=?;";
            psAcao = connection.Conexao().prepareStatement(atualizarUsuario);
            psAcao.setString(1, nome);
            psAcao.setString(2, email);
            psAcao.setString(3, senha);
            psAcao.setString(4, cpf);
            java.sql.Date dataNascimentoConvertido = new java.sql.Date(dataNascimento.getTime());
            psAcao.setDate(5, dataNascimentoConvertido);
            psAcao.setFloat(6, Float.parseFloat(rendaMensal));
            psAcao.setInt(7, idUsuario);

            int rsInsert = psAcao.executeUpdate();

            if (rsInsert == 1) {
                String atualizarConta = "UPDATE contas SET senha_conta=?, tipo=?, status=? WHERE id_conta=?;";
                psAcao = connection.Conexao().prepareStatement(atualizarConta);
                psAcao.setString(1, senhaConta);
                psAcao.setString(2, tipoConta);
                psAcao.setString(3, StatusConta);
                psAcao.setInt(4, idConta);

                rsInsert = psAcao.executeUpdate();

                if (rsInsert == 1) {
                    isSuccess = true;
                    JOptionPane.showMessageDialog(null, "dados atualizados com sucesso!");
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro > " + e.getMessage());
        }
        return isSuccess;
    }
;
}
