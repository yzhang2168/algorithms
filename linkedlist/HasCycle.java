package algorithms.linked;

import data_structures.ListNodeGenerics;

public class HasCycle<E> {

    public boolean hasCycle(ListNodeGenerics<E> head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNodeGenerics<E> fast = head;
        ListNodeGenerics<E> slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }
}
