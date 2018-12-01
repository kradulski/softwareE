package nflproject.mobile.cs.fsu.edu.nflfootballcoach.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "players")
public class Players {
    @PrimaryKey
    private int id;

    @ColumnInfo
    private int rating;

    @ColumnInfo
    private String firstName;

    @ColumnInfo
    private String lastName;

    @ColumnInfo
    private String position;

    @ColumnInfo
    private String year;

    public Players(int id, int rating, String firstName, String lastName, String position, String year)
    {
        this.id = id;
        this.rating = rating;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.year = year;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public String getYear() { return year; }
    public void setYear(String year) { this.year = year; }
}