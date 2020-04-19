package oui.sncf.todo.unit.usecases.tasks;

import org.junit.jupiter.api.Test;
import oui.sncf.todo.adapters.secondary.tasksdata.inmemmories.InMemoryTaskRepository;
import oui.sncf.todo.core.domain.tasks.models.Task;
import oui.sncf.todo.core.domain.tasks.models.TaskDoesNotExistException;
import oui.sncf.todo.core.domain.tasks.port.TaskRepository;
import oui.sncf.todo.core.usecases.tasks.RetrieveByName;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class RetrieveTaskBNameTest {

    TaskRepository taskRepository = new InMemoryTaskRepository();
    RetrieveByName retrieveByName = new RetrieveByName(taskRepository);

    @Test
    void should_return_a_task_by_name(){
        Task expectedTask = new Task("ouigo");
        taskRepository.save(expectedTask);

        Task actualTask = retrieveByName.get("ouigo");

        assertThat(actualTask).isEqualTo(expectedTask);
    }

    @Test
    void should_not_return_the_task_when_it_does_not_exit(){
        Throwable thrown = catchThrowable(() ->  retrieveByName.get("ouigo"));
        assertThat(thrown)
                .isInstanceOf(TaskDoesNotExistException.class)
                .hasMessage("ouigo doesn't exist");
    }
}
