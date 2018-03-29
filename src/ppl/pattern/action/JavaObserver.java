package ppl.pattern.action;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * java官方观察者模式
 * @author Smith
 *
 */
public class JavaObserver
{
	 public static void main(String[] args)
	{
			GameServer2 gs = new GameServer2();
			Gamer2 g1 = new Gamer2(1);
			Gamer2 g2 = new Gamer2(2);
			Gamer2 g3 = new Gamer2(99);
			gs.register(g1);
			gs.register(g2);
			gs.register(g3);
			gs.addObserver(g1);
			gs.addObserver(g2);
			gs.addObserver(g3);
			System.out.println("g3 升级 !");
			gs.upgradeLevel(g3, 1);
			g1.displayAllLevel();
	}
}

//Java 官方观察者模式 Observable


//具体目标对象
class GameServer2 extends Observable
{
	//服务器数据
	private ArrayList<Gamer2> list = new ArrayList<>();
	
	public void register(Observer o)
	{
		list.add((Gamer2)o);
	}

	public void remove(Observer o)
	{
		list.remove((Gamer2)o);
	}
	
	public ArrayList<Gamer2> getListData()
	{
		return list;
	}
	
	public void upgradeLevel(Observer o, int num)
	{
		Gamer2 g = (Gamer2)o;
		g.upgradeLevel(num);
		setChanged();
		notifyObservers(list);
	}
}


//具体观察者
class Gamer2 implements Observer
{
	//本地数据
	private ArrayList<Gamer2> list;
	private int level;
	
	public Gamer2(int level)
	{
		super();
		this.level = level;
	}

	@Override
	public void update(Observable o, Object arg)
	{
		GameServer2 gs = (GameServer2)o;
		ArrayList<Gamer2> serList = gs.getListData();
		list = serList; //这里只是模拟，实际情况服务器和客户端是分开的，需要数据拷贝
	}
	
	public void upgradeLevel(int num)
	{
		level += num;
	}
	
	public void displayAllLevel()
	{
		for(Gamer2 g:list)
		{
			System.out.println(g.level);
		}
	}
}
