package ppl.pattern.action;
/**
 * 责任链模式：
 * 处理请求，一个一个向上审批
 * 避免过多的if else使用
 * @author Smith
 *
 */
public class ChainPattern
{
	public static void main(String[] args)
	{
		LeaveRequest rqs = new LeaveRequest(5);
		Leader sm = new GroupLeader("aaa");
		Leader rm = new Manager("bbb");
		sm.setNext(rm);
		sm.handleRequest(rqs);
	}
}

class LeaveRequest
{
	private int day;

	public LeaveRequest(int day)
	{
		super();
		this.day = day;
	}

	public int getDay()
	{
		return day;
	}
}

abstract class Leader
{
	protected String name;
	protected Leader nextLeader;

	public Leader(String name)
	{
		this.name = name;
	}
	
	public void setNext(Leader nextLeader)
	{
		this.nextLeader = nextLeader;
	}
	abstract void handleRequest(LeaveRequest rqs);
}

class GroupLeader extends Leader
{
	public GroupLeader(String name)
	{
		super(name);
	}	
	
	@Override
	void handleRequest(LeaveRequest rqs)
	{
		if (rqs.getDay() < 3)
		{
			System.out.println("GroupLeader:"+name+"批准!");
		}
		else
		{
			if(nextLeader != null)
			{
				nextLeader.handleRequest(rqs);
			}
		}
	}
}

class Manager extends Leader
{
	public Manager(String name)
	{
		super(name);
	}	
	
	@Override
	void handleRequest(LeaveRequest rqs)
	{
		if (rqs.getDay() >= 3)
		{
			System.out.println("Manager:"+name+"批准!");
		}
		else
		{
			if(nextLeader != null)
			{
				nextLeader.handleRequest(rqs);
			}
		}
	}
}





