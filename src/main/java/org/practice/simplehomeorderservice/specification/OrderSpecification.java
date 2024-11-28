package org.practice.simplehomeorderservice.specification;


import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import org.practice.simplehomeorderservice.entities.Customer;
import org.practice.simplehomeorderservice.entities.Specialist;
import org.practice.simplehomeorderservice.entities.SubTask;
import org.practice.simplehomeorderservice.entities.Task;
import org.practice.simplehomeorderservice.enumerations.OrderStatus;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class OrderSpecification implements Specification<Order> {

    private final Customer customer;
    private final Specialist specialist;
    private final SubTask subTask;
    private final Task task;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final OrderStatus orderStatus;
    private final String orderByField;
    private final Boolean ascending;
    private final Integer minOrderCount;
    private final LocalDate accountCreationDate;

    @Override
    public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        filterByCustomer(predicates, root, criteriaBuilder);
        filterBySpecialist(predicates, root, criteriaBuilder);
        filterBySubTask(predicates, root, criteriaBuilder);
        filterByTask(predicates, root, criteriaBuilder);
        filterByDateRange(predicates, root, criteriaBuilder);
        filterByStatus(predicates, root, criteriaBuilder);

        filterByOrderCountForCustomer(predicates, root, query, criteriaBuilder);
        filterByOrderCountForSpecialist(predicates, root, query, criteriaBuilder);
        filterByJoinDate(predicates, root, criteriaBuilder);
        filterByOrderStatusForCustomer(predicates, root, criteriaBuilder);
        filterByOrderStatusForSpecialist(predicates, root, criteriaBuilder);

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private void filterByCustomer(List<Predicate> predicates, Root<Order> root, CriteriaBuilder criteriaBuilder) {
        if (customer != null) {
            predicates.add(criteriaBuilder.equal(root.get("customer"), customer));
        }
    }

    private void filterBySpecialist(List<Predicate> predicates, Root<Order> root, CriteriaBuilder criteriaBuilder) {
        if (specialist != null) {
            predicates.add(criteriaBuilder.equal(root.get("specialist"), specialist));
        }
    }

    private void filterByDateRange(List<Predicate> predicates, Root<Order> root, CriteriaBuilder criteriaBuilder) {
        if (startDate != null && endDate != null) {
            predicates.add(criteriaBuilder.between(root.get("dateOfService"), startDate, endDate));
        }
    }

    private void filterByStatus(List<Predicate> predicates, Root<Order> root, CriteriaBuilder criteriaBuilder) {
        if (orderStatus != null) {
            predicates.add(criteriaBuilder.equal(root.get("orderStatus"), orderStatus));
        }
    }

    private void filterBySubTask(List<Predicate> predicates, Root<Order> root, CriteriaBuilder criteriaBuilder) {
        if (subTask != null) {
            predicates.add(criteriaBuilder.equal(root.get("subTask"), subTask));
        }
    }

    private void filterByTask(List<Predicate> predicates, Root<Order> root, CriteriaBuilder criteriaBuilder) {
        if (task != null) {
            predicates.add(criteriaBuilder.equal(root.get("subTask").get("task"), task));
        }
    }

    private void filterByOrderCountForCustomer(List<Predicate> predicates, Root<Order> root,CriteriaQuery<?> query ,CriteriaBuilder criteriaBuilder) {
        if (minOrderCount != null && minOrderCount > 0) {
            Subquery<Long> subquery = query.subquery(Long.class);
            Root<Order> orderRoot = subquery.from(Order.class);
            subquery.select(criteriaBuilder.count(orderRoot))
                    .where(criteriaBuilder.equal(orderRoot.get("customer"), root.get("customer")));

            predicates.add(criteriaBuilder.greaterThanOrEqualTo(subquery, minOrderCount.longValue()));
        }
    }

    private void filterByOrderCountForSpecialist(List<Predicate> predicates, Root<Order> root ,CriteriaQuery<?> query ,CriteriaBuilder criteriaBuilder) {
        if (minOrderCount != null && minOrderCount > 0) {
            Subquery<Long> subquery = query.subquery(Long.class);
            Root<Order> orderRoot = subquery.from(Order.class);
            subquery.select(criteriaBuilder.count(orderRoot))
                    .where(criteriaBuilder.equal(orderRoot.get("specialist"), root.get("specialist")));

            predicates.add(criteriaBuilder.greaterThanOrEqualTo(subquery, minOrderCount.longValue()));
        }
    }

    private void filterByJoinDate(List<Predicate> predicates, Root<Order> root, CriteriaBuilder criteriaBuilder) {
        if (accountCreationDate != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("customer").get("createdDate"), accountCreationDate));
        }
    }

    private void filterByOrderStatusForCustomer(List<Predicate> predicates, Root<Order> root, CriteriaBuilder criteriaBuilder) {
        if (orderStatus != null && customer != null) {
            predicates.add(criteriaBuilder.equal(root.get("orderStatus"), orderStatus));
            predicates.add(criteriaBuilder.equal(root.get("customer"), customer));
        }
    }

    private void filterByOrderStatusForSpecialist(List<Predicate> predicates, Root<Order> root, CriteriaBuilder criteriaBuilder) {
        if (orderStatus != null && specialist != null) {
            predicates.add(criteriaBuilder.equal(root.get("orderStatus"), orderStatus));
            predicates.add(criteriaBuilder.equal(root.get("specialist"), specialist));
        }
    }

    private void applySorting(CriteriaQuery<?> query, Root<Order> root, CriteriaBuilder criteriaBuilder) {
        if (orderByField != null && !orderByField.isEmpty()) {
            if (ascending) {
                query.orderBy(criteriaBuilder.asc(root.get(orderByField)));
            } else {
                query.orderBy(criteriaBuilder.desc(root.get(orderByField)));
            }
        }
    }
}
