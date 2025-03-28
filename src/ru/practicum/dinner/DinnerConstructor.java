package ru.practicum.dinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class DinnerConstructor {
    HashMap<String, ArrayList<String>> menu;
    ArrayList<String> dishesByType;
    Random randomizer;

    DinnerConstructor() {
        menu = new HashMap<>();
        randomizer = new Random();
    }

    void addNewDish(String type, String name) {
        if (menu.containsKey(type)) {
            dishesByType = menu.get(type);
//            проверка на уникальность названия блюда в рамках типа
            if (dishesByType.contains(name)) {
                System.out.println("Данное блюдо уже существует");
            } else {
                dishesByType.add(name);
            }
        } else {
            dishesByType = new ArrayList<>();
            dishesByType.add(name);
            menu.put(type, dishesByType);
        }
    }

    void generateDishCombos(int quantity, ArrayList<String> types) {
//        проверим возможность собрать запрашиваемое количество уникальных комбо
        int uniqueCombos = 1;
        for (String type : types) {
            uniqueCombos *= menu.get(type).size();
        }
        if (uniqueCombos < quantity) {
            System.out.println("Не возможно собрать требуемое количество уникальных комбо.");
            System.out.println("С текущим набором типов блюд можно собрать наборов: " + uniqueCombos);
            return;
        }

        ArrayList<String> combo;
        ArrayList<ArrayList<String>> combos = new ArrayList<>();

        for (int i = 1; i <= quantity; i++) {
            combo = new ArrayList<>();
            for (String type : types) {
                dishesByType = menu.get(type);
                int randomIndex = randomizer.nextInt(dishesByType.size());
                combo.add(dishesByType.get(randomIndex));
            }

//            проверка уникальности комбо, чтобы не повторялись
            boolean isComboUnique = true;
            for (int j = 0; j < combos.size(); j++) {
                if (combo.equals(combos.get(j))) {
                    isComboUnique = false;
                    break;
                }
            }

            if (isComboUnique) {
                combos.add(combo);
            } else {
                // если комбо не уникально надо не потерять итерацию иначе общее количество комбо будет меньше
                i--;
            }
        }

        for (int i = 0; i < combos.size(); i++) {
            System.out.println("Комбо " + (i+1));
            System.out.println(combos.get(i));
        }
    }

    boolean isDishTypeExists(String type) {
        return menu.containsKey(type);
    }
}
