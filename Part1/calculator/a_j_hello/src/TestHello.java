import static org.junit.Assert;
import org.junit.Test;

public class TestHello extends Hello {
        @Test
        public void HelloWorld() {
        String result = Hello.hello();
        Assert.assertEquals("Hello World!!", result);
    }
}