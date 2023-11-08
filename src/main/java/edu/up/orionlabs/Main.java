package edu.up.orionlabs;

import edu.up.orionlabs.banco.Repository;
import edu.up.orionlabs.decorator.BachareladoDecorator;
import edu.up.orionlabs.decorator.MestradoDecorator;
import edu.up.orionlabs.decorator.TecnicoDecorator;
import edu.up.orionlabs.entity.Aluno;
import edu.up.orionlabs.factory.MongodbRepositoryFactory;
import edu.up.orionlabs.factory.RepositoryFactory;
import edu.up.orionlabs.factory.SqliteRepositoryFactory;

public class Main {
    public static void main(String[] args) {
        RepositoryFactory factory = Main.selecionarBanco("mongodb");
        Repository repository = factory.criarRepositorio();

        Aluno aluno = repository.addAluno("Joãozinho");

        aluno = new TecnicoDecorator(aluno, "Java", 7.5f, repository);
        aluno = new TecnicoDecorator(aluno, "HTML", 2.5f, repository);
        aluno = new TecnicoDecorator(aluno, "CSS", 7.5f, repository);
        aluno = new MestradoDecorator(aluno, "Kotlin", "B", repository);
        aluno = new BachareladoDecorator(aluno, "Padroes de projetos", 8f, repository);
        aluno = new BachareladoDecorator(aluno, "HTML Avançado", 0f, repository);

        aluno.calcularNotas();


    }

    public static RepositoryFactory selecionarBanco(String banco) {
        if (banco.equalsIgnoreCase("sqlite")) {
            return new SqliteRepositoryFactory();
        } else if (banco.equalsIgnoreCase("mongodb")) {
            return new MongodbRepositoryFactory();
        } else {
            throw new RuntimeException("Banco selecionado invalido. Utilize sqlite ou mongodb");
        }
    }

}