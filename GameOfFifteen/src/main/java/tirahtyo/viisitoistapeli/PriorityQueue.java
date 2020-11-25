/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tirahtyo.viisitoistapeli;

/**
 *
 * @author birgi
 */
public class PriorityQueue {
    private Node[] pqueue;
    private int next;
    
    public PriorityQueue() {
        pqueue = new Node[5];
        next = 1;
    }
    
    public void add(Node node) {
        int i = next;
        pqueue[next] = node;
        next++;
        
        while(i > 1 && pqueue[i/2].getFScore() > pqueue[i].getFScore()) {
            Node temp = pqueue[i/2];
            pqueue[i/2] = pqueue[i];
            pqueue[i] = temp;
            i = i/2;
        }
    }
    
    public Node poll() {
        Node node = pqueue[1];
        next--;
        int i = 1;
        pqueue[1] = pqueue[next];
        pqueue[next] = null;
        Node temp = pqueue[i];
        while(pqueue[2*i] != null && pqueue[2*i + 1] != null && 
                (pqueue[2*i].getFScore() < pqueue[i].getFScore() ||
                pqueue[2*i + 1].getFScore() < pqueue[i].getFScore())) {
            if (pqueue[2*i].getFScore() < pqueue[2*i + 1].getFScore()) {
                pqueue[i] = pqueue[2*i];
                pqueue[2*i] = temp;
                i = 2*i;
            }
            else {
                pqueue[i] = pqueue[2*i + 1];
                pqueue[2*i + 1] = temp;
                i = 2*i + 1;
            }
        }
        if (pqueue[2*i] != null && pqueue[2*i].getFScore() < pqueue[i].getFScore()) {
            pqueue[i] = pqueue[2*i];
            pqueue[2*i] = temp;
        }
        return node;
    }
    
    public boolean isEmpty() {
        return pqueue[1] == null;
    }
}
