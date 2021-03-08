package leetcode.删除链表重复元素;

/**
 * Created by shejiewei on 2021/2/26.
 */
public class Test {

    public ListNode cut(ListNode head)
    {
        if(head==null)
        {
            return null;
        }
        ListNode cur=head;
        while(cur!=null&&cur.next!=null){
            if(cur.val==cur.next.val)
            {
                cur.next=cur.next.next;
            }
            else{
                cur=cur.next;
            }
        }
        return head;
    }
}
