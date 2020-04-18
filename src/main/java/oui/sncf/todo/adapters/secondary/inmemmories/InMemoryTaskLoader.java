package oui.sncf.todo.adapters.secondary.inmemmories;

import oui.sncf.todo.core.domain.models.Task;
import oui.sncf.todo.core.domain.models.TaskStatus;
import oui.sncf.todo.core.domain.port.loaders.TaskLoader;

import java.util.LinkedHashSet;
import java.util.Set;

public class InMemoryTaskLoader implements TaskLoader {

    private Set<Task> tasks;

    public InMemoryTaskLoader() {
        initTasks();
    }

    @Override
    public Set<Task> fetch() {
        return tasks;
    }

    private void initTasks(){
        tasks = new LinkedHashSet<>();
        tasks.add(new Task("task 1"));
        tasks.add(new Task("task 3", TaskStatus.DONE));
        tasks.add(new Task("task 2"));
        tasks.add(new Task("task 4"));
        tasks.add(new Task("task 5", TaskStatus.DONE));
    }
}
