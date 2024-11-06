import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * This class inherits the Java Swing framework as per online library documentation
 */
public class GUI  extends javax.swing.JFrame {
	
	private JFrame frame;
	private List<Project> projects;
	private List<FilmProject> filmProjects;
	private JComboBox<String> locationFilterComboBox;
	private JComboBox<String> venueSizeFilterComboBox;
	private List<MusicProject> musicProjects;
	private List<TheaterProject> theaterProjects;
	private List<TVProject> tvProjects;
	private JTable tableFilmProjects;
	private JTable tableMusicProjects;
	private JTable tableTheaterProjects;
	private JTable tableTVProjects;
	private JTable tableProjects;
	private static JPanel tableAllProjectsPanel;
	private static JPanel tableFilmProjectsPanel;
	private static JPanel tableTheaterProjectsPanel;
	private static JPanel tableMusicProjectsPanel;
	private static JPanel tableTVProjectsPanel;
	private JPanel tablesPanel;
	private JTextArea summaryTextArea;
	
	/**
	 * Class Constructor
	 * @param projects
	 * @param filmProjects
	 * @param musicProjects
	 * @param theaterProjects
	 * @param tvProjects
	 */
	public GUI(List<Project> projects, List<FilmProject> filmProjects, 
			List<MusicProject> musicProjects, List<TheaterProject> theaterProjects,
			List<TVProject> tvProjects) {
		
		this.projects = projects;
		this.filmProjects = filmProjects;
		this.musicProjects = musicProjects;
		this.theaterProjects = theaterProjects;
		this.tvProjects = tvProjects;
		
		SwingUtilities.invokeLater(() -> {
            try {
                // Set the look and feel
            	// This is used for determining the design of the GUI
                UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            } catch (Exception e) {
                e.printStackTrace();
            }

            createAndShowGUI();
        });
    }

	/**
	 * This method is responsible for the main body of the GUI and the
	 * triggering of button actions as relevant
	 */
    private void createAndShowGUI() {
    	
        // Create and configure the main GUI frame (the GUI window)
        JFrame frame = new JFrame("Scotia Visual Productions - Projects");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 600);
        frame.setLocationRelativeTo(null); // Centre the GUI frame on the desktop screen

        // Create and configure the content panel
        // This panel is the base which will hold all other panels and components
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(new Color(18, 30, 49)); // Navy blue
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Create and configure the side menu panel
        JPanel sideMenuPanel = new JPanel(new GridLayout(5, 1, 0, 10));
        sideMenuPanel.setBackground(new Color(32, 53, 84)); // Dark blue
        sideMenuPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Create menu options based on Project Types
        String[] menuOptions = {"All", "Film", "Theater", "Music", "TV"};
        // loop through button options and determine its actions
        for (String option : menuOptions) {
            JButton button = new JButton(option);
            button.setForeground(Color.WHITE); // Set text colour to white
            button.setBackground(new Color(32, 53, 84)); // Dark blue
            button.addActionListener(e -> { // action of each button
            	
                // Action to perform when project type button option is clicked
            	// When clicked, table with relevant dataset is selected and made available
            	// as well as summary text is created based on filters
            	
            	if(option.equals("Film")) {
            		setSelectedTablePanel(tableFilmProjectsPanel);
            		summaryText(locationFilterComboBox.getSelectedItem().toString(),
                    		venueSizeFilterComboBox.getSelectedItem().toString());
            	}
            	else if(option.equals("Theater")) {
            		setSelectedTablePanel(tableTheaterProjectsPanel);
            		summaryText(locationFilterComboBox.getSelectedItem().toString(),
                    		venueSizeFilterComboBox.getSelectedItem().toString());
            	}
            	else if(option.equals("Music")) {
            		setSelectedTablePanel(tableMusicProjectsPanel);
            		summaryText(locationFilterComboBox.getSelectedItem().toString(),
                    		venueSizeFilterComboBox.getSelectedItem().toString());
            	}
            	else if(option.equals("TV")) {
            		setSelectedTablePanel(tableTVProjectsPanel);
            		summaryText(locationFilterComboBox.getSelectedItem().toString(),
                    		venueSizeFilterComboBox.getSelectedItem().toString());
            	}
            	else if(option.equals("All")) {
            		setSelectedTablePanel(tableAllProjectsPanel);
            		summaryText(locationFilterComboBox.getSelectedItem().toString(),
                    		venueSizeFilterComboBox.getSelectedItem().toString());
            	}
            	summaryText(locationFilterComboBox.getSelectedItem().toString(),
                		venueSizeFilterComboBox.getSelectedItem().toString());
                JOptionPane.showMessageDialog(frame, "Data filtered to: " + option, "Project Type Selected", JOptionPane.INFORMATION_MESSAGE);
            });
            sideMenuPanel.add(button);
        }

        // Add side menu panel to content panel
        contentPanel.add(sideMenuPanel, BorderLayout.WEST);
        
        // Create and configure the filter side menu
        JPanel filterPanel = new JPanel(new GridLayout(10, 1, 0, 10));
        filterPanel.setBackground(new Color(32, 53, 84)); // Dark blue
        filterPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        // Add a text identifying menu as Filters to improve user friendliness
        JLabel filtersLabel = new JLabel("Filters", SwingConstants.CENTER);
        filtersLabel.setForeground(Color.WHITE);
        Font boldFont = filtersLabel.getFont().deriveFont(Font.BOLD);
        filtersLabel.setFont(boldFont);
        filtersLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        
        // Create label and dropdown for Location filter
        JLabel locationLabel = new JLabel("Location");
        locationLabel.setForeground(Color.WHITE);
        // Options on the dropdown
        String[] locationOptions = {"All", "Aberdeen","Berlin","Chicago","Dunfermline",
        		"Edinburgh","Glasgow","Inverness","Los Angeles","New York","Paris", 
        		"Stirling","Toronto", "Vienna"};
        locationFilterComboBox = new JComboBox<>();
        for (String option : locationOptions) {
        	locationFilterComboBox.addItem(option);
        }
        
