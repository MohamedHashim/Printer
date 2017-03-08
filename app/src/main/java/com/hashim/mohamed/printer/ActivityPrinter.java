package com.hashim.mohamed.printer;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;


public class ActivityPrinter extends Activity {

	
	
    android.pt.printer.Printer printer = null;
    EditText print_EditText =null;
    AlertDialog adia = null;
    String printer_status = null;
    //Bitmap  bmplogo = null;
    
    boolean openflg =false;
    
    Context context = null;
    
    private long mExitTime =0; 
    
    Builder builder = null;
    
    Button openButton = null;
    
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.activity_printer);
		
		printer = new android.pt.printer.Printer();

		context = ActivityPrinter.this;
		
		
		print_EditText = (EditText) findViewById(R.id.et_content); 
		
		builder = new Builder(this);
		if(printer.openPrinter() == 0)
		{
			openflg = true;
			show(context, "success ");
		}
		//openButton = (Button) findViewById(R.id.bt_openPrinter);
		
		//openPrinter(null);
		 
		//bmplogo = BitmapFactory.decodeResource(getResources(),R.drawable.logo);
		
		//int width = bmplogo.getWidth();   // 获取位图的宽  
	   // int height = bmplogo.getHeight(); // 获取位图的高  
	     
	   // int size = bmplogo.getByteCount();
	     
	   // Log.d("simon", "width ="+width);
	   // Log.d("simon", "height ="+height); 
	    
	   // Log.d("simon", "size=="+size);  
	    
	   // Log.d("simon", "height*width=="+height*width);  
	    
		        
	}   
	
	//消息提示对话框
	public  static void Messagebox(Context context,String info)
	{ 
		Builder builder = new Builder(context);    
		builder.setTitle("title");    
		builder.setMessage(info);          
		builder.setPositiveButton("yes", null);          
		builder.show();                              
	}   
	
	//消息提示土司
	public  static void Toast(Context context,String info)
	{ 
		Toast.makeText(context, info, Toast.LENGTH_LONG).show();
	}
	public void show(Context context ,String msg)
	{
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
		
	}
	public void queState_print(int status) {
		switch(status)
		{
			case -2:
				show(context,"time out");
				break;
			case -1:
				show(context,"query fail");
				break;
			case 0:
				show(context,"normal");
				break;
			case 1:
				show(context,"no paper");
				break;
				
			case 2:
				show(context,"hot");
				break;
			case 3:
				show(context,"hot and no paper");
				break;
			default:
				show(context,"invalid");
				break;
			
		}
	}
	/*
	public void openPrinter(View view)  
	{
		 
		 int res = -1;  
		 res = printer.open();
		 
		 if (openflg) {
			 
			 Toast(context,"printer is open");      
		}
		 else
		 {
		 
		     if(res == 0)
		     {
		    	 openflg = true;    
		    	 
		    	 openButton.setBackgroundColor(Color.rgb(0, 0, 0xff));  
		    	  
		    	 Toast(context,"open success");      
		     }  
		     else {       
		    	   
		    	 Toast(context,"open fail"); 
			} 
		 }
		   
	}
	*/
	
	public void zoonIn(View view)
	{
 	       builder.setTitle("set"); 	
	       builder.setIcon(R.drawable.ic_launcher);
	       builder.setMessage("ZoomIn");
	       view = View.inflate(this, R.layout.two_seekbar_dialog, null);
	       
	       final SeekBar seekBar_h = (SeekBar) view.findViewById(R.id.seek_bar_first);
	       final SeekBar seekBar_w = (SeekBar) view.findViewById(R.id.seek_bar_second);
	       
	       seekBar_h.setMax(4);	
	       seekBar_h.setProgress(1);
	       seekBar_w.setMax(4); 
	       seekBar_w.setProgress(1);	 					       
	       
	       final TextView tView_h = (TextView) view.findViewById(R.id.seek_text_first);
	       final TextView tView_w = (TextView) view.findViewById(R.id.seek_text_second);
	       
	       tView_h.setText("high X1"); 
	       tView_w.setText("wide X1");  	 	   
		
	       builder.setView(view);  
	       
	       builder.setNegativeButton("NO", null);
	       builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
			
			@Override 
			public void onClick(DialogInterface arg0, int arg1) { 
				// TODO Auto-generated method stub 
				int ret = printer.setZoonIn(seekBar_w.getProgress(), seekBar_h.getProgress());
				if(ret < 0)
				{
					Toast(context,"set fail");
				}

			}     
		  }); 
	       
	       seekBar_h.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
				
				@Override
				public void onStopTrackingTouch(SeekBar seekBar) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onStartTrackingTouch(SeekBar seekBar) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onProgressChanged(SeekBar seekBar, int progress,
						boolean fromUser) {
					 // TODO Auto-generated method stub 
					 if (seekBar_h.getProgress() < 1)   
					 {    
						 seekBar_h.setProgress(1);                               
					 }                        
					 
					 tView_h.setText("high X "+seekBar_h.getProgress());  
				}
			}); 
	       
	       seekBar_w.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
				
				@Override
				public void onStopTrackingTouch(SeekBar seekBar) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onStartTrackingTouch(SeekBar seekBar) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onProgressChanged(SeekBar seekBar, int progress,
						boolean fromUser) { 
					// TODO Auto-generated method stub 
					 if (seekBar_w.getProgress()<1)
					 {
						 seekBar_w.setProgress(1);
					 }
					
					 tView_w.setText("wide X "+seekBar_w.getProgress());  
				}
			}); 						       
	       
	     builder.show();   	 	       
	       
	 	
	} 
	
	
	public void set_align(View view)
	{
		
		 Builder builder = new Builder(this);
		
    	builder.setTitle("set_align"); 
    	builder.setIcon(R.drawable.ic_launcher);    
    	builder.setSingleChoiceItems(new String[]{"left","mid","right"}, 0, new DialogInterface.OnClickListener() {   
			
			@Override   
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
			    int ret = printer.setAlignType(which); 
			    
				if (ret<0) {      
					
					Toast(context,"set fail");            
				}	  
			    
			    dialog.dismiss();       
			}      
		}); 
    
    	builder.show();     		
	} 
	
	  
	public void set_l_margin(View view) 
	{  
		 
 	       builder.setTitle("set"); 	
	       builder.setIcon(R.drawable.ic_launcher);
	       builder.setMessage("left margin");
	       view = View.inflate(this, R.layout.seekbar_dialog, null);
	       final SeekBar seekBar_left_margin = (SeekBar) view.findViewById(R.id.seek_bar);
	  
	       seekBar_left_margin.setMax(255);
	       seekBar_left_margin.setProgress(0);  
	       
	       
	       final TextView tView_sleep = (TextView) view.findViewById(R.id.seek_text);
	       tView_sleep.setText("left margin：0");
	       
	       builder.setView(view); 
	       
	       builder.setPositiveButton("set", new DialogInterface.OnClickListener() {
				
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				
				int ret = printer.setLeftMargin(seekBar_left_margin.getProgress());
				
				if (ret<0) {  
				
					Toast(context,"set fail");      
				}	 			
				 
			}   
			
		  }); 
	       
	       seekBar_left_margin.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
				
				@Override
				public void onStopTrackingTouch(SeekBar seekBar) {  
					// TODO Auto-generated method stub  
					
				}
				
				@Override
				public void onStartTrackingTouch(SeekBar seekBar) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onProgressChanged(SeekBar seekBar, int progress,
						boolean fromUser) {
					// TODO Auto-generated method stub 
					if (progress < 0) 
					{ 
						seekBar.setProgress(0);    
					}
					
					tView_sleep.setText("left margin： "+seekBar_left_margin.getProgress());   
				}   
			});   
	       
	     builder.show(); 	       
		
	} 
	
	
	public void set_r_margin(View view) 
	{  
		 
 	       builder.setTitle("set"); 	
	       builder.setIcon(R.drawable.ic_launcher);
	       builder.setMessage("right margin");
	       view = View.inflate(this, R.layout.seekbar_dialog, null);
	       final SeekBar seekBar_right_margin = (SeekBar) view.findViewById(R.id.seek_bar);
	  
	       seekBar_right_margin.setMax(255);
	       seekBar_right_margin.setProgress(0);  
	       
	       
	       final TextView tView_sleep = (TextView) view.findViewById(R.id.seek_text);
	       tView_sleep.setText("right margin：0");
	       
	       builder.setView(view); 
	       
	       builder.setPositiveButton("set", new DialogInterface.OnClickListener() {
				
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				
				int ret = printer.setRightMargin(seekBar_right_margin.getProgress());
				
				if (ret<0) {  
				
					Toast(context,"set fail");       
				}	 			
				 
			}   
			
		  }); 
	       
	       seekBar_right_margin.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
				
				@Override
				public void onStopTrackingTouch(SeekBar seekBar) {  
					// TODO Auto-generated method stub  
					
				}
				
				@Override
				public void onStartTrackingTouch(SeekBar seekBar) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onProgressChanged(SeekBar seekBar, int progress,
						boolean fromUser) {
					// TODO Auto-generated method stub 
					if (progress < 0) 
					{  
						seekBar.setProgress(0);     
					}  
					
					tView_sleep.setText("right margin： "+seekBar_right_margin.getProgress());   
				}   
			});   
	       
	     builder.show(); 	       
		
	} 
	
	public void set_line_sp(View view) 
	{
	       builder.setTitle("set");  	
	       builder.setIcon(R.drawable.ic_launcher);
	       builder.setMessage("line space");
	       view = View.inflate(this, R.layout.seekbar_dialog, null);
	       final SeekBar seekBar_right_margin = (SeekBar) view.findViewById(R.id.seek_bar);
	  
	       seekBar_right_margin.setMax(255);
	       seekBar_right_margin.setProgress(0);  
	       
	       
	       final TextView tView_sleep = (TextView) view.findViewById(R.id.seek_text);
	       tView_sleep.setText("line space：0");
	       
	       builder.setView(view); 
	       
	       builder.setPositiveButton("set", new DialogInterface.OnClickListener() {
				
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				
				int ret = printer.setLineSpacingByDotPitch(seekBar_right_margin.getProgress());
				
				if (ret<0) {   
				
					Toast(context,"set fail");        
				}	 	 		
				 
			}   
			
		  }); 
	       
	       seekBar_right_margin.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
				
				@Override
				public void onStopTrackingTouch(SeekBar seekBar) {  
					// TODO Auto-generated method stub  
					
				}
				
				@Override
				public void onStartTrackingTouch(SeekBar seekBar) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onProgressChanged(SeekBar seekBar, int progress,
						boolean fromUser) {
					// TODO Auto-generated method stub 
					if (progress < 0) 
					{  
						seekBar.setProgress(0);        
					}   
					
					tView_sleep.setText("line space： "+seekBar_right_margin.getProgress());   
				}   
			});   
	       
	     builder.show(); 		
		
		
	}
	
	
	public void set_word_sp(View view)
	{
	       builder.setTitle("set"); 	
	       builder.setIcon(R.drawable.ic_launcher);
	       builder.setMessage("word space");
	       view = View.inflate(this, R.layout.seekbar_dialog, null);
	       final SeekBar seekBar_right_margin = (SeekBar) view.findViewById(R.id.seek_bar);
	  
	       seekBar_right_margin.setMax(255);
	       seekBar_right_margin.setProgress(0);  
	       
	       
	       final TextView tView_sleep = (TextView) view.findViewById(R.id.seek_text);
	       tView_sleep.setText("word space：0");
	       
	       builder.setView(view); 
	       
	       builder.setPositiveButton("set", new DialogInterface.OnClickListener() {
				
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				
				int ret = printer.setWordSpacingByDotPitch(seekBar_right_margin.getProgress());
				
				if (ret<0) {  
				
					Toast(context,"set fail");       
				}	 			
				 
			}   
			
		  }); 
	       
	       seekBar_right_margin.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
				
				@Override
				public void onStopTrackingTouch(SeekBar seekBar) {  
					// TODO Auto-generated method stub  
					
				}
				
				@Override
				public void onStartTrackingTouch(SeekBar seekBar) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onProgressChanged(SeekBar seekBar, int progress,
						boolean fromUser) {
					// TODO Auto-generated method stub 
					if (progress < 0) 
					{  
						seekBar.setProgress(0);     
					}  
					
					tView_sleep.setText("word space： "+seekBar_right_margin.getProgress());   
				}   
			});   
	       
	     builder.show(); 			
			
		
	}
	
	
	public void set_print_orient(View view)
	{
		
		 Builder builder = new Builder(this);
			
	    	builder.setTitle("print_orient"); 
	    	builder.setIcon(R.drawable.ic_launcher);    
	    	builder.setSingleChoiceItems(new String[]{"normal","reverse"}, 0, new DialogInterface.OnClickListener() {   
				
				@Override   
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
				    int ret = printer.setPrintOrientation(which);
				    
					if (ret<0) {    
						
						Toast(context,"set fail");          
					}  	  
				    
				    dialog.dismiss();       
				}      
			}); 
	    
	    	builder.show();		 
		
	}
	
	
	
	public void set_bold(View view)
	{
		
		 Builder builder = new Builder(this);
			
	    	builder.setTitle("set_bold"); 
	    	builder.setIcon(R.drawable.ic_launcher);    
	    	builder.setSingleChoiceItems(new String[]{"normal","bold"}, 0, new DialogInterface.OnClickListener() {   
				
				@Override   
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
				    int ret = -1;

					ret = printer.setBold(which);
				    
					if (ret<0) {    
						  
						Toast(context,"set fail");             
					}    	  
				    
				    dialog.dismiss();       
				}      
			}); 
	    
	    	builder.show();				
		
	} 
	
	
	public void set_under_line(View view)
	{
		
		 Builder builder = new Builder(this);
			
	    	builder.setTitle("under_line"); 
	    	builder.setIcon(R.drawable.ic_launcher);    
	    	builder.setSingleChoiceItems(new String[]{"normal","under_line"}, 0, new DialogInterface.OnClickListener() {   
				
				@Override   
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
				    //int ret = printer.setUnderLine(which);
					/*
					if (ret<0) {    
						
						Toast(context,"set fail");          
					}  	  
				    */
				    dialog.dismiss();       
				}      
			}); 
	    
	    	builder.show();			
		
		
	}
	public void printStatus(View view){
		
	
		Log.i("123", "setdia");
		int que_status = -1;
		que_status = printer.queState();
		switch(que_status)
		{
			case -2:
				printer_status = "time out";
				//show(MainActivity.this,"time out");
				break;
			case -1:
				printer_status = "query fail";
				//show(MainActivity.this,"query fail");
				break;
			case 0:
				printer_status = "normal";
				//show(MainActivity.this,"normal");
				break;
			case 1:
				printer_status = "no paper";
				//show(MainActivity.this,"no paper");
				break;
				
			case 2:
				printer_status = "hot";
				//show(MainActivity.this,"hot");
				break;
			case 3:
				printer_status = "hot and no paper";
				//show(MainActivity.this,"hot and no paper");
				break;
			default:
				//show(MainActivity.this,"invalid");
				break;
			
		}
		adia =new Builder(this.context).setTitle("status")//设置对话框标题
		  
	    .setMessage(printer_status)//设置显示的内容  
	    .setPositiveButton("back",new DialogInterface.OnClickListener() {//添加确定按钮  
	  
	          
	  
	         @Override  
	  
	         public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件  
	  
	             // TODO Auto-generated method stub  
	        	 dialog.dismiss();
	         }  
	  
	     })
	    .setOnKeyListener(new OnKeyListener() {
			
			@Override
			public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
				// TODO Auto-generated method stub
				if(keyCode == KeyEvent.KEYCODE_BACK)
				{
					
					adia.dismiss();
				}
				return false;
			}
	      }).show();//在按键响应事件中显示此对话框 

			
	}
	
	public void set_inverse(View view)
	{
		
		 Builder builder = new Builder(this);
			
	    	builder.setTitle("set_inverse");  
	    	builder.setIcon(R.drawable.ic_launcher);     
	    	builder.setSingleChoiceItems(new String[]{"normal","inverse"}, 0, new DialogInterface.OnClickListener() {   
				
				@Override   
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
				    //int ret = printer.setInverse(which);  

  
				    
				    dialog.dismiss();       
				}      
			}); 
	    
	    	builder.show();				
		
		
	}
	public void underline(View view)
	{
		
		 Builder builder = new Builder(this);
			
	    	builder.setTitle("set_underline");  
	    	builder.setIcon(R.drawable.ic_launcher);     
	    	builder.setSingleChoiceItems(new String[]{"normal","underline"}, 0, new DialogInterface.OnClickListener() {   
				
				@Override   
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
				    //int ret = printer.setUnderLine(which);  

					//if (ret<0) {    
					//	
					//	Toast(context,"set fail");           
					//}  	  
				    
				    dialog.dismiss();       
				}      
			}); 
	    
	    	builder.show();				
		
		
	}	
	
	/*
	public void set_feed_paper(View view)
	{
	       builder.setTitle("action"); 	
	       builder.setIcon(R.drawable.ic_launcher);
	       builder.setMessage("feed_paper");
	       view = View.inflate(this, R.layout.seekbar_dialog, null);
	       final SeekBar seekBar_right_margin = (SeekBar) view.findViewById(R.id.seek_bar);
	  
	       seekBar_right_margin.setMax(255);
	       seekBar_right_margin.setProgress(5);   
	       
	       
	       final TextView tView_sleep = (TextView) view.findViewById(R.id.seek_text);
	       tView_sleep.setText("paper line：5");    
	       
	       builder.setView(view);   
	       
	       builder.setPositiveButton("feed", new DialogInterface.OnClickListener() {
				
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				
				int ret = printer.feedPaper(seekBar_right_margin.getProgress());
				
				if (ret<0) {   
				
					Toast(context,"set fail");       
				}	 			
				 
			}   
			
		  }); 
	      
	       seekBar_right_margin.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
				
				@Override
				public void onStopTrackingTouch(SeekBar seekBar) {  
					// TODO Auto-generated method stub  
					
				}
				
				@Override
				public void onStartTrackingTouch(SeekBar seekBar) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onProgressChanged(SeekBar seekBar, int progress,
						boolean fromUser) {
					// TODO Auto-generated method stub 
					if (progress < 0)  
					{  
						seekBar.setProgress(0);       
					}  
					
					tView_sleep.setText("paper line： "+seekBar_right_margin.getProgress());   
				}   
			});   
	       
	     builder.show(); 			
		
		
	}
	*/
    public void set_lf(View view)
    {
    	   builder.setTitle("set"); 	
	       builder.setIcon(R.drawable.ic_launcher);
	       builder.setMessage("set");
	       view = View.inflate(this, R.layout.seekbar_dialog, null);
	       final SeekBar seekBar_enter = (SeekBar) view.findViewById(R.id.seek_bar);
	  
	       seekBar_enter.setMax(255);
	       seekBar_enter.setProgress(1);   
	       
	       
	       final TextView tView_sleep = (TextView) view.findViewById(R.id.seek_text);
	       tView_sleep.setText("enter：1");    
	       
	       builder.setView(view);   
	       
	       builder.setPositiveButton("set", new DialogInterface.OnClickListener() {
				
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				
				
				int paper_status = printer.queState();
				if(paper_status != 0)
				{
					queState_print(paper_status);
					return;
				}
				//int ret = printer.printBlankLines(seekBar_enter.getProgress());
				int ret = printer.feedPaper(seekBar_enter.getProgress());
				if (ret<0) {   
				
					Toast(context,"set fail");       
				}	 			
				 
			}   
			
		  }); 
	      
	       seekBar_enter.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
				
				@Override
				public void onStopTrackingTouch(SeekBar seekBar) {  
					// TODO Auto-generated method stub  
					
				}
				
				@Override
				public void onStartTrackingTouch(SeekBar seekBar) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onProgressChanged(SeekBar seekBar, int progress,
						boolean fromUser) {
					// TODO Auto-generated method stub 
					if (progress < 0)  
					{  
						seekBar.setProgress(0);       
					}  
					
					tView_sleep.setText("enter： "+seekBar_enter.getProgress());   
				}   
			});   
	       
	     builder.show(); 			
    	
    	/*
		int ret = printer.printBlankLines(200);
		
		if (ret<0) {       
		 
			Toast(context,"set fail");          
		} 
		*/	   	
    	 
    }
	
	
	public void printtes(View view)
	{
		if (print_EditText.getText().toString().equals("")) {
			
			Messagebox(this, "content is empty");            
			return;          
		} 
		int paper_status = printer.queState();
		if(paper_status != 0)
		{
			queState_print(paper_status);
			return;
		}
		int len = printer.printString(print_EditText.getText().toString());
		
		Log.d("simon", "len = "+ len +"\r\n");
		
		if (len < 0) {  
			  
			 Toast.makeText(this, "print fail ", Toast.LENGTH_LONG).show();
		}
	}	
	
	public void printcode(View view)
	{
		//Log.d("simon","printimage start");
		
		//int ret = 0;
		
		Intent intent = new Intent(context, OneBarcode.class);
		
		startActivity(intent);
		
		//ret = PrintImage(bmplogo);
		
		//  ret = printer.printImage();
		
		//ret = printer.printLF();
		
		//Log.d("simon","printimage end");
		
		//Log.d("simon", "ret = "+ ret +"\r\n");
		
		//if (ret < 0) {       
		 
		//	Toast(context,"printimage fail");   
		//} 	   	
		          
	}	
	

	public void printimage(View view)
	{
		//Log.d("simon","printimage start");
		
		//int ret = 0;
		
		Intent intent = new Intent(context, PrintActivity.class);
		
		startActivity(intent);
		
		//ret = PrintImage(bmplogo);
		
		//  ret = printer.printImage();
		
		//ret = printer.printLF();
		
		//Log.d("simon","printimage end");
		
		//Log.d("simon", "ret = "+ ret +"\r\n");
		
		//if (ret < 0) {       
		 
		//	Toast(context,"printimage fail");   
		//} 	   	
		          
	}	
	
	/*
	public void ClosePrinter(View view)
	{
		exitPrinter();      
	}	
	*/
	
	public void exitPrinter()
	{
		
		 int res = -1; 
		
		 
		 if (openflg) {
			 
			 res = printer.closePrinter();
				
		     if(res == 0)
		     {
		    	 Toast.makeText(this, "close success ", Toast.LENGTH_SHORT).show();  
		     }
		     else { 
		    	 
		    	 Toast.makeText(this, "close fail ", Toast.LENGTH_SHORT).show();     
			}        			 
			 
		}
		 
		printer = null; 
		openflg = false;     
	    System.exit(0);           
		
	}		
	
	
	
   public void getversion(View view)
   {
	   
	  byte[] version = new byte[]{0x00,0x00,0x00}; 
	  
	  //int res = printer.getVersion()
	  String res = printer.getPrinterVersion();
	 // res = printer.printImage();
	/*
	  if(res ==3 ){
		  String verString = new String(version, 0, 3);
	  
		  Messagebox(this, verString);
	  }else{
		  Messagebox(this, "get printer version fail");
	  }
	 */
	  Messagebox(this, res);
       
   }

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		
		if (keyCode == KeyEvent.KEYCODE_BACK ) { 
		  	
			  
			if ((System.currentTimeMillis() - mExitTime) > 2000)   
			{    
				Toast.makeText(this, "Once press again,exit program", Toast.LENGTH_SHORT).show(); 
				mExitTime = System.currentTimeMillis();                                       
		    }       
			else
			{     
				exitPrinter();                               
			}         
		   
			return true;      
			
		} 
		
		return super.onKeyDown(keyCode, event);  
	} 

	  
}
