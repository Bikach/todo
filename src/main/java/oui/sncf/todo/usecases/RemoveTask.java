package oui.sncf.todo.usecases;

import oui.sncf.todo.adapters.dtos.TaskDto;
import oui.sncf.todo.core.port.TaskRepository;
import oui.sncf.todo.core.task.Task;

public class RemoveTask {

    private final TaskRepository taskRepository;

    public RemoveTask(final TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void by(final String taskName) {
        //TODO
        TaskDto taskDto = taskRepository.getByName(taskName).get();
        Task task = new Task(taskDto.getName(), taskDto.getStatus());
        task.isAlreadyInProgress();
        taskRepository.remove(task);
    }
}
