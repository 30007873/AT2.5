package ToDo;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ToDo extends Application {
    private ListView<Task> sourceTasks = new ListView<Task>();
    private ListView<Task> targetTasks = new ListView<Task>();
    //Create Text Area
    private TextArea eventLogs = new TextArea(new String());
    private static final DataFormat TO_DO = new DataFormat("TO DO");

    @Override
    public void start(Stage stage) {
        // Create Labels
        Label sourceTaskListLabel = new Label("Available tasks: ");
        Label targetTaskListLabel = new Label("To Do: ");

        // Set Size of the TextFields
        sourceTasks.setPrefSize(400, 200);
        targetTasks.setPrefSize(400, 200);
        eventLogs.setPrefSize(810, 200);

        sourceTasks.getItems().addAll(this.findAllTasks());

        sourceTasks.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        targetTasks.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        //Create Grid Pane
        GridPane gridPane1 = new GridPane();
        gridPane1.setHgap(10);
        gridPane1.setVgap(10);

        //Add the labels and Field ti the Grid Pane
        gridPane1.addRow(1, sourceTaskListLabel, targetTaskListLabel);
        gridPane1.addRow(2, sourceTasks, targetTasks);
        gridPane1.addRow(3, eventLogs);

        //Set Grid Pane
        GridPane gridPane2 = new GridPane();
        gridPane2.setHgap(10);
        gridPane2.setVgap(10);
        gridPane2.addRow(1, eventLogs);

        //Adding Event Handler: Mouse Event Handler
        sourceTasks.setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                log("Action on available task: drag detected");
                onDrag(mouseEvent, sourceTasks);
            }
        });

        sourceTasks.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent dragEvent) {
                log("Action on available task: drag over");
                dragOver(dragEvent, sourceTasks);
            }
        });

        sourceTasks.setOnDragDropped(new EventHandler<DragEvent>() {
            public void handle(DragEvent dragEvent) {
                log("Action on available task: drag dropped");
                dragDropped(dragEvent, sourceTasks);
            }
        });

        sourceTasks.setOnDragDone(new EventHandler<DragEvent>() {
            public void handle(DragEvent dragEvent) {
                log("Action on available task: drag done");
                dragComplete(dragEvent, sourceTasks);
            }
        });

        targetTasks.setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                log("Action on todo: drag detected");
                onDrag(mouseEvent, targetTasks);
            }
        });

        targetTasks.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent dragEvent) {
                log("Action on todo: drag over");
                dragOver(dragEvent, targetTasks);
            }
        });

        targetTasks.setOnDragDropped(new EventHandler<DragEvent>() {
            public void handle(DragEvent dragEvent) {
                log("Action on todo: drag dropped");
                dragDropped(dragEvent, targetTasks);
            }
        });

        targetTasks.setOnDragDone(new EventHandler<DragEvent>() {
            public void handle(DragEvent dragEvent) {
                log("Action on todo: drag done");
                dragComplete(dragEvent, targetTasks);
            }
        });
        //Create V Box
        VBox vBox = new VBox();
        //Add Grid Panes to V Box
        vBox.getChildren().addAll(gridPane1, gridPane2);
        //Set V Box styles
        vBox.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: #878787;");
        //Create Scene
        Scene scene = new Scene(vBox);
        //Add scene to stage
        stage.setScene(scene);
        //Set Title
        stage.setTitle("Drag And Drop");
        //Stage Display
        stage.show();
    }
    //List tasks for Drag and Drop
    public static ObservableList<Task> findAllTasks() {
        ObservableList<Task> observableList = FXCollections.<Task>observableArrayList();
        Task task1 = new Task("Doctor's appointment");
        Task task2 = new Task("Purchase computer");
        Task task3 = new Task("Catch a flight");
        Task task4 = new Task("Attend interview");
        Task task5 = new Task("Finish assignment");
        Task task6 = new Task("Misc");
        observableList.addAll(task1, task2, task3, task4, task5, task6);
        return observableList;
    }

    private void onDrag(MouseEvent mouseEvent, ListView<Task> listView) {
        int count = listView.getSelectionModel().getSelectedIndices().size();
        if (count == 0) {
            mouseEvent.consume();
            return;
        }
        // Initiate a drag-and-drop gesture
        Dragboard dragboard = listView.startDragAndDrop(TransferMode.COPY_OR_MOVE);
        List<Task> selectedItems = this.getSelectedTasks(listView);
        ClipboardContent content = new ClipboardContent();
        content.put(TO_DO, selectedItems);
        dragboard.setContent(content);
        mouseEvent.consume();
    }

    private void dragOver(DragEvent dragevent, ListView<Task> listView) {
        Dragboard dragboard = dragevent.getDragboard();
        if (dragevent.getGestureSource() != listView && dragboard.hasContent(TO_DO)) {
            dragevent.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }
        dragevent.consume();
    }

    private void dragDropped(DragEvent dragEvent, ListView<Task> listView) {
        boolean status = false;
        // Transfer the data to the target
        Dragboard dragboard = dragEvent.getDragboard();
        if (dragboard.hasContent(TO_DO)) {
            List<Task> list = (ArrayList<Task>) dragboard.getContent(TO_DO);
            listView.getItems().addAll(list);
            status = true;
        }
        dragEvent.setDropCompleted(status);
        dragEvent.consume();
    }

    private void dragComplete(DragEvent dragEvent, ListView<Task> listView) {
        TransferMode transferMode = dragEvent.getTransferMode();
        if (transferMode == TransferMode.MOVE) {
            removeSelectedTasks(listView);
        }
        dragEvent.consume();
    }

    public static List<Task> getSelectedTasks(ListView<Task> listView) {
        List<Task> list = new ArrayList<Task>(listView.getSelectionModel().getSelectedItems());
        return list;
    }

    public static void removeSelectedTasks(ListView<Task> listView) {
        List<Task> selectedList = new ArrayList<>();
        for (Task Task : listView.getSelectionModel().getSelectedItems()) {
            selectedList.add(Task);
        }
        listView.getSelectionModel().clearSelection();
        listView.getItems().removeAll(selectedList);
    }

    private void log(String string) {
        this.eventLogs.appendText(string + "\n");
    }

    /**
     * MAIN APPLICATION LAUNCH AND HELP FILES
     * @param args
     */
    public static void main(String[] args) {

        //Help files
        String url = "HelpFile.html";
        //File object of the help file
        File htmlFile = new File(url);
        // load the help file in a default browser
        try {
        Desktop.getDesktop().browse(htmlFile.toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Run Application
        Application.launch(args);
    }
}