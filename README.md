# MCSR Ranked Stats Viewer

An MCSR Ranked stats viewer where the user can input a name, and timeframe/game count to check their stats and list possible improvements.

## The Problem

Current stats viewing is very limited and only contains individual splits, and a couple overall stats, with few criteria the user can select. This makes it much harder to improve when the user may be unaware of specific issues they may be having. The API contains much more that can be viewed to further show what specific things a player needs to improve on. (Example, much easier to understand if you have experience: Being able to check the last weeks worth of games, and seeing that on village seeds, bastion splits were slower, so the player could figure out the issue much easier, rather than only seeing that their entire village average was slower).

## How It Works

The user enters a player name and a timeframe or game count. The app gets that player's matches from the MCSR Ranked API and shows their general stats along with some basic improvements.

![System diagram](docs/system-diagram.png)

## MVP Scope

- Search a player by name
- Choose a timeframe or game count
- Grab match data from the API
- Show base stats similar to the current Discord bot (general stats), with basic improvements
- Super basic GUI that just outputs the data

## Tech Stack

- **Language:** Java
- **Libraries:** JavaFX, Jackson
- **External tools:** MCSR Ranked API (https://docs.mcsrranked.com/), Gradle

## Setup

```bash
git clone https://github.com/VladM07D/mcsr-stats-app
cd mcsr-stats-app
./gradlew run
```

## Usage

Enter a player name, pick a timeframe or game count, and the app will show their stats.

```bash
git clone https://github.com/VladM07D/mcsr-stats-app
cd mcsr-stats-app
./gradlew run
```

## Roadmap

- [ ] Base visuals, such as a clean GUI with user data then stats
- [ ] Opponent comparison
- [ ] More specific improvements (like the village/bastion example above)
- [ ] Graphs with player and opponent splits
- [ ] Saving data the user wants to look back at
- [ ] Showing improvements over time/season span

## Author

Vlad, https://github.com/VladM07D