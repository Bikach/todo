package oui.sncf.todo.unit.usecases.tasks;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import oui.sncf.todo.adapters.inmemmories.InMemoryTaskRepository;
import oui.sncf.todo.adapters.dtos.TaskDto;
import oui.sncf.todo.core.port.TaskRepository;
import oui.sncf.todo.core.task.Task;
import oui.sncf.todo.core.task.TaskStatus;
import oui.sncf.todo.unit.builders.TaskDtoBuilder;
import oui.sncf.todo.usecases.RetrieveTasks;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class RetrieveTasksTest {


    private static final TaskRepository taskRepository = new InMemoryTaskRepository();
    private final RetrieveTasks retrieveTasks = new RetrieveTasks(taskRepository);

    private static final String NO_PREFIX = "";

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
        Set<TaskDto> tasks = retrieveTasks.retrieve(Optional.empty());
        assertThat(tasks.toArray())
                .containsExactly(
                        new TaskDtoBuilder().name("task 1").status(TaskStatus.TODO).build(),
                        new TaskDtoBuilder().name("task 2").status(TaskStatus.TODO).build(),
                        new TaskDtoBuilder().name("task 3").status(TaskStatus.DONE).build(),
                        new TaskDtoBuilder().name("task 4").status(TaskStatus.TODO).build(),
                        new TaskDtoBuilder().name("task 5").status(TaskStatus.DONE).build()
                );
    }


    @Test
    void should_only_return_done_tasks(){
        Set<TaskDto> doneStatusFilteredTasks = retrieveTasks.retrieve(Optional.of(TaskStatus.DONE));
        assertThat(doneStatusFilteredTasks.toArray())
                .containsExactly(
                        new TaskDtoBuilder().name("task 3").status(TaskStatus.DONE).build(),
                        new TaskDtoBuilder().name("task 5").status(TaskStatus.DONE).build()
                );
    }

    @Test
    void should_only_return_in_progress_tasks(){
        Set<TaskDto> inProgressFilteredTasks = retrieveTasks.retrieve(Optional.of(TaskStatus.TODO));
        assertThat(inProgressFilteredTasks.toArray())
                .containsExactly(
                        new TaskDtoBuilder().name("task 1").status(TaskStatus.TODO).build(),
                        new TaskDtoBuilder().name("task 2").status(TaskStatus.TODO).build(),
                        new TaskDtoBuilder().name("task 4").status(TaskStatus.TODO).build()
                );
    }

}
