package ppl.pattern.action;

import java.util.ArrayList;
import java.util.List;

/**
 * 观察者模式：
 * 用于广播场景，目标对象作为消息发布者(Push)
 * 其他方作为消息订阅者，也称为观察者(Pull)
 * 例如：
 * 游戏服务器，聊天室等等
 * @author Smith
 *
 */
public class ObserverPattern
{
	public static void main(String[] args)
	{
		GameServer gs = new GameServer();
		Gamer g1 = new Gamer(1);
		Gamer g2 = new Gamer(2);
		Gamer g3 = new Gamer(99);
		gs.register(g1);
		gs.register(g2);
		gs.register(g3);
		gs.notifyOthers();
		System.out.println("初始化 !");
		g1.displayAllLevel();
		System.out.println("g3 升级 !");
		gs.upgradeLevel(g3, 1);
		g1.displayAllLevel();
	}
}

//Java 官方观察者模式 Observable

//抽象目标对象
interface Subject
{
	void notifyOthers();
	void register(Observer o);
	void remove(Observer o);
}
//具体目标对象
class GameServer implements Subject
{
	//服务器数据
	private ArrayList<Gamer> list = new ArrayList<>();
	@Override
	public void notifyOthers()
	{
		for (Observer g:list)
		{
			g.update(this);
		}
	}
	
	public void upgradeLevel(Observer o, int num)
	{
		Gamer g = (Gamer)o;
		g.upgradeLevel(num);
		notifyOthers();
	}
	
	public ArrayList<Gamer> getListData()
	{
		return list;
	}

	@Override
	public void register(Observer o)
	{
		list.add((Gamer)o);
	}

	@Override
	public void remove(Observer o)
	{
		list.remove((Gamer)o);
	}
}

//抽象观察者
interface Observer
{
	void update(Subject s);
}
//具体观察者
class Gamer implements Observer
{
	//本地数据
	private ArrayList<Gamer> list;
	private int level;
	
	public Gamer(int level)
	{
		super();
		this.level = level;
	}

	@Override
	public void update(Subject s)
	{
		GameServer gs = (GameServer)s;
		ArrayList<Gamer> serList = gs.getListData();
		list = serList; //这里只是模拟，实际情况服务器和客户端是分开的，需要数据拷贝
	}
	
	public void upgradeLevel(int num)
	{
		level += num;
	}
	
	public void displayAllLevel()
	{
		for(Gamer g:list)
		{
			System.out.println(g.level);
		}
	}
}

