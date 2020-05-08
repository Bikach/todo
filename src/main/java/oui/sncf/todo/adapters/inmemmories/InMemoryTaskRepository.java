package oui.sncf.todo.adapters.inmemmories;

import oui.sncf.todo.adapters.dtos.TaskDto;
import oui.sncf.todo.core.port.TaskRepository;
import oui.sncf.todo.core.task.Task;
import oui.sncf.todo.core.task.TaskStatus;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class InMemoryTaskRepository implements TaskRepository {

    private Set<Task> tasks = new LinkedHashSet<>();

    @Override
    public void save(Task task) {
        tasks.add(task);
    }

    @Override
    public void remove(Task task) {
        tasks.remove(task);
    }

    @Override
    public Optional<TaskDto> getByName(String name) {
        return tasks.stream()
                .filter(task -> task.getName().equals(name))
                .map(task ->  new TaskDto(task.getPrefix(), task.getName(), task.getStatus()))
                .findAny();
    }

    @Override
    public Optional<TaskDto> getByPrefix(String prefix) {
        return tasks.stream()
                .filter(task -> task.getPrefix().equals(prefix))
                .map(task ->  new TaskDto(task.getPrefix(), task.getName(), task.getStatus()))
                .findFirst();
    }

    @Override
    public Set<TaskDto> fetch(Optional<TaskStatus> status) {
        return status.isPresent() ? filteredTasks(status.get()) : unfilteredTasks();
    }

    private Set<TaskDto> unfilteredTasks(){
        return tasks.stream()
                .map(task ->  new TaskDto(task.getPrefix(), task.getName(), task.getStatus()))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private Set<TaskDto> filteredTasks(TaskStatus status){
        return tasks.stream()
                .filter(task -> task.getStatus().equals(status))
                .map(task ->  new TaskDto(task.getPrefix(), task.getName(), task.getStatus()))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
