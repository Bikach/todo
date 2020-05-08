package oui.sncf.todo.unit.usecases.tasks;

import org.junit.jupiter.api.Test;
import oui.sncf.todo.adapters.inmemmories.InMemoryTaskRepository;
import oui.sncf.todo.adapters.dtos.TaskDto;
import oui.sncf.todo.core.task.Task;
import oui.sncf.todo.core.task.TaskDoesNotExistException;
import oui.sncf.todo.core.task.TaskStatus;
import oui.sncf.todo.core.port.TaskRepository;
import oui.sncf.todo.usecases.ChangeTaskStatus;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class changeTaskStatusTest {

    private final TaskRepository taskRepository = new InMemoryTaskRepository();;
    private final ChangeTaskStatus changeTaskStatus = new ChangeTaskStatus(taskRepository);

    @Test
    void should_return_a_task_that_has_changed_to_done(){
        Task task = new Task("ouigo");
        taskRepository.save(task);

        changeTaskStatus.of("ouigo", TaskStatus.DONE);

        TaskDto actualTask = taskRepository.getByName(task.getName()).get();
        assertThat(new Task(actualTask.getName(), actualTask.getStatus()))
                .isEqualTo(new Task("ouigo", TaskStatus.DONE));
    }

    @Test
    void should_return_a_task_that_has_changed_to_in_progress(){
        Task task = new Task("ouigo", TaskStatus.DONE);
        taskRepository.save(task);

        changeTaskStatus.of("ouigo", TaskStatus.IN_PROGRESS);

        TaskDto actualTask = taskRepository.getByName(task.getName()).get();
        assertThat(new Task(actualTask.getName(), actualTask.getStatus()))
                .isEqualTo(new Task("ouigo", TaskStatus.IN_PROGRESS));
    }

    @Test
    void should_can_not_change_the_status_when_the_task_does_not_exist(){
        assertThatThrownBy(() -> changeTaskStatus.of("bad name", TaskStatus.IN_PROGRESS))
                .isInstanceOf(TaskDoesNotExistException.class)
                .hasMessage("The Task doesn't exist.");
    }
}
