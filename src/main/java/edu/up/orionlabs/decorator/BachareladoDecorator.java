package edu.up.orionlabs.decorator;

import edu.up.orionlabs.banco.Repository;
import edu.up.orionlabs.entity.Aluno;
import edu.up.orionlabs.entity.DisciplinaEnum;

public class BachareladoDecorator extends Decorator {
    float nota;

    public BachareladoDecorator(Aluno aluno, String nome, float nota, Repository repository) {
        super(aluno, nome, repository);
        this.nota = nota;
    }

    @Override
    public void calcularNotas() {
        this.aluno.calcularNotas();
        Boolean aprovado = this.nota >= 6;

        repository.addDisciplina(aluno, nome, DisciplinaEnum.Bacharelado, Float.toString(this.nota));

        System.out.println("Disciplina Bacharelado " + this.nome + ". Nota: " + this.nota + ". Aprovado: " + aprovado);
    }

}
