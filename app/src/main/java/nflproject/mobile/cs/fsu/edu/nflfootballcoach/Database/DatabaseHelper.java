package nflproject.mobile.cs.fsu.edu.nflfootballcoach.Database;

import android.content.Context;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import nflproject.mobile.cs.fsu.edu.nflfootballcoach.DAOs.GamesDAO;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.DAOs.StateDAO;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.DAOs.TeamsDAO;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.models.Game;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.models.State;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.models.Team;

public class DatabaseHelper {

    AppDatabase database;

    Context mContext;

    TeamsDAO teamsDAO;

    GamesDAO gamesDAO;

    StateDAO stateDAO;

    Random rand = new Random();

    public DatabaseHelper(Context context, AppDatabase database){
        this.database = database;
        this.mContext = context;
        this.teamsDAO = database.getTeamsDAO();
        this.gamesDAO = database.getGamesDAO();
        this.stateDAO = database.getStateDAO();
    }

    public void reset(){
        deleteGame();
        gamesDAO.deleteAllGames();
        populateDatabase();
    }

    public void populateDatabase(){
        populateTeams();
        populateGames();
    }

    public void populateTeams() {
        teamsDAO.insert(new Team("Boston College", "BC","ACC", "Atlantic", 0, 0,
                0, 0, 76, 85, 38));
        teamsDAO.insert(new Team("Clemson", "CLEM","ACC", "Atlantic", 0,0,
                0, 0, 81, 95, 1476));
        teamsDAO.insert(new Team("Florida State", "FSU","ACC", "Atlantic", 0,0,
                0,0,75,85,450));
        teamsDAO.insert(new Team("Louisville", "LOU","ACC", "Atlantic", 0,0,
                0, 0, 87,79,0));
        teamsDAO.insert(new Team("NC State", "NCST","ACC", "Atlantic", 0,0,
                0,0, 83,81,60));
        teamsDAO.insert(new Team("Syracuse", "SYR","ACC", "Atlantic", 0,0,
                0,0,77,77,0));
        teamsDAO.insert(new Team("Wake Forest", "WF", "ACC", "Atlantic", 0,0,
                0,0, 80,82,0));
        teamsDAO.insert(new Team("Duke", "DUKE","ACC", "Coastal", 0,0,
                0,0, 76,84,0));
        teamsDAO.insert(new Team("Georgia Tech", "GT","ACC", "Coastal", 0,0,
                0,0,82,81,0));
        teamsDAO.insert(new Team("North Carolina", "UNC","ACC", "Coastal", 0,0,
                0,0, 73,78,0));
        teamsDAO.insert(new Team("Pittsburgh", "PITT","ACC", "Coastal", 0,0,
                0,0,75,80,0));
        teamsDAO.insert(new Team("Miami", "UM","ACC", "Coastal", 0,0,
                0,0,79,87,1027));
        teamsDAO.insert(new Team("Virginia", "UVA","ACC", "Coastal", 0,0,
                0,0, 73,84, 0));
        teamsDAO.insert(new Team("Virginia Tech", "VT","ACC", "Coastal", 0,0,
                0,0, 75,90,384));


        teamsDAO.insert(new Team("Baylor", "BAY","Big 12", "", 0,0,
                0,0,72,75,0));
        teamsDAO.insert(new Team("Iowa State", "IAST","Big 12", "", 0,0,
                0,0,80,85,21));
        teamsDAO.insert(new Team("Kansas", "KAN","Big 12", "", 0,0,
                0,0,70,76,0));
        teamsDAO.insert(new Team("Kansas State", "KSST","Big 12", "", 0,0,
                0,0,76,79,22));
        teamsDAO.insert(new Team("Oklahoma", "OKLA","Big 12", "", 0,0,
                0,0,95,76, 1173));
        teamsDAO.insert(new Team("Oklahoma State", "OKST","Big 12", "", 0,0,
                0,0,91,80,96));
        teamsDAO.insert(new Team("TCU", "TCU","Big 12", "", 0,0,
                0,0,78,88,543));
        teamsDAO.insert(new Team("Texas", "TEX","Big 12", "", 0,0,
                0,0,70,87,351));
        teamsDAO.insert(new Team("Texas Tech", "TTECH","Big 12", "", 0,0,
                0,0,80,78,0));
        teamsDAO.insert(new Team("West Virginia", "WVU","Big 12", "", 0,0,
                0,0,79,77,533));

        teamsDAO.insert(new Team("Indiana", "IND","Big Ten", "East", 0,0,
                0,0,75,86,0));
        teamsDAO.insert(new Team("Maryland", "MD","Big Ten", "East", 0,0,
                0,0,78,78,0));
        teamsDAO.insert(new Team("Michigan", "MICH","Big Ten", "East", 0,0,
                0,0,74,90,778));
        teamsDAO.insert(new Team("Michigan State", "MIST","Big Ten", "East", 0,0,
                0,0,77,91,877));
        teamsDAO.insert(new Team("Ohio State", "OHST","Big Ten", "East", 0,0,
                0,0,89,90,1256));
        teamsDAO.insert(new Team("Penn State", "PSU","Big Ten", "East", 0,0,
                0,0,88,88,1012));
        teamsDAO.insert(new Team("Rutgers", "RUTG","Big Ten", "East", 0,0,
                0,0,70,82,0));
        teamsDAO.insert(new Team("Illinois", "ILL","Big Ten", "West", 0,0,
                0,0,67,78,0));
        teamsDAO.insert(new Team("Iowa", "IOWA","Big Ten", "West", 0,0,
                0,0,79,88,18));
        teamsDAO.insert(new Team("Minnesota", "MINN","Big Ten", "West", 0,0,
                0,0,75,84,0));
        teamsDAO.insert(new Team("Nebraska", "NEB","Big Ten", "West", 0,0,
                0,0,79,75,0));
        teamsDAO.insert(new Team("Northwestern", "NW","Big Ten", "West", 0,0,
                0,0,76,87,28));
        teamsDAO.insert(new Team("Purdue", "PURD","Big Ten", "West", 0,0,
                0,0, 76,85,0));
        teamsDAO.insert(new Team("Wisconsin", "WISC","Big Ten", "West", 0,0,
                0,0,83,94,1271));

        teamsDAO.insert(new Team("California", "CAL","Pac-12", "North", 0,0,
                0,0,74,79,0));
        teamsDAO.insert(new Team("Oregon", "ORE","Pac-12", "North", 0,0,
                0,0,76,81,312));
        teamsDAO.insert(new Team("Oregon State", "ORST","Pac-12", "North", 0,0,
                0,0,71,69,0));
        teamsDAO.insert(new Team("Stanford", "STAN","Pac-12", "North", 0,0,
                0,0,83,81,804));
        teamsDAO.insert(new Team("Washington", "WASH","Pac-12", "North", 0,0,
                0,0,85,91,1215));
        teamsDAO.insert(new Team("Washington State", "WSU","Pac-12", "North", 0,0,
                0,0,75,85,0));
        teamsDAO.insert(new Team("Arizona", "ARI","Pac-12", "South", 0,0,
                0,0,86,75,68));
        teamsDAO.insert(new Team("Arizona State", "ASU","Pac-12", "South", 0,0,
                0,0,79,76,0));
        teamsDAO.insert(new Team("Colorado", "COLO","Pac-12", "South", 0,0,
                0,0,77,77,0));
        teamsDAO.insert(new Team("UCLA", "UCLA","Pac-12", "South", 0,0,
                0,0,81,73,0));
        teamsDAO.insert(new Team("USC", "USC","Pac-12", "South", 0,0,
                0,0,83,82,773));
        teamsDAO.insert(new Team("Utah", "UTAH","Pac-12", "South", 0,0,
                0,0, 74,85,106));

        teamsDAO.insert(new Team("Florida", "FLA","SEC", "East",0,0,
                0,0,72,82,148));
        teamsDAO.insert(new Team("Georgia", "UGA","SEC", "East", 0,0,
                0,0,87,89,1350));
        teamsDAO.insert(new Team("Kentucky", "UK","SEC", "East", 0,0,
                0,0,76,77,13));
        teamsDAO.insert(new Team("Missouri", "MIZZ","SEC", "East", 0,0,
                0,0,83,78,0));
        teamsDAO.insert(new Team("South Carolina", "SCAR","SEC", "East", 0,0,
                0,0,75,85,216));
        teamsDAO.insert(new Team("Tennessee", "TENN","SEC", "East", 0,0,
                0,0,71,80,0));
        teamsDAO.insert(new Team("Vanderbilt", "VAND","SEC", "East", 0,0,
                0,0,79,81,0));
        teamsDAO.insert(new Team("Alabama", "ALA","SEC", "West", 0,0,
                0,0,85,95,1505));
        teamsDAO.insert(new Team("Arkansas", "ARK","SEC", "West", 0,0,
                0,0,80,75,0));
        teamsDAO.insert(new Team("Auburn", "AUB","SEC", "West",0,0,
                0,0,83,91,1013));
        teamsDAO.insert(new Team("LSU", "LSU","SEC", "West", 0,0,
                0,0,81,87,292));
        teamsDAO.insert(new Team("Ole Miss", "MISS","SEC", "West", 0,0,
                0,0,79,75,0));
        teamsDAO.insert(new Team("Mississippi State", "MSST","SEC", "West", 0,0,
                0,0,79,87,511));
        teamsDAO.insert(new Team("Texas A&M", "TAMU","SEC", "West", 0,0,
                0,0,76,80,51));

        //BYE week "team"
        teamsDAO.insert(new Team("BYE", "BYE","BYE", "BYE", 0,0,
                0,0,0,0,0));

        //Just a couple of "fake" cupcake teams. Not playable.
        teamsDAO.insert(new Team("Buffalo", "BUFF", "MAC", "EAST",0,0,
                0,0,60,60,0));
        teamsDAO.insert(new Team("Ohio", "OHIO", "MAC", "EAST", 0,0,
                0,0,60,60,0));
        teamsDAO.insert(new Team("Bowling Green", "BGSU", "MAC", "EAST", 0,0,
                0,0,60,60,0));
        teamsDAO.insert(new Team("Miami-OH", "M-OH", "MAC", "EAST",0,0,
                0,0,60,60,0));
        teamsDAO.insert(new Team("Kent State", "KENT", "MAC","East",0,0,
                0,0,60,60,0));
        teamsDAO.insert(new Team("Akron", "AKRON", "MAC", "East", 0,0,
                0,0,60,60,0));
        teamsDAO.insert(new Team("Northern Illinois", "NIU", "MAC", "West",0,0,
                0,0,60,60,0));
        teamsDAO.insert(new Team("Eastern Michican", "EMU", "MAC", "West", 0,0,
                0,0, 60,60,0));
        teamsDAO.insert(new Team("Toledo", "TOL", "MAC","West", 0,0,
                0,0,60,60,0));
        teamsDAO.insert(new Team("Western Michigan", "WMU", "MAC","West",0,0,
                0,0,60,60,0));
        teamsDAO.insert(new Team("Ball State", "BALL", "MAC","West",0,0,
                0,0,60,60,0));
        teamsDAO.insert(new Team("Central Michigan", "CMU", "MAC", "West", 0,0,
                0,0,60,60,0));
    }

