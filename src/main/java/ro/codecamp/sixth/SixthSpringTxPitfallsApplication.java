package ro.codecamp.sixth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ro.codecamp.tx.domain.Employee;
import ro.codecamp.tx.repository.EmployeeRepository;

@SpringBootApplication
public class SixthSpringTxPitfallsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SixthSpringTxPitfallsApplication.class, args);
    }
}

interface EmployeeAggregationService {

    void aggregate();
}

@Component
@RequiredArgsConstructor
class EmployeeRestClient {

    private final ApplicationEventPublisher eventPublisher;

    public void findNewEmployees() {
        final List<Employee> employees = Arrays.asList(
                Employee.builder().domainName("msmith").build(),
                Employee.builder().domainName("dritchie").build()
        );
        eventPublisher.publishEvent(employees);
    }
}

@Component
@RequiredArgsConstructor
class EmployeeExcelReader {

    private final ApplicationEventPublisher eventPublisher;

    public void findNewEmployees() {
        final List<Employee> employees = Arrays.asList(
                Employee.builder().domainName("jdoe").build()
        );
        eventPublisher.publishEvent(employees);
    }
}

@Service
@RequiredArgsConstructor
@Slf4j
class EmployeeAggregationServiceImpl implements EmployeeAggregationService {

    private final EmployeeRepository employeeRepository;

    private final EmployeeRestClient employeeRestClient;

    private final EmployeeExcelReader employeeExcelReader;

    @Override
    public void aggregate() {
    }

    @Transactional
    @EventListener
    public void saveEmployees(final List<Employee> allEmployees) {
        employeeRepository.saveAll(allEmployees);
    }
}
