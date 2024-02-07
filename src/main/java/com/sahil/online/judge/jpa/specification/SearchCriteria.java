package com.sahil.online.judge.jpa.specification;

import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
public class SearchCriteria {
    private String key;
    private SearchOperation searchOperation;
    private boolean isOrOperation;
    private List<Object> arguments;

    public SearchCriteria (String key, SearchOperation searchOperation, List<Object> arguments) {
        this(key, searchOperation, false, arguments);
    }

    public SearchCriteria(String key, SearchOperation searchOperation, boolean isOrOperation, List<Object> arguments) {
        super();
        this.key = key;
        this.searchOperation = searchOperation;
        this.isOrOperation = isOrOperation;
        this.arguments = arguments;
    }

    public SearchCriteria (String key, SearchOperation searchOperation, String prefix, String suffix, List<Object> arguments) {
        if (SearchOperation.EQUALITY.equals(searchOperation)) {
            boolean startsWithAsterisk = Objects.nonNull(prefix) && prefix.contains(SearchOperation.ZERO_OR_MORE_REGEX);
            boolean endsWithAsterisk = Objects.nonNull(suffix) && suffix.contains(SearchOperation.ZERO_OR_MORE_REGEX);
            if (startsWithAsterisk && endsWithAsterisk) {
                searchOperation = SearchOperation.CONTAINS;
            } else if (startsWithAsterisk) {
                searchOperation = SearchOperation.ENDS_WITH;
            } else if (endsWithAsterisk) {
                searchOperation = SearchOperation.STARTS_WITH;
            }
        }
        this.key = key;
        this.searchOperation = searchOperation;
        this.arguments = arguments;
    }
}

