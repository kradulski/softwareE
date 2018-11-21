package nflproject.mobile.cs.fsu.edu.nflfootballcoach.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

//Schema for the Teams table

@Entity
public class Teams {

    @PrimaryKey
    @NonNull
    private String name;

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
    private double offRating;

    @ColumnInfo
    private double defRating;

    @ColumnInfo
    private int rankingVotes;

    public String getName()
    {
        return name;
    }
    public void setName(String name) { this.name = name; }

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

    public double getOffRating() { return offRating; }
    public void setOffRating(double offRating) { this.offRating = offRating; }

    public double getDefRating() { return defRating; }
    public void setDefRating(double defRating) { this.defRating = defRating; }

    public int getRankingVotes() { return rankingVotes; }
    public void setRankingVotes(int rankingVotes) { this.rankingVotes = rankingVotes; }

}
