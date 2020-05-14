package oui.sncf.todo.adapters.inmemmories;

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
    public Optional<Task> getByName(String name) {
        return tasks.stream()
                .map(task -> new Task(task.getName(), task.getStatus()))
                .filter(task -> task.getName().equals(name))
                .findAny();
    }

    @Override
    public Set<Task> fetch(TaskStatus status) {
        return status == null ? unfilteredTasks() : filteredTasks(status) ;
    }

    private Set<Task> unfilteredTasks(){
        return tasks;
    }

    private Set<Task> filteredTasks(TaskStatus status){
        return tasks.stream()
                .filter(task -> task.getStatus().equals(status))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
