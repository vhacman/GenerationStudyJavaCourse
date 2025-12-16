package com.generation.modulo6.main;

import com.generation.library.Console;

public class MainHouse
{

	public static void main(String[] args)
	{
        House casa = new House();
        
        casa.address = "Via Verdi 100, Cassano";
        casa.area = 100;
        casa.spm = 1000;
        
        Console.print(casa.toString());
	}

}
