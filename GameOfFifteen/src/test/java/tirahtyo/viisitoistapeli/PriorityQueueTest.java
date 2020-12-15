
package tirahtyo.viisitoistapeli;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * junit tests for priorityqueue.
 * 
 */
public class PriorityQueueTest {
    
    private PriorityQueue pqueue;

    @Before
    public void setUp() {
        pqueue = new PriorityQueue();
    }
    
    @Test
    public void addingManyValuesIncreasesTheSizeOfQueue() {
        for (int i = 0; i < 100; i++) {
            Node node = new Node(null, null, i, 0, 0, 0, ' ');
            pqueue.add(node);
        }
        assertTrue(pqueue.getArray().length > 100);
    }

    @Test
    public void pollPollstheNodeWithSmallestHeuristic() {
        for (int i = 0; i < 10; i++) {
            Node node1 = new Node(null, null, 13, 0, 0, 12*i, ' ');
            Node node2 = new Node(null, null, 78, 0, 0, 3*i, ' ');
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
            node.setManhattan(node2.getManhattan());
            node.setLinearcol(node2.getLinearcol());
            node.setLinearrow(node2.getLinearrow());
        }
    }
}
