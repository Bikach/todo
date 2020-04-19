package oui.sncf.todo.unit.usecases.tasks;

import org.junit.jupiter.api.Test;
import oui.sncf.todo.adapters.secondary.tasksdata.inmemmories.InMemoryTaskRepository;
import oui.sncf.todo.core.domain.tasks.models.Task;
import oui.sncf.todo.core.domain.tasks.models.TaskAlreadyExistException;
import oui.sncf.todo.core.domain.tasks.port.TaskRepository;
import oui.sncf.todo.core.usecases.tasks.CreateTaskByName;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class CreateTaskByNameTest {

    private final TaskRepository taskRepository = new InMemoryTaskRepository();;
    private final CreateTaskByName createTaskByName = new CreateTaskByName(taskRepository);

    @Test
    void should_return_a_new_task_called_ouigo(){
        Task actualTask = createTaskByName.of("ouigo");
        assertThat(actualTask).isEqualTo(new Task("ouigo"));
    }

    @Test
    void should_not_be_able_to_create_a_task_that_already_exists(){
        createTaskByName.of("ouigo");
        Throwable thrown = catchThrowable(() ->  createTaskByName.of("ouigo") );
        assertThat(thrown)
                .isInstanceOf(TaskAlreadyExistException.class)
                .hasMessage("Task{name='ouigo', statue=IN_PROGRESS} already exist");
    }
}