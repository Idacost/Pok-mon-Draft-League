package pokemondraftleague;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        boolean playerCheck = false;
        int playerNumber = 0;

        System.out.println("Welcome to the Pokémon Draft League!\n");

        //Input Players
        Scanner scanner = new Scanner(System.in);

        while (!playerCheck) {
            System.out.println("How many players?");
            System.out.println("\t1. 4 Players");
            System.out.println("\t2. 8 Players\n");
            System.out.print("Enter choice: ");
            int numPlayers = scanner.nextInt();
            scanner.nextLine(); //consume newline

            if (numPlayers == 1){
                playerNumber = 4;
                playerCheck = true;
            } else if (numPlayers == 2)  {
                playerNumber = 8;
                playerCheck = true;
            } else {
                System.out.println("Number of players not valid, please make a valid selection\n");
            }
        }

        List<Player> players = new ArrayList<>();
        for (int i = 0; i < playerNumber; i++) {
            System.out.println("\nPlayer #" + (i + 1) + ":");
            System.out.print("\tEnter player name: ");
            String name = scanner.nextLine();

            System.out.print("\tEnter team name: ");
            String teamName = scanner.nextLine();

            Player player = new Player(name, teamName);
            players.add(player);
            System.out.println("Player " + player.getName() + " with team " + player.getTeamName() + " created.");
        }

        System.out.println("\n--- Teams ---");
        for (Player p : players) {
            System.out.println(p.getName() + " - " + p.getTeamName());
        }

        System.out.println();

        List<Pokemon> allPokemon = Pokemon.getAllPokemon();
        Draft draft = new Draft(players, allPokemon);
        draft.assignRandomOrder();
        draft.runSnakeDraft(scanner);
        draft.handleTrades(scanner);

        // Schedule Matches
        List<Match> matches = new ArrayList<>();
        for (int i = 0; i < players.size(); i++) {
            for (int j = i + 1; j < players.size(); j++) {
                matches.add(new Match(players.get(i), players.get(j)));
            }
        }
        Collections.shuffle(matches);

        for (Match m : matches) {
            m.playMatch(scanner);
        }

        printRankings(players);

        // Playoffs
        players.sort(Comparator.comparingInt(Player::getWins).thenComparingInt(Player::getSurvivors).reversed());
        System.out.println("\n--- Playoffs ---");

        if (players.size() == 4) {
            Match finalMatch = new Match(players.get(0), players.get(1));
            finalMatch.playMatch(scanner);

            // Print the winner of the final match
            System.out.println("\nThe winner of the Pokémon Draft League is: " + finalMatch.getWinner().getName());
        } else {
            Match semi1 = new Match(players.get(0), players.get(3));
            Match semi2 = new Match(players.get(1), players.get(2));
            semi1.playMatch(scanner);
            semi2.playMatch(scanner);

            Player f1 = semi1.getWinner();
            Player f2 = semi2.getWinner();

            Match finalMatch = new Match(f1, f2);
            finalMatch.playMatch(scanner);

            // Print the winner of the final match
            System.out.println("\nThe winner of the Pokémon Draft League is: " + finalMatch.getWinner().getName());
        }

        System.out.println("\nLeague concluded. Thank you for playing!");
        }

        private static Player findPlayerByName(List<Player> players, String name) {
            return players.stream().filter(p -> p.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
        }

        private static void printPokemonList(Player player) {
            for (Pokemon p : player.getDraftedPokemon()) {
                System.out.println(" - " + p.getName() + " [" + p.getTier() + "]");
            }
        }

        private static Pokemon findPokemonByName(Player player, String name) {
            return player.getDraftedPokemon().stream().filter(p -> p.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
        }

    public static void printRankings(List<Player> players) {
        // Sort first by wins (descending), then by survivors (descending) as a tiebreaker
        players.sort((p1, p2) -> {
            int winCompare = Integer.compare(p2.getWins(), p1.getWins());
            if (winCompare != 0) {
                return winCompare;
            } else {
                return Integer.compare(p2.getSurvivors(), p1.getSurvivors());
            }
        });

        System.out.println("\n--- Final Rankings Before Playoffs ---");
        for (int i = 0; i < players.size(); i++) {
            Player p = players.get(i);
            System.out.println((i + 1) + ". " + p.getName() +
                    " (Team: " + p.getTeamName() + ") - Wins: " + p.getWins() +
                    ", Pokémon Surviving: " + p.getSurvivors());
        }
    }

}
