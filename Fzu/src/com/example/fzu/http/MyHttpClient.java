package com.example.fzu.http;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
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
    private HttpClient httpclient;
    private HttpParams httpparams;
    private Context mctx;
    private static MyHttpClient myhttpclient;

    private MyHttpClient()
    {
    	
    }
    
    /**
     * 
     * @Description:单例模式
     * @return       
     * @throws
     */
    synchronized public static MyHttpClient getInstance()
    {
    	if(myhttpclient==null)
    	    myhttpclient=new MyHttpClient();
    	return myhttpclient;
    }
    
	public void init(Context ctx)
	{
		mctx=ctx;
		httpparams=new BasicHttpParams();
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
	
	public String doPost(String url,List<NameValuePair>params) throws Exception
	{
		HttpPost httpRequest=new HttpPost(url);
		String strResult="doPostError";
		
		try {
			httpRequest.addHeader("Referer", Fzu.REFERER);
			httpRequest.setEntity(new UrlEncodedFormEntity(params,"gb2312"));
			HttpResponse httpResponse=httpclient.execute(httpRequest);
			if(httpResponse.getStatusLine().getStatusCode()==200){
				strResult=changeInputStream(httpResponse.getEntity().getContent(),"gb2312");
			}else{
				strResult="doPostErrorResponse:"+httpResponse.getStatusLine().getStatusCode();
			}
		} finally{
			Log.d(LOG_TAG,strResult);
			writeFileData("xml.txt",strResult);
			
		}
		
		return strResult;
	}
	
     /**
      * 
      * @Description:将输入流按特定编码转换成String
      * @param inputStream
      * @param encode
      * @return       
      * @throws
      */
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
		             }finally{
		            	 if(outputStream!=null)
							try {
								outputStream.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
		             }
		             
		          }
		        return result;
	}
	 

    /**
     * 
     * @Description:将字符串写入应用程序私有目录
     * @param fileName
     * @param message       
     * @throws
     */
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
