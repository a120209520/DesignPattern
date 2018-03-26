package ppl.pattern.single;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合模式：
 * 用来处理树形结构
 * [1]抽象构件：定义了叶子和容器的共同点
 * [2]叶子
 * [3]容器
 * @author Smith
 *
 */
public class CompositePattern
{
	public static void Test01()
	{
		AbstractFile l1,l2,l3,l4;
		Folder f1,f2;
		
		l1 = new ImageFile("aaa.img");
		l2 = new ImageFile("bbb.img");
		l3 = new ImageFile("ccc.mp4");
		l4 = new ImageFile("ddd.mp4");
		f1 = new Folder("myFile");
		f2 = new Folder("video");
		
		f1.add(f2);
		f1.add(l1);
		f1.add(l2);
		f2.add(l3);
		f2.add(l4);
		
		f1.open();
	}
	public static void main(String[] args)
	{
		Test01();
	}
}

//[1]抽象构件
interface AbstractFile
{
	void open();
}

class Folder implements AbstractFile
{
	private String name;
	private List<AbstractFile> list = new ArrayList<>();
	
	public Folder(String name)
	{
		super();
		this.name = name;
	}
	
	public void add(AbstractFile f)
	{
		list.add(f);
	}
	public void remove(AbstractFile f)
	{
		list.remove(f);
	}

	@Override
	public void open()
	{
		System.out.println("打开文件夹:"+name);
		for (AbstractFile f:list)
		{
			f.open();
		}
	}	
}

class ImageFile implements AbstractFile
{
	private String name;

	public ImageFile(String name)
	{
		super();
		this.name = name;
	}
	
	public void open()
	{
		System.out.println("打开图像文件:"+name);
	}
}

class VideoFile implements AbstractFile
{
	private String name;

	public VideoFile(String name)
	{
		super();
		this.name = name;
	}
	
	public void open()
	{
		System.out.println("打开视频文件:"+name);
	}
}
