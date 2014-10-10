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

	SimpleFTP client;
	Button Enviar, Buscar;
	TextView Text_link;

	/********* work only for Dedicated IP ***********/
	static final String FTP_HOST = "ftp.ushuaiamovil.com.ar";

	/********* FTP USERNAME ***********/
	static final String FTP_USER = "ushuaiamovil";

	/********* FTP PASSWORD ***********/
	static final String FTP_PASS = "4rVdN6lH";
	
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
			pd = new ProgressDialog(MainActivity.this);
			pd.setMessage("subiendo...");
			pd.show();
			super.onPreExecute();
		}
		
		@Override
		protected Boolean doInBackground(Void... params)
		{
			return uploadFile();
		}
		
		@Override
		protected void onPostExecute(Boolean resutl)
		{
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
		client = new SimpleFTP();
		try
		{

			client.connect(FTP_HOST,21,FTP_USER,FTP_PASS);

			//client.connect(FTP_HOST, 21);// con este uso usuario anonymous y pass anonymous
			
			 // Set binary mode.
			client.bin();
		    
		    // Change to a new working directory on the FTP server.
			client.cwd("public_html");
		    
		    // Upload some files.
			 File f = new File("/sdcard/Download/logo.jpg");
			
			boolean rta = client.stor(f);
				    
		   	    
		    // Quit from the FTP server.
			client.disconnect();
			return rta;

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

}