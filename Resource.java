
import java.util.*;



class Resource
{
	private int numResources;
	private final int MAX = 5;
	public Resource(int startLevel)
	{
		numResources = startLevel;
	}

	public int getLevel()
	{
		return numResources;
	}
	public Stack<Integer> stack = new Stack<>();
	public  int max=100;
	public  int min=1;
	public int popedItem,randomNum;


	public synchronized void addOne()
	{
		try
		{
			while (numResources >= MAX)	wait();

			numResources++;

			randomNum = stack.push((int)(Math.random() *(max - min + 1) + min  ));

			System.out.println("PUSHED ITEM = " + randomNum);
			if(stack.size()==5){
				System.out.println("STACK IS FULL");
			}

			notifyAll();

		}
		catch (InterruptedException interruptEx)
		{
			System.out.println(interruptEx);
		}
	}

	public synchronized int takeOne()
	{
		try
		{
			while (numResources == 0) wait();


			popedItem = stack.pop();

			numResources--;
			System.out.println("POPED ITEM = " + popedItem);
			if(stack.isEmpty()){
				System.out.println("STACK IS EMPTY");
			}


			notify();

		}
		catch (InterruptedException interruptEx)
		{
			System.out.println(interruptEx);
		}
		return popedItem;
	}
}
