package oui.sncf.todo.usecases;

import oui.sncf.todo.adapters.dtos.TaskDto;
import oui.sncf.todo.core.task.Task;
import oui.sncf.todo.core.task.TaskStatus;
import oui.sncf.todo.core.port.TaskRepository;

public class ChangeTaskStatus {

    private final TaskRepository taskRepository;

    public ChangeTaskStatus(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void of(String nameTask, TaskStatus newStatus){
        TaskDto taskDto = taskRepository.getByName(nameTask);
        Task task = new Task(taskDto.getName(), taskDto.getStatus());
        task.changeStatus(newStatus);
        taskRepository.save(task);
    }
}
