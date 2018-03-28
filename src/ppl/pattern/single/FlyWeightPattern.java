package ppl.pattern.single;

import java.util.HashMap;
import java.util.Map;

/**
 * 享元模式：
 * 如果有很多相似的对象，可以共享部分资源，用于节省内存
 * 比如棋子就有很多相似点，只有位置和颜色不同
 * 通常与工厂模式结合使用
 * @author Smith
 *
 */
public class FlyWeightPattern
{
	public static void main(String[] args)
	{
		//调用工厂创建一个具有相同颜色的棋子
		ChessFactory cf = new ChessFactory();
		IChess c1 = cf.getChess("black");
		IChess c2 = cf.getChess("black");
		System.out.println(c1 == c2);
		//比如我们要下两步棋，这里只用一个对象就够了
		//如果不使用享元思路，可能会new 很多个棋子，造成内存浪费
		c1.setLoacation(new Loacation(1,1));
		c2.setLoacation(new Loacation(2,2));
	}
}

//抽象享元类
//接口或抽象类，声明公共方法，可读取内部状态，设置外部状态
interface IChess
{
	//内部状态(共享部分)
	String getColor();
	
	//外部状态(非共享部分)
	void setLoacation(Loacation l);
}

//享元工厂(核心!)
class ChessFactory 
{
	private Map<String,IChess> map = new HashMap<>();
	public IChess getChess(String color)
	{
		if (!map.containsKey(color))
		{
			IChess c = new MyChess(color);
			map.put(color, c);
		}
		return map.get(color);
	}
}

//共享享元类
class MyChess implements IChess
{
	String color;

	public MyChess(String color)
	{
		super();
		this.color = color;
	}
	@Override
	public String getColor()
	{
		return color;
	}
	@Override
	public void setLoacation(Loacation l)
	{
		System.out.println(color+":"+l.getX()+"/"+l.getY());
	}
}
//非共享享元类
class Loacation
{
	private int x;
	private int y;
	public int getX()
	{
		return x;
	}
	public void setX(int x)
	{
		this.x = x;
	}
	public int getY()
	{
		return y;
	}
	public void setY(int y)
	{
		this.y = y;
	}
	public Loacation(int x, int y)
	{
		super();
		this.x = x;
		this.y = y;
	}
}
