import java.util.ArrayList;

public class Game2048 {

    private int[][] grid = null;

    public void startGame() {
        grid = new int[4][4];
        IO.println("Welcome to 2048!\n");
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
}
