package org.example.orderitemsservice;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderItemMapper {

    public OrderItem dtoToEntity(OrderItemDto dto, Long id) {
        OrderItem orderItem = new OrderItem();
        orderItem.setId(id);
        orderItem.setOrderId(dto.getOrderId());
        orderItem.setProductId(dto.getProductId());
        orderItem.setAmount(dto.getAmount());
        return orderItem;
    }

    public OrderItemDto entityToDto(OrderItem orderItem) {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setOrderId(orderItem.getOrderId());
        orderItemDto.setProductId(orderItem.getProductId());
        orderItemDto.setAmount(orderItem.getAmount());
        return orderItemDto;
    }

    public List<OrderItemDto> mapList(List<OrderItem> orderItems) {
        List<OrderItemDto> dtos = new ArrayList<>();
        for (OrderItem orderItem : orderItems) {
            dtos.add(entityToDto(orderItem));
        }
        return dtos;
    }
}