    public void populateGames(){
        oocGameGeneration();    //generates the OOC games
        accGameGeneration();    //generates ACC games
        big12GameGeneration();  //generates Big 12 games
        secGameGeneration();    //generates sec games
        big10GameGeneration();  //generates Big 10 games
        pac12GameGeneration();  //generates Pac 12 games
    }

    public void oocGameGeneration(){
        List<Team> allTeams = teamsDAO.getTeams();             //the real teams
        List<Team> fillerTeams = teamsDAO.getFillerTeams();    //not the real teams

        String away, home;

        Collections.shuffle(allTeams);

        for(int wk = 1; wk<=3; wk++)
        {
            int i = 0;
            int j = allTeams.size() - 1;
            while(i < j)
            {
                if (allTeams.get(i).getConference().equals(allTeams.get(j).getConference()))
                {
                    int opp = rand.nextInt(12);
                    boolean alreadyPlayed = true;
                    do
                    {
                        Game newGame = new Game(allTeams.get(i).getName(), fillerTeams.get(opp).getName(), wk, 0, 0 );
                        if(gamesDAO.getGame(newGame.getHome(), newGame.getAway()) == null) {
                            gamesDAO.insert(newGame);
                            alreadyPlayed = false;
                        }
                        else
                            opp = (opp + 1) % fillerTeams.size();
                    } while (alreadyPlayed);

                    opp = rand.nextInt(12);
                    alreadyPlayed = true;
                    do
                    {
                        Game newGame = new Game(allTeams.get(j).getName(), fillerTeams.get(opp).getName(), wk, 0, 0 );
                        if(gamesDAO.getGame(newGame.getHome(), newGame.getAway()) == null) {
                            gamesDAO.insert(newGame);
                            alreadyPlayed = false;
                        }
                        else
                            opp = (opp + 1) % fillerTeams.size();
                    } while (alreadyPlayed);
                }
                else
                {
                    if(wk % 2 == 0)
                    {
                        home = allTeams.get(j).getName();
                        away = allTeams.get(i).getName();
                    }
                    else
                    {
                        home = allTeams.get(i).getName();
                        away = allTeams.get(j).getName();
                    }
                    gamesDAO.insert(new Game(home,away,wk,0,0));
                }

                i++;
                j--;
            }
            Collections.rotate(allTeams, 1);
        }

        //ACC and SEC teams have an additional OOC game
        List<Team> accTeams = teamsDAO.getTeamsFromConference("ACC");
        List<Team> secTeams = teamsDAO.getTeamsFromConference("SEC");

        Collections.shuffle(accTeams);
        Collections.shuffle(secTeams);

        for(int i = 0; i<accTeams.size(); i++)
        {
            int rVar = rand.nextInt(2);
            if(rVar == 0){
                home = accTeams.get(i).getName();
                away = secTeams.get(i).getName();
            }
            else{
                home = secTeams.get(i).getName();
                away = accTeams.get(i).getName();
            }

            if(gamesDAO.getGame(home, away) != null || gamesDAO.getGame(away, home) != null) {
                int opp = rand.nextInt(12);
                boolean alreadyPlayed = true;
                do {
                    Game newGame = new Game(accTeams.get(i).getName(), fillerTeams.get(opp).getName(), 4, 0, 0);
                    if (gamesDAO.getGame(newGame.getHome(), newGame.getAway()) == null) {
                        gamesDAO.insert(newGame);
                        alreadyPlayed = false;
                    }
                    else
                        opp = (opp + 1) % fillerTeams.size();
                } while (alreadyPlayed);

                opp = rand.nextInt(12);
                alreadyPlayed = true;
                do {
                    Game newGame = new Game(secTeams.get(i).getName(), fillerTeams.get(opp).getName(), 4, 0, 0);
                    if (gamesDAO.getGame(newGame.getHome(), newGame.getAway()) == null) {
                        gamesDAO.insert(newGame);
                        alreadyPlayed = false;
                    }
                    else
                        opp = (opp + 1) % fillerTeams.size();
                } while (alreadyPlayed);
            }
            else
                gamesDAO.insert(new Game(home, away, 4,0,0));
        }
    }

