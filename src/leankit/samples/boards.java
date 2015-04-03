package leankit.samples;

import leankit.rest.Api;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * 
 */


/**
 * @author habanero
 *
 */
public class boards {
	Api iApi;
	JSONObject iJSONObject;
	
    public boards(String sUser, String sPw, String sHost, String sUri, String sParms) {
    	String iUri = "https://";
    	iUri += sHost + sUri;
    	iApi = new Api(sUser, sPw, sHost, iUri, sParms);
    	iJSONObject = iApi.postRequest();
    }
	
    /**
     * 
     */
    public Long getReplyCode() {
    	Long lRc = 0L;
    	lRc = (Long)iJSONObject.get("ReplyCode");
    	return lRc;
    }
    
    /**
     * 
     */
    public String getReplyText() {
    	String sText;
    	sText = (String)iJSONObject.get("ReplyText");
    	return sText;
    }
    
    
    private void printBoards() {
    	//System.out.println(iJSONObject.toString());
    	JSONArray rarr	= (JSONArray)  iJSONObject.get("ReplyData");
    	JSONArray carr = (JSONArray)rarr.get(0);
    	
    for(int i = 0; i < carr.size(); i++){
    	JSONObject r = (JSONObject)carr.get(i);
    	System.out.println("***** Title: " 			+ r.get("Title"));
    	System.out.println("IsArchived" 			+ r.get("IsArchived"));
    	System.out.println("BreakoutBoards: " 		+ r.get("BreakoutBoards"));
    	System.out.println("ParentId: "				+ r.get("ParentId"));
    	System.out.println("Description: " 			+ r.get("Description"));
    	System.out.println("CreationDate: " 		+ r.get("CreationDate"));
    	System.out.println("Id: " 					+ r.get("Id"));
    	System.out.println("DefaultDropLaneId: " 	+ r.get("DefaultDropLaneId"));
    	System.out.println("IsPrivate: " 			+ r.get("IsPrivate"));
    	System.out.println("DrillThroughBoards: " 	+ r.get("DrillThroughBoards"));
    	System.out.println("IsBreakoutBoard: " 		+ r.get("IsBreakoutBoard")); 
    	}

    }

    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if(args.length != 3) {
			System.out.println("Usage: [userid] [password] [URL e.g abc.leankit.com]");
		} else {
		System.out.println(args[0] +"-" + args[1] +"-" + args[2]);
		boards b = new boards(args[0], args[1], args[2], "/kanban/api/boards", "" );
		System.out.println("Reply code: " + b.getReplyCode());
		System.out.println("Reply text: " + b.getReplyText());
		b.printBoards();
		}
	}
	
}
