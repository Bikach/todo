package oui.sncf.todo.usecases;

import oui.sncf.todo.adapters.dtos.TaskDto;
import oui.sncf.todo.core.port.TaskRepository;
import oui.sncf.todo.core.task.Task;
import oui.sncf.todo.core.task.TaskDoesNotExistException;
import oui.sncf.todo.core.task.TaskStatus;

public class ChangeTaskStatus {

    private final TaskRepository taskRepository;

    public ChangeTaskStatus(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void of(final String nameTask, final TaskStatus newStatus){
        TaskDto taskDto = getTaskDto(nameTask);
        Task task = new Task(taskDto.getName(), taskDto.getStatus());
        task.changeStatus(newStatus);
        taskRepository.save(task);
    }

    private TaskDto getTaskDto(String nameTask){
        return taskRepository.getByName(nameTask)
                .orElseThrow(() -> new TaskDoesNotExistException("The Task doesn't exist."));
    }
}
