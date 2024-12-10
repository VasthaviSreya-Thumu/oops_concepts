import java.util.*;

class Snake {
    private int head;
    private int tail;

    public Snake(int head, int tail) {
        this.head = head;
        this.tail = tail;
    }

    public int getHead() {
        return head;
    }

    public int getTail() {
        return tail;
    }
}

class Ladder {
    private int start;
    private int end;

    public Ladder(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}

class Player {
    private String name;
    private int position;

    public Player(String name) {
        this.name = name;
        this.position = 0; // Starting position
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}

class SnakeAndLadderGame {
    private List<Snake> snakes;
    private List<Ladder> ladders;
    private List<Player> players;
    private int boardSize = 100;
    private Random random = new Random();

    public SnakeAndLadderGame(List<Snake> snakes, List<Ladder> ladders, List<Player> players) {
        this.snakes = snakes;
        this.ladders = ladders;
        this.players = players;
    }

    public void startGame() {
        boolean gameWon = false;

        while (!gameWon) {
            for (Player player : players) {
                int diceValue = rollDice();
                int newPosition = player.getPosition() + diceValue;

                if (newPosition > boardSize) {
                    System.out.println(player.getName() + " rolled a " + diceValue + " and cannot move.");
                } else {
                    newPosition = checkForSnakesAndLadders(newPosition);
                    System.out.println(player.getName() + " rolled a " + diceValue + " and moved from "
                            + player.getPosition() + " to " + newPosition);
                    player.setPosition(newPosition);

                    if (newPosition == boardSize) {
                        System.out.println(player.getName() + " wins the game!");
                        gameWon = true;
                        break;
                    }
                }
            }
        }
    }

    private int rollDice() {
        return random.nextInt(6) + 1;
    }

    private int checkForSnakesAndLadders(int position) {
        for (Snake snake : snakes) {
            if (snake.getHead() == position) {
                System.out.println("Oh no! Bitten by a snake at " + position);
                return snake.getTail();
            }
        }

        for (Ladder ladder : ladders) {
            if (ladder.getStart() == position) {
                System.out.println("Yay! Climbed a ladder at " + position);
                return ladder.getEnd();
            }
        }

        return position;
    }
}

public class SnakeAndLadder {
    public static void main(String[] args) {
        List<Snake> snakes = new ArrayList<>();
        snakes.add(new Snake(14, 7));
        snakes.add(new Snake(31, 26));
        snakes.add(new Snake(78, 39));
        snakes.add(new Snake(94, 72));

        List<Ladder> ladders = new ArrayList<>();
        ladders.add(new Ladder(3, 22));
        ladders.add(new Ladder(15, 44));
        ladders.add(new Ladder(48, 84));
        ladders.add(new Ladder(68, 89));

        List<Player> players = new ArrayList<>();
        players.add(new Player("Alice"));
        players.add(new Player("Bob"));

        SnakeAndLadderGame game = new SnakeAndLadderGame(snakes, ladders, players);
        game.startGame();
    }
}
