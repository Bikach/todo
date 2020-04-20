package oui.sncf.todo.unit.usecases.tasks;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import oui.sncf.todo.adapters.secondary.tasksdata.inmemmories.InMemoryTaskLoader;
import oui.sncf.todo.core.domain.tasks.models.Task;
import oui.sncf.todo.core.domain.tasks.models.TaskStatus;
import oui.sncf.todo.core.domain.tasks.port.TaskLoader;
import oui.sncf.todo.core.usecases.tasks.RetrieveTasks;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class RetrieveTasksTest {

    private final TaskLoader taskLoader = new InMemoryTaskLoader();;
    private final RetrieveTasks retrieveTasks = new RetrieveTasks(taskLoader);


    @Test
    void should_return_tasks_without_filter_and_sort(){
        Set<Task> tasks = retrieveTasks.fetch();
        assertThat(tasks.toArray())
                .containsExactly(
                        new Task("task 1"),
                        new Task("task 2"),
                        new Task("task 3", TaskStatus.DONE),
                        new Task("task 4"),
                        new Task("task 5", TaskStatus.DONE)
                );
    }

    @Nested
    class filter {
        @Test
        void should_only_return_done_tasks(){
            Set<Task> doneStatusFilteredTasks = retrieveTasks.FilterByStatus(TaskStatus.DONE);
            assertThat(doneStatusFilteredTasks.toArray())
                    .containsExactly(
                            new Task("task 3", TaskStatus.DONE),
                            new Task("task 5", TaskStatus.DONE)
                    );
        }

        @Test
        void should_only_return_in_progress_tasks(){
            Set<Task> inProgressFilteredTasks = retrieveTasks.FilterByStatus(TaskStatus.IN_PROGRESS);
            assertThat(inProgressFilteredTasks.toArray())
                    .containsExactly(
                            new Task("task 1"),
                            new Task("task 2"),
                            new Task("task 4")
                    );
        }
    }

    @Nested
    class Sort {
        @Test
        void shouldReturnTasksSortedByStatusDone(){
            Set<Task> tasksSorted = retrieveTasks.SortByStatus(TaskStatus.DONE);
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
            Set<Task> tasksSorted = retrieveTasks.SortByStatus(TaskStatus.IN_PROGRESS);
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
}
