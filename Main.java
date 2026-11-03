public class Main {
    public static void main(String[] args) {
        Game2048 game = new Game2048();
        game.startGame();
        game.showBoard();
        game.addTiles(2);
        game.showBoard();
    }
}


