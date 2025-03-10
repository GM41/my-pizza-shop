package org.example.orderitemsservice;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductEventConsumer {
    @Autowired
    private OrderItemService orderItemService;

    @KafkaListener(topics = "product-events", groupId = "my-consumer-group")
    public void consumeProductDeletedEvent(ConsumerRecord<String, String> record) {
        System.out.println("Получено событие: " + record.value());
        long product_id = Long.parseLong(record.value());
        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.addAll(orderItemService.findAll());
        orderItems.forEach(orderItem -> {
            if(orderItem.getProductId() == product_id){
                orderItemService.delete(orderItem.getId());
            }
        });
    }
}