        // Create label and dropdown for Venue Size filter
        JLabel venueSizeLabel = new JLabel("Venue Size");
        venueSizeLabel.setForeground(Color.WHITE);
        // Options of venue sizes
        String[] venueSizeOptions = {"All", "Small", "Medium", "Large"};
        venueSizeFilterComboBox = new JComboBox<>();
        for (String option : venueSizeOptions) {
        	venueSizeFilterComboBox.addItem(option);
        }
        
        // Creation of a button to save and apply selected filters
        // Filters will not be applied until this button is selected
        // Once selected, the tables are updated with the relevant filtering
        JButton applyFiltersBtn = new JButton("Apply Filters");
        applyFiltersBtn.setForeground(Color.WHITE); // Set text colour to white
        applyFiltersBtn.setBackground(new Color(32, 53, 84)); // Dark blue
        applyFiltersBtn.addActionListener(e -> {
        	tableFilmProjects = createTable("Update", "Film", locationFilterComboBox.getSelectedItem().toString(),
            		venueSizeFilterComboBox.getSelectedItem().toString());
        	tableMusicProjects = createTable("Update", "Music", locationFilterComboBox.getSelectedItem().toString(),
            		venueSizeFilterComboBox.getSelectedItem().toString());
        	tableTheaterProjects = createTable("Update", "Theater", locationFilterComboBox.getSelectedItem().toString(),
            		venueSizeFilterComboBox.getSelectedItem().toString());
        	tableTVProjects = createTable("Update", "TV", locationFilterComboBox.getSelectedItem().toString(),
            		venueSizeFilterComboBox.getSelectedItem().toString());
        	tableProjects = createTable("Update", "All", locationFilterComboBox.getSelectedItem().toString(),
            		venueSizeFilterComboBox.getSelectedItem().toString());
        });
        
        // Create empty label to be used as a space (design purposes only)
        JLabel spaceLabel = new JLabel("");
        
