package oui.sncf.todo.usecases;

import oui.sncf.todo.domain.port.TaskRepository;
import oui.sncf.todo.domain.task.Task;

import java.util.Optional;

public class RemoveTask {

    private final TaskRepository taskRepository;

    public RemoveTask(final TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void by(final String taskName) {
        Optional<Task> taskOptional = taskRepository.getByName(taskName);
        taskOptional.ifPresent(task -> {
            task.isAlreadyTodo();
            taskRepository.remove(task);
        });
    }
}
