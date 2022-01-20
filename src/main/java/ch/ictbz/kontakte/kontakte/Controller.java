package ch.ictbz.kontakte.kontakte;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

public class Controller {
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField phoneTextField;

    ContactList contactList = new ContactList();
    @FXML
    private TableView<Contact> tableContacts;

    @FXML
    public void initialise() {
        tableContacts.getColumns().clear();

        // 1. column "name"
        TableColumn tColName = new TableColumn("Name");
        tColName.setMinWidth(220);
        tColName.setCellValueFactory(new PropertyValueFactory<>("name"));

        // 2. column "EMail"
        TableColumn tColEmail = new TableColumn("EMail");
        tColEmail.setMinWidth(175);
        tColEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        // 3. column "Phone"
        TableColumn tColPhone = new TableColumn("Phone");
        tColPhone.setMinWidth(155);
        tColPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));

        tableContacts.getColumns().addAll(tColName, tColEmail, tColPhone);

        // set data source
        tableContacts.setItems(contactList.getContacts());
    }

    public void onButtonAdd() {
        if(nameTextField.getText().isEmpty()) return;

        // new contact instance
        Contact contact = new Contact();
        contact.setName(nameTextField.getText());
        contact.setEmail(emailTextField.getText());
        contact.setPhone(phoneTextField.getText());
        // add new contact to contact list
        contactList.getContacts().add(contact);
    }


    public void onButtonDeleteList() {
        contactList.clear();
    }

    public void onButtonImportCSV() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Kontakte importieren");
        // show "save at"-dialogue
        File file = fileChooser.showOpenDialog(null);
        // assign file path to a string variable
        String path = file.getPath();

        contactList.importCsv(path);
    }

    public void onButtonExportCSV() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Kontakte exportieren");
        // show "save at"-dialogue
        File file = fileChooser.showSaveDialog(null);
        // assign file path to a string variable
        String path = file.getPath();

        contactList.exportCsv(path);
    }
}