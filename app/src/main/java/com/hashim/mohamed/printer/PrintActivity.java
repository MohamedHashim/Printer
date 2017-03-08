package com.hashim.mohamed.printer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;



import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.MediaStore;
import android.pt.Cprinter;
import android.pt.printer.Printer;
import android.R.integer;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;

import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;



public class PrintActivity extends Activity {

	
	 ImageView iView = null;
	 Bitmap bmp_bk  = null;
	 Printer printer = null;
	 
	 private String AbsolutePath = null;
	 //SerialPort uart = null;
	 //InputStream  in = null;
	 //OutputStream out = null;

	 
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_print);
		
		printer = new Printer();
		if(printer.openPrinter() != 0)
		{
			Log.i("123", "open fail");
			return;
		}
		
		iView = (ImageView) findViewById(R.id.iv_pic);   
		
		bmp_bk = BitmapFactory.decodeResource(getResources(),R.drawable.ic);
		
		
		//uart = new SerialPort("com7",9600); 
        
        //in = uart.getInputStream();
         
        //out = uart.getOutputStream();
 
		
		int width = bmp_bk.getWidth();   // ��ȡλͼ�Ŀ�  
	    int height = bmp_bk.getHeight(); // ��ȡλͼ�ĸ�  
	     
	    int size = bmp_bk.getByteCount();
	     
	    //Log.d("simon", "width ="+width);
	    //Log.d("simon", "height ="+height); 
	    
	    Log.d("simon", "size="+size);  
	    
	    Log.d("simon", "height*width="+height*width);  
	    
	    
	    
	    

		
		
	}

	public static void Messagebox(Context context,String info)
	{
		Builder builder = new Builder(context);    
		builder.setTitle("title");   
		builder.setMessage(info);     
		builder.setPositiveButton("yes", null);    
		builder.show();            
	}
	
	public void show(Context context ,String msg)
	{
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
		
	}
	public void queState_print(int status) {
		switch(status)
		{
			case -2:
				show(PrintActivity.this,"time out");
				break;
			case -1:
				show(PrintActivity.this,"query fail");
				break;
			case 0:
				show(PrintActivity.this,"normal");
				break;
			case 1:
				show(PrintActivity.this,"no paper");
				break;
				
			case 2:
				show(PrintActivity.this,"hot");
				break;
			case 3:
				show(PrintActivity.this,"hot and no paper");
				break;
			default:
				show(PrintActivity.this,"invalid");
				break;
			
		}
	}
	public void change(View view) throws IOException
	{
		  //Messagebox(MainActivity.this,"edaonnnnnn");   
		  
//		  out.write(new byte[]{ 0x32,0x33,0x56,0x67,0x59
//				     ,0x31 ,0x32 ,0x33,0x67,0x59 ,0x31 ,0x32 
//				     ,0x31 ,0x32 ,0x33,0x67,0x59 ,0x31 ,0x32 
//				       ,0x67,0x59 ,0x31 ,0x32 ,
//				       0x67,0x59 ,0x31 ,0x32, 
//				       0x34 ,0x35 ,0x0A });      
		    
			if(!check_paper())
			{
				return;
			}		     	  
		  //printmap(convertToBlackWhite(bmp_bk)); 
		  
		  printer.printPicture(bmp_bk, bmp_bk.getWidth(), bmp_bk.getHeight());  
		   
	} 
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==0 && resultCode == RESULT_OK)
		{
			 
			 Bitmap bm = null;
			 AbsolutePath = null;
			 String[] proj = {MediaStore.Images.Media.DATA};
			 ContentResolver resolver = getContentResolver();
			 try {
				bm = MediaStore.Images.Media.getBitmap(resolver, data.getData());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//image_picture.setImageBitmap(bm);
			// miniLcd.displayPicture(0, 0, bm);
			 
			Cursor cursor = managedQuery(data.getData(), proj, null, null, null); 
			int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
			cursor.moveToFirst();
			AbsolutePath = cursor.getString(column_index);
			bmp_bk = BitmapFactory.decodeFile(AbsolutePath);
			bmp_bk = convertToBlackWhite(bmp_bk);
			iView.setImageBitmap(bmp_bk);

			//ed_path.setText(AbsolutePath);
			//ed_picture.setText(path);
		}
	};	
public void select(View view)
{
	Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
	intent.setType("image/*");
	intent.putExtra("return-data", true);
	startActivityForResult(intent, 0);
}
		  
  private boolean check_paper() {
		int status = printer.queState();
		if(status != 0)
		{
			queState_print(status);
			return false;
		}
		return true;
}

