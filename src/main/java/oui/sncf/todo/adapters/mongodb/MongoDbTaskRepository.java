package oui.sncf.todo.adapters.mongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;
import oui.sncf.todo.adapters.dtos.TaskDto;
import oui.sncf.todo.core.task.Task;
import oui.sncf.todo.core.port.TaskRepository;
import oui.sncf.todo.core.task.TaskStatus;

import java.util.Set;

@Repository
@EnableMongoRepositories(basePackages = "oui.sncf.todo.adapters.mongodb")
public class MongoDbTaskRepository implements TaskRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void save(Task task) {
        TaskDto taskDto = new TaskDto(task.getPrefix(),task.getName(), task.getStatus());
        mongoTemplate.save(taskDto);
    }

    @Override
    public void remove(Task task) {
        TaskDto taskDto = new TaskDto(task.getName(), task.getPrefix(), task.getStatus());
        mongoTemplate.remove(taskDto);
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
