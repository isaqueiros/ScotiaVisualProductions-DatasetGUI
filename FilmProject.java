import java.util.Date;

public class FilmProject extends Project { // Class inherits from Project
	
	private static final String projectType = "Film";
	private final String format;
	
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
	 * @param format
	 */
	public FilmProject(String projectId, String projectName, Date projectDate, 
			String projectLocation, double projectCost, double projectPriceToCustomer, 
			String sizeOfVenue,int projectDuration,String durationUnit,String format) {
		
		super(projectId, projectName, projectType, projectDate, projectLocation,
				projectCost, projectPriceToCustomer, sizeOfVenue, projectDuration,
				durationUnit);
		this.format = format;
	}
	
	/**
	 * Getters
	 * @return String
	 */
	public String getFormat(){
		return format;
	}

}
