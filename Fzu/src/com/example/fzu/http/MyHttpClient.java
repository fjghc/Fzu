package com.example.fzu.http;

import java.io.IOException;
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

import android.util.Log;

import com.example.fzu.entity.Student;

public class MyHttpClient{
	private static final String LOG_TAG="MyHttpClient";
    private Student mstudent;
    private HttpClient httpclient;
    private HttpParams httpparams;
    
	public HttpClient getHttpClient()
	{
		httpparams=new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpparams, 3*1000);  //设置连接的超时时间
		HttpConnectionParams.setSoTimeout(httpparams, 2*1000);   //等待数据的超时时间
		HttpConnectionParams.setSocketBufferSize(httpparams, 8192);   //设置socket缓冲区大小
		HttpClientParams.setRedirecting(httpparams, true);   //设置自动重定向
		httpclient=new DefaultHttpClient(httpparams);
	   
		return httpclient;
	}
	
	public String doPost(String url,List<NameValuePair>params)
	{
		HttpPost httpRequest=new HttpPost(url);
		String strResult="doPostError";
		
		try {
			httpRequest.setEntity(new UrlEncodedFormEntity(params,HTTP.UTF_8));
			HttpResponse httpResponse=httpclient.execute(httpRequest);
			if(httpResponse.getStatusLine().getStatusCode()==200){
				strResult=EntityUtils.toString(httpResponse.getEntity());
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
			Log.d(MyHttpClient.LOG_TAG,strResult);
		}
		
		return strResult;
	}
	
	public void close()
	{
		httpclient.getConnectionManager().shutdown();
	}
}
