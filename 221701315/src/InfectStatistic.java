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
	static int[][] people_count=new int[35][4];//定义一个二维数组记录所有种类的人数依次为感染 疑似 治愈 死亡
	public static void main(String [] args)//处理传入的参数
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
	
	public int[][] first_give()//初始化
	{
		int[][] a=new int[35][4];
		for(int i=0;i<35;i++)
		{
			for(int j=0;j<4;j++)
			{
				a[i][j]=0;
			}
		}
		return a;
	}
	public void read_text(String path) 
	{
		people_count=first_give();//将所有省市的的人数初始化
		try 
		{
			File afile=new File(path);
			if(afile.isFile() && afile.exists())
			{
				InputStreamReader read = new InputStreamReader(
	                    new FileInputStream(afile),"UTF-8");
	            BufferedReader buffered_reader = new BufferedReader(read);
	            String line_txt = null;
	            while((line_txt=buffered_reader.readLine())!=null)
	            {
	            	if(line_txt.contains("//"))//文档结尾，统计全国人数
	            	{
	            		for(int i=1;i<province1.length;i++)
	            		{
	            			for(int j=0;j<4;j++)
	            			{
	            				people_count[0][j]=people_count[0][j]+people_count[i][j];
	            			}
	            		}
	            		break;
	            	}
	            	String[] split=line_txt.split(" ");//分割读进来的字符串
	            	for(int i=0;i<province1.length;i++)
	            	{
	            		if(split[0]==province1[i])//判断省份
	            		{
	            			if(split[1]=="新增")
	            			{
	            				if(split[2]=="感染患者")
	            				{
	            					people_count[i][0]=people_count[i][0]+Integer.parseInt(split[3].substring(0,split[3].length()-1));
	            				}
	            				else if(split[2]=="疑似患者")
	            				{
	            					people_count[i][1]=people_count[i][1]+Integer.parseInt(split[3].substring(0,split[3].length()-1));
	            				}
	            			}
	            			else if(split[1]=="死亡")
	            			{
	            				people_count[i][3]=people_count[i][3]+Integer.parseInt(split[2].substring(0,split[2].length()-1));
	            				people_count[i][0]=people_count[i][0]-Integer.parseInt(split[2].substring(0,split[2].length()-1));
	            			}
	            			else if(split[1]=="治愈")
	            			{
	            				people_count[i][2]=people_count[i][2]+Integer.parseInt(split[2].substring(0,split[2].length()-1));
	            				people_count[i][0]=people_count[i][0]-Integer.parseInt(split[2].substring(0,split[2].length()-1));
	            			}
	            			else if(split[1]=="排除")
	            			{
	            				people_count[i][1]=people_count[i][1]-Integer.parseInt(split[3].substring(0,split[3].length()-1));
	            			}
	            			else if(split[1]=="疑似患者")
	            			{
	            				if(split[2]=="流入")
	            				{
	            					for(int j=0;j<province1.length;j++)
	            					{
	            						if(split[3]==province1[j])
	            						{
	            							people_count[i][1]=people_count[i][1]-Integer.parseInt(split[4].substring(0,split[4].length()-1));
	            							people_count[j][1]=people_count[j][1]+Integer.parseInt(split[4].substring(0,split[4].length()-1));
	            						}
	            					}
	            				}
	            				else if(split[2]=="确诊感染")
	            				{
	            					people_count[i][1]=people_count[i][1]-Integer.parseInt(split[3].substring(0,split[3].length()-1));
	            					people_count[i][0]=people_count[i][0]+Integer.parseInt(split[3].substring(0,split[3].length()-1));
	            				}
	            			}
	            			else if(split[1]=="感染患者")
	            			{
	            				for(int j=0;j<province1.length;j++)
            					{
            						if(split[3]==province1[j])
            						{
            							people_count[i][0]=people_count[i][0]-Integer.parseInt(split[4].substring(0,split[4].length()-1));
            							people_count[j][0]=people_count[j][0]+Integer.parseInt(split[4].substring(0,split[4].length()-1));
            						}
            					}
	            			}
	            		}
	            	}
	            }
	            read.close();
			}
		}
		catch(Exception e)
		{
			System.out.print("读取文件出错");
		}
	}
	
	public void write_txt(String path)//用来将数据输出到txt文件中
	{
		
	}
}
