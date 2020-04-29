package oui.sncf.todo.unit.usecases.tasks;

import org.junit.jupiter.api.Test;
import oui.sncf.todo.adapters.inmemmories.InMemoryTaskRepository;
import oui.sncf.todo.adapters.mongodb.TaskDto;
import oui.sncf.todo.core.port.TaskRepository;
import oui.sncf.todo.usecases.CreateTask;

import static org.assertj.core.api.Assertions.assertThat;

class CreateTaskTest {

    private final TaskRepository taskRepository = new InMemoryTaskRepository();;
    private final CreateTask createTask = new CreateTask(taskRepository);

    @Test
    void should_return_a_new_task_called_ouigo(){
        createTask.by("ouigo");
        TaskDto actualTask = taskRepository.getByName("ouigo");
        assertThat(actualTask.getName()).isEqualTo("ouigo");
    }
}