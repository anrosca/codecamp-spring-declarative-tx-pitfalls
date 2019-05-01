package ro.codecamp.tx.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import ro.codecamp.tx.domain.Employee;

public interface SpringDataEmployeeRepository extends CrudRepository<Employee, Long> {

    Optional<Employee> findByDomainName(String domainName);
}
