package in.daaconline.doomshell.rahul.quiz;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class QuizCategoryGET {

    @SerializedName("success")
    private int success;

    public int getSuccess() {
        return success;
    }

    @SerializedName("output")
    private ArrayList<Output> output;

    public ArrayList<Output> getOutput() {
        return output;
    }

    public class Output
    {
        @SerializedName("course_id")
        private String course_id;

        public String getCourse_id() {
            return course_id;
        }

        @SerializedName("course_name")
        private String course_name;

        public String getCourse_name() {
            return course_name;
        }

        @SerializedName("image")
        private String image;

        public String getImage() {
            return image;
        }
    }
}
