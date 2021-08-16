package sg.edu.rp.c346.id19048236.myapp;

import java.io.Serializable;

import androidx.annotation.NonNull;

public class Movie implements Serializable {

    private int id;
    private String title;
    private String actors;
    private int yearReleased;
    private int stars;

    public Movie(int id, String title, String actors, int yearReleased, int stars) {
        this.id = id;
        this.title = title;
        this.actors = actors;
        this.yearReleased = yearReleased;
        this.stars = stars;

    }

    public int getId() {
        return id;
    }

    public Movie setId(int id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Movie setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getActors() {
        return actors;
    }

    public Movie setActors(String actors) {
        this.actors = actors;
        return this;
    }

    public int getYearReleased() {
        return yearReleased;
    }

    public Movie setYearReleased(int yearReleased) {
        this.yearReleased = yearReleased;
        return this;
    }

    public int getStars() {
        return stars;
    }

    public Movie setStars(int stars) {
        this.stars = stars;
        return this;
    }

    @NonNull
    @Override
    public String toString() {
        String starsString = "";
        for (int i = 0; i < stars; i++) {
            starsString += "*";
        }
        return title + "\n" + actors + " - " + yearReleased + "\n" + starsString;

    }
}