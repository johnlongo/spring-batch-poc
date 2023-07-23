package apptecinc.com.springbatchpoc.job;

import org.apache.kafka.common.requests.ProduceResponse.RecordError;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.integration.config.EnableIntegration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.step.item.ChunkProcessor;
import org.springframework.batch.core.step.item.SimpleChunkProcessor;
import org.springframework.batch.integration.chunk.ChunkProcessorChunkHandler;
import org.springframework.batch.integration.chunk.RemoteChunkingWorkerBuilder;
import org.springframework.batch.integration.config.annotation.EnableBatchIntegration;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import apptecinc.com.springbatchpoc.dto.SalesInfoDTO;

@Profile("worker")
@Configuration
@EnableBatchProcessing
@EnableIntegration
@RequiredArgsConstructor
@Slf4j
public class SalesInfoJobWorker {
    /*
     * private final MessageChannels messageChannel;
     * private final RecordError recordProcessor;
     * 
     * @Bean
     * public RemoteChunkingWorkerBuilder<SalesInfoDTO, SalesInfoDTO>
     * remoteChunkingWorker() {
     * return new RemoteChunkingWorkerBuilder<>();
     * }
     * 
     * @Bean
     * public ItemWriter<SalesInfoDTO> writer() {
     * return items -> items.forEach(item -> log.info("Item writer: {}", item));
     * }
     * 
     * @Bean
     * public IntegrationFlow workerFlow() {
     * return this.remoteChunkingWorker()
     * .itemProcessor(recordProcessor)
     * .itemWriter(writer())
     * .inputChannel(requests()) // requests received from the manager
     * .outputChannel(replies()) // replies sent to the manager
     * .build();
     * }
     * 
     * @Bean
     * public DirectChannel requests() {
     * return new DirectChannel();
     * }
     * 
     * @Bean
     * public IntegrationFlow inboundFlow() {
     * return IntegrationFlows
     * .from(messageChannel.clientRequests())
     * .channel(requests())
     * .get();
     * }
     * 
     * @Bean
     * public DirectChannel replies() {
     * return new DirectChannel();
     * }
     * 
     * @Bean
     * public IntegrationFlow outboundFlow() {
     * return IntegrationFlows
     * .from(replies())
     * .channel(messageChannel.clientReplies())
     * .get();
     * }
     * 
     */
}
