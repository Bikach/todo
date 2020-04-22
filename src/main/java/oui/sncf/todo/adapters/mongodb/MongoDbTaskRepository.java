package oui.sncf.todo.adapters.mongodb;

import org.springframework.stereotype.Repository;
import oui.sncf.todo.core.task.Task;
import oui.sncf.todo.core.port.TaskRepository;

import java.util.Set;

@Repository
public class MongoDbTaskRepository implements TaskRepository {

    @Override
    public boolean save(Task task) {
        return false;
    }

    @Override
    public boolean remove(Task task) {
        return false;
    }

    @Override
    public Task getByName(String taskName) {
        return null;
    }

    @Override
    public Set<Task> fetch() {
        return null;
    }
}
