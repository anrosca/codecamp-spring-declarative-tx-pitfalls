package ro.codecamp.third;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ro.codecamp.tx.config.TransactionManagerConfiguration;
import ro.codecamp.tx.domain.Employee;
import ro.codecamp.tx.repository.EmployeeRepository;

@SpringBootApplication
@Slf4j
@Import(TransactionManagerConfiguration.class)
public class ThirdSpringTxPitfallsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThirdSpringTxPitfallsApplication.class, args);
    }

    @Bean
    public CommandLineRunner init(EmployeeService service) {
        return (args) -> {
            service.createAll(Arrays.asList(Employee.builder()
                            .domainName("msmith")
                            .firstName("Mike")
                            .lastName("Smith")
                            .email("Mike.Smith@endava.com")
                            .build(),
                    Employee.builder()
                            .firstName("jdoe")
                            .lastName("John")
                            .lastName("Doe")
                            .email("John.Doe@endava.com")
                            .build()));
        };
    }
}

@Service
@Slf4j
@RequiredArgsConstructor
class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Employee create(Employee employee) {
        log.info("Creating a new employee");
        return employeeRepository.save(employee);
    }

    @Transactional
    public Collection<Employee> createAll(Collection<Employee> employees) {
        log.info("Batch creating users.");
        return employees.stream()
                .map(this::create)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}










//create tx - createAll

//create tx - create
//commit tx - create

//create tx - create
//commit tx - create

//commit tx - createAll








































//-javaagent:spring-agent-2.5.6.SEC03.jar -javaagent:aspectjweaver-1.9.3.jar