package oui.sncf.todo.unit.usecases.tasks;

import org.junit.jupiter.api.Test;
import oui.sncf.todo.adapters.inmemmories.InMemoryTaskRepository;
import oui.sncf.todo.core.task.Task;
import oui.sncf.todo.core.task.TaskDoesNotExistException;
import oui.sncf.todo.core.port.TaskRepository;
import oui.sncf.todo.usecases.RemoveTask;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class RemoveTaskTest {

    private final TaskRepository taskRepository = new InMemoryTaskRepository();;
    private final RemoveTask removeTask = new RemoveTask(taskRepository);

    @Test
    void should_removed_task(){
        Task task = new Task("ouigo");
        taskRepository.save(task);

        removeTask.by(task.getName());

        Set<Task> tasks = taskRepository.fetch();
        assertThat(tasks.contains(task)).isFalse();
    }
    @Test
    void should_not_be_able_to_remove_a_task_that_does_not_exist(){
        Task task = new Task("ouigo");
        Throwable thrown = catchThrowable(() ->  removeTask.by(task.getName()) );
        assertThat(thrown)
                .isInstanceOf(TaskDoesNotExistException.class)
                .hasMessage("The task ouigo doesn't exist");
    }
}