@SuppressLint("NewApi")
  public void printmap(Bitmap bmp) throws IOException
  {
	  
	  
		if (bmp == null) {
			
			return;
		}
		
//			Options optsa = new BitmapFactory.Options();
//			
//			optsa.inSampleSize = 10;  
		
		int modh, modw;
		
		int[] pixels = new int[bmp.getByteCount()];
		
		byte[] setdat = new byte[48];
		
		//byte[] setdat1 = new byte[48];
		
		for (int i = 0; i < setdat.length; i++) {
			setdat[i] = 0;
		}
		
		bmp.getPixels(pixels, 0, bmp.getWidth(), 0, 0, bmp.getWidth(),bmp.getHeight());
				
		int height1 = bmp.getHeight();
		
		int weight1 = bmp.getWidth(); 

		Log.d("simon", "weight1 ="+weight1);
	    Log.d("simon", "height1 ="+height1);
	    
	    byte[] head= new byte[5];
	    
	    head[0]= 0x1b;
	    head[1]= 0x2a;
	    head[2]= 0x21;
	    head[3]= (byte)((weight1>>0)&0xff);
	    head[4]= (byte)((weight1>>8)&0xff);
	    
	    
	    
	    if (height1 % 24 != 0)
        {
            modh = height1 % 24;
            height1 = height1 + (24 - modh);
        }

        if (weight1 % 8 != 0)
        {
            modw = weight1 % 8;
            weight1 = weight1 + (8 - modw);
        }
	    
	    
	    int sum = 0;
	    int index = 0; 
	    
	   // byte[] sentdat = new byte[(weight1+(8 -weight1 % 8))*3 + 5];
	    
	    byte[] sentdat = new byte[weight1*3 + 5];
	    
	    for (int n = 0; n < 5; n++) {
			  
			  sentdat[n] = head[n];
			} 
	    	
	    
		for (int i = 0; i < height1; i++) {
			
			/* if(i % 24 == 0)
				{
				    
				  //printer.printImage(sentdat);
				  for (int m = 0; m < 5; m++) {
					  
					  sentdat[i] = head[i];
					} 
				} 
			    */
			
			//Log.d("simon", "height_cnt=="+i);   
			
			for (int j = 0; j < weight1; j++) {
				 
			    int color = pixels[i*weight1+j];
			    
			    int red = ((color & 0x00FF0000) >> 16);
		        int green = ((color & 0x0000FF00) >> 8);
		        int blue = (color & 0x000000FF);
		        
		        if ((red < 100) && (green < 100) && (blue < 100))
		        {

		        	int ior = (index%8);
		        	
		        	int iir = (index/8);
		        	
		        	int xor_val = (1<<(7-ior));  
		        	
		        	setdat[iir] = (byte) (setdat[iir]|xor_val);			        	
		        	
		        }
	

		        index++;
				
			}
			
			int cnt = ((index+7)/8); 
			
			Log.d("simon", "height1=="+i); 
			Log.d("simon", "cnt=="+cnt);
			
			
			for (int k = 0; k < cnt; k++) {
				
				sentdat[5+sum+k] = setdat[k];
				//Log.d("simon", "sentdat["+k+"] ="+Integer.toHexString(sentdat[k]&0xff));  
				 
			}
			
			sum =sum + cnt;
			
			if(sum == weight1*3)
			{
				//Log.d("simon", "sum =="+sum);
				
		    	int ret = 0;
		    	
				//ret = printer.printImage(sentdat);
		    	
				if(ret < 0){
					Messagebox(PrintActivity.this,"printImage fail");
				}
				sum = 0;
			}
			
			cnt   = 0;
			index = 0;
		    
		    for (int m = 0; m < setdat.length; m++) { 
		    	
		    	//setdat1[m] = 0;
				setdat[m] = 0; 
			}
		   
		   
		}
  }
  
public Bitmap Zoom(Bitmap bmp)
{
    //��ȡ���ͼƬ�Ŀ�͸�
    int width = bmp.getWidth();
    int height = bmp.getHeight();
    
    //����Ԥת���ɵ�ͼƬ�Ŀ�Ⱥ͸߶�
    int newWidth = bmp.getWidth()/2;
    int newHeight =  bmp.getHeight()/2;
    
    //���������ʣ��³ߴ��ԭʼ�ߴ�
    float scaleWidth = ((float) newWidth) / width;
    float scaleHeight = ((float) newHeight) / height;
    // ��������ͼƬ�õ�matrix����
    Matrix matrix = new Matrix();
    // ����ͼƬ����
    matrix.postScale(scaleWidth, scaleHeight);
    // �����µ�ͼƬ
    Bitmap resizedBitmap = Bitmap.createBitmap(bmp, 0, 0,
    width, height, matrix, true);
    return resizedBitmap;
}
  
  public Bitmap convertToBlackWhite(Bitmap bmp) { 
	  
	    //int width = bmp.getWidth();   // ��ȡλͼ�Ŀ�
	    //int height = bmp.getHeight(); // ��ȡλͼ�ĸ�
	    
	    //width����384���еȱ�������
	    while(bmp.getWidth() >= 384)
	    {
	    	bmp = Zoom(bmp);
	    	
	    	//width = width/2;
	    	//height = height/2;
	    }
	    int width = bmp.getWidth();   // ��ȡλͼ�Ŀ�
	    int height = bmp.getHeight(); // ��ȡλͼ�ĸ�
	    int[] pixels = new int[width * height]; // ͨ��λͼ�Ĵ�С�������ص�����

	    bmp.getPixels(pixels, 0, width, 0, 0, width, height);
	    
	    int alpha = 0xFF << 24;
	    
	    for (int i = 0; i < height; i++) {
	    	
	      for (int j = 0; j < width; j++) {
	    	  
	        int grey = pixels[width * i + j];

	        //������ԭɫ
	        int red = ((grey & 0x00FF0000) >> 16);
	        int green = ((grey & 0x0000FF00) >> 8);
	        int blue = (grey & 0x000000FF);
	        
	        //ת���ɻҶ�����
	        grey = (int) (red * 0.3 + green * 0.59 + blue * 0.11);
	        grey = alpha | (grey << 16) | (grey << 8) | grey;
	        pixels[width * i + j] = grey;
	        
	      }
	      
	    }
	    
	    //�½�ͼƬ
	    Bitmap newBmp = Bitmap.createBitmap(width, height, Config.RGB_565); 
	    //����ͼƬ����
	    newBmp.setPixels(pixels, 0, width, 0, 0, width, height);    
	    
	    Log.d("simon", "width="+width+"\r\n");
	    Log.d("simon", "height="+height+"\r\n");
	    //Bitmap resizeBmp = ThumbnailUtils.extractThumbnail(newBmp, 80, 80);             
		     
		    return newBmp;
		    
		    
		  }

   
}



