import java.util.Date;

public class TheaterProject extends Project { // Class inherits from Project
	
	private static final String projectType = "Theater";
	private final String playwright;
	
	/**
	 * Class Constructor
	 * @param projectId
	 * @param projectName
	 * @param projectDate
	 * @param projectLocation
	 * @param projectCost
	 * @param projectPriceToCustomer
	 * @param sizeOfVenue
	 * @param projectDuration
	 * @param durationUnit
	 * @param playwright
	 */
	public TheaterProject(String projectId, String projectName, Date projectDate, 
			String projectLocation, double projectCost, double projectPriceToCustomer, 
			String sizeOfVenue,int projectDuration,String durationUnit,String playwright) {
		
		super(projectId, projectName, projectType, projectDate, projectLocation,
				projectCost, projectPriceToCustomer, sizeOfVenue, projectDuration,
				durationUnit);
		this.playwright = playwright;
	}
	
	/**
	 * Getters
	 * @return
	 */
	public String getPlaywright(){
		return playwright;
	}

}
