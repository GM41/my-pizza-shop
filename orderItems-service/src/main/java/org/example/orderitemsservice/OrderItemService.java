package org.example.orderitemsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;

    public List<OrderItem> findAll() {
        return orderItemRepository.findAll();
    }

    public Page<OrderItem> findAll(Pageable pageable) {
        return orderItemRepository.findAll(pageable);
    }

    public OrderItem create(OrderItemDto dto) {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderId(dto.getOrderId());
        orderItem.setProductId(dto.getProductId());
        orderItem.setAmount(dto.getAmount());
        return orderItemRepository.save(orderItem);
    }

    public OrderItem update(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    public void delete(Long id) {
        orderItemRepository.deleteById(id);
    }
}
