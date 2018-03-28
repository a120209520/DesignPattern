package ppl.pattern.action;

import java.util.LinkedList;
import java.util.List;

/**
 * 迭代器模式：
 * 太常见，参考jdk源码
 * @author Smith
 *
 */
public class IteratorPattern
{
	public static void main(String[] args)
	{
		List<String> list = new LinkedList<>();
		list.add("a");
		list.add("b");
		for(String s:list)
		{
			System.out.println(s);
		}
	}
}
