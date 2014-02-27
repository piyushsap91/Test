package package1;
import java.util.*;
import java.io.*;

public class Palindrome 
{
	
	Palindrome()
	{
	}
	
	public static void main(String[] s1) throws IOException
	{
		try
		{
				System.out.println("Enter the string");
				Scanner r = new Scanner(System.in);
				String s = r.nextLine();
				String st;
			//	System.out.println("String is "+s);
				int i,j,len,mid,flag;
				len= s.length();
			//	System.out.println(len);
				mid=len/2;
			//	System.out.println(mid);
				flag=1;
				for(i=0,j=len-1;i<=mid;i++,j--)
				{	
					//System.out.println("loop number "+i);
					//System.out.println(s.charAt(i));
					//System.out.println(s.charAt(j));
					st = s.toLowerCase();
					if(st.charAt(i)!=st.charAt(j))
					{
						//System.out.println("Inside if loop");
						flag = 0;
						break;
					}
				}
				if(flag==0)
					System.out.println("the String is not a Palindrome");
				else
					System.out.println("the String is a Palindrome");
				
		}
		catch(Exception e)
		{
		
		}
	}
	
}
