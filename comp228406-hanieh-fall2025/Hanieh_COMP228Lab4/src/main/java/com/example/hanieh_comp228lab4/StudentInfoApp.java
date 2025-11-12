package com.example.hanieh_comp228lab4;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class StudentInfoApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));


        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(8);

        TextField txtName = new TextField();
        TextField txtAddress = new TextField();
        TextField txtCity = new TextField();
        TextField txtProvince = new TextField();
        TextField txtPostal = new TextField();
        TextField txtPhone = new TextField();
        TextField txtEmail = new TextField();

        form.addRow(0, new Label("Full Name:"), txtName);
        form.addRow(1, new Label("Address:"), txtAddress);
        form.addRow(2, new Label("City:"), txtCity);
        form.addRow(3, new Label("Province:"), txtProvince);
        form.addRow(4, new Label("Postal Code:"), txtPostal);
        form.addRow(5, new Label("Phone:"), txtPhone);
        form.addRow(6, new Label("Email:"), txtEmail);

        root.setTop(form);


        VBox centerBox = new VBox(10);
        centerBox.setPadding(new Insets(10));

        ToggleGroup majorGroup = new ToggleGroup();
        RadioButton rbCS = new RadioButton("Computer Science");
        RadioButton rbBus = new RadioButton("Business");
        rbCS.setToggleGroup(majorGroup);
        rbBus.setToggleGroup(majorGroup);

        ComboBox<String> cmbCourses = new ComboBox<>();
        cmbCourses.setPromptText("Select a course");
        ListView<String> lstCourses = new ListView<>();

        rbCS.setOnAction(e -> {
            cmbCourses.getItems().setAll("Java Programming", "Data Structures", "Databases", "Operating Systems");
            lstCourses.getItems().clear();
        });

        rbBus.setOnAction(e -> {
            cmbCourses.getItems().setAll("Accounting", "Marketing", "Business Law", "Economics");
            lstCourses.getItems().clear();
        });

        cmbCourses.setOnAction(e -> {
            String course = cmbCourses.getValue();
            if (course != null && !lstCourses.getItems().contains(course)) {
                lstCourses.getItems().add(course);
            }
        });

        CheckBox chkSports = new CheckBox("Sports Club");
        CheckBox chkMusic = new CheckBox("Music Club");
        CheckBox chkVolunteer = new CheckBox("Volunteer Work");

        centerBox.getChildren().addAll(
                new Label("Major:"), rbCS, rbBus,
                cmbCourses,
                new Label("Selected Courses:"), lstCourses,
                new Label("Activities:"), chkSports, chkMusic, chkVolunteer
        );
        root.setCenter(centerBox);


        TextArea txtOutput = new TextArea();
        txtOutput.setEditable(false);
        txtOutput.setPrefHeight(150);

        Button btnDisplay = new Button("Display Student Info");
        btnDisplay.setOnAction(e -> {
            StringBuilder sb = new StringBuilder();
            sb.append("Full Name: ").append(txtName.getText()).append("\n");
            sb.append("Address: ").append(txtAddress.getText()).append("\n");
            sb.append("City: ").append(txtCity.getText()).append("\n");
            sb.append("Province: ").append(txtProvince.getText()).append("\n");
            sb.append("Postal Code: ").append(txtPostal.getText()).append("\n");
            sb.append("Phone: ").append(txtPhone.getText()).append("\n");
            sb.append("Email: ").append(txtEmail.getText()).append("\n");

            RadioButton selectedMajor = (RadioButton) majorGroup.getSelectedToggle();
            sb.append("Major: ").append(selectedMajor != null ? selectedMajor.getText() : "N/A").append("\n");
            sb.append("Courses: ").append(lstCourses.getItems().isEmpty() ? "None" : lstCourses.getItems()).append("\n");

            sb.append("Activities: ");
            boolean any = false;
            if (chkSports.isSelected()) { sb.append("Sports "); any = true; }
            if (chkMusic.isSelected()) { sb.append("Music "); any = true; }
            if (chkVolunteer.isSelected()) { sb.append("Volunteer "); any = true; }
            if (!any) sb.append("None");

            txtOutput.setText(sb.toString());
        });

        VBox bottom = new VBox(10, btnDisplay, txtOutput);
        bottom.setPadding(new Insets(10));
        root.setBottom(bottom);

        Scene scene = new Scene(root, 600, 700);
        primaryStage.setTitle("Student Information Form");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
