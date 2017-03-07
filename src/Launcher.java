/**
 * Лаунчер для запуска игры;
 */
public class Launcher {
    public static void main(String[] args) {
        Game game = new CommandLineGame();
        game.start();
    }
}