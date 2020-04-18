package oui.sncf.todo.core.domain.tasks.models;

import java.util.Objects;

public class Task {

    private final String name;
    private final TaskStatus statue;

    public Task(String name) {
        this.name = name;
        this.statue = TaskStatus.IN_PROGRESS;
    }

    public Task(final String name, final TaskStatus statue){
        this.name = name;
        this.statue = statue;
    }

    public String getName() {
        return name;
    }

    public TaskStatus getStatue() {
        return statue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(name, task.name) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", statue=" + statue +
                '}';
    }
}
