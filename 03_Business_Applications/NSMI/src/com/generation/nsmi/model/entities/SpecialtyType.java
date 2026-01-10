package com.generation.nsmi.model.entities;

public enum SpecialtyType
{
	CARDIOLOGY(60000, 150000),
	NEUROLOGY(65000, 160000),
	PEDIATRICS(50000, 120000),
	ORTHOPEDICS(70000, 180000),
	PSYCHIATRY(55000, 130000),
	ONCOLOGY(75000, 170000),
	SURGERY(80000, 200000);

	private final double minSalary;
	private final double maxSalary;

	SpecialtyType(double minSalary, double maxSalary)
	{
		this.minSalary = minSalary;
		this.maxSalary = maxSalary;
	}

	public double getMinSalary()
	{
		return minSalary;
	}

	public double getMaxSalary()
	{
		return maxSalary;
	}

	public boolean isValidSalary(double salary)
	{
		return salary >= minSalary && salary <= maxSalary;
	}

	@Override
	public String toString()
	{
		return String.format("%s (€%.2f - €%.2f)", name(), minSalary, maxSalary);
	}
}
