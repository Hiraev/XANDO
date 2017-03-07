/**
 * Реализация интерфейса Game.
 * Данный класс представляет
 * игру крестики - нолики,
 * работающую через командную
 * строку.
 * В классе создается экземпляр логики игры - Logic,
 * в конструктор передан данный класс, чтобы была
 * возможность вызвать метод end из объекта Logic.
 * Так же в поле прописан Scanner для простоты
 * считывания команд.
 */

import java.util.InputMismatchException;
import java.util.Scanner;

public class CommandLineGame implements Game {
    private Logic logic = Logic.getInstance(this);
    private String playerOne;
    private String playerTwo;
    private boolean game = true;
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void start() {
        System.out.println("" +
                "Игра крестики нолики\n" +
                "Начинают крестики\n" +
                "Чтобы выбрать ячейку, в которую вы хотите положить\n" +
                "крестик или нолик, напиши номер ячейки\n" +
                "Нумерация выглядит следующим образом:\n");
        System.out.println("" +
                " 1 | 2 | 3 \n" +
                "-----------\n" +
                " 4 | 5 | 6 \n" +
                "-----------\n" +
                " 7 | 8 | 9 \n");

        checkName("Первый игрок, введите ваше имя:", 1);
        checkName("Второй игрок, введите ваше имя:", 2);
        boolean switchPlayer = true;
        /**
         * Получаем ходы в бесконечном цикле.
         * Полученные значения проверяются
         * в методе checkStoke
         */
        while (game) {
            if (switchPlayer) {
                System.out.println(playerOne + ", сделайте свой ход (X)");
            } else {
                System.out.println(playerTwo + ", сделайте свой ход (0)");
            }
            if (logic.next(checkStoke())) {
                switchPlayer = !switchPlayer;
                System.out.println("\n" + logic.getBoard());
            }
        }
    }

    /**
     * Вызывается из объекта Logic, когда обнаружен победитель
     * или когда поле полностью заполнилось.
     * @param winner - победиль, передается из Logic
     */
    @Override
    public void end(Logic.Player winner) {
        game = false;
        if (winner == Logic.Player.X)
            System.out.println("\nПобедил(а) : " + playerOne);
        if (winner == Logic.Player.O)
            System.out.println("\nПобедил(а) : " + playerTwo);
        if (winner == null)
            System.out.println("\nНичья!");
    }

    /**
     * Данный метод проверяет не пустую ли строку ввел
     * пользователь, а также убирает лишние пробелы в
     * начале и в конце.
     * @param text - текст, который будет выводится
     * @param player - номер игрока, чье имя хочет
     *               получить метод.
     */
    private void checkName(String text, int player) {
        System.out.println(text);
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Имя не может быть пустым");
            checkName(text, player);
        } else {
            if (player == 1) playerOne = name;
            else if (player == 2) playerTwo = name;
        }
    }

    private int checkStoke() {
        int stoke = 0;
        try {
            stoke = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Введите корректное значение ячейки: 1-9");
            checkStoke();
        }
        return stoke;
    }
}
