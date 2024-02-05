package com.sahil.online.judge.jpa.specification;

public enum SearchOperation {
    EQUALITY, NEGATION, GREATER_THAN, LESS_THAN, LIKE, STARTS_WITH, ENDS_WITH, CONTAINS, BETWEEN, IN, JOIN, JOIN_IN,JOIN_BETWEEN,
    JOIN_GREATER_THAN, JOIN_GREATER_THAN_OR_EQUAL, JOIN_LESS_THAN, JOIN_LESS_THAN_OR_EQUAL,
    JOIN_LIKE, JOIN_CONTAINS, JOIN_STARTS_WITH, JOIN_ENDS_WITH, FIND_IN_SET, JOIN_FIND_IN_SET, ORDER_BY_DESC,
    GREATER_THAN_OR_EQUAL, LESS_THAN_OR_EQUAL,IS_NULL;

    public static final String ZERO_OR_MORE_REGEX = "*";

    public static SearchOperation getOperation(char op) {
        return switch (op) {
            case ':' -> EQUALITY;
            case '!' -> NEGATION;
            case '>' -> GREATER_THAN;
            case '<' -> LESS_THAN;
            case '~' -> LIKE;
            default -> throw new IllegalStateException("Unexpected value: " + op);
        };
    }
}

