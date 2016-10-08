package com.example.listviewqaq;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer.Orientation;

import com.example.listviewqaq.line.SendThread;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class bar extends Activity {

	private Handler handler;
	private Thread con;
	private CategorySeries series;
	private XYSeries Xseries;
	private XYMultipleSeriesDataset mDataset;
	private GraphicalView chart;
	private XYMultipleSeriesRenderer renderer;
	private Context context;
	private SendThread ST;
	private String itemtext;
	private char  dingyue;
	private Socket socket;//socket
	private int position;
	private String id1;
	private bao bb2=new bao();
	private bao bb=new bao();
	int[] xv = new int[100];
	int[] yv = new int[100];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.line);
		
		context = getApplicationContext();
		Intent intent = getIntent();
		position=intent.getIntExtra("pos",-1);
	    itemtext = intent.getStringExtra("tex");
	    id1=intent.getStringExtra("id");
	    dingyue=intent.getCharExtra("dingyue", 'Q');
		LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout1);

		chart = ChartFactory.getBarChartView(context, getBarDemoDataset(), getDemoRenderer(), Type.DEFAULT);
		layout.addView(chart, new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));
		String aa="1"+dingyue;
			aa=aa+id1;
			ST=new SendThread(aa);
			con=new Thread(ST) ;//开网络通信线程
			con.start();
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				 bb2=(bao) msg.obj;
				updateChart(bb2.data,bb2.name);
				
			}
		};

		
		

	}
	//另起一个线程进行网络通信操作
  	public class SendThread implements Runnable{
  		String ss;
  		boolean stop=false;
  		int[] dat={0,0,0,0,0,0,0,0,0,0};
  		String[] na={"","","","","","","","","",""};
  		
		public SendThread(String ss)//暗号
		{
		super();
		this.ss = ss;
		
		}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		try {
			socket = new Socket("10.0.2.2",9898);
			PrintWriter out = new PrintWriter( new BufferedWriter( new OutputStreamWriter(socket.getOutputStream())),true);        
            out.println(ss);//发送订阅数据 
				  
            BufferedReader	br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String  recive =null;
            
            
            
            int b=0;
            do{
            
            out.println(ss);//发送订阅数据
            for(int i=0;i<10;i++){
                recive=br.readLine();
                na[i]=recive;
                }
            for(int i=0;i<10;i++){
            recive=br.readLine();
            dat[i]=Integer.valueOf(recive).intValue();
            }
	        Message msg =new Message();
	        
	        bb.name=na;
        	bb.data=dat;
	        
        	msg.obj=bb;
        	
        	handler.sendMessage(msg);
        	 try 
        	   {
        		Thread.currentThread().sleep(1000);//
        	   }
        	   catch(Exception e)
        	   {              		   
        	   }
            }while(recive != null && stop ==false);
//            {
//            	
//	        	  
//                
//                	//msg1.arg1= b;
//	        	    recive=br.readLine() ;
//	        	    b=Integer.valueOf(recive).intValue();
//                	
//                	Message msg1 =new Message();
//                	msg1.arg1=b;
//                	handler.sendMessage(msg1);
//            	
//            }
	        
        	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	 }
  		
  	}
	private XYMultipleSeriesDataset getDemoDataset() {
		mDataset = new XYMultipleSeriesDataset();
        final int nr = 10;
        
        
            
       
        return mDataset;
      }
	private XYMultipleSeriesRenderer getDemoRenderer() {
		renderer = new XYMultipleSeriesRenderer();
		//renderer.setOrientation(Orientation.VERTICAL);
		if(dingyue=='4'){
        renderer.setChartTitle("敌方我方实时参团率");
        
        renderer.setYTitle("百分比");
        renderer.setXTitle("");
		}else if(dingyue=='5'){
			renderer.setChartTitle("输出率");
			 renderer.setXTitle("");
	        renderer.setYTitle("百分比");
		}else if(dingyue=='6'){
			renderer.setChartTitle("金钱比");
			 renderer.setXTitle("");
	        renderer.setYTitle("百分比");
		}else if(dingyue=='7'){
			renderer.setChartTitle("承受伤害率");
			 renderer.setXTitle("");
	        renderer.setYTitle("百分比");
		}else if(dingyue=='8'){
			renderer.setChartTitle("实时KDA值");
			 renderer.setXTitle("");
	        renderer.setYTitle("KDA");
		}else if(dingyue=='9'){
			renderer.setChartTitle("补兵&中立生物实时数量");
			 renderer.setXTitle("");
	        renderer.setYTitle("数量");
		}else if(dingyue=='a'){
			renderer.setChartTitle("击杀史诗级中立生物实时个数");
			 renderer.setXTitle("");
	        renderer.setYTitle("个数");
		}
        
        renderer.setAxisTitleTextSize(30);
        renderer.setChartTitleTextSize(30);
        renderer.setLabelsTextSize(20);
        renderer.setLegendTextSize(30);
        renderer.setPointSize(5f);
        renderer.setApplyBackgroundColor(true);//设置背景颜色
        renderer.setBackgroundColor(Color.BLACK);
        renderer.setMargins(new int[] {20, 30, 15, 0});
        XYSeriesRenderer r = new XYSeriesRenderer();
        
        r.setColor(Color.RED);
        r.setPointStyle(PointStyle.SQUARE);
        r.setFillBelowLine(true);
        r.setFillBelowLineColor(Color.BLACK);
        r.setFillPoints(true);
        renderer.addSeriesRenderer(r);//1
        
        r = new XYSeriesRenderer();
        r.setPointStyle(PointStyle.CIRCLE);
        r.setColor(Color.YELLOW);
        r.setFillPoints(true);       
        renderer.addSeriesRenderer(r);//2
        
        r = new XYSeriesRenderer();
        r.setPointStyle(PointStyle.CIRCLE);
        r.setColor(Color.YELLOW);
        r.setFillPoints(true);
        renderer.addSeriesRenderer(r);//3
        
        r = new XYSeriesRenderer();
        r.setPointStyle(PointStyle.CIRCLE);
        r.setColor(Color.YELLOW);
        r.setFillPoints(true);
        renderer.addSeriesRenderer(r);//4
        
        r = new XYSeriesRenderer();
        r.setPointStyle(PointStyle.CIRCLE);
        r.setColor(Color.YELLOW);
        r.setFillPoints(true);
        renderer.addSeriesRenderer(r);//5
        
        r = new XYSeriesRenderer();
        r.setPointStyle(PointStyle.CIRCLE);
        r.setColor(Color.BLUE);
        r.setFillPoints(true);
        renderer.addSeriesRenderer(r);//6
        
        r = new XYSeriesRenderer();
        r.setPointStyle(PointStyle.CIRCLE);
        r.setColor(Color.BLUE);
        r.setFillPoints(true);
        renderer.addSeriesRenderer(r);//7
        
        r = new XYSeriesRenderer();
        r.setPointStyle(PointStyle.CIRCLE);
        r.setColor(Color.BLUE);
        r.setFillPoints(true);
        renderer.addSeriesRenderer(r);//8
        
        r = new XYSeriesRenderer();
        r.setPointStyle(PointStyle.CIRCLE);
        r.setColor(Color.BLUE);
        r.setFillPoints(true);
        renderer.addSeriesRenderer(r);//9
        
        r = new XYSeriesRenderer();
        r.setPointStyle(PointStyle.CIRCLE);
        r.setColor(Color.BLUE);
        r.setFillPoints(true);
        renderer.addSeriesRenderer(r);//10
        
        
       renderer.setBarWidth(50f);
       renderer.setBarSpacing(4f);
        renderer.setAxesColor(Color.DKGRAY);
        renderer.setLabelsColor(Color.LTGRAY);
        renderer.setDisplayValues(true);//设置柱子上是否显示数量值
        for(int i=0;i<10;i++){
        ((XYSeriesRenderer) renderer.getSeriesRendererAt(i)).setDisplayChartValues(true);// 在第0条柱形图上显示数据
        ((XYSeriesRenderer) renderer.getSeriesRendererAt(i)).setChartValuesTextSize(20);
        }

        renderer.setXLabelsAlign(Align.LEFT);//刻度线与X轴坐标文字左侧对齐
        renderer.setYLabelsAlign(Align.LEFT);//Y轴与Y轴坐标文字左对齐
        renderer.setPanEnabled(true, true);//允许左右拖动,但不允许上下拖动.
        renderer.setXAxisMin(-8);
        renderer.setXAxisMax(20);
        
        return renderer;
      }
	   private void setChartSettings(XYMultipleSeriesRenderer renderer) {
	        renderer.setChartTitle("Chart demo");
	        renderer.setXTitle("x values");
	        renderer.setYTitle("y values");
	        renderer.setXAxisMin(0.5);
	        renderer.setXAxisMax(10.5);
	        renderer.setYAxisMin(0);
	        renderer.setYAxisMax(210);
	      }

	@Override
	public void onDestroy() {
		

		ST.stop=true;
	
		
		super.onDestroy();
	}

	

	private XYMultipleSeriesDataset getBarDemoDataset() {
         mDataset = new XYMultipleSeriesDataset();
        final int nr = 10;
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
        	if(i==0){
             series = new CategorySeries(""+itemtext  + (i + 1));
        	}else if(i<5&&i>0){
        	 series = new CategorySeries("我方队友" + (i + 1));
        	}else {
        		series = new CategorySeries("敌方队伍" + (i + 1));
        	}
            mDataset.addSeries(series.toXYSeries());
        }
//        for (int i = 0; i <10; i++) {
//          series = new CategorySeries("Demo series " + (i + 1));
//         
//        
//          series.add(100 + r.nextInt() % 100);
//          
//          mDataset.addSeries(series.toXYSeries());
//        }
        return mDataset;
      }

	private void updateChart(int a[],String[] name) {
		// 移除数据集中旧的点集
		
		//mDataset.removeSeries(Xseries);
		// 判断当前点集中到底有多少点，因为屏幕总共只能容纳100个，所以当点数超过100时，清空点
		
		
		// 在数据集中添加新的点集
		for(int i=0;i<10;i++)
		{
		mDataset.getSeriesAt(i).clear();
		
		mDataset.getSeriesAt(i).add(i, a[i]);
		mDataset.getSeriesAt(i).setTitle(name[i]);
		}
		Arrays.sort(a);
		renderer.setYAxisMin(0);
		
        renderer.setYAxisMax(a[9]+50);
		//mDataset.addSeries(Xseries);

		// 视图更新，没有这一步，曲线不会呈现动态
		// 如果在非UI主线程中，需要调用postInvalidate()，具体参考api
		
		chart.invalidate();
		
		


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
