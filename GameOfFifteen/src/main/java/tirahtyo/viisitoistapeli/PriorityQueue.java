/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tirahtyo.viisitoistapeli;

/**
 * My implementation of a priorityQueue.
 * Min value always on peek of the queue, in this case min fScore
 */
public class PriorityQueue {
    private Node[] pqueue;
    private int next;
    
    /**
     * constructor, creates new priorityQueue
     */
    public PriorityQueue() {
        pqueue = new Node[5];
        next = 1;
    }
    public Node[] getArray() {
        return pqueue;
    }
    
    /**
     * add the given node to this queue and puts the queue in right order after.
     * @param node Node we want to be added in this queue
     */
    public void add(Node node) {
        int i = next;
        if (next == pqueue.length) {
            increaseSize();
        }
        pqueue[next] = node;
        next++;
        
        while (i > 1 && pqueue[i / 2].getFScore() > pqueue[i].getFScore()) {
            Node temp = pqueue[i / 2];
            pqueue[i / 2] = pqueue[i];
            pqueue[i] = temp;
            i = i / 2;
        }
    }
    
    /**
     * Retrieves and removes first node in this queue
     * @return node with min f score
     */
    public Node poll() {
        Node node = pqueue[1];
        next--;
        int i = 1;
        pqueue[1] = pqueue[next];
        pqueue[next] = null;
        Node temp = pqueue[i];
        while (hasBothKids(i)) {
            int j = smaller(i);
            pqueue[i] = pqueue[j];
            pqueue[j] = temp;
            i = j;
        }
        if (hasLeftKid(i)) {
            if (pqueue[2 * i].getFScore() < pqueue[i].getFScore()) {
                pqueue[i] = pqueue[2 * i];
                pqueue[2 * i] = temp;
            }
        }
        return node;
    }
    
    private boolean hasBothKids(int i) {
        if (2 * i + 1 < pqueue.length) {
            if (pqueue[2 * i + 1] != null) {
                return true;
            }
        }
        return false;
    }
    private boolean hasLeftKid(int i) {
        if (2 * i < pqueue.length) {
            if (pqueue[2 * i] != null) {
                return true;
            }
        } 
        return false;
    }
    private int smaller(int i) {
        if (pqueue[2 * i].getFScore() < pqueue[2 * i + 1].getFScore()) {
            return 2 * i;
        }
        else {
            return 2 * i + 1;
        }
    }
    private void increaseSize() {
        Node[] temp = new Node[2 * next];
        for (int i = 0; i < next; i++) {
            temp[i] = pqueue[i];
        }
        pqueue = temp;
    }
    
    public boolean isEmpty() {
        return pqueue[1] == null;
    }
}
