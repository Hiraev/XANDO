/**
 * Класс логики игры.
 * Здесь происходят все операции с полем.
 * Добавление хода, проверки, выявление
 * победителя.
 */
public class Logic {
    private final String SPECIAL_SYMBOL = "•";
    private static Logic instance;

    enum Player {
        X, O;

        public Player opposite() {
            return this == X ? O : X;
        }
    }

    private String[][] board;
    private Player player;
    private Game game;
    private int stroke;

    /**
     * Конструтор. Здесь проиходит иициализация всех полей.
     * board заполняется точками.
     *
     * @param game - необходим для вызова метода end()
     */
    private Logic(Game game) {
        this.game = game;
        board = new String[3][3];
        player = Player.X;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = SPECIAL_SYMBOL;
            }
        }
    }

    /**
     * Данный класс может быть создан в единственном экземляре.
     * Ссылку на созданный класс можно получить через этот метод.
     *
     * @param game - нужен для передачи в конструктор
     * @return - возращает instance, при первом вызове
     * создается объект Logic, при последующих вызовах
     * возращается уже имеющийся объект.
     */
    public static Logic getInstance(Game game) {
        if (instance == null) {
            instance = new Logic(game);
        }
        return instance;
    }

    public String[][] getBoard() {
        return board;
    }

    /**
     * Метод получае номер ячейки и преобразует ее в ячейку Cell.
     * Здесь происходит увеличение поля stroke, которое хранит номер
     * хода. Если номер хода равен 9, вызывается метод end у объекта
     * Game.
     * @param cell - число, указываюшее позицию ячейки, от 1 до 9
     * @return возвращает false, когда в ячейку нельзя записать значение
     */
    public boolean next(int cell) {
        Cell cell1 = new Cell(cell);
        if (cell < 1 || cell > 9) {
            System.out.println("Такой ячейки нет!");
            return false;
        }
        if (!(board[cell1.getRow()][cell1.getColumn()].equals(SPECIAL_SYMBOL))) {
            System.out.println("Ячейка занята!");
            return false;
        }
        stroke++;
        addPlayer(cell);
        Player winner = whoWin();
        if (winner != null) game.end(winner);
        if (stroke == 9) game.end(null);
        return true;
    }

    private void addPlayer(int cell1) {
        Cell cell = new Cell(cell1);
        board[cell.getRow()][cell.getColumn()] = player.toString();
        player = player.opposite();
    }

    private Player whoWin() {
        return cellIterator();
    }

    /**
     * Определение победителя.
     * Примитивные циклы, бегающие по вертикали
     * и горизонтали. А также две проверки для
     * диагональных линий.
     * @return возвращется X или O или null
     */
    private Player cellIterator() {
        for (int i = 0; i < 3; i++) {
            String currentCell = board[i][0];
            if (currentCell.equals(SPECIAL_SYMBOL)) continue;
            int length = 0;
            for (int j = 0; j < 3; j++) {
                if (currentCell.equals(board[i][j])) {
                    length++;
                }
                if (length == 3) return Player.valueOf(currentCell);
            }

        }
        for (int i = 0; i < 3; i++) {
            String currentCell = board[0][i];
            if (currentCell.equals(SPECIAL_SYMBOL)) continue;
            int length = 0;
            for (int j = 0; j < 3; j++) {
                if (currentCell.equals(board[j][i])) {
                    length++;
                }
                if (length == 3) return Player.valueOf(currentCell);
            }
        }
        if (board[1][1].equals(SPECIAL_SYMBOL)) return null;
        if ((board[0][0].equals(board[1][1])) && (board[1][1].equals(board[2][2]))) return Player.valueOf(board[1][1]);
        if ((board[0][2].equals(board[1][1])) && (board[1][1].equals(board[2][0]))) return Player.valueOf(board[1][1]);
        return null;
    }
}
