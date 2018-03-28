package ppl.pattern.struct;
/**
 * 适配器模式：
 * 使用适配器来对两端接口进行适配
 * 常用于新老系统更新适配
 * 再例如InputStreamReader(InputStream)也用到了适配器模式
 * @author Smith
 *
 */
public class AdapterPattern
{
	public static void main(String[] args)
	{
		KeyBoard kb = new KeyBoard();
		ScreenPrintAdapter adapter = new ScreenPrintAdapter();
		kb.typeWriting("adapte me adapte me!", adapter);
	}
}

class KeyBoard
{
	public void typeWriting(String s, PrintAdapter pa)
	{
		pa.output(s);
	}
}

class Screen
{
	public void print(String s)
	{
		System.out.println(s);
	}
}

interface PrintAdapter
{
	void output(String s);
}

class ScreenPrintAdapter implements PrintAdapter
{
	private Screen sc;
	@Override
	public void output(String s)
	{
		sc = new Screen();
		sc.print(s);
	}
}
