package com.sahil.online.judge.jpa.specification;

import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GenericSpecificationBuilder<T> {

    private final List<SearchCriteria> params;
    private final List<Specification<T>> specifications;

    public GenericSpecificationBuilder() {
        this.params = new ArrayList<>();
        this.specifications = new ArrayList<>();
    }

    public final GenericSpecificationBuilder<T> with(final String key, final SearchOperation searchOperation, final List<Object> arguments) {
        return with(key, searchOperation, false, arguments);
    }

    public final GenericSpecificationBuilder<T> with(final String key, final SearchOperation searchOperation, final boolean isOrOperation, final List<Object> arguments) {
        params.add(new SearchCriteria(key, searchOperation, isOrOperation, arguments));
        return this;
    }

    public final GenericSpecificationBuilder<T> with(final String key, final String operation, final List<Object> arguments) {
        return with(key, SearchOperation.getOperation(operation.charAt(0)), null, null, arguments);
    }

    public final GenericSpecificationBuilder<T> with(final String key, final SearchOperation searchOperation, final String prefix, final String suffix, final List<Object> arguments) {
        params.add(new SearchCriteria(key, searchOperation, prefix, suffix, arguments));
        return this;
    }

    public final GenericSpecificationBuilder<T> with(Specification<T> specification) {
        specifications.add(specification);
        return this;
    }

    public final GenericSpecificationBuilder<T> reset() {
        params.clear();
        specifications.clear();
        return this;
    }

    public Specification<T> build() {
        Specification<T> result = Specification.where(null);
        if (!params.isEmpty()) {
            result = new GenericSpecification<>(params.get(0));
            for (int index = 1; index < params.size(); ++index) {
                SearchCriteria searchCriteria = params.get(index);
                result = searchCriteria.isOrOperation() ? Specification.where(result).or(new GenericSpecification<>(searchCriteria))
                        : Specification.where(result).and(new GenericSpecification<>(searchCriteria));
            }
        }
        if (!specifications.isEmpty()) {
            int index = 0;
            if (Objects.isNull(result)) {
                result = specifications.get(index++);
            }
            for (; index < specifications.size(); ++index) {
                result = Specification.where(result).and(specifications.get(index));
            }
        }
        return result;
    }
}

