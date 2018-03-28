package ppl.pattern.create;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/**
 * 原型模式：
 * 将对象a克隆到对象b，然后对b进行修改和操作
 * 通常用于创建比较复杂的对象或者重复大量创建某对象
 * @author Smith
 *
 */
public class PrototypePattern
{
	public static void Test01()
	{
		//1. 利用clone函数实现深克隆
		System.out.println("Use clone()");
		try
		{
			Date date = new Date(12345);
			Sheep s1 = new Sheep("喜羊羊", date);
			System.out.println("s1:"+s1);
			
			Sheep s2 = (Sheep)s1.clone();
			s2.setName("懒羊羊");
			s2.setBirthday(new Date());
			System.out.println("s1:"+s1);
			System.out.println("s2:"+s2);
		} catch (CloneNotSupportedException e)
		{
			e.printStackTrace();
		}	
	}
	public static void Test02()
	{
		//2. 利用序列化实现深克隆
		System.out.println("Use Serializable");
		try
		{
			Date date = new Date(12345);
			Sheep s1 = new Sheep("喜羊羊", date);
			System.out.println("s1:"+s1);
	
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(s1);
			byte[] bytes = bos.toByteArray();
			
			ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bis);
			Sheep s2 = (Sheep)ois.readObject();
			s2.setName("懒羊羊");
			s2.setBirthday(new Date());
			System.out.println("s1:"+s1);
			System.out.println("s2:"+s2);
		} catch (IOException e)
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void Test03() throws Exception
	{
		//速度测试
		Sheep osheep = new Sheep();
		long start = System.currentTimeMillis();
		for (int i=0; i<100; i++)
		{
			Sheep s = new Sheep();
		}
		long stop = System.currentTimeMillis();
		System.out.println("new 耗时:"+(stop-start)+"ms");  //1031ms
		
		start = System.currentTimeMillis();
		for (int i=0; i<100; i++)
		{
			Sheep s = (Sheep) osheep.clone();
		}
		stop = System.currentTimeMillis();
		System.out.println("clone 耗时:"+(stop-start)+"ms"); //0ms 最快
		
		start = System.currentTimeMillis();
		for (int i=0; i<100; i++)
		{
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(osheep);
			byte[] bytes = bos.toByteArray();
			
			ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bis);
			Sheep s = (Sheep)ois.readObject();
		}
		stop = System.currentTimeMillis();
		System.out.println("serialize 耗时:"+(stop-start)+"ms"); //60ms
	}
	
	public static void main(String[] args) throws Exception
	{
		Test03();
	}
}

class Sheep implements Cloneable,Serializable
{
	private String name;
	private Date birthday;
	
	public Sheep()
	{
		//模拟复杂对象，每创建一次消耗10ms
		try
		{
			Thread.sleep(10);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	public Sheep(String name, Date birthday)
	{
		super();
		this.name = name;
		this.birthday = birthday;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public void setBirthday(Date birthday)
	{
		this.birthday = birthday;
	}
	
	@Override
	public String toString()
	{
		return "[name]:"+name+",[birthday]:"+birthday;
	}
	@Override
	protected Object clone() throws CloneNotSupportedException
	{
		Object obj = super.clone();
		Sheep s = (Sheep)obj;
		if (null != birthday)
		{
			s.birthday = (Date)birthday.clone();
		}
		return obj;
	}
}










