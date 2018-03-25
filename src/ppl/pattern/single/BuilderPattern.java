package ppl.pattern.single;
/**
 * 构建者模式
 * 用于生成复杂对象，包含很多组件，构建者模式就是用来构建并组装组件
 * @author Smith
 *
 */
public class BuilderPattern
{
	public static void main(String[] args)
	{
		RobotAssembler ra = new PPLRobotAssembler();
		Robot r = ra.assembleRobot();
		r.move();
		r.attack();
	}
}

//复杂对象
class Robot
{
	private Head head;
	private Body body;
	private Weapon weapon;
	public void setHead(Head head)
	{
		this.head = head;
	}
	public void setBody(Body body)
	{
		this.body = body;
	}
	public void setWeapon(Weapon weapon)
	{
		this.weapon = weapon;
	}

	public void attack()
	{
		System.out.println("use "+weapon+" attack!");
	}
	public void move()
	{
		System.out.println("move "+body+" move "+head);
	}
}

//构成对象的组件
class Head
{
	private String type;
	
	public Head(String type)
	{
		super();
		this.type = type;
	}

	@Override
	public String toString()
	{
		return type;
	}
}
class Body
{
	private String type;
	
	public Body(String type)
	{
		super();
		this.type = type;
	}

	@Override
	public String toString()
	{
		return type;
	}
}
class Weapon
{
	private String type;
	
	public Weapon(String type)
	{
		super();
		this.type = type;
	}

	@Override
	public String toString()
	{
		return type;
	}
}

//构建组件
interface RobotBuilder
{
	Head buildHead();
	Body buildBody();
	Weapon bulidWeapon();
}
class PPLRobotBuilder implements RobotBuilder
{
	@Override
	public Head buildHead()
	{
		return new Head("Big head");
	}

	@Override
	public Body buildBody()
	{
		return new Body("Strong body");
	}

	@Override
	public Weapon bulidWeapon()
	{
		return new Weapon("Laser");
	}
}

//组装组件
interface RobotAssembler
{
	Robot assembleRobot();
}
class PPLRobotAssembler implements RobotAssembler
{
	Robot rb;
	@Override
	public Robot assembleRobot()
	{
		rb = new Robot();
		PPLRobotBuilder pplBuilder = new PPLRobotBuilder();
		rb.setBody(pplBuilder.buildBody());
		rb.setHead(pplBuilder.buildHead());
		rb.setWeapon(pplBuilder.bulidWeapon());
		return rb;
	}
}





