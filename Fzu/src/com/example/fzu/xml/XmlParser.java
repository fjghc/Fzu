/**
 * XmlParser.java
 * com.example.fzu.xml
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2013-12-1 		zdd
 *
 * Copyright (c) 2013, TNT All Rights Reserved.
*/

package com.example.fzu.xml;

import java.io.IOException;
import java.io.InputStream;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.util.Log;

/**
 * ClassName:XmlParser
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 *
 * @author   zdd
 * @version  
 * @since    Ver 1.1
 * @Date	 2013-12-1		下午5:16:27
 *
 * @see 	 
 * @deprecated 
 */
public class XmlParser {
	private static final String LOG_TAG="XmlParser";
	
	public void getDataFormCourseXML(InputStream in,String inputEncoding)
	{
		try {
			XmlPullParserFactory xppfactory=XmlPullParserFactory.newInstance();
			XmlPullParser xpp=xppfactory.newPullParser();
			xpp.setInput(in, inputEncoding);
			
			int eventType=xpp.getEventType();
			
			while(eventType!=XmlPullParser.END_DOCUMENT)
			{
				if(eventType==XmlPullParser.START_DOCUMENT){
					Log.d(LOG_TAG,"xml parser start_document...");
				}else if(eventType==XmlPullParser.START_TAG){
					Log.d(LOG_TAG,"start tag "+xpp.getName()+"..");	
					
				}else if(eventType==XmlPullParser.END_TAG){
					Log.d(LOG_TAG,"end tag "+xpp.getName()+"..");
					
				}else if(eventType==XmlPullParser.TEXT){
					Log.d(LOG_TAG,"text "+xpp.getText()+".");
					
				}
				try {
					eventType=xpp.next();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					Log.d(LOG_TAG,"IOException");
					e.printStackTrace();
					
				}
			}
			Log.d(LOG_TAG,"xml parser end_document...");
				
			
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			Log.d(LOG_TAG,"XmlPullParserException");
			e.printStackTrace();
			
		}
		
		
		
	}

}

