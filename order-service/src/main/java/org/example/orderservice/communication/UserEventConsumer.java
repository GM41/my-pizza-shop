package org.example.orderservice.communication;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.example.orderservice.Order;
import org.example.orderservice.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserEventConsumer {
    @Autowired
    private OrderService orderService;

    @KafkaListener(topics = "user-delete-event", groupId = "my-consumer-group")
    public void consumeUserDeleteEvent(ConsumerRecord<String, String> record){
        Long userId = Long.parseLong(record.value());
        List<Order> orders = new ArrayList<>();
        orders.addAll(orderService.findAll());
        orders.forEach(order -> {
            if(order.getUserId().equals(userId)){
                orderService.deleteOrder(order.getId());
            }
        });
    }
}
