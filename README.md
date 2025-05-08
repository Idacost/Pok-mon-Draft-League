# Pokémon Draft League

A simple Pokémon Draft League system designed to simulate a competitive draft league, allowing players to draft Pokémon, trade Pokémon, play matches, and track results. The system supports registration, snake-style drafting, trades, and playoff matchups.

## Features

- **Player Registration:** Players can register with their name and team name.
- **Drafting:** Pokémon are drafted in a snake-style format, with players selecting Pokémon from predefined tiers.
- **Tiers**: Pokémon are categorized into tiers for strategic drafting.
- **Trades:** Players can trade Pokémon with mutual agreement.
- **Match Results:** Players report match results, which are used to calculate player standings.
- **Playoffs:** The top players advance to the playoffs based on their regular-season results.

## Prerequisites

- Java 8 or higher
- IDE (e.g., IntelliJ IDEA, Eclipse) or a text editor to run the code
- Git (optional for version control)

## Project Structure

```plaintext
├── src/
│   ├── pokemondraftleague
│   │   ├── Draft.java
│   │   ├── Player.java
│   │   ├── Pokemon.java
│   │   ├── Match.java
│   │   └── Main.java
└── README.md
```

## Getting Started

### Clone the Repository

1. Clone the repository or download the project files.
```bash
git clone https://github.com/Idacost/Pokemon-Draft-League.git
```
2. Navigate to the project directory.

3. Open the src folder and compile the Java files.

4. Run the application from the Main.java file.

## How to Use

Registration: The player will be prompted to enter their name and team name to register for the draft.

Drafting: Players will select Pokémon from the available tiers. The system will follow a randomly assigned snake draft order, allowing players to pick in turns.

Trading: After drafting, players can trade Pokémon with others, given mutual agreement.

Match Results: Players can report the results of their matches, and the system will calculate the scores.

Playoffs: At the end of the regular season, the system will automatically select top-performing players and organize the playoffs.

Winner: At the end of playoffs, players will report the result of their matches and a winner will be decided.

