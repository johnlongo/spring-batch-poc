package apptecinc.com.springbatchpoc.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.batch.integration.chunk.ChunkRequest;
import org.springframework.util.SerializationUtils;

import apptecinc.com.springbatchpoc.dto.SalesInfoDTO;

@Slf4j
public class ChunkRequestDeserializer implements Deserializer<ChunkRequest<SalesInfoDTO>> {
    @Override
    public ChunkRequest<SalesInfoDTO> deserialize(String topic, byte[] data) {
        log.info("============ deserializing");
        if (data == null) {
            return null;
        }
        return (ChunkRequest<SalesInfoDTO>) SerializationUtils.deserialize(data);
    }
}
