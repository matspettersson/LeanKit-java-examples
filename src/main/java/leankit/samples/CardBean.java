package leankit.samples;


/**
* "Container" for the Card attributes.
* 
* <p>This example is only for read-only information. The set operations will not update the board. They are only for internal use.
*  
* @author Mats
* @version 0.9
*/

public class CardBean {
	private String iLane = "";
	private String iTitle = "";
	private String iDescription = "";
	private String iAssignedUser = "";
	private String iLastActivity = "";
	private String iStartDate = "";
	private String iDueDate = "";
	private String iLastComment = "";
	private String iTypeName = "";
	private String iPriorityText = "";

public CardBean(String lane,
		String title,
		String description,
		String assignedUser,
		String lastActivity,
		String startDate,
		String dueDate,
		String lastComment,
		String typeName,
		String prioritytext) {

	setLane(lane);
	setTitle(title);
	setDescription(description);
	setAssignedUser(assignedUser);
	setLastActivity(lastActivity);
	setStartDate(startDate);
	setDueDate(dueDate);
	setLastComment(lastComment);
	setTypeName(typeName);
	setPriorityText(prioritytext);
    }

    public CardBean() {

    }

    public String getLane() {
	return iLane;
    }

    public void setLane(String lane) {
	iLane = lane;
    }

    public String getTitle() {
	return iTitle;
    }

    public void setTitle(String title) {
	iTitle = title;
    }


    public String getDescription() {
	return iDescription;
    }

    public void setDescription(String description) {
	iDescription = description;
    }

    public String getAssignedUser() {
	return iAssignedUser;
    }

    public void setAssignedUser(String assigneduser) {
	iAssignedUser = assigneduser;
    }

    public String getLastActivity() {
	return iLastActivity;
    }

    public void setLastActivity(String lastactivity) {
	iLastActivity = lastactivity;
    }

    public String getStartDate() {
	return iStartDate;
    }

    public void setStartDate(String startdate) {
	iStartDate = startdate;
    }

    public String getDueDate() {
	return iDueDate;
    }

    public void setDueDate(String duedate) {
	iDueDate = duedate;
    }

    public String getLastComment() {
	return iLastComment;
    }
    public void setLastComment(String lastcomment) {
	iLastComment = lastcomment;
    }

    public String getTypeName() {
	return iTypeName;
    }
    public void setTypeName(String typename) {
	iTypeName = typename;
    }


    public String getPriorityText() {
	return iPriorityText;
    }

    public void setPriorityText(String prioritytext) {
	iPriorityText = prioritytext;
    }



    public String toString() {
	return	iLane + "," +
		iTitle + "," +
		iDescription + "," +
		iAssignedUser + "," +
		iLastActivity + "," +
		iStartDate + "," +
		iDueDate + "," +
		iLastComment + "," +
		iTypeName  + "," +
		iPriorityText;

    }


} 




