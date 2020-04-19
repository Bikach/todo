package oui.sncf.todo.adapters.secondary.tasksdata;

import org.springframework.stereotype.Repository;
import oui.sncf.todo.core.domain.tasks.models.Task;
import oui.sncf.todo.core.domain.tasks.models.TaskStatus;
import oui.sncf.todo.core.domain.tasks.port.TaskLoader;

import java.util.LinkedHashSet;
import java.util.Set;

@Repository
public class DataBaseTasksLoader implements TaskLoader {

    private Set<Task> tasks;

    @Override
    public Set<Task> fetch() {
        initTasks();
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
