package xyz.zhangyi.ddd.eas.core.gateway.acl.impl.event;

import com.alibaba.fastjson.JSON;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import xyz.zhangyi.ddd.eas.core.domain.Event;
import xyz.zhangyi.ddd.eas.core.gateway.acl.interfaces.event.Destination;
import xyz.zhangyi.ddd.eas.core.gateway.acl.interfaces.event.EventPublisher;

import java.util.Properties;

public class EventKafkaPublisher<T> implements EventPublisher {
    private Destination destination;
    private Producer<String, String> producer;

    public EventKafkaPublisher(Destination destination) {
        this.destination = destination;
    }

    @Override
    public void publish(Event event) {
        producer = createProducer();
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(destination.topic(), JSON.toJSONString(event));
        producer.send(producerRecord);
    }

    protected Producer<String, String> createProducer() {
        Properties props = buildProperties(destination);
        return new KafkaProducer<>(props);
    }

    private Properties buildProperties(Destination destination) {
        Properties props = new Properties();
        props.put("bootstrap.servers", destination.server());
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        return props;
    }
}