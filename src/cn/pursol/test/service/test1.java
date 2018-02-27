package cn.pursol.test.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;


public class test1 {
	public static void main(String args[]) throws ClientProtocolException, IOException{		
		String result = "";
		 List<BasicNameValuePair> params1 = new ArrayList<BasicNameValuePair>();
			HttpPost httpPost = new HttpPost("http://oa.cysy.bjedu.cn:8080/bdms/findSubject?token=9400030@10057@2017-09-25");
			httpPost.setEntity(new UrlEncodedFormEntity(params1, HTTP.UTF_8));
			HttpClient httpclient = new DefaultHttpClient();
			HttpResponse httpResponse = httpclient.execute(httpPost);
			// 请求成功
			System.out.println("httpResponse.getStatusLine().getStatusCode()="+httpResponse.getStatusLine().getStatusCode());
			if (httpResponse.getStatusLine().getStatusCode() ==HttpStatus.SC_OK) {
				HttpEntity entity = httpResponse.getEntity();
				if (entity != null) {
					result = EntityUtils.toString(entity, "utf-8");
					System.out.println("result="+result);
				}
			}
	}
}
