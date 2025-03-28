package ru.practicum.dinner;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static DinnerConstructor dc;
    static Scanner scanner;

    public static void main(String[] args) {
        dc = new DinnerConstructor();
        scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            String command = scanner.nextLine();

            switch (command) {
                case "1":
                    addNewDish();
                    break;
                case "2":
                    generateDishCombo();
                    break;
                case "3":
                    return;
                default:
                    System.out.println("Неизвестная команда");
            }
        }
    }

    private static void printMenu() {
        System.out.println("Выберите команду:");
        System.out.println("1 - Добавить новое блюдо");
        System.out.println("2 - Сгенерировать комбинации блюд");
        System.out.println("3 - Выход");
        System.out.println("4 - test");
    }

    private static void addNewDish() {
        System.out.println("Введите тип блюда:");
        String dishType = scanner.nextLine();
        System.out.println("Введите название блюда:");
        String dishName = scanner.nextLine();

        dc.addNewDish(dishType, dishName);
    }

    private static void generateDishCombo() {
        System.out.println("Начинаем конструировать обед...");

        System.out.println("Введите количество наборов, которые нужно сгенерировать:");
        int numberOfCombos = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Вводите типы блюда, разделяя символом переноса строки (enter). Для завершения ввода введите пустую строку");
        String nextItem = scanner.nextLine();

        ArrayList<String> dishesTypesForCombo = new ArrayList<>();
        while (!nextItem.isEmpty()) {
//            по хорошему стоило бы проверить на уникальность типа в комбо
//            но в требованиях указано, что они могут повторяться
            if (dc.isDishTypeExists(nextItem)) {
                dishesTypesForCombo.add(nextItem);
            } else {
//                если пользователь ввёл несуществующий тип,
//                программа должна вывести предупреждающее сообщение и предложить ввести другой тип
                System.out.println("Данного типа блюд не заведено, введите другой тип");
            }
            nextItem = scanner.nextLine();
        }

        // сгенерируйте комбинации блюд и выведите на экран
        dc.generateDishCombos(numberOfCombos, dishesTypesForCombo);
    }
}
