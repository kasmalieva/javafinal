package com;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;


public class Controller {


    @FXML
    TextField idCreate;
    @FXML
    TextField usernameCreate;
    @FXML
    TextField passwordCreate;
    @FXML
    TextField ageCreate;
    @FXML
    TextField usernameLogin;
    @FXML
    TextField passwordLogin;
    @FXML
    Label userLabel;
    @FXML
    TextField newNoteField;
    @FXML
    Label newNoteLabel = new Label("newSuccessLabel");
    @FXML
    Label newSuccessLabel;
    @FXML
    Label loginLabel;
    private String userId;

    public void buttonCreate(ActionEvent actionEvent){
        User user = new User();
        user.setId(idCreate.getText());
        user.setName(usernameCreate.getText());
        user.setPassword(passwordCreate.getText());
        user.setAge(ageCreate.getText());
        UserDao userDao = new UserDao();
        User createdUser = userDao.createUser(user);
        if(createdUser!=null){
            System.out.println(createdUser);
        }
        else{
            System.out.println("Creation of a user failed!");
        }
        idCreate.setText("");
        usernameCreate.setText("");
        passwordCreate.setText("");
        ageCreate.setText("");

    }
    public void buttonLogin (ActionEvent actionEvent){
        System.out.println("starting to search user");
        Boolean isLogined = false;
        UserDao userDao = new UserDao();
        for(User user: userDao.getUsers()){
            if(user.getName().equals(usernameLogin.getText()) && user.getPassword().equals(passwordLogin.getText())){
                isLogined = true;
                try{
                    showSuccessWindow(user);
                }catch (Exception ex){
                    System.out.println("Login failed: ERROR: "+ ex.getMessage());
                }
            }
            userId = user.getId();
        }
        if(isLogined){
            System.out.println("Login success1");
        }
        else{
            System.out.println("Login failed!");
            loginLabel.setText("Login failed! Try again!");
        }

    }
    private void showSuccessWindow(User user) throws Exception{
        setUser(user.getId());
        NoteDao noteDao = new NoteDao();
        Parent root = FXMLLoader.load(getClass().getResource("UserInfo.fxml"));
        Stage stage = new Stage();
        stage.setTitle("User " + user.getName() + " age : " + user.getAge());
        stage.setScene(new Scene(root, 600, 500));
        stage.initModality(Modality.APPLICATION_MODAL);
        Note currentNote = null;
        List<Note> notes = noteDao.getNotes();
        for(int i = notes.size()-1; i>=0; i--){
            if(notes.get(i).getUserId().equals(user.getId())){
                currentNote = notes.get(i);
            }
        }
        if(currentNote == null){
            currentNote = new Note("Your note", user.getId());
            noteDao.createNote(currentNote);
        }
        newNoteLabel.setText(currentNote.getText());
        stage.show();


    }

    private void setUser(String user) {
        userId = user;
    }

    public void changeNote(){
        Note note = new Note(newNoteField.getText(), userId);
        newNoteLabel.setText(newNoteField.getText());
        NoteDao noteDao = new NoteDao();
        noteDao.createNote(note);
    }

}
