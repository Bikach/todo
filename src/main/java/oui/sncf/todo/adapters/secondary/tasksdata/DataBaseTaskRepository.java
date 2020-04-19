package oui.sncf.todo.adapters.secondary.tasksdata;

import org.springframework.stereotype.Repository;
import oui.sncf.todo.core.domain.tasks.models.Task;
import oui.sncf.todo.core.domain.tasks.models.TaskStatus;
import oui.sncf.todo.core.domain.tasks.port.TaskRepository;

import java.util.Optional;

@Repository
public class DataBaseTaskRepository implements TaskRepository {

    @Override
    public boolean save(Task task) {
        return false;
    }

    @Override
    public boolean remove(Task task) {
        return false;
    }

    @Override
    public boolean update(Task task) {
        return false;
    }

    @Override
    public Optional<Task> getByName(String taskName) {
        return Optional.of(new Task("toto", TaskStatus.DONE));
    }
}
