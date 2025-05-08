package pokemondraftleague;

import java.util.*;

public class Match {
    private Player player1;
    private Player player2;
    private Player winner;
    private int p1Alive;
    private int p2Alive;

    public Match(Player p1, Player p2) {
        this.player1 = p1;
        this.player2 = p2;
    }

    public void playMatch(Scanner scanner) {
        System.out.println("\nMatch: " + player1.getName() + " vs " + player2.getName());

        System.out.println("Who won the match?");
        System.out.println("1. " + player1.getName());
        System.out.println("2. " + player2.getName());

        int choice = -1;
        while (choice != 1 && choice != 2) {
            System.out.print("Enter 1 or 2: ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            } else {
                scanner.next();
            }
        }

        if (choice == 1) {
            winner = player1;
            winner.winScore();
            player2.loseScore();
        } else {
            winner = player2;
            winner.winScore();
            player1.loseScore();
        }

        System.out.print("How many Pokémon did " + winner.getName() + " have left alive? (Between 1 - 6): ");
        int alive = -1;
        while (alive < 0 || alive > 6) {
            if (scanner.hasNextInt()) {
                alive = scanner.nextInt();
            } else {
                scanner.next();
            }
        }

        if (winner == player1) {
            p1Alive = alive;
            p2Alive = 0;
        } else {
            p2Alive = alive;
            p1Alive = 0;
        }

        player1.addSurvivors(p1Alive);
        player2.addSurvivors(p2Alive);
    }

    public Player getWinner() {
        return winner;
    }

}