    public void accGameGeneration(){
        //get teams from both divisions
        List<Team> accAtlTeams = teamsDAO.getTeamsByDivision("Atlantic");
        List<Team> accCoastalTeams = teamsDAO.getTeamsByDivision("Coastal");

        //randomize team order
        Collections.shuffle(accAtlTeams);
        Collections.shuffle(accCoastalTeams);

        //generate interdivisional games
        for(int i = 0; i<accAtlTeams.size(); i++)
            gamesDAO.insert(new Game(accAtlTeams.get(i).getName(), accCoastalTeams.get(i).getName(), 5,
                    0,0));

        Collections.rotate(accAtlTeams,1);

        //generate interdivisional games again
        for(int i = 0; i<accAtlTeams.size(); i++)
        {
            gamesDAO.insert(new Game(accAtlTeams.get(i).getName(), accCoastalTeams.get(i).getName(), 13,
                    0,0));
        }

        //generate divisional games using round robin algorithm
        String home, away;
        for (int wk = 6; wk <= 12; wk++)
        {
            int i = 0;
            int j = accAtlTeams.size() - 1;

            while(i != j)
            {
                if (wk % 2 == 0) {
                    home = accAtlTeams.get(j).getName();
                    away = accAtlTeams.get(i).getName();
                }
                else {
                    home = accAtlTeams.get(i).getName();
                    away = accAtlTeams.get(j).getName();
                }
                gamesDAO.insert(new Game(home, away, wk, 0, 0));

                if (wk % 2 == 0) {
                    home = accCoastalTeams.get(j).getName();
                    away = accCoastalTeams.get(i).getName();
                }
                else {
                    home = accCoastalTeams.get(i).getName();
                    away = accCoastalTeams.get(j).getName();
                }
                gamesDAO.insert(new Game(home, away, wk, 0, 0));

                i++;
                j--;
            }
            gamesDAO.insert(new Game(accAtlTeams.get(i).getName(), "BYE", wk, 0,0));
            gamesDAO.insert(new Game(accCoastalTeams.get(i).getName(), "BYE", wk, 0,0));

            Collections.rotate(accAtlTeams, 1);
            Collections.rotate(accCoastalTeams, 1);
        }
    }

