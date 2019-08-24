package com.me.skiplist;

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

}
