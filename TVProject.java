import java.util.Date;

public class TVProject extends Project { // Class inherits from Project
	
	private static final String projectType = "TV";
	private final String network;
	
	/**
	 * Class constructor
	 * @param projectId
	 * @param projectName
	 * @param projectDate
	 * @param projectLocation
	 * @param projectCost
	 * @param projectPriceToCustomer
	 * @param sizeOfVenue
	 * @param projectDuration
	 * @param durationUnit
	 * @param network
	 */
	public TVProject(String projectId, String projectName, Date projectDate, 
			String projectLocation, double projectCost, double projectPriceToCustomer, 
			String sizeOfVenue,int projectDuration,String durationUnit,String network) {
		
		super(projectId, projectName, projectType, projectDate, projectLocation,
				projectCost, projectPriceToCustomer, sizeOfVenue, projectDuration,
				durationUnit);
		this.network = network;
	}
	
	/**
	 * Getters
	 * @return
	 */
	public String getNetwork(){
		return network;
	}

}
