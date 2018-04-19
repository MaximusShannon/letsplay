package controllers.dynamicviewcontrollers;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class UniquePostController {

    public Text username;
    public Text postTitle;
    public Text postDescription;
    public Text postTags;
    public Text postLanguage;
    public Text postCount;
    public Text commentCount;
    public Button viewPost;
    /*
    * Microphone required to language spoken image views to notify the user
    * which requirements are activated
    * */
    public ImageView mr_x ,mr_tick ;
    public ImageView cp_x, cp_tick;
    public ImageView comp_x, comp_tick;
    public ImageView acm_x, acm_tick;
    public ImageView acf_x, acf_tick;
    public ImageView lang_x, lang_tick;
    public Label isOnline_green;
    public Label isOnline_red;

}
