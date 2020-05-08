package oui.sncf.todo.usecases;

import oui.sncf.todo.adapters.dtos.TaskDto;
import oui.sncf.todo.core.port.TaskRepository;
import oui.sncf.todo.core.task.Task;
import oui.sncf.todo.core.task.TaskDoesNotExistException;

public class RemoveTask {

    private final TaskRepository taskRepository;

    public RemoveTask(final TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void by(final String taskName) {
        TaskDto taskDto = getTaskDto(taskName);
        Task task = new Task(taskDto.getName(), taskDto.getStatus());
        task.isAlreadyInProgress();
        taskRepository.remove(task);
    }

    private TaskDto getTaskDto(String nameTask){
        return taskRepository.getByName(nameTask)
                .orElseThrow(() -> new TaskDoesNotExistException("The Task doesn't exist."));
    }
}
