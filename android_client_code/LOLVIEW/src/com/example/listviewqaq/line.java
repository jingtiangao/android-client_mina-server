package com.example.listviewqaq;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

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

public class line extends Activity {

	private Handler handler;
	
	private XYSeries series;
	private XYMultipleSeriesDataset mDataset;
	private GraphicalView chart;
	private XYMultipleSeriesRenderer renderer;
	private Context context;
	
	private String itemtext;
	private Thread con;
	private Socket socket;//socket
	private int position;
	private String id1;
	private char  dingyue;
	int[] xv = new int[100];
	int[] yv = new int[100];
    private SendThread ST;
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

		chart = ChartFactory.getLineChartView(context, getDemoDataset(), getDemoRenderer());
		layout.addView(chart, new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));
		String aa="1"+dingyue+"";
			aa=aa+id1;
			ST=new SendThread(aa);
			con=new Thread(ST) ;//开网络通信线程
			con.start();
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
			
				updateChart(msg.arg1);
				
			}
		};

		
		

	}
	//另起一个线程进行网络通信操作
  	public class SendThread implements Runnable{
  		String ss;
  		boolean stop=false;
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
           
            
            int b=0;
         
	        String  recive=br.readLine();
	        Message msg =new Message();
        	b=Integer.valueOf(recive).intValue();
        	
        	msg.arg1=b;
        	handler.sendMessage(msg);
	       
	        while(recive != null&&stop==false)
            {
            	
                	
	        	   out.println(ss);
	        	
                	//msg1.arg1= b;
	        	    recive=br.readLine() ;
	        	    b=Integer.valueOf(recive).intValue();
                	
                	Message msg1 =new Message();
                	msg1.arg1=b;
                	handler.sendMessage(msg1);
                   try 
              	   {
              		Thread.currentThread().sleep(1000);//����
              	   }
              	   catch(Exception e)
              	   {              		   
              	   }
            	
            }
	        
        	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	 }
  		
  	}
	private XYMultipleSeriesDataset getDemoDataset() {
		mDataset = new XYMultipleSeriesDataset();
        final int nr = 10;
        
        for (int i = 0; i < 2; i++) {
           series = new XYSeries(""+itemtext  + (i + 1));
          
            
         
            mDataset.addSeries(series);
        }
        return mDataset;
      }
	private XYMultipleSeriesRenderer getDemoRenderer() {
		renderer = new XYMultipleSeriesRenderer();
		if(dingyue=='d'){
        renderer.setChartTitle("获得金钱值");
        renderer.setXTitle("比赛时间");
        renderer.setYTitle("金钱");
		}else if(dingyue=='f'){
			renderer.setChartTitle("实时补兵值");
	        renderer.setXTitle("比赛时间");
	        renderer.setYTitle("数量");
		}
        
        renderer.setAxisTitleTextSize(20);
        renderer.setChartTitleTextSize(20);
        renderer.setLabelsTextSize(20);
        renderer.setLegendTextSize(20);
        renderer.setPointSize(5f);
        renderer.setApplyBackgroundColor(true);//设置背景颜色
        renderer.setBackgroundColor(Color.BLACK);
        renderer.setMargins(new int[] {20, 30, 15, 0});
        XYSeriesRenderer r = new XYSeriesRenderer();
        r.setColor(Color.BLUE);
        r.setPointStyle(PointStyle.SQUARE);
        r.setFillBelowLine(true);
        r.setFillBelowLineColor(Color.BLACK);
        r.setFillPoints(true);
        renderer.addSeriesRenderer(r);
        r = new XYSeriesRenderer();
        r.setPointStyle(PointStyle.CIRCLE);
        r.setColor(Color.GREEN);
        r.setFillPoints(true);
        renderer.addSeriesRenderer(r);
        renderer.setAxesColor(Color.DKGRAY);
        renderer.setLabelsColor(Color.LTGRAY);
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

	

	

	private void updateChart(int a) {
		// 移除数据集中旧的点集
		mDataset.removeSeries(series);
		// 判断当前点集中到底有多少点，因为屏幕总共只能容纳100个，所以当点数超过100时，清空点
		int length = series.getItemCount();
		
		// 在数据集中添加新的点集
			series.add(length, a);
		mDataset.addSeries(series);

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
