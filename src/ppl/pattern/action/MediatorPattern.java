package ppl.pattern.action;

import java.util.HashMap;

/**
 * 中介者模式：
 * 例如复杂网结构，各个类都由中介类进行交互
 * @author Smith
 *
 */
public class MediatorPattern
{
	public static void main(String[] args)
	{
		President pd = new President();
		Department dp1 = new Development(pd);
		Department dp2 = new Financial(pd);
		dp1.selfWork();
		dp2.selfWork();
		dp1.outWork();
	}
}

interface Mediator
{
	void command(String dname);
	void register(String dname,Department dp);
}

interface Department
{
	void selfWork();
	void outWork();
}

class President implements Mediator
{
	HashMap<String,Department> map = new HashMap<>();
	@Override
	public void command(String dname)
	{
		map.get(dname).outWork();
	}
	@Override
	public void register(String dname, Department dp)
	{
		map.put(dname, dp);
	}
}

class Development implements Department
{
	Mediator m;
	String name = "dev";

	public Development(Mediator m)
	{
		this.m = m;
		m.register(name, this);
	}
	@Override
	public void selfWork()
	{
		System.out.println("写代码!");
	}
	@Override
	public void outWork()
	{
		System.out.println("要钱！");
		m.command("finan");
	}
}

class Financial implements Department
{
	Mediator m;
	String name = "finan";
	
	public Financial(Mediator m)
	{
		this.m = m;
		m.register(name, this);
	}

	@Override
	public void selfWork()
	{
		System.out.println("算钱!");
	}

	@Override
	public void outWork()
	{
		System.out.println("发钱!");
	}
}


