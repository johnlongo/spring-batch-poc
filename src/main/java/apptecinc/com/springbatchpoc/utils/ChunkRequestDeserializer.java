package apptecinc.com.springbatchpoc.utils;

import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.batch.integration.chunk.ChunkRequest;
import org.springframework.util.SerializationUtils;
import apptecinc.com.springbatchpoc.dto.SalesInfoDTO;

public class ChunkRequestDeserializer implements Deserializer<ChunkRequest<SalesInfoDTO>> {
    @Override
    public ChunkRequest<SalesInfoDTO> deserialize(String s, byte[] data) {
        if (data == null) {
            return null;
        }
        return (ChunkRequest<SalesInfoDTO>) SerializationUtils.deserialize(data);
    }
}
