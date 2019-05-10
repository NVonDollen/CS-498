/*Nicolas Von Dollen
This is a program to project tattoo png's onto body parts - The application should serve an audience of people that want to get a tattoo.
The initial concept is to allow someone to pick from a photo library of models / body types that match up to theirs, then upload an image of a tattoo.
The application will run and project the image of the tattoo on the body so that those who desire to get a tattoo will have an idea of what the tattoo may look like
on their body prior to the tattoo appointment.
CS 498 - Capstone
Tattoo Preview Simulator
(C) 2019, SUNY Polytechnic Institute programmed by Nicolas Von Dollen
Last updated May 2019
Language: Java, JavaFX */

package tattoo;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.RenderedImage;
import java.io.File; //used to create a file object
import java.io.IOException; //used to catch exceptions dealing with IO
import java.awt.image.BufferedImage; //used to create image object that will hold the image
import javax.imageio.ImageIO; 	//used to perform the read/write operation
import javafx.scene.layout.AnchorPane;

public class Controller extends Button{
    //VARIABLES
    public int sceneWidth = 963;
    public int sceneHeight = 640;
    private Image userImage = null;
    //
    private Image fitArm = null;
    private Image fitBack = null;
    private Image fitLeg = null;
    private Image fitChest = null;
    //
    private Image skinnyArm = null;
    private Image skinnyBack = null;
    private Image skinnyLeg = null;
    private Image skinnyShoulder = null;
    //
    private Image heavyArm = null;
    private Image heavyBack = null;
    private Image heavyLeg = null;
    private Image heavyChest = null;
    //
    private Image beginImage = null;
    private Image backgroundImage = null;

    public String selectedBodyPart = "";
    public String selectedBodyType = "";
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private ObservableList<String> bodyTypeList = FXCollections.observableArrayList("Fit", "Skinny", "Heavy");
    private ObservableList<String> bodyPartList = FXCollections.observableArrayList("Back", "Arm", "Leg","Chest");
    //private ObservableList<String> fitBodyPartList = FXCollections.observableArrayList("Back", "Arm", "Leg", "Chest");
    //private ObservableList<String> heavyBodyPartList = FXCollections.observableArrayList("Back", "Arm", "Leg", "Chest");
    //private ObservableList<String> skinnyBodyPartList = FXCollections.observableArrayList("Back", "Arm", "Leg", "Shoulder");

    ////////ChoiceBoxes/////////////////////////////////
    @FXML
    private ChoiceBox bodyPartBox;
    @FXML
    private ChoiceBox bodyTypeBox;

    ////////ImageViews//////////////////////////////////
    @FXML
    private ImageView topLayer;
    @FXML
    private ImageView bottomLayer;
    //@FXML
    //private ImageView backgroundLayer;

    ////////AnchorPanes/////////////////////////////////
    @FXML
    private AnchorPane mainPane;

    ///////Buttons//////////////////////////////////////
    @FXML
    public Button rotNinety;

    /*Function to read / upload image*/
    public void readImage() {
        try {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Select Image");

                Stage stage = (Stage)mainPane.getScene().getWindow();

                File file = fileChooser.showOpenDialog(stage);

                if(file != null){
                    userImage = new Image(file.toURI().toString());
                }
            System.out.println("Reading complete.");
                topLayer.setImage(userImage);
        } catch (Exception e) {
            System.out.println("Error" + e);
        }
    }

