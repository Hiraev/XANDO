/**
 * Интерфейс, который определяет
 * какие методы должны быть у
 * игры крестики - нолики.
 */

public interface Game {
    void start();

    void end(Logic.Player winner);
}

