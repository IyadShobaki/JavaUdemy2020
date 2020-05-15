package sample;


import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;


public class Controller {
//    private Task<ObservableList<String>> task;

    @FXML
    private ListView listView;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Label progressLabel;

    private Service<ObservableList<String>> service;

    public void initialize() {


        //we moved the following code to EmployeeService class
//        task = new Task<ObservableList<String>>() {
//            @Override
//            protected ObservableList<String> call() throws Exception {
//
//                String[] names = {"Iyad Shobaki",
//                        "Bill Rogers",
//                        "Jack Jill",
//                        "Joan Andrews",
//                        "Mary Johnson",
//                        "Bob McDonald"};
//
//                ObservableList<String> employees = FXCollections.observableArrayList();
//
//                for(int i=0; i<6; i++){
//                    employees.add(names[i]);
//                    updateMessage("Added " + names[i] +" to the list");
//                    updateProgress(i + 1, 6);
//                    Thread.sleep(200);
//                }
//                return employees;
//            }
//        };
//        progressBar.progressProperty().bind(task.progressProperty());
//        progressLabel.textProperty().bind(task.messageProperty());
//        listView.itemsProperty().bind(task.valueProperty());


        service = new EmployeeService();
        progressBar.progressProperty().bind(service.progressProperty());
        progressLabel.textProperty().bind(service.messageProperty());
        listView.itemsProperty().bind(service.valueProperty());

//        service.setOnRunning(new EventHandler<WorkerStateEvent>() {
//            @Override
//            public void handle(WorkerStateEvent workerStateEvent) {
//                progressBar.setVisible(true);
//                progressLabel.setVisible(true);
//            }
//        });
//
//        service.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
//            @Override
//            public void handle(WorkerStateEvent workerStateEvent) {
//                progressBar.setVisible(false);
//                progressLabel.setVisible(false);
//            }
//        });
//
//        progressBar.setVisible(false);
//        progressLabel.setVisible(false);

        progressBar.visibleProperty().bind(service.runningProperty());
        progressLabel.visibleProperty().bind(service.runningProperty());

    }

    @FXML
    public void buttonPressed() {
//        new Thread(task).start();
        if(service.getState() == Service.State.SUCCEEDED){
            service.reset();
            service.start();
        }else if(service.getState() == Service.State.READY){
            service.start();
        }

    }

}


//import javafx.application.Platform;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.concurrent.Task;
//import javafx.fxml.FXML;
//import javafx.scene.control.ListView;
//
//
//public class Controller {
//    private Task<ObservableList<String>> task;
//
//    @FXML
//    private ListView listView;
//
//    public void initialize() {
//        task = new Task<ObservableList<String>>() {
//            @Override
//            protected ObservableList<String> call() throws Exception {
//                Thread.sleep(1000); //stimulating the database fetch
//
//                final ObservableList<String> employees = FXCollections.observableArrayList(
//                        "Iyad Shobaki",
//                        "Bill Rogers",
//                        "Jack Jill",
//                        "Joan Andrews",
//                        "Mary Johnson",
//                        "Bob McDonald");
//
////                Platform.runLater(new Runnable() {
////                    @Override
////                    public void run() {
////                        listView.setItems(employees);
////                    }
////                });
//                return employees;
//            }
//        };
//        //instead of using Platform.runLater() is better to use data binding
//        listView.itemsProperty().bind(task.valueProperty());
//
//    }
//
//    @FXML
//    public void buttonPressed() {
//        new Thread(task).start();
//    }
//
//}
