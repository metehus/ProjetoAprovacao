package edu.up.orionlabs.banco;

import edu.up.orionlabs.entity.Aluno;
import edu.up.orionlabs.entity.Disciplina;
import edu.up.orionlabs.entity.DisciplinaEnum;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class SqliteRepository implements Repository {
    Connection connection;

    public SqliteRepository(Connection conn) {
        this.connection = conn;
    }

    @Override
    public Aluno addAluno(String nome) {

        Aluno aluno = new Aluno();
        aluno.nome = nome;

        String sqlInsertAluno = "INSERT INTO aluno (nome) VALUES (?) RETURNING *;";

        PreparedStatement preparedStatement = this.createPreparedStatement(sqlInsertAluno);

        try {
            preparedStatement.setString(1, aluno.nome);

            var result = preparedStatement.executeQuery();

            int id = result.getInt("id");

            aluno.id = id;
            preparedStatement.close();
            return aluno;

        } catch (SQLException e) {
            System.out.println("Aluno não inserido");
            return null;
        }
    }

    @Override
    public Disciplina addDisciplina(Aluno aluno, String nome, DisciplinaEnum tipoDisciplina, String nota) {
        Disciplina disciplina = new Disciplina();
        disciplina.alunoId = aluno.getId();
        disciplina.alunoHashId = null;
        disciplina.nome = nome;
        disciplina.tipoDisciplina = tipoDisciplina;
        disciplina.alunoNota = nota;

        String sqlInsertDisciplina = "INSERT INTO disciplina ("
                + "alunoId,"
                + "nomeDisciplina,"
                + "tipoDisciplina,"
                + "alunoNota"
                + ") VALUES(?,?,?,?);";

        PreparedStatement preparedStatement = this.createPreparedStatement(sqlInsertDisciplina);

        try {
            preparedStatement.setInt(1, disciplina.alunoId);
            preparedStatement.setString(2, disciplina.nome);
            preparedStatement.setString(3, disciplina.tipoDisciplina.toString());
            preparedStatement.setString(4, disciplina.alunoNota);

            preparedStatement.executeUpdate();

            preparedStatement.close();
            return disciplina;
        } catch (SQLException e) {
            System.out.println("Disciplinas não inseridos");
            return null;
        }
    }

    public void criarTabelas() throws SQLException {
        String sqlAluno = "CREATE TABLE IF NOT EXISTS aluno"
                + "("
                + "id integer PRIMARY KEY AUTOINCREMENT,"
                + "nome text NOT NULL"
                + ")";

        String sqlInscricoes = "CREATE TABLE IF NOT EXISTS disciplina"
                + "("
                + "alunoId integer,"
                + "nomeDisciplina text,"
                + "tipoDisciplina text,"
                + "alunoNota text,"
                + "FOREIGN KEY (alunoId) REFERENCES aluno(id)"
                + ")";

        Statement stmt = this.createStatement();

        stmt.execute(sqlAluno);
        System.out.println("Tabela aluno criada!");
        stmt.execute(sqlInscricoes);
        System.out.println("Tabela inscricoes criada!");
    }

    public Statement createStatement() {
        try {
            return this.connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public PreparedStatement createPreparedStatement(String sql) {
        try {
            return this.connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
