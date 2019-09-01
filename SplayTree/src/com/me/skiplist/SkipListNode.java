package com.me.skiplist;

import java.util.Objects;

/**
 * @Description: 跳表节点
 * @Author: Light
 * @CreateDate: 2019/8/23$ 10:55$
 * @Version: 1.0
 */
public class SkipListNode<T extends Comparable<T>> {
    private T value;
    private SkipListNode next=null;
    private SkipListNode downNext=null;

    public SkipListNode(T value) {
        this.value = value;
    }

    public SkipListNode(T value, SkipListNode next, SkipListNode downNext) {
        this.value = value;
        this.next = next;
        this.downNext = downNext;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public SkipListNode getNext() {
        return next;
    }

    public void setNext(SkipListNode next) {
        this.next = next;
    }

    public SkipListNode getDownNext() {
        return downNext;
    }

    public void setDownNext(SkipListNode downNext) {
        this.downNext = downNext;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SkipListNode<?> that = (SkipListNode<?>) o;
        return Objects.equals(value, that.value) &&
                Objects.equals(next, that.next) &&
                Objects.equals(downNext, that.downNext);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, next, downNext);
    }
}
