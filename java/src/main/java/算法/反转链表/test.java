package 算法.反转链表;

/**
 * Created by shejiewei on 2021/1/18.
 */
public class test {

     public static void main(String[] args) {

      }

        public ListNode ReverseList(ListNode head) {

            if(head==null)
                return null;
            ListNode pre=null;
            ListNode next=null;
            while(head!=null){

                next=head.next;
                head.next=pre;
                pre=head;
                head=next;
            }
            return pre;
        }

    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
}
