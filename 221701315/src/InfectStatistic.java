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
	public void read_txt(String path) 
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
		try
		{
			File afile=new File(path);
			if(!afile.exists()){                //判断文件是否存在
                afile.createNewFile();        //创建文件
            }
			OutputStreamWriter write1 = new OutputStreamWriter(
                    new FileOutputStream(afile),"UTF-8");
			 BufferedWriter write = new BufferedWriter(write1);
			File file =new File(log);//日志文件存在的文件夹
			String[] names= file.list();
			for(String s:names)
			{
				if(s.compareTo(date)<=0||date==null)
				{
					read_txt(log+File.separator+s);
				}
			}
			//对输出进行限定
			if(province.length==0)//没有输入任何省份只输出全国
			{
				out(write,0);
				write.newLine();//换行
				String last_line="// 该文档并非真实数据，仅供测试使用";
				write.write(last_line);
			}
			else
			{
				for(int i=0;i<province.length;i++)
				{
					int province_number;
					for(int j=0;j<province1.length;j++)
					{
						if(province[i]==province1[j])//判断省份和对应索引
						{
							province_number=j;
							out(write,province_number);
							write.newLine();//换行
						}
					}
				}
				String last_line="// 该文档并非真实数据，仅供测试使用";
				write.write(last_line);
			}
			 //关闭输出流
			 write.flush();  
	         write.close();
		}
		catch(Exception e) 
		{	
			System.out.print("读取文件出错");
		}
	}
	public void out(BufferedWriter write,int province_number)//根据type类型进行输出
	{
		if(type.length!=0)
		{
			for(int i=0;i<type.length;i++)
			{
				if(type[i]=="ip")
				{
					try
					{
						String a="感染患者"+people_count[province_number][0]+"人 ";
						write.write(a);
					}
					catch(Exception e)
					{
						System.out.print("输出产生错误");
					}
				}
				else if(type[i]=="sp")
				{
					try
					{
						String a="疑似患者"+people_count[province_number][1]+"人 ";
						write.write(a);
					}
					catch(Exception e)
					{
						System.out.print("输出产生错误");
					}
				}
				else if(type[i]=="cure")
				{
					try
					{
						String a="治愈"+people_count[province_number][2]+"人 ";
						write.write(a);
					}
					catch(Exception e)
					{
						System.out.print("输出产生错误");
					}
				}
				else if(type[i]=="dead")
				{
					try
					{
						String a="死亡"+people_count[province_number][3]+"人";
						write.write(a);
					}
					catch(Exception e)
					{
						System.out.print("输出产生错误");
					}
				}
			}
		}
		else
		{
			try
			{
				String a="感染患者"+people_count[province_number][0]+"人 "+"疑似患者"+people_count[province_number][1]+"人 "+
						"治愈"+people_count[province_number][2]+"人 "+"死亡"+people_count[province_number][3]+"人";
				write.write(a);
			}
			catch(Exception e)
			{
				System.out.print("输出产生错误");
			}
		}
	}
}
