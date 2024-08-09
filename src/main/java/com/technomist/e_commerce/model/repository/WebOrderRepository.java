package com.technomist.e_commerce.model.repository;

import com.technomist.e_commerce.model.AuthUser;
import com.technomist.e_commerce.model.WebOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WebOrderRepository extends JpaRepository<WebOrder, Long> {

    List<WebOrder> findByUser(AuthUser user);

}
