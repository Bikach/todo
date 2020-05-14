package oui.sncf.todo.usecases;

import oui.sncf.todo.core.port.TaskRepository;
import oui.sncf.todo.core.task.Task;

import java.util.Optional;

public class ChangeTaskName {

    private final TaskRepository taskRepository;

    public ChangeTaskName(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void of(final String oldName, final String newName){
        Optional<Task> taskOptional = taskRepository.getByName(oldName);
        taskOptional.ifPresent(task -> {
            task.changeName(newName);
            taskRepository.save(task);
        });
    }
}
