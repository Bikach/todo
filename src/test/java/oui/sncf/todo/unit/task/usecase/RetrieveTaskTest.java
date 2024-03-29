package oui.sncf.todo.unit.task.usecase;

import org.junit.jupiter.api.Test;
import oui.sncf.todo.core.domain.TaskRepository;
import oui.sncf.todo.core.domain.task.Task;
import oui.sncf.todo.core.domain.task.TaskStatus;
import oui.sncf.todo.core.domain.task.exception.TaskDoesNotExistException;
import oui.sncf.todo.core.usecases.RetrieveTask;
import oui.sncf.todo.infrastructure.mongoDB.inmemmories.InMemoryTaskRepository;
import oui.sncf.todo.unit.task.builders.TaskBuilder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RetrieveTaskTest {

    TaskRepository taskRepository = new InMemoryTaskRepository();
    RetrieveTask retrieveTask = new RetrieveTask(taskRepository);

    @Test
    void should_return_a_task_by_name(){
        Task task = new Task("ouigo");
        taskRepository.save(task);

        Task actualTask = retrieveTask.byName("ouigo");

        assertThat(actualTask)
                .isEqualTo(new TaskBuilder()
                        .name("ouigo")
                        .status(TaskStatus.TODO)
                        .build()
        );
    }

    @Test
    void should_not_return_a_task_when_the_name_does_not_exist(){
        assertThatThrownBy(() -> retrieveTask.byName("bad name"))
                .isInstanceOf(TaskDoesNotExistException.class)
                .hasMessage("The Task doesn't exist.");
    }
}
