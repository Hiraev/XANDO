public class Cell {
    private int row;
    private int column;

    public Cell(int cell) {
        switch (cell) {
            case 1:
                row = 0;
                column = 0;
                break;
            case 2:
                row = 0;
                column = 1;
                break;
            case 3:
                row = 0;
                column = 2;
                break;
            case 4:
                row = 1;
                column = 0;
                break;
            case 5:
                row = 1;
                column = 1;
                break;
            case 6:
                row = 1;
                column = 2;
                break;
            case 7:
                row = 2;
                column = 0;
                break;
            case 8:
                row = 2;
                column = 1;
                break;
            case 9:
                row = 2;
                column = 2;
                break;
        }
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}

