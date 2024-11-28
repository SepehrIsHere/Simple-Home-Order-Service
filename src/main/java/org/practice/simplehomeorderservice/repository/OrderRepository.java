package org.practice.simplehomeorderservice.repository;


import org.practice.simplehomeorderservice.entities.Customer;
import org.practice.simplehomeorderservice.entities.Order;
import org.practice.simplehomeorderservice.entities.SubTask;
import org.practice.simplehomeorderservice.enumerations.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>, JpaSpecificationExecutor<Order> {

    @Query("SELECT o FROM Order o WHERE o.customer = :customer")
    List<Order> findByCustomer(@Param("customer") Customer customer);

    @Query("SELECT o FROM Order o WHERE o.nameOfOrder = :nameOfOrder")
    Order findByNameOfOrder(@Param("nameOfOrder") String nameOfOrder);

    @Query("SELECT o FROM Order o WHERE o.subTask = :subTask")
    List<Order> findByRelatedSubTask(@Param("subTask") SubTask subTask);

    @Query("SELECT o FROM Order o WHERE o.status = :status")
    List<Order> findOrderByOrderStatus(@Param("status") OrderStatus status);
}
