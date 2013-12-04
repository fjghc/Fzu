/**
 * FileUtil.java
 * com.example.fzu.utils
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2013-12-3 		zdd
 *
 * Copyright (c) 2013, TNT All Rights Reserved.
*/

package com.example.fzu.utils;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.util.EncodingUtils;

import android.content.Context;

/**
 * ClassName:FileUtil
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 *
 * @author   zdd
 * @since    Ver 1.1
 * @Date	 2013-12-3		下午4:50:56
 */
public class FileUtil {
	
    /**
     * 
     * @Description:将字符串写入应用程序私有目录
     * @param fileName
     * @param message       
     * @throws
     */
	public static void writeFileData(String fileName,String message,Context mctx){ 

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
	
	   public String readFileData(String fileName,Context mctx){ 

	        String res=""; 

	        try{ 

	         FileInputStream fin = mctx.openFileInput(fileName); 

	         int length = fin.available(); 

	         byte [] buffer = new byte[length]; 

	         fin.read(buffer);     

	         res = EncodingUtils.getString(buffer, "UTF-8"); 

	         fin.close();     

	        } 

	        catch(Exception e){ 

	         e.printStackTrace(); 

	        } 

	        return res; 

	    }   
	
    /**
     * 
     * @Description:将输入流按特定编码转换成String
     * @param inputStream
     * @param encode
     * @return       
     * @throws
     */
	 public static String changeInputStream(InputStream inputStream, String encode) {
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
}

