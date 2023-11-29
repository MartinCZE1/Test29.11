package cz.secda1.test1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TestUtilsTest {

    private static List<LunchItem> allMeals;

    @BeforeAll
    public static void setup() {
        LunchDBService lunchDBService = new LunchDBService();
        allMeals = lunchDBService.getAll();
    }

    @Test
    public void todaysMenuTest() {
        List<LunchItem> items = TestUtils.todaysMenu(allMeals, "29.11.2023");
        assertNotNull(items);
        assertEquals(4, items.size());
        assertTrue(items.containsAll(List.of(allMeals.get(20), allMeals.get(21), allMeals.get(22), allMeals.get(23))));
    }

    @Test
    public void todaysMenuFilterNamesTest() {
        List<String> items = TestUtils.todaysMenuFilterNames(allMeals, "29.11.2023");
        assertNotNull(items);
        assertEquals(4, items.size());
        assertTrue(items.containsAll(List.of("Houbová",
                "Vepřový guláš s cibulí, těstoviny, ovoce",
                "Francouzské brambory, červená řepa, ovoce",
                "Džus, čaj, voda, ochucené mléko")));
    }


    @Test
    public void getAllMainDishesSortedTest() {
        List<String> items = TestUtils.getAllMainDishesSorted(allMeals);
        assertNotNull(items);
        assertEquals(16, items.size());
        assertArrayEquals(new String[]{"Bramborák se salámem, zeleninový salát",
                "Brokolicová poezie, brambory, rajče",
                "Bulgur s drcenými rajčaty a parmazánem",
                "Drůbeží špíz, hranolky, dresing tzaziky",
                "Francouzské brambory, červená řepa, ovoce",
                "Jogurtové knedlíky s jahodami",
                "Kari rizoto zeleninové se sýrem",
                "Koprovka s vejcem a bramborem",
                "Kotleta s jabky a křenem, brambory",
                "Maďarský perkelt, rýže",
                "Ovocné knedlíky se zakysanou smetanou , ovoce",
                "Svíčková pečeně na smetaně, houskový knedlík",
                "Tagliatelle se smetanovou omáčkou a masem",
                "Vepřová plec na žampiónech, rýže",
                "Vepřový guláš s cibulí, těstoviny, ovoce",
                "Zapečené těstoviny se sýrem"}, items.toArray());
    }

    @Test
    public void getAllMainDishesWithoutAllergensTest() {
        List<String> items = TestUtils.getAllMainDishesWithoutAllergens(allMeals);
        assertNotNull(items);
        assertEquals(1, items.size());
        assertEquals("Maďarský perkelt, rýže", items.get(0));
    }

    @Test
    public void getDatesWhenDrinkIsFruitDrinkOrSyrupTest() {
        List<String> items = TestUtils.getDatesWhenDrinkIsFruitDrinkOrSyrup(allMeals);
        assertNotNull(items);
        assertEquals(2, items.size());
        assertArrayEquals(new String[]{"30.11.2023", "01.12.2023"}, items.toArray());
    }

    @Test
    public void getAllOrderedDishesTest() {
        List<String> items = TestUtils.getAllOrderedDishes(allMeals);
        assertNotNull(items);
        assertEquals(24, items.size());
        assertArrayEquals(new String[]{"polevka: Valašská kyselica",
                "oběd1: Vepřová plec na žampiónech, rýže",
                "nápoj: voda, čaj, ochucené mléko",
                "polevka: Hovězí s jáhly",
                "oběd1: Kari rizoto zeleninové se sýrem",
                "nápoj: voda, čaj, ochucené mléko",
                "polevka: Polévka s červenou čočkou",
                "oběd1: Kotleta s jabky a křenem, brambory",
                "nápoj: Džus, voda, čaj, ochucené mléko",
                "polevka: Frankfurtská",
                "oběd1: Koprovka s vejcem a bramborem",
                "nápoj: Džus, voda, čaj, ochucené mléko",
                "polevka:  Gulášová",
                "oběd1: Maďarský perkelt, rýže",
                "nápoj: Džus, čaj, voda, ochucené mléko",
                "polevka: Houbová",
                "oběd1: Vepřový guláš s cibulí, těstoviny, ovoce",
                "nápoj: Džus, čaj, voda, ochucené mléko",
                "polevka: Zeleninová s vejci",
                "oběd1: Svíčková pečeně na smetaně, houskový knedlík",
                "nápoj: Ovocný nápoj, čaj, voda, ochucené mléko",
                "polevka: Beskydská kmínová",
                "oběd1: Drůbeží špíz, hranolky, dresing tzaziky",
                "nápoj: Sirup, čaj, voda, ochucené mléko"}, items.toArray());
    }

    @Test
    public void getSumOfAllOrderedMealsTest() {
        Double sum = TestUtils.getSumOfAllOrderedMeals(allMeals);
        assertNotNull(sum);
        assertEquals(200d, sum);
    }

    @Test
    public void getSumOfAllOrderedMealsDefaultValueTest() {
        Double sum = TestUtils.getSumOfAllOrderedMeals(List.of(allMeals.get(2), allMeals.get(6)));
        assertNotNull(sum);
        assertEquals(0d, sum);
    }

    @Test
    public void groupMealsByDateTest() {
        Map<String, List<LunchItem>> map = TestUtils.groupMealsByDate(allMeals);
        assertNotNull(map);
        assertEquals(8, map.size());
        Set<String> keys = map.keySet();
        assertArrayEquals(new String[]{"28.11.2023",
                "24.11.2023",
                "22.11.2023",
                "30.11.2023",
                "01.12.2023",
                "15.11.2023",
                "23.11.2023",
                "29.11.2023"}, keys.toArray(new String[0]));
        int i = 0;
        assertArrayEquals(new LunchItem[]{allMeals.get(i++), allMeals.get(i++), allMeals.get(i++), allMeals.get(i++)}, map.get("15.11.2023").toArray());
        assertArrayEquals(new LunchItem[]{allMeals.get(i++), allMeals.get(i++), allMeals.get(i++), allMeals.get(i++)}, map.get("22.11.2023").toArray());
        assertArrayEquals(new LunchItem[]{allMeals.get(i++), allMeals.get(i++), allMeals.get(i++), allMeals.get(i++)}, map.get("23.11.2023").toArray());
        assertArrayEquals(new LunchItem[]{allMeals.get(i++), allMeals.get(i++), allMeals.get(i++), allMeals.get(i++)}, map.get("24.11.2023").toArray());
        assertArrayEquals(new LunchItem[]{allMeals.get(i++), allMeals.get(i++), allMeals.get(i++), allMeals.get(i++)}, map.get("28.11.2023").toArray());
        assertArrayEquals(new LunchItem[]{allMeals.get(i++), allMeals.get(i++), allMeals.get(i++), allMeals.get(i++)}, map.get("29.11.2023").toArray());
        assertArrayEquals(new LunchItem[]{allMeals.get(i++), allMeals.get(i++), allMeals.get(i++), allMeals.get(i++)}, map.get("30.11.2023").toArray());
        assertArrayEquals(new LunchItem[]{allMeals.get(i++), allMeals.get(i++), allMeals.get(i++), allMeals.get(i)}, map.get("01.12.2023").toArray());
    }


    @Test
    public void groupMealsByAllergensTest() {
        Map<List<Allergen>, List<LunchItem>> map = TestUtils.groupMealsByAllergens(allMeals);
        assertNotNull(map);
        assertEquals(6, map.size());
        assertTrue(map.keySet().containsAll(List.of(List.of(),
                List.of(LunchDBService.eggs),
                List.of(LunchDBService.milk),
                List.of(LunchDBService.wheat, LunchDBService.milk),
                List.of(LunchDBService.wheat, LunchDBService.eggs),
                List.of(LunchDBService.milk, LunchDBService.eggs)
        )));

        for (LunchItem item : map.get(List.of(LunchDBService.milk, LunchDBService.eggs))) {
            System.out.println("allMeals.get(" + allMeals.indexOf(item) + ")");
        }
        assertEquals(map.get(List.of()), List.of(allMeals.get(4), allMeals.get(8), allMeals.get(17)));

        assertEquals(map.get(List.of(LunchDBService.eggs)), List.of(allMeals.get(25)));

        assertEquals(map.get(List.of(LunchDBService.milk)), List.of(allMeals.get(3), allMeals.get(5), allMeals.get(7),
                allMeals.get(9), allMeals.get(11), allMeals.get(15), allMeals.get(19), allMeals.get(22), allMeals.get(23),
                allMeals.get(26), allMeals.get(27), allMeals.get(28), allMeals.get(29), allMeals.get(31)));

        assertEquals(map.get(List.of(LunchDBService.wheat, LunchDBService.milk)), List.of(allMeals.get(0), allMeals.get(1),
                allMeals.get(2), allMeals.get(6), allMeals.get(10), allMeals.get(12), allMeals.get(14), allMeals.get(16),
                allMeals.get(20), allMeals.get(21), allMeals.get(30)));

        assertEquals(map.get(List.of(LunchDBService.wheat, LunchDBService.eggs)), List.of(allMeals.get(24)));

        assertEquals(map.get(List.of(LunchDBService.milk, LunchDBService.eggs)), List.of(allMeals.get(13), allMeals.get(18)));
    }
}