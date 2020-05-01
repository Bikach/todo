package oui.sncf.todo.usecases;

import oui.sncf.todo.core.port.TaskRepository;
import oui.sncf.todo.core.task.Task;

public class ChangeTaskName {

    private final TaskRepository taskRepository;

    public ChangeTaskName(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void of(final Task task, final String newName){
        task.changeName(newName);
        taskRepository.save(task);
    }
}
