package edu.up.orionlabs.entity;

import org.bson.types.ObjectId;

public class Disciplina {
    public int alunoId;
    public ObjectId alunoHashId;
    public String nome;
    public DisciplinaEnum tipoDisciplina;
    public String alunoNota;
}