    public void big12GameGeneration(){
        List<Team> big12Teams = teamsDAO.getTeamsFromConference("Big 12");

        Collections.shuffle(big12Teams);

        Team finalTeam = big12Teams.get(big12Teams.size() - 1);

        String home, away;
        for(int wk = 4; wk<=13; wk++)
        {
            int i = 0;
            int j = big12Teams.size() - 1;

            while(i < j)
            {
                if(wk % 7 == 0)
                {
                    for(int k = 0; k < big12Teams.size(); k++)
                        gamesDAO.insert(new Game(big12Teams.get(k).getName(), "BYE", wk, 0,0));
                }
                else
                {
                    if (wk % 2 == 0)
                    {
                        home = big12Teams.get(j).getName();
                        away = big12Teams.get(i).getName();
                    }
                    else
                    {
                        home = big12Teams.get(i).getName();
                        away = big12Teams.get(j).getName();
                    }
                    gamesDAO.insert(new Game(home, away, wk, 0, 0));
                }
                i++;
                j--;
            }

            if(wk % 7 != 0) {
                big12Teams.remove(big12Teams.size() - 1);
                Collections.rotate(big12Teams, 1);
                big12Teams.add(finalTeam);
            }
        }
    }

    public void secGameGeneration(){
        //get teams from both divisions
        List<Team> secEastTeams = teamsDAO.getTeamsFromConferenceDivision("SEC","East");
        List<Team> secWestTeams = teamsDAO.getTeamsFromConferenceDivision("SEC","West");

        //randomize team order
        Collections.shuffle(secEastTeams);
        Collections.shuffle(secWestTeams);

        //generate interdivisional games
        for(int i = 0; i<secEastTeams.size(); i++)
            gamesDAO.insert(new Game(secEastTeams.get(i).getName(), secWestTeams.get(i).getName(), 5,
                    0,0));

        Collections.rotate(secEastTeams,1);

        //generate interdivisional games again
        for(int i = 0; i<secEastTeams.size(); i++)
        {
            gamesDAO.insert(new Game(secWestTeams.get(i).getName(), secEastTeams.get(i).getName(), 13,
                    0,0));
        }

        //generate divisional games using round robin algorithm
        String home, away;
        for (int wk = 6; wk <= 12; wk++)
        {
            int i = 0;
            int j = secEastTeams.size() - 1;

            while(i != j)
            {
                if (wk % 2 == 0) {
                    home = secEastTeams.get(j).getName();
                    away = secEastTeams.get(i).getName();
                }
                else {
                    home = secEastTeams.get(i).getName();
                    away = secEastTeams.get(j).getName();
                }
                gamesDAO.insert(new Game(home, away, wk, 0, 0));

                if (wk % 2 == 0) {
                    home = secWestTeams.get(j).getName();
                    away = secWestTeams.get(i).getName();
                }
                else {
                    home = secWestTeams.get(i).getName();
                    away = secWestTeams.get(j).getName();
                }
                gamesDAO.insert(new Game(home, away, wk, 0, 0));

                i++;
                j--;
            }
            gamesDAO.insert(new Game(secEastTeams.get(i).getName(), "BYE", wk, 0,0));
            gamesDAO.insert(new Game(secWestTeams.get(i).getName(), "BYE", wk, 0,0));

            Collections.rotate(secEastTeams, 1);
            Collections.rotate(secWestTeams, 1);
        }
    }

