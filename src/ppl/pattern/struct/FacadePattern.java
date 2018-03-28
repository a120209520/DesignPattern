package ppl.pattern.struct;

/**
 * 外观模式：利用中间类，尽可能降低类之间的耦合
 * @author Smith
 *
 */
public class FacadePattern
{
	public static void main(String[] args)
	{
		//对于使用者，只需要与MiddleCompany接触即可
		new MiddleCompany().register();
	}
}

//中间类
class MiddleCompany
{
	void register()
	{
		new Step1().step01();
		new Step2().step02();
	}
}

class Step1
{
	void step01()
	{
		System.out.println("注册第一步");
	}
}
class Step2
{
	void step02()
	{
		System.out.println("注册第二步");
	}
}
