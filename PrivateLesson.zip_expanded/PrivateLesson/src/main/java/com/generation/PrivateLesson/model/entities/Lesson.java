package com.generation.PrivateLesson.model.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import com.generation.library.Entity;

public class Lesson extends Entity
{
	protected LocalDate		date;
	protected int			hour;
	protected Student		student;
	protected Teacher		teacher;
	protected int			price;

	public Lesson() {}

	public Lesson(LocalDate date, int hour, Student student, Teacher teacher, int price)
	{
		super();
		this.date    = date;
		this.hour    = hour;
		this.student = student;
		this.teacher = teacher;
		this.price   = price;
	}

	@Override
	public List<String> getErrors()
	{
		List<String> res = new ArrayList<>();

		if (date == null)         	res.add("Date is required");
		if (hour < 9 || hour > 18) 	res.add("Hour must be between 9 and 18");
		if (student == null)      	res.add("Student is required");
		if (teacher == null)      	res.add("Teacher is required");
		if (price < 0)           	res.add("Price must be positive");
		return res;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(date, hour, price, student, teacher);
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lesson other = (Lesson) obj;
		return Objects.equals(date, other.date) 		&&
				hour == other.hour 						&& 
				price == other.price					&& 
				Objects.equals(student, other.student) 	&&
				Objects.equals(teacher, other.teacher);
	}

	@Override
	public String toString()
	{
		return "Lesson [date=" + date + ", hour=" + hour + ", student=" + student + ", teacher=" + teacher + ", price=" + price + "]";
	}

	public LocalDate	getDate()					{ return date; 				}
	public int			getHour()					{ return hour; 				}
	public Student		getStudent()				{ return student; 			}
	public Teacher		getTeacher()				{ return teacher; 			}
	public int			getPrice()					{ return price; 			}

	public void			setDate(LocalDate date)		{ this.date = date; 		}
	public void			setDate(String date)		{ this.date = LocalDate.parse(date); 		}

	public void			setHour(int hour)			{ this.hour = hour; 		}
	public void			setStudent(Student student)	{ this.student = student; 	}
	public void			setTeacher(Teacher teacher)	{ this.teacher = teacher; 	}
	public void			setPrice(int price)			{ this.price = price; 		}
}
