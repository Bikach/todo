package oui.sncf.todo.unit.usecases.tasks;

import org.junit.jupiter.api.Test;
import oui.sncf.todo.adapters.inmemmories.InMemoryTaskRepository;
import oui.sncf.todo.core.port.TaskRepository;
import oui.sncf.todo.core.task.Task;
import oui.sncf.todo.core.task.TaskStatus;
import oui.sncf.todo.usecases.RemoveTask;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class RemoveTaskTest {

    private final TaskRepository taskRepository = new InMemoryTaskRepository();;
    private final RemoveTask removeTask = new RemoveTask(taskRepository);

    @Test
    void should_removed_task(){
        Task task = new Task("ouigo", TaskStatus.DONE);
        taskRepository.save(task);

        removeTask.by(task.getName());

        Set<Task> tasks = taskRepository.fetch();
        assertThat(tasks).doesNotContain(task);
    }

    @Test
    void should_not_remove_a_task_that_have_in_progress_status(){
        Task task = new Task("ouigo", TaskStatus.IN_PROGRESS);
        taskRepository.save(task);

        removeTask.by(task.getName());

        Set<Task> tasks = taskRepository.fetch();
        assertThat(tasks).containsExactly(task);
    }
}
