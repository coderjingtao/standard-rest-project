package com.joseph.standardwebproject.dto;

import java.util.function.Function;

public abstract class DTOConverter<S,T> implements Function<S,T> {
    protected abstract T doForward(S s);
    protected abstract S doBackward(T t);

    @Override
    public T apply(S s) {
        return doForward(s);
    }
}
