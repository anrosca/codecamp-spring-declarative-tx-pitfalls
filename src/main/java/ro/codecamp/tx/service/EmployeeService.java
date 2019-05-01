package ro.codecamp.tx.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ro.codecamp.tx.domain.Employee;
import ro.codecamp.tx.repository.EmployeeRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Transactional
    public void createAll(Iterable<Employee> employees) {
        for (Employee e : employees) {
            employeeRepository.save(e);
            log.info("Saved the employee: {}", e);
        }
    }

    public Iterable<Employee> findAll() {
        return employeeRepository.findAll();
    }
}

