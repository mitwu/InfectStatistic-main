import java.io.*;
import java.lang.*;
import java.util.*;

public class InfectStatistic 
{
	static String log,out,date,type[],province[];
	static String province1[]= 
	{
		"全国","安徽","澳门","北京","重庆","福建","甘肃",
		 "广东","广西","贵州","海南","河北","河南","黑龙江",
		"湖北","湖南","吉林","江苏","江西","辽宁","内蒙古",
		 "宁夏","青海","山东","山西","陕西","上海","四川",
		"台湾","天津","西藏","香港","新疆","云南","浙江"
	};
	
	
	public static void main(String [] args)
	{
		for(int i=0;i<args.length;i++)
		{
			if(args[i].contentEquals("-log"))
			{
				log=args[i+1];
			}
			else if(args[i].contentEquals("-out"))
			{
				out=args[i+1];
			}
			if(args[i].contentEquals("-date"))
			{
				date=args[i+1];
			}
			if(args[i].contentEquals("-type"))
			{
				int a=0;
				while(true)
				{
					if(args[i+1+a]!=null&&args[i+1+a].equals("ip"))
					{
						type[a]=args[i+1+a];
						a++;
						if(args[i+2+a]!=null)
						{
							continue;
						}
						else
						{
							break;
						}
					}
					if(args[i+1+a]!=null&&args[i+1+a].equals("sp"))
					{
						type[a]=args[i+1+a];
						a++;
						if(args[i+2+a]!=null)
						{
							continue;
						}
						else
						{
							break;
						}
					}
					if(args[i+1+a]!=null&&args[i+1+a].equals("cure"))
					{
						type[a]=args[i+1+a];
						a++;
						if(args[i+2+a]!=null)
						{
							continue;
						}
						else
						{
							break;
						}
					}
					if(args[i+1+a]!=null&&args[i+1+a].equals("dead"))
					{
						type[a]=args[i+1+a];
						a++;
						if(args[i+2+a]!=null)
						{
							continue;
						}
						else
						{
							break;
						}
					}
					break;
				}
			}
			if(args[i].contentEquals("-province"))
			{
				for(int j=0;j<province1.length;j++)
				{
					if(args[i+1+j]!=null)
					{
						province[j]=args[i+1+j];
					}
				}
			}
		}
	}
	
}
