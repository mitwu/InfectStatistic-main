import java.io.*;
import java.lang.*;
import java.util.*;

public class InfectStatistic 
{
	static String log,out,date,type[],province[];
	static String province1[]= 
	{
		"ȫ��","����","����","����","����","����","����",
		 "�㶫","����","����","����","�ӱ�","����","������",
		"����","����","����","����","����","����","���ɹ�",
		 "����","�ຣ","ɽ��","ɽ��","����","�Ϻ�","�Ĵ�",
		"̨��","���","����","���","�½�","����","�㽭"
	};
	static int[][] people_count=new int[35][4];//����һ����ά�����¼�����������������Ϊ��Ⱦ ���� ���� ����
	public static void main(String [] args)//������Ĳ���
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
	
	public int[][] first_give()//��ʼ��
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
		people_count=first_give();//������ʡ�еĵ�������ʼ��
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
	            	if(line_txt.contains("//"))//�ĵ���β��ͳ��ȫ������
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
	            	String[] split=line_txt.split(" ");//�ָ���������ַ���
	            	for(int i=0;i<province1.length;i++)
	            	{
	            		if(split[0]==province1[i])//�ж�ʡ��
	            		{
	            			if(split[1]=="����")
	            			{
	            				if(split[2]=="��Ⱦ����")
	            				{
	            					people_count[i][0]=people_count[i][0]+Integer.parseInt(split[3].substring(0,split[3].length()-1));
	            				}
	            				else if(split[2]=="���ƻ���")
	            				{
	            					people_count[i][1]=people_count[i][1]+Integer.parseInt(split[3].substring(0,split[3].length()-1));
	            				}
	            			}
	            			else if(split[1]=="����")
	            			{
	            				people_count[i][3]=people_count[i][3]+Integer.parseInt(split[2].substring(0,split[2].length()-1));
	            				people_count[i][0]=people_count[i][0]-Integer.parseInt(split[2].substring(0,split[2].length()-1));
	            			}
	            			else if(split[1]=="����")
	            			{
	            				people_count[i][2]=people_count[i][2]+Integer.parseInt(split[2].substring(0,split[2].length()-1));
	            				people_count[i][0]=people_count[i][0]-Integer.parseInt(split[2].substring(0,split[2].length()-1));
	            			}
	            			else if(split[1]=="�ų�")
	            			{
	            				people_count[i][1]=people_count[i][1]-Integer.parseInt(split[3].substring(0,split[3].length()-1));
	            			}
	            			else if(split[1]=="���ƻ���")
	            			{
	            				if(split[2]=="����")
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
	            				else if(split[2]=="ȷ���Ⱦ")
	            				{
	            					people_count[i][1]=people_count[i][1]-Integer.parseInt(split[3].substring(0,split[3].length()-1));
	            					people_count[i][0]=people_count[i][0]+Integer.parseInt(split[3].substring(0,split[3].length()-1));
	            				}
	            			}
	            			else if(split[1]=="��Ⱦ����")
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
			System.out.print("��ȡ�ļ�����");
		}
	}
	
	public void write_txt(String path)//���������������txt�ļ���
	{
		
	}
}
