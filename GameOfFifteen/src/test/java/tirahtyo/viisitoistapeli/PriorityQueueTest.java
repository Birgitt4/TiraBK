/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tirahtyo.viisitoistapeli;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author birgi
 */
public class PriorityQueueTest {
    
    private PriorityQueue pqueue;
    
    public PriorityQueueTest() {
    }

    
    @Before
    public void setUp() {
        pqueue = new PriorityQueue();
    }
    
    @Test
    public void addingManyValuesIncreasesTheSizeOfQueue() {
        for (int i = 0; i < 100; i++) {
            Node node = new Node(null, null, i, 0, ' ');
            pqueue.add(node);
        }
        assertTrue(pqueue.getArray().length > 100);
    }

    @Test
    public void pollPollstheNodeWithSmallestHeuristic() {
        for (int i = 0; i < 10; i++) {
            Node node1 = new Node(null, null, 13, 12*i, ' ');
            Node node2 = new Node(null, null, 78, 3*i, ' ');
            pqueue.add(node1);
            pqueue.add(node2);
        }
        boolean works = true;
        Node node = pqueue.poll();
        while (!pqueue.isEmpty()) {
            Node node2 = pqueue.poll();
            if (node2.getFScore() < node.getFScore()) {
                works = false;
            }
            node.setGScore(node2.getGScore());
            node.setHScore(node2.getHScore());
        }
    }
}
