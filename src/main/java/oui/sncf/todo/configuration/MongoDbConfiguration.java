package oui.sncf.todo.configuration;

import com.mongodb.MongoClientURI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDbFactory;

@Configuration
public class MongoDbConfiguration {

    private Environment env;

    public MongoDbConfiguration(Environment env) {
        this.env = env;
    }

    @Bean
    public MongoDbFactory mongoDbFactory() {
        MongoClientURI clientURI = new MongoClientURI(env.getProperty("spring.data.mongodb.uri"));
        return new SimpleMongoClientDbFactory(String.valueOf(clientURI));
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoDbFactory());
    }
}
