package oui.sncf.todo.infrastructure.mongoDB.mongodb;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;
import oui.sncf.todo.core.domain.TaskRepository;
import oui.sncf.todo.core.domain.task.Task;
import oui.sncf.todo.core.domain.task.TaskStatus;
import oui.sncf.todo.infrastructure.mongoDB.document.TaskDocument;
import oui.sncf.todo.infrastructure.mongoDB.document.TaskDocumentBuilder;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@EnableMongoRepositories(basePackages = "oui.sncf.todo.adapters.driven.mongodb")
public class MongoDbTaskRepository implements TaskRepository {

    public static final String COLLECTION_NAME = "task";
    public static final String NAME_CRITERIA = "name";

    private final MongoTemplate mongoTemplate;

    public MongoDbTaskRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void save(Task task) {
        TaskDocument taskDto = new TaskDocumentBuilder().name(task.getName()).status(task.getStatus()).build();
        Query query = Query.query(Criteria.where(NAME_CRITERIA).is(taskDto.getName()));
        mongoTemplate.remove(query, TaskDocument.class);
        mongoTemplate.save(taskDto, COLLECTION_NAME);
    }

    @Override
    public void remove(Task task) {
        TaskDocument taskDto = new TaskDocumentBuilder().name(task.getName()).status(task.getStatus()).build();
        Query query = Query.query(Criteria.where(NAME_CRITERIA).is(taskDto.getName()));
        mongoTemplate.remove(query, TaskDocument.class, COLLECTION_NAME);
    }

    @Override
    public Optional<Task> getByName(String taskName) {
        Query query = Query.query(Criteria.where(NAME_CRITERIA).is(taskName));
        return mongoTemplate
                .find(query, TaskDocument.class)
                .stream()
                .map(document ->  new Task(document.getName(), document.getStatus()))
                .findAny();
    }

    @Override
    public Set<Task> fetch(TaskStatus status) {
        Optional<TaskStatus> optionalStatus = Optional.ofNullable(status);
        return optionalStatus.map(taskStatus -> mongoTemplate.findAll(TaskDocument.class)
                .stream()
                .filter(taskDto -> taskDto.getStatus().equals(taskStatus))
                .map(taskDto ->  new Task(taskDto.getName(), taskDto.getStatus()))
                .collect(Collectors.toCollection(LinkedHashSet::new)))
                .orElseGet(this::unfilteredTasks);
    }

    private LinkedHashSet<Task> unfilteredTasks(){
        return mongoTemplate.findAll(TaskDocument.class).stream()
                .map(taskDto ->  new Task(taskDto.getName(), taskDto.getStatus()))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
