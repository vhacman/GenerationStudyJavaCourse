package com.generation.gbb.cache;

import java.util.ArrayList;
import java.util.List;

import com.generation.library.Entity;

public class PartialCache<X extends Entity>
{
    int             maxSize;
    List<X>         content = new ArrayList<X>();

    public PartialCache(int size)
    {
        this.maxSize = size;
    }


    public boolean contains(X x)
    {
    	return content.contains(x);
    }
    
    public void	removeElement(X x)
    {
    	content.remove(x);
    }
    public void addElement(X x)
    {
        if(contains(x))
            return;
        content.add(x);

        if(content.size() > maxSize)
            content.remove(0);
    }
    public X findById(int id)
    {
        for(X x : content)
            if(x.getId() == id)
                return x;
                
        return null;
    }

    public int getMaxSize()                 { return maxSize;           }
    public void setMaxSize(int maxSize)     { this.maxSize = maxSize;   }
    public List<X> getContent()             { return content;           }
    public void setContent(List<X> content) { this.content = content;   }
}
