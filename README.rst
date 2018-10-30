College Football Coach Project
==============================

User Interface
==============


I. Main Screen
--------------
- Play Game button
- How To Play button
- Reset button
    - Resets database, unbinds selected team from user, if applicable
    - Pop-up alert will double-check with user before resetting database

II. (on selecting How To Play)
------------------------------
- Open new Activity
- Scroll-able text Instructions

III. (on selecting Start New Game)
----------------------------------
- Open new Activity
- "Choose A Team" at top
- Scroll-able Team Selection

IV. (on selecting a Team)
-------------------------
- Open new Activity
- This will be the new default screen on start-up during season
- Three tabs (This Week, Rankings, My Team)
	- This Week displays most recent game, next game, your team stats
		- Also includes Play Next Game button
	- Rankings displays all team stats so far in season
	- My Team displays your team's players, stats, game history
- One Options button in top right corner (Gear Icon?)
	- Allows user to visit How To Play Activity or Reset
- As user plays through season
	- Stats are updated for your players
	- Stats are updated for all teams (W/L, Ranking)
- Currently, the plan is to automatically update your roster between seasons
    - i.e. No manual recruitment; graduating players will leave and be randomly replaced
- Once roster is updated, new season begins


         `EVERYTHING BELOW THIS LINE IS OPTIONAL`
``ONLY ATTEMPT TO IMPLEMENT AFTER SEASON IS IMPLEMENTED``


- User acquires certain amount of Recruitment Dollars
	- Based on team's performance
	- Displayed on My Team tab during season
- Once user finishes season, display a message on This Week tab
	- Say that the season is over and display an Off-Season button
	- Nothing new can happen until Start Off-Season is selected


V. (on selecting Start Off-Season)
----------------------------------
- Open new Activity
- This will be the new default screen on start-up during Off-Season
- No tabs, just catalog of available players
	- This includes Name and Ranking (A-F, +/-)
	- Should it include Position?
- Options button in top right corner still available
- Recruitment Dollars displayed at top
	- Adjusted as players are recruited
	- Players will be semi-randomly assigned if you run out
	  of RDs and your team isn't full
		- Semi-randomly meaning that you'll be more likely to get bad players than good ones
- Once team is full, a Toast will appear with a button to start next season

VI. (on selecting Start Next Season)
------------------------------------
- Return to IV. Repeat IV, V until Database is reset (user starts new game)
