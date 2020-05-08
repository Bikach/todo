package oui.sncf.todo.unit.usecases.tasks;

import org.junit.jupiter.api.Test;
import oui.sncf.todo.adapters.inmemmories.InMemoryTaskRepository;
import oui.sncf.todo.adapters.dtos.TaskDto;
import oui.sncf.todo.core.port.TaskRepository;
import oui.sncf.todo.core.task.Task;
import oui.sncf.todo.core.task.TaskDoesNotExistException;
import oui.sncf.todo.unit.builders.TaskDtoBuilder;
import oui.sncf.todo.usecases.RetrieveTask;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RetrieveTaskTest {

    TaskRepository taskRepository = new InMemoryTaskRepository();
    RetrieveTask retrieveTask = new RetrieveTask(taskRepository);

    @Test
    void should_return_a_task_by_name(){
        Task expectedTask = new Task("ouigo");
        taskRepository.save(expectedTask);

        TaskDto actualTask = retrieveTask.byName("ouigo");

        assertThat(actualTask).isEqualTo(
                new TaskDtoBuilder()
                        .name(expectedTask.getName())
                        .status(expectedTask.getStatus())
                        .build()
        );
    }

    @Test
    void should_not_return_a_task_when_the_name_does_not_exist(){
        assertThatThrownBy(() -> retrieveTask.byName("bad name"))
                .isInstanceOf(TaskDoesNotExistException.class)
                .hasMessage("The Task doesn't exist.");
    }

    @Test
    void should_return_a_task_by_prefix(){
        Task expectedTask = new Task("ouigo");
        expectedTask.addPrefix("prefix");
        taskRepository.save(expectedTask);

        TaskDto actualTask = retrieveTask.byPrefix("prefix");

        assertThat(actualTask).isEqualTo(
                new TaskDtoBuilder()
                        .prefix(expectedTask.getPrefix())
                        .name(expectedTask.getName())
                        .status(expectedTask.getStatus())
                        .build()
        );
    }

    @Test
    void should_not_return_a_task_when_the_prefix_does_not_exist(){
        assertThatThrownBy(() -> retrieveTask.byPrefix("bad prefix"))
                .isInstanceOf(TaskDoesNotExistException.class)
                .hasMessage("The Task doesn't exist.");
    }
}
