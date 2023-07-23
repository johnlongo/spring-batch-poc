package apptecinc.com.springbatchpoc.job;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.integration.chunk.RemoteChunkingManagerStepBuilderFactory;
import org.springframework.batch.integration.config.annotation.EnableBatchIntegration;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.expression.common.LiteralExpression;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.handler.LoggingHandler;
import org.springframework.integration.kafka.dsl.Kafka;
import org.springframework.integration.kafka.outbound.KafkaProducerMessageHandler;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;

import apptecinc.com.springbatchpoc.dto.SalesInfoDTO;
import lombok.RequiredArgsConstructor;

@Profile("manager")
@Configuration
@EnableBatchProcessing
@EnableBatchIntegration
@RequiredArgsConstructor
public class SalesInfoJobManager {

    private static final String OUTBOUND_KAFKA_TOPIC = "sales-chunkRequests";

    private static final String INBOUND_KAFKA_TOPIC = "sales-chunkReplies";

    private static final String INPUT_FILE = "/data/sales-info-small.csv";

    private final RemoteChunkingManagerStepBuilderFactory remoteChunkingManagerStepBuilderFactory;

    private final KafkaTemplate<String, SalesInfoDTO> salesInfoKafkaTemplate;

    @Bean
    public Job salesManagerJob(JobRepository jobRepository, Step salesInfoStepManager) {
        return new JobBuilder("Sales-Info-Manager-Job")
                .repository(jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(salesInfoStepManager)
                .build();
    }

    @Bean
    public TaskletStep salesInfoStepManager() {
        return this.remoteChunkingManagerStepBuilderFactory
                .get("ReaderManagerStep")
                .<SalesInfoDTO, SalesInfoDTO>chunk(1)
                .reader(salesInfoReader())
                .outputChannel(outboundChannel())
                .inputChannel(inBoundChannel())
                .allowStartIfComplete(Boolean.TRUE)
                .build();
    }

    public FlatFileItemReader<SalesInfoDTO> salesInfoReader() {
        return new FlatFileItemReaderBuilder<SalesInfoDTO>()
                .resource(new ClassPathResource(INPUT_FILE))
                .name("salesInfoReader")
                .delimited()
                .delimiter(",")
                .names("ID", "REGION", "COUNTRY", "ITEM_TYPE", "SALES_CHANNEL", "ORDER_PRIORITY", "ORDER_DATE",
                        "ORDER_ID", "SHIP_DATE", "UNIT_SOLD", "UNIT_PRICE", "UNIT_COST", "TOTAL_REVENUE", "TOTAL_COST",
                        "TOTAL_PROFIT")
                .linesToSkip(1)
                .targetType(SalesInfoDTO.class)
                .build();
    }

    @Bean
    public DirectChannel outboundChannel() {
        return new DirectChannel();
    }

    @Bean
    public IntegrationFlow outboudFlow() {
        var producerMessageHandler = new KafkaProducerMessageHandler<String, SalesInfoDTO>(salesInfoKafkaTemplate);
        producerMessageHandler.setTopicExpression(new LiteralExpression(OUTBOUND_KAFKA_TOPIC));
        return IntegrationFlows.from(outboundChannel())
                .log(LoggingHandler.Level.WARN)
                .handle(producerMessageHandler)
                .get();
    }

    @Bean
    public QueueChannel inBoundChannel() {
        return new QueueChannel();
    }

    @Bean
    public IntegrationFlow inBoundFlow(ConsumerFactory<String, SalesInfoDTO> consumerFactory) {
        return IntegrationFlows
                .from(Kafka.messageDrivenChannelAdapter(consumerFactory, INBOUND_KAFKA_TOPIC))
                .log(LoggingHandler.Level.WARN)
                .channel(inBoundChannel())
                .get();
    }
}
