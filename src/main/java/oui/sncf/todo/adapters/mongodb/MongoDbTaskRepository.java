package oui.sncf.todo.adapters.mongodb;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;
import oui.sncf.todo.adapters.dtos.TaskDto;
import oui.sncf.todo.core.task.Task;
import oui.sncf.todo.core.port.TaskRepository;
import oui.sncf.todo.core.task.TaskDoesNotExistException;
import oui.sncf.todo.core.task.TaskStatus;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@EnableMongoRepositories(basePackages = "oui.sncf.todo.adapters.mongodb")
public class MongoDbTaskRepository implements TaskRepository {

    public static final String COLLECTION_NAME = "task";
    public static final String NAME_CRITERIA = "name";
    public static final String PREFIX_CRITERIA = "prefix";
    public static final String TASK_DOES_NOT_EXIST_EXCEPTION_MESSAGE = "The Task doesn't exist.";

    private MongoTemplate mongoTemplate;

    public MongoDbTaskRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void save(Task task) {
        TaskDto taskDto = new TaskDto(task.getPrefix(),task.getName(), task.getStatus());
        mongoTemplate.save(taskDto, COLLECTION_NAME);
    }

    @Override
    public void remove(Task task) {
        TaskDto taskDto = new TaskDto(task.getPrefix(), task.getName(), task.getStatus());
        Query query = Query.query(Criteria.where(NAME_CRITERIA).is(taskDto.getName()));
        mongoTemplate.remove(query, TaskDto.class, COLLECTION_NAME);
    }

    @Override
    public TaskDto getByName(String taskName) {
        Query query = Query.query(Criteria.where(NAME_CRITERIA).is(taskName));
        return getTaskDtoBy(query);
    }

    @Override
    public TaskDto getByPrefix(String prefix) {
        Query query = Query.query(Criteria.where(PREFIX_CRITERIA).is(prefix));
        return getTaskDtoBy(query);    }

    private TaskDto getTaskDtoBy(Query query) {
        return mongoTemplate
                .find(query, TaskDto.class)
                .stream()
                .findAny()
                .orElseThrow(
                        () -> new TaskDoesNotExistException(TASK_DOES_NOT_EXIST_EXCEPTION_MESSAGE)
                );
    }

    @Override
    public Set<TaskDto> fetch(TaskStatus status) {
        if(status == null)
            return new LinkedHashSet<>(mongoTemplate.findAll(TaskDto.class));
        return mongoTemplate.findAll(TaskDto.class)
                .stream()
                .filter(taskDto -> taskDto.getStatus().equals(status))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
