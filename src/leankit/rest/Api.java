/**
 * 
 */
package leankit.rest;

import java.io.InputStream;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * @author Mats Pettersson
 *
 */
public class Api {
    String iUsername;
    String iPassword;
    String iHost;
    String iUri;
    String iParameters;

/**
 * 
 * @param user
 * @param password
 * @param host
 * @param uri
 * @param parms
 */
public Api(String user, String password, String host, String uri, String parms) {
	iUsername = user;
	iPassword = password;
	iHost = host;
	iUri = uri;
	iParameters = parms;
	
	}	

/**
 * 
 * @return JSONObject
 */
public JSONObject postRequest() {
	JSONObject jobj = null;
	
	
    try {
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope(iHost, -1, null, null),
                new UsernamePasswordCredentials(iUsername, iPassword));
        CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultCredentialsProvider(credsProvider)
                .build();
        
        HttpPost request = new HttpPost(iUri);
        request.addHeader("content-type", "application/json");
        StringEntity params =new StringEntity(iParameters);
        request.setEntity(params);
        HttpResponse response = httpClient.execute(request);
        //System.out.println(response);
        HttpEntity entity = response.getEntity();
        InputStream is = entity.getContent();

        String respstr = IOUtils.toString(is, "UTF-8");

	    JSONParser jsonParser = new JSONParser();
	    jobj = (JSONObject) jsonParser.parse(respstr);
    	httpClient.close();
    }catch (Exception ex) {
    	ex.printStackTrace();
    } finally {
   }    
return jobj;
}


}