    public void big10GameGeneration(){
        //get teams from both divisions
        List<Team> big10EastTeams = teamsDAO.getTeamsFromConferenceDivision("Big Ten","East");
        List<Team> big10WestTeams = teamsDAO.getTeamsFromConferenceDivision("Big Ten", "West");

        //randomize team order
        Collections.shuffle(big10EastTeams);
        Collections.shuffle(big10WestTeams);

        int rVar = rand.nextInt(2);

        //generate interdivisional games
        for(int i = 0; i<big10EastTeams.size(); i++) {
            if(rVar == 0)
                gamesDAO.insert(new Game(big10EastTeams.get(i).getName(), big10WestTeams.get(i).getName(), 4,
                        0, 0));
            else
                gamesDAO.insert(new Game(big10WestTeams.get(i).getName(), big10EastTeams.get(i).getName(), 4,
                        0, 0));
        }
        Collections.rotate(big10EastTeams,1);

        //generate interdivisional games
        for(int i = 0; i<big10EastTeams.size(); i++) {
            if(rVar == 0)
                gamesDAO.insert(new Game(big10WestTeams.get(i).getName(), big10EastTeams.get(i).getName(), 5,
                        0, 0));
            else
                gamesDAO.insert(new Game(big10EastTeams.get(i).getName(), big10WestTeams.get(i).getName(), 5,
                        0, 0));
        }
        Collections.rotate(big10EastTeams,1);

        //generate interdivisional games
        for(int i = 0; i<big10EastTeams.size(); i++) {
            if(rVar == 0)
                gamesDAO.insert(new Game(big10EastTeams.get(i).getName(), big10WestTeams.get(i).getName(), 13,
                        0, 0));
            else
                gamesDAO.insert(new Game(big10WestTeams.get(i).getName(), big10EastTeams.get(i).getName(), 13,
                        0, 0));
        }

        //generate divisional games using round robin algorithm
        String home, away;
        for (int wk = 6; wk <= 12; wk++)
        {
            int i = 0;
            int j = big10EastTeams.size() - 1;

            while(i != j)
            {
                if (wk % 2 == 0) {
                    home = big10EastTeams.get(j).getName();
                    away = big10EastTeams.get(i).getName();
                }
                else {
                    home = big10EastTeams.get(i).getName();
                    away = big10EastTeams.get(j).getName();
                }
                gamesDAO.insert(new Game(home, away, wk, 0, 0));

                if (wk % 2 == 0) {
                    home = big10WestTeams.get(j).getName();
                    away = big10WestTeams.get(i).getName();
                }
                else {
                    home = big10WestTeams.get(i).getName();
                    away = big10WestTeams.get(j).getName();
                }
                gamesDAO.insert(new Game(home, away, wk, 0, 0));

                i++;
                j--;
            }
            gamesDAO.insert(new Game(big10EastTeams.get(i).getName(), "BYE", wk, 0,0));
            gamesDAO.insert(new Game(big10WestTeams.get(i).getName(), "BYE", wk, 0,0));

            Collections.rotate(big10EastTeams, 1);
            Collections.rotate(big10WestTeams, 1);
        }
    }

