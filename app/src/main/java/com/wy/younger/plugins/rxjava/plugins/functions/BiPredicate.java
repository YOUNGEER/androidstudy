package com.wy.younger.plugins.rxjava.plugins.functions;

public interface BiPredicate<T1, T2> {

    Boolean test(T1 t1, T2 t2) throws Exception;
}
