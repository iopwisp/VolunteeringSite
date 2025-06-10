package volunteering.VolunteeringSite.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ExKafkaProducer {

    private final KafkaTemplate<String , String> kafkaTemplate;

    public ExKafkaProducer(KafkaTemplate<String , String > kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic , String message){
        kafkaTemplate.send(topic , message);
    }
}
