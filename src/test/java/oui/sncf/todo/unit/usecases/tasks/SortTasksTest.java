package oui.sncf.todo.unit.usecases.tasks;

import org.junit.jupiter.api.Test;
import oui.sncf.todo.adapters.secondary.tasksdata.inmemmories.InMemoryTaskLoader;
import oui.sncf.todo.core.domain.tasks.models.Task;
import oui.sncf.todo.core.domain.tasks.models.TaskStatus;
import oui.sncf.todo.core.domain.tasks.port.TaskLoader;
import oui.sncf.todo.core.usecases.tasks.SortTasks;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class SortTasksTest {

    TaskLoader taskLoader = new InMemoryTaskLoader();
    SortTasks tasksByStatusDone = new SortTasks(taskLoader);

    @Test
    void shouldReturnTasksSortedByStatusDone(){
        Set<Task> tasksSorted = tasksByStatusDone.ByStatus(TaskStatus.DONE);
        assertThat(tasksSorted.toArray())
                .containsExactly(
                        new Task("task 3", TaskStatus.DONE),
                        new Task("task 5", TaskStatus.DONE),
                        new Task("task 1"),
                        new Task("task 2"),
                        new Task("task 4")
                );
    }

    @Test
    void shouldReturnTasksSortedByStatusInProgress(){
        Set<Task> tasksSorted = tasksByStatusDone.ByStatus(TaskStatus.IN_PROGRESS);
        assertThat(tasksSorted.toArray())
                .containsExactly(
                        new Task("task 1"),
                        new Task("task 2"),
                        new Task("task 4"),
                        new Task("task 3", TaskStatus.DONE),
                        new Task("task 5", TaskStatus.DONE)
                );
    }
}
