package com.wy.younger.数据结构;

import android.support.annotation.NonNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

/**
 * @package:com.wy.younger.数据结构
 * @data on:2019/1/14 9:44
 * author:YOUNG
 * desc:TODO
 */
public class QueueDemo implements Queue<String> {

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @NonNull
    @Override
    public Iterator<String> iterator() {
        return null;
    }

    @NonNull
    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @NonNull
    @Override
    public <T> T[] toArray(@NonNull T[] a) {
        return null;
    }

    @Override
    public boolean add(String s) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(@NonNull Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(@NonNull Collection<? extends String> c) {
        return false;
    }

    @Override
    public boolean removeAll(@NonNull Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(@NonNull Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean offer(String s) {
        return false;
    }

    @Override
    public String remove() {
        return null;
    }

    @Override
    public String poll() {
        return null;
    }

    @Override
    public String element() {
        return null;
    }

    @Override
    public String peek() {
        return null;
    }
}
