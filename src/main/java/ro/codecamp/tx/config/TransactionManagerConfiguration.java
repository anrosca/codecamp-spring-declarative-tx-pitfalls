package ro.codecamp.tx.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.PlatformTransactionManager;

import ro.codecamp.tx.manager.LoggingJpaTransactionManager;

@Configuration
@EntityScan(basePackages = "ro.codecamp.tx.domain")
@ComponentScan(basePackages = "ro.codecamp.tx.repository")
public class TransactionManagerConfiguration {

    @Bean
    @Primary
    public PlatformTransactionManager transactionManager() {
        return new LoggingJpaTransactionManager();
    }
}
