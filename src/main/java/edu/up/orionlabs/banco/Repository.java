package edu.up.orionlabs.banco;

import edu.up.orionlabs.entity.Aluno;
import edu.up.orionlabs.entity.Disciplina;
import edu.up.orionlabs.entity.DisciplinaEnum;

import java.sql.SQLException;

public interface Repository {
    Aluno addAluno(String nome);
    Disciplina addDisciplina(Aluno aluno, String nome, DisciplinaEnum tipo, String nota);
}
