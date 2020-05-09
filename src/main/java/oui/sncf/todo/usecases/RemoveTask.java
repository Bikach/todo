package oui.sncf.todo.usecases;

import oui.sncf.todo.core.port.TaskRepository;
import oui.sncf.todo.core.task.Task;
import oui.sncf.todo.core.task.TaskDoesNotExistException;

public class RemoveTask {

    private final TaskRepository taskRepository;

    public RemoveTask(final TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void by(final String taskName) {
        Task task = getTask(taskName);
        task.isAlreadyTodo();
        taskRepository.remove(task);
    }

    private Task getTask(String nameTask){
        return taskRepository.getByName(nameTask)
                .map(taskDto -> new Task(taskDto.getName(), taskDto.getStatus()))
                .orElseThrow(() -> new TaskDoesNotExistException("The Task doesn't exist."));
    }
}
