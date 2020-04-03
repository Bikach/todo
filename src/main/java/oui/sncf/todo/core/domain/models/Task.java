package oui.sncf.todo.core.domain.models;

import java.util.Objects;

public class Task {

    private final String name;
    private final TaskStatue statue;

    public Task(String name) {
        this.name = name;
        this.statue = TaskStatue.IN_PROGRESS;
    }

    public Task(final String name, final TaskStatue statue){
        this.name = name;
        this.statue = statue;
    }

    public String getName() {
        return name;
    }

    public TaskStatue getStatue() {
        return statue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(name, task.name) &&
                statue == task.statue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, statue);
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", statue=" + statue +
                '}';
    }
}
