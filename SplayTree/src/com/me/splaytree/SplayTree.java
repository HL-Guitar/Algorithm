package com.me.splaytree;

import com.me.skiplist.SkipListNode;

/**
 * @Description: 伸展树
 * @Author: Light
 * @CreateDate: 2019/8/22$ 8:49$
 * @Version: 1.0
 */
public class SplayTree <T extends Comparable<T>>{
    private SplayTreeNode<T> mRoot; //根节点
    //子节点
    public class SplayTreeNode <T extends Comparable<T>>{
        T key;  //键值
        SplayTreeNode<T> left;
        SplayTreeNode<T> right;

        public SplayTreeNode(){
            this.left=null;
            this.right=null;
        }

        public SplayTreeNode(T key) {
            this.key = key;
        }

        public SplayTreeNode(T key, SplayTreeNode<T> left, SplayTreeNode<T> right) {
            this.key = key;
            this.left = left;
            this.right = right;
        }
    }

    public SplayTree() {
        this.mRoot = null;
    }

    /*
     * 前序遍历"伸展树"
     */
    private void preOrder(SplayTreeNode<T> tree) {
        if(tree != null) {
            System.out.print(tree.key+" ");
            preOrder(tree.left);
            preOrder(tree.right);
        }
    }

    public void preOrder() {
        preOrder(mRoot);
    }

    /*
     * 中序遍历"伸展树"
     */
    private void inOrder(SplayTreeNode<T> tree) {
        if(tree != null) {
            inOrder(tree.left);
            System.out.print(tree.key+" ");
            inOrder(tree.right);
        }
    }

    public void inOrder() {
        inOrder(mRoot);
    }

    /*
     * 后序遍历"伸展树"
     */
    private void postOrder(SplayTreeNode<T> tree) {
        if(tree != null)
        {
            postOrder(tree.left);
            postOrder(tree.right);
            System.out.print(tree.key+" ");
        }
    }

    public void postOrder() {
        postOrder(mRoot);
    }

    /*
     * (递归实现)查找"伸展树x"中键值为key的节点
     */
    private SplayTreeNode<T> search(SplayTreeNode<T> x, T key) {
        if(x==null){
            return x;
        }
        int cmp = key.compareTo(x.key);
        if(cmp<0)
            return search(x.left,key);
        else if(cmp>0)
            return search(x.right,key);
        else
            return x;

    }

    public SplayTreeNode<T> search(T key) {
        return search(mRoot, key);
    }

    /*
     * (非递归实现)查找"伸展树x"中键值为key的节点
     */
    private SplayTreeNode<T> iterativeSearch(SplayTreeNode<T> x, T key) {
        while (x!=null){
            int cmp = key.compareTo(x.key);
            if (cmp<0)
                x=x.left;
            else if (cmp>0)
                x=x.right;
            else
                return x;
        }
        return x;
    }

    public SplayTreeNode<T> iterativeSearch(T key) {
        return iterativeSearch(mRoot, key);
    }

    /*
     * 查找最小结点：返回tree为根结点的伸展树的最小结点。
     */
    private SplayTreeNode<T> minimum(SplayTreeNode<T> tree) {
        if (tree == null)
            return null;

        while(tree.left != null)
            tree = tree.left;
        return tree;
    }

    public T minimum() {
        SplayTreeNode<T> p = minimum(mRoot);
        if (p != null)
            return p.key;

        return null;
    }

    /*
     * 查找最大结点：返回tree为根结点的伸展树的最大结点。
     */
    private SplayTreeNode<T> maximum(SplayTreeNode<T> tree) {
        if (tree == null)
            return null;

        while(tree.right != null)
            tree = tree.right;
        return tree;
    }

    public T maximum() {
        SplayTreeNode<T> p = maximum(mRoot);
        if (p != null)
            return p.key;

        return null;
    }


    /**
     * zag旋转
     * @param p
     */
    public void lRot(SplayTreeNode<T> p){
        //前置条件：p有右孩子，实现向左旋转
        SplayTreeNode r = p.right;
        p.right=r.left;
        r.left=p;
        p=r; //r成为根节点，左节点变为p,原r的左孩子成了p的右孩子
    }

    /**
     * zig旋转
     * @param p
     */
    public void rRot(SplayTreeNode<T> p){
        //前置条件：p有左孩子，实现向右旋转
        SplayTreeNode r = p.left;
        p.left = r.right;
        r.right = p;
        p = r; //r成为根节点，右节点变为p,原r的右孩子成了p的左孩子
    }


    public String insert(SplayTreeNode<T> p, T x) {
        SplayTreeNode r;
        String result = ResultCode.SUCCESS.getValue();;
        if(p == null){  //插入新节点
            p = new SplayTreeNode<T>();
            return result;
        }
        if(x.equals(p.key)){
            result = ResultCode.DUPLITED.getValue();
            return result;
        }
        int compareVal = x.compareTo(p.key);
        if(compareVal<0){
            r = p.left;
            if(r==null){  //left为空，找到插入点。先生成再zig旋转
                r =  new SplayTreeNode<T>(x);
                r.right = p;
                p=r;
                return result;
            }else if(compareVal==0){ //存在这个值，直接zig旋转
                rRot(p);
                result = ResultCode.DUPLITED.getValue();
                return result;
            }
            int kCompareVal = x.compareTo((T) r.key);
            if(kCompareVal<0){ //符合双重旋转的条件，zigzig旋转
                result = insert(r.left, x); //递归寻找r.left的插入点
                rRot(r);
            }else{    //符合双重旋转的条件，zigzag旋转
                result = insert(r.right, x);
                lRot(r);
                p.left = r;
            }
            rRot(p); //插入的节点旋转为根节点
        }else{
            r=p.right;
            int compareTVal = x.compareTo((T) r.key);
            if(r==null){
                r = new SplayTreeNode(x);
                r.left=p;
                p=r;
                return result;
            }else if(compareTVal==0){ //zag旋转
                lRot(p);
                result = ResultCode.DUPLITED.getValue();
                return result;
            }
            if(compareTVal>0){  //zagzag旋转
                result = insert(r.right, x);
                lRot(p);
            }else{ //zagzig旋转
                result = insert(r.left, x);
                rRot(r);
                p.right=r;
            }
            lRot(p);
        }
        return result;
    }



    /*
     * 销毁伸展树
     */
    private void destroy(SplayTreeNode<T> tree) {
        if (tree==null)
            return ;

        if (tree.left != null)
            destroy(tree.left);
        if (tree.right != null)
            destroy(tree.right);

        tree=null;
    }

    public void clear() {
        destroy(mRoot);
        mRoot = null;
    }

    /*
     * 打印"伸展树"
     *
     * key        -- 节点的键值
     * direction  --  0，表示该节点是根节点;
     *               -1，表示该节点是它的父结点的左孩子;
     *                1，表示该节点是它的父结点的右孩子。
     */
    private void print(SplayTreeNode<T> tree, T key, int direction) {

        if(tree != null) {

            if(direction==0)    // tree是根节点
                System.out.printf("%2d is root\n", tree.key);
            else                // tree是分支节点
                System.out.printf("%2d is %2d's %6s child\n", tree.key, key, direction==1?"right" : "left");

            print(tree.left, tree.key, -1);
            print(tree.right,tree.key,  1);
        }
    }

    public void print() {
        if (mRoot != null)
            print(mRoot, mRoot.key, 0);
    }
}
