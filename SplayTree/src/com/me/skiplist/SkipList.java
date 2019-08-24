package com.me.skiplist;

import java.util.Random;

/**
 * @Description:跳表
 * @Author: Light
 * @CreateDate: 2019/8/23$ 10:52$
 * @Version: 1.0
 */
public class SkipList<T extends Comparable<T>> {

    private int maxLevel;
    private SkipListNode[] root;
    private int[] powers;
    private Random rd = new Random();

    SkipList(int maxLevel){
        this.maxLevel=maxLevel;
        root = new SkipListNode[maxLevel];
        powers = new int[maxLevel];
        for (int j = 0; j < maxLevel; j++) {
            root[j] = null;
        }
        choosePowers();
    }

    private void choosePowers() {
        powers[maxLevel-1] = (2 << (maxLevel-1) -1);
        for (int i = maxLevel - 2, j = 0; i >= 0; i--, j++)
            powers[i] = powers[i+1] - (2 << j);
        }

}
