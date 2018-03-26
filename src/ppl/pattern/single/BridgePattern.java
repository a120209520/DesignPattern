package ppl.pattern.single;

/**
 * 桥接模式：
 * 用于多层继承结构(多维结构)
 * 每个维度的继承只用关心自己
 * @author Smith
 *
 */
public class BridgePattern
{
	public static void Test01()
	{
		Computer c1 = new Computer(new AppleCompany());
		Computer c2 = new Desktop(new AppleCompany());
		Computer c3 = new Laptop(new HpCompany());
		c1.play();
		c2.play();
		c3.play();
	}
	public static void main(String[] args)
	{
		Test01();
	}
}

//桥接模式
class Computer
{
	protected Brand b;
	public Computer(Brand b)
	{
		this.b = b;
	}
	public void play()
	{
		System.out.println("玩"+b.type()+"电脑");
	}
}
class Desktop extends Computer
{
	public Desktop(Brand b)
	{
		super(b);
	}
	@Override
	public void play()
	{
		b.type();
		System.out.println("玩"+b.type()+"台式机");
	}
}
class Laptop extends Computer
{
	public Laptop(Brand b)
	{
		super(b);
	}
	@Override
	public void play()
	{
		b.type();
		System.out.println("玩"+b.type()+"笔记本");
	}
}


class Brand
{
	public String type() {return "";}
}

class AppleCompany extends Brand
{
	@Override
	public String type()
	{
		return "Apple";
	}
}
class HpCompany extends Brand
{
	@Override
	public String type()
	{
		return "Hp";
	}
}

//未使用桥接模式，非常繁琐
/*
abstract class Computer
{
	abstract void play();
}
class Desktop extends Computer
{
	public void play()
	{
		System.out.println("玩台式机");
	}
}
class Laptop extends Computer
{
	public void play()
	{
		System.out.println("玩笔记本");
	}
}
class iMac extends Desktop
{
	public void play()
	{
		System.out.println("玩iMac");
	}
}
class MacBook extends Laptop
{
	public void play()
	{
		System.out.println("玩Laptop");
	}
}
*/









