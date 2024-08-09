package com.technomist.e_commerce.api.controller.order;

import com.technomist.e_commerce.model.AuthUser;
import com.technomist.e_commerce.model.WebOrder;
import com.technomist.e_commerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public List<WebOrder> getOrders(@AuthenticationPrincipal AuthUser user) {
        return orderService.getOrders(user);
    }

}












