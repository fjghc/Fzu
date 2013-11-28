package com.example.fzu.http;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.util.Log;

import com.example.fzu.entity.Fzu;
import com.example.fzu.entity.Student;

public class MyHttpClient{
	private static final String LOG_TAG="MyHttpClient";
    private Student mstudent;
    private HttpClient httpclient;
    private HttpParams httpparams;
    private Context mctx;
    
	public void init(Context ctx)
	{
		mctx=ctx;
		httpparams=new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpparams, 3*1000);  //连接超时时间
		HttpConnectionParams.setSoTimeout(httpparams, 2*1000);   //等待数据超时时间
		HttpConnectionParams.setSocketBufferSize(httpparams, 8192);   //设置socket缓冲区大小
		HttpClientParams.setRedirecting(httpparams, true);   //设置重定向
		httpclient=new DefaultHttpClient(httpparams);

	}
	
	public String doPost(String url,List<NameValuePair>params)
	{
		HttpPost httpRequest=new HttpPost(url);
		String strResult="doPostError";
		BufferedReader reader;
		
		try {
			httpRequest.addHeader("Referer", Fzu.REFERER);
			httpRequest.setEntity(new UrlEncodedFormEntity(params,"gb2312"));
			HttpResponse httpResponse=httpclient.execute(httpRequest);
			if(httpResponse.getStatusLine().getStatusCode()==200){
				strResult=changeInputStream(httpResponse.getEntity().getContent(),"gb2312");
			}else{
				strResult="doPostErrorResponse:"+httpResponse.getStatusLine().getStatusCode();
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Log.d(LOG_TAG,strResult);
			writeFileData("xml.txt",strResult);
			
		}
		
		return strResult;
	}
	
	 private String changeInputStream(InputStream inputStream, String encode) {
		         ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		         byte[] data = new byte[1024];
		         int len = 0;
		         String result = "";
		         if (inputStream != null) {
		             try {
		                 while ((len = inputStream.read(data)) != -1) {
		                     outputStream.write(data, 0, len);
		                 }
		                 result = new String(outputStream.toByteArray(), encode);
		 
		             } catch (IOException e) {
		                 e.printStackTrace();
		              }
		          }
		        return result;
	}
	 
	  public void writeFileData(String fileName,String message){ 

	       try{ 

	        FileOutputStream fout =mctx.openFileOutput(fileName, mctx.MODE_PRIVATE);

	        byte [] bytes = message.getBytes(); 

	        fout.write(bytes); 

	        fout.close(); 

	        } 

	       catch(Exception e){ 

	        e.printStackTrace(); 

	       } 

	}    
	
	public void close()
	{
		httpclient.getConnectionManager().shutdown();
	}
}
