package oui.sncf.todo.configuration;

/*
@Configuration
public class MongoDbConfiguration {

    private final Environment env;

    public MongoDbConfiguration(Environment env) {
        this.env = env;
    }

    @Bean
    public MongoDbFactory mongoDbFactory() {
        MongoClientURI clientURI = new MongoClientURI(Objects.requireNonNull(env.getProperty("spring.data.mongodb.uri")));
        return new SimpleMongoClientDbFactory(String.valueOf(clientURI));
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoDbFactory());
    }
}

 */
