package oui.sncf.todo.integration;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import oui.sncf.todo.adapters.driven.MysqlTaskRepository;
import oui.sncf.todo.domain.task.TaskStatus;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class MysqlTaskRepositoryTest {

    @Autowired
    private static JdbcTemplate jdbcTemplate;

    @Autowired
    private MysqlTaskRepository taskRepository;

    private static final String SQL_INSERT_TASK = "INSERT INTO taskDto (name, status) VALUES (?, ?)";

    @BeforeEach
    void init() {
        jdbcTemplate.update(SQL_INSERT_TASK, "task 1", TaskStatus.TODO.toString());
        jdbcTemplate.update(SQL_INSERT_TASK, "task 2", TaskStatus.TODO.toString());
        jdbcTemplate.update(SQL_INSERT_TASK, "task 3", TaskStatus.TODO.toString());
        jdbcTemplate.update(SQL_INSERT_TASK, "task 4", TaskStatus.DONE.toString());
        jdbcTemplate.update(SQL_INSERT_TASK, "task 5", TaskStatus.DONE.toString());
    }



    @Test
    void Should_save_a_new_task() {
        assertThat(1).isEqualTo(2);
    }

}
