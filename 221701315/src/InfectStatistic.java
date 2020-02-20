import java.io.*;
import java.lang.*;
import java.util.*;

public class InfectStatistic 
{
	static String log,out,date;static String[] type=new String[4];static String[] province=new String[35];
	static String province1[]= 
	{
		"ȫ��","����","����","����","����","����","����",
		 "�㶫","����","����","����","�ӱ�","����","������",
		"����","����","����","����","����","����","���ɹ�",
		 "����","�ຣ","ɽ��","ɽ��","����","�Ϻ�","�Ĵ�",
		"̨��","���","����","���","�½�","����","�㽭"
	};
	static int[][] people_count=new int[35][4];//����һ����ά�����¼�����������������Ϊ��Ⱦ ���� ���� ����
	
	public static void main(String[] args)//������Ĳ���
	{
		test(args);
		write_txt(out);
	}
	public static void test(String[] args)
	{
		for(int i=0;i<args.length;i++)
		{
			if(args[i].compareTo("-log")==0)
			{
				log=args[i+1];
			}
			else if(args[i].compareTo("-out")==0)
			{
				out=args[i+1];
			}
			if(args[i].compareTo("-date")==0)
			{
				date=args[i+1];
			}
			if(args[i].compareTo("-type")==0)
			{
				int a=0;
				while(true)
				{
					if((i+1+a)<args.length)
					{
						if(args[i+1+a].compareTo("ip")==0)
						{
							type[a]=args[i+1+a];
							a++;
							if((i+2+a)<args.length)
							{
								continue;
							}
							else
							{
								break;
							}
						}
					}
					if((i+1+a)<args.length)
					{
						if(args[i+1+a].compareTo("sp")==0)
						{
							type[a]=args[i+1+a];
							a++;
							if((i+2+a)<args.length)
							{
								continue;
							}
							else
							{
								break;
							}
						}
					}
					if((i+1+a)<args.length)
					{
						if(args[i+1+a].compareTo("cure")==0)
						{
							type[a]=args[i+1+a];
							a++;
							if((i+2+a)<args.length)
							{
								continue;
							}
							else
							{
								break;
							}
						}
					}
					if((i+1+a)<args.length)
					{
						if(args[i+1+a].compareTo("dead")==0)
						{
							type[a]=args[i+1+a];
							a++;
							if((i+2+a)<args.length)
							{
								continue;
							}
							else
							{
								break;
							}
						}
					}
					break;
				}
			}
			if(args[i].compareTo("-province")==0)
			{
				for(int j=0;j<province1.length;j++)
				{
					if((i+1+j)<args.length)
					{
						if(args[i+1+j].length()!=0)
						{
							String a=args[i+1+j];
							province[j]=a;
						}
					}
				}
			}
		}
	}
	public static int[][] first_give()//��ʼ��
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
	public static void read_txt(String path) 
	{
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
	            		people_count[0][0]=0;people_count[0][1]=0;people_count[0][2]=0;people_count[0][3]=0;
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
	            	for(int i=1;i<province1.length;i++)
	            	{
	            		if((split[0].compareTo(province1[i]))==0)//�ж�ʡ��
	            		{
	            			if(split[1].compareTo("����")==0)
	            			{
	            				if(split[2].compareTo("��Ⱦ����")==0)
	            				{
	            					people_count[i][0]=people_count[i][0]+Integer.parseInt(split[3].substring(0,split[3].length()-1));
	            				}
	            				else if(split[2].compareTo("���ƻ���")==0)
	            				{
	            					people_count[i][1]=people_count[i][1]+Integer.parseInt(split[3].substring(0,split[3].length()-1));
	            				}
	            			}
	            			else if(split[1].compareTo("����")==0)
	            			{
	            				people_count[i][3]=people_count[i][3]+Integer.parseInt(split[2].substring(0,split[2].length()-1));
	            				people_count[i][0]=people_count[i][0]-Integer.parseInt(split[2].substring(0,split[2].length()-1));
	            			}
	            			else if(split[1].compareTo("����")==0)
	            			{
	            				people_count[i][2]=people_count[i][2]+Integer.parseInt(split[2].substring(0,split[2].length()-1));
	            				people_count[i][0]=people_count[i][0]-Integer.parseInt(split[2].substring(0,split[2].length()-1));
	            			}
	            			else if(split[1].compareTo("�ų�")==0)
	            			{
	            				people_count[i][1]=people_count[i][1]-Integer.parseInt(split[3].substring(0,split[3].length()-1));
	            			}
	            			else if(split[1].compareTo("���ƻ���")==0)
	            			{
	            				if(split[2].compareTo("����")==0)
	            				{
	            					for(int j=0;j<province1.length;j++)
	            					{
	            						if(split[3].compareTo(province1[j])==0)
	            						{
	            							people_count[i][1]=people_count[i][1]-Integer.parseInt(split[4].substring(0,split[4].length()-1));
	            							people_count[j][1]=people_count[j][1]+Integer.parseInt(split[4].substring(0,split[4].length()-1));
	            						}
	            					}
	            				}
	            				else if(split[2].compareTo("ȷ���Ⱦ")==0)
	            				{
	            					people_count[i][1]=people_count[i][1]-Integer.parseInt(split[3].substring(0,split[3].length()-1));
	            					people_count[i][0]=people_count[i][0]+Integer.parseInt(split[3].substring(0,split[3].length()-1));
	            				}
	            			}
	            			else if(split[1].compareTo("��Ⱦ����")==0)
	            			{
	            				for(int j=0;j<province1.length;j++)
            					{
            						if(split[3].compareTo(province1[j])==0)
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
			System.out.print("��ȡ�ļ�����1");
		}
	}
	
	public static void write_txt(String path)//���������������txt�ļ���
	{
		try
		{
			people_count=first_give();//������ʡ�еĵ�������ʼ��
			File afile=new File(path);
			if(!afile.exists()){                //�ж��ļ��Ƿ����
                afile.createNewFile();        //�����ļ�
            }
			OutputStreamWriter write1 = new OutputStreamWriter(
                    new FileOutputStream(afile),"UTF-8");
			 BufferedWriter write = new BufferedWriter(write1);
			File file =new File(log);//��־�ļ����ڵ��ļ���
			File[] names= file.listFiles();
			for(File s:names)
			{
				String filename=s.getName();
				int dot = filename.lastIndexOf('.');
				filename=filename.substring(0, dot);
				if(date==null)
				{
					read_txt(s.getPath());
				}
				else if((filename.compareTo(date))<=0)
				{
					read_txt(s.getPath());
				}
			}
			//����������޶�
			if(province[0]==null)//û�������κ�ʡ��ֻ���ȫ��  
			{
				write.write(province1[0]+" ");
				out(write,0);
				write.newLine();//����
				String last_line="// ���ĵ�������ʵ���ݣ���������ʹ��";
				write.write(last_line);
			}
			else
			{
				for(int i=0;i<province.length;i++)
				{
					int province_number;
					for(int j=0;j<province1.length;j++)
					{
						if(province[i]==province1[j])//�ж�ʡ�ݺͶ�Ӧ����
						{
							write.write(province1[j]+" ");
							province_number=j;
							out(write,province_number);
							write.newLine();//����
						}
					}
				}
				String last_line="// ���ĵ�������ʵ���ݣ���������ʹ��";
				write.write(last_line);
			}
			 //�ر������
			 write.flush();  
	         write.close();
		}
		catch(Exception e) 
		{	
			System.out.print("��ȡ�ļ�����2");
		}
	}
	public static void out(BufferedWriter write,int province_number)//����type���ͽ������
	{
		if(type[0]!=null)
		{
			for(int i=0;i<type.length;i++)
			{
				if(type[i]=="ip")
				{
					try
					{
						String a="��Ⱦ����"+people_count[province_number][0]+"�� ";
						write.write(a);
					}
					catch(Exception e)
					{
						System.out.print("�����������");
					}
				}
				else if(type[i]=="sp")
				{
					try
					{
						String a="���ƻ���"+people_count[province_number][1]+"�� ";
						write.write(a);
					}
					catch(Exception e)
					{
						System.out.print("�����������");
					}
				}
				else if(type[i]=="cure")
				{
					try
					{
						String a="����"+people_count[province_number][2]+"�� ";
						write.write(a);
					}
					catch(Exception e)
					{
						System.out.print("�����������");
					}
				}
				else if(type[i]=="dead")
				{
					try
					{
						String a="����"+people_count[province_number][3]+"�� ";
						write.write(a);
					}
					catch(Exception e)
					{
						System.out.print("�����������");
					}
				}
			}
		}
		else 
		{
			try
			{
				String a="��Ⱦ����"+people_count[province_number][0]+"�� "+"���ƻ���"+people_count[province_number][1]+"�� "+
						"����"+people_count[province_number][2]+"�� "+"����"+people_count[province_number][3]+"��";
				
				write.write(a);
			}
			catch(Exception e)
			{
				System.out.print("�����������");
			}
		}
	}
}
