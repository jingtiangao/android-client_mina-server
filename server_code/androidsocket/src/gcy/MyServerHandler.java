package gcy;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public class MyServerHandler extends IoHandlerAdapter{

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		System.out.println("exceptionCaught");
	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		String fuck=(String)message;
		char[] s =  fuck.toCharArray();
		
		
		
		String ss;
		System.out.println("messageReceived: " + fuck);
		if(s[0]=='0'){
			for(int i=0;i<10;i++)
			{
			ss=gcy.MinaServer.D.get(""+i).name;
			session.write( ss+"\n");
			
			}
		}else if(s[0]=='1'&&(s[1]=='4'||s[1]=='5'||s[1]=='6'||s[1]=='7'||s[1]=='8'||s[1]=='9'||s[1]=='a')){
			
			
		if(s[1]=='4'){
			int sum=0;
			for(int i=0;i<10;i++ )
			{
				sum+=gcy.MinaServer.D.get(""+i).jisha[1];
			}
			for(int i=0;i<10;i++)
			{  
				int a22=Integer.parseInt(""+s[2])+i;
				if(Integer.parseInt(""+s[2])+i<10){
				ss=gcy.MinaServer.D.get(Integer.parseInt(""+s[2])+i+"").name;
				session.write( ss+"\n");
				System.out.println(ss);
			   }else{
				   ss=gcy.MinaServer.D.get(Integer.parseInt(""+s[2])+i-10+"").name;
					session.write( ss+"\n");
					System.out.println(ss);
			   }
			}
			ss=(gcy.MinaServer.D.get(""+s[2]).jisha[0]+gcy.MinaServer.D.get(""+s[2]).jisha[2])*100/sum+"";
			session.write( ss+"\n");
			
			
			for(int i=0;i<9;i++ )
			{
				ss=(gcy.MinaServer.D.get(""+i).jisha[0]+gcy.MinaServer.D.get(""+i).jisha[2])*100/sum+"";
				session.write( ss+"\n");
				System.out.println(ss);
						
		    }
			
		}else if(s[1]=='5'){
				int sum=0;
				for(int i=0;i<10;i++ )
				{
					sum+=gcy.MinaServer.D.get(""+i).shanghai[0];
				}
				for(int i=0;i<10;i++)
				{  
					int a22=Integer.parseInt(""+s[2])+i;
					if(Integer.parseInt(""+s[2])+i<10){
					ss=gcy.MinaServer.D.get(Integer.parseInt(""+s[2])+i+"").name;
					session.write( ss+"\n");
					System.out.println(ss);
				   }else{
					   ss=gcy.MinaServer.D.get(Integer.parseInt(""+s[2])+i-10+"").name;
						session.write( ss+"\n");
						System.out.println(ss);
				   }
				}
				ss=gcy.MinaServer.D.get(""+s[2]).shanghai[0]*100/sum+"";
				session.write( ss+"\n");
				
				
				for(int i=0;i<9;i++ )
				{
					ss=gcy.MinaServer.D.get(""+i).shanghai[0]*100/sum+"";
					session.write( ss+"\n");
					System.out.println(ss);
					
					
					
			    }
			}else if(s[1]=='6'){
				int sum=0;
				for(int i=0;i<10;i++ )
				{
					sum+=gcy.MinaServer.D.get(""+i).qita[0];
				}
				for(int i=0;i<10;i++)
				{  
					int a22=Integer.parseInt(""+s[2])+i;
					if(Integer.parseInt(""+s[2])+i<10){
					ss=gcy.MinaServer.D.get(Integer.parseInt(""+s[2])+i+"").name;
					session.write( ss+"\n");
					System.out.println(ss);
				   }else{
					   ss=gcy.MinaServer.D.get(Integer.parseInt(""+s[2])+i-10+"").name;
						session.write( ss+"\n");
						System.out.println(ss);
				   }
				}
				ss=gcy.MinaServer.D.get(""+s[2]).qita[0]*100/sum+"";
				session.write( ss+"\n");
				
				
				for(int i=0;i<9;i++ )
				{
					ss=gcy.MinaServer.D.get(""+i).qita[0]*100/sum+"";
					session.write( ss+"\n");
					System.out.println(ss);
					
					
					
			    }
				
			}else if(s[1]=='7'){
				int sum=0;
				for(int i=0;i<10;i++ )
				{
					sum+=gcy.MinaServer.D.get(""+i).chengshou[1];
				}
				for(int i=0;i<10;i++)
				{  
					int a22=Integer.parseInt(""+s[2])+i;
					if(Integer.parseInt(""+s[2])+i<10){
					ss=gcy.MinaServer.D.get(Integer.parseInt(""+s[2])+i+"").name;
					session.write( ss+"\n");
					System.out.println(ss);
				   }else{
					   ss=gcy.MinaServer.D.get(Integer.parseInt(""+s[2])+i-10+"").name;
						session.write( ss+"\n");
						System.out.println(ss);
				   }
				}
				ss=gcy.MinaServer.D.get(""+s[2]).chengshou[1]*100/sum+"";
				session.write( ss+"\n");
				
				
				for(int i=0;i<9;i++ )
				{
					ss=gcy.MinaServer.D.get(""+i).chengshou[1]*100/sum+"";
					session.write( ss+"\n");
					System.out.println(ss);
							
			    }
				
			}else if(s[1]=='8'){
				
				for(int i=0;i<10;i++)
				{  
					int a22=Integer.parseInt(""+s[2])+i;
					if(Integer.parseInt(""+s[2])+i<10){
					ss=gcy.MinaServer.D.get(Integer.parseInt(""+s[2])+i+"").name;
					session.write( ss+"\n");
					System.out.println(ss);
				   }else{
					   ss=gcy.MinaServer.D.get(Integer.parseInt(""+s[2])+i-10+"").name;
						session.write( ss+"\n");
						System.out.println(ss);
				   }
				}
				ss=(gcy.MinaServer.D.get(""+s[2]).jisha[0]+gcy.MinaServer.D.get(""+s[2]).jisha[2])/(gcy.MinaServer.D.get(""+s[2]).jisha[1]+1)+"";
				session.write( ss+"\n");
				
				
				for(int i=0;i<9;i++ )
				{
					ss=(gcy.MinaServer.D.get(""+i).jisha[0]+gcy.MinaServer.D.get(""+i).jisha[2])/(gcy.MinaServer.D.get(""+i).jisha[1]+1)+"";
					session.write( ss+"\n");
					System.out.println(ss);
							
			    }
			}else if(s[1]=='9'){
				
				for(int i=0;i<10;i++)
				{  
					int a22=Integer.parseInt(""+s[2])+i;
					if(Integer.parseInt(""+s[2])+i<10){
					ss=gcy.MinaServer.D.get(Integer.parseInt(""+s[2])+i+"").name;
					session.write( ss+"\n");
					System.out.println(ss);
				   }else{
					   ss=gcy.MinaServer.D.get(Integer.parseInt(""+s[2])+i-10+"").name;
						session.write( ss+"\n");
						System.out.println(ss);
				   }
				}
				ss=(gcy.MinaServer.D.get(""+s[2]).qita[2]+gcy.MinaServer.D.get(""+s[2]).qita[3])+"";
				session.write( ss+"\n");
				
				
				for(int i=0;i<9;i++ )
				{
					ss=(gcy.MinaServer.D.get(""+i).qita[2]+gcy.MinaServer.D.get(""+i).qita[3])+"";
					session.write( ss+"\n");
					System.out.println(ss);
							
			    }
				
			}else if(s[1]=='a'){
				
				for(int i=0;i<10;i++)
				{  
					int a22=Integer.parseInt(""+s[2])+i;
					if(Integer.parseInt(""+s[2])+i<10){
					ss=gcy.MinaServer.D.get(Integer.parseInt(""+s[2])+i+"").name;
					session.write( ss+"\n");
					System.out.println(ss);
				   }else{
					   ss=gcy.MinaServer.D.get(Integer.parseInt(""+s[2])+i-10+"").name;
						session.write( ss+"\n");
						System.out.println(ss);
				   }
				}
				ss="1";
				session.write( ss+"\n");
				
				
				for(int i=0;i<9;i++ )
				{
					ss="0";
					session.write( ss+"\n");
					System.out.println(ss);
							
			    }
			}
					
		}else if(s[0]=='1'&&(s[1]=='d'||s[1]=='f')){
			
			if(s[1]=='d'){
			ss=gcy.MinaServer.D.get(""+s[2]).qita[0]+"";
			session.write( ss+"\n");
			}else if(s[1]=='f'){
				ss=gcy.MinaServer.D.get(""+s[2]).qita[2]+"";
				session.write( ss+"\n");
			}
		}
		else
		{
			
		}
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		System.out.println("messageSent");
		
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		System.out.println("sessionClosed");
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		System.out.println("sessionCreated");
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		System.out.println("sessionIdle");
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		System.out.println("sessionOpened");
	}
	

}
