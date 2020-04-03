package oui.sncf.todo.unit.usecases;

import org.junit.jupiter.api.Test;
import oui.sncf.todo.adapters.secondary.inmemmories.repositories.InMemoryTaskRepository;
import oui.sncf.todo.core.domain.models.Task;
import oui.sncf.todo.core.domain.models.TaskStatus;
import oui.sncf.todo.core.domain.port.repositories.TasksRepository;
import oui.sncf.todo.core.usecases.FilterTheTasks;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilterTheTasksTest {

    private final TasksRepository tasksRepository = new InMemoryTaskRepository();;
    private final FilterTheTasks filterTheTasks = new FilterTheTasks(tasksRepository);

    @Test
    void should_only_return_done_tasks(){
        tasksRepository.save(new Task("Tache 1"));
        tasksRepository.save(new Task("Tache 2", TaskStatus.DONE));
        tasksRepository.save(new Task("Tache 3", TaskStatus.IN_PROGRESS));
        tasksRepository.save(new Task("Tache 4", TaskStatus.DONE));

        Set<Task> tasksFilterByDoneStatus = filterTheTasks.byDoneStatus();

        assertEquals(2, tasksFilterByDoneStatus.size());
    }

    @Test
    void should_only_return_the_tasks_in_progress(){
        tasksRepository.save(new Task("Tache 1"));
        tasksRepository.save(new Task("Tache 2", TaskStatus.IN_PROGRESS));
        tasksRepository.save(new Task("Tache 3", TaskStatus.IN_PROGRESS));
        tasksRepository.save(new Task("Tache 4", TaskStatus.DONE));

        Set<Task> tasksFilterByTaskInProgress = filterTheTasks.byInProgressStatus();

        assertEquals(3, tasksFilterByTaskInProgress.size());
    }

}
