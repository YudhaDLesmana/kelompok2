package com.enigma.kelompok.utils.specification;

import com.enigma.kelompok.model.Stock;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class StockSpecification {
    public static Specification<Stock> hasName(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }

    public static Specification hasPrice(Integer price) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("price"), price);
    }

    public static Specification<Stock> getStockSpecification(String name, Integer price) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (name != null && !name.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
            }

            if (price != null && price > 0) {
                Predicate pricePredicate;
                if (price <= 1000) {
                    pricePredicate = criteriaBuilder.lessThanOrEqualTo(
                            root.get("price"), 1000);
                } else if (price <= 3000) {
                    pricePredicate = criteriaBuilder.lessThanOrEqualTo(
                            root.get("price"), 3000);
                } else if (price <= 5000) {
                    pricePredicate = criteriaBuilder.lessThanOrEqualTo(
                            root.get("price"), 5000);
                } else if (price <= 10000) {
                    pricePredicate = criteriaBuilder.lessThanOrEqualTo(
                            root.get("price"), 10000);
                } else {
                    pricePredicate = criteriaBuilder.lessThanOrEqualTo(
                            root.get("price"), price);
                }
                predicates.add(pricePredicate);
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
