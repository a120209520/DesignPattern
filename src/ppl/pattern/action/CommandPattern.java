package ppl.pattern.action;

/**
 * 命令模式：
 * 司令下发命令，需要经过处理再传达到各士兵
 * @author Smith
 *
 */
public class CommandPattern
{
	public static void main(String[] args)
	{
		Receiver r = new Receiver();
		Command c = new ConcreteCommand(r);
		Invoke i = new Invoke(c);
		i.call();
	}
}

//命令执行者
class Receiver
{
	public void action()
	{
		System.out.println("执行!");
	}
}

interface Command
{
	//具体处理方法
	void execute();
}

class ConcreteCommand implements Command
{
	private Receiver recv;

	public ConcreteCommand(Receiver recv)
	{
		super();
		this.recv = recv;
	}

	@Override
	public void execute()
	{
		System.out.println("执行前工作");
		recv.action();
		System.out.println("执行后工作");
	}
}

class Invoke
{
	private Command cmd;

	public Invoke(Command cmd)
	{
		super();
		this.cmd = cmd;
	}
	
	public void call()
	{
		cmd.execute();
	}
}
