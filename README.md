<h1 align="center">
  <img alt="channel banner" title="move.it" src="https://user-images.githubusercontent.com/51729214/206331424-e5cc1e25-bec5-459d-ab4c-552b9121da4a.png" />
  <p></p>

</h1>

<p align="center">
  <a href="#title">Projeto</a>&nbsp;|&nbsp;
  <a href="#requirements">Pré-requisitos</a>&nbsp;|&nbsp;
  <a href="#libs">Adicionando libs externas</a>&nbsp;|&nbsp;
  <a href="#tables">Configurando tabelas no MySQL</a>&nbsp;|&nbsp;
  <a href="#run">Rodando o sistema</a>&nbsp;|&nbsp;
  <a href="#links">Links úteis</a>&nbsp;|&nbsp;
  <a href="#license">Licença</a>&nbsp;|&nbsp;
</p>

<h1 id="title">💻 Projeto</h1>

<p>&nbsp;Esse projeto foi criado durante o curso de faculdade de Análise e Desenvolvimento de sistemas na Universidade Anhembi Morumbi com intuito de agregar todos os conhecimentos adquiridos durante o semestre.</p>
<p>&nbsp;Desenvolvemos um aplicação em Java com interface gráfica e integração ao MySQL, basicamente uma conta bancária com sistema de gerenciamento de contas e gastos mensais, os dados são persistidos no banco de dados e buscados sempre que necessários garantindo o salvamento do mesmo, além disso conta também com uma interface de administrador onde tem acesso as contas criadas e pode fazer algumas alterações nelas.
</p>

<h2 id="requirements">🔨 Pré-requisitos</h1>

<p>&nbsp;Antes de começar, você vai precisar ter instalado em sua máquina as seguintes ferramentas: <a href="https://git-scm.com">Git</a>, <a href="https://netbeans.apache.org">Netbeans</a>, <a href="https://mysql.com/">MySQL</a>, além dessas ferramentas é necessário baixar alguas bibliotecas que vamos usar mais pra frente nas configurações como o conector do sql com o java: <a href="https://dev.mysql.com/get/Downloads/Connector-J/mysql-connector-j-8.0.31.tar.gz">mysql-connector</a> e a biblioteca de calendário: <a href="https://www.toedter.com/download/jcalendar-1.4.zip">jcalendar</a> .</p>

