package oui.sncf.todo.adapters.mongodb;

import oui.sncf.todo.core.task.TaskStatus;

import java.util.Objects;

public class TaskDto {

    private final String name;
    private TaskStatus status;

    public TaskDto(String name) {
        this.name = name;
    }

    public TaskDto(String name, TaskStatus status) {
        this.name = name;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskDto taskDto = (TaskDto) o;
        return Objects.equals(name, taskDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "TaskDto{" +
                "name='" + name + '\'' +
                ", status=" + status +
                '}';
    }
}
