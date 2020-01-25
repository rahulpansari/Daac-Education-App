package in.daaconline.doomshell.rahul.courses;

import com.google.gson.annotations.SerializedName;

public class CourseDetailGET {
    @SerializedName("success")
    int success;

    public int getSuccess() {
        return success;
    }

    @SerializedName("message")
    String message;

    public String getMessage() {
        return message;
    }
}
