package edu.up.orionlabs.decorator;

import edu.up.orionlabs.banco.Repository;
import edu.up.orionlabs.entity.Aluno;
import edu.up.orionlabs.entity.DisciplinaEnum;

public class TecnicoDecorator extends Decorator {
    float nota;

    public TecnicoDecorator(Aluno aluno, String nome, float nota, Repository repository) {
        super(aluno, nome, repository);
        this.nota = nota;
    }

    @Override
    public void calcularNotas() {
        aluno.calcularNotas();
        Boolean aprovado = this.nota >= 7.0;

        repository.addDisciplina(aluno, nome, DisciplinaEnum.CursoTecnico, Float.toString(this.nota));

        System.out.println("Disciplina técnica " + this.nome + ". Nota: " + this.nota + ". Aprovado: " + aprovado);
    }
}
