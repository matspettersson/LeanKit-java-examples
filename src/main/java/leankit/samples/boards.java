package leankit.samples;

import leankit.rest.Api;				// Simple wrapper for REST calls.

import org.json.simple.JSONArray;		// Google JSON.simple - A simple Java toolkit for JSON
import org.json.simple.JSONObject;

/**
 *
 * Sample class for listing all the boards that belong to a leankit account.
 * 
 * Run this example by specifying <e-mail address> <password> <url to board>
 * <e-mail address> and <password> - your credentials.
 * <url> - e.g abc.leankit.com.
 * 
 */


/**
 * @author Mats
 *
 */
public class boards {
	Api iApi;
	JSONObject iJSONObject;

	/**
	 * @param  	String sUser e.g abc@abc.com
	 * @param 	String sPw e.g password
	 * @param	String sHost e.g abc.leankit.com
	 * @param	String sUri - in this case: /kanban/api/boards
	 * @param	String sParms - not used in this example
	 */	
    public boards(String sUser, String sPw, String sHost, String sUri, String sParms) {
    	String iUri = "https://";
    	iUri += sHost + sUri;
    	iApi = new Api(sUser, sPw, sHost, iUri, sParms);
    	iJSONObject = iApi.postRequest();
    }
	
    /**
     * Extracts the reply code from the REST call to leankit.* 
     */
    public Long getReplyCode() {
    	Long lRc = new Long(0);
    	lRc = (Long)iJSONObject.get("ReplyCode");
    	return lRc;
    }
    
    /**
     * Extracts the reply text from the REST call to leankit.
     */
    public String getReplyText() {
    	String sText;
    	sText = (String)iJSONObject.get("ReplyText");
    	return sText;
    }
    
	/**
	 * Iterate through the boards and print some information.
	 */   
    private void printBoards() {
    	JSONArray rarr	= (JSONArray)iJSONObject.get("ReplyData");
    	JSONArray carr = (JSONArray)rarr.get(0);
    	
		for(int i = 0; i < carr.size(); i++){
			JSONObject r = (JSONObject)carr.get(i);
			System.out.println("***** Title: " 			+ r.get("Title") + " *****");
			System.out.println("IsArchived" 			+ r.get("IsArchived"));
			System.out.println("BreakoutBoards: " 		+ r.get("BreakoutBoards"));
			System.out.println("ParentId: "				+ r.get("ParentId"));
			System.out.println("Description: " 			+ r.get("Description"));
			System.out.println("CreationDate: " 		+ r.get("CreationDate"));
			System.out.println("Id: " 					+ r.get("Id"));
			System.out.println("DefaultDropLaneId: "	+ r.get("DefaultDropLaneId"));
			System.out.println("IsPrivate: " 			+ r.get("IsPrivate"));
			System.out.println("DrillThroughBoards: " 	+ r.get("DrillThroughBoards"));
			System.out.println("IsBreakoutBoard: " 		+ r.get("IsBreakoutBoard")); 
			}
    }

	/**
	 * @param  	e-mail address
	 * @param 	password
	 * @param	URL to leankit kanban board
	 */
	public static void main(String[] args) {
		if(args.length == 3) {
			boards b = new boards(args[0], args[1], args[2], "/kanban/api/boards", "" );
			System.out.println("Reply code: " + b.getReplyCode());
			System.out.println("Reply text: " + b.getReplyText());
			b.printBoards();
		} else {
			System.out.println("Usage: <e-mail address> <password> <url to board - e.g. abc.leankit.com");
		}
	}
}
