package com.generation.gbb.model.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.generation.library.Entity;

public class Trip extends Entity
{
    protected String     city;
    protected LocalDate  date;
    protected String     review;
    protected int        score;


    /**
     * Empty constructor.
     */
    public Trip() {}

    /**
     * Constructor with all fields.
     *
     * @param city   Name of the city
     * @param date   Trip date
     * @param review Trip review
     * @param score  Trip score (0-10)
     */
    public Trip(String city, LocalDate date, String review, int score)
    {
        this.city   = city;
        this.date   = date;
        this.review = review;
        this.score  = score;
    }

    /**
     * Constructor with all fields including ID.
     *
     * @param id     Trip identifier
     * @param city   Name of the city
     * @param date   Trip date
     * @param review Trip review
     * @param score  Trip score (0-10)
     */
    public Trip(int id, String city, LocalDate date, String review, int score)
    {
        this.id     = id;
        this.city   = city;
        this.date   = date;
        this.review = review;
        this.score  = score;
    }


    /**
     * Validates the fields of the Trip entity.
     *
     * @return List of validation error messages
     */
    @Override
    public List<String> getErrors()
    {
        List<String> errors = new ArrayList<>();

        if(isMissing(city))
            errors.add("City name is required");

        if(date == null)
            errors.add("Date is required");
        else
        {
            LocalDate hundredYearsAgo = LocalDate.now().minusYears(100);
            if(date.isBefore(hundredYearsAgo))
                errors.add("Trip date cannot be more than 100 years ago");
            else if(date.isAfter(LocalDate.now()))
                errors.add("Trip date cannot be in the future");
        }

        if(isMissing(review))
            errors.add("Review is required");

        if(score < 0 || score > 10)
            errors.add("Score must be between 0 and 10");

        return errors;
    }


    /**
     * Returns the city name.
     *
     * @return city
     */
    public String getCity()          { return city; }

    /**
     * Returns the trip date.
     *
     * @return date
     */
    public LocalDate getDate()       { return date; }

    /**
     * Returns the trip review.
     *
     * @return review
     */
    public String getReview()        { return review; }

    /**
     * Returns the trip score.
     *
     * @return score
     */
    public int getScore()            { return score; }


    /**
     * Sets the city name.
     *
     * @param city City name
     */
    public void setCity(String city)             { this.city = city; }

    /**
     * Sets the trip date.
     *
     * @param date Trip date
     */
    public void setDate(LocalDate date)          { this.date = date; }

    /**
     * Sets the trip date from a String.
     *
     * @param date Date as string (ISO format)
     */
    public void setDate(String date)             { this.date = LocalDate.parse(date); }

    /**
     * Sets the trip review.
     *
     * @param review Trip review
     */
    public void setReview(String review)         { this.review = review; }

    /**
     * Sets the trip score.
     *
     * @param score Trip score (0-10)
     */
    public void setScore(int score)              { this.score = score; }
}
