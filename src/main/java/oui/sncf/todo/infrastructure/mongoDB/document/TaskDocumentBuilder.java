package oui.sncf.todo.infrastructure.mongoDB.document;

import oui.sncf.todo.core.domain.task.TaskStatus;

public class TaskDocumentBuilder {

    private String name;
    private TaskStatus status;

    public TaskDocumentBuilder() {}

    public TaskDocument build(){
        return new TaskDocument(this.name, this.status);
    }

    public TaskDocumentBuilder name(String name){
        this.name = name;
        return this;
    }

    public TaskDocumentBuilder status(TaskStatus status){
        this.status = status;
        return this;
    }
}
