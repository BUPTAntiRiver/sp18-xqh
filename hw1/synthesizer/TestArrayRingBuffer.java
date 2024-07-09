package synthesizer;
import edu.princeton.cs.algs4.In;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void testPeek() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(10);
        arb.enqueue(100);
        arb.enqueue(200);
        assertEquals(arb.peek(), (Integer)100);
        assertEquals(arb.peek(), (Integer)100);
    }

    @Test
    public void testDequeue() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(10);
        arb.enqueue(100);
        arb.enqueue(200);
        assertEquals(arb.dequeue(), (Integer)100);
        assertEquals(arb.dequeue(), (Integer)200);
    }

    @Test
    public void testIterator(){
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(10);
        for(int i = 0; i < arb.capacity; i += 1){
            arb.enqueue(i);
        }
        int k = 0;
        for(int i : arb){
            assertEquals(i, k);
            k += 1;
        }
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
