	package gcy;
import java.util.Random;


public class data {
	public int [] a={0,0};
	String name;
    public int weizhi;
    public int tuandui;
    public int[] jisha={0,0,0,0,0};
    public int[] shanghai={0,0,0};
    public int[] chengshou={0,0,0,0};
    public int[] qita={0,0,0,0};
    public int id;
    private int count=0;
    public void change()//
    {
    	Random rand =new Random();
    	a[0]=rand.nextInt(10);
    	a[1]=rand.nextInt(60);
    	if (count == 60)
    	{
    		count=0;
    	}
    	
    	shanghai[1]+=100;
    	shanghai[2]+=100;
    	chengshou[1]+=100;
    	chengshou[2]+=100;
    	chengshou[0]+=3;
    	qita[0]+=1;
    	if(count==10)
    	{
    		qita[1]+=1;
    	}
    	if(count == 59)
    	{
    		qita[3]++;
    	}
    	if(tuandui==0)
    	{
    	chengshou[2]+=rand.nextInt(1000);
    	chengshou[3]+=rand.nextInt(1000);
    	}
    	else if(tuandui==1)		
    	{
    	  shanghai[1]+=rand.nextInt(500);
    	}
    	else if(tuandui==2)
    	{
    	shanghai[2]+=rand.nextInt(500);
    	}
    	else if(tuandui==3)
    	{
    	chengshou[0]+=rand.nextInt(200);	
    	}
    	else
    	{   	
    	}
    	
    	if(weizhi==0)
    	{
    		qita[0]+=rand.nextInt(20);
    		qita[2]+=1;
    		
    	}
    	else if(weizhi==1)
    	{
    		qita[0]+=rand.nextInt(30);
    		qita[2]+=1;
    	}
    	else if(weizhi ==2)
    	{
    		qita[0]+=rand.nextInt(30);
    		qita[2]+=2;
    	}
    	else if(weizhi ==4)
    	{
    		qita[0]+=rand.nextInt(30);
    		qita[3]+=1;
    	}
    	else
    	{
    		qita[0]+=rand.nextInt(10);
    	}
    	//GC garbage collection delete
            shanghai[0]=shanghai[1]+shanghai[2];
            chengshou[1]=chengshou[2]+chengshou[3];
    	count++;
    }
}
