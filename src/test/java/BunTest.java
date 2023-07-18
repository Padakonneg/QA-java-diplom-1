import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;

@RunWith(MockitoJUnitRunner.class)
public class BunTest {

    private final String name = "Название";
    private final float price = (float) 10;

    @Mock
    private Bun bun;

    @Before
    public void setUp() {
        bun = new Bun(name, price);
    }

    @Test
    public void getName() {
        Assert.assertEquals("Некорректный возврат имени", name, bun.getName());
    }

    @Test
    public void getPrice() {
        Assert.assertEquals("Некорректно возвращённая цена", price, bun.getPrice(), 0);
    }
}
