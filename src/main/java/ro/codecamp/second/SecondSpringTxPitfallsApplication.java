package ro.codecamp.second;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
public class SecondSpringTxPitfallsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecondSpringTxPitfallsApplication.class, args);
    }

    @Slf4j
    @RequiredArgsConstructor
    @Service
    public static class EmployeeService {

        private final EmployeeRepository employeeRepository;

        @Transactional
        @PostConstruct
        public void init() {
            log.info("Creating a new employee in PostConstruct");
            employeeRepository.save(Employee.builder()
                    .domainName("msmith")
                    .firstName("Mike")
                    .lastName("Smith")
                    .email("Mike.Smith@abc.com")
                    .build());
        }
    }
}
