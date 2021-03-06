package nflproject.mobile.cs.fsu.edu.nflfootballcoach;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import nflproject.mobile.cs.fsu.edu.nflfootballcoach.DAOs.GamesDAO;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.DAOs.PlayersDAO;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.DAOs.StateDAO;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.DAOs.TeamsDAO;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.Database.AppDatabase;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.Database.DatabaseHelper;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.models.Game;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.models.Players;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.models.State;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.models.Team;

public class ScheduleFragment extends Fragment {

    AppDatabase database = AppDatabase.getInstance(getActivity());
    GamesDAO gamesDAO = database.getGamesDAO();
    StateDAO stateDAO = database.getStateDAO();
    TeamsDAO teamsDAO = database.getTeamsDAO();
    Button play;



    void resetWL()
    {
        List<Team> everyTeam = teamsDAO.getEveryTeam();
        for (int i = 0; i < everyTeam.size(); ++i)
        {
            Team individual = everyTeam.get(i);
            individual.setWins(0);
            individual.setLosses(0);
            individual.setConLosses(0);
            individual.setConWins(0);
            teamsDAO.update(individual);
        }
    }

    void getChampions()
    {
        List<Team> atlantic = teamsDAO.getTeamsFromConferenceDivision("ACC", "Atlantic");
        List<Team> coastal = teamsDAO.getTeamsFromConferenceDivision("ACC", "Coastal");
        List<Team> EastBigTen = teamsDAO.getTeamsFromConferenceDivision("Big Ten", "East");
        List<Team> WestBigTen = teamsDAO.getTeamsFromConferenceDivision("Big Ten", "West");
        List<Team> NorthPac= teamsDAO.getTeamsFromConferenceDivision("Pac-12", "North");
        List<Team> SouthPac = teamsDAO.getTeamsFromConferenceDivision("Pac-12", "South");
        List<Team> EastSEC = teamsDAO.getTeamsFromConferenceDivision("SEC", "East");
        List<Team> WestSEC = teamsDAO.getTeamsFromConferenceDivision("SEC", "West");

        gamesDAO.insert(new Game(bestInDivision(atlantic), bestInDivision(coastal), 14, 0, 0));
        gamesDAO.insert(new Game(bestInDivision(EastBigTen), bestInDivision(WestBigTen), 14, 0, 0));
        gamesDAO.insert(new Game(bestInDivision(NorthPac), bestInDivision(SouthPac), 14, 0, 0));
        gamesDAO.insert(new Game(bestInDivision(EastSEC), bestInDivision(WestSEC), 14, 0, 0));
    }

    String bestInDivision(List<Team> t){

        int maxIndex = 0, maxWins = 0;

        for (int i = 1; i < t.size(); i++)
        {
            if (t.get(i).getConWins() > maxWins) {
                maxWins = t.get(i).getConWins();
                maxIndex = i;
            }
        }

        int max2Index = 0, max2Wins = 0;
        for (int i = 1; i < t.size(); i++){
            if (t.get(i).getConWins() > max2Wins && i != maxIndex){
                max2Wins = t.get(i).getConWins();
                max2Index = i;
            }
        }

        if (maxWins == max2Wins)
        {
            List<Team> teamRanks = teamsDAO.getAllRankings();

            for (int i = 0; i < t.size(); i++)
            {
                if (teamRanks.get(i).getName().equals(t.get(maxIndex).getName()))
                    return t.get(maxIndex).getName();
                if (teamRanks.get(i).getName().equals(t.get(max2Index).getName()))
                    return t.get(max2Index).getName();
            }
        }

        return t.get(maxIndex).getName();
    }

