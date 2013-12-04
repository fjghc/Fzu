package com.example.fzu.http;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import android.content.Context;
import android.util.Log;

import com.example.fzu.Fzu;

public class MyHttpClient{
	private static final String LOG_TAG="MyHttpClient";
    private volatile static HttpClient httpclient;  //volatile使用共享内存中的该值、禁止线程的私有拷贝

    private MyHttpClient()
    {
    	
    }
    
    /**
     * 
     * @Description:单例模式
     * @return       
     * @throws
     */
     public static HttpClient getInstance()
    {

    	if(httpclient==null)    //双重加锁，确保只有第一次创建时进行同步锁，提升synchronized性能
    	{
    		synchronized(MyHttpClient.class){
            	if(httpclient==null){
                HttpParams httpparams=new BasicHttpParams();
        		ConnManagerParams.setTimeout(httpparams,1*1000);   //连接管理器的超时
        		HttpConnectionParams.setConnectionTimeout(httpparams, 2*1000);  //连接超时时间
        		HttpConnectionParams.setSoTimeout(httpparams, 4*1000);   //等待数据超时时间
        		HttpConnectionParams.setSocketBufferSize(httpparams, 8192);   //设置socket缓冲区大小
        		HttpClientParams.setRedirecting(httpparams, true);   //设置重定向
        		
        		
        		SchemeRegistry schReg=new SchemeRegistry();   //多线程安全
        		schReg.register(new Scheme("http",PlainSocketFactory.getSocketFactory(),80));
        		schReg.register(new Scheme("https",SSLSocketFactory.getSocketFactory(),443));
        		ThreadSafeClientConnManager tsccm=new ThreadSafeClientConnManager(httpparams, schReg);
        		httpclient=new DefaultHttpClient(tsccm,httpparams);
            	}
    		}
    	}
    	return httpclient;
    }
    
	public void close()
	{
		httpclient.getConnectionManager().shutdown();
	}
}
