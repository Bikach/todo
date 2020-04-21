package oui.sncf.todo.adapters.secondary.tasksdata;

import org.springframework.stereotype.Repository;
import oui.sncf.todo.core.domain.tasks.models.Task;
import oui.sncf.todo.core.domain.tasks.port.TaskLoader;

import java.util.Set;

@Repository
public class MongoDbTasksLoader implements TaskLoader {

    @Override
    public Set<Task> fetch() {
        return null;
    }
}
