package oui.sncf.todo.adapters.mongodb;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;
import oui.sncf.todo.adapters.dtos.TaskDto;
import oui.sncf.todo.core.port.TaskRepository;
import oui.sncf.todo.core.task.Task;
import oui.sncf.todo.core.task.TaskStatus;

import java.util.Optional;
import java.util.Set;

@Repository
@EnableMongoRepositories(basePackages = "oui.sncf.todo.adapters.mongodb")
public class MongoDbTaskRepository implements TaskRepository {

    public static final String COLLECTION_NAME = "task";
    public static final String NAME_CRITERIA = "name";

    private MongoTemplate mongoTemplate;

    public MongoDbTaskRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void save(Task task) {
        TaskDto taskDto = new TaskDto(task.getName(), task.getStatus());
        mongoTemplate.save(taskDto, COLLECTION_NAME);
    }

    @Override
    public void remove(Task task) {
        TaskDto taskDto = new TaskDto(task.getName(), task.getStatus());
        Query query = Query.query(Criteria.where(NAME_CRITERIA).is(taskDto.getName()));
        mongoTemplate.remove(query, TaskDto.class, COLLECTION_NAME);
    }

    @Override
    public Optional<Task> getByName(String taskName) {
        Query query = Query.query(Criteria.where(NAME_CRITERIA).is(taskName));
        return mongoTemplate
                .find(query, TaskDto.class)
                .stream()
                .map(taskDto ->  new Task(taskDto.getName(), taskDto.getStatus()))
                .findAny();
    }

    @Override
    public Set<Task> fetch(TaskStatus status) {
        return null;
        // TODO
        /*return status.map(taskStatus -> mongoTemplate.findAll(TaskDto.class)
                .stream()
                .filter(taskDto -> taskDto.getStatus().equals(taskStatus))
                .map(taskDto ->  new Task(taskDto.getName(), taskDto.getStatus()))
                .collect(Collectors.toCollection(LinkedHashSet::new)))
                .orElseGet(
                        () -> new LinkedHashSet<Task>(mongoTemplate.findAll(TaskDto.class))
                );

         */
    }
}