    /*Function that runs automatically when the code is executed -- will contain ability to set items in ChoiceBox*/
    @FXML
    public void initialize(){
        beginImage = new Image("tattoo/beginImage.jpg");
        backgroundImage = new Image("tattoo/night.jpg");

        //FIT BODY PART IMAGES
        fitArm = new Image("tattoo/fitArm.jpg");
        fitBack = new Image("tattoo/fitBack.png");
        fitLeg = new Image("tattoo/fitLeg.jpg");
        fitChest = new Image("tattoo/fitChest.jpg");

        //SKINNY BODY PART IMAGES
        skinnyArm = new Image("tattoo/skinnyArm.jpg");
        skinnyBack = new Image("tattoo/skinnyBack.jpg");
        skinnyLeg = new Image("tattoo/fitLeg.jpg");
        skinnyShoulder = new Image("tattoo/skinnyShoulder.jpg");

        //HEAVY BODY PART IMAGES
        heavyChest = new Image("tattoo/heavyChest.jpg");
        heavyBack = new Image("tattoo/heavyBack.jpg");
        heavyArm = new Image("tattoo/heavyArm.jpg");
        heavyLeg = new Image("tattoo/fitLeg.jpg");

        ///Welcome Screen on the Right Hand Side///
        //backgroundLayer.setImage(backgroundImage);
        bottomLayer.setImage(beginImage);
        bottomLayer.setFitWidth(525);

        ////////////////////////////////////////BODY TYPE SECTION//////////////////////////////////////////

        bodyTypeBox.getSelectionModel().selectFirst();
        bodyTypeBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                selectedBodyType = "";
                System.out.println("***Body Type Selected: " + bodyTypeBox.getItems().get((int) newValue));
                selectedBodyType = (String) bodyTypeBox.getItems().get((int) newValue);
                //System.out.println(selectedBodyType);

                    ///////////////////////////CHANGE THE LIST TO WORK FROM DEPENDING ON THE BODY TYPE SELECTED////////////
                    if(selectedBodyType == "Fit"){
                        bodyPartBox.setItems(bodyPartList);
                        //System.out.println("We have selected fit and have entered this if statement");
                    }
                    if(selectedBodyType == "Skinny") {
                        bodyPartBox.setItems(bodyPartList);
                    }
                    if(selectedBodyType == "Heavy"){
                        bodyPartBox.setItems(bodyPartList);
                    }
            }
        });

        ///////////////////////////////////////BODY PART CHOICEBOX SECTION/////////////////////////////////
        /*Each section may begin with
        * topLayer.setImage(null)
        * in order to clear the image of the tattoo. Tod (tattoo artists in Utica) suggested this remain commented out
        * to allow the consumer to easily see their tattoo on multiple body parts.
        * */
        bodyTypeBox.setItems(bodyTypeList);
        bodyPartBox.setItems(bodyPartList);
        //bodyPartBox.getSelectionModel().selectFirst();
        bodyPartBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                //Button rotNinety = new Button();
                selectedBodyPart = "";
                System.out.println("Body Part Selected: " + bodyPartBox.getItems().get((int) newValue));
                selectedBodyPart = (String) bodyPartBox.getItems().get((int) newValue);
                //System.out.println(selectedBodyPart);

                //////////////////////////////FIT BODY TYPE SELECTED///////////////////////////////////////////////////
                //975 x 575 is the beginning window size//
                if (selectedBodyPart == "Arm" && selectedBodyType == "Fit") {
                    //topLayer.setImage(null);
                    bottomLayer.setImage(fitArm);
                    //ADJUST ARM IMAGE LENGTH TO LOOK BETTER//
                    //bottomLayer.setFitWidth(XXXXXXXXX);
                    topLayer.setFitHeight(255);                                                                                 //<--------- DO THIS WITH EVERY SECTION TO MAKE TATTOOS FIT PROPERLY
                    topLayer.setFitWidth(240);
                    topLayer.setRotate(270);
                    topLayer.setY(180);
                    topLayer.setX(20);

                } else if (selectedBodyPart == "Back" && selectedBodyType == "Fit") {
                    //topLayer.setImage(null);
                    bottomLayer.setImage(fitBack);
                    topLayer.setRotate(0);
                    topLayer.setFitWidth(160);
                    topLayer.setFitHeight(170);
                    topLayer.setY(260);
                    topLayer.setX(160);

                } else if (selectedBodyPart == "Leg" && selectedBodyType == "Fit") {
                    //topLayer.setImage(null);
                    bottomLayer.setImage(fitLeg);
                    topLayer.setRotate(0);
                    topLayer.setFitWidth(110);
                    topLayer.setFitHeight(250);
                    topLayer.setX(292);
                    topLayer.setY(180);

                } else if (selectedBodyPart == "Chest" && selectedBodyType == "Fit") {
                    bottomLayer.setImage(fitChest);
                    topLayer.setRotate(0);
                    topLayer.setX(160);
                    topLayer.setY(32);
                    topLayer.setFitHeight(160);
                    topLayer.setFitWidth(120);
                }

                ///////////////////////////////SKINNY BODY TYPE SELECTED///////////////////////////////////////////////
                if (selectedBodyPart == "Back" && selectedBodyType == "Skinny") {
                    //topLayer.setImage(null);
                    bottomLayer.setImage(skinnyBack);
                    topLayer.setRotate(0);
                    topLayer.setX(230);
                    topLayer.setY(195);
                    topLayer.setFitHeight(160);
                    topLayer.setFitWidth(120);

                } else if (selectedBodyPart == "Arm" && selectedBodyType == "Skinny") {
                    //topLayer.setImage(null);
                    bottomLayer.setImage(skinnyArm);
                    topLayer.setY(365);
                    topLayer.setX(217);
                    topLayer.setRotate(90);
                    topLayer.setFitHeight(165);
                    topLayer.setFitWidth(135);

                } else if (selectedBodyPart == "Chest" && selectedBodyType == "Skinny") {
                    //topLayer.setImage(null);
                    bottomLayer.setImage(skinnyShoulder);
                    topLayer.setRotate(0);
                    topLayer.setFitWidth(310);
                    topLayer.setFitHeight(315);
                    topLayer.setX(95);
                    topLayer.setY(230);

                } else if (selectedBodyPart == "Leg" && selectedBodyType == "Skinny") {
                    //topLayer.setImage(null);
                    bottomLayer.setImage(skinnyLeg);
                    topLayer.setRotate(0);
                    topLayer.setFitWidth(110);
                    topLayer.setFitHeight(250);
                    topLayer.setX(292);
                    topLayer.setY(180);
                }

                ///////////////////////////////HEAVY BODY TYPE SELECTED////////////////////////////////////////////////
                if (selectedBodyPart == "Back" && selectedBodyType == "Heavy") {
                    //topLayer.setImage(null);
                    bottomLayer.setImage(heavyBack);
                    topLayer.setRotate(0);
                    topLayer.setX(260);
                    topLayer.setY(138);
                    topLayer.setFitWidth(120);
                    topLayer.setFitHeight(135);

                } else if (selectedBodyPart == "Arm" && selectedBodyType == "Heavy") {
                    //topLayer.setImage(null);
                    bottomLayer.setImage(heavyArm);
                    topLayer.setRotate(0);
                    topLayer.setX(170);
                    topLayer.setY(160);
                    topLayer.setFitWidth(230);
                    topLayer.setFitHeight(275);

                } else if (selectedBodyPart == "Chest" && selectedBodyType == "Heavy") {
                    //topLayer.setImage(null);
                    bottomLayer.setImage(heavyChest);
                    topLayer.setRotate(0);
                    topLayer.setFitWidth(160);
                    topLayer.setFitHeight(230);
                    topLayer.setX(275);
                    topLayer.setY(255);

                } else if (selectedBodyPart == "Leg" && selectedBodyType == "Heavy") {
                    //topLayer.setImage(null);
                    bottomLayer.setImage(fitLeg);
                    topLayer.setRotate(0);
                    topLayer.setFitWidth(110);
                    topLayer.setFitHeight(250);
                    topLayer.setX(292);
                    topLayer.setY(180);
                }
            }
        });
    }
}


