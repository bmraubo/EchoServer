import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestDummy {

    @Test
    void testDummy() {
        Assertions.assertEquals(true,true);
    }

    @Test
    void testDummy2() {
        // test should fail
        Assertions.assertEquals(true, false);
    }
}
