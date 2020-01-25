package in.daaconline.doomshell.rahul.Interview;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class InterviewQuestionGET {
    @SerializedName("success")
    private int success;
    public int getSuccess() {
        return success;
    }

    @SerializedName("output")
    private ArrayList<Model> output;

    public ArrayList<Model> getOutput() {
        return output;
    }

    public class Model{
        @SerializedName("ques")
        private String question;

        public String getQuestion() {
            return question;
        }

        @SerializedName("op1")
        private String option1;

        public String getOption1() {
            return option1;
        }
        @SerializedName("op2")
        private String option2;

        public String getOption2() {
            return option2;
        }
        @SerializedName("op3")
        private String option3;

        public String getOption3() {
            return option3;
        }
        @SerializedName("op4")
        private String option4;

        public String getOption4() {
            return option4;
        }


        @SerializedName("ans")
        private String answer;

        public String getAnswer() {
            return answer;
        }
    }
}
