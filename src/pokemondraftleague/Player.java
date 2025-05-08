package pokemondraftleague;

import java.util.*;

public class Player {
    private String name;
    private String teamName;
    private List<Pokemon> draftedPokemon;
    private int wins;
    private int losses;
    private int totalSurvivors = 0;

    public Player(String name, String teamName) {
        this.name = name;
        this.teamName = teamName;
        this.draftedPokemon = new ArrayList<>();
        this.wins = 0;
        this.losses = 0;
        this.totalSurvivors = 0;
    }

    public void draftPokemon(Pokemon pokemon) {
        draftedPokemon.add(pokemon);
    }

    public void tradePokemon(Pokemon toGive, Pokemon toReceive) {
        draftedPokemon.remove(toGive);
        draftedPokemon.add(toReceive);
    }

    public void winScore() {
        wins++;
    }

    public void loseScore() {
        losses++;
    }


    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public List<Pokemon> getDraftedPokemon() {
        return draftedPokemon;
    }

    public void setDraftedPokemon(List<Pokemon> draftedPokemon) {
        this.draftedPokemon = draftedPokemon;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public void addSurvivors(int survivors) {
        totalSurvivors += survivors;
    }

    public int getSurvivors() {
        return totalSurvivors;
    }
}
