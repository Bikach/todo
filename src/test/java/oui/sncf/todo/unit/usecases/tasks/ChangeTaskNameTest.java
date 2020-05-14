package oui.sncf.todo.unit.usecases.tasks;

import org.junit.jupiter.api.Test;
import oui.sncf.todo.adapters.inmemmories.InMemoryTaskRepository;
import oui.sncf.todo.core.port.TaskRepository;
import oui.sncf.todo.core.task.Task;
import oui.sncf.todo.core.task.TaskStatus;
import oui.sncf.todo.unit.builders.TaskBuilder;
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
        assertThat(optionalActualTask)
                .isPresent()
                .isEqualTo(Optional.of(
                        new TaskBuilder()
                                .name("nongo")
                                .status(TaskStatus.TODO)
                                .build()
                ));
    }
}
