package ppl.pattern.struct;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理模式：(重要!)
 * 代理用来完成前期和后期工作，而核心类仅专注完成自己的工作
 * 真实角色：具体的实现类
 * 抽象角色：代理角色面向的类或接口
 * 代理角色：面向抽象角色完成前期和后期工作
 * @author Smith
 *
 */
public class ProxyPattern
{
	public static void Test01()
	{
		//1.静态代理
		PPL ppl = new PPL();
		ProxyStar proxy = new ProxyStar(ppl);
		proxy.work();
	}
	public static void Test02()
	{
		//2.动态代理
		PPL ppl = new PPL();
		StarHandler handler = new StarHandler(ppl);
		Star proxy = (Star) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[] {Star.class}, handler);
		proxy.sing();
	}
	public static void main(String[] args)
	{
		Test02();
	}
}

/**
 * 1.静态代理
 * @author Smith
 *
 */
//抽象角色
interface Star
{
	void sing();
}
//真实角色
class PPL implements Star
{
	@Override
	public void sing()
	{
		System.out.println("ppl sing!");
	}
}
//代理角色
class ProxyStar implements Star
{
	private Star star;
	public ProxyStar(Star star)
	{
		this.star = star;
	}
	public void work()
	{
		step1();
		step2();
		sing();
		step3();
		step4();
	}
	private void step1()
	{
		System.out.println("step1");
	}
	private void step2()
	{
		System.out.println("step2");
	}
	public void sing()
	{
		star.sing();
	}
	private void step3()
	{
		System.out.println("step3");
	}
	private void step4()
	{
		System.out.println("step4");
	}
}

/**
 * 2.动态代理
 * @author Smith
 *
 */
class StarHandler implements InvocationHandler
{
	private Star star;
	public StarHandler(Star star)
	{
		this.star = star;
	}

	private void step1()
	{
		System.out.println("step1");
	}
	private void step2()
	{
		System.out.println("step2");
	}
	private void step3()
	{
		System.out.println("step3");
	}
	private void step4()
	{
		System.out.println("step4");
	}
	@Override
	public Object invoke(Object obj, Method method, Object[] args) throws Throwable
	{
		step1();
		step2();
		if (method.getName().equals("sing"))
		{
			star.sing();
		}
		step3();
		step4();
		return null;
	}
}






