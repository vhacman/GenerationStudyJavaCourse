package com.generation.library.view;

import com.generation.library.Console;

public class LambdaDemo 
{

	public static void main(String[] args) 
	{
	
		Nerd domenico = 
			new Nerd("Domenico", 90001, "Non lo sa");
		
		// ho: IMPLEMENTATO L'INTERFACCIA USANDO UNA CLASSE
		// ANONIMA E PRIVA CHE ESISTE SOLO IN QUESTO MAIN
		// HO DOVUTO IMPLEMENTARE I METODI ASTRATTI
		// CHE IN QUESTO CASO ERANO SOLO UNO (render(Nerd))
		// appena implemntata la ho anche istanziata
		// creando un oggetto unico per questa classe unica
		//
		EntityView<Nerd> basicView = 
			new EntityView<Nerd>()
			{

				@Override
				public String render(Nerd x) 
				{
					return x.name+" "+x.level+" "+x.weakness;
				};
			};
		//						            input -> output
		//									parametri -> return
		EntityView<Nerd> basicViewLambda = x -> x.name+" "+x.level+" "+x.weakness;
		// parametri di rendere -> return di render )
		// 32 equivale a 22-30
		
		Console.print(basicView.render(domenico));	
		Console.print(basicViewLambda.render(domenico));	
		
			
	}

}
