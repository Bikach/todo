package oui.sncf.todo.adapters.mongodb;

import org.springframework.stereotype.Repository;
import oui.sncf.todo.core.task.Task;
import oui.sncf.todo.core.port.TaskRepository;
import oui.sncf.todo.core.task.TaskStatus;

import java.util.Set;

@Repository
public class MongoDbTaskRepository implements TaskRepository {

    @Override
    public void save(Task task) { }

    @Override
    public boolean remove(Task task) {
        return false;
    }

    @Override
    public Task getByName(String taskName) {
        return null;
    }

    @Override
    public Set<TaskDto> fetch(TaskStatus status) {
        return null;
    }
}
