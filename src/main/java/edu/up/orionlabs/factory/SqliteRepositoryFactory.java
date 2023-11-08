package edu.up.orionlabs.factory;

import com.sun.tools.javac.Main;
import edu.up.orionlabs.banco.Repository;
import edu.up.orionlabs.banco.SqliteRepository;
import edu.up.orionlabs.entity.Aluno;
import edu.up.orionlabs.entity.Disciplina;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SqliteRepositoryFactory implements RepositoryFactory {


    @Override
    public Repository criarRepositorio() {

        try {
            Class.forName("org.sqlite.JDBC");

            Connection connection = DriverManager.getConnection("jdbc:sqlite:banco.db");
            System.out.println("Conectado");

            SqliteRepository sqliteRepository = new SqliteRepository(connection);
            sqliteRepository.criarTabelas();

            return new SqliteRepository(connection);

        } catch (Exception exception) {
            System.out.println("Não foi possível conectar no SQlite");
            exception.printStackTrace();
            return null;
        }
    }
}