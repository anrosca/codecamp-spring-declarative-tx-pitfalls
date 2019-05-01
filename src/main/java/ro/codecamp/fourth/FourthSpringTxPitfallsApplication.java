package ro.codecamp.fourth;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ro.codecamp.tx.config.TransactionManagerConfiguration;
import ro.codecamp.tx.domain.Employee;
import ro.codecamp.tx.repository.EmployeeRepository;

@SpringBootApplication
@Slf4j
@Import(TransactionManagerConfiguration.class)

public class FourthSpringTxPitfallsApplication {

    public static void main(String[] args) {
        SpringApplication.run(FourthSpringTxPitfallsApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(FooService fooService) {
        return args -> {
            fooService.foo();
        };
    }
}

interface FooService {

    @Transactional
    void foo();
}

@Service
@RequiredArgsConstructor
@Slf4j
class FooServiceImpl implements FooService {

    private final EmployeeRepository employeeRepository;

    @Override
    public void foo() {
        log.info("Creating a new employee");
        employeeRepository.save(Employee.builder()
                .domainName("anrosca")
                .email("Andrei.Rosca@endava.com")
                .firstName("Andrei")
                .lastName("Rosca")
                .build());
    }
}























//-javaagent:spring-agent-2.5.6.SEC03.jar -javaagent:aspectjweaver-1.9.3.jar