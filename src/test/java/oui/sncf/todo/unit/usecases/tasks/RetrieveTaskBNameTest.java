package oui.sncf.todo.unit.usecases.tasks;

import org.junit.jupiter.api.Test;
import oui.sncf.todo.adapters.inmemmories.InMemoryTaskRepository;
import oui.sncf.todo.core.task.Task;
import oui.sncf.todo.core.task.TaskDoesNotExistException;
import oui.sncf.todo.core.port.TaskRepository;
import oui.sncf.todo.usecases.RetrieveTask;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class RetrieveTaskBNameTest {

    TaskRepository taskRepository = new InMemoryTaskRepository();
    RetrieveTask retrieveTask = new RetrieveTask(taskRepository);

    @Test
    void should_return_a_task_by_name(){
        Task expectedTask = new Task("ouigo");
        taskRepository.save(expectedTask);

        Task actualTask = retrieveTask.get("ouigo");

        assertThat(actualTask).isEqualTo(expectedTask);
    }

    @Test
    void should_not_return_the_task_when_it_does_not_exit(){
        Throwable thrown = catchThrowable(() ->  retrieveTask.get("ouigo"));
        assertThat(thrown)
                .isInstanceOf(TaskDoesNotExistException.class)
                .hasMessage("The task ouigo doesn't exist");
    }
}
