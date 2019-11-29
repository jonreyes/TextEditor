package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.Scanner;

public class Controller {

    private Stage stage;
    private FileChooser fileChooser = new FileChooser();

    @FXML
    private TextArea fileText;

    @FXML
    private Label wordCount;

    @FXML
    private Label vowelCount;

    @FXML
    protected void openFile(ActionEvent e){
        File file = fileChooser.showOpenDialog(this.stage);
        if(file!=null){
            try {
                Scanner in = new Scanner(file);
                StringBuilder text = new StringBuilder();
                while(in.hasNextLine()){
                    String line = in.nextLine()+"\n";
                    text.append(line);
                }
                fileText.setText(text.toString());
            }catch (FileNotFoundException fe){
                fe.printStackTrace();
            }
        }
    }

    @FXML
    protected void saveFile(ActionEvent e){
        File file = fileChooser.showSaveDialog(this.stage);
        if(file!=null){
            try {
                FileWriter fw = new FileWriter(file.getPath());
                fw.write(fileText.getText());
                fw.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

    @FXML
    protected void count(ActionEvent e){
        String[] split = fileText.getText().split("\\s");
        int c = 0;
        int v = 0;
        for(String s: split){
            if(!s.isEmpty())c++;
            for(char l: s.toCharArray()){
                for(char vowel: "AEIOUaeiou".toCharArray()){
                    if (l==vowel){
                        v++;
                    }
                }
            }
        }
        wordCount.setText(""+c);
        vowelCount.setText(""+v);
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

}
