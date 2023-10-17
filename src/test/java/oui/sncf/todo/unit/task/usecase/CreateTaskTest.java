package oui.sncf.todo.unit.task.usecase;

import org.junit.jupiter.api.Test;
import oui.sncf.todo.core.domain.TaskRepository;
import oui.sncf.todo.core.domain.task.Task;
import oui.sncf.todo.core.domain.task.TaskStatus;
import oui.sncf.todo.core.domain.task.exception.TaskAlreadyExistException;
import oui.sncf.todo.core.usecases.CreateTask;
import oui.sncf.todo.infrastructure.mongoDB.inmemmories.InMemoryTaskRepository;
import oui.sncf.todo.unit.task.builders.TaskBuilder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CreateTaskTest {

    private final TaskRepository taskRepository = new InMemoryTaskRepository();
    private final CreateTask createTask = new CreateTask(taskRepository);

    @Test
    void should_create_a_new_task_that_contain_a_name(){
        createTask.by("ouigo");
        Optional<Task> optionalTask = taskRepository.getByName("ouigo");

        assertThat(optionalTask)
                .isPresent()
                .isEqualTo(
                        Optional.of(new TaskBuilder()
                                .name("ouigo")
                                .status(TaskStatus.TODO)
                                .build()
                        )
                );
    }

    @Test
    void should_not_create_a_new_task_when_it_already_exist(){
        taskRepository.save(new Task("ouigo"));
        assertThatThrownBy(() -> createTask.by("ouigo"))
                .isInstanceOf(TaskAlreadyExistException.class)
                .hasMessage("The task already exist.");
    }
}