package com.example.subirarchivo;

import java.io.File;
import org.jibble.simpleftp.*;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity  {
    

	SimpleFTP client ;
	Button Enviar,Buscar;
	TextView Text_link;
    
    /*********  work only for Dedicated IP ***********/
    static final String FTP_HOST= "http://192.168.0.110";
     
    /*********  FTP USERNAME ***********/
    static final String FTP_USER = "diego";
     
    /*********  FTP PASSWORD ***********/
    static final String FTP_PASS  ="diego";
     
    Button btn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
 
        btn = (Button) findViewById(R.id.btn_EnviarArchivo);
        
      btn.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			
			File f = new File("/sdcard/logo.png");
	         
	      
	        uploadFile(f);
			
		}
	});
    }
     
    
   
  
         
        /********** Pick file from sdcard *******/
   /*     File f = new File("/sdcard/Download/falcon.txt");
         
        // Upload sdcard file
        uploadFile(f);*/
         
    
     
    public void uploadFile(File fileName){
         
         
         client = new SimpleFTP();
          
        try {
             
       //     client.connect(FTP_HOST,21,FTP_USER,FTP_PASS);
           
        	  client.connect(FTP_HOST,21);
             
        } catch (Exception e) {
            e.printStackTrace();
            try {
            	client.disconnect();    
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
         
    }
     
  
   
 
	   
	    
    
}