import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    static CharacterComparator offBy5 = new OffByN(5);
    static CharacterComparator offBy3 = new OffByN(3);

    @Test
    public void testOffByN(){
        assertTrue(offBy5.equalChars('a', 'f'));
        assertFalse(offBy5.equalChars('a', 'b'));
        assertTrue(offBy3.equalChars('a', 'd'));
        assertFalse(offBy3.equalChars('a', 'c'));
    }

}
