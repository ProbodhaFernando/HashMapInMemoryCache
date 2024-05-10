package com.example.demo.cache;

import com.example.demo.constants.CurrencyCode;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class LRUMapCacheImpl extends LRUMapCache {

    @Override
    public void write(CurrencyCode key, BigDecimal value) {
        CacheNode node = new CacheNode();
        node.key = key;
        node.value = value;

        if (getCache().size() >= getMaxCapacity()) {
            //Cache has reached the maximum size. Hence, need to evict cache.
            //Remove tail element(Least Recently Used element) and add the new element.
            getCache().remove(getTail().key);
            removeNode(getTail());
            getCache().put(key, node);
            addTopNode(node);
        } else {
            getCache().put(key, node);
            addTopNode(node);
        }

    }

    /**
     * This method removes a node from the doubly linked list.
     * @param node
     */
    private void removeNode(CacheNode node) {
        CacheNode prev = node.prev;
        CacheNode next = node.next;

        //If either previous or next node of this specific node is null,
        //set the head or tail accordingly.
        if (prev == null) {
            setHead(next);
        } else {
            //else switch the next element to remove the node.
            prev.next = next;
        }

        if(next == null) {
            setTail(prev);
        } else {
            //else switch the previous node to remove the node.
            next.prev = prev;
        }
    }

    /**
     * This method adds a node to the top(Head) of doubly linked list.
     * @param node
     */
    private void addTopNode(CacheNode node) {
        CacheNode head = getHead();

        if (head == null) {
            setHead(node);
            node.prev = null;
            node.next = null;
        } else {
            head.prev = node;
            node.next = head;
            node.prev = null;
            setHead(node);
        }

        if (getTail() == null) {
            setTail(node);
        }
    }

}
