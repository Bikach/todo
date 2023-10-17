package oui.sncf.todo.unit.task.builders;

import oui.sncf.todo.core.domain.task.Task;
import oui.sncf.todo.core.domain.task.TaskStatus;

public class TaskBuilder {

    private String name;
    private TaskStatus status;

    public TaskBuilder() {}

    public Task build(){
        return new Task(this.name, this.status);
    }

    public TaskBuilder name(String name){
        this.name = name;
        return this;
    }

    public TaskBuilder status(TaskStatus status){
        this.status = status;
        return this;
    }
}
