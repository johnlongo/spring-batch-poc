package apptecinc.com.springbatchpoc.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.batch.integration.chunk.ChunkRequest;
import org.springframework.util.SerializationUtils;

import apptecinc.com.springbatchpoc.dto.SalesInfoDTO;

@Slf4j
public class ChunkRequestSerializer implements Serializer<ChunkRequest<SalesInfoDTO>> {
    @Override
    public byte[] serialize(String s, ChunkRequest<SalesInfoDTO> chunkRequest) {

        if (chunkRequest == null) {
            log.warn("cannot serialize data because the data is null");
            return new byte[0];
        }
        byte[] dataBytes = null;
        try {
            dataBytes = SerializationUtils.serialize(chunkRequest);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return dataBytes;

    }

}
