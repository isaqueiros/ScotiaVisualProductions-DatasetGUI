import java.util.Date;

public class Project {
	private final String projectId;
	private final String projectName;
	private final String projectType;
	private final Date projectDate;
	private final String projectLocation;
	private final Double projectCost;
	private final Double projectPriceToCustomer;
	private final String sizeOfVenue;
	private final int projectDuration;
	private final String durationUnit;
	
	/**
	 * The class constructor initialises the variables
	 * Values are provided via arguments received upon creation of the object
	 * @param projectId
	 * @param projectName
	 * @param projectType
	 * @param projectDate
	 * @param projectLocation
	 * @param projectCost
	 * @param projectPriceToCustomer
	 * @param sizeOfVenue
	 * @param projectDuration
	 * @param durationUnit
	 */
	
	public Project(String projectId, String projectName, String projectType, 
			Date projectDate, String projectLocation, double projectCost,
			double projectPriceToCustomer, String sizeOfVenue, int projectDuration,
			String durationUnit){
		this.projectId = projectId;
		this.projectName = projectName;
		this.projectType = projectType;
		this.projectDate = projectDate;
		this.projectLocation = projectLocation;
		this.projectCost = projectCost;
		this.projectPriceToCustomer = projectPriceToCustomer;
		this.sizeOfVenue = sizeOfVenue;
		this.projectDuration = projectDuration;
		this.durationUnit = durationUnit;
	}
	
	/**
	 * Getter methods for each of class variables
	 * @return
	 */
	
	public String getProjectId(){
		return projectId;
	}
	
	public String getProjectName(){
		return projectName;
	}
	
	public String getProjectType(){
		return projectType;
	}
	
	public Date getProjectDate(){
		return projectDate;
	}
	
	public String getProjectLocation(){
		return projectLocation;
	}
	
	public double getProjectCost(){
		return projectCost;
	}
	
	public double getProjectPriceToCustomer(){
		return projectPriceToCustomer;
	}
	
	public String getSizeOfVenue(){
		return sizeOfVenue;
	}
	
	public int getProjectDuration(){
		return projectDuration;
	}
	
	public String getDurationUnit(){
		return durationUnit;
	}

}
