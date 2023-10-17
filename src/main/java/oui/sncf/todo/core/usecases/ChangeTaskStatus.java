package oui.sncf.todo.core.usecases;

import oui.sncf.todo.core.domain.TaskRepository;
import oui.sncf.todo.core.domain.task.Task;
import oui.sncf.todo.core.domain.task.TaskStatus;

import java.util.Optional;

public class ChangeTaskStatus {

    private final TaskRepository taskRepository;

    public ChangeTaskStatus(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void of(final String taskName, final TaskStatus newStatus){
        Optional<Task> taskOptional = taskRepository.getByName(taskName);
        taskOptional.ifPresent(task -> {
            task.changeStatus(newStatus);
            taskRepository.save(task);
        });
    }
}
