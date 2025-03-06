package org.example.orderitemsservice;

import jakarta.validation.Valid;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orderItem")
public class OrderItemController {
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private OrderItemMapper orderItemMapper;

    @PostMapping
    public ResponseEntity<OrderItem> createOrderItem(@Valid @RequestBody OrderItemDto dto) {
        return new ResponseEntity<>(orderItemService.create(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<OrderItem>> getOrderItems() {
        return new ResponseEntity<>(orderItemService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<OrderItemDto>> getOrderItems(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        Page<OrderItem> oiPage = orderItemService.findAll(PageRequest.of(page,size));
        List<OrderItemDto> dto = orderItemMapper.mapList(oiPage.getContent());

        Page<OrderItemDto> response = new PageImpl<>(dto, oiPage.getPageable(), oiPage.getTotalElements());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItem> updateOrderItem(@Valid @RequestBody OrderItemDto dto, @PathVariable Long id) {
        return new ResponseEntity<>(orderItemService.update(orderItemMapper.dtoToEntity(dto, id)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OrderItem> deleteOrderItem(@PathVariable Long id) {
        orderItemService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
