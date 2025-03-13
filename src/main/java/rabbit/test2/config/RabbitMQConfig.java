package rabbit.test2.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue emailQueue() {
        return new Queue("email-queue", false);
    }

    @Bean
    public Queue statsQueue() {
        return new Queue("stats-queue", false);
    }
}