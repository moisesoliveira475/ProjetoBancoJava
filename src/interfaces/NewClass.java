//btnAtualizar.addActionListener(e -> {
//            Conexao connection = new Conexao();
//            PreparedStatement psAcao;
//
//            Usuario p = new Usuario();
//
//            List<Integer> id_usuarios = new ArrayList<>();
//            List<String> nomes = new ArrayList<>();
//            List<String> emails = new ArrayList<>();
//            List<String> senhas = new ArrayList<>();
//            List<String> cpfs = new ArrayList<>();
//            List<Date> dataNs = new ArrayList<>();
//            List<Float> rendaMs = new ArrayList<>();
//            List<Integer> id_contas = new ArrayList<>();
//
//            try {
//                String buscarDados = "select * from usuarios;";
//                psAcao = connection.Conexao().prepareStatement(buscarDados);
//
//                ResultSet rs;
//                rs = psAcao.executeQuery();
//
//                while (rs.next()) {
//                    java.util.Date dataNConvertido = new java.sql.Date(rs.getDate("data_nascimento").getTime());
//                    
//                    id_usuarios.add(rs.getInt("id_usuario"));
//                    nomes.add(rs.getString("nome"));
//                    emails.add(rs.getString("email"));
//                    senhas.add(rs.getString("senha"));
//                    cpfs.add(rs.getString("cpf"));
//                    dataNs.add(dataNConvertido);
//                    rendaMs.add(rs.getFloat("renda_mensal"));
//                    id_contas.add(rs.getInt("id_conta"));
//                }
//                
//                int index = 0;
//                for(Integer id:id_usuarios) {
//                    p.setId(id);
//                    p.setNome(nomes.get(index));
//                    p.setEmail(emails.get(index));
//                    p.setSenha(senhas.get(index));
//                    p.setCpf(cpfs.get(index));
//                    p.setDataNascimento(dataNs.get(index));
//                    p.setRendaMensal(rendaMs.get(index));
//                    p.setIdConta(id_contas.get(index));
//                    index++;
//                    tModelo.addRow(p);
//                }
//            } catch (SQLException er) {
//                System.out.println(er);
//            }
//        });
//
//btnAtualizar.addActionListener(e -> {
//            Conexao connection = new Conexao();
//            PreparedStatement psAcao;
//
//            List<Integer> qtdUsuarios = new ArrayList<>();
//
//            try {
//                String buscarQtdUsuarios = "select id_usuario from usuarios;";
//                psAcao = connection.Conexao().prepareStatement(buscarQtdUsuarios);
//                
//                ResultSet rs;
//                rs = psAcao.executeQuery();
//                
//                while(rs.next()) {
//                    qtdUsuarios.add(rs.getInt("id_usuario"));
//                }
//                System.out.println(qtdUsuarios);
//                for(Integer usuario : qtdUsuarios) {
//                    String buscarDados = "select * from usuarios where id_usuario=?;";
//                    psAcao = connection.Conexao().prepareStatement(buscarDados);
//                    psAcao.setInt(1, usuario);
//                    
//                    rs = psAcao.executeQuery();
//                    
//                    if(rs.next()) {
//                        Usuario p = new Usuario();
//                        p.setId(rs.getInt("id_usuario"));
//                        p.setNome(rs.getString("nome"));
//                        p.setEmail(rs.getString("email"));
//                        p.setSenha(rs.getString("senha"));
//                        p.setCpf(rs.getString("cpf"));
//                        java.util.Date dataNConvertido = new java.sql.Date(rs.getDate("data_nascimento").getTime());
//                        p.setDataNascimento(dataNConvertido);
//                        p.setRendaMensal(rs.getFloat("renda_mensal"));
//                        p.setIdConta(rs.getInt("id_conta")); 
//                        System.out.println(p);
//                        tModelo.addRow(p);
//                    }
//                   
//                }
//            } catch (SQLException er) {
//                System.out.println(er);
//            }
//        });

// modelo de verificação se usuario ja existe na lista
//if (tModelo.getRowCount() > 0) {
//                            for (var i = 1; i < tModelo.getRowCount(); i++) {
//                                if (rs.getInt("id_usuario") == (int) tModelo.getValueAt(i, 0)) {
//                                    tModelo.setValueAt(rs.getInt("id_usuario"), i, 0);
//                                    tModelo.setValueAt(rs.getString("nome"), i, 1);
//                                    tModelo.setValueAt(rs.getString("email"), i, 2);
//                                    tModelo.setValueAt(rs.getString("senha"), i, 3);
//                                    tModelo.setValueAt(rs.getString("cpf"), i, 4);
//                                    tModelo.setValueAt(dataNConvertido, i, 5);
//                                    tModelo.setValueAt(rs.getFloat("renda_mensal"), i, 6);
//                                    tModelo.setValueAt(rs.getInt("id_conta"), i, 7);
//                                } else {
//                                    tModelo.addRow(p);
//                                }
//                            }
//                        } else {
//                            tModelo.addRow(p);
//                        }
