package com.me;

/**
 * @Description:
 * @Author: Light
 * @CreateDate: 2019/9/30$ 10:37$
 * @Version: 1.0
 */
public class Solution02 {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        int carry = 0; //进位
        while (l1!=null || l2!=null){
            int x = l1==null?0:l1.val;
            int y = l2==null?0:l2.val;
            int sum = x+y+carry;
            int mod = sum/10;
            carry=mod;
            sum = sum%10;
            cur.next = new ListNode(sum);
            cur=cur.next;
            if(l1!=null){
                l1=l1.next;
            }
            if(l2!=null){
                l2=l2.next;
            }
        }
        if(carry==1){
            cur.next = new ListNode(1);
        }
        return pre.next;
    }

    public static  class ListNode {
     int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }

    public static void main(String[] args) {
        int[] arr1 = {2,4,3};
        int[] arr2 = {5,6,4};
        ListNode  node1 = new ListNode(arr1[0]);
        ListNode  nodetemp = node1;
        ListNode  node2 = new ListNode(arr2[0]);
        ListNode  nodetemp2 = node2;
        for (int i = 1; i <arr1.length ; i++) {
            nodetemp.next = new ListNode(arr1[i]);
            nodetemp = nodetemp.next;
        }

        for (int i = 1; i <arr2.length ; i++) {
            nodetemp2.next = new ListNode(arr2[i]);
            nodetemp2 = nodetemp2.next;
        }
        ListNode listNode = addTwoNumbers(node1, node2);
        while (listNode!=null){
            System.out.print(listNode.val);
            listNode=listNode.next;
        }
    }
}
