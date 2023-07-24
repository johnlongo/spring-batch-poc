package apptecinc.com.springbatchpoc.job;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.integration.chunk.RemoteChunkingWorkerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.expression.common.LiteralExpression;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.handler.LoggingHandler;
import org.springframework.integration.kafka.dsl.Kafka;
import org.springframework.integration.kafka.outbound.KafkaProducerMessageHandler;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import apptecinc.com.springbatchpoc.dto.SalesInfoDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Profile("worker")
@Configuration
@EnableBatchProcessing
@EnableIntegration
@RequiredArgsConstructor
@Slf4j
public class SalesInfoJobWorker {

    private final RemoteChunkingWorkerBuilder<SalesInfoDTO, SalesInfoDTO> remoteChunkingWorkerBuilder = new RemoteChunkingWorkerBuilder<SalesInfoDTO, SalesInfoDTO>();
    private final KafkaTemplate<String, SalesInfoDTO> salesInfoKafkaTemplate;

    @Bean
    public IntegrationFlow salesWorkerStep() {
        return this.remoteChunkingWorkerBuilder
                .inputChannel(inBoundChannel())
                .outputChannel(outboundChannel())
                .itemProcessor(salesInfoDTO -> {
                    log.info("Item Processing: {}", salesInfoDTO);
                    return salesInfoDTO;
                })
                .itemWriter(items -> {
                })
                .build();
    }

    @Bean
    public QueueChannel inBoundChannel() {
        return new QueueChannel();
    }

    @Bean
    public IntegrationFlow inBoundFlow(ConsumerFactory<String, SalesInfoDTO> consumerFactory) {
        return IntegrationFlows
                .from(Kafka.messageDrivenChannelAdapter(consumerFactory, JobConstants.WORKER_INBOUND_KAFKA_TOPIC))
                .log(LoggingHandler.Level.WARN)
                .channel(inBoundChannel())
                .get();
    }

    @Bean
    public DirectChannel outboundChannel() {
        return new DirectChannel();
    }

    @Bean
    public IntegrationFlow outboudFlow() {
        var producerMessageHandler = new KafkaProducerMessageHandler<String, SalesInfoDTO>(salesInfoKafkaTemplate);
        producerMessageHandler.setTopicExpression(new LiteralExpression(JobConstants.WORKER_OUTBOUND_KAFKA_TOPIC));
        return IntegrationFlows.from(outboundChannel())
                .log(LoggingHandler.Level.WARN)
                .handle(producerMessageHandler)
                .get();
    }

}
