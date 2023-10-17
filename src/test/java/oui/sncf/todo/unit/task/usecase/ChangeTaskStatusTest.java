package oui.sncf.todo.unit.task.usecase;

import org.junit.jupiter.api.Test;
import oui.sncf.todo.core.domain.TaskRepository;
import oui.sncf.todo.core.domain.task.Task;
import oui.sncf.todo.core.domain.task.TaskStatus;
import oui.sncf.todo.infrastructure.mongoDB.inmemmories.InMemoryTaskRepository;
import oui.sncf.todo.unit.task.builders.TaskBuilder;
import oui.sncf.todo.core.usecases.ChangeTaskStatus;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class ChangeTaskStatusTest {

    public static final String NAME_TASK = "ouigo";
    private final TaskRepository taskRepository = new InMemoryTaskRepository();
    private final ChangeTaskStatus changeTaskStatus = new ChangeTaskStatus(taskRepository);

    @Test
    void should_return_a_task_that_has_changed_to_done(){
        Task task = new Task(NAME_TASK, TaskStatus.TODO);
        taskRepository.save(task);

        changeTaskStatus.of(NAME_TASK, TaskStatus.DONE);


        Optional<Task> optionalActualTask = taskRepository.getByName(NAME_TASK);
        assertThat(optionalActualTask)
                .isPresent()
                .isEqualTo(Optional.of(
                        new TaskBuilder()
                                .name(NAME_TASK)
                                .status(TaskStatus.DONE)
                                .build()
                ));
    }

    @Test
    void should_return_a_task_that_has_changed_to_todo(){
        Task task = new Task(NAME_TASK, TaskStatus.DONE);
        taskRepository.save(task);

        changeTaskStatus.of(NAME_TASK, TaskStatus.TODO);

        Optional<Task> optionalActualTask = taskRepository.getByName(NAME_TASK);
        assertThat(optionalActualTask)
                .isPresent()
                .isEqualTo(Optional.of(
                        new TaskBuilder()
                                .name(NAME_TASK)
                                .status(TaskStatus.TODO)
                                .build()
                ));
    }
}
