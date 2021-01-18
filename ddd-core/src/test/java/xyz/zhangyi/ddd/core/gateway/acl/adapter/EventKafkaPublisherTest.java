package xyz.zhangyi.ddd.eas.core.gateway.acl.adapter;

import com.alibaba.fastjson.JSON;
import org.apache.kafka.clients.producer.MockProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.Test;
import xyz.zhangyi.ddd.eas.core.event.ApplicationEvent;
import xyz.zhangyi.ddd.eas.core.gateway.acl.port.Destination;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class EventKafkaPublisherTest {
    private final MockProducer<String, String> mockProducer = new MockProducer<>(true, new StringSerializer(), new StringSerializer());

    private class CandidateNominated extends ApplicationEvent {
        private String notification;
        private String candidateId;
        private String nominatorId;

        private CandidateNominated(String notification, String candidateId, String nominatorId) {
            this.notification = notification;
            this.candidateId = candidateId;
            this.nominatorId = nominatorId;
        }
    }

    private class EventKafkaMockPublisher extends EventKafkaPublisher<CandidateNominated> {
        public EventKafkaMockPublisher(Destination destination) {
            super(destination);
        }

        @Override
        protected Producer<String, String> createProducer() {
            return mockProducer;
        }
    }

    @Test
    public void should_send_CandidateNominated_application_events() {
        Destination destination = new Destination("localhost", 9012, "test");
        EventKafkaMockPublisher mockPublisher = new EventKafkaMockPublisher(destination);
        CandidateNominated candidateNominated1 = new CandidateNominated("nominate ticket", "201912101000", "201001010110");
        CandidateNominated candidateNominated2 = new CandidateNominated("nominate ticket again", "201912101000", "201001010110");

        mockPublisher.publish(candidateNominated1);
        mockPublisher.publish(candidateNominated2);

        List<ProducerRecord<String, String>> actualHistory = mockProducer.history();
        List<ProducerRecord<String, String>> expectedHistory = Arrays.asList(
                new ProducerRecord<>("test", JSON.toJSONString(candidateNominated1)),
                new ProducerRecord<>("test", JSON.toJSONString(candidateNominated2))
        );
        assertThat(actualHistory).isEqualTo(expectedHistory);
    }
}