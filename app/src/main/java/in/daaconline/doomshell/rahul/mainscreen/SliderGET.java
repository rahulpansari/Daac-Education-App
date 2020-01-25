package in.daaconline.doomshell.rahul.mainscreen;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SliderGET {

@SerializedName("body")
ArrayList<Body> body;

    public ArrayList<Body> getBody() {
        return body;
    }

    public class Body
    {   @SerializedName("desc")
        private String desc;
        @SerializedName("image")
        private String image;

        public String getDesc() {
            return desc;
        }

        public String getImage() {
            return image;
        }
    }
}
