This project presents the creation of a Graphical User Interface (GUI) using Java framework Swing for visualisation and filtering of raw data for the ScotiaVisualProductions dataset provided.
The project implements concepts of Object-Oriented Programming with Java and it was created for an 2nd Year University assignment.

The application follows the below UML Class Diagram, with a superclass entitled Project and 4 subclasses (FilmProject, MusicProject, TheaterProject and TVProject).
![T4Diagram1](https://github.com/user-attachments/assets/6e145cf6-de40-4f1d-b42a-103ba9b86d36)

A screenshot of the GUI can be observed below. The program will read a csv file and save each record as an instance of it's relevant class, then display the objects created in a dataframe format.
The interface provides a button to create a new project which, once clicked, displayes a pop-up tab with a forms to capture user input for the new object being created. 
The GUI also offers filtering options, with the option to filter by project type displayed on the left side of the screen, as well as the option to filter by location and project venue size displayed on the right side of the screen.
At the top of the interface, the tab Projects Data is the main tab displaying the dataset, while the tab Summary opens a pop-up tab displaying a text commentary summarising the information of the dataset as per filters selected.
![GUI Screenshot](https://github.com/user-attachments/assets/4535a2e8-cf0e-4b5e-9582-d5a47be824b6)

**Further development**
The current plans for further development of this application include:
- Addition of Clear Filters button option to interface.
- Addition of dashboard with data visualisation options in the Summary tab to support the text commentary.
- Update of New Project Record tab to add error prevention by specifying data types and making fields mandatory, as well as transforming data field into a datepicker with default date.
