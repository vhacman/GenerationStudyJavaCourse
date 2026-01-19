package com.generation.library.model.entities;

import java.util.ArrayList;
import java.util.List;

import com.generation.library.Entity;

public class Pet extends Entity
{
    String  name;
    String  species;


    public Pet() {}
    public Pet(String name, String species)
    {
        super();
        this.name    = name;
        this.species = species;
    }


    @Override
    public List<String> getErrors()
    {
        List<String> res = new ArrayList<String>();
        
        if(isMissing(name))
            res.add("Missing name");
            
        if(isMissing(species))
            res.add("Missing species");
            
        return res;
    }

    @Override
	public String toString()
	{
		return "Pet [name=" + name + ", species=" + species + "]";
	}
    
    
	public String 	getName()                { return name; }
    public void 	setName(String name)       { this.name = name; }
    
    public String 	getSpecies()             { return species; }
    public void 	setSpecies(String species) { this.species = species; }

}
