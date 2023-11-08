package edu.up.orionlabs.decorator;

import edu.up.orionlabs.banco.Repository;
import edu.up.orionlabs.entity.Aluno;

public abstract class Decorator extends Aluno {
    protected String nome;
    protected Aluno aluno;
    protected Repository repository;

    public Decorator(Aluno aluno, String nome, Repository repository) {
        this.aluno = aluno;
        this.nome = nome;
        this.repository = repository;
    }

    @Override
    public int getId() {
        return aluno.getId();
    }
}
