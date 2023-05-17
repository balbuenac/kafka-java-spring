package utec.kafka.retrytimeout;

import utec.kafka.retrytimeout.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class KafKaConsumer {

    private static AtomicInteger counter = new AtomicInteger(0);

    private static final Logger LOGGER = LoggerFactory.getLogger(KafKaConsumer.class);

    @KafkaListener(topics = AppConstants.TOPIC_NAME,
                    groupId = AppConstants.GROUP_ID)
    public void consume(String message) throws Exception {

        // DO NOT DELETE
        if (counter.get() < 2) {
            counter.incrementAndGet();
            throw new Exception("You should retry !");
        }

        LOGGER.info(String.format("Message received -> %s", message));
    }
}