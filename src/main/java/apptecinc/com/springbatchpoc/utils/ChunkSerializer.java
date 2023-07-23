package apptecinc.com.springbatchpoc.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.util.SerializationUtils;

@Slf4j
public class ChunkSerializer implements Serializer<Object> {
    @Override
    public byte[] serialize(String topic, Object data) {

        if (data == null) {
            log.warn("cannot serialize data because the data is null");
            return new byte[0];
        }
        byte[] dataBytes = null;
        try {
            System.out.println(
                    "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX "
                            + data.toString());
            dataBytes = SerializationUtils.serialize(data);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return dataBytes;

    }

}
