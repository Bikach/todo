package oui.sncf.todo.core.usecases;

import oui.sncf.todo.core.domain.TaskRepository;
import oui.sncf.todo.core.domain.task.Task;

import java.util.Optional;

public class ChangeTaskName {

    private final TaskRepository taskRepository;

    public ChangeTaskName(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void of(final String oldName, final String newName){
        Optional<Task> taskOptional = taskRepository.getByName(oldName);
        taskOptional.ifPresent(task -> {
            taskRepository.remove(task);
            task.changeName(newName);
            taskRepository.save(task);
        });
    }
}
