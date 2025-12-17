package com.generation.rs.demo;

import com.generation.library.Console;
import com.generation.library.FileWriter;
import com.generation.rs.model.entities.Repair;
import com.generation.rs.view.RepairView;

public class DemoRepairView
{

	public static void main(String[] args)
	{
		RepairView viewPreview = new RepairView("template/repairPreView.txt");
		RepairView viewCLient = new RepairView("template/clientView.html");
		Repair r = new Repair();
		r.id = "1";
		r.client = "Ferdinando";
		r.fix = "Iphone con schermo rotto. Sostituito";
		r.materialPartsCost = 300;
		r.hour = 2;
		r.price = 350;
		r.phone = "3278149801";
		
		Console.print(viewPreview.render(r));
		FileWriter.writeTo("template/test.html", viewCLient.render(r));
	}
}
