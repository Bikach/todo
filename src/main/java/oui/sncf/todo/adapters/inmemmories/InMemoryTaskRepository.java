package oui.sncf.todo.adapters.inmemmories;

import oui.sncf.todo.adapters.mongodb.TaskDto;
import oui.sncf.todo.core.port.TaskRepository;
import oui.sncf.todo.core.task.Task;
import oui.sncf.todo.core.task.TaskStatus;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class InMemoryTaskRepository implements TaskRepository {

    private Set<Task> tasks = new LinkedHashSet<>();

    @Override
    public void save(Task task) {
        tasks.add(task);
    }

    @Override
    public boolean remove(Task task) {
        return tasks.remove(task);
    }

    @Override
    public Task getByName(String name) {
        return tasks.stream()
                .filter(task -> task.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Set<TaskDto> fetch(TaskStatus status) {
        return (status == null) ? unfilteredTasks(): filteredTasks(status);

    }

    private Set<TaskDto> unfilteredTasks(){
        return tasks.stream()
                .map(task -> new TaskDto(task.getName(), task.getStatus()))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private Set<TaskDto> filteredTasks(TaskStatus status){
        return tasks.stream()
                .filter(task -> task.getStatus().equals(status))
                .map(task -> new TaskDto(task.getName(), task.getStatus()))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
