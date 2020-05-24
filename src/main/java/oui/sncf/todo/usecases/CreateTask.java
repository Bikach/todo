package oui.sncf.todo.usecases;

import oui.sncf.todo.domain.port.TaskRepository;
import oui.sncf.todo.domain.task.Task;

import java.util.Optional;


public class CreateTask {

    private final TaskRepository taskRepository;

    public CreateTask(final TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void by(final String name)  {
        Optional<Task> task = taskRepository.getByName(name);
        task.ifPresent(Task::alreadyExist);
        taskRepository.save(new Task(name));
    }
}
