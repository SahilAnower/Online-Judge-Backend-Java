package com.sahil.online.judge.jpa.specification;

import jakarta.persistence.criteria.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collection;
import java.util.List;

@Slf4j
public class GenericSpecification<T> implements Specification<T> {

    private SearchCriteria searchCriteria;

    public GenericSpecification (final SearchCriteria searchCriteria) {
        super();
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Object> arguments = searchCriteria.getArguments();
        Object arg = new Object();
        if(arguments.size() != 0)
            arg = arguments.get(0);
        int size = arguments.size();
        criteriaQuery.distinct(true);
        switch (searchCriteria.getSearchOperation()) {
            case EQUALITY:
                return criteriaBuilder.equal(root.get(searchCriteria.getKey()), arg);
            case NEGATION:
                return criteriaBuilder.notEqual(root.get(searchCriteria.getKey()), arg);
            case GREATER_THAN:
                return criteriaBuilder.greaterThan(root.get(searchCriteria.getKey()), (Comparable) arg);
            case LESS_THAN:
                return criteriaBuilder.lessThan(root.get(searchCriteria.getKey()), (Comparable) arg);
            case GREATER_THAN_OR_EQUAL:
                return criteriaBuilder.greaterThanOrEqualTo(root.get(searchCriteria.getKey()), (Comparable) arg);
            case LESS_THAN_OR_EQUAL:
                return criteriaBuilder.lessThanOrEqualTo(root.get(searchCriteria.getKey()), (Comparable) arg);
            case LIKE:
                return criteriaBuilder.like(root.get(searchCriteria.getKey()), arg.toString());
            case STARTS_WITH:
                return criteriaBuilder.like(root.get(searchCriteria.getKey()), arg + "%");
            case ENDS_WITH:
                return criteriaBuilder.like(root.get(searchCriteria.getKey()), "%" + arg);
            case CONTAINS:
                return criteriaBuilder.like(root.get(searchCriteria.getKey()), "%" + arg + "%");
            case BETWEEN:
                return criteriaBuilder.between(root.get(searchCriteria.getKey()), (Comparable) arg, (Comparable) arguments.get(1));
            case IN:
                return root.get(searchCriteria.getKey()).in(arguments);
            case JOIN:
                Join<?, ?> joinExpression = getJoinExpression(searchCriteria, root, arguments, size-2);
                return criteriaBuilder.equal(joinExpression.get(arguments.get(size - 2).toString()), arguments.get(size - 1));
            case JOIN_BETWEEN:
                joinExpression = getJoinExpression(searchCriteria, root, arguments, size-3);
                return criteriaBuilder.between(joinExpression.get(arguments.get(size - 3).toString()), (Comparable) arguments.get(size - 2), (Comparable) arguments.get(size - 1));
            case JOIN_GREATER_THAN_OR_EQUAL:
                joinExpression = getJoinExpression(searchCriteria, root, arguments, size-2);
                return criteriaBuilder.greaterThanOrEqualTo(joinExpression.get(arguments.get(size - 2).toString()), (Comparable) arguments.get(size - 1));
            case JOIN_GREATER_THAN:
                joinExpression = getJoinExpression(searchCriteria, root, arguments, size-2);
                return criteriaBuilder.greaterThan(joinExpression.get(arguments.get(size - 2).toString()), (Comparable) arguments.get(size - 1));
            case JOIN_LESS_THAN_OR_EQUAL:
                joinExpression = getJoinExpression(searchCriteria, root, arguments, size-2);
                return criteriaBuilder.lessThanOrEqualTo(joinExpression.get(arguments.get(size - 2).toString()), (Comparable) arguments.get(size - 1));
            case JOIN_LESS_THAN:
                joinExpression = getJoinExpression(searchCriteria, root, arguments, size-2);
                return criteriaBuilder.lessThan(joinExpression.get(arguments.get(size - 2).toString()), (Comparable) arguments.get(size - 1));
            case JOIN_IN:
                joinExpression = getJoinExpression(searchCriteria, root, arguments, size-2);
                if (size == 1) {
                    return joinExpression.in((Collection<?>) arg);
                }
                return joinExpression.get(arguments.get(size - 2).toString()).in((Collection<?>) arguments.get(size - 1));
            case JOIN_LIKE:
                return criteriaBuilder.like(root.join(searchCriteria.getKey()).get(arg.toString()), arguments.get(1).toString());
            case JOIN_CONTAINS:
                return criteriaBuilder.like(root.join(searchCriteria.getKey()).get(arg.toString()), "%" + arguments.get(1) + "%");
            case JOIN_STARTS_WITH:
                return criteriaBuilder.like(root.join(searchCriteria.getKey()).get(arg.toString()), arguments.get(1) + "%");
            case JOIN_ENDS_WITH:
                return criteriaBuilder.like(root.join(searchCriteria.getKey()).get(arg.toString()), "%" + arguments.get(1));
            case FIND_IN_SET:
                return criteriaBuilder.greaterThan(criteriaBuilder.function("FIND_IN_SET", Integer.class,
                        criteriaBuilder.literal(arg), root.get(searchCriteria.getKey())), 0);
            case JOIN_FIND_IN_SET:
                return criteriaBuilder.greaterThan(criteriaBuilder.function("FIND_IN_SET", Integer.class,
                        criteriaBuilder.literal(arguments.get(1).toString()), root.join(searchCriteria.getKey()).get(arg.toString())), 0);
            case ORDER_BY_DESC:
                criteriaQuery.orderBy(criteriaBuilder.desc(root.get(searchCriteria.getKey())));
                return criteriaBuilder.equal(root.get(arg.toString()), arguments.get(1));
            case IS_NULL:
                return criteriaBuilder.isNull(root.get(searchCriteria.getKey()));
            default:
                throw new IllegalStateException("Unexpected value: " + searchCriteria.getSearchOperation());
        }
    }

    private Join<?, ?> getJoinExpression(SearchCriteria searchCriteria, Root<T> root, List<Object> arguments, int size) {
        Join<?, ?> joinExpression = root.join(searchCriteria.getKey());
        for (int index = 0; index < size; index++) {
            joinExpression = joinExpression.join(arguments.get(index).toString());
        }
        return joinExpression;
    }
}