    void playWeekGames()
    {
        //play all games
        List<State> temp = stateDAO.getPlayerTeam();

        String yourTeam = temp.get(0).getPlayerTeam();
        String difficulty = stateDAO.getDifficulty();
        if (stateDAO.getWeek() == 14)
            getChampions();
        else if (stateDAO.getWeek() == 15)
        {
            List<Team> top25 = teamsDAO.getRankings();
            List<String> thetop4 = new ArrayList<>();
            for (int i = 0; i < 4; ++i)
            {
                thetop4.add(top25.get(i).getName());
            }
            gamesDAO.insert(new Game(thetop4.get(0), thetop4.get(3), 15, 0, 0));
            gamesDAO.insert(new Game(thetop4.get(1), thetop4.get(2), 15, 0, 0));
        }
        List<Game> games = gamesDAO.getGamesOfWeek(temp.get(0).getWeek());
        String number1 = "";
        String number2 = "";
        String nationalChampion = "";
        int natWin = 0;
        int natLoss = 0;
        String nationalLoser = "";
        int natLoserWin = 0;
        int natLoserLoss = 0;
        int natWinnerScore = 0;
        int natLoserScore = 0;
        int tempcount = 0;
        for (int j = 0; j < games.size(); ++j) {
            if (!games.get(j).getAway().equals("BYE")) {
                Team homeTeam = teamsDAO.getTeamByName(games.get(j).getHome());
                Team awayTeam = teamsDAO.getTeamByName(games.get(j).getAway());

                //game generation
                Random seed = new Random();
                int drives = 18 + seed.nextInt(4) - 2;
                int homeOffense = homeTeam.getOffRating();
                int homeDefense = homeTeam.getDefRating();
                int awayOffense = awayTeam.getOffRating();
                int awayDefense = awayTeam.getDefRating();
                int homeScore = 0;
                int awayScore = 0;
                //home offense vs away defense
                for (int i = 0; i < drives / 2; ++i) {
                    int aTempMod = seed.nextInt(30) - 15;
                    int hTempMod = seed.nextInt(30) - 15 + 5;
                    int tempHO, tempAD;
                    tempHO = homeOffense + hTempMod;
                    tempAD = awayDefense + aTempMod;
                    if (difficulty.equals("hard") && homeTeam.getName().equals(yourTeam))
                        tempHO -= 3;
                    else if (difficulty.equals("hard") && awayTeam.getName().equals(yourTeam))
                        tempAD -= 3;
                    if (tempHO > tempAD + 5)
                        homeScore += 7;
                    else if (tempHO > tempAD)
                        homeScore += 3;
                }
                //home defense vs away offense
                for (int i = 0; i < drives / 2; ++i) {
                    int aTempMod = seed.nextInt(30) - 15;
                    int hTempMod = seed.nextInt(30) - 15 + 5;
                    int tempHD, tempAO;
                    tempHD = homeDefense + hTempMod;
                    tempAO = awayOffense + aTempMod;
                    if (difficulty.equals("hard") && homeTeam.getName().equals(yourTeam))
                        tempHD -= 5;
                    else if (difficulty.equals("hard") && awayTeam.getName().equals(yourTeam))
                        tempAO -= 5;
                    if (tempAO > tempHD + 5)
                        awayScore += 7;
                    else if (tempAO > tempHD)
                        awayScore += 3;
                }
                //determining
                if (homeScore >= awayScore) {
                    if(homeScore == awayScore)
                        homeScore += 3;
                    int win = homeTeam.getWins() + 1;
                    homeTeam.setWins(win);
                    int loss = awayTeam.getLosses() + 1;
                    awayTeam.setLosses(loss);
                    if (homeTeam.getConference().equals(awayTeam.getConference())) {
                        int cwin = homeTeam.getConWins() + 1;
                        homeTeam.setConWins(cwin);
                        int closs = awayTeam.getConLosses() + 1;
                        awayTeam.setConLosses(closs);
                    }
                    //update career win/loss
                    if (homeTeam.getName().equals(yourTeam)) {
                        int w = temp.get(0).getCareerWins() + 1;
                        temp.get(0).setCareerWins(w);
                        stateDAO.update(temp.get(0));
                        //Toast.makeText(getActivity(), yourTeam + " " + homeScore + " - " + awayScore + " ",
                          //      Toast.LENGTH_LONG).show();
                    }
                    if (awayTeam.getName().equals(yourTeam)) {
                        int l = temp.get(0).getCareerLosses() + 1;
                        temp.get(0).setCareerLosses(l);
                        stateDAO.update(temp.get(0));
                        //Toast.makeText(getActivity(), yourTeam + " " + homeScore + " - " + awayScore + " ",
                        //        Toast.LENGTH_LONG).show();
                    }
                } else {
                    int win = awayTeam.getWins() + 1;
                    awayTeam.setWins(win);
                    int loss = homeTeam.getLosses() + 1;
                    homeTeam.setLosses(loss);
                    if (homeTeam.getConference().equals(awayTeam.getConference())) {
                        int cwin = awayTeam.getConWins() + 1;
                        awayTeam.setConWins(cwin);
                        int closs = homeTeam.getConLosses() + 1;
                        homeTeam.setConLosses(closs);
                    }
                    //update career win/loss
                    if (homeTeam.getName().equals(yourTeam)) {
                        int l = temp.get(0).getCareerLosses() + 1;
                        temp.get(0).setCareerLosses(l);
                        stateDAO.update(temp.get(0));
                       // Toast.makeText(getActivity(), yourTeam + " " + homeScore + " - " + awayScore + " ",
                         //       Toast.LENGTH_LONG).show();
                    }
                    if (awayTeam.getName().equals(yourTeam)) {
                        int w = temp.get(0).getCareerWins() + 1;
                        temp.get(0).setCareerWins(w);
                        stateDAO.update(temp.get(0));
                       // Toast.makeText(getActivity(), yourTeam + " " + homeScore + " - " + awayScore + " ",
                         //       Toast.LENGTH_LONG).show();
                    }
                }
                games.get(j).setHomeScore(homeScore);
                games.get(j).setAwayScore(awayScore);
                gamesDAO.update(games.get(j));
                //New team rankings
                int newVotesHome = 0;
                int newVotesAway = 0;
                if (temp.get(0).getWeek() == 16)
                {
                    if (homeScore >= awayScore)
                    {
                        nationalChampion = games.get(j).getHome();
                        natWin = teamsDAO.getTeamByName(games.get(j).getHome()).getWins();
                        natLoss = teamsDAO.getTeamByName(games.get(j).getHome()).getLosses();
                        nationalLoser = games.get(j).getAway();
                        natLoserWin = teamsDAO.getTeamByName(games.get(j).getAway()).getWins();
                        natLoserLoss = teamsDAO.getTeamByName(games.get(j).getAway()).getLosses();
                        natWinnerScore = homeScore;
                        natLoserScore = awayScore;
                    }
                    else {
                        nationalChampion = games.get(j).getAway();
                        natWin = teamsDAO.getTeamByName(games.get(j).getAway()).getWins();
                        natLoss = teamsDAO.getTeamByName(games.get(j).getAway()).getLosses();
                        nationalLoser = games.get(j).getHome();
                        natLoserWin = teamsDAO.getTeamByName(games.get(j).getHome()).getWins();
                        natLoserLoss = teamsDAO.getTeamByName(games.get(j).getHome()).getLosses();
                        natWinnerScore = awayScore;
                        natLoserScore = homeScore;
                    }
                }
                if (temp.get(0).getWeek() == 15 && tempcount == 0)
                {
                    if (homeScore >= awayScore)
                    {
                        number1 = games.get(j).getHome();
                        tempcount++;
                    }
                    else {
                        number1 = games.get(j).getAway();
                        tempcount++;
                    }
                }
                else if (temp.get(0).getWeek() == 15 && tempcount == 1)
                {
                    if (homeScore >= awayScore)
                    {
                        number2 = games.get(j).getHome();
                        tempcount++;
                    }
                    else {
                        number2 = games.get(j).getAway();
                        tempcount++;
                    }
                }
                if (homeTeam.getRankingVotes() <= awayTeam.getRankingVotes()) {
                    if (homeScore >= awayScore) {
                        newVotesHome = (homeScore - awayScore) * 2 + 40;
                        newVotesAway = -((homeScore - awayScore) * 2 + 40 + 100);
                    } else {
                        newVotesHome = -((homeScore - awayScore) * 2 + 100);
                        newVotesAway = (homeScore - awayScore) * 2;
                    }
                } else {
                    if (homeScore >= awayScore) {
                        newVotesHome = (homeScore - awayScore) * 2;
                        newVotesAway = -((homeScore - awayScore) * 2 + 50);
                    } else {
                        newVotesHome = -((homeScore - awayScore) * 2 + 40 + 100);
                        newVotesAway = (homeScore - awayScore) * 2 + 100;
                    }
                }
                int updateVotesHome = homeTeam.getRankingVotes() + newVotesHome;
                int updateVotesAway = awayTeam.getRankingVotes() + newVotesAway;
                if (updateVotesAway < 0)
                    updateVotesAway = 0;
                if (updateVotesHome < 0)
                    updateVotesHome = 0;
                homeTeam.setRankingVotes(updateVotesHome);
                awayTeam.setRankingVotes(updateVotesAway);

                /*if (homeTeam.getName().equals(yourTeam))
                    Toast.makeText(getActivity(), "New Votes: " + homeTeam.getRankingVotes(),
                            Toast.LENGTH_LONG).show();
                else if (homeTeam.getName().equals(yourTeam))
                    Toast.makeText(getActivity(), "New Votes: " + homeTeam.getRankingVotes(),
                            Toast.LENGTH_LONG).show();*/

                teamsDAO.update(homeTeam);
                teamsDAO.update(awayTeam);

            }
        }
        if (temp.get(0).getWeek() == 15)
        {
            gamesDAO.insert(new Game(number1, number2, 16, 0, 0));
        }
        int theWeek = temp.get(0).getWeek() + 1;
        temp.get(0).setWeek(theWeek);
        stateDAO.update(temp.get(0));
        if (temp.get(0).getWeek() == 17)
        {
            DialogInterface.OnClickListener dialogListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case DialogInterface.BUTTON_NEUTRAL:
                            DatabaseHelper dh = new DatabaseHelper(getActivity(), database);
                            dh.buildNewSeason();
                            FragmentTransaction ft = getFragmentManager().beginTransaction();
                            ft.detach(ScheduleFragment.this).attach(ScheduleFragment.this).commit();
                            dialog.dismiss();
                            break;
                    }
                }
            };
            List<Team> teamRanks = teamsDAO.getAllRankings();
            int myTeamRank = 0;
            for (int i = 0; i < teamRanks.size(); ++i)
            {
                if (teamRanks.get(i).getName().equals(stateDAO.getPlayerTeamString()))
                    myTeamRank = i + 1;
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Season Summary")
                    .setMessage(((nationalChampion + " (" + natWin + "-" + natLoss + ") won the National Championship, beating " +
                                    nationalLoser + " (" + natLoserWin + "-" + natLoserLoss + ") " + natWinnerScore + "-" +
                                    natLoserScore + ".\n\n" +
                            "Your team, " + stateDAO.getPlayerTeamString() + ", finished the season ranked #"
                            + Integer.toString(myTeamRank) + " with " + teamsDAO.getTeamByName(stateDAO.getPlayerTeamString()).getWins() +
                            " wins and " + teamsDAO.getTeamByName(stateDAO.getPlayerTeamString()).getLosses() + " losses.")))
                    .setNeutralButton("Dismiss", dialogListener).show();
        }
    }

    //Write this and pass res to the function: Resources res = getResources();
    void leavingPlayers(View testView, Resources res)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Players Graduating");

        PlayersDAO playersDAO = database.getPlayersDAO();
        String yourTeam = stateDAO.getPlayerTeamString();
        Team homeTeam = teamsDAO.getTeamByName(yourTeam);
        int oldORating = homeTeam.getOffRating();
        int oldDRating = homeTeam.getDefRating();
        int wins = homeTeam.getWins();
        int prestige = 0;
        if (wins < 3)
            prestige -= 3;
        else if (wins < 6)
            prestige -= 1;
        else if (wins < 9)
            prestige += 1;
        else  if (wins < 11)
            prestige += 3;
        int totalRating = ((oldORating + oldDRating)/2) + prestige;

        List<Players> allPlayers = playersDAO.getPlayers();
        Vector<Integer> ids = new Vector<>();
        int counter = 0;
        Random seed = new Random();
        String[] firstNames = res.getStringArray(R.array.firstNames);
        String[] lastNames = res.getStringArray(R.array.lastNames);
        Vector<String> firstname = new Vector<>();
        Vector<String> position = new Vector<>();
        Vector<String> lastname = new Vector<>();


        final ArrayAdapter<String> newPlayers = new ArrayAdapter<String>(getActivity(), android.R.layout.select_dialog_singlechoice);
        final ArrayAdapter<String> graduates = new ArrayAdapter<String>(getActivity(), android.R.layout.select_dialog_singlechoice);
        for (int i = 0; i < 48; ++i)
        {
            if (allPlayers.get(i).getYear().equals("SR"))
            {
                Players player = allPlayers.get(i);
                graduates.add(player.getFirstName() + " " + player.getLastName() + ", " + player.getPosition() + ", " + player.getYear() + ", rating: " + player.getRating());
                ids.add(i);
                ++counter;
                position.add(player.getPosition());
                int playerFirstName = seed.nextInt(firstNames.length);
                firstname.add(firstNames[playerFirstName]);
                int playerLastName = seed.nextInt(lastNames.length);
                lastname.add(lastNames[playerLastName]);
                playersDAO.deletePlayer(i);
            }
        }

        List<Players> remainingPlayers = playersDAO.getPlayers();
        final ArrayAdapter<String> playersStaying = new ArrayAdapter<String>(getActivity(), android.R.layout.select_dialog_singlechoice);

        for (int i = 0; i < 48 - counter; ++i)
        {
            Players player = remainingPlayers.get(i);
            int skillIncrease = seed.nextInt(4) + 1;
            if (player.getYear().equals("FR"))
            {
                player.setYear("SO");
            }
            else if (player.getYear().equals("SO"))
            {
                player.setYear("JR");
            }
            else
            {
                player.setYear("SR");
            }
            int temp = player.getRating() + skillIncrease;
            if (temp >= 100)
                temp = 99;
            player.setRating(temp);
            playersDAO.update(player);
            playersStaying.add(player.getFirstName() + " " + player.getLastName() + ", " + player.getPosition() + ", " + player.getYear() + ", rating: " + player.getRating() + " (+" + skillIncrease + ")");
        }

        for (int i = 0; i < firstname.size(); ++i)
        {
            int rating = seed.nextInt(totalRating + prestige + 15 - (totalRating + prestige - 15) + 1) + totalRating + prestige - 15;
            if (rating >= 100)
                rating = 99;
            playersDAO.insert(new Players(ids.get(i), rating, firstname.get(i), lastname.get(i), position.get(i), "FR"));
            newPlayers.add(firstname.get(i) + " " + lastname.get(i) + ", " + position.get(i) + ", FR, rating: " + Integer.toString(rating));
        }


        String[] why = new String[graduates.getCount()];
        final String[] why2 = new String[graduates.getCount()];
        final String[] why3 = new String[playersStaying.getCount()];
        for (int i = 0; i < graduates.getCount(); ++i)
        {
            why[i] = graduates.getItem(i);
            why2[i] = newPlayers.getItem(i);
        }
        for (int i = 0; i < playersStaying.getCount(); ++i)
            why3[i] = playersStaying.getItem(i);

        //update team talent based on new players
        List<Players> finalPlayers = playersDAO.getPlayers();
        int newORating = 0;
        int newDRating = 0;
        for (int i = 0; i < 26; ++i)
            newORating += finalPlayers.get(i).getRating();
        for (int i = 26; i < 48; ++i)
            newDRating += finalPlayers.get(i).getRating();
        newORating = newORating/26;
        newDRating = newDRating/22;
        homeTeam.setOffRating(newORating);
        homeTeam.setDefRating(newDRating);
        teamsDAO.update(homeTeam);

        builder.setItems(why, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialog, int which) {
                AlertDialog.Builder builderInner = new AlertDialog.Builder(getActivity());
                builderInner.setTitle("New Players");
                builderInner.setItems(why2, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        AlertDialog.Builder builderInner2 = new AlertDialog.Builder(getActivity());
                        builderInner2.setTitle("Player Improvements");
                        builderInner2.setItems(why3, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialog.dismiss();
                            }
                        });
                        builderInner2.show();
                    }
                });
                builderInner.show();
            }
        });
        builder.show();
    }

    String playerTeam;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);
        TextView week = view.findViewById(R.id.weekDisplay);
        State s = stateDAO.getState();
        int tempWeek = s.getWeek();
        int tempYear = s.getYear();
        String temp = Integer.toString(tempYear) + " Season, Week " + Integer.toString(tempWeek);
        week.setText(temp);

        ListView schedule = (ListView) view.findViewById(R.id.scheduleList);
        play = view.findViewById(R.id.play_week_btn);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playWeekGames();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.detach(ScheduleFragment.this).attach(ScheduleFragment.this).commit();
                if (stateDAO.getWeek() == 17)
                {
                    Resources res = getResources();
                    leavingPlayers(view, res);
                }
                /*new Thread() {
                    @Override
                    public void run() {
                        playWeekGames();
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.detach(ScheduleFragment.this).attach(ScheduleFragment.this).commit();
                        try {

                            // code runs in a thread
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                }
                            });
                        } catch (final Exception ex) {
                            Log.i("---","Exception in thread");
                        }
                    }
                }.start();*/
            }
        });

        playerTeam = stateDAO.getPlayerTeamString();               //get team player is currently playing as
        List<Game> gameList = gamesDAO.getGamesOfTeam(playerTeam); //get games of team player is playing as

        //populate schedule rows
        ArrayList<scheduleRow> scheduleArr = new ArrayList<scheduleRow>(gameList.size());
        for(int i = 0; i<gameList.size(); i++)
        {
            scheduleRow entry = new scheduleRow("","");

            boolean home = true;
            if(gameList.get(i).getAway().equals("BYE"))
                entry.setOpponent("BYE");
            else if (playerTeam.equals(gameList.get(i).getHome()))
                entry.setOpponent("vs " + database.getTeamsDAO().getTeamByName(gameList.get(i).getAway()).getAbbreviation());
            else {
                entry.setOpponent("at " + database.getTeamsDAO().getTeamByName(gameList.get(i).getHome()).getAbbreviation());
                home = false;
            }

            if (gameList.get(i).getHomeScore() > gameList.get(i).getAwayScore() && home ||
                    gameList.get(i).getHomeScore() < gameList.get(i).getAwayScore() && !home) {
                entry.setResult("W " + gameList.get(i).getHomeScore() + "-" + gameList.get(i).getAwayScore());
            }
            else if(gameList.get(i).getHomeScore() < gameList.get(i).getAwayScore() && !home) {
                entry.setResult("W " + gameList.get(i).getAwayScore() + "-" + gameList.get(i).getHomeScore());
            }
            else if(gameList.get(i).getHomeScore() > gameList.get(i).getAwayScore() && !home ||
                    gameList.get(i).getHomeScore() < gameList.get(i).getAwayScore() && home) {
                entry.setResult("L " + gameList.get(i).getHomeScore() + "-" + gameList.get(i).getAwayScore());
            }
            else if(gameList.get(i).getHomeScore() < gameList.get(i).getAwayScore() && home){
                entry.setResult("L " + gameList.get(i).getAwayScore() + "-" + gameList.get(i).getHomeScore());
            }
            else
                entry.setResult("");

            scheduleArr.add(entry);
        }

        ScheduleListAdapter adapter = new ScheduleListAdapter(getActivity(), R.layout.schedule_adapter_view_layout, scheduleArr);
        schedule.setAdapter(adapter);


        return view;
    }
}
