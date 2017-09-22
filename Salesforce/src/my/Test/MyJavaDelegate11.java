package my.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.logging.Logger;

import com.amazonaws.util.json.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.ISO8601Utils;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.EngineServices;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import org.activiti.engine.impl.pvm.PvmProcessElement;
import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;
import org.activiti.engine.impl.pvm.process.ProcessElementImpl;

public class MyJavaDelegate11 implements JavaDelegate {
	
	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	 public void execute(DelegateExecution execution) throws Exception {
		//final Logger log = Logger.getLogger(MyJavaDelegate11.class.getName());
		String name = "";
	//	Logger logger = Logger.getLogger("MyLog");
		//FileHandler fh;
       	
		//TODO Auto-generated method stub
		HttpClient httpClient = new DefaultHttpClient();
		ObjectMapper objectMapper = new ObjectMapper();
		CloseableHttpClient client = HttpClients.createDefault();
		JsonNode jsonNode = null;
	    String jsonString = null;
	    String jsonString2 = null;
	    String jsonString3 = null;
	    String jsonString4 = null;
	    String jsonString5 = null;
	    int totalsize = 0;
	    String ManagerName = "";
	    String ownerName = "";
	    String test = "";
	    String urlVariable = "";
	    String host = "";
	    String username = "";
	    String password = "";
	    String client_id = "";
	    String client_secret = "";
	    String theirwebsite = "";
	    Date newDate;
	    String account_id = "";
	    String Phone="";
	    String Fax="";
	    String AccountNumber = "";
	    String Industry = "";
	    String Sic = "";
	    
	 //  fh = new FileHandler("/Users/fmalagrino/Desktop/logs_activiti/MyLogFile.log");
//    logger.addHandler(fh);
   //   logger.setLevel(Level.ALL);
  //  SimpleFormatter formatter = new SimpleFormatter();
 // fh.setFormatter(formatter);
	    
	 	
	 	host =  execution.getVariable("host").toString();
		username = execution.getVariable("username").toString();
		password = execution.getVariable("password").toString();
		client_id = execution.getVariable("client_id").toString();
		client_secret = execution.getVariable("client_secret").toString();
		
		HttpPost httpPost = new HttpPost(host);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("username", username));
		params.add(new BasicNameValuePair("password", password));
	    params.add(new BasicNameValuePair("grant_type","password"));
	    params.add(new BasicNameValuePair("client_id",client_id));
	    params.add(new BasicNameValuePair("client_secret",client_secret));
		
	   httpPost.setEntity(new UrlEncodedFormEntity(params));
		
	    CloseableHttpResponse response = client.execute(httpPost);
	 //   logger.info(response.toString());
	    jsonString = EntityUtils.toString(response.getEntity());
	    if (jsonString.isEmpty()) {
	   // 	logger.info("response mappings present but response is empty (status line: " + response.getStatusLine() + " )");
     } else {
     	jsonNode = objectMapper.readTree(jsonString);
     	test = execution.getVariable("test").toString().replaceAll("[\\[\\]]", "").replaceAll("\"", "").replace(" ", "%20");
     //	logger.info(test);
     	account_id = execution.getVariable("account_id").toString();
     	String url = jsonNode.findValue("instance_url").toString().replace("\"","") + "/services/data/v39.0/sobjects/Account/" + account_id;
     //	logger.info ("URL : " + url);
     	HttpPatch httpPatch2 = new HttpPatch(url);
     	String token = "Bearer " + jsonNode.findValue("access_token").toString().replace("\"","");
     	httpPatch2.addHeader("Content-Type", "application/json");
     	httpPatch2.addHeader("Authorization", token);
    // 	logger.info ("BANANAS");
     	CloseableHttpResponse response1 = client.execute(httpPatch2);
	    jsonString2 = EntityUtils.toString(response1.getEntity());
     //	StringEntity params2 = new StringEntity("{\"name\": \"Test This\"}");
	 //  logger.info ("BANANAS2");
     	
     
     		 
         	 theirwebsite = execution.getVariable("theirwebsite").toString().replace("%20", " ");
         	if  (theirwebsite.startsWith("http://www.")) {
           	 execution.setVariable("theirwebsite", theirwebsite);
           }else if(theirwebsite.startsWith("https://www.")){
           	 execution.setVariable("theirwebsite", theirwebsite);
           } else {
           	theirwebsite = "http://www." + theirwebsite;
           	 execution.setVariable("theirwebsite", theirwebsite);
           }
         	 Phone = execution.getVariable("Phone").toString().replace("%20", " ");
         	 Fax = execution.getVariable("Fax").toString().replace("%20", " ");
         	 AccountNumber = execution.getVariable("AccountNumber").toString().replace("%20", " ");
      	    Industry = execution.getVariable("Industry").toString().replace("%20", " ");
      	    Sic = execution.getVariable("Sic").toString().replace("%20", " ");
         	 
    

         	 
         //	 logger.info("theirwebsite: " + theirwebsite);
         //	logger.info("Sic: " + Sic);
         //	logger.info("Industry" + Industry);
         	
         	
         	 execution.setVariable("Phone", Phone);
             execution.setVariable("Fax", Fax);
             execution.setVariable("AccountNumber", AccountNumber);
             execution.setVariable("Industry", Industry);
             execution.setVariable("Sic", Sic);
            
         	 
    	//StringEntity params1 =new StringEntity("{\"Name\": \"" + name.replace("%20", " ") + "\"}");
    	StringEntity params1 =new StringEntity("{\"Phone\": \"" + Phone.replace("%20", " ") + "\",\"Fax\": \"" + Fax.replace("%20", " ") + "\",\"AccountNumber\": \"" + AccountNumber.replace("%20", " ") + "\",\"Sic\": \"" + Sic.replace("%20", " ") + "\",\"Industry\": \"" + Industry.replace("%20", " ") + "\",\"Website\": \"" + theirwebsite.replace("%20", " ") +"\"}");
    //	logger.info("mmmm: " + params1);
     	httpPatch2.setEntity(params1);
     	HttpResponse response2 = httpClient.execute(httpPatch2);
     	
     }
 }
}



