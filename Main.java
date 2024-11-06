import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
	
	private static List<Project> dataProjects;
	private static List<FilmProject> filmProjects;
	private static List<MusicProject> musicProjects;
	private static List<TheaterProject> theaterProjects;
	private static List<TVProject> tvProjects;
	
	public static void main(String[] args) throws IOException, ParseException {
		dataProjects = new ArrayList<>();
		filmProjects = new ArrayList<>();
		musicProjects = new ArrayList<>();
		theaterProjects = new ArrayList<>();
		tvProjects = new ArrayList<>();
		dataReader();
		new GUI(dataProjects, filmProjects, musicProjects, theaterProjects, tvProjects);
	}
	
	/**
	 * Method that access the data csv file, parsers line by line and 
	 * create appropriate Project objects based on project_type
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void dataReader() throws IOException, ParseException {
		
		// Captures user input to determine file path and name
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the input file name (e.g. scotia_visual_productions_projects.csv): ");
		String inputFileName = scanner.nextLine();
	    File dataFile = new File(inputFileName); // Capture existing input file
	    
	    String newInput;
	    while (!(dataFile).exists()) {
	    	System.out.print("Provide a valid input file name: ");
	        newInput = scanner.nextLine();
	        dataFile = new File(newInput);
	    }
		
		String line = "";  
		String splitBy = ","; // set comma as separator 
		
		BufferedReader br = new BufferedReader(new FileReader(dataFile)); 
		int iterationCSV = 0; // used to ignore the header row of the csv
		
		while ((line = br.readLine()) != null)   //returns a Boolean value  
		{  
			if(iterationCSV == 0) { // this line is used to ignore the header row
				iterationCSV++;  
		        continue;
		    }
			
			String[] project = line.split(splitBy);    // use comma as separator  
			
			String projectId = project[0];
			String projectName = project[1];
			String projectType = project[2];
			Date projectDate = new SimpleDateFormat("dd/MM/yyyy").parse(project[3]);  
			String projectLocation = project[4];
			Double projectCost = Double.parseDouble(project[5]);
			Double projectPriceToCustomer = Double.parseDouble(project[6]);
			String sizeOfVenue = project[7];
			int projectDuration = Integer.parseInt(project[8]);
			String projectDurationUnit = project[9];
			
			// Each project will be added to both the All Projects arraylist and the 
			// specific project type arraylist
			if (projectType.equals("Film")) {
				// Create new Music Project obj and add it to the ArrayList
				String format = project[13];
				FilmProject projectObj = new FilmProject(projectId, projectName, 
						projectDate, projectLocation,projectCost,projectPriceToCustomer, 
						sizeOfVenue, projectDuration, projectDurationUnit, format);
				dataProjects.add(projectObj);
				filmProjects.add(projectObj);
			}
			else if (projectType.equals("Music")) {
				// Create new Music Project obj and add it to the ArrayList
				String genre = project[12];
				MusicProject projectObj = new MusicProject(projectId, projectName, 
						projectDate, projectLocation,projectCost,projectPriceToCustomer, 
						sizeOfVenue, projectDuration, projectDurationUnit, genre);
				dataProjects.add(projectObj);
				musicProjects.add(projectObj);
			}
			else if (projectType.equals("Theater")) {
				// Create new Theater Project obj and add it to the ArrayList
				String playwright = project[11];
				TheaterProject projectObj = new TheaterProject(projectId, projectName, 
						projectDate, projectLocation,projectCost,projectPriceToCustomer, 
						sizeOfVenue, projectDuration, projectDurationUnit, playwright);
				dataProjects.add(projectObj);
				theaterProjects.add(projectObj);
			}
			else if (projectType.equals("TV")) {
				// Create new TV Project obj and add it to the ArrayList
				String network = project[10];
				TVProject projectObj = new TVProject(projectId, projectName, projectDate,
						projectLocation,projectCost, projectPriceToCustomer, 
						sizeOfVenue, projectDuration, projectDurationUnit, network);
				dataProjects.add(projectObj);
				tvProjects.add(projectObj);
			}
			else {
				// Create new Project obj and add it to the ArrayList
				Project projectObj = new Project(projectId, projectName, projectType, 
						projectDate, projectLocation,projectCost, 
						projectPriceToCustomer, sizeOfVenue, projectDuration,
						projectDurationUnit);
				dataProjects.add(projectObj);
			}

		}
		
	}
}
