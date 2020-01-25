package in.daaconline.doomshell.rahul.contactus;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ContactGET {
    @SerializedName("success")
    private int success;

    public int getSuccess() {
        return success;
    }

    @SerializedName("branches")
    private ArrayList<Contactus> branches;

    public ArrayList<Contactus> getBranches() {
        return branches;
    }

    public class Contactus
    {
        @SerializedName("name")
        private String name;

        public String getName() {
            return name;
        }

        @SerializedName("address")

        public String address;

        public String getAddress() {
            return address;
        }

        @SerializedName("phone")

        public String phone;

        public String getPhone() {
            return phone;
        }

        @SerializedName("Lat")
        private String Lat;

        public String getLat() {
            return Lat;
        }

        @SerializedName("Long")
        public String Long;

        public String getLong() {
            return Long;
        }
    }
}
