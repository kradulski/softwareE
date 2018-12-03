package nflproject.mobile.cs.fsu.edu.nflfootballcoach.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

//Schema for the Teams table

@Entity(tableName = "oldTeams")
public class oldTeam {

    @PrimaryKey
    @NonNull
    private String name;

    @ColumnInfo
    private String abbreviation;

    @ColumnInfo
    private String conference;

    @ColumnInfo
    private String division;

    @ColumnInfo
    private int wins;

    @ColumnInfo
    private int losses;

    @ColumnInfo
    private int conWins;

    @ColumnInfo
    private int conLosses;

    @ColumnInfo
    private int offRating;

    @ColumnInfo
    private int defRating;

    @ColumnInfo
    private int rankingVotes;

    public oldTeam(String name, String abbreviation,String conference, String division, int wins, int losses, int conWins, int conLosses,
                int offRating, int defRating, int rankingVotes)
    {
        this.name = name;
        this.abbreviation = abbreviation;
        this.conference = conference;
        this.division = division;
        this.wins = wins;
        this.losses = losses;
        this.conWins = conWins;
        this.conLosses = conLosses;
        this. offRating = offRating;
        this.defRating = defRating;
        this.rankingVotes = rankingVotes;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAbbreviation() { return abbreviation; }
    public void setAbbreviation(String abbreviation) { this.abbreviation = abbreviation; }

    public String getConference() { return conference; }
    public void setConference(String conference) { this.conference = conference; }

    public String getDivision() { return division; }
    public void setDivision(String division) { this.division = division; }

    public int getWins() { return wins; }
    public void setWins(int wins) { this.wins = wins; }

    public int getLosses() { return losses; }
    public void setLosses(int losses) {this.losses = losses; }

    public int getConWins() { return conWins; }
    public void setConWins(int conWins) { this.conWins = conWins; }

    public int getConLosses() { return conLosses; }
    public void setConLosses(int conLosses) { this.conLosses = conLosses; }

    public int getOffRating() { return offRating; }
    public void setOffRating(int offRating) { this.offRating = offRating; }

    public int getDefRating() { return defRating; }
    public void setDefRating(int defRating) { this.defRating = defRating; }

    public int getRankingVotes() { return rankingVotes; }
    public void setRankingVotes(int rankingVotes) { this.rankingVotes = rankingVotes; }

}