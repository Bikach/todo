package oui.sncf.todo.unit.usecases.tasks;

import org.junit.jupiter.api.Test;
import oui.sncf.todo.adapters.inmemmories.InMemoryTaskRepository;
import oui.sncf.todo.core.task.Task;
import oui.sncf.todo.core.task.TaskAlreadyExistException;
import oui.sncf.todo.core.port.TaskRepository;
import oui.sncf.todo.usecases.CreateTask;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class CreateTaskTest {

    private final TaskRepository taskRepository = new InMemoryTaskRepository();;
    private final CreateTask createTask = new CreateTask(taskRepository);

    @Test
    void should_return_a_new_task_called_ouigo(){
        Task actualTask = createTask.of("ouigo");
        assertThat(actualTask).isEqualTo(new Task("ouigo"));
    }

    @Test
    void should_not_be_able_to_create_a_task_that_already_exists(){
        createTask.of("ouigo");
        Throwable thrown = catchThrowable(() ->  createTask.of("ouigo") );
        assertThat(thrown)
                .isInstanceOf(TaskAlreadyExistException.class)
                .hasMessage("Task{name='ouigo', statue=IN_PROGRESS} already exist");
    }
}