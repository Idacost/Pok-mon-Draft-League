package pokemondraftleague;

import java.util.*;

public class Pokemon {
    private String name;
    private String tier;

    public Pokemon(String name, String tier) {
        this.name = name;
        this.tier = tier;
    }

    public String getName() {
        return name;
    }

    public String getTier() {
        return tier;
    }

    public static List<Pokemon> getAllPokemon() {
        List<Pokemon> all = new ArrayList<>();

        // Ubers
        String[] ubers = {
                "Annihilape", "Baxcalibur", "Chi-Yu", "Cinderace", "Deoxys Speed", "Enamorus", "Garchomp",
                "Gholdengo", "Greninja", "Iron Bundle", "Iron Treads", "Landorus-Therian", "Latias", "Latios",
                "Manaphy", "Mega Charizard X", "Mega Diancie", "Mega Lopunny", "Mega Mawile", "Melmetal", "Mew",
                "Roaring Moon", "Sneasler", "Tornadus-Therian", "Ursaluna", "Ursaluna-Bloodmoon", "Victini",
                "Walking Wake", "Zapdos", "Zeraora"
        };
        for (String name : ubers) all.add(new Pokemon(name, "Ubers"));

        // OU
        String[] ou = {
                "Aegislash", "Archaludon", "Celesteela", "Corviknight", "Deoxys Defense", "Dracovish", "Excadrill",
                "Heatran", "Iron Boulder", "Iron Hands", "Jirachi", "Mega Aerodactyl", "Mega Charizard Y",
                "Mega Gardevoir", "Meowscarada", "Raging Bolt", "Slowking-Galar", "Tapu Fini", "Tapu Lele",
                "Terrakion", "Ting-Lu", "Urshifu RS", "Urshifu SS", "Volcarona", "Weavile", "Zygarde"
        };
        for (String name : ou) all.add(new Pokemon(name, "OU"));

        // UU
        String[] uu = {
                "Blacephalon", "Ceruledge", "Clefable", "Conkeldurr", "Cresselia", "Cyclizar", "Dragonite", "Gengar",
                "Gliscor", "Hawlucha", "Hippowdon", "Hoopa-Unbound", "Hydreigon", "Iron Moth", "Kartana", "Kingambit",
                "Kommo-o", "Kyurem", "Mamoswine", "Mandibuzz", "Mega Aggron", "Mega Altaria", "Mega Garchomp",
                "Mega Gyarados", "Mega Heracross", "Mega Swampert", "Mega Tyranitar", "Mega Venusaur", "Necrozma",
                "Ogerpon-Hearthflame", "Ogerpon-Wellspring", "Pelipper", "Primarina", "Quaquaval", "Rillaboom",
                "Rotom-Heat", "Rotom-Wash", "Salamence", "Scizor", "Scream Tail", "Skarmory", "Skeledirge", "Slowbro",
                "Slowking", "Sylveon", "Tangrowth", "Thundurus", "Thundurus-Therian", "Toxapex", "Tyranitar", "Zapdos-Galar"
        };
        for (String name : uu) all.add(new Pokemon(name, "UU"));

        return all;
    }
}
