package volunteering.VolunteeringSite.kafka;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ExKafkaListener {

    @KafkaListener(topics = "order-events" , groupId = "my-group")
    public void listen(String message){
        System.out.println("message:" + message);
    }
}
