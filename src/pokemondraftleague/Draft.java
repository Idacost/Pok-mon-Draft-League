package pokemondraftleague;

import java.util.*;
import java.util.stream.Collectors;

public class Draft {
    private List<Player> players;
    private Queue<Pokemon> availablePokemon;
    private List<Player> draftOrder;

    public Draft(List<Player> players, List<Pokemon> pokemonList) {
        this.players = players;
        this.availablePokemon = new LinkedList<>(pokemonList);
        this.draftOrder = new ArrayList<>();
    }

    public void assignRandomOrder() {
        draftOrder = new ArrayList<>(players);
        Collections.shuffle(draftOrder);
        System.out.println("Draft Order:");
        for (int i = 0; i < draftOrder.size(); i++) {
            System.out.println((i + 1) + ". " + draftOrder.get(i).getName());
        }
    }

    private String getTierForRound(int round) {
        if (round < 2) return "Ubers";
        else if (round < 5) return "OU";
        else return "UU";
    }

    public void runSnakeDraft(Scanner scanner) {
        int totalRounds = 8;

        for (int round = 0; round < totalRounds; round++) {
            List<Player> currentOrder = (round % 2 == 0) ? draftOrder : new ArrayList<>(draftOrder);
            if (round % 2 != 0) Collections.reverse(currentOrder);

            System.out.println("\n--- Round " + (round + 1) + " ---");

            for (Player player : currentOrder) {
                playerPickPokemon(scanner, player, round);
            }
        }

        System.out.println("\n--- Draft Summary ---");
        for (Player player : players) {
            System.out.println(player.getName() + " (Team: " + player.getTeamName() + ")");
            System.out.println("Drafted Pokémon:");
            for (Pokemon pokemon : player.getDraftedPokemon()) {
                System.out.println(" - " + pokemon.getName() + " [" + pokemon.getTier() + "]");
            }
        }
    }

    public void playerPickPokemon(Scanner scanner, Player player, int round) {
        String currentTier = getTierForRound(round);

        System.out.println("\n" + player.getName() + ", it's your turn to pick a " + currentTier + " Pokémon.");

        // Count player's current picks per tier
        Map<String, Integer> tierCounts = new HashMap<>();
        for (Pokemon p : player.getDraftedPokemon()) {
            tierCounts.put(p.getTier(), tierCounts.getOrDefault(p.getTier(), 0) + 1);
        }

        // Only show available Pokémon from the current tier
        List<Pokemon> options = availablePokemon.stream()
                .filter(p -> p.getTier().equals(currentTier))
                .collect(Collectors.toList());

        for (int i = 0; i < options.size(); i++) {
            Pokemon p = options.get(i);
            System.out.println((i + 1) + ". " + p.getName());
        }

        int choice = -1;
        while (choice < 1 || choice > options.size()) {
            System.out.print("Enter the number of the Pokémon you want to draft: ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            } else {
                System.out.print("Invalid Choice");
                scanner.next(); // skip invalid input
            }
        }

        Pokemon chosen = options.get(choice - 1);
        availablePokemon.remove(chosen);
        player.draftPokemon(chosen);
        System.out.println(player.getName() + " drafted " + chosen.getName());
    }

    public void handleTrades(Scanner scanner) {
        boolean trading = true;

        while (trading) {
            System.out.println("\nWould any player like to initiate a trade? (yes/no)");
            String input = scanner.next().trim().toLowerCase();

            if (!input.equals("yes")) {
                trading = false;
                continue;
            }

            scanner.nextLine(); // consume leftover newline

            System.out.print("Enter your name: ");
            String initiatorName = scanner.nextLine();
            Player initiator = findPlayerByName(initiatorName);

            if (initiator == null) {
                System.out.println("Player not found.");
                continue;
            }

            System.out.print("Enter the name of the player you want to trade with: ");
            String targetName = scanner.nextLine();
            Player target = findPlayerByName(targetName);

            if (target == null || target == initiator) {
                System.out.println("Invalid target player.");
                continue;
            }

            System.out.println("\n" + target.getName() + "'s Pokémon:");
            List<Pokemon> targetMons = target.getDraftedPokemon();
            for (int i = 0; i < targetMons.size(); i++) {
                System.out.println((i + 1) + ". " + targetMons.get(i).getName());
            }

            System.out.print("Enter the number of the Pokémon you want from " + target.getName() + ": ");
            int targetIndex = scanner.nextInt() - 1;
            if (targetIndex < 0 || targetIndex >= targetMons.size()) {
                System.out.println("Invalid selection.");
                continue;
            }
            Pokemon wanted = targetMons.get(targetIndex);

            System.out.println("\nYour Pokémon:");
            List<Pokemon> initiatorMons = initiator.getDraftedPokemon();
            for (int i = 0; i < initiatorMons.size(); i++) {
                System.out.println((i + 1) + ". " + initiatorMons.get(i).getName());
            }

            System.out.print("Enter the number of the Pokémon you want to offer: ");
            int offerIndex = scanner.nextInt() - 1;
            if (offerIndex < 0 || offerIndex >= initiatorMons.size()) {
                System.out.println("Invalid selection.");
                continue;
            }
            Pokemon offered = initiatorMons.get(offerIndex);

            scanner.nextLine(); // consume newline
            System.out.println("\n" + target.getName() + ", do you accept the trade?");
            System.out.println(initiator.getName() + " offers " + offered.getName() + " for your " + wanted.getName());
            System.out.print("Accept? (yes/no): ");
            String response = scanner.nextLine().trim().toLowerCase();

            if (response.equals("yes")) {
                initiator.tradePokemon(offered, wanted);
                target.tradePokemon(wanted, offered);
                System.out.println("Trade completed!");
            } else {
                System.out.println("Trade declined.");
            }
        }
    }

    private Player findPlayerByName(String name) {
        for (Player player : players) {
            if (player.getName().equalsIgnoreCase(name)) {
                return player;
            }
        }
        return null;
    }

}
