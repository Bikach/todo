package oui.sncf.todo.adapters.secondary.inmemmories;

import oui.sncf.todo.core.domain.models.Task;
import oui.sncf.todo.core.domain.repositories.TasksRepository;

import java.util.HashSet;
import java.util.Set;

public class InMemoryTaskRepository implements TasksRepository {

    Set<Task> tasks;

    public InMemoryTaskRepository(Set<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public void add(Task task) {
        tasks.add(task);
    }
}
