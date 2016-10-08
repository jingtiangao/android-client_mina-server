package com.example.listviewqaq;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.chart.TimeChart;

import org.achartengine.model.CategorySeries;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.DialRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import org.achartengine.renderer.DialRenderer.Type;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.SectionIndexer;
import android.widget.Toast;

import com.example.android.util.StringMatcher;
import com.example.android.widget.IndexableListView;


public class list2 extends Activity {
	private ArrayList<String> mItems;
	private IndexableListView mListView;
	private String itemtext;
	private Context context;
	private String id1;
	private Socket socket;//socket
	Handler handler;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
      //使用Intent对象得到FirstActivity传递来的参数
        context=this;
        Intent intent = getIntent();
       itemtext = intent.getStringExtra("tex");
               id1=intent.getStringExtra("id");
        mItems = new ArrayList<String>();
        mItems.add("在线用户："+itemtext);
        mItems.add("敌我实力对比");
        mItems.add("敌我团队贡献对比");
        mItems.add("个人实时英雄数据");
       // Collections.sort(mItems);
        ContentAdapter adapter = new ContentAdapter(this,
                android.R.layout.simple_list_item_1, mItems);
        
        mListView = (IndexableListView) findViewById(R.id.listview1);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new OnItemClickListener()
        {
        

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Toast toast=Toast.makeText(getApplicationContext(),position+"QAQ" , Toast.LENGTH_SHORT);
				toast.show();
				
				//char[] aa={'1',(char)position,};
				//aa=((aa.toString())+itemtext).toCharArray();
				//new Thread(new SendThread(aa)).start() ;//开网络通信线程
				if(position==1)
				{
					//Intent intent = ChartFactory.getLineChartIntent(context, getDemoDataset(), getDemoRenderer());
				      //startActivity(intent);
					Intent intent = new Intent(context, list3.class);
					intent.putExtra("tex", itemtext);
					intent.putExtra("pos", position);
					intent.putExtra("id", id1);
					startActivity(intent);
				}
				else if(position==2)
				{
					Intent intent = new Intent(context, list3.class);
					intent.putExtra("tex", itemtext);
					intent.putExtra("pos", position);
					intent.putExtra("id", id1);
					startActivity(intent);
				}else if(position==3){
					
					
						Intent intent = new Intent(context, list3.class);
						intent.putExtra("tex", itemtext);
						intent.putExtra("pos", position);
						intent.putExtra("id", id1);
						startActivity(intent);
					
				}else if(position==4){
					Intent intent = new Intent(context, ziliao.class);
					intent.putExtra("tex", itemtext);
					intent.putExtra("pos", position);
					intent.putExtra("id", id1);
					startActivity(intent);
					
				}else if(position==6){
					Intent intent = execute(context);
					intent.putExtra("tex", itemtext);
					intent.putExtra("pos", position);
					intent.putExtra("id", id1);
					startActivity(intent);
					
				}
				
				
			}
        });
        mListView.setFastScrollEnabled(true);//设置Listview结束
        
        
    }
    
    
    

      private XYMultipleSeriesDataset getDateDemoDataset() {
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        final int nr = 10;
        long value = new Date().getTime() - 3 * TimeChart.DAY;
        Random r = new Random();
        for (int i = 0; i < 2; i++) {
          TimeSeries series = new TimeSeries("Demo series " + (i + 1));
          for (int k = 0; k < nr; k++) {
            series.add(new Date(value + k * TimeChart.DAY / 4), 20 + r.nextInt() % 100);
          }
          dataset.addSeries(series);
        }
        return dataset;
      }

      

   
	
      public XYMultipleSeriesRenderer getBarDemoRenderer() {
        XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
        renderer.setAxisTitleTextSize(16);
        renderer.setChartTitleTextSize(20);
        renderer.setLabelsTextSize(15);
        renderer.setLegendTextSize(15);
        renderer.setMargins(new int[] {20, 30, 15, 0});
        XYSeriesRenderer r = new XYSeriesRenderer();
        r.setColor(Color.BLUE);
        renderer.addSeriesRenderer(r);
        r = new XYSeriesRenderer();
        r.setColor(Color.GREEN);
        renderer.addSeriesRenderer(r);
        return renderer;
      }

   

      public Intent execute(Context context) {
    	    CategorySeries category = new CategorySeries("个人暴击最高值与最低值范围图");
    	    category.add("最近一次暴击伤害", 750);
    	    category.add("最小暴击伤害", 650);
    	    category.add("最高暴击伤害", 900);
    	    DialRenderer renderer = new DialRenderer();
    	    renderer.setChartTitleTextSize(20);
    	    renderer.setLabelsTextSize(15);
    	    renderer.setLegendTextSize(15);
    	    renderer.setMargins(new int[] {20, 30, 15, 0});
    	    SimpleSeriesRenderer r = new SimpleSeriesRenderer();
    	    r.setColor(Color.BLUE);
    	    renderer.addSeriesRenderer(r);
    	    r = new SimpleSeriesRenderer();
    	    r.setColor(Color.rgb(0, 150, 0));
    	    renderer.addSeriesRenderer(r);
    	    r = new SimpleSeriesRenderer();
    	    r.setColor(Color.GREEN);
    	    renderer.addSeriesRenderer(r);
    	    renderer.setLabelsTextSize(20);
    	    renderer.setApplyBackgroundColor(true);
    	    renderer.setBackgroundColor(Color.BLACK);
    	    renderer.setLabelsColor(Color.WHITE);
    	    
    	    renderer.setShowLabels(true);
    	    renderer.setVisualTypes(new DialRenderer.Type[] {Type.ARROW, Type.NEEDLE, Type.NEEDLE});
    	    renderer.setMinValue(0);
    	    renderer.setMaxValue(1500);
    	    return ChartFactory.getDialChartIntent(context, category, renderer, "个人暴击最高值与最低值范围图");
    	  }
    private class ContentAdapter extends ArrayAdapter<String> implements SectionIndexer {
    	
    	private String mSections = "#ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    	
		public ContentAdapter(Context context, int textViewResourceId,
				List<String> objects) {
			super(context, textViewResourceId, objects);
		}
        
		@Override
		public int getPositionForSection(int section) {
			// If there is no item for current section, previous section will be selected
			for (int i = section; i >= 0; i--) {
				for (int j = 0; j < getCount(); j++) {
					if (i == 0) {
						// For numeric section
						for (int k = 0; k <= 9; k++) {
							if (StringMatcher.match(String.valueOf(getItem(j).charAt(0)), String.valueOf(k)))
								return j;
						}
					} else {
						if (StringMatcher.match(String.valueOf(getItem(j).charAt(0)), String.valueOf(mSections.charAt(i))))
							return j;
					}
				}
			}
			return 0;
		}

		@Override
		public int getSectionForPosition(int position) {
			return 0;
		}

		@Override
		public Object[] getSections() {
			String[] sections = new String[mSections.length()];
			for (int i = 0; i < mSections.length(); i++)
				sections[i] = String.valueOf(mSections.charAt(i));
			return sections;
		}
    }
}