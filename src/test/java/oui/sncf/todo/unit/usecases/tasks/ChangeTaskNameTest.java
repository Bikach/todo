package oui.sncf.todo.unit.usecases.tasks;

import org.junit.jupiter.api.Test;
import oui.sncf.todo.adapters.driven.inmemmories.InMemoryTaskRepository;
import oui.sncf.todo.domain.port.TaskRepository;
import oui.sncf.todo.domain.task.Task;
import oui.sncf.todo.usecases.ChangeTaskName;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class ChangeTaskNameTest {

    private final TaskRepository taskRepository = new InMemoryTaskRepository();
    private final ChangeTaskName changeTaskName = new ChangeTaskName(taskRepository);


    @Test
    void should_change_the_task_name(){
        Task task = new Task("ouigo");
        taskRepository.save(task);

        changeTaskName.of("ouigo", "nongo");

        Optional<Task> optionalActualTask = taskRepository.getByName("nongo");
        assertThat(optionalActualTask).isPresent();

        Optional<Task> oldOptionalTask = taskRepository.getByName("ouigo");
        assertThat(oldOptionalTask).isNotPresent();
    }
}
