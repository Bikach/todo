package oui.sncf.todo.adapters.mongodb;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import oui.sncf.todo.adapters.dtos.TaskDto;
import oui.sncf.todo.core.task.Task;
import oui.sncf.todo.core.port.TaskRepository;
import oui.sncf.todo.core.task.TaskStatus;

import java.util.Set;

@Repository
public class MongoDbTaskRepository implements TaskRepository {

    private MongoTemplate mongoTemplate;

    public MongoDbTaskRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void save(Task task) {
        mongoTemplate.save(task);
    }

    @Override
    public void remove(Task task) {
    }

    @Override
    public TaskDto getByName(String taskName) {
        return null;
    }

    @Override
    public TaskDto getByPrefix(String prefix) {
        return null;
    }

    @Override
    public Set<TaskDto> fetch(TaskStatus status) {
        return null;
    }
}
