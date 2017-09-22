package my.Test;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Salesforce implements JavaDelegate {
String host = "";
String username = "";
String password = "";
String client_id = "";
String client_secret = "";

    protected static final Logger logger = LoggerFactory.getLogger(Salesforce.class);
    



    public void execute(DelegateExecution execution) throws Exception {
    	InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("salesforce.properties");
		Properties properties = new Properties();
		properties.load(inputStream);
		host = properties.getProperty("salesforce.host");
		username   = properties.getProperty("salesforce.username");
		password = properties.getProperty("salesforce.password");
		client_id = properties.getProperty("salesforce.client_id");
		client_secret = properties.getProperty("salesforce.client_secret");
		execution.setVariable("host", host);
		execution.setVariable("username", username);
		execution.setVariable("password", password);
		execution.setVariable("client_id", client_id);
		execution.setVariable("client_secret", client_secret);
		

    }


}