        // Button option to add a new project to the dataset
        // Once this button is selected, a pop-up box appears with fields to be filled
        JButton newRecordBtn = new JButton("Add new project");
        newRecordBtn.setForeground(Color.WHITE); // Set text colour to white
        newRecordBtn.setBackground(new Color(32, 53, 84)); // Dark blue
        newRecordBtn.addActionListener(e-> {
        	// Create a JPanel to hold the form components
            JPanel formPanel = new JPanel(new GridLayout(0, 2, 5, 5));

            // Add text field into Form Panel for Project ID
            formPanel.add(new JLabel("Project ID:"));
            JTextField projectIDField = new JTextField();
            formPanel.add(projectIDField);
            
            // Add text field for Project Name
            formPanel.add(new JLabel("Project Name:"));
            JTextField projectNameField = new JTextField();
            formPanel.add(projectNameField);

            // Add dropdown field for Project Type
            formPanel.add(new JLabel("Project Type:"));
            String[] projectTypes = {"Film", "Music", "Theater", "TV"};
            JComboBox<String> projectTypeComboBox = new JComboBox<>(projectTypes);
            formPanel.add(projectTypeComboBox);
            
            // Creation of optional fields and text labels
            // This would refer to the fields exclusive to each project type
            // Refer to UML Diagram for further information
            JTextField filmFormatField = new JTextField();
            JLabel filmFormat = new JLabel("Film Format:");
        	JTextField theaterPlaywrightField = new JTextField();
        	JLabel theaterPlaywright = new JLabel("Theater Playwright:");
        	JTextField musicGenreField = new JTextField();
            formPanel.add(new JLabel("Project Cost (£):"));
            JTextField projectCost = new JTextField();
            formPanel.add(projectCost);
        	JLabel musicGenre = new JLabel("Music Genre:");
        	JTextField tvNetworkField = new JTextField();
        	JLabel tvNetwork = new JLabel("TV Network:");
            
            // Add text field for date
            formPanel.add(new JLabel("Project Date (dd/MM/yyyy):"));
            JTextField projectDate = new JTextField();
            formPanel.add(projectDate);
            
            // Add dropdown field for Project Location
            formPanel.add(new JLabel("Project Location:"));
            String[] projectLocations = Arrays.copyOfRange(locationOptions, 1, locationOptions.length);
            JComboBox<String> projectLocationComboBox = new JComboBox<>(projectLocations);
            formPanel.add(projectLocationComboBox);
            
            // Add field for Cost to customer
            formPanel.add(new JLabel("Cost to Customer (£):"));
            JTextField projectCostToCustomer = new JTextField();
            formPanel.add(projectCostToCustomer);
            
            // Add dropdown field for Venue Size
            formPanel.add(new JLabel("Project Type:"));
            String[] venueSizes = {"Small", "Medium", "Large"};
            JComboBox<String> venueSizeComboBox = new JComboBox<>(venueSizes);
            formPanel.add(venueSizeComboBox);
            
            // Add int field for Duration
            formPanel.add(new JLabel("Project Duration:"));
            SpinnerModel projectDurationSpinnerModel = new SpinnerNumberModel(1,1,48,1); // Set initial value as 1, min value as 1, max value as 48, and step
            JSpinner projectDurationSpinner = new JSpinner(projectDurationSpinnerModel);
            formPanel.add(projectDurationSpinner);
            
            // Add dropdown for Duration Unit
            formPanel.add(new JLabel("Duration Unit:"));
            String[] durationUnitOptions = {"hours", "days"};
            JComboBox<String> durationUnitComboBox = new JComboBox<>(durationUnitOptions);
            formPanel.add(durationUnitComboBox);
            
            // Placeholder on forms where optional fields will be located
            // This is used for design purposes only, facilitating the appearance of optional
            // fields conditional to project type selected
            JLabel placeholderField = new JLabel("");
            formPanel.add(placeholderField);
            
            // Action to be performed once a project type has been selected.
            // For each project type, the relevant optional field should appear.
            // Accounting for potential modifications, it also removes fields from other types
            // that are not applicable for the scenario.
            projectTypeComboBox.addActionListener(event -> {
            	String selectedType = (String) projectTypeComboBox.getSelectedItem();
            	
            	if (selectedType.equals("Film")) {
                    formPanel.add(filmFormat);
                    formPanel.add(filmFormatField);
                    formPanel.remove(placeholderField);
                    formPanel.remove(theaterPlaywrightField);
                    formPanel.remove(theaterPlaywright);
                    formPanel.remove(musicGenre);
                    formPanel.remove(musicGenreField);
                    formPanel.remove(tvNetwork);
                    formPanel.remove(tvNetworkField);
            		}
            	else if (selectedType.equals("Theater")) {
                    formPanel.add(theaterPlaywright);
                    formPanel.add(theaterPlaywrightField);
                    formPanel.remove(placeholderField);
                    formPanel.remove(filmFormatField);
                    formPanel.remove(filmFormat);
                    formPanel.remove(musicGenre);
                    formPanel.remove(musicGenreField);
                    formPanel.remove(tvNetwork);
                    formPanel.remove(tvNetworkField);
            		}
            	else if (selectedType.equals("Music")) {
                    formPanel.add(musicGenre);
                    formPanel.add(musicGenreField);
                    formPanel.remove(placeholderField);
                    formPanel.remove(filmFormatField);
                    formPanel.remove(filmFormat);
                    formPanel.remove(theaterPlaywrightField);
                    formPanel.remove(theaterPlaywright);
                    formPanel.remove(tvNetwork);
                    formPanel.remove(tvNetworkField);
            		}
            	else if (selectedType.equals("TV")) {
                    formPanel.add(tvNetwork);
                    formPanel.add(tvNetworkField);
                    formPanel.remove(placeholderField);
                    formPanel.remove(filmFormatField);
                    formPanel.remove(filmFormat);
                    formPanel.remove(theaterPlaywrightField);
                    formPanel.remove(theaterPlaywright);
                    formPanel.remove(musicGenre);
                    formPanel.remove(musicGenreField);
            		}
            	
            	formPanel.revalidate();
                formPanel.repaint();
            	}
            );

            // Show the pop-up dialog with the form for new project record creation
            int result = JOptionPane.showConfirmDialog(frame, formPanel, "New Project Record", JOptionPane.OK_CANCEL_OPTION);
            
            // Set action triggered by user clicking on Ok button
            // Action to capture the values of the fields, save in local variables
            // and trigger the creation of the new record within the relevant
            // Java classes.
            // Field types are casted and parsed as required when saving to relevant variables
            if (result == JOptionPane.OK_OPTION) {
            	String projectID = projectIDField.getText();
	            String projectName = projectNameField.getText();
	            String type = (String) projectTypeComboBox.getSelectedItem();
	            // Parse the date field from String to Date
	            Date projectDateVal = Calendar.getInstance().getTime();
	            try {
	                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	                projectDateVal = dateFormat.parse(projectDate.getText());
	            } catch (ParseException ex) {
	                // Handle the case where the input date format is incorrect
	                // For example, display an error message to the user
	                System.err.println("Invalid date format. Please enter date in dd/MM/yyyy format.");
	            }
	            String location = (String) projectLocationComboBox.getSelectedItem();
	            double projectCostVal = Double.parseDouble(projectCost.getText());
	            double projectCostToCost = Double.parseDouble(projectCostToCustomer.getText());
	            String venueSize = (String) venueSizeComboBox.getSelectedItem();
	            int projectDuration = (int) projectDurationSpinner.getValue();
	            String durationUnit = (String) durationUnitComboBox.getSelectedItem();
	            String optionalField;
	            
	            // Define which field is the optional field for each project type case
	            if(type.equals("Film")) {optionalField = filmFormatField.getText();
	            }else if (type.equals("Music")) {
	            	optionalField = musicGenreField.getText();}else if (
	            			type.equals("Theater")) {
	            		optionalField = theaterPlaywrightField.getText();} else {
	            			optionalField = tvNetworkField.getText();
	            		}
	            
	            // Call method to create a new project
	            createNewProject(projectID, projectName, type, projectDateVal, location,
	            		projectCostVal, projectCostToCost, venueSize, projectDuration,
	            		durationUnit, optionalField);
            }
        });
        
        // Add all the filter fields and buttons (Apply filters and Create new record) to 
        // the filter panel
        filterPanel.add(filtersLabel);
        filterPanel.add(locationLabel);
        filterPanel.add(locationFilterComboBox);
        filterPanel.add(venueSizeLabel);
        filterPanel.add(venueSizeFilterComboBox);
        filterPanel.add(spaceLabel);
        filterPanel.add(applyFiltersBtn);
        filterPanel.add(newRecordBtn);
        
        // Save filter panel in the right hand side of the window
        contentPanel.add(filterPanel, BorderLayout.EAST);
        
        // Create and configure the footer panel
        JPanel footerPanel = new JPanel(new BorderLayout());
        footerPanel.setBackground(new Color(32, 53, 84)); // Dark blue
        footerPanel.setBorder(new EmptyBorder(5, 10, 5, 10));

        // Create and configure the footer label
        JLabel footerLabel = new JLabel("© 2024 isaqueiros", SwingConstants.CENTER); // Footer text with my own customisation :)
        footerLabel.setForeground(Color.WHITE); // Set text colour to white
        footerPanel.add(footerLabel, BorderLayout.CENTER); // centralise text in the footer

        // Add footer panel to content panel in the end of the window
        contentPanel.add(footerPanel, BorderLayout.SOUTH);
        
        // Create and configure the buttons panel which have option of Projects Data and Summary
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        buttonsPanel.setBackground(new Color(18, 30, 49)); // Navy blue

