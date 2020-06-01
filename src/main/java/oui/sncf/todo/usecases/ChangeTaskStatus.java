package oui.sncf.todo.usecases;

import oui.sncf.todo.domain.port.TaskRepository;
import oui.sncf.todo.domain.task.Task;
import oui.sncf.todo.domain.task.TaskStatus;

import java.util.Optional;

public class ChangeTaskStatus {

    private final TaskRepository taskRepository;

    public ChangeTaskStatus(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void of(final String taskName, final TaskStatus newStatus){
        Optional<Task> taskOptional = taskRepository.getByName(taskName);
        taskOptional.ifPresent(task -> {
            taskRepository.remove(task);
            task.changeStatus(newStatus);
            taskRepository.save(task);
        });
    }
}
