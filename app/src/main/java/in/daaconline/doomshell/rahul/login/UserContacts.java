package in.daaconline.doomshell.rahul.login;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class UserContacts {
    public String name;
    public String number;
    public UserContacts(String name,String phone)
    {
        this.name=name;
        this.number=phone;
    }
    public JSONObject getJSONObject() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("name", name);
            obj.put("mobile", number);
System.gc();
        } catch (JSONException e) {
            Log.e("json","Exception");
        }
        return obj;
    }

}
