package ro.codecamp.tx.service;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ro.codecamp.tx.domain.ReportContent;
import ro.codecamp.tx.domain.ReportContentGenerationRequest;
import ro.codecamp.tx.repository.ReportContentRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReportContentGenerationService {

    private final ReportContentRepository reportContentRepository;

    @Transactional
    public void generate(final ReportContentGenerationRequest request) {
        final byte[] reportBlob = doGenerateReport();
        final ReportContent reportContent = request.toReportContent();
        reportContent.setContent(reportBlob);
        reportContentRepository.save(reportContent);
        log.info("Successfully generated: {}", request);
    }

    private byte[] doGenerateReport() {
        //very complex logic which generates the csv or pdf
        sleep();
        return new byte[] {1, 2, 3};
    }

    private void sleep() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
