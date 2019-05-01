package ro.codecamp.third;

import java.util.Collections;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

import ro.codecamp.tx.domain.Employee;
import ro.codecamp.tx.repository.EmployeeRepository;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    private EmployeeService service;

    @Before
    public void setUp() {
        service = new EmployeeService(employeeRepository);
    }

    @Test
    public void testCreate() {
        Employee employee = Employee.builder().domainName("msmith").build();

        service.createAll(Collections.singletonList(employee));

        verify(employeeRepository).save(employee);
    }
}
