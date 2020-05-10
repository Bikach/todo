package oui.sncf.todo.unit.builders;

import oui.sncf.todo.core.task.Task;
import oui.sncf.todo.core.task.TaskStatus;

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
