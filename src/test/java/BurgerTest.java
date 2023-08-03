import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import java.util.List;

import static org.mockito.Mockito.when;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    private final float priceBun = (float) 10;
    private final float priceIngredientSauce = (float) 15;
    private final float priceIngredientFilling = (float) 25;
    private final float summaBurger = priceBun * 2 + priceIngredientSauce + priceIngredientFilling;
    private final String nameIngredientSauce = "Брусничный соус";
    private final String nameIngredientFilling = "Котлетка из мраморной говядины";
    private final String nameBun = "Ржаная булочка";

    @Spy
    private Burger burger;

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredientSauce;

    @Mock
    private Ingredient ingredientFilling;

    @Before
    public void setUp() {
        burger = new Burger();
        when(bun.getPrice()).thenReturn(priceBun);
        when(bun.getName()).thenReturn(nameBun);
        when(ingredientSauce.getPrice()).thenReturn(priceIngredientSauce);
        when(ingredientSauce.getName()).thenReturn(nameIngredientSauce);
        when(ingredientSauce.getType()).thenReturn(SAUCE);
        when(ingredientFilling.getPrice()).thenReturn(priceIngredientFilling);
        when(ingredientFilling.getName()).thenReturn(nameIngredientFilling);
        when(ingredientFilling.getType()).thenReturn(FILLING);
    }

    @Test
    public void getPrice() {
        burger.setBuns(bun);
        burger.addIngredient(ingredientSauce);
        burger.addIngredient(ingredientFilling);
        Assert.assertEquals("Ошибка в подсчётах стоимости бургера", summaBurger, burger.getPrice(), 0);
    }

    @Test
    public void getReceipt() {
        burger.setBuns(bun);
        burger.addIngredient(ingredientFilling);
        burger.addIngredient(ingredientSauce);
        String createBurger = burger.getReceipt();
        Assert.assertTrue("Ошибка: Некорректный состав бургера", createBurger.contains(nameBun) && createBurger.contains(nameIngredientFilling) && createBurger.contains(nameIngredientSauce));
    }

    @Test
    public void addIngredient() {
        burger.addIngredient(ingredientFilling);
        Assert.assertTrue("Ошибка: В бургер не добавился ингридиент", burger.ingredients.contains(ingredientFilling));
    }

    @Test
    public void removeIngredient() {
        burger.addIngredient(ingredientFilling);
        Assert.assertTrue(burger.ingredients.size() > 0);
        burger.removeIngredient(0);
        Assert.assertEquals("Ошибка: Не был удалён ингредиент", List.of(), burger.ingredients);
    }

    @Test
    public void moveIngredient() {
        burger.addIngredient(ingredientFilling);
        burger.addIngredient(ingredientSauce);
        burger.moveIngredient(1, 0);
        Assert.assertTrue("Ошибка: ингридиент не перемещён", burger.ingredients.get(0).equals(ingredientSauce));
    }
}
