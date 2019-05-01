package ro.codecamp.first;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ro.codecamp.tx.config.TransactionManagerConfiguration;
import ro.codecamp.tx.domain.Employee;
import ro.codecamp.tx.repository.EmployeeRepository;

@SpringBootApplication
@Import(TransactionManagerConfiguration.class)
@Slf4j
public class FirstSpringTxPitfallsApplication {

    public static void main(String[] args) {
        SpringApplication.run(FirstSpringTxPitfallsApplication.class, args);
    }

    @Bean
    public CommandLineRunner init(EmployeeService service) {
        return (args) -> {
            log.info("Batch creating employees");
            service.createAll(Arrays.asList(
                    Employee.builder().domainName("anrosca").build(),
                    Employee.builder().domainName("msmith").build()
            ));
            log.info("All employees: {}", service.findAll().collect(toList()));
        };
    }
}

@Service
@RequiredArgsConstructor
@Slf4j
class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Transactional(readOnly = true)
    public Stream<Employee> findAll() {
        log.info("Finding all employees");
        return StreamSupport.stream(employeeRepository.findAll().spliterator(), false);
    }

    @Transactional
    public void createAll(final List<Employee> employees) {
        employeeRepository.saveAll(employees);
    }
}