package gcy;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
//the file is based on mina struct
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import gcy.MyServerHandler;
import gcy.MyTextLineFactory;
import gcy.Server.MyTask;
public class MinaServer {

	/**
	 * @param args
	 */
	static Map<String,data> D=new HashMap<String,data>();
	static Random rand;
	public static void init()
	{
		 rand =new Random();
      for(int i=0;i<10;i++)
      {
       data Q= new data();
       D.put(Integer.toString(i), Q);  
   	   D.get(Integer.toString(i)).id=i;
      }
      D.get("0").name="景天GAO";
      D.get("0").weizhi=0;
      D.get("0").tuandui=0;
      D.get("0").jisha[0]=rand.nextInt(10);
      D.get("0").jisha[1]=rand.nextInt(10);
      D.get("0").jisha[2]=rand.nextInt(10);
      D.get("0").jisha[3]=2;
      D.get("0").jisha[4]=3;
      D.get("1").name="dangdang123";
      D.get("1").weizhi=1;
      D.get("1").tuandui=2;
      D.get("1").jisha[0]=rand.nextInt(10);
      D.get("1").jisha[1]=rand.nextInt(10);
      D.get("1").jisha[2]=rand.nextInt(10);
      D.get("1").jisha[3]=8;
      D.get("1").jisha[4]=3;
      D.get("2").name="请叫我阳哥YY";
      D.get("2").weizhi=2;
      D.get("2").tuandui=1;
      D.get("2").jisha[0]=8;
      D.get("2").jisha[1]=1;
      D.get("2").jisha[2]=10;
      D.get("2").jisha[3]=4;
      D.get("2").jisha[4]=3;
      D.get("3").name="打铁还需自身硬";
      D.get("3").weizhi=3;
      D.get("3").tuandui=0;
      D.get("3").jisha[0]=0;
      D.get("3").jisha[1]=10;
      D.get("3").jisha[2]=30;
      D.get("3").jisha[3]=0;
      D.get("3").jisha[4]=0;
      D.get("4").name="奔放的小河";
      D.get("4").weizhi=4;
      D.get("4").tuandui=0;
      D.get("4").jisha[0]=5;
      D.get("4").jisha[1]=5;
      D.get("4").jisha[2]=5;
      D.get("4").jisha[3]=3;
      D.get("4").jisha[4]=4;
      //2队
      D.get("5").name="初夏冰";
      D.get("5").weizhi=0;
      D.get("5").tuandui=0;
      D.get("5").jisha[0]=4;
      D.get("5").jisha[1]=0;
      D.get("5").jisha[2]=2;
      D.get("5").jisha[3]=2;
      D.get("5").jisha[4]=3;
      D.get("6").name="夜倾风雪";
      D.get("6").weizhi=1;
      D.get("6").tuandui=2;
      D.get("6").jisha[0]=rand.nextInt(10);
      D.get("6").jisha[1]=rand.nextInt(10);
      D.get("6").jisha[2]=rand.nextInt(10);
      D.get("6").jisha[3]=1;
      D.get("6").jisha[4]=2;
      D.get("7").name="再见肖秀荣";
      D.get("7").weizhi=2;
      D.get("7").tuandui=1;
      D.get("7").jisha[0]=rand.nextInt(10);
      D.get("7").jisha[1]=rand.nextInt(10);
      D.get("7").jisha[2]=rand.nextInt(10);
      D.get("7").jisha[3]=4;
      D.get("7").jisha[4]=3;
      D.get("8").name="叫我党哥";
      D.get("8").weizhi=3;
      D.get("8").tuandui=0;
      D.get("8").jisha[0]=rand.nextInt(10);
      D.get("8").jisha[1]=rand.nextInt(10);
      D.get("8").jisha[2]=rand.nextInt(10);
      D.get("8").jisha[3]=0;
      D.get("8").jisha[4]=0;
      D.get("9").name="江涛";
      D.get("9").weizhi=4;
      D.get("9").tuandui=0;
      D.get("9").jisha[0]=rand.nextInt(10);
      D.get("9").jisha[1]=rand.nextInt(10);
      D.get("9").jisha[2]=rand.nextInt(10);
      D.get("9").jisha[3]=3;
      D.get("9").jisha[4]=4;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		init();
    	
    	Timer timer = new Timer();
    	timer.schedule(new MyTask(), 100, 1000);		
		
		try {
			NioSocketAcceptor acceptor = new NioSocketAcceptor();
			acceptor.setHandler(new MyServerHandler());
			acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new MyTextLineFactory()));
			acceptor.bind(new InetSocketAddress(9898));
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	static class MyTask extends java.util.TimerTask{
    	public void run()
    	{
    		for(int i=0;i<10;i++)
    	      {
    		
    			(D.get(i+"")).change();
    	      
    	       
    	      }
    	}
    }

}
