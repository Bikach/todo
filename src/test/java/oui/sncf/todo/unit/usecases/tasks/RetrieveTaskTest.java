package oui.sncf.todo.unit.usecases.tasks;

import org.junit.jupiter.api.Test;
import oui.sncf.todo.adapters.driven.inmemmories.InMemoryTaskRepository;
import oui.sncf.todo.domain.port.TaskRepository;
import oui.sncf.todo.domain.task.Task;
import oui.sncf.todo.domain.task.TaskDoesNotExistException;
import oui.sncf.todo.domain.task.TaskStatus;
import oui.sncf.todo.unit.builders.TaskBuilder;
import oui.sncf.todo.usecases.RetrieveTask;

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
