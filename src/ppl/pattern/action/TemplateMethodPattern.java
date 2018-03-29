package ppl.pattern.action;

/**
 * 模板模式：
 * 一方只关注流程，另一方则实现具体步骤
 * 比如算法实现，父类只设计骨架，
 * 而具体实现由子类实现
 * @author Smith
 *
 */
public class TemplateMethodPattern
{
	public static void main(String[] args)
	{
		//一般使用方式
		TemplateMethod t = new Process01();
		t.process();
		
		//匿名内部类使用方式
		TemplateMethod t2 = new TemplateMethod()	
		{	
			@Override
			public void step1()
			{
				System.out.println("NoName[1]");
			}	
			@Override
			public void step2()
			{
				System.out.println("NoName[2]");
			}
			@Override
			public void step3()
			{
				System.out.println("NoName[3]");
			}
		};
		
	}
}

abstract class TemplateMethod
{
	public abstract void step1();
	public abstract void step2();
	public abstract void step3();
	//模板方法(一般骨架不能被重写)
	//也叫钩子方法
	public final void process()
	{
		this.step1();
		this.step2();
		this.step3();
	}	
}

class Process01 extends TemplateMethod
{
	@Override
	public void step1()
	{
		System.out.println("Process01[1]");
	}
	@Override
	public void step2()
	{
		System.out.println("Process01[2]");
	}
	@Override
	public void step3()
	{
		System.out.println("Process01[3]");
	}
}