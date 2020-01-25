package in.daaconline.doomshell.rahul.courses;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CoursesGET {

    @SerializedName("success")
    public int success;

    public int getSuccess() {
        return success;
    }

    @SerializedName("body")
    public Body body;

    public Body getBody() {
        return body;
    }

    public class Body
    {   @SerializedName("Accounts")
        ArrayList<CourseDesc> accounts;

        public ArrayList<CourseDesc> getAccounts() {
            return accounts;
        }
        @SerializedName("Advance")
        ArrayList<CourseDesc> advance;

        public ArrayList<CourseDesc> getAdvance() {
            return advance;
        }
        @SerializedName("Degree Courses")
        ArrayList<CourseDesc> degree;

        public ArrayList<CourseDesc> getDegree() {
            return degree;
        }

        @SerializedName("Designing")
        ArrayList<CourseDesc> designing;

        public ArrayList<CourseDesc> getDesigning() {
            return designing;
        }

        @SerializedName("Mobile Development")
        ArrayList<CourseDesc> mobile;

        public ArrayList<CourseDesc> getMobile() {
            return mobile;
        }

        @SerializedName("Programming")
        ArrayList<CourseDesc> programming;

        public ArrayList<CourseDesc> getProgramming() {
            return programming;
        }

        @SerializedName("Security & Testing")
        ArrayList<CourseDesc> security;

        public ArrayList<CourseDesc> getSecurity() {
            return security;
        }

        public class CourseDesc
        {
            @SerializedName("cname")
                public String cname;

            public String getCname() {
                return cname;
            }

            @SerializedName("duration")
            public String duration;

            public String getDuration() {
                return duration;
            }

            @SerializedName("image")
            public String image;

            public String getImage() {
                return image;
            }

            @SerializedName("id")

            public String id;

            public String getId() {
                return id;
            }

            @SerializedName("description")
            public String desc;

            public String getDesc() {
                return desc;
            }
        }
    }
}