<h2 id="gitclone">🎲 Clonando o repositório dentro do NetBeans</h2>
<p>Para clonar o repositório no netbeans é bem simples, abra o editor de código <br> • Acesse -> Team(equipe) -> Git -> Clone; <br> • Coloque a URL(https://github.com/moisesoliveira475/ProjetoBancoJava.git) em Repository URL(URL do repositório); <br> • Dê next(próximo) até finalizar e aparecer a pasta nos seus projetos.</p>

<h2 id="libs">📚 Adicionando as bibiliotecas externas</h2>
<p>Nessa etapa vamos adicionar ao nosso projeto as biblicotecas que baixamos no começo do passo a passo, com o seu netbeans aberto...</p>

<p align="left"> • Expanda o projeto, vá e Libraries(bibliotecas), clique com o botão direito do mouse e vá e (Add JAR/Folder), como mostrar o exemplo abaixo:
<img alt="channel banner" title="move.it" src="https://user-images.githubusercontent.com/51729214/206335824-72537279-71f1-4288-854a-4d05b50034d4.png" />
<br></p>
<p align="left"> • Procure a biblioteca baixada, descompacte-a e depois procure dentro da mesma o "arquivo.jar", lembre-se de fazer isso em todas as bilbiotecas citadas acima!<br>
<img alt="channel banner" width="800px" title="move.it" src="https://user-images.githubusercontent.com/51729214/206336886-9da51dec-38a7-4aa7-a77c-e50abfdf22a5.png" />
<br></p>

<h2 id="tables">#️⃣ Configurando as tabelas no MySQL</h2>
<p>Agora vamos criar nosso banco de dados e também as nossas tabelas que irão ser usadas pelo sistema...<br>Após ter instalado o SQL, abra o WorkBrench e execute os seguintes códigos abaixo. ATENÇÃO QUALQUER CÓDIGO COLADO ERRADO PODE PREJUDICAR A CONEXÃO!</p>

```bash
# Criando o banco de dados
 create database bancouam;

# Criando a tabela contas
CREATE TABLE IF NOT EXISTS `contas`
(
    `id_conta`    INT             NOT NULL AUTO_INCREMENT,
    `numero`      VARCHAR(5)      NOT NULL UNIQUE,
    `senha_conta` VARCHAR(6)      NOT NULL,
    `tipo`        ENUM ('c', 'p') NOT NULL,
    `status`      ENUM ('1', '0') NOT NULL,
    `saldo`       DECIMAL(9, 2)   NULL,
    PRIMARY KEY (`id_conta`)
);
# Criando a tabela usuários
CREATE TABLE IF NOT EXISTS `usuarios`
(
    `id_usuario`      INT           NOT NULL AUTO_INCREMENT,
    `nome`            VARCHAR(30)   NOT NULL,
    `email`           VARCHAR(50)   NOT NULL UNIQUE,
    `senha`           VARCHAR(12)   NOT NULL,
    `cpf`             VARCHAR(11)   NOT NULL UNIQUE,
    `data_nascimento` DATE          NOT NULL,
    `renda_mensal`    DECIMAL(9, 2) NULL,
    `id_conta`        INT           NOT NULL,
    FOREIGN KEY (id_conta) REFERENCES contas (id_conta),
    PRIMARY KEY (`id_usuario`)
);
# Criando a tabela gastos
CREATE TABLE IF NOT EXISTS `gastos`
(
    `id_gasto` INT           NOT NULL AUTO_INCREMENT,
    `titulo`   VARCHAR(30)   NOT NULL,
    `valor`    DECIMAL(9, 2) NOT NULL,
    PRIMARY KEY (`id_gasto`)
);
# Criando a tabela movimentações
CREATE TABLE IF NOT EXISTS `movimentacoes`
(
    `id_movimentacao` INT           NOT NULL AUTO_INCREMENT,
    `titulo`          VARCHAR(45)   NOT NULL,
    `valor`           DECIMAL(9, 2) NOT NULL,
    `data_criacao`    DATE          NOT NULL,
    PRIMARY KEY (`id_movimentacao`)
);
# Criando a tabela metas
CREATE TABLE IF NOT EXISTS `metas`
(
    `id_meta`          INT           NOT NULL AUTO_INCREMENT,
    `titulo`           VARCHAR(40)   NOT NULL,
    `valor_estipulado` DECIMAL(9, 2) NOT NULL,
    `valor_aportado`   DECIMAL(9, 2) NULL DEFAULT 0,
    `data_inicial`     DATE          NOT NULL,
    `data_final`       DATE          NULL,
    PRIMARY KEY (`id_meta`)
);

# Criando as tabelas que fazem os relacionamentos
CREATE TABLE IF NOT EXISTS `usuarios_criam_metas`
(
    `id_usuarios_criam_metas` INT NOT NULL AUTO_INCREMENT,
    `id_usuario`              INT NOT NULL,
    `id_meta`                 INT NOT NULL,
    PRIMARY KEY (id_usuarios_criam_metas),
    FOREIGN KEY (id_usuario) REFERENCES usuarios (id_usuario),
    FOREIGN KEY (id_meta) REFERENCES metas (id_meta)
);
CREATE TABLE IF NOT EXISTS `usuarios_fazem_movimentacoes`
(
    `id_usuarios_fazem_movimentacoes` INT NOT NULL AUTO_INCREMENT,
    `date`                            DATE,
    `id_usuario`                      INT,
    `id_movimentacao`                 INT,
    PRIMARY KEY (id_usuarios_fazem_movimentacoes),
    FOREIGN KEY (id_usuario) REFERENCES usuarios (id_usuario),
    FOREIGN KEY (id_movimentacao) REFERENCES movimentacoes (id_movimentacao)
);
CREATE TABLE IF NOT EXISTS `usuarios_registram_gastos`
(
    `id_usuarios_registram_gastos` INT NOT NULL AUTO_INCREMENT,
    `data`                       DATE,
    `id_usuario`                 INT,
    `id_gasto`                  INT,
    PRIMARY KEY (id_usuarios_registram_gastos),
    FOREIGN KEY (id_usuario) references usuarios (id_usuario),
    FOREIGN KEY (id_gasto) references gastos (id_gasto)
);
```

<h2 id="run">🏃 Agora é só rodar o sistema</h2>
<p>Agora para finalizar, você prêcisa executar o arquivo principal chamado Partida, para isso pressione F6 ou dê dois cliques no arquivo e SHIFT + F6;<br>
<img alt="channel banner" width="670px" title="move.it" src="https://user-images.githubusercontent.com/51729214/206338728-943f628c-0a62-4cc0-b76d-14dea60ff37b.png" />
</p>

<h2 id="links">✨ Links úteis</h2>

As seguintes ferramentas e bibliotecas foram usadas na construção do projeto:

- [NetBeans](https://netbeans.apache.org/)
- [MySQL](https://mysql.com)
- [JCalendar](https://toedter.com/jcalendar/)
- [SQLConnectorJava](https://www.mysql.com/products/connector/)

<h2 id="license">📄 Licença</h2>

Esse projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.
