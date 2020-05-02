package oui.sncf.todo.unit.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import oui.sncf.todo.adapters.mongodb.MongoDbTaskRepository;
import oui.sncf.todo.core.port.TaskRepository;

public class MongoDbTaskRepositoryTest {

    @Autowired
    private MongoTemplate mongoTemplate;


    private TaskRepository taskRepository = new MongoDbTaskRepository(mongoTemplate);

}
