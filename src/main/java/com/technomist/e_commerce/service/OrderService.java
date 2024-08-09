package com.technomist.e_commerce.service;

import com.technomist.e_commerce.model.AuthUser;
import com.technomist.e_commerce.model.WebOrder;
import com.technomist.e_commerce.model.repository.WebOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final WebOrderRepository webOrderRepository;

    public List<WebOrder> getOrders(AuthUser user) {
        return webOrderRepository.findByUser(user);
    }



}