    public void pac12GameGeneration() {
        List<Team> pac12NorthTeams = teamsDAO.getTeamsFromConferenceDivision("Pac-12", "North");
        List<Team> pac12SouthTeams = teamsDAO.getTeamsFromConferenceDivision("Pac-12", "South");

        Collections.shuffle(pac12NorthTeams);
        Collections.shuffle(pac12SouthTeams);

        //generate interdivisional games
        for(int i = 0; i<pac12NorthTeams.size(); i++) {
            gamesDAO.insert(new Game(pac12NorthTeams.get(i).getName(), pac12SouthTeams.get(i).getName(), 4,
                    0, 0));
        }
        Collections.rotate(pac12NorthTeams,1);

        //generate interdivisional games
        for(int i = 0; i<pac12NorthTeams.size(); i++) {
            gamesDAO.insert(new Game(pac12SouthTeams.get(i).getName(), pac12NorthTeams.get(i).getName(), 5,
                    0, 0));
        }
        Collections.rotate(pac12NorthTeams,1);

        //generate interdivisional games
        for(int i = 0; i<pac12NorthTeams.size(); i++) {
            gamesDAO.insert(new Game(pac12SouthTeams.get(i).getName(), pac12NorthTeams.get(i).getName(), 12,
                    0, 0));
        }
        Collections.rotate(pac12NorthTeams,1);

        //generate interdivisional games
        for(int i = 0; i<pac12NorthTeams.size(); i++) {
            gamesDAO.insert(new Game(pac12NorthTeams.get(i).getName(), pac12SouthTeams.get(i).getName(), 13,
                    0, 0));
        }

        Team finalNorthTeam = pac12NorthTeams.get(pac12NorthTeams.size() - 1);
        Team finalSouthTeam = pac12SouthTeams.get(pac12SouthTeams.size() - 1);

        String home, away;
        for(int wk = 6; wk<=11; wk++)
        {
            int i = 0;
            int j = pac12NorthTeams.size() - 1;

            while(i < j)
            {
                if(wk % 8 == 0)
                {
                    for(int k = 0; k < pac12NorthTeams.size(); k++) {
                        gamesDAO.insert(new Game(pac12SouthTeams.get(k).getName(), "BYE", wk, 0, 0));
                        gamesDAO.insert(new Game(pac12NorthTeams.get(k).getName(), "BYE", wk, 0,0));
                    }
                }
                else
                {
                    if (wk % 2 == 0)
                    {
                        home = pac12NorthTeams.get(j).getName();
                        away = pac12NorthTeams.get(i).getName();
                    }
                    else
                    {
                        home = pac12NorthTeams.get(i).getName();
                        away = pac12NorthTeams.get(j).getName();
                    }
                    gamesDAO.insert(new Game(home, away, wk, 0, 0));

                    if (wk % 2 == 0)
                    {
                        home = pac12SouthTeams.get(j).getName();
                        away = pac12SouthTeams.get(i).getName();
                    }
                    else
                    {
                        home = pac12SouthTeams.get(i).getName();
                        away = pac12SouthTeams.get(j).getName();
                    }
                    gamesDAO.insert(new Game(home, away, wk, 0, 0));
                }
                i++;
                j--;
            }

            if(wk % 8 != 0) {
                pac12NorthTeams.remove(pac12NorthTeams.size() - 1);
                pac12SouthTeams.remove(pac12SouthTeams.size() - 1);
                Collections.rotate(pac12NorthTeams, 1);
                Collections.rotate(pac12SouthTeams,1);
                pac12NorthTeams.add(finalNorthTeam);
                pac12SouthTeams.add(finalSouthTeam);
            }
        }
    }

    public void deleteGame(){
        stateDAO.deleteGame();
    }

    public void newGame(){
        stateDAO.insert(new State("", 0, 0, 0, 2018, 1, ""));
    }

}
