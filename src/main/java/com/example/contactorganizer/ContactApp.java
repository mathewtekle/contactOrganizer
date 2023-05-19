package com.example.contactorganizer;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class ContactApp extends Application {

    private TableView<Contact> tableView;

    TextField firstNameField = new TextField("Enter first name");
    TextField lastNameField = new TextField("Enter last name");
    TextField emailField = new TextField("Enter primary email");
    TextField secondaryEmailField = new TextField("Enter secondary email (optional) ");
    TextField phoneField = new TextField("Enter phone number");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Create the TableView and columns
        tableView = new TableView<>();
        TableColumn<Contact, String> firstNameColumn = new TableColumn<>("First Name");
        TableColumn<Contact, String> lastNameColumn = new TableColumn<>("Last Name");
        TableColumn<Contact, String> emailColumn = new TableColumn<>("Email");
        TableColumn<Contact, String> secondaryEmailColumn = new TableColumn<>("Secondary Email");
        TableColumn<Contact, String> phoneColumn = new TableColumn<>("Phone Number");

        // Set cell value factories to map columns to Contact properties
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        secondaryEmailColumn.setCellValueFactory(new PropertyValueFactory<>("secondaryEmail"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        firstNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        firstNameColumn.setOnEditCommit(event -> {
            Contact contact = event.getRowValue();
            contact.setFirstName(event.getNewValue());
        });

        lastNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNameColumn.setOnEditCommit(event -> {
            Contact contact = event.getRowValue();
            contact.setFirstName(event.getNewValue());
        });

        emailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        emailColumn.setOnEditCommit(event -> {
            Contact contact = event.getRowValue();
            contact.setEmail(event.getNewValue());
        });

        secondaryEmailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        secondaryEmailColumn.setOnEditCommit(event -> {
            Contact contact = event.getRowValue();
            contact.setEmail(event.getNewValue());
        });

        phoneColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        phoneColumn.setOnEditCommit(event -> {
            Contact contact = event.getRowValue();
            contact.setEmail(event.getNewValue());
        });

        // Add columns to the TableView
        tableView.getColumns().addAll(firstNameColumn, lastNameColumn, emailColumn, secondaryEmailColumn, phoneColumn);

        // Set sample data
        tableView.setItems(FXCollections.observableArrayList(
                new Contact("John", "Doe", "john.doe@example.com", "john.doe@umbc.edu","1234567890"),
                new Contact("Jane", "Smith", "jane.smith@example.com","jane.smith@umbc.edu","9876543210")
        ));

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> addContact());

        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> deleteContact());

        // Create the root layout
        VBox root = new VBox(tableView, firstNameField, lastNameField, emailField, secondaryEmailField, phoneField, addButton, deleteButton);
        tableView.setEditable(true);

        // Create the scene and set it on the stage
        Scene scene = new Scene(root, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Contact App");
        primaryStage.show();
    }

    private void addContact() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String secEmail = secondaryEmailField.getText();
        String phone = phoneField.getText();

        if (!firstName.isEmpty() && !lastName.isEmpty() && !email.isEmpty() && !phone.isEmpty() && !secEmail.isEmpty()) {
            tableView.getItems().add(new Contact(firstName, lastName, email, secEmail, phone));
            clearFields();
        }
//        else if (!firstName.isEmpty() && !lastName.isEmpty() && !email.isEmpty() && !phone.isEmpty() && !secEmail.isEmpty()) {
//            tableView.getItems().add(new Contact(firstName, lastName, email, phone));
//            clearFields();
//        }
    }

    private void clearFields() {
        firstNameField.clear();
        lastNameField.clear();
        emailField.clear();
        secondaryEmailField.clear();
        phoneField.clear();
    }

    private void deleteContact() {
        Contact selectedContact = tableView.getSelectionModel().getSelectedItem();
        if (selectedContact != null) {
            tableView.getItems().remove(selectedContact);
        }
    }

    public static class Contact {
        private String firstName;
        private String lastName;
        private String email;
        private String secondaryEmail;
        private String phoneNumber;

        public Contact(String firstName, String lastName, String email, String secondaryEmail, String phoneNumber) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.secondaryEmail = secondaryEmail;
            this.phoneNumber = phoneNumber;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String setEmail(String email){
            this.email = email;
            return email;
        }

        public String setFirstName(String firstName){
            this.firstName = firstName;
            return firstName;
        }

        public String getEmail() {
            return email;
        }

        public String getSecondaryEmail(){
            return secondaryEmail;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }
    }
}
