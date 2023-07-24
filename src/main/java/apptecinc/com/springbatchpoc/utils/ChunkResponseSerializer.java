package apptecinc.com.springbatchpoc.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.batch.integration.chunk.ChunkResponse;
import org.springframework.util.SerializationUtils;

@Slf4j
public class ChunkResponseSerializer implements Serializer<ChunkResponse> {
    @Override
    public byte[] serialize(String s, ChunkResponse chunkResponse) {

        if (chunkResponse == null) {
            log.warn("cannot serialize data because the data is null");
            return new byte[0];
        }
        byte[] dataBytes = null;
        try {
            dataBytes = SerializationUtils.serialize(chunkResponse);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return dataBytes;

    }

}
