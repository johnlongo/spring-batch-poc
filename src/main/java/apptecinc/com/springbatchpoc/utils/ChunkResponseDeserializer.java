package apptecinc.com.springbatchpoc.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.batch.integration.chunk.ChunkResponse;
import org.springframework.util.SerializationUtils;

@Slf4j
public class ChunkResponseDeserializer implements Deserializer<ChunkResponse> {
    @Override
    public ChunkResponse deserialize(String s, byte[] data) {
        log.info("============ deserializing");
        if (data == null) {
            return null;
        }
        return (ChunkResponse) SerializationUtils.deserialize(data);
    }
}
