package oui.sncf.todo.unit.usecases.tasks;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import oui.sncf.todo.adapters.driven.inmemmories.InMemoryTaskRepository;
import oui.sncf.todo.domain.port.TaskRepository;
import oui.sncf.todo.domain.task.Task;
import oui.sncf.todo.domain.task.TaskStatus;
import oui.sncf.todo.unit.builders.TaskBuilder;
import oui.sncf.todo.usecases.RetrieveTasks;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class RetrieveTasksTest {


    private static final TaskRepository taskRepository = new InMemoryTaskRepository();
    private final RetrieveTasks retrieveTasks = new RetrieveTasks(taskRepository);

    @BeforeAll
    static void initAll(){
        taskRepository.save(new Task("task 1"));
        taskRepository.save(new Task("task 2"));
        taskRepository.save(new Task("task 3", TaskStatus.DONE));
        taskRepository.save(new Task("task 4"));
        taskRepository.save(new Task("task 5", TaskStatus.DONE));
    }


    @Test
    void should_return_tasks_without_filter(){
        Set<Task> tasks = retrieveTasks.retrieve(null);
        assertThat(tasks.toArray())
                .containsExactly(
                        new TaskBuilder().name("task 1").status(TaskStatus.TODO).build(),
                        new TaskBuilder().name("task 2").status(TaskStatus.TODO).build(),
                        new TaskBuilder().name("task 3").status(TaskStatus.DONE).build(),
                        new TaskBuilder().name("task 4").status(TaskStatus.TODO).build(),
                        new TaskBuilder().name("task 5").status(TaskStatus.DONE).build()
                );
    }


    @Test
    void should_only_return_done_tasks(){
        Set<Task> doneStatusFilteredTasks = retrieveTasks.retrieve(TaskStatus.DONE);
        assertThat(doneStatusFilteredTasks.toArray())
                .containsExactly(
                        new TaskBuilder().name("task 3").status(TaskStatus.DONE).build(),
                        new TaskBuilder().name("task 5").status(TaskStatus.DONE).build()
                );
    }

    @Test
    void should_only_return_in_progress_tasks(){
        Set<Task> inProgressFilteredTasks = retrieveTasks.retrieve(TaskStatus.TODO);
        assertThat(inProgressFilteredTasks.toArray())
                .containsExactly(
                        new TaskBuilder().name("task 1").status(TaskStatus.TODO).build(),
                        new TaskBuilder().name("task 2").status(TaskStatus.TODO).build(),
                        new TaskBuilder().name("task 4").status(TaskStatus.TODO).build()
                );
    }
}
