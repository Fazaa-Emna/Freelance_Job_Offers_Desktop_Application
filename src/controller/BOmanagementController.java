package controller;

import entities.Event;
import entities.Hackathon;
import entities.Workshop;
import java.awt.Insets;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import services.EventService;
import services.HackathonService;
import services.WorkshopService;
import utils.DataBaseConnection;

public class BOmanagementController implements Initializable {

    @FXML
    private Button addHackathonButton;

    @FXML
    private Button addWorkshopButton;

    @FXML
    private Button deleteButton;
    @FXML
    private Button addWorkshop;
    
    @FXML
    private Button addHackathon;

    @FXML
    private TableColumn<Workshop, Integer> hackathonEvent_idColumn;

    @FXML
    private TableColumn<Hackathon, String> hackathonDescriptionColumn;

    @FXML
    private TableColumn<Hackathon, Date> hackathonEndDateColumn;

    @FXML
    private TableColumn<Hackathon, String> hackathonLocationColumn;

    @FXML
    private TableColumn<Hackathon, Integer> hackathonMaxAttendeesColumn;

    @FXML
    private TableColumn<Hackathon, String> hackathonNameColumn;

    @FXML
    private TableColumn<Hackathon, String> hackathonPrizesColumn;

    @FXML
    private TableColumn<Hackathon, Date> hackathonRegistrationDeadlineColumn;

    @FXML
    private TableColumn<Hackathon, Date> hackathonStartDateColumn;

    @FXML
    private TableColumn<Hackathon, Date> hackathonSubmissionDeadlineColumn;

    @FXML
    private TableView<Hackathon> hackathonsTable;

    @FXML
    private TableColumn<Workshop, String> workshopAgendaColumn;

    @FXML
    private TableColumn<Workshop, String> workshopDescriptionColumn;

    @FXML
    private TableColumn<Workshop, Date> workshopEndDateColumn;

    @FXML
    private TableColumn<Workshop, String> workshopLocationColumn;

    @FXML
    private TableColumn<Workshop, Integer> workshopMaxAttendeesColumn;

    @FXML
    private TableColumn<Workshop, Integer> workshopEvent_idColumn;

    @FXML
    private TableColumn<Workshop, String> workshopNameColumn;

    @FXML
    private TableColumn<Workshop, Date> workshopRegistrationDeadlineColumn;

    @FXML
    private TableColumn<Workshop, Date> workshopStartDateColumn;

    @FXML
    private TableView<Workshop> workshopsTable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        WorkshopService workshopService = new WorkshopService();
        HackathonService hackathonService = new HackathonService();
        ObservableList WorkshopList = workshopService.getAllWorkshops();
        ObservableList HackathonList = hackathonService.getAllHackathons();
        workshopsTable.setItems(WorkshopList);
        hackathonsTable.setItems(HackathonList);
        hackathonEvent_idColumn.setCellValueFactory(new PropertyValueFactory<>("event_id"));
        hackathonDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("event_name"));
        hackathonEndDateColumn.setCellValueFactory(new PropertyValueFactory<>("end_date"));
        hackathonLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        hackathonMaxAttendeesColumn.setCellValueFactory(new PropertyValueFactory<>("max_attendees"));
        hackathonNameColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        hackathonPrizesColumn.setCellValueFactory(new PropertyValueFactory<>("prizes"));
        hackathonRegistrationDeadlineColumn.setCellValueFactory(new PropertyValueFactory<>("registrationDeadline"));
        hackathonStartDateColumn.setCellValueFactory(new PropertyValueFactory<>("start_date"));
        hackathonSubmissionDeadlineColumn.setCellValueFactory(new PropertyValueFactory<>("submissionDeadline"));
        workshopEvent_idColumn.setCellValueFactory(new PropertyValueFactory<>("event_id"));
        workshopAgendaColumn.setCellValueFactory(new PropertyValueFactory<>("agenda"));
        workshopDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("event_name"));
        workshopEndDateColumn.setCellValueFactory(new PropertyValueFactory<>("end_date"));
        workshopLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        workshopMaxAttendeesColumn.setCellValueFactory(new PropertyValueFactory<>("max_attendees"));
        workshopNameColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        workshopRegistrationDeadlineColumn.setCellValueFactory(new PropertyValueFactory<>("registrationDeadline"));
        workshopStartDateColumn.setCellValueFactory(new PropertyValueFactory<>("start_date"));
    }

    public void Delete() {
        EventService eventService = new EventService();
        try {
            // Get the selected row from the table
            int selectedIndex = workshopsTable.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                // Extract the ID of the event from the selected row
                int eventId = workshopEvent_idColumn.getCellData(selectedIndex);
                // Call the deleteEvent() method with the ID of the event
                eventService.deleteEvent(eventId);
                // Remove the selected row from the table
                workshopsTable.getItems().remove(selectedIndex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BOmanagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
public void Deletee() {
        EventService eventService = new EventService();
        try {
            // Get the selected row from the table
            int selectedIndex = hackathonsTable.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                // Extract the ID of the event from the selected row
                int eventId = hackathonEvent_idColumn.getCellData(selectedIndex);
                // Call the deleteEvent() method with the ID of the event
                eventService.deleteEvent(eventId);
                // Remove the selected row from the table
                hackathonsTable.getItems().remove(selectedIndex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BOmanagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
void addWorkshop(ActionEvent event) throws IOException {
    // Load the FXML file of the UI you want to display
    Parent root = FXMLLoader.load(getClass().getResource("../view/Workshop.fxml"));

    // Create a new scene with the loaded FXML file as its root node
    Scene scene = new Scene(root);

    // Get the current stage (window) from the button's scene
    Stage stage = (Stage) addWorkshopButton.getScene().getWindow();

    // Set the new scene on the stage
    stage.setScene(scene);

    // Show the stage
    stage.show();
}
@FXML
void addHackathon(ActionEvent event) throws IOException {
    // Load the FXML file of the UI you want to display
    Parent root = FXMLLoader.load(getClass().getResource("../view/Hackathon.fxml"));

    // Create a new scene with the loaded FXML file as its root node
    Scene scene = new Scene(root);

    // Get the current stage (window) from the button's scene
    Stage stage = (Stage) addHackathonButton.getScene().getWindow();

    // Set the new scene on the stage
    stage.setScene(scene);

    // Show the stage
    stage.show();
}

public void Update() {
        WorkshopService workshopService = new WorkshopService();
        // Get the selected row from the table
        int selectedIndex = workshopsTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            // Extract the ID of the event from the selected row
            int eventId = workshopEvent_idColumn.getCellData(selectedIndex);
            // Call the deleteEvent() method with the ID of the event
            Workshop workshop;
            workshop=workshopService.getById(eventId);
            //this is a comment
        }
    }





}