        // Create and configure the "Projects Data" button
        JButton projectsDataButton = new JButton("Projects Data");
        projectsDataButton.setForeground(Color.WHITE);
        projectsDataButton.setBackground(new Color(32, 53, 84)); // Dark blue
        projectsDataButton.setPreferredSize(new Dimension(400, 30));
        projectsDataButton.addActionListener(e -> {
            // Show table panel when "Projects Data" button is clicked
        	tableAllProjectsPanel.setVisible(true);
        });

        // Create and configure the "Summary" button
        // Once clicked, the summary opens a pop-up window with produced text based
        // on dataset and selected filters.
        JButton summaryButton = new JButton("Summary");
        summaryButton.setForeground(Color.WHITE);
        summaryTextArea = new JTextArea();
        summaryButton.setBackground(new Color(32, 53, 84)); // Dark blue
        summaryButton.setPreferredSize(new Dimension(400, 30));
        summaryButton.addActionListener(e -> {
            // Initialise a JTextArea if not existant and update summary text
        	if (summaryTextArea == null) {
                summaryTextArea.setLineWrap(true);
                summaryTextArea.setWrapStyleWord(true);
                summaryTextArea.setEditable(false);
            }
        	// Generate the summary text based on filters
        	// Text is generated with summaryText method
            summaryText(locationFilterComboBox.getSelectedItem().toString(),
            		venueSizeFilterComboBox.getSelectedItem().toString());

            // Create the pop-up window with the summary text
            JScrollPane scrollPane = new JScrollPane(summaryTextArea);
            scrollPane.setPreferredSize(new Dimension(400, 200));
            JOptionPane.showMessageDialog(frame, scrollPane, "Summary", JOptionPane.INFORMATION_MESSAGE);
        });

        // Add buttons Summary and Data to the buttons panel
        buttonsPanel.add(projectsDataButton);
        buttonsPanel.add(summaryButton);
        
        // Create and configure the table panel to store the All Projects table
        tableAllProjectsPanel = new JPanel(new BorderLayout());
        tableAllProjectsPanel.setBackground(new Color(18, 30, 49)); // Navy blue

        // Create and configure the table for all Projects
        tableProjects = createTable("Create", "All", locationFilterComboBox.getSelectedItem().toString(),
        		venueSizeFilterComboBox.getSelectedItem().toString());
        JScrollPane scrollPaneAllProjects = new JScrollPane(tableProjects);
        tableAllProjectsPanel.add(scrollPaneAllProjects, BorderLayout.CENTER);
        tableAllProjectsPanel.setVisible(true);
        
        // Create and configure the table panel to store Film Projects table
        tableFilmProjectsPanel = new JPanel(new BorderLayout());
        tableFilmProjectsPanel.setBackground(new Color(18, 30, 49)); // Navy blue

        // Create and configure the table for Film Projects
        tableFilmProjects = createTable("Create", "Film", locationFilterComboBox.getSelectedItem().toString(),
        		venueSizeFilterComboBox.getSelectedItem().toString());
        JScrollPane scrollPaneFilmProjects = new JScrollPane(tableFilmProjects);
        tableFilmProjectsPanel.add(scrollPaneFilmProjects, BorderLayout.CENTER);
        
        // Create and configure the table panel to store Theater Projects table
        tableTheaterProjectsPanel = new JPanel(new BorderLayout());
        tableTheaterProjectsPanel.setBackground(new Color(18, 30, 49)); // Navy blue

        // Create and configure the table for Theater Projects
        tableTheaterProjects = createTable("Create", "Theater", locationFilterComboBox.getSelectedItem().toString(),
        		venueSizeFilterComboBox.getSelectedItem().toString());
        JScrollPane scrollPaneTheaterProjects = new JScrollPane(tableTheaterProjects);
        tableTheaterProjectsPanel.add(scrollPaneTheaterProjects, BorderLayout.CENTER);
        
        // Create and configure the table panel to store Music Projects table
        tableMusicProjectsPanel = new JPanel(new BorderLayout());
        tableMusicProjectsPanel.setBackground(new Color(18, 30, 49)); // Navy blue

        // Create and configure the table for Music Projects
        tableMusicProjects = createTable("Create", "Music", locationFilterComboBox.getSelectedItem().toString(),
        		venueSizeFilterComboBox.getSelectedItem().toString());
        JScrollPane scrollPaneMusicProjects = new JScrollPane(tableMusicProjects);
        tableMusicProjectsPanel.add(scrollPaneMusicProjects, BorderLayout.CENTER);
        
        // Create and configure the table panel to store TV Projects table
        tableTVProjectsPanel = new JPanel(new BorderLayout());
        tableTVProjectsPanel.setBackground(new Color(18, 30, 49)); // Navy blue

        // Create and configure the table for TV Projects
        tableTVProjects = createTable("Create", "TV", locationFilterComboBox.getSelectedItem().toString(),
        		venueSizeFilterComboBox.getSelectedItem().toString());
        JScrollPane scrollPanelTVProjects = new JScrollPane(tableTVProjects);
        tableTVProjectsPanel.add(scrollPanelTVProjects, BorderLayout.CENTER);
        
        // Create a panel to hold the current table based on filters
        // Note: this setting is controlled via the Filters triggers
        tablesPanel = new JPanel(new GridLayout(0, 1));

        // Add table panels to the all projects panel
        tablesPanel.add(tableAllProjectsPanel);

        // Add table and button panel to content panel, which is the main space in
        // the GUI window
        contentPanel.add(buttonsPanel, BorderLayout.NORTH); // display at the top
        contentPanel.add(tablesPanel, BorderLayout.CENTER); // display centrally

