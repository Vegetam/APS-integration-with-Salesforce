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

public class MyJavaDelegate10 implements JavaDelegate {
    
    /**
     * @param args
     * @throws IOException 
     * @throws ClientProtocolException 
     */
     public void execute(DelegateExecution execution) throws Exception {
    //  final Logger log = Logger.getLogger(MyJavaDelegate10.class.getName());
        String name = "";
    // Logger logger = Logger.getLogger("MyLog");
 // FileHandler fh;
        
        //TODO Auto-generated method stub
        HttpClient httpClient = new DefaultHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();
        CloseableHttpClient client = HttpClients.createDefault();
        JsonNode jsonNode = null;
        JsonNode jsonNode2 = null;
        JsonNode jsonNode3 = null;
        JsonNode jsonNode4 = null;
        JsonNode jsonNode5 = null;
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
        String account_id = "";
        String Phone="";
        String Fax="";
        String AccountNumber = "";
        String Industry = "";
        String Sic = "";
        String Photo = "";
//    fh = new FileHandler("/Users/fmalagrino/Desktop/logs_activiti/MyLogFile.log");
 //  logger.addHandler(fh);
 //     logger.setLevel(Level.ALL);
//    SimpleFormatter formatter = new SimpleFormatter();
  //    fh.setFormatter(formatter);
        
        
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
    // logger.info(response.toString());
        jsonString = EntityUtils.toString(response.getEntity());
        if (jsonString.isEmpty()) {
      //    logger.info("response mappings present but response is empty (status line: " + response.getStatusLine() + " )");
     } else {
        jsonNode = objectMapper.readTree(jsonString);
        test = execution.getVariable("test").toString().replaceAll("[\\[\\]]", "").replaceAll("\"", "").replace(" ", "%20");
        //logger.info(test);
        String url = jsonNode.findValue("instance_url").toString().replace("\"","") + "/services/data/v36.0/query/?q=SELECT%20name%20from%20Account%20where%20name%3D'" + test +"\'";
  //   logger.info ("URL : " + url);
            //HttpPost httpPost2 = new HttpPost(url);
        HttpGet httpGet2 = new HttpGet(url);
        String token = "Bearer " + jsonNode.findValue("access_token").toString().replace("\"","");
        httpGet2.addHeader("Content-Type", "application/json");
        httpGet2.addHeader("Authorization", token);
  //    logger.info ("BANANAS");
        CloseableHttpResponse response1 = client.execute(httpGet2);
        jsonString2 = EntityUtils.toString(response1.getEntity());
        //StringEntity params2 = new StringEntity("{\"name\": \"Test This\"}");
    //    logger.info ("BANANAS2");
        try{ 
  //        logger.info ("BANANAS3");
              jsonNode2 = objectMapper.readTree(jsonString2);
               
               totalsize = jsonNode2.findValue("totalSize").asInt();

             //     logger.info("SIZE : " + totalsize);
              execution.setVariable("name1",name);
   
     //  logger.info ("BANANAS 2");
     name = jsonNode2.findValue("Name").toString().replaceAll("[\\[\\]]", "").replaceAll("\"", "");
    // logger.info("names :" + name);
     execution.setVariable("name",name);
     urlVariable = jsonNode2.findValue("url").toString().replaceAll("\"", "");
     execution.setVariable("urlVariable",urlVariable);
   String url2 = jsonNode.findValue("instance_url").toString().replace("\"","") + urlVariable;
    //logger.info ("URL2 : " +  url2);
        //HttpPost httpPost2 = new HttpPost(url);
    HttpGet httpGet3= new HttpGet(url2);
     token = "Bearer " + jsonNode.findValue("access_token").toString().replace("\"","");
    httpGet3.addHeader("Content-Type", "application/json");
    httpGet3.addHeader("Authorization", token);
    //logger.info ("BANANAS4");
    CloseableHttpResponse response2 = client.execute(httpGet3);
    jsonString3 = EntityUtils.toString(response2.getEntity());
    //logger.info(response2.toString());
    jsonNode3 = objectMapper.readTree(jsonString3);
 
    String website = "https://eu7.salesforce.com/" + jsonNode3.findValue("Id").toString().replaceAll("\"", "");
    account_id = jsonNode3.findValue("Id").toString().replaceAll("\"", "");
    execution.setVariable("account_id" , account_id);
  //  logger.info("account_id: " + account_id);
    execution.setVariable("website", website);
    String theirwebsite = jsonNode3.findValue("Website").toString().replaceAll("\"", "");
    if  (theirwebsite.startsWith("http://www.")) {
         execution.setVariable("theirwebsite", theirwebsite);
    }else if(theirwebsite.startsWith("https://www.")){
         execution.setVariable("theirwebsite", theirwebsite);
    } else {
        theirwebsite = "http://www." + theirwebsite;
         execution.setVariable("theirwebsite", theirwebsite);
    }
  
    Phone = jsonNode3.findValue("Phone").toString().replaceAll("\"", "");
    Fax = jsonNode3.findValue("Fax").toString().replaceAll("\"", "");
    AccountNumber = jsonNode3.findValue("AccountNumber").toString().replaceAll("\"", "");
    Industry = jsonNode3.findValue("Industry").toString().replaceAll("\"", "");
    Sic = jsonNode3.findValue("Sic").toString().replaceAll("\"", "");
 //   logger.info ("Sic2" + Sic);
    Photo = "https://eu7.salesforce.com/" + jsonNode3.findValue("PhotoUrl").toString().replaceAll("\"", "");
    execution.setVariable("Phone", Phone);
    execution.setVariable("Fax", Fax);
    execution.setVariable("AccountNumber", AccountNumber);
    execution.setVariable("Industry", Industry);
    execution.setVariable("Sic", Sic);
   // logger.info ("Sic1" + Sic);
    execution.setVariable("Photo", Photo);
    String ownerId = jsonNode3.findValue("OwnerId").toString().replaceAll("\"", "");
 //    logger.info("ownerId " + ownerId);
   // logger.info("customerSuccessManager " + customerSuccessManager);
    if (ownerId != null ) {
        String url3 =  jsonNode.findValue("instance_url").toString().replace("\"","") + "/services/data/v36.0/sobjects/User/" + ownerId ;
        //logger.info ("URL3 : " +  url3);
            //HttpPost httpPost2 = new HttpPost(url);
        HttpGet httpGet4= new HttpGet(url3);
         token = "Bearer " + jsonNode.findValue("access_token").toString().replace("\"","");
        httpGet4.addHeader("Content-Type", "application/json");
        httpGet4.addHeader("Authorization", token);
        //logger.info ("BANANAS43");
        CloseableHttpResponse response3 = client.execute(httpGet4);
        jsonString4 = EntityUtils.toString(response3.getEntity());
        //logger.info(response3.toString());
        jsonNode4 = objectMapper.readTree(jsonString4);
        String testCalendar1 = jsonNode4.findValue("CreatedDate").toString().replaceAll("\"", "");
        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");     
        Date newDate = (Date)sdf3.parse(testCalendar1);
       // logger.info("test Calendar 1: " + newDate);
        execution.setVariable("newDate", newDate);
        ownerName = jsonNode4.findValue("Name").toString().replaceAll("\"", "");
     //   logger.info("onwer name :" + ownerName);
        execution.setVariable("ownerid", ownerName);
    }else {
         execution.setVariable("ownerid", ownerName);
    }



    
   // String testTest1 = jsonNode3.fields().toString();
   // logger.info(testTest1);
  
   
    
    //StringEntity params2 = new StringEntity("{\"name\": \"Test This\"}");
    //logger.info ("BANANAS6");
        }catch(Exception x){
            //logger.info("exception:" + x.getStackTrace());
        }
        
        //StringEntity params1 =new StringEntity("{\"fHCM2__Unique_Id__c\": \"" + unique_id + "\",\"name\": \"" + name + "\",\"fHCM2__Gender__c\": \"" + gender + "\",\"fHCM2__Home_Address_1__c\": \"" + homeAddress1 + "\", \"fHCM2__Home_Address_2__c\": \"" + homeAddress2 + "\",  \"fHCM2__Home_Address_3__c\": \"" + homeAddress3 + "\",  \"fHCM2__Home_Address_Postal_Code__c\": \"" + homeZipCode + "\",  \"fHCM2__Home_Address_City__c\": \"" + homeCity + "\",  \"fHCM2__Home_Address_Region__c\": \"" + homeRegion + "\",  \"fHCM2__Home_Address_Country__c\": \"" + homeCountry + "\",  \"fHCM2__Home_Email__c\": \"" + homeEmail + "\",  \"fHCM2__Personal_Mobile__c\": \"" + personalMobile + "\",  \"fHCM2__Home_Phone__c\": \"" + homePhone + "\",  \"fHCM2__Middle_Name__c\": \"" + middleName + "\",  \"fHCM2__Email__c\": \"" + workEmail + "\",  \"fHCM2__Phone_Number__c\": \"" + workPhone + "\",  \"fHCM2__Mobile_Number__c\": \"" + workMobile + "\",  \"fHCM2__Username__c\": \"" + username + "\",  \"fHCM2__Country__c\": \"" + country + "\",  \"fHCM2__Location__c\": \"" + location + "\",  \"Title__c\": \"" + title + "\",  \"fHCM2__Social_Security_Number__c\": \"" + ssn + "\",  \"fHCM2__Employment_Status__c\": \"" + status + "\",  \"Cost_Code__c\": \"" + costCode  + "\",  \"Title__c\": \"" + title + "\",  \"Skype__c\": \"" + skype + "\",  \"Employment_Category__c\": \"" + employmentCategory  + "\",  \"Office_or_Home_Based__c\": \"" + homeOrOffice  + "\",  \"Current_Status__c\": \"" +  currentStatus  + "\",  \"Incentive_Plan__c\": \"" + incentivePlan + "\",  \"Guarantee__c\": \"" + guarantee + "\",  \"Guarantee_Months__c\": \"" + guarantee_months + "\",  \"Recruitment_Source__c\": \"" + recruitment + "\",  \"Agency__c\": \"" + agency + "\",  \"fHCM2__Marital_Status__c\": \"" + martialStatus + "\",  \"fHCM2__Self_Vacation_Days_Accrued__c\": \"" + selfVacationAccuratedDays  + "\",  \"fHCM2__Self_Vacation_Compensation__c\": \"" + selfVacationCompensation  + "\",  \"fHCM2__Self_Vacation_Days_Per_Year__c\": \"" + selfVacationAccuratedDaysPerYears  + "\",  \"Termination__c\": \"" + termination + "\",  \"Voluntary__c\": \"" + voluntary + "\",  \"Involuntary__c\": \"" + involuntary + "\",  \"Regret_non_regret__c\": \"" + regretOrNot  +  "\",  \"Background_check_done__c\": \"" + backgroundCheck  + "\"}");
        //httpPost2.setEntity(params1);
        
        HttpResponse response2 = httpClient.execute(httpGet2);
        HttpEntity entity = response.getEntity();
        jsonString = EntityUtils.toString(response2.getEntity());
        //logger.info("json : " + jsonString);
        if (jsonString.isEmpty()) {
            System.out.println("response mappings present but response is empty (status line: " + response.getStatusLine() + " )");
     } else {
         jsonNode = objectMapper.readTree(jsonString);
         
     }
     }
 }
}



