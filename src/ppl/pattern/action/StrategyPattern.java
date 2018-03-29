package ppl.pattern.action;

/**
 * 策略模式：
 * 例如商家针对不同客户，给出不同报价
 * @author Smith
 *
 */
public class StrategyPattern
{
	public static void main(String[] args)
	{
		//OldUsrStrategy st = ;
		
		Calculator c = new Calculator(new OldUsrStrategy());
		System.out.println(c.showPrice(100));
		c = new Calculator(new NewUsrStrategy());
		System.out.println(c.showPrice(100));
	}
}

//策略接口
interface Strategy
{
	double getPrice(double price);
}

//具体策略
class OldUsrStrategy implements Strategy
{
	@Override
	public double getPrice(double price)
	{
		return price*0.9;
	}
}
class NewUsrStrategy implements Strategy
{
	@Override
	public double getPrice(double price)
	{
		return price*0.8;
	}
}

//调用者
class Calculator 
{
	Strategy st;
	public Calculator(Strategy st)
	{
		super();
		this.st = st;
	}
	public double showPrice(double price)
	{
		return st.getPrice(price);
	}
}



//如果不使用策略模式，如果新增策略，需要反复修改代码，不符合开闭原则
/*
public double getPrice(String user, double price)
{
	double result = 0;
	switch(user)
	{
		case "olduser": result=price*0.9; break;
		case "newuser": result=price*0.8; break;
	}
	return result;
}
 */



