package apptecinc.com.springbatchpoc.job;

public interface JobConstants {
    public static final String MANAGER_OUTBOUND_KAFKA_TOPIC = "sales-chunkRequests";
    public static final String MANAGER_INBOUND_KAFKA_TOPIC = "sales-chunkReplies";
    public static final String MANAGER_INPUT_FILE = "/data/sales-info-small.csv";
    public static final Integer MANAGER_CHUNK_SIZE = 1000;

    public static final String WORKER_INBOUND_KAFKA_TOPIC = "sales-chunkRequests";
    public static final String WORKER_OUTBOUND_KAFKA_TOPIC = "sales-chunkReplies";
}
