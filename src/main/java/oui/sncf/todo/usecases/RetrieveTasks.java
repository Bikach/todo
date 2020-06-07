package oui.sncf.todo.usecases;

import oui.sncf.todo.domain.port.DisplayTasks;
import oui.sncf.todo.domain.port.TaskRepository;
import oui.sncf.todo.domain.task.Task;
import oui.sncf.todo.domain.task.TaskStatus;

import java.util.Set;

public class RetrieveTasks implements DisplayTasks {

    private final TaskRepository taskRepository;

    public RetrieveTasks(final TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Set<Task> retrieve(TaskStatus status){
        return taskRepository.fetch(status);
    }
}
