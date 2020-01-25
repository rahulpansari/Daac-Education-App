package in.daaconline.doomshell.rahul;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TeamGET {

    @SerializedName("success")
    private int success;

    public int getSuccess() {
        return success;
    }

    @SerializedName("output")
    public ArrayList<Output> output;

    public ArrayList<Output>getOutput() {
        return output;
    }

    public class Output
    {
        @SerializedName("name")
        private String name;

        public String getName() {
            return name;
        }

        @SerializedName("experience")
        private String experience;

        public String getExperience() {
            return experience;
        }

        @SerializedName("area")
        private ArrayList<String> area;


        public ArrayList<String> getArea() {
            return area;
        }

        @SerializedName("image")
        private String image;

        public String getImage() {
            return image;
        }
    }
}
