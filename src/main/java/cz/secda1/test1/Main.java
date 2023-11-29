package cz.secda1.test1;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        LunchDBService service = new LunchDBService();
        List<LunchItem> items = service.getAll();

        //Vypište všechny obědy včetně polévky a pití podávané dnes
        System.out.println(TestUtils.todaysMenu(items, "29.11.2023"));

        //Ze seznamu dnes nabízených jídel vypište pouze názvy jídel (name)
        System.out.println(TestUtils.todaysMenuFilterNames(items, "29.11.2023"));

        //Vypište všechny názvy (name) hlavních jídel (kind.equals(LunchItemKind.MAIN_DISH) seřazené podle abecedy
        List<String> dishes = TestUtils.getAllMainDishesSorted(items);
        System.out.println(dishes);

        //Vypište všechna hlavní jídla bez alergenů. Vypište pouze názvy jídel
        System.out.println(TestUtils.getAllMainDishesWithoutAllergens(items));

        //Vypište datumy kdy je jako nápoj podáván "Ovocný nápoj", nebo "Sirup"
        System.out.println(TestUtils.getDatesWhenDrinkIsFruitDrinkOrSyrup(items));

        //Vypište všechna přihlášená jídla vypište druh jídla (menuOption) a název jídla (name) formát: menuOption: name
        System.out.println(TestUtils.getAllOrderedDishes(items));

        //vypište cenu všech objednaných jídel. (200Kč)
        System.out.println(TestUtils.getSumOfAllOrderedMeals(items));

        //Seskupte obědy do mapy (Map<String, List<LunchItem>>) kde klíčem bude datum výdeje jídla a hodnotou List jídel nabízených v daný den. Použijte funkci groupBy
        Map<String, List<LunchItem>> map = TestUtils.groupMealsByDate(items);
        System.out.println(map);

        //Seskupte obědy do Mapy podle jejich alergenů Map<List<Allergen>, List<LunchItem>> kdy klíčem bude list obsažených alergenů a hodontou List jídel obashující dané alergeny.
        Map<List<Allergen>, List<LunchItem>> mapAllergens = TestUtils.groupMealsByAllergens(items);
        System.out.println(mapAllergens);

    }
}
