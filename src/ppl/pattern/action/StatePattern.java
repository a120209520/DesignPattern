package ppl.pattern.action;

/**
 * 状态模式：类似状态机
 * @author Smith
 *
 */
public class StatePattern
{
	public static void main(String[] args)
	{
		WorkSpace ws = new WorkSpace();
		ws.setState(new FreeState());
		ws.setState(new BusyState());
	}
}

//环境类
class WorkSpace
{
	private State s;
	public void setState(State s)
	{
		this.s = s;
		s.handle();
	}
}


//抽象状态类
interface State
{
	void handle();
}

//具体状态类
class FreeState implements State
{
	@Override
	public void handle()
	{
		System.out.println("Free");
	}
}
class BusyState implements State
{
	@Override
	public void handle()
	{
		System.out.println("Busy");
	}
}
class StopState implements State
{
	@Override
	public void handle()
	{
		System.out.println("Stop");
	}
}