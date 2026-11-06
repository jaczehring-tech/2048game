import java.util.ArrayList;

public class Game2048 {

    private int[][] grid = null;
    private int moves = 0;

    public void startGame() {
        grid = new int[4][4];
        moves = 0;
        IO.println("Welcome to 2048!\n");
        addTiles(2);
        showBoard();
        IO.println("Slide Tiles With WASD\nQuit: Q  Show Board: P");
        while (true) {
            moves++;
            if (playerMove() == 0) {
                return;
            }
            addTiles();
            showBoard();
        }
    }

    public void addTiles(int amount) {

        int rows = 4;
        int cols = 4;
        ArrayList<Integer> openSpotIndices = new ArrayList<Integer>();

        /**
         * Iterate through the grid and add the indexes from 0-16
         */
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) {
                    openSpotIndices.add(new Integer(i * 4 + j));
                }
            }
        }
        while (amount > 0 && openSpotIndices.size() > 0) {
            int randomIndex = (int) (Math.random() * openSpotIndices.size());
            int openIndex = openSpotIndices.get(randomIndex);
            int numberToAdd = 2;
            if (Math.random() > 0.8) numberToAdd = 4; //The real 2048 has a 20% chance to add a 4 tile, and 80% to add a 2 tile
            grid[openIndex / 4][openIndex % 4] = numberToAdd;
            openSpotIndices.remove(randomIndex);
            amount--;
        }
    }

    public void addTiles() {
        addTiles(1);
    }

    public void showBoard() {
        for (int[] i : grid) {
            for (int j : i) {
                IO.print(j + " ");
            }
            IO.println();
        }
        IO.println();
    }

    public int playerMove() {
        boolean properMove = false;
        while (!properMove) {
            String response = IO.readln("Your Move: ").toLowerCase();
            switch (response) {
                case "p" -> {
                    IO.println();
                    showBoard();
                }
                case "q" -> {
                    IO.println("Goodbye!");
                    return 0;
                }
                case "w" -> {
                    slideTilesVertically(-1);
                    properMove = true;
                }
                case "s" -> {
                    slideTilesVertically(1);
                    properMove = true;
                }
                case "a" -> {
                    slideTilesHorizontally(-1);
                    properMove = true;
                }
                case "d" -> {
                    slideTilesHorizontally(1);
                    properMove = true;
                }
                default -> IO.println("Invalid Input");
            }
        }
        return 1;
    }

    public void slideTilesHorizontally(int dir) {
        int start;
        int end;
        if (dir == 1) {
            start = grid[0].length - 2;
            end = -1;
        } else {
            start = 1;
            end = grid[0].length;
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = start; j != end; j -= dir) {
                int slideIndex = j + dir;
                while (slidesToIndex(i, slideIndex)) {
                    slideIndex += dir;
                }
                if (slideIndex != j + dir) {
                    grid[i][slideIndex - dir] = grid[i][j];
                    grid[i][j] = 0;
                }
            }
        }
    }

    public void slideTilesVertically(int dir) {
        int start;
        int end;
        if (dir == 1) {
            start = grid.length - 2;
            end = -1;
        } else {
            start = 1;
            end = grid.length;
        }
        for (int i = 0; i < grid[0].length; i++) {
            for (int j = start; j != end; j -= dir) {
                int slideIndex = j + dir;
                while (slidesToIndex(slideIndex, i)) {
                    slideIndex += dir;
                }
                if (slideIndex != j + dir) {
                    grid[slideIndex - dir][i] = grid[j][i];
                    grid[j][i] = 0;
                }
            }
        }
    }

    private boolean slidesToIndex(int row, int col) {
        if (row < 0 || row >= grid.length) {
            return false;
        }
        if (col < 0 || col >= grid[0].length) {
            return false;
        }
        return grid[row][col] == 0;
    }

}
