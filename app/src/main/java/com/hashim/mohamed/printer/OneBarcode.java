package com.hashim.mohamed.printer;


import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.os.Bundle;
import android.pt.printer.Printer;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class OneBarcode extends Activity {

	Printer printer = null;
	EditText print_ean8 =null;
    EditText print_ean13 =null;
    EditText print_upca =null;
    EditText print_upce =null;
    EditText print_code39 =null;
    EditText print_code128 =null;
    EditText print_itf =null;
    EditText print_codabar =null;
    EditText print_code93 =null;
    EditText print_QRCode = null;
    
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_one_barcode);
		
		printer = new Printer();
		if(printer.openPrinter() != 0)
		{
			Log.i("123", "open fail");
			return;
		}

		print_ean8 = (EditText) findViewById(R.id.et_ean8);
		print_ean13 = (EditText) findViewById(R.id.et_ean13);
		print_upca = (EditText) findViewById(R.id.et_upca);
		print_upce = (EditText) findViewById(R.id.et_upce);
		print_code39 = (EditText) findViewById(R.id.et_code39);
		print_code128 = (EditText) findViewById(R.id.et_code128);
		print_itf = (EditText) findViewById(R.id.et_itf);
		print_codabar = (EditText) findViewById(R.id.et_codabar);
		print_code93 = (EditText) findViewById(R.id.et_code93);
		print_QRCode = (EditText) findViewById(R.id.et_QRcode);
		
	}
	public void show(Context context ,String msg)
	{
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
		
	}
	public void queState_print(int status) {
		switch(status)
		{
			case -2:
				show(OneBarcode.this,"time out");
				break;
			case -1:
				show(OneBarcode.this,"query fail");
				break;
			case 0:
				show(OneBarcode.this,"normal");
				break;
			case 1:
				show(OneBarcode.this,"no paper");
				break;
				
			case 2:
				show(OneBarcode.this,"hot");
				break;
			case 3:
				show(OneBarcode.this,"hot and no paper");
				break;
			default:
				show(OneBarcode.this,"invalid");
				break;
			
		}
	}
	public static void Messagebox(Context context,String info)
	{
		Builder builder = new Builder(context);    
		builder.setTitle("title");   
		builder.setMessage(info);     
		builder.setPositiveButton("yes", null);    
		builder.show();            
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
	public void printcode_ean8(View view)
	{
		//Log.d("simon","printcode_ean8 start");
		
		//int ret =printer.printString("EAN8��");
		if(!check_paper())
		{
			return;
		}
		int ret = 0;
		
		String str = print_ean8.getText().toString();
		
		Log.d("simon","str = "+ str);
		
		//String str1 = "";
		
		//for (int i = 0;i < str.length();i++) 
		//{ 
		//int ch = (int)str.charAt(i); 
		//String s4 = Integer.toHexString(ch); 
		//str1 = str1 + s4; 
		//} 
		
		byte [] bytes = str.getBytes();
		
		for(int  i = 0;i < str.length(); i++)
			{
			Log.d("simon","bytes["+i+"] =" + bytes[i]);
			//bytes[i] = Integer.toHexString(bytes[i]);
			}
		
		//Log.d("simon","str1 = "+ str1);
		
		if(str.equals("")){
			Messagebox(this, "the number 0f 7 between 0~9 is empty");            
			return;
		}else{
			
		ret = printer.printEAN8(str);
		
		//ret = printer.printLF();
		
		//Log.d("simon","printcode_ean8 end");
		
		//Log.d("simon", "ret = "+ ret +"\r\n");
		
		if (ret < 0) {       
		 
			Messagebox(this,"input the number of digits is not 7");          
		} 	
	}
		          
	}
	
	

	public void printcode_ean13(View view)
	{
		//Log.d("simon","printcode_ean13 start");
		if(!check_paper())
		{
			return;
		}
		int ret = 0;
		
		String str = print_ean13.getText().toString();
		
		Log.d("simon","str = "+ str);
		
		byte [] bytes = str.getBytes();
		
		for(int  i = 0;i < str.length(); i++)
			{
			Log.d("simon","bytes["+i+"] =" + bytes[i]);
			//bytes[i] = Integer.toHexString(bytes[i]);
			}
		
		if(str.equals("")){
			Messagebox(this, "the number 0f 12 between 0~9 is empty");            
			return;
		}else{
			
		ret = printer.printEAN13(str);
		
		//ret = printer.printLF();
		
		//Log.d("simon","printcode_ean13 end");
		
		//Log.d("simon", "ret = "+ ret +"\r\n");
		
		if (ret < 0) {       
		 
			Messagebox(this,"input the number of digits is not 12");          
		}
		
	  }
		          
	}
	
	public void printcode_upca(View view)
	{
		//Log.d("simon","printcode_upca start");
		if(!check_paper())
		{
			return;
		}
		int ret = 0;
		
		String str = print_upca.getText().toString();
		
		Log.d("simon","str = "+ str);
		
		byte [] bytes = str.getBytes();
		
		for(int  i = 0;i < str.length(); i++)
			{
			Log.d("simon","bytes["+i+"] =" + bytes[i]);
			//bytes[i] = Integer.toHexString(bytes[i]);
			}
		
		if(str.equals("")){
			Messagebox(this, "the number 0f 11 between 0~9 is empty");            
			return;
		}else{
			
		ret = printer.printUPCA(str);
		
		//ret = printer.printLF();
		
		//Log.d("simon","printcode_upca end");
		
		//Log.d("simon", "ret = "+ ret +"\r\n");
		
		if (ret < 0) {       
		 
			Messagebox(this,"input the number of digits is not 11");          
		} 	
		
		}
		          
	}
	
	public void printcode_upce(View view)
	{
		//Log.d("simon","printcode_upce start");
		if(!check_paper())
		{
			return;
		}
		int ret = 0;
		
		String str = print_upce.getText().toString();
		
		Log.d("simon","str = "+ str);
		
		byte [] bytes = str.getBytes();
		
		for(int  i = 0;i < str.length(); i++)
			{
			Log.d("simon","bytes["+i+"] =" + bytes[i]);
			//bytes[i] = Integer.toHexString(bytes[i]);
			}
		
		if(str.equals("")){
			Messagebox(this, "the number 0f 8 between 0~9 is empty");            
			return;
		}else{
		
		ret = printer.printUPCE(str);
		
		//ret = printer.printLF();
		
		//Log.d("simon","printcode_upce end");
		
		//Log.d("simon", "ret = "+ ret +"\r\n");
		
		if (ret < 0) {       
		 
			Messagebox(this,"input the number of digits is not 8");          
		} 	  
	 }
		          
	}
	
	public void printcode_code39(View view)
	{
		//Log.d("simon","printcode_upce start");
		if(!check_paper())
		{
			return;
		}
		int ret = 0;
		int len = 0;
	
		String str = print_code39.getText().toString();
		
		Log.d("simon","str = "+ str);
		
		len = str.length();
		
		if(len > 14){
			Messagebox(this, "the number of input character is greater than 14");          
			return;
		}
		
		byte [] bytes = str.getBytes();
		
		for(int  i = 0; i < len; i++)
			{
			Log.d("simon","bytes["+i+"] =" + bytes[i]);
			//bytes[i] = Integer.toHexString(bytes[i]);
			}
		
		if(str.equals("")){
			Messagebox(this, "the character between 0~9,A~Z, ,$,%,*,+,-,.,/,is empty");          
			return;
		}else{
		
		ret = printer.printCODE39(str);
		
		//ret = printer.printLF();
		
		//Log.d("simon","printcode_upce end");
		
		//Log.d("simon", "ret = "+ ret +"\r\n");
		
		if (ret < 0) {       
		 
			Messagebox(this,"input the number of digits is not 8");          
		} 	  
	 }
		          
	}
	
	public void printcode_code128(View view)
	{
		//Log.d("simon","printcode_upce start");
		if(!check_paper())
		{
			return;
		}
		int ret = 0;
		int len = 0;
		
		String str = print_code128.getText().toString();
		
		Log.d("simon","str = "+ str);
		
		len = str.length();
		
		byte [] bytes = str.getBytes();
		
		for(int  i = 0; i < len; i++)
			{
			Log.d("simon","bytes["+i+"] =" + bytes[i]);
			//bytes[i] = Integer.toHexString(bytes[i]);
			}
		if(len > 14){
			Messagebox(this, "the number of input character is greater than 14");          
			return;
		}
		
		if(str.equals("")){
			Messagebox(this, "the input character is empty");          
			return;
		}else{
		
		ret = printer.printCODE128(str);
		
		//ret = printer.printLF();
		
		//Log.d("simon","printcode_upce end");
		
		//Log.d("simon", "ret = "+ ret +"\r\n");
		
		if (ret < 0) {       
		 
			Messagebox(this,"input the number of digits is wrong");          
		} 	  
	 }
		          
	}
	
	public void printcode_itf(View view)
	{
		//Log.d("simon","printcode_upce start");
		if(!check_paper())
		{
			return;
		}
		int ret = 0;
		int len = 0;
	
		String str = print_itf.getText().toString();
		
		Log.d("simon","str = "+ str);
		
		len = str.length();
		
		byte [] bytes = str.getBytes();
		
		for(int  i = 0; i < len; i++)
			{
			Log.d("simon","bytes["+i+"] =" + bytes[i]);
			//bytes[i] = Integer.toHexString(bytes[i]);
			}
		
		if(len % 2 != 0){
			Messagebox(this, "the number of input character is not even number");          
			return;
		}
		
		if(len > 14){
			Messagebox(this, "the number of input character is greater than 14");          
			return;
		}
		
		if(str.equals("")){
			Messagebox(this, "the input character is empty");          
			return;
		}else{
		
		ret = printer.printITF(str);
		
		//ret = printer.printLF();
		
		//Log.d("simon","printcode_upce end");
		
		//Log.d("simon", "ret = "+ ret +"\r\n");
		
		if (ret < 0) {       
		 
			Messagebox(this,"input the number of digits is wrong");          
		} 	  
	 }
		          
	}

	public void printcode_codabar(View view)
	{
		//Log.d("simon","printcode_upce start");
		if(!check_paper())
		{
			return;
		}
		int ret = 0;
		int len = 0;
		
		String str = print_codabar.getText().toString();
		
		Log.d("simon","str = "+ str);
		
		len = str.length();
		
		byte [] bytes = str.getBytes();
		
		for(int  i = 0; i < len; i++)
			{
			Log.d("simon","bytes["+i+"] =" + bytes[i]);
			//bytes[i] = Integer.toHexString(bytes[i]);
			}
		
		if(len > 14){
			Messagebox(this, "the number of input character is greater than 14");          
			return;
		}
		
		if(str.equals("")){
			Messagebox(this, "the input character is empty");          
			return;
		}else{
		
		ret = printer.printCODABAR(str);
		
		//ret = printer.printLF();
		
		//Log.d("simon","printcode_upce end");
		
		//Log.d("simon", "ret = "+ ret +"\r\n");
		
		if (ret < 0) {       
		 
			Messagebox(this,"input the number of digits is wrong");          
		} 	  
	 }
		          
	}
	public void printcode_QR(View view)
	{
		//Log.d("simon","printcode_upce start");
		if(!check_paper())
		{
			return;
		}
		int ret = 0;
		int len = 0;
		
		String str = print_QRCode.getText().toString();
		
	
		
		if(str.equals("")){
			Messagebox(this, "the input character is empty");          
			return;
		}else{
		
		ret = printer.printQR(str, 4);
		
		//ret = printer.printLF();
		
		//Log.d("simon","printcode_upce end");
		
		//Log.d("simon", "ret = "+ ret +"\r\n");
		
		if (ret < 0) {       
		 
			Messagebox(this,"input the number of digits is wrong");          
		} 	  
	 }
		          
	}
	public void printcode_code93(View view)
	{
		//Log.d("simon","printcode_upce start");
		if(!check_paper())
		{
			return;
		}
		int ret = 0;
		int len = 0;
		
		String str = print_code93.getText().toString();
		
		Log.d("simon","str = "+ str);
		
		len = str.length();
		
		byte [] bytes = str.getBytes();
		
		for(int  i = 0; i < len; i++)
			{
			Log.d("simon","bytes["+i+"] =" + bytes[i]);
			//bytes[i] = Integer.toHexString(bytes[i]);
			}
		
		if(len > 14){
			Messagebox(this, "the number of input character is greater than 14");          
			return;
		}
		
		if(str.equals("")){
			Messagebox(this, "the input character is empty");          
			return;
		}else{
		
		ret = printer.printCODE93(str);
		
		//ret = printer.printLF();
		
		//Log.d("simon","printcode_upce end");
		
		//Log.d("simon", "ret = "+ ret +"\r\n");
		
		if (ret < 0) {       
		 
			Messagebox(this,"input the number of digits is wrong");          
		} 	  
	 }
		          
	}

}
