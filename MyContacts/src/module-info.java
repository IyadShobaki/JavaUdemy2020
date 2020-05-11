module MyContacts {
    requires java.base;
    requires java.xml;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens sample.datamodel;
    opens sample;
}