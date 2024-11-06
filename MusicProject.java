import java.util.Date;

public class MusicProject extends Project { // Class inherits from Project
	private static final String projectType = "Music";
	private final String genre;
	
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
	 * @param genre
	 */
	public MusicProject(String projectId, String projectName, Date projectDate, 
			String projectLocation, double projectCost, double projectPriceToCustomer, 
			String sizeOfVenue,int projectDuration,String durationUnit,String genre) {
		
		super(projectId, projectName, projectType, projectDate, projectLocation,
				projectCost, projectPriceToCustomer, sizeOfVenue, projectDuration,
				durationUnit);
		this.genre = genre;
	}
	
	/**
	 * Getters
	 * @return
	 */
	public String getGenre(){
		return genre;
	}

}
