package in.daaconline.doomshell.rahul.himanshu;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class JobSliderGET {
    @SerializedName("success")
     private int success;

    public int getSuccess() {
        return success;
    }

    @SerializedName("body")
    public ArrayList<Body> body;

    public ArrayList<Body> getBody() {
        return body;
    }

    public class Body
    {
        @SerializedName("desc")
        public String desc;

        public String getDesc() {
            return desc;
        }

        @SerializedName("image")
        public String image;

        public String getImage() {
            return image;
        }
    }
}
