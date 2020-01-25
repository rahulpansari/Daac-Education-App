package in.daaconline.doomshell.rahul.himanshu;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AllJobs
{
    @SerializedName("success")

    private int Success;

    public int getSuccess() { return Success; }

    @SerializedName("output")
    private ArrayList<Models> JobList;

    public ArrayList<Models> getJobList()
    {
        return JobList;
    }

    public class Models
    {

        @SerializedName("job_id")
        private String jobid;

        public String getJobid()
        {
            return jobid;
        }
@SerializedName("status")
private String Status;

        public String getStatus() {
            return Status;
        }

        @SerializedName("company")
        private String CompanyName;

        @SerializedName("logo")
        private String Logo;

        @SerializedName("location")
        private String Location;

        @SerializedName("contactInfo")
        private String Contact;

        @SerializedName("vacancy")
        private String Vacancy;

        @SerializedName("timestamp")
        private String TimeStamp;

        @SerializedName("posting_date")
        private String PostDate;

        public String getPostDate() { return PostDate; }

        public String getTimeStamp() { return TimeStamp; }

        public String getVacancy() { return Vacancy; }

        public String getCompanyName() {
            return CompanyName;
        }

        public String getLocation() {
            return Location;
        }

        public String getContact() { return Contact; }

        public String getLogo(){ return Logo; }
    }
}
