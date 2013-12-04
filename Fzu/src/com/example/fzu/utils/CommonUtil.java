/**
 * CommonUtil.java
 * com.example.fzu.utils
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2013-12-4 		zdd
 *
 * Copyright (c) 2013, TNT All Rights Reserved.
*/

package com.example.fzu.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.util.Log;

/**
 * ClassName:CommonUtil
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 *
 * @author   zdd 
 * @since    Ver 1.1
 * @Date	 2013-12-4		下午5:21:20
 */
public class CommonUtil {
    private static final String LOG_TAG="CommonUtil";
	
    public static String md5(String source) {  
        
        StringBuffer sb = new StringBuffer(32);  
              
        try {  
            MessageDigest md = MessageDigest.getInstance("MD5");  
            byte[] array = md.digest(source.getBytes("utf-8"));  
                  
            for (int i = 0; i < array.length; i++) {  
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).toUpperCase().substring(1, 3));  
            }  
        } catch (Exception e) {  
            Log.d(LOG_TAG,"CommonUtil md5 error!!!");
            return null;  
        }  
              
        return sb.toString();  
    }  

}

