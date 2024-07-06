package com.enigma.kelompok.utils.specification;

import com.enigma.kelompok.model.User;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class UserSpecification {

    public static Specification<User> hasUsername(String username) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("username"), "%" + username + "%");
    }

    public static Specification hasBalance(Integer balance) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("balance"), balance);
    }

    public static Specification<User> getUserSpecification(String username, Integer balance) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (username != null && !username.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("username"), "%" + username + "%"));
            }

            if (balance != null && balance > 0) {
                Predicate balancePredicate;
                if (balance <= 500_000) {
                    balancePredicate = criteriaBuilder.lessThanOrEqualTo(
                            root.get("balance"), 500_000);
                    predicates.add(balancePredicate);
                } else if (balance <= 1_000_000) {
                    balancePredicate = criteriaBuilder.lessThanOrEqualTo(
                            root.get("balance"), 1_000_000);
                    predicates.add(balancePredicate);
                } else if (balance <= 2_000_000) {
                    balancePredicate = criteriaBuilder.lessThanOrEqualTo(
                            root.get("balance"), 2_000_000);
                    predicates.add(balancePredicate);
                } else if (balance <= 5_000_000) {
                    balancePredicate = criteriaBuilder.lessThanOrEqualTo(
                            root.get("balance"), 5_000_000);
                    predicates.add(balancePredicate);
                } else {
                    balancePredicate = criteriaBuilder.lessThanOrEqualTo(
                            root.get("balance"), balance);
                    predicates.add(balancePredicate);
                }
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
