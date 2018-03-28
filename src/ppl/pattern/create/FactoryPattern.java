package ppl.pattern.create;
/**
 * 工厂模式：使创建者和调用者分离
 * 核心思想：用工厂方法代替new
 * 
 * 结论：
 * 
 * @author Smith
 */
public class FactoryPattern
{
	public static void Test01()
	{
		//如果不使用工厂模式
		//则Test01需要依赖类Apple Pizza和接口Eatable
		Eatable e1 = new Apple();
		Eatable e2 = new Pizza();
		e1.eat();
		e2.eat();
	}
	public static void Test02()
	{
		//1.简单工厂
		FoodFactory01 factory = new FoodFactory01();
		Eatable e1 = factory.createFood("Apple");
		Eatable e2 = factory.createFood("Pizza");
		e1.eat();
		e2.eat();
	}
	public static void Test03()
	{
		//2.工厂方法
		Eatable e1 = new AppleFactory().createFood();
		Eatable e2 = new PizzaFactory().createFood();
		e1.eat();
		e2.eat();
	}
	public static void Test04()
	{
		//3.抽象工厂
		SetMeal s1 = new BigSetMeal();
		s1.createCola().drink();
		s1.createHamburger().eat();
		SetMeal s2 = new SmallSetMeal();
		s2.createCola().drink();
		s2.createHamburger().eat();
	}
	
	public static void main(String[] args)
	{
		Test04();
	}
}

interface Eatable
{
	void eat();
}

class Apple implements Eatable
{
	public void eat()
	{
		System.out.println("eat a Apple");
	}
}

class Pizza implements Eatable
{
	public void eat()
	{
		System.out.println("eat a Pizza");
	}
}



/**
 * 1.简单工厂
 * 优点：简单易用，项目中经常使用
 * 缺点：无法满足开闭原则，如果要新增类型，需要修改工厂
 * @author Smith
 */
class FoodFactory01
{
	public Eatable createFood(String type)
	{
		switch(type)
		{
			case "Apple": return new Apple();
			case "Pizza": return new Pizza();
			default : return null;
		}
	}
	
	/* 另一种实现方式
	public Eatable createApple()
	{	
		return new Apple();
	}
	public Eatable createPizza()
	{	
		return new Pizza();
	}
	*/
}

/**
 * 2.工厂方法
 * 优点：满足开闭原则
 * 缺点：新增的类比较多，复杂性很高
 * @author Smith
 */
interface FoodFactory02
{
	Eatable createFood();
}

class AppleFactory implements FoodFactory02
{
	@Override
	public Eatable createFood()
	{
		return new Apple();
	}
}

class PizzaFactory implements FoodFactory02
{
	@Override
	public Eatable createFood()
	{
		return new Pizza();
	}
}

/**
 * 3.抽象工厂
 * 适用于生产产品族
 * @author Smith
 */
//产品接口和类
interface Cola
{
	void drink();
}
interface Hamburger
{
	void eat();
}

class BigCola implements Cola
{
	@Override
	public void drink()
	{
		System.out.println("big cola");
	}
}
class SmallCola implements Cola
{
	@Override
	public void drink()
	{
		System.out.println("small cola");
	}
}

class BigHamburger implements Hamburger
{
	@Override
	public void eat()
	{
		System.out.println("big hamburger");
	}
}
class SmallHamburger implements Hamburger
{
	@Override
	public void eat()
	{
		System.out.println("Small hamburger");
	}
}

//产品族接口和类
interface SetMeal 
{
	Cola createCola();
	Hamburger createHamburger();
}

class BigSetMeal implements SetMeal
{
	@Override
	public Cola createCola()
	{
		return new BigCola();
	}

	@Override
	public Hamburger createHamburger()
	{
		return new BigHamburger();
	}
}
class SmallSetMeal implements SetMeal
{
	@Override
	public Cola createCola()
	{
		return new SmallCola();
	}

	@Override
	public Hamburger createHamburger()
	{
		return new SmallHamburger();
	}
}

