package com.generation.gbb.model.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.generation.library.Entity;

public class Trip extends Entity
{
    protected String        city;
    protected LocalDate     date;
    protected String        review;
    protected int           score;


    public Trip() {}

    public Trip(String city, LocalDate date, String review, int score)
    {
        this.city = city;
        this.date = date;
        this.review = review;
        this.score = score;
    }


    @Override
    public List<String> getErrors()
    {
        List<String> errors = new ArrayList<>();

        if(isMissing(city)) 
            errors.add("Nome della città obbligatorio");
        
        if(date == null)
            errors.add("La data deve essere presente");
        else if(date.isBefore(LocalDate.now())) 
            errors.add("La data non può essere precedente alla data attuale");
        
        if(isMissing(review))
            errors.add("Review deve essere presente");
        
        if(score < 0 || score > 10)
            errors.add("Score deve essere compreso tra 0 e 10");
        
        return errors;
    }


    public String getCity()           		{ return city; }
    public void setCity(String city)  		{ this.city = city; }

    public LocalDate getDate()             	{ return date; }
    public void setDate(LocalDate date)    	{ this.date = date; }

    public String getReview()              	{ return review; }
    public void setReview(String review)   	{ this.review = review; }

    public int getScore()           		{ return score; }
    public void setScore(int score) 		{ this.score = score; }
}
