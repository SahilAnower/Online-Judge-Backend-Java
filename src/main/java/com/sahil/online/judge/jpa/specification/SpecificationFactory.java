package com.sahil.online.judge.jpa.specification;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SpecificationFactory<T> {
    public Specification<T> isEqual (String key, Object arg) {
        GenericSpecificationBuilder<T> builder = new GenericSpecificationBuilder<>();
        return builder.with(key, SearchOperation.EQUALITY, Collections.singletonList(arg))
                .build();
    }

    public Specification<T> isNull (String key) {
        GenericSpecificationBuilder<T> builder = new GenericSpecificationBuilder<>();
        return builder.with(key, SearchOperation.IS_NULL, Collections.emptyList()).build();
    }

    public Specification<T> isNotEqual (String key, Object arg) {
        GenericSpecificationBuilder<T> builder = new GenericSpecificationBuilder<>();
        return builder.with(key, SearchOperation.NEGATION, Collections.singletonList(arg))
                .build();
    }

    public Specification<T> isGreaterThan(String key, Comparable arg) {
        GenericSpecificationBuilder<T> builder = new GenericSpecificationBuilder<>();
        return builder.with(key, SearchOperation.GREATER_THAN, Collections.singletonList(arg)).build();
    }

    public Specification<T> isGreaterThanOrEqual(String key, Comparable arg) {
        GenericSpecificationBuilder<T> builder = new GenericSpecificationBuilder<>();
        return builder.with(key, SearchOperation.GREATER_THAN_OR_EQUAL, Collections.singletonList(arg)).build();
    }

    public Specification<T> isJoinGreaterThanOrEqual(String key, List<Object> arguments) {
        GenericSpecificationBuilder<T> builder = new GenericSpecificationBuilder<>();
        return builder.with(key, SearchOperation.JOIN_GREATER_THAN_OR_EQUAL, arguments).build();
    }

    public Specification<T> isLessThan(String key, Comparable arg) {
        GenericSpecificationBuilder<T> builder = new GenericSpecificationBuilder<>();
        return builder.with(key, SearchOperation.LESS_THAN, Collections.singletonList(arg)).build();
    }

    public Specification<T> isLessThanOrEqual(String key, Comparable arg) {
        GenericSpecificationBuilder<T> builder = new GenericSpecificationBuilder<>();
        return builder.with(key, SearchOperation.LESS_THAN_OR_EQUAL, Collections.singletonList(arg)).build();
    }

    public Specification<T> isJoinLessThan(String key, List<Object> arguments) {
        GenericSpecificationBuilder<T> builder = new GenericSpecificationBuilder<>();
        return builder.with(key, SearchOperation.JOIN_LESS_THAN, arguments).build();
    }

    public Specification<T> orderByDescAndIsEqual(String orderKey, String key, Object arg) {
        GenericSpecificationBuilder<T> builder = new GenericSpecificationBuilder<>();
        return builder.with(orderKey, SearchOperation.ORDER_BY_DESC, Arrays.asList(key, arg)).build();
    }

    public Specification<T> isLike(String key, String arg) {
        GenericSpecificationBuilder<T> builder = new GenericSpecificationBuilder<>();
        return builder.with(key, SearchOperation.LIKE, Collections.singletonList(arg)).build();
    }

    public Specification<T> isStartingWith(String key, String arg) {
        GenericSpecificationBuilder<T> builder = new GenericSpecificationBuilder<>();
        return builder.with(key, SearchOperation.STARTS_WITH, Collections.singletonList(arg)).build();
    }

    public Specification<T> isEndingWith(String key, String arg) {
        GenericSpecificationBuilder<T> builder = new GenericSpecificationBuilder<>();
        return builder.with(key, SearchOperation.ENDS_WITH, Collections.singletonList(arg)).build();
    }

    public Specification<T> isContaining(String key, String arg) {
        GenericSpecificationBuilder<T> builder = new GenericSpecificationBuilder<>();
        return builder.with(key, SearchOperation.CONTAINS, Collections.singletonList(arg)).build();
    }

    public Specification<T> isBetween(String key, Comparable low, Comparable high) {
        GenericSpecificationBuilder<T> builder = new GenericSpecificationBuilder<>();
        return builder.with(key, SearchOperation.BETWEEN, Arrays.asList(low, high)).build();
    }

    public Specification<T> isIn(String key, Collection<?> arguments) {
        GenericSpecificationBuilder<T> builder = new GenericSpecificationBuilder<>();
        return builder.with(key, SearchOperation.IN, new ArrayList<>(arguments)).build();
    }

    public Specification<T> isJoinEqual(String key, List<Object> arguments) {
        GenericSpecificationBuilder<T> builder = new GenericSpecificationBuilder<>();
        return builder.with(key, SearchOperation.JOIN, arguments).build();
    }

    public Specification<T> isJoinIn(String key, String joinColumn, List<Object> arguments) {
        List<Object> args = new ArrayList<>();
        args.add(joinColumn);
        args.add(arguments);
        GenericSpecificationBuilder<T> builder = new GenericSpecificationBuilder<>();
        return builder.with(key, SearchOperation.JOIN_IN, args).build();
    }

     /*
      Joins on the table specified by the key field. Typical usage would be while using
      {@link javax.persistence.ElementCollection}.
      @param key the field on which join will take place
     * @param arguments the arguments to filter upon
     * @return specification
     */

    public Specification<T> isJoinIn(String key, List<Object> arguments) {
        GenericSpecificationBuilder<T> builder = new GenericSpecificationBuilder<>();
        return builder.with(key, SearchOperation.JOIN_IN, Collections.singletonList(arguments)).build();
    }

    public Specification<T> isJoinLike(String key, List<Object> arguments) {
        GenericSpecificationBuilder<T> builder = new GenericSpecificationBuilder<>();
        return builder.with(key, SearchOperation.JOIN_LIKE, arguments).build();
    }

    public Specification<T> isJoinContaining(String key, List<Object> arguments) {
        GenericSpecificationBuilder<T> builder = new GenericSpecificationBuilder<>();
        return builder.with(key, SearchOperation.JOIN_CONTAINS, arguments).build();
    }

    public Specification<T> findInSet(String key, String arg) {
        GenericSpecificationBuilder<T> builder = new GenericSpecificationBuilder<>();
        return builder.with(key, SearchOperation.FIND_IN_SET, Collections.singletonList(arg)).build();
    }

    public Specification<T> findInSet(String key, List<Object> arguments) {
        GenericSpecificationBuilder<T> builder = new GenericSpecificationBuilder<>();
        return builder.with(key, SearchOperation.JOIN_FIND_IN_SET, arguments).build();
    }

    public Specification<T> isProjectIdIn (@NotNull final Long projectId) {
        return isJoinIn("projects", "id", Collections.singletonList(projectId));
    }

    public Specification<T> isCreatedAtBetween(Date from, Date to) {
        return isBetween("createdAt", from, to);
    }

    public Specification<T> isUpdatedAtBetween(Date from, Date to) {
        return isBetween("updatedAt", from, to);
    }

    public Specification<T> isName (String name) {
        return isContaining("name", name);
    }

    public Specification<T> isId (Long id) {
        return isEqual("id", id);
    }
}
