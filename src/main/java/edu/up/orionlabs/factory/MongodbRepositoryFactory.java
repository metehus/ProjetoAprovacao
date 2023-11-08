package edu.up.orionlabs.factory;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import edu.up.orionlabs.banco.MongodbRepository;
import edu.up.orionlabs.banco.Repository;

public class MongodbRepositoryFactory implements RepositoryFactory {
    String connectionUri = "mongodb://localhost:27017";

    @Override
    public Repository criarRepositorio() {
        try {
            MongoClient client = MongoClients.create(connectionUri);
            MongoDatabase database = client.getDatabase("banco");


            return new MongodbRepository(database);
        } catch (Exception exception) {
            System.out.println("Não foi posível conectar no mongodb");
            exception.printStackTrace();
            return null;
        }
    }
}
