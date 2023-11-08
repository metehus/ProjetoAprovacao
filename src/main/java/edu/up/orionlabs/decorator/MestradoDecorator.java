package edu.up.orionlabs.decorator;

import edu.up.orionlabs.banco.Repository;
import edu.up.orionlabs.entity.Aluno;
import edu.up.orionlabs.entity.DisciplinaEnum;

public class MestradoDecorator extends Decorator {
    String nota;

    public MestradoDecorator(Aluno aluno, String nome, String nota, Repository repository) {
        super(aluno, nome, repository);
        this.nota = nota;
    }

    @Override
    public void calcularNotas() {
        this.aluno.calcularNotas();
        Boolean aprovado = this.nota != "D";

        repository.addDisciplina(aluno, nome, DisciplinaEnum.Mestrado, this.nota);

        System.out.println("Disciplina mestrado " + this.nome + ". Nota: " + this.nota + ". Aprovado: " + aprovado);

    }
}
