package gcy;
import java.io.BufferedReader;  
import java.io.BufferedWriter;  
import java.io.InputStreamReader;  
import java.io.OutputStreamWriter;  
import java.io.PrintWriter;  
import java.net.ServerSocket;  
import java.net.Socket;  
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import gcy.data; 
public class Server implements Runnable  
{  
	static int []  a1 ={1,2,3,4,0};
	  static Map<String,data> D=new HashMap<String,data>();//��ӳ��������ݹ���
	public static void init()//��ʼ��ӳ��?����500��data����ÿ��data����������������
	{
      for(int i=0;i<500;i++)
      {
       data Q= new data();
       D.put(Integer.toString(i), Q);
      }
	}
    public void run()  
    {  
        try  
        {  
        	
            ServerSocket serverSocket = new ServerSocket(54321);  
            while (true)  
            {  
            	
                Socket client = serverSocket.accept();  
                System.out.println("accept");  
                try  
                {  
                	
                    BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));  
                    String str = in.readLine();  
                    System.out.println("read:" + str);    
                  
                    PrintWriter out = new PrintWriter( new BufferedWriter( new OutputStreamWriter(client.getOutputStream())),true);        
                  //  out.println(str); 
                   for(int i=0;i<30;i++)
                   {
                	   out.println(D.get(str).a[0]);
                	   
                	  // if(str=="stop")
                	    // break;
                		   
                	   
                	   try 
                	   {
                		Thread.currentThread().sleep(1000);//����
                	   }
                	   catch(Exception e)
                	   {              		   
                	   }
                   }
                 //�ر���
                    out.close();  
                    in.close();  
                }  
                catch (Exception e)  
                {  
                    System.out.println(e.getMessage());  
                    e.printStackTrace();  
                }  
                finally  
                {  
                	//�ر� 
                    client.close();  
                    System.out.println("close");  
                }  
            }  
        }  
        catch (Exception e)  
        {  
            System.out.println(e.getMessage());  
        }  
    }  
    
    
  //main������������ 
    public static void main(String a[])  //main������һ����̬�ķ�������������þ�̬����
    {  
    	
    	init();
    	//��ʱ����ʼ��
    	Timer timer = new Timer();
    	timer.schedule(new MyTask(), 100, 1000);//��100�����ִ�д�����,ÿ�μ��2��ִ��һ��,����һ��Data����,�Ϳ�����ĳ���̶���ʱ��ִ���������.
    	//�����������߳�
        Thread desktopServerThread = new Thread(new Server());  
        desktopServerThread.start();  							//
    }
  //�þ�̬�ڲ���ķ�������ʱ������Ϊÿ��1S�͸��һ��ӳ��������ж�������
    static class MyTask extends java.util.TimerTask{
    	public void run()
    	{
    		for(int i=0;i<500;i++)
    	      {
    			
    			(D.get(Integer.toString(i))).change();
    	      
    	       
    	      }
    	}
    }
    
    
}  