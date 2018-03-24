package ppl.pattern.single;
/**
 * 单例模式：只生成一个实例，减少系统性能开销，当一个对象
 * 占用资源比较多时，比如读取配置、产生其他依赖对象，则可
 * 产生单例对象，然后永驻内存。
 * @author Smith
 */
public class SinglePattern
{
	public static void main(String[] args)
	{
		
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
class Single01
{
	private static final Single01 instance = new Single01();
	private Single01() {}
	
	public Single01 getInstance()
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
	
	public synchronized Single02 getInstance()
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

/**
 * 4.静态内部类模式
 * [1]创建private构造器
 * [2]创建private static内部类，在里面创建单例对象
 * [3]创建public方法，用于返回内部类生成的单例对象
 * 
 * 优点：线程安全+延迟加载 ，因为加载外部类时，不会马上加载内部类
 * 缺点：
 * @author Smith
 */
class Single04
{
	
}


