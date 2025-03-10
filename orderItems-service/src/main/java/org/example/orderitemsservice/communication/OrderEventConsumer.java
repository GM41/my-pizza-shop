package org.example.orderitemsservice.communication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.example.orderitemsservice.OrderItem;
import org.example.orderitemsservice.OrderItemDto;
import org.example.orderitemsservice.OrderItemMapper;
import org.example.orderitemsservice.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderEventConsumer {
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private OrderItemMapper orderItemMapper;

    @KafkaListener(topics = "order-delete-event", groupId = "my-consumer-group")
    public void consumeOrderDeleteEvent(ConsumerRecord<String, String> record) {
        Long orderId = Long.parseLong(record.value());
        List<OrderItem> list = new ArrayList<>();
        list.addAll(orderItemService.findAll());
        list.forEach(orderItem -> {
            if(orderItem.getOrderId().equals(orderId)) {
                orderItemService.delete(orderItem.getId());
            }
        });
    }

    @KafkaListener(topics = "order-create-event", groupId = "my-consumer-group")
    public void consumeOrderCreateEvent(ConsumerRecord<String, String> record) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(record.value());
        OrderItemDto dto = objectMapper.readValue(record.value(), OrderItemDto.class);
        orderItemService.create(dto);
    }
}
