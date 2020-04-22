package oui.sncf.todo.usecases;

import oui.sncf.todo.core.task.Task;
import oui.sncf.todo.core.task.TaskStatus;
import oui.sncf.todo.core.port.TaskRepository;

public class ChangeTaskStatus {

    private final TaskRepository taskRepository;

    public ChangeTaskStatus(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void of(String nameTask, TaskStatus newStatus){
        Task task = taskRepository.getByName(nameTask);
        task.changeStatus(newStatus);
        taskRepository.save(task);
    }
}
