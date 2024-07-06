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

            if (username != null && !username.equals("")) {
                predicates.add(criteriaBuilder.like(root.get("username"), "%" + username + "%"));
            }

            if (balance != null) {
                predicates.add(criteriaBuilder.equal(root.get("balance"), balance));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
