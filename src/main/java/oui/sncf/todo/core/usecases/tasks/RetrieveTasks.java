package oui.sncf.todo.core.usecases.tasks;

import oui.sncf.todo.core.domain.tasks.models.Task;
import oui.sncf.todo.core.domain.tasks.models.TaskStatus;
import oui.sncf.todo.core.domain.tasks.port.TaskLoader;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RetrieveTasks {

    private final TaskLoader taskLoader;

    public RetrieveTasks(TaskLoader taskLoader) {
        this.taskLoader = taskLoader;
    }


    public Set<Task> fetch(TaskStatus filter, TaskStatus sort){
        if(filter == null )
            return taskLoader.fetch();
        return taskLoader.fetch().stream()
                .filter(task -> task.getStatue().equals(filter))
                .collect(Collectors.toSet());
    }

}
