package ppl.pattern.single;
/**
 * 装饰器模式：
 * 比如一个父类有很多种类型，如果用继承，
 * 会出现排列组合的子类，过于庞大
 * @author Smith
 *
 */
public class WrapperPattern
{
	public static void main(String[] args)
	{
		ICar c = new Car();
		SuperCar sc = new SwimCar(new FlyCar(new SuperCar(c)));
		sc.run();
	}
}

//抽象构件
interface ICar
{
	void run();
}

//被装饰构建
class Car implements ICar
{
	@Override
	public void run()
	{
		System.out.println("100km/h");
	}
}

class SuperCar implements ICar
{
	private ICar icar;
	
	public SuperCar(ICar icar)
	{
		this.icar = icar;
	}
	public void run()
	{
		icar.run();
	}
}
class FlyCar extends SuperCar
{
	public FlyCar(ICar icar)
	{
		super(icar);
	}
	@Override
	public void run()
	{
		System.out.println("fly!");
		super.run(); //装饰器的关键，会顺着调用上一个装饰器，直到完成所有装饰
	}
}
class SwimCar extends SuperCar
{
	public SwimCar(ICar icar)
	{
		super(icar);
	}
	@Override
	public void run()
	{
		System.out.println("swim!");
		super.run(); //装饰器的关键，会顺着调用上一个装饰器，直到完成所有装饰
	}
}

