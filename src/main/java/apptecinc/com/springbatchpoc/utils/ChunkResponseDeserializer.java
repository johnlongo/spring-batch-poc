package apptecinc.com.springbatchpoc.utils;

import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.batch.integration.chunk.ChunkResponse;
import org.springframework.util.SerializationUtils;

public class ChunkResponseDeserializer implements Deserializer<ChunkResponse> {
    @Override
    public ChunkResponse deserialize(String s, byte[] data) {
        if (data == null) {
            return null;
        }
        return (ChunkResponse) SerializationUtils.deserialize(data);
    }
}
