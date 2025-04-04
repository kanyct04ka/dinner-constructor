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
        // добавить в меню тип блюда с пустым списком, если такого типа нет
        menu.putIfAbsent(type, new ArrayList<>());
        // получить список блюд по типу
        dishesByType = menu.get(type);
        // добавить блюдо в список, если такого в нем нет
        if (!dishesByType.contains(name)) {
            dishesByType.add(name);
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
            boolean isComboUnique = false;
//            создать уникальное комбо, которое еще не повторялось
            outer:
            do {
                combo = new ArrayList<>();
                for (String type : types) {
                    dishesByType = menu.get(type);
                    int randomIndex = randomizer.nextInt(dishesByType.size());
                    combo.add(dishesByType.get(randomIndex));
                }

                for (int j = 0; j < combos.size(); j++) {
                    if (combo.equals(combos.get(j))) {
                        break outer;
                    }
                }
                isComboUnique = true;
            } while (!isComboUnique);

            combos.add(combo);
        }

        for (int i = 0; i < combos.size(); i++) {
            System.out.println("Комбо " + (i+1));
            System.out.println(combos.get(i));
        }
    }

    boolean isDishTypeExists(String type) {
        return menu.containsKey(type);
    }

    boolean isMenuEmpty() {
        return menu.isEmpty();
    }
}
