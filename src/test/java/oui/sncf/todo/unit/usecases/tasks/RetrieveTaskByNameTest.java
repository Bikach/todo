package oui.sncf.todo.unit.usecases.tasks;

import org.junit.jupiter.api.Test;
import oui.sncf.todo.adapters.inmemmories.InMemoryTaskRepository;
import oui.sncf.todo.adapters.dtos.TaskDto;
import oui.sncf.todo.core.port.TaskRepository;
import oui.sncf.todo.core.task.Task;
import oui.sncf.todo.core.task.TaskDoesNotExistException;
import oui.sncf.todo.unit.builders.TaskDtoBuilder;
import oui.sncf.todo.usecases.RetrieveTaskByName;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RetrieveTaskByNameTest {

    TaskRepository taskRepository = new InMemoryTaskRepository();
    RetrieveTaskByName retrieveTaskByName = new RetrieveTaskByName(taskRepository);

    @Test
    void should_return_a_task_by_name(){
        Task expectedTask = new Task("ouigo");
        taskRepository.save(expectedTask);

        TaskDto actualTask = retrieveTaskByName.retrieve("ouigo");

        assertThat(actualTask).isEqualTo(
                new TaskDtoBuilder()
                        .name(expectedTask.getName())
                        .status(expectedTask.getStatus())
                        .build()
        );
    }

    @Test
    void should_not_return_a_task_when_the_name_does_not_exist(){
        assertThatThrownBy(() -> retrieveTaskByName.retrieve("bad name"))
                .isInstanceOf(TaskDoesNotExistException.class)
                .hasMessage("The Task doesn't exist.");
    }
}
