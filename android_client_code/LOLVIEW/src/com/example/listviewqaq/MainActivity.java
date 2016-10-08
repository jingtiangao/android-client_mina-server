package com.example.listviewqaq;





import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.SectionIndexer;
import android.widget.Toast;
  
import android.widget.ListView;  
import android.widget.SimpleAdapter;  


import com.example.android.util.StringMatcher;
import com.example.android.widget.IndexableListView;


public class MainActivity extends Activity {
	private ArrayList<String> mItems;
	private IndexableListView mListView;
	private String[] Q={"","","","","","","","","",""};
	private Context context;
	private Socket socket;//socket 
	Handler handler;
	String[] ite={"单击在线用户查看其对战数据"};
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;
        new Thread(new ConnectThread("0")).start();//开网络通信线程
        mItems = new ArrayList<String>();
        handler =new Handler()
		{
			public void handleMessage(Message msg)
			{
				String str1;
				 str1= (String)msg.obj;//handler
				Q[msg.arg1]=str1;
				 updateitem(str1);// 刷新LIST
				super.handleMessage(msg);
			}
		};
		 for(int j=0;j<ite.length;j++)
         {
         mItems.add(ite[j]);
         }
         //Collections.sort(mItems);

         ContentAdapter adapter = new ContentAdapter(this,
                 android.R.layout.simple_list_item_1, mItems);
         
         mListView = (IndexableListView) findViewById(R.id.listview);
         mListView.setAdapter(adapter);
         mListView.setOnItemClickListener(new OnItemClickListener()
         {
         

 			@Override
 			public void onItemClick(AdapterView<?> parent, View view,
 					int position, long id) {
 				// TODO Auto-generated method stub
 				//Toast toast=Toast.makeText(getApplicationContext(),position+"" , Toast.LENGTH_SHORT);
 				//toast.show();
 				

				String ss=(String)parent.getAdapter().getItem(position);
 				Intent intent = null;
 			      
 		        intent = new Intent(context, list2.class);
 		       intent.putExtra("tex", ss);
 		       int i;
 		       for(i=0;i<Q.length;i++)
 		       {
 		    	if(Q[i].equals(ss))
 		    	{
 		    	break;	
 		    	}
 		    			
 		       }
 		       intent.putExtra("id", i+"");
 		    
 		        startActivity(intent);
 				
 				
 				
 			}
         	});
         mListView.setFastScrollEnabled(true);	
       
    }
    public void updateitem(String mes)
    {
    	 
         mItems.add(mes);
         
         //Collections.sort(mItems); //no collection

         ContentAdapter adapter = new ContentAdapter(this,
                 android.R.layout.simple_list_item_1, mItems);
         
         mListView = (IndexableListView) findViewById(R.id.listview);
         mListView.setAdapter(adapter);
         
        
    }
  //另起一个线程进行网络通信操作
  	public class ConnectThread implements Runnable{
  		String ss;
  		
		public ConnectThread(String ss)
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
        	msg.obj=recive;
        	msg.arg1=b; 
        	handler.sendMessage(msg);
        	
        	
	        while(recive != null)
            {
            	
                	
                
                	//msg1.arg1= b;
	        	    
                	b++;
                	recive=br.readLine() ;
                	Message msg1 =new Message();
                	msg1.obj=recive;
                	msg1.arg1=b; 
                	handler.sendMessage(msg1);
                	
            	
            }
	        
        	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
  		
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