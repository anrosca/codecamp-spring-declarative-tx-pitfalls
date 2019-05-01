package ro.codecamp.tx.repository;

import org.springframework.data.repository.CrudRepository;

import ro.codecamp.tx.domain.Report;

public interface ReportRepository extends CrudRepository<Report, Long> {

}
