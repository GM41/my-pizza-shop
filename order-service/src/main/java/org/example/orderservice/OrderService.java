package org.example.orderservice;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Page<Order> findAll(Pageable pageable) {
        return  orderRepository.findAll(pageable);
    }

    public  Order save(OrderDto dto) {
        Order order = new Order();
        order.setUserId(dto.getUserId());
        return orderRepository.save(order);
    }

    public Order updateOrder(Order order){
        if(!orderRepository.existsById(order.getId())){
            throw new EntityNotFoundException("Order Not Found");
        }
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id){
        if(!orderRepository.existsById(id)){
            throw new EntityNotFoundException("Order Not Found");
        }
        orderRepository.deleteById(id);
    }
}
