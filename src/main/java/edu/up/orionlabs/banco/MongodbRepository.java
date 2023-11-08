package edu.up.orionlabs.banco;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import edu.up.orionlabs.entity.Aluno;
import edu.up.orionlabs.entity.Disciplina;
import edu.up.orionlabs.entity.DisciplinaEnum;

import org.bson.Document;

public class MongodbRepository implements Repository {
    private final MongoCollection<Document> alunosCollection;
    private final MongoCollection<Document> disciplinasCollection;

    public MongodbRepository(MongoDatabase database) {
        this.alunosCollection = database.getCollection("aluno");
        this.disciplinasCollection = database.getCollection("disciplina");
    }

    @Override
    public Aluno addAluno(String nome) {
        Document alunoDocument = new Document("nome", nome);
        alunosCollection.insertOne(alunoDocument);

        Aluno aluno = new Aluno();
        aluno.setId(Integer.parseInt(alunoDocument.getObjectId("_id").toString()));
        aluno.setNome(nome);

        return aluno;
    }

    @Override
    public Disciplina addDisciplina(Aluno aluno, String nome, DisciplinaEnum tipoDisciplina, String nota) {
        Document disciplinaDocument = new Document();
        disciplinaDocument.append("alunoId", aluno.getId());
        disciplinaDocument.append("nomeDisciplina", nome);
        disciplinaDocument.append("tipoDisciplina", tipoDisciplina.toString());
        disciplinaDocument.append("alunoNota", nota);

        disciplinasCollection.insertOne(disciplinaDocument);

        Disciplina disciplina = new Disciplina();
        disciplina.alunoId = aluno.getId();
        disciplina.nome = nome;
        disciplina.tipoDisciplina = tipoDisciplina;
        disciplina.alunoNota = nota;

        return disciplina;
    }

    public void criarColecoes() {
        // Não é necessário criar coleções explicitamente no MongoDB, elas são criadas dinamicamente quando você insere dados.
        System.out.println("Coleções aluno e disciplina prontas para uso!");
    }
}
