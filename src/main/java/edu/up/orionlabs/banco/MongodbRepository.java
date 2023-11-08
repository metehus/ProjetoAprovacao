package edu.up.orionlabs.banco;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import edu.up.orionlabs.entity.Aluno;
import edu.up.orionlabs.entity.Disciplina;
import edu.up.orionlabs.entity.DisciplinaEnum;

import org.bson.Document;
import org.bson.types.ObjectId;

public class MongodbRepository implements Repository {
    private final MongoCollection<Document> alunosCollection;
    private final MongoCollection<Document> disciplinasCollection;
    private ObjectId alunoHashId;

    public MongodbRepository(MongoDatabase database) {
        this.alunosCollection = database.getCollection("aluno");
        this.disciplinasCollection = database.getCollection("disciplina");
    }

    @Override
    public Aluno addAluno(String nome) {
        try{
            Document alunoDocument = new Document("nome", nome);
            alunosCollection.insertOne(alunoDocument);

            alunoHashId = alunoDocument.getObjectId("_id");

            Aluno aluno = new Aluno();
            aluno.setId(0);
            aluno.setNome(nome);

            return aluno;
        } catch(MongoException ex){
            System.err.println("Erro ao inserir aluno no MongoDB: " + ex.getMessage());
            return null;
        }
    }

    @Override
    public Disciplina addDisciplina(Aluno aluno, String nome, DisciplinaEnum tipoDisciplina, String nota) {
        Document disciplinaDocument = new Document();
        disciplinaDocument.append("alunoId", aluno.getId());
        disciplinaDocument.append("alunoHashId", alunoHashId);
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

}
