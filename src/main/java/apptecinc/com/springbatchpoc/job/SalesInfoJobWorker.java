package apptecinc.com.springbatchpoc.job;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.integration.config.EnableIntegration;

import lombok.RequiredArgsConstructor;

@Profile("worker")
@Configuration
@EnableBatchProcessing
@EnableIntegration
@RequiredArgsConstructor
public class SalesInfoJobWorker {

}
