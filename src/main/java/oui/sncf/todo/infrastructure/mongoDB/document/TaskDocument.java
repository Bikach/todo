package oui.sncf.todo.infrastructure.mongoDB.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import oui.sncf.todo.core.domain.task.TaskStatus;

import java.util.Objects;

@Document("task")
public class TaskDocument {

    @Id
    private String id;
    private final String name;
    private final TaskStatus status;

    public TaskDocument(String name, TaskStatus status) {
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public TaskStatus getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskDocument o1 = (TaskDocument) o;
        return Objects.equals(name, o1.name) &&
                status == o1.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, status);
    }
}