        // Add content panel to the frame (window)
        frame.add(contentPanel);
        // Set the frame as visible
        frame.setVisible(true);
    }
    
    /**
     * Method creates or updates tables based on selected filters and project type
     * @param action
     * @param typeOfProject
     * @param selectedLocation
     * @param selectedVenueSize
     * @return
     */
    private JTable createTable(String action, String typeOfProject, 
    		String selectedLocation, String selectedVenueSize) {
    	DefaultTableModel model;
    	
    	if(typeOfProject.equals("Film")) {
    		// Define column names
            String[] columnNamesFilm = {"Project ID", "Project Name", "Project Type", "Project Date", "Project Location",
                    "Project Cost", "Price to Customer", "Size of Venue", "Duration", "Duration Unit", "Format"};
            
            // Create table structure with column names and 0 rows if action requested
            // by method call is to create (other option: Update)
            if(action.equals("Create")) {
            	model = new DefaultTableModel(columnNamesFilm, 0);
            }
            else { // if action is Update, capture existing model and reset but don't create a new instance
            	model = (DefaultTableModel) tableFilmProjects.getModel();
                model.setRowCount(0);
            }

            // Populate table model with project data based on project type passed
            // FIlters consider all the three options of filters in the GUI
            for (FilmProject project : filmProjects) {
            	if(project.getProjectType().equals(typeOfProject) && (
            			selectedLocation.equals("All") || project.getProjectLocation().equals(
            					selectedLocation)) && (selectedVenueSize.equals(
            							"All") || project.getSizeOfVenue().equals(
            									selectedVenueSize))) {
            	
            	SimpleDateFormat newDateFormat = new SimpleDateFormat("dd/MM/yyyy");
	            String formattedDate = newDateFormat.format(project.getProjectDate());
	            
	            Object[] rowData = {
	                    project.getProjectId(),
	                    project.getProjectName(),
	                    project.getProjectType(),
	                    formattedDate,
	                    project.getProjectLocation(),
	                    project.getProjectCost(),
	                    project.getProjectPriceToCustomer(),
	                    project.getSizeOfVenue(),
	                    project.getProjectDuration(),
	                    project.getDurationUnit(),
	                    project.getFormat()	            
	                    };
	            model.addRow(rowData);
            	}
            }
    	}
    	
    	else if(typeOfProject.equals("Music")) {
    		// Define column names
            String[] columnNamesMusic = {"Project ID", "Project Name", "Project Type", "Project Date", "Project Location",
                    "Project Cost", "Price to Customer", "Size of Venue", "Duration", "Duration Unit", "Genre"};
            
            // Create table with column names and 0 rows
            if(action.equals("Create")) {
            	model = new DefaultTableModel(columnNamesMusic, 0);
            }
            else { // if action is Update, capture existing model and reset
            	model = (DefaultTableModel) tableMusicProjects.getModel();
                model.setRowCount(0);
            }

            // Populate table model with music project data
            for (MusicProject project : musicProjects) {
            	if(project.getProjectType().equals(typeOfProject) && (
            			selectedLocation.equals("All") || project.getProjectLocation().equals(
            					selectedLocation)) && (selectedVenueSize.equals(
            							"All") || project.getSizeOfVenue().equals(
            									selectedVenueSize))) {
            	
            	SimpleDateFormat newDateFormat = new SimpleDateFormat("dd/MM/yyyy");
	            String formattedDate = newDateFormat.format(project.getProjectDate());
	            
	            Object[] rowData = {
	                    project.getProjectId(),
	                    project.getProjectName(),
	                    project.getProjectType(),
	                    formattedDate,
	                    project.getProjectLocation(),
	                    project.getProjectCost(),
	                    project.getProjectPriceToCustomer(),
	                    project.getSizeOfVenue(),
	                    project.getProjectDuration(),
	                    project.getDurationUnit(),
	                    project.getGenre()	            
	                    };
	            model.addRow(rowData);
            	}
            }
    	}
    	
    	else if(typeOfProject.equals("Theater") ) {
    		// Define column names
            String[] columnNamesTheater = {"Project ID", "Project Name", "Project Type", "Project Date", "Project Location",
                    "Project Cost", "Price to Customer", "Size of Venue", "Duration", "Duration Unit", "Playwright"};
            
            // Create table with column names and 0 rows
            if(action.equals("Create")) {
            	model = new DefaultTableModel(columnNamesTheater, 0);
            }
            else { // if action is Update, capture existing model and reset
            	model = (DefaultTableModel) tableTheaterProjects.getModel();
                model.setRowCount(0);
            }

            // Populate table model with theater project data
            for (TheaterProject project : theaterProjects) {
            	if(project.getProjectType().equals(typeOfProject) && (
            			selectedLocation.equals("All") || project.getProjectLocation().equals(
            					selectedLocation)) && (selectedVenueSize.equals(
            							"All") || project.getSizeOfVenue().equals(
            									selectedVenueSize))) {
            	
            	SimpleDateFormat newDateFormat = new SimpleDateFormat("dd/MM/yyyy");
	            String formattedDate = newDateFormat.format(project.getProjectDate());
	            
	            Object[] rowData = {
	                    project.getProjectId(),
	                    project.getProjectName(),
	                    project.getProjectType(),
	                    formattedDate,
	                    project.getProjectLocation(),
	                    project.getProjectCost(),
	                    project.getProjectPriceToCustomer(),
	                    project.getSizeOfVenue(),
	                    project.getProjectDuration(),
	                    project.getDurationUnit(),
	                    project.getPlaywright()	            
	                    };
	            model.addRow(rowData);
            	}
            }
    	}
    	
    	else if(typeOfProject.equals("TV")) {
    		// Define column names
            String[] columnNamesTV = {"Project ID", "Project Name", "Project Type", "Project Date", "Project Location",
                    "Project Cost", "Price to Customer", "Size of Venue", "Duration", "Duration Unit", "Network"};
            
            // Create table with column names and 0 rows
            if(action.equals("Create")) {
            	model = new DefaultTableModel(columnNamesTV, 0);
            }
            else { // if action is Update, capture existing model and reset
            	model = (DefaultTableModel) tableTVProjects.getModel();
                model.setRowCount(0);
            }

            // Populate table model with TV project data
            for (TVProject project : tvProjects) {
            	if(project.getProjectType().equals(typeOfProject) && (
            			selectedLocation.equals("All") || project.getProjectLocation().equals(
            					selectedLocation)) && (selectedVenueSize.equals(
            							"All") || project.getSizeOfVenue().equals(
            									selectedVenueSize))) {
            	
            	SimpleDateFormat newDateFormat = new SimpleDateFormat("dd/MM/yyyy");
	            String formattedDate = newDateFormat.format(project.getProjectDate());
	            
	            Object[] rowData = {
	                    project.getProjectId(),
	                    project.getProjectName(),
	                    project.getProjectType(),
	                    formattedDate,
	                    project.getProjectLocation(),
	                    project.getProjectCost(),
	                    project.getProjectPriceToCustomer(),
	                    project.getSizeOfVenue(),
	                    project.getProjectDuration(),
	                    project.getDurationUnit(),
	                    project.getNetwork()	            
	                    };
	            model.addRow(rowData);
            	}
            }
    	}
    	
    	else if (typeOfProject.equals("All")) {
            // Define column names
    	        String[] columnNames = {"Project ID", "Project Name", "Project Type", "Project Date", "Project Location",
    	                "Project Cost", "Price to Customer", "Size of Venue", "Duration", "Duration Unit"};
    	        
    	     // Create table with column names and 0 rows
                if(action.equals("Create")) {
                	model = new DefaultTableModel(columnNames, 0);
                }
                else { // if action is Update, capture existing model and reset
                	model = (DefaultTableModel) tableProjects.getModel();
                    model.setRowCount(0);
                }
    	
    	        // Populate table model with all project data
    	        for (Project project : projects) {
    	        	
    	        	if((selectedLocation.equals("All") || project.getProjectLocation(
    	        			).equals(selectedLocation)) && (selectedVenueSize.equals(
    	        					"All") || project.getSizeOfVenue().equals(
    	        							selectedVenueSize))) {
    	        	
    		        	SimpleDateFormat newDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    		            String formattedDate = newDateFormat.format(project.getProjectDate());
    		            
    		            Object[] rowData = {
    		                    project.getProjectId(),
    		                    project.getProjectName(),
    		                    project.getProjectType(),
    		                    formattedDate,
    		                    project.getProjectLocation(),
    		                    project.getProjectCost(),
    		                    project.getProjectPriceToCustomer(),
    		                    project.getSizeOfVenue(),
    		                    project.getProjectDuration(),
    		                    project.getDurationUnit()
    		            };
    		            model.addRow(rowData);
    	        	}
    	        }
    	}
    	
    	else { // Use case if new project type is added via Excel
        // Define column names
	        String[] columnNames = {"Project ID", "Project Name", "Project Type", "Project Date", "Project Location",
	                "Project Cost", "Price to Customer", "Size of Venue", "Duration", "Duration Unit"};
	        
	     // Create table with column names and 0 rows
            if(action.equals("Create")) {
            	model = new DefaultTableModel(columnNames, 0);
            }
            else { // if action is Update, capture existing model and reset
            	model = (DefaultTableModel) tableProjects.getModel();
                model.setRowCount(0);
            }
	
	        // Populate table model with project data
	        for (Project project : projects) {
	        	
	        	if(project.getProjectType().equals(
	        			typeOfProject) && (selectedLocation.equals(
	        					"All") || project.getProjectLocation().equals(
	                				selectedLocation)) && (selectedVenueSize.equals(
	                					"All") || project.getSizeOfVenue().equals(
	                						selectedVenueSize))) {
	        	
		        	SimpleDateFormat newDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		            String formattedDate = newDateFormat.format(project.getProjectDate());
		            
		            Object[] rowData = {
		                    project.getProjectId(),
		                    project.getProjectName(),
		                    project.getProjectType(),
		                    formattedDate,
		                    project.getProjectLocation(),
		                    project.getProjectCost(),
		                    project.getProjectPriceToCustomer(),
		                    project.getSizeOfVenue(),
		                    project.getProjectDuration(),
		                    project.getDurationUnit()
		            };
		            model.addRow(rowData);
	        	}
	        }}

        // Create and return JTable with populated table model based on filters
        return  new JTable(model);
    }
    
    /**
     * Based on Project Type filter together with location and venue size filters,
     * generates financial summary of projects, including cost, revenue, profit and
     * workload.
     * @param selectedLocation
     * @param selectedVenueSize
     */
    private void summaryText(String selectedLocation, String selectedVenueSize) {
    	String summary = "Summary produced based on filters where Project Type is ";
    	DecimalFormat df = new DecimalFormat("#,##0.00");
    	
    	// Based on the current filters and what table is visible within the tablesPanel
    	if(tableAllProjectsPanel.getParent()==tablesPanel) { // uses GUI components hierarchy to check if table is within tablesPanel
    		summary += "All";
    		if(!selectedLocation.equals("All")) {
    			summary += ",\nProject Location is: "+selectedLocation;
    		}
    		if(!selectedVenueSize.equals("All")) {
    			summary += "\nand Venue Size is: "+selectedVenueSize;
    		}
    		summary += "\n\n";
    		
    		// Trackers
    		int projectsCount = 0;
    		double projectCost = 0.0;
    		double projectValue = 0.0;
    		int hours = 0;
    		int days = 0;
    		
    		// loop through the projects to update trackers with count and sums as relevant
    		for (Project project : projects) {
    			
    			if((selectedLocation.equals("All") || project.getProjectLocation(
	        			).equals(selectedLocation)) && (selectedVenueSize.equals(
	        					"All") || project.getSizeOfVenue().equals(
	        							selectedVenueSize))) {
    				projectsCount += 1;
    				projectCost += project.getProjectCost();
    				projectValue += project.getProjectPriceToCustomer();
    				if(project.getDurationUnit().equals("hours")) {
    					hours += project.getProjectDuration();
    				} else if (project.getDurationUnit().equals("days")) {
    					days += project.getProjectDuration();
    				}
    			}
    		}
    		
    		double profit = projectValue - projectCost;
    		double workload = days + (hours/24);
    		
    		// Text to be generated with values of trackers
    		summary += "Scotia Visual Productions has produced a total of "+ Integer.toString(
    				projectsCount)+" projects.\n"
    				+ "The cost of the projects has sum to £"+
    				df.format(projectCost)+" and\nhas generated a revenue of £"+
    				df.format(projectValue)+".\nBased on this performance, "
    				+ "the business has achieved £"+df.format(profit)+"\nin profit.\n"
    				+"The "+ Integer.toString(projectsCount)+" projects required "+
    				df.format(workload)+" days of work.";
    	}
    	else if(tableFilmProjectsPanel.getParent()==tablesPanel) {
    		summary += "Film";
    		if(!selectedLocation.equals("All")) {
    			summary += ",\nProject Location is: "+selectedLocation;
    		}
    		if(!selectedVenueSize.equals("All")) {
    			summary += "\nand Venue Size is: "+selectedVenueSize;
    		}
    		summary += "\n\n";
    		
    		int projectsCount = 0;
    		double projectCost = 0.0;
    		double projectValue = 0.0;
    		int hours = 0;
    		int days = 0;
    		
    		for (FilmProject project : filmProjects) {
    			
    			if((selectedLocation.equals("All") || project.getProjectLocation(
	        			).equals(selectedLocation)) && (selectedVenueSize.equals(
	        					"All") || project.getSizeOfVenue().equals(
	        							selectedVenueSize))) {
    				projectsCount += 1;
    				projectCost += project.getProjectCost();
    				projectValue += project.getProjectPriceToCustomer();
    				if(project.getDurationUnit().equals("hours")) {
    					hours += project.getProjectDuration();
    				} else if (project.getDurationUnit().equals("days")) {
    					days += project.getProjectDuration();
    				}
    			}
    		}
    		
    		double profit = projectValue - projectCost;
    		double workload = days + (hours/24);
    		
    		summary += "Scotia Visual Productions has produced a total of "+ Integer.toString(
    				projectsCount)+" Film projects.\n"
    				+ "The cost of the projects has sum to £"+
    				df.format(projectCost)+" and\nhas generated a revenue of £"+
    				df.format(projectValue)+".\nBased on this performance, "
    				+ "the business has achieved £"+df.format(profit)+"\nin profit.\n"
    				+"The "+ Integer.toString(projectsCount)+" projects required "+
    				df.format(workload)+" days of work.";
    	}
    	else if(tableTheaterProjectsPanel.getParent()==tablesPanel) {
    		summary += "Theater";
    		if(!selectedLocation.equals("All")) {
    			summary += ",\nProject Location is: "+selectedLocation;
    		}
    		if(!selectedVenueSize.equals("All")) {
    			summary += "\nand Venue Size is: "+selectedVenueSize;
    		}
    		summary += "\n\n";
    		
    		int projectsCount = 0;
    		double projectCost = 0.0;
    		double projectValue = 0.0;
    		int hours = 0;
    		int days = 0;
    		
    		for (TheaterProject project : theaterProjects) {
    			
    			if((selectedLocation.equals("All") || project.getProjectLocation(
	        			).equals(selectedLocation)) && (selectedVenueSize.equals(
	        					"All") || project.getSizeOfVenue().equals(
	        							selectedVenueSize))) {
    				projectsCount += 1;
    				projectCost += project.getProjectCost();
    				projectValue += project.getProjectPriceToCustomer();
    				if(project.getDurationUnit().equals("hours")) {
    					hours += project.getProjectDuration();
    				} else if (project.getDurationUnit().equals("days")) {
    					days += project.getProjectDuration();
    				}
    			}
    		}
    		
    		double profit = projectValue - projectCost;
    		double workload = days + (hours/24);
    		
    		summary += "Scotia Visual Productions has produced a total of "+ Integer.toString(
    				projectsCount)+" Theater projects.\n"
    				+ "The cost of the projects has sum to £"+
    				df.format(projectCost)+" and\nhas generated a revenue of £"+
    				df.format(projectValue)+".\nBased on this performance, "
    				+ "the business has achieved £"+df.format(profit)+"\nin profit.\n"
    				+"The "+ Integer.toString(projectsCount)+" projects required "+
    	    		df.format(workload)+" days of work.";
    				;
    	}
    	else if(tableMusicProjectsPanel.getParent()==tablesPanel) {
    		summary += "Music";
    		if(!selectedLocation.equals("All")) {
    			summary += ",\nProject Location is: "+selectedLocation;
    		}
    		if(!selectedVenueSize.equals("All")) {
    			summary += "\nand Venue Size is: "+selectedVenueSize;
    		}
    		summary += "\n\n";
    		
    		int projectsCount = 0;
    		double projectCost = 0.0;
    		double projectValue = 0.0;
    		int hours = 0;
    		int days = 0;
    		
    		for (MusicProject project : musicProjects) {
    			
    			if((selectedLocation.equals("All") || project.getProjectLocation(
	        			).equals(selectedLocation)) && (selectedVenueSize.equals(
	        					"All") || project.getSizeOfVenue().equals(
	        							selectedVenueSize))) {
    				projectsCount += 1;
    				projectCost += project.getProjectCost();
    				projectValue += project.getProjectPriceToCustomer();
    				if(project.getDurationUnit().equals("hours")) {
    					hours += project.getProjectDuration();
    				} else if (project.getDurationUnit().equals("days")) {
    					days += project.getProjectDuration();
    				}
    			}
    		}
    		
    		double profit = projectValue - projectCost;
    		double workload = days + (hours/24);
    		
    		summary += "Scotia Visual Productions has produced a total of "+ Integer.toString(
    				projectsCount)+" Music projects.\n"
    				+ "The cost of the projects has sum to £"+
    				df.format(projectCost)+" and\nhas generated a revenue of £"+
    				df.format(projectValue)+".\nBased on this performance, "
    				+ "the business has achieved £"+df.format(profit)+"\nin profit.\n"
    				+"The "+ Integer.toString(projectsCount)+" projects required "+
    	    		df.format(workload)+" days of work.";
    	}
    	else if(tableTVProjectsPanel.getParent()==tablesPanel) {
    		summary += "TV";
    		if(!selectedLocation.equals("All")) {
    			summary += ",\nProject Location is: "+selectedLocation;
    		}
    		if(!selectedVenueSize.equals("All")) {
    			summary += "\nand Venue Size is: "+selectedVenueSize;
    		}
    		summary += "\n\n";
    		
    		int projectsCount = 0;
    		double projectCost = 0.0;
    		double projectValue = 0.0;
    		int hours = 0;
    		int days = 0;
    		
    		for (TVProject project : tvProjects) {
    			
    			if((selectedLocation.equals("All") || project.getProjectLocation(
	        			).equals(selectedLocation)) && (selectedVenueSize.equals(
	        					"All") || project.getSizeOfVenue().equals(
	        							selectedVenueSize))) {
    				projectsCount += 1;
    				projectCost += project.getProjectCost();
    				projectValue += project.getProjectPriceToCustomer();
    				if(project.getDurationUnit().equals("hours")) {
    					hours += project.getProjectDuration();
    				} else if (project.getDurationUnit().equals("days")) {
    					days += project.getProjectDuration();
    				}
    			}
    		}
    		
    		double profit = projectValue - projectCost;
    		double workload = days + (hours/24);
    		
    		summary += "Scotia Visual Productions has produced a total of "+ Integer.toString(
    				projectsCount)+" TV projects.\n"
    				+ "The cost of the projects has sum to £"+
    				df.format(projectCost)+" and\nhas generated a revenue of £"+
    				df.format(projectValue)+".\nBased on this performance, "
    				+ "the business has achieved £"+df.format(profit)+"\nin profit.\n"
    				+"The "+ Integer.toString(projectsCount)+" projects required "+
    	    		df.format(workload)+" days of work.";
    	}
    	summaryTextArea.setText(summary);
    }
    
    /**
     * Receives the current table that was filtered based on Project Type filter buttons
     * and make the table visible by adding to the main table panel, while removing previous ones
     * @param selectedTablePanel
     */
    private void setSelectedTablePanel(JPanel selectedTablePanel) {
        tablesPanel.removeAll(); // Remove all components from tablesPanel
        tablesPanel.add(selectedTablePanel, BorderLayout.CENTER); // Add selectedTablePanel to the center
        // Reset the panel
        tablesPanel.revalidate(); // Revalidate the layout to reflect changes
        tablesPanel.repaint(); // Repaint the panel
    }
    
    /**
     * Method creates a new project based on Project Type, using the relevant class
     * @param projectId
     * @param projectName
     * @param type
     * @param projectDateVal
     * @param location
     * @param projectCostVal
     * @param projectCostToCost
     * @param venueSize
     * @param projectDuration
     * @param durationUnit
     * @param optionalField
     */
    private void createNewProject(String projectId, String projectName, String type, Date projectDateVal,
    		String location, double projectCostVal, double projectCostToCost, String venueSize, 
    		int projectDuration,String durationUnit, String optionalField) {
    	
    	// Create the projects based on project type selected by user
    	if(type.equals("Film")) {
	    	FilmProject newProject = new FilmProject(projectId, projectName, projectDateVal, 
	    			location, projectCostVal, projectCostToCost, venueSize, projectDuration,
					durationUnit, optionalField);
	    	projects.add(newProject);
	    	filmProjects.add(newProject);
	    	tableFilmProjects = createTable("Update", "Film", locationFilterComboBox.getSelectedItem().toString(),
            		venueSizeFilterComboBox.getSelectedItem().toString());
	    	tableProjects = createTable("Update", "All", locationFilterComboBox.getSelectedItem().toString(),
            		venueSizeFilterComboBox.getSelectedItem().toString());
    }
    	else if(type.equals("Music")) {
	    	MusicProject newProject = new MusicProject(projectId, projectName, projectDateVal, 
	    			location, projectCostVal, projectCostToCost, venueSize, projectDuration,
					durationUnit, optionalField);
	    	projects.add(newProject);
	    	musicProjects.add(newProject);
	    	tableMusicProjects = createTable("Update", "Music", locationFilterComboBox.getSelectedItem().toString(),
            		venueSizeFilterComboBox.getSelectedItem().toString());
	    	tableProjects = createTable("Update", "All", locationFilterComboBox.getSelectedItem().toString(),
            		venueSizeFilterComboBox.getSelectedItem().toString());
    }
    	else if(type.equals("Theater")) {
	    	TheaterProject newProject = new TheaterProject(projectId, projectName, projectDateVal, 
	    			location, projectCostVal, projectCostToCost, venueSize, projectDuration,
					durationUnit, optionalField);
	    	projects.add(newProject);
	    	theaterProjects.add(newProject);
	    	tableTheaterProjects = createTable("Update", "Theater", locationFilterComboBox.getSelectedItem().toString(),
            		venueSizeFilterComboBox.getSelectedItem().toString());
	    	tableProjects = createTable("Update", "All", locationFilterComboBox.getSelectedItem().toString(),
            		venueSizeFilterComboBox.getSelectedItem().toString());
    }
    	else if(type.equals("TV")) {
	    	TVProject newProject = new TVProject(projectId, projectName, projectDateVal, 
	    			location, projectCostVal, projectCostToCost, venueSize, projectDuration,
					durationUnit, optionalField);
	    	projects.add(newProject);
	    	tvProjects.add(newProject);
	    	tableTVProjects = createTable("Update", "TV", locationFilterComboBox.getSelectedItem().toString(),
            		venueSizeFilterComboBox.getSelectedItem().toString());
	    	tableProjects = createTable("Update", "All", locationFilterComboBox.getSelectedItem().toString(),
            		venueSizeFilterComboBox.getSelectedItem().toString());
    }
}}