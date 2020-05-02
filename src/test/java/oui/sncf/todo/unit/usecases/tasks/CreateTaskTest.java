package oui.sncf.todo.unit.usecases.tasks;

import org.junit.jupiter.api.Test;
import oui.sncf.todo.adapters.inmemmories.InMemoryTaskRepository;
import oui.sncf.todo.adapters.dtos.TaskDto;
import oui.sncf.todo.core.port.TaskRepository;
import oui.sncf.todo.usecases.CreateTask;

import static org.assertj.core.api.Assertions.assertThat;

class CreateTaskTest {

    private final TaskRepository taskRepository = new InMemoryTaskRepository();;
    private final CreateTask createTask = new CreateTask(taskRepository);

    @Test
    void should_return_a_new_task_that_contain_a_name_without_prefix(){
        createTask.by("ouigo");
        TaskDto actualTask = taskRepository.getByName("ouigo");
        assertThat(actualTask.getName()).isEqualTo("ouigo");
    }

    @Test
    void should_return_a_new_task_that_contain_a_name_with_prefix(){
        createTask.by("prefix","ouigo");
        TaskDto actualTask = taskRepository.getByPrefix("prefix");
        assertThat(actualTask.getName()).isEqualTo("prefix:ouigo");
    }



}