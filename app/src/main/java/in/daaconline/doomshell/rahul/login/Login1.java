package in.daaconline.doomshell.rahul.login;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Login1 {

@SerializedName("success")
    int success;

    public int getSuccess() {
        return success;
    }



    @SerializedName("output")
    ArrayList<Output> output;

    public ArrayList<Output> getOutput() {
        return output;
    }

    public class Output {
        @SerializedName("mobile")
        String mobile;

        public String getMobile() {
            return mobile;
        }

        @SerializedName("username")
        String user_name;

        public String getUser_name() {
            return user_name;
        }

      @SerializedName("userid")
        public String userid;

        public String getuserid() {
            return userid;
        }
    }
    @SerializedName("mobile")
    String mobile;

    public String getMobile() {
        return mobile;
    }

    @SerializedName("username")
    String user_name;
    public String getUser_name() {
        return user_name;
    }

    @SerializedName("imeino")
    String imei;

    public String getImei() {
        return imei;
    }
    @SerializedName("id")
    String id;

    public String getId() {
        return id;
    }
}
