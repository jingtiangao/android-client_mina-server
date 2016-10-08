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
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

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


public class ziliao extends Activity {
	private ArrayList<String> mItems;
	private IndexableListView mListView;
	private String itemtext;
	private Context context;
	private String id1;
	private Socket socket;//socket
	private int position;

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
        mItems.add("A.当前用户:"+itemtext);
        mItems.add("B.当前位置:"+"中单");
        mItems.add("C.团队功能:"+"AP");
        mItems.add("D.当前击杀:"+"4");
        mItems.add("E.死亡次数:"+"2");
        mItems.add("F.助攻次数:"+"20");
        
        
      
        Collections.sort(mItems);

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
//				if(position==2)
//				{
//					//Intent intent = ChartFactory.getLineChartIntent(context, getDemoDataset(), getDemoRenderer());
//				      //startActivity(intent);
//					Intent intent = new Intent(context, line.class);
//					intent.putExtra("tex", itemtext);
//					intent.putExtra("pos", position);
//					intent.putExtra("id", id1);
//					startActivity(intent);
//				}
//				else if(position==9)
//				{
//					Intent intent = new Intent(context, bar.class);
//					intent.putExtra("tex", itemtext);
//					intent.putExtra("pos", position);
//					intent.putExtra("id", id1);
//					startActivity(intent);
//				}else if(position==7){
//					
//					
//						Intent intent = new Intent(context, pie.class);
//						intent.putExtra("tex", itemtext);
//						intent.putExtra("pos", position);
//						intent.putExtra("id", id1);
//						startActivity(intent);
//					
//				}
				
				
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
