package ppl.pattern.action;

/**
 * 备忘录模式：
 * 保存草稿、恢复文档、撤销操作等
 * @author Smith
 *
 */
public class MementoPattern
{
	public static void main(String[] args)
	{
		CareTaker taker = new CareTaker();
		Emp emp = new Emp("ppl", 25, 30000);
		System.out.println(emp);
		taker.setEm(emp.memento());
		emp.setSalary(40000);
		System.out.println(emp);
		emp.recovery(taker.getEm());
		System.out.println(emp);
	}
}

//源发器类
class Emp
{
	private String name;
	private int age;
	private double salary;
	
	public Emp(String name, int age, double salary)
	{
		super();
		this.name = name;
		this.age = age;
		this.salary = salary;
	}

	//备忘操作
	public EmpMemento memento()
	{
		return new EmpMemento(this);
	}
	
	public void recovery(EmpMemento em)
	{
		this.name = em.getName();
		this.age = em.getAge();
		this.salary = em.getSalary();
	}
	
	@Override
	public String toString()
	{
		return "["+name+"]"+"["+age+"]"+"["+salary+"]";
	}

	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public int getAge()
	{
		return age;
	}
	public void setAge(int age)
	{
		this.age = age;
	}
	public double getSalary()
	{
		return salary;
	}
	public void setSalary(double salary)
	{
		this.salary = salary;
	}
}

//备忘录类
class EmpMemento
{
	private String name;
	private int age;
	private double salary;
	
	public EmpMemento(Emp e)
	{
		this.name = e.getName();
		this.age = e.getAge();
		this.salary = e.getSalary();
	}
	
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public int getAge()
	{
		return age;
	}
	public void setAge(int age)
	{
		this.age = age;
	}
	public double getSalary()
	{
		return salary;
	}
	public void setSalary(double salary)
	{
		this.salary = salary;
	}
}

//负责人类
class CareTaker
{
	//还可以用容器来实现多步回退
	private EmpMemento em;
	
	public EmpMemento getEm()
	{
		return em;
	}

	public void setEm(EmpMemento em)
	{
		this.em = em;
	}
}
