package leankit.samples;
import leankit.rest.Api;				// Simple wrapper for REST calls.

import org.json.simple.JSONArray;		// Google JSON.simple - A simple Java toolkit for JSON
import org.json.simple.JSONObject;
import java.util.ArrayList;

/**
 * @author Mats 
 *
 */
public class board {
	Api iApi;
	JSONObject iJSONObject;
	ArrayList arrlist = new ArrayList();

/**
	@param sUser
	@param sPw
	@param sHost
	@param sUri
	@param sParms
	@param sBoard
	@param sSearch
 */
    public board(String sUser, String sPw, String sHost, String sUri, String sParms, String sBoard, String sSearch) {
    	String iUri = "https://";
    	iUri += sHost + sUri + "/" + sBoard;
    	iApi = new Api(sUser, sPw, sHost, iUri, sParms);
    	iJSONObject = iApi.postRequest();		// Call Leankit and store the response in the instance variable.
		populateArrayList();
    }

/**
	@return the ArrayList that contains all the cards from leankit.
 */
public ArrayList getArray() {
	return arrlist;
}

/**
	
 */
private String getChainOfParentLanes(JSONObject jobj, Long lId) {
	String sLaneNames = "";
	
	JSONArray rarr = (JSONArray) jobj.get("ReplyData");
	JSONObject robj = (JSONObject)rarr.get(0); 	//Is ReplyData "always" 0 size array?
    
    JSONArray larr = (JSONArray)robj.get("Lanes");
    
    for(int m=0; m < larr.size(); m++){
    	JSONObject c = (JSONObject)larr.get(m);
    	//System.out.println("###" + c.toString());
    	Long l = (Long)c.get("Id");
    	//System.out.println("### l=" + l + "  lId=" + lId);
    	if(lId.equals(l) == true) {
    		//System.out.println("Match!");
    		//System.out.println("sId=" + sId);
    		sLaneNames = (String)c.get("Title");
    		//System.out.println("Name: " + sLaneNames);
    		break;
    	}
    }
    return sLaneNames;
}

/**
 *	
 */
private String getLaneName(JSONObject jobj, Long lId) {
	String sLaneName = "";
	String sParents = "";
	
	JSONArray rarr = (JSONArray) jobj.get("ReplyData");
	JSONObject robj = (JSONObject)rarr.get(0); 	//Is ReplyData "always" 0 size array?
    
    JSONArray larr = (JSONArray)robj.get("Lanes");
    
    for(int m=0; m < larr.size(); m++){
    	JSONObject c = (JSONObject)larr.get(m);
    	Long l = (Long)c.get("Id");
	Long l0 = new Long(0L);
    	//System.out.println("### l=" + l + "  lId=" + lId);
    	if(lId.equals(l) == true) {
    		//System.out.println("Match!");
    		//System.out.println("sId=" + sId);
    		sLaneName = (String)c.get("Title");			//Behöver iterera till toppen
    		Long lPid = (Long)c.get("ParentLaneId");
    		if(lPid.equals(l0) == false)
    			sParents += "/" + getChainOfParentLanes(jobj, lPid);
    		//System.out.println("Name: " + sLaneName);
    		break;
    	}
    }
    return sParents + "/" + sLaneName;
}


/**
 *	
 */
private void populateArrayList() {
	JSONArray rarr	= (JSONArray) iJSONObject.get("ReplyData");
	JSONObject robj = (JSONObject)rarr.get(0); 
	JSONArray larr 	= (JSONArray)robj.get("Lanes");

 for(int m=0; m < larr.size(); m++){
 	JSONObject c = (JSONObject)larr.get(m);
 	String sl = getLaneName(iJSONObject, (Long)c.get("ParentLaneId"));
 	String sLane = sl + "/" + c.get("Title");
 	fillArray(c, sLane);
 	}

}


/**
 *	
 */
private void fillArray(JSONObject jobj, String sLane) {
	String userString = "";
	String lane = "";
	JSONArray carr = (JSONArray) jobj.get("Cards");

	for(int i=0; i<carr.size(); i++){
		JSONObject r = (JSONObject)carr.get(i);
		JSONArray j1 = (JSONArray)r.get("AssignedUsers");
		
		for(int j=0; j < j1.size(); j++){
			String title, description, externalcardid, prioŕitytext,lastactivity,startdate,duedate,lastcomment,typename,assignedusername, prioritytext;
			Long size;
			//System.out.println(r.toString());
			if(sLane.startsWith("//")) {
			  lane = sLane.substring(2);
			  } else
			lane = sLane;

//			JSONArray j1 = (JSONArray)r.get("AssignedUsers");
			int n = j1.size();

/* Hur tänkte jag här? Size är väl 1? */
			for (int y = 0; y < j1.size(); y++) {
				JSONObject j2 = (JSONObject)j1.get(y);
				userString = (String)j2.get("FullName");
				}

			title			= (String)r.get("Title");
			String result	= (String)title.replaceAll("[\"]","");
			title			= result;
			
			description		= (String) r.get("Description");
			externalcardid	= (String) r.get("ExternalCardID");
			size			= (Long)   r.get("Size");
			prioritytext	= (String) r.get("PriorityText");
			lastactivity	= (String) r.get("LastActivity");
			startdate		= (String) r.get("StartDate");
			duedate			= (String) r.get("DueDate");
			lastcomment		= (String) r.get("LastComment");
			typename 		= (String) r.get("TypeName");

			assignedusername  = (String) r.get("AssignedUserName");
			prioritytext 	= (String) r.get("PriorityText");

			CardBean cb = new CardBean(lane, title, description, 
					assignedusername, lastactivity, startdate, duedate,
					lastcomment, typename, prioritytext);
			arrlist.add(cb);
		}			
	   }
	
	}

	/**
	 * @param  	e-mail address
	 * @param 	password
	 * @param	URL to leankit kanban board
	 * @param	board id. (You can get the board id from the "boards" example.
	 */
	public static void main(String[] args) {
		if(args.length == 4) {
		   board b = new board(args[0], args[1], args[2], "/kanban/api/boards", "", args[3], "" );
		   ArrayList a = b.getArray();
		   System.out.println(a.toString());
		} else {
		System.out.println("Usage: <e-mail address> <password> <url to board - e.g. abc.leankit.com> <board id>");
		}
	}

}
