import java.util.*;

public class Scrabble
{
	public static void main(String[] args) throws Exception
	{
		Scanner scannner=new Scanner(System.in);
		String input=new String();
		System.out.println("Choose what letters can be used:");
		input=scannner.nextLine();
		int num=10;	//maximum number of letters which can be used
		char[] letters=input.toCharArray();	//an array of usable characters
		FileIO file = new FileIO();
		String[] inputs = file.load("dictionary.txt");

		Comparator<String> comparator = new lengthCompare();
        PriorityQueue<String> queue = new PriorityQueue<String>(comparator);
		for(int i=0; i < inputs.length; i++)			//Reads in lines from file
		{
				queue.add(inputs[i]);
		}

		String[] words=new String[num];
		int count=0;
		while(queue.size()>0 && count<num)
		{
			String temp=queue.remove();
			if (Validity(temp,letters))
			{
				words[count]=temp;
				count++;
			}
		}
		System.out.println("Results:");
		for(int i=0;i<words.length;i++)
		{
			if(words[i]!=null)
			{
				System.out.println((i+1)+":"+words[i]);
			}
			else
			{
				break;// if no words match or there are no more words stop the loop
			}
		}
	}

	public static boolean Validity(String word,char[] ar)
	{

		for(int a=97;a<123;a++)//ascii code for 'a' through 'z'
		{
			boolean condition=true;
			int count1=0,count2=0;
			for(int b=0;b<word.length();b++)
			{
				if((int)word.charAt(b)==a)
				{
					count1++;//count1 records the frequency of the letter in the word
				}
			}
			for(int c=0;c<ar.length;c++)
			{
				if((int)ar[c]==a)
				{
					count2++;//count2 records the frequency of the letter in the users array of letters
				}
			}
			if((count1>count2))
			{
				return false;//If current letter appears more in word than letter array, return false
			}
		}
		return true;			//Return true
	}


}