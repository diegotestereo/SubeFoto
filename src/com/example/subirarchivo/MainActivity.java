package com.example.subirarchivo;

import java.io.File;
import java.io.FileInputStream;

import org.jibble.simpleftp.SimpleFTP;

import android.R.bool;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity
{


	Button Enviar, Buscar;
	TextView Text_link;

	/********* work only for Dedicated IP ***********/
	//static final String FTP_HOST = "ftp.ushuaiamovil.com.ar";
	static final String FTP_HOST = "192.168.0.7";

	/********* FTP USERNAME ***********/
//	static final String FTP_USER = "ushuaiamovil";
	static final String FTP_USER = "diego";

	/********* FTP PASSWORD ***********/
	//static final String FTP_PASS = "4rVdN6lH";
	static final String FTP_PASS = "diego";
	Button btn;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btn = (Button) findViewById(R.id.btn_EnviarArchivo);

		btn.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{				
				new tareaAsincrona().execute();
			}
		});
	}
	
	public class tareaAsincrona extends AsyncTask<Void, Void, Boolean>
	{
		ProgressDialog pd;
		
		@Override
		protected void onPreExecute()
		{
			Log.d("tareaasincrona","onPreExecute");
			pd = new ProgressDialog(MainActivity.this);
			pd.setMessage("subiendo...");
			pd.show();
			super.onPreExecute();
		}
		
		@Override
		protected Boolean doInBackground(Void... params)
		{
			Log.d("tareaasincrona","doInBackground");
			return uploadFile();
		}
		
		@Override
		protected void onPostExecute(Boolean resutl)
		{
			Log.d("tareaasincrona","onPostExecute");
			pd.dismiss();
			if (resutl)
			{
				Toast.makeText(getApplicationContext(), "Todo ok!", Toast.LENGTH_SHORT).show();
			}
			else
			{
				Toast.makeText(getApplicationContext(), "Error!", Toast.LENGTH_SHORT).show();
			}
			super.onPostExecute(resutl);
		}
	}

	/********** Pick file from sdcard *******/
	/*
	 * File f = new File("/sdcard/Download/falcon.txt");
	 * 
	 * // Upload sdcard file uploadFile(f);
	 */

	public boolean uploadFile()
	{
		SimpleFTP client; client = new SimpleFTP();
		Log.d("uploadFile","client.connect(FTP_HOST,21,FTP_USER,FTP_PASS);");
		try
		{

			client.connect(FTP_HOST,21,FTP_USER,FTP_PASS);
			Log.d("uploadFile","client.connect(FTP_HOST,21,FTP_USER,FTP_PASS);");
		//	client.connect(FTP_HOST, 21);// con este uso usuario anonymous y pass anonymous
			
			 // Set binary mode.
			client.bin();
			Log.d("uploadFile","client.bin();");
		    // Change to a new working directory on the FTP server.
			client.cwd("public_html");
			Log.d("uploadFile","client.cwd(public_html)");
		    // Upload some files.
			 File f = new File("/sdcard/Download/350211.rar");
			 Log.d("uploadFile"," File f = new File(/sdcard/Download/logo.png);");
			boolean rta = client.stor(f);
				    
		   	    
		    // Quit from the FTP server.
			client.disconnect();
			 Log.d("uploadFile","client.disconnect();");
			return rta;

		}
		catch (Exception e)
		{
			e.printStackTrace();
			 Log.d("uploadFile","tiro error");
		}
		return false;
	}

}