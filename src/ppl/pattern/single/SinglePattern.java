package ppl.pattern.single;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.concurrent.CountDownLatch;

/**
 * 单例模式：只生成一个实例，减少系统性能开销，当一个对象
 * 占用资源比较多时，比如读取配置、产生其他依赖对象，则可
 * 产生单例对象，然后永驻内存。
 * 
 * 结论：
 * 通常建议使用静态内部类模式
 * 如果对象较小(允许提前加载)，并且考虑到破解，建议使用枚举式
 * @author Smith
 */
public class SinglePattern
{
	public static void Test01()
	{
		//基本测试
		Single01 s1 = Single01.getInstance();
		Single01 ss1 = Single01.getInstance();
		System.out.println(s1==ss1);

		Single02 s2 = Single02.getInstance();
		Single02 ss2 = Single02.getInstance();
		System.out.println(s2==ss2);
		
		Single04 s4 = Single04.getInstance();
		Single04 ss4 = Single04.getInstance();
		System.out.println(s4==ss4);
		
		Single05 s5 = Single05.getInstance();
		Single05 ss5 = Single05.getInstance();
		System.out.println(s5==ss5);
	}
	
	public static void Test02() throws Exception
	{
		//通过反射破解单例模式(不含枚举模式)
		Class<?> cla = Class.forName("ppl.pattern.single.Single01");
		Constructor<?> cons = cla.getDeclaredConstructor(null);
		cons.setAccessible(true);
		Single01 s1 = (Single01) cons.newInstance(null);
		Single01 ss1 = (Single01) cons.newInstance(null);
		System.out.println(s1==ss1);
	}
	
	public static void Test03() throws Exception
	{
		//通过反序列化破解单例模式(不含枚举模式)
		Single01 s1 = Single01.getInstance();
		String path = "G:/OneDrive/Study/Java/Project/Design-Pattern/file/single";
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
		oos.writeObject(s1);
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
		Single01 ss1 = (Single01)ois.readObject();
		System.out.println(s1 == ss1);
	}
	
	public static void Test04() throws Exception
	{
		//效率测试
		long start = System.currentTimeMillis();
		int threadNum = 10;
		CountDownLatch counter = new CountDownLatch(threadNum);
		for(int i=0; i<threadNum; i++)
		{
			new Thread(new Runnable()
			{
				@Override
				public void run()
				{
					for(int i=0; i<1000000; i++)
					{
						Single01 s = Single01.getInstance(); //饿汉式:12ms
						//Single02 s = Single02.getInstance(); //懒汉式:199ms
						//Single03 s = Single03.getInstance(); //双锁式:27ms，不建议使用
						//Single04 s = Single04.getInstance(); //静态内部类模式:21ms
						//Single05 s = Single05.getInstance(); //枚举式:23ms
					}
					counter.countDown();
				}
			}).start();
		}
		counter.await();
		
		long end = System.currentTimeMillis();
		System.out.println("单例模式:"+(end-start)+"ms");
	}
	
	public static void main(String[] args) throws Exception
	{
		Test04();
	}
}

/**
 * 1.饿汉式
 * [1]创建private构造器
 * [2]创建private static对象，并初始化
 * [3]创建public方法，用于返回该对象
 * 
 * 优点：对象是在类加载阶段生成的，因此线程安全，调用效率高
 * 缺点：不用也加载，资源利用率低
 * @author Smith
 */
class Single01 implements Serializable
{
	private static final Single01 instance = new Single01();
	private Single01() 
	{
		//防反射破解
		if (null != instance)
		{
			throw new RuntimeException();
		}
	}
	
	public static Single01 getInstance()
	{
		return instance;
	}
	
	/**
	 * 防反序列化破解
	 */
	private Object readResolve() throws ObjectStreamException
	{
		return instance;
	}
}

/**
 * 2.懒汉式
 * [1]创建private构造器
 * [2]创建private static对象
 * [3]创建public方法，用于返回该对象，在次建立对象
 * 
 * 优点：使用才加载，资源利用率高
 * 缺点：对象在类方法中生成，线程不安全，需要synchronized，调用效率低
 * @author Smith
 *
 */
class Single02
{
	private static Single02 instance;
	private Single02() {}
	
	public static synchronized Single02 getInstance()
	{
		if (null == instance)
		{
			instance = new Single02();
		}
		return instance;
	}
}


/**
 * 3.双重检测锁模式
 * 优点：在饿汉式getInstance()中，用双重块锁来优化效率
 * 缺点：由于编译器和JVM原因，偶尔会出问题(不建议使用)
 * @author Smith
 */
class Single03
{
	private static Single03 instance = null; 
	public static Single03 getInstance() 
	{ 
		if (instance == null) 
		{ 
			Single03 sc; 
			synchronized (Single03.class) 
			{ 
				sc = instance; 
				if (sc == null) 
				{ 
					synchronized (Single03.class) 
					{ 
						if(sc == null) 
						{ 
							sc = new Single03(); 
						} 
					} 
					instance = sc; 
				} 
			} 
		} 
		return instance; 
	}
	private Single03() {} 
}

/**
 * 4.静态内部类模式(建议使用)
 * [1]创建private构造器
 * [2]创建private static内部类，在里面创建单例对象
 * [3]创建public方法，用于返回内部类生成的单例对象
 * 
 * 优点：线程安全+延迟加载 ，因为加载外部类时，不会马上加载内部类
 * 缺点：可以被反射、反序列化破解private
 * @author Smith
 */
class Single04
{
	private static class SingleInner
	{
		private static final Single04 instance = new Single04();
	}
	private Single04() {}
	
	public static Single04 getInstance()
	{
		return SingleInner.instance;
	}
}


/**
 * 5.枚举模式(建议使用)
 * [1]创建enum 而不是class 
 * [2]定义对象即可(本身就是单例)
 * [3]可添加自己的方法
 * 
 * 优点：线程安全，不会被反射、反序列化破解
 * 缺点：不包含懒加载
 * @author Smith
 */
enum Single05
{
	INSTANCE;
	public static Single05 getInstance()
	{
		return INSTANCE;
	}
}

















