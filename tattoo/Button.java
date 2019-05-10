package tattoo;

import javafx.scene.image.ImageView;
public class Button {
    public void rotateNinety(ImageView topLayer) {
        try {
            int rotation = 0;
            topLayer.setRotate(rotation + 90);
            rotation += 90;
            if(rotation == 360)
            {
                rotation = 0;
            }
        } catch (Exception e) {
            System.out.println("Error" +e);
        }
    }
}
