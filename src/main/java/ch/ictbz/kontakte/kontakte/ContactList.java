package ch.ictbz.kontakte.kontakte;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.io.*;


public class ContactList {
    private ObservableList<Contact> contacts = FXCollections.observableArrayList();

    public void clear() {
        getContacts().clear();
    }
    public void exportCsv(String filePath) throws IOException {
        filePath += ".csv";
        String[][] contactsStringArray = new String[contacts.toArray().length][3];
        for (int i = 0; i < contacts.toArray().length; i++) {
            contactsStringArray[i][0] = contacts.get(i).getName();
            contactsStringArray[i][1] = contacts.get(i).getEmail();
            contactsStringArray[i][2] = contacts.get(i).getPhone();
        }

        File csvFile = new File(filePath);
        FileWriter fileWriter = new FileWriter(csvFile);
        for (String[] data : contactsStringArray) {
            StringBuilder line = new StringBuilder();
            for (int i = 0; i < data.length; i++) {
                line.append(data[i]);
                if(i != data.length - 1) {
                    line.append(',');
                }
            }
            line.append("\n");
            fileWriter.write(line.toString());
        }
        fileWriter.close();

    }


    public void importCsv(String filePath) throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader(filePath));
        String row = csvReader.readLine();
        while (row != null) {
            String[] data = row.split(",");

            Contact contact = new Contact();
            contact.setName(data[0]);
            contact.setEmail(data[1]);
            contact.setPhone(data[2]);
            getContacts().add(contact);

            row = csvReader.readLine();
        }
        csvReader.close();
    }
    public ObservableList<Contact> getContacts() {
        return contacts;
    }
}
