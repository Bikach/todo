package oui.sncf.todo.adapters.secondary;

import org.springframework.stereotype.Repository;
import oui.sncf.todo.core.domain.models.Task;
import oui.sncf.todo.core.domain.port.loaders.TaskLoader;

import java.util.Set;

@Repository
public class DataBaseTasksLoader implements TaskLoader {

    private Set<Task> tasks;

    @Override
    public Set<Task> fetch() {
        return tasks;
    }
}
