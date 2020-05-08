package oui.sncf.todo.usecases;

import oui.sncf.todo.core.port.TaskRepository;
import oui.sncf.todo.core.task.Task;

import java.util.Optional;


public class CreateTask {

    private final TaskRepository taskRepository;

    public CreateTask(final TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void by(final String name)  {
        Optional<Task> task = getTask(name);
        task.ifPresent(Task::alreadyExist);
        taskRepository.save(new Task(name));
    }

    private Optional<Task> getTask(String nameTask){
        return taskRepository.getByName(nameTask)
                .map(taskDto -> new Task(taskDto.getName(), taskDto.getStatus()));
    }
}
