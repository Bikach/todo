package oui.sncf.todo.unit.usecases.tasks;

import org.junit.jupiter.api.Test;
import oui.sncf.todo.adapters.inmemmories.InMemoryTaskRepository;
import oui.sncf.todo.adapters.dtos.TaskDto;
import oui.sncf.todo.core.port.TaskRepository;
import oui.sncf.todo.core.task.Task;
import oui.sncf.todo.core.task.TaskAlwaysInProgressException;
import oui.sncf.todo.core.task.TaskDoesNotExistException;
import oui.sncf.todo.core.task.TaskStatus;
import oui.sncf.todo.unit.builders.TaskDtoBuilder;
import oui.sncf.todo.usecases.RemoveTask;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

public class RemoveTaskTest {

    private final TaskRepository taskRepository = new InMemoryTaskRepository();
    private final RemoveTask removeTask = new RemoveTask(taskRepository);

    private static final TaskStatus NO_FILTER = null;

    @Test
    void should_removed_task(){
        Task task = new Task("ouigo", TaskStatus.DONE);
        taskRepository.save(task);

        removeTask.by(task.getName());

        Set<TaskDto> tasks = taskRepository.fetch(Optional.empty());
        TaskDto taskDto =  new TaskDtoBuilder()
                .name(task.getName())
                .status(task.getStatus())
                .build();

        assertThat(tasks).doesNotContain(taskDto);
    }

    @Test
    void should_not_remove_a_task_that_have_in_progress_status(){
        Task task = new Task("ouigo", TaskStatus.IN_PROGRESS);
        taskRepository.save(task);

        assertThatThrownBy(() -> removeTask.by(task.getName()))
                .isInstanceOf(TaskAlwaysInProgressException.class)
                .hasMessage("The task is already in progress");
    }

    @Test
    void should_not_remove_a_task_that_does_not_exist(){
        assertThatThrownBy(() -> removeTask.by("bad name"))
                .isInstanceOf(TaskDoesNotExistException.class)
                .hasMessage("The Task doesn't exist.");
    }
}
