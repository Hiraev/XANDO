public class Cell {
    private int row;
    private int column;

    public Cell(int cell) {
        row = (cell - 1) / 3;
        column = (cell - 1) % 3;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}

