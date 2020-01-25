package in.daaconline.doomshell.rahul.login;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;

import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.daac.R;

import in.daaconline.doomshell.rahul.broadcast.MyBroadcast;
import in.daaconline.doomshell.rahul.mainscreen.MainActivity;
import in.daaconline.doomshell.rahul.mainscreen.Welcome;
import in.daaconline.doomshell.rahul.retrofit.RetrofitApi;
import in.daaconline.doomshell.rahul.retrofit.RetrofitMethod;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class Otp extends AppCompatActivity {
    String id,username,mobile,imeino;
    int no=0;
    Intent j;
    ImageView otp_img;
    TextView text;
    Cursor c;
    JSONArray jsonArray = new JSONArray();
    EditText edit;
    ProgressDialog progressDialog;
    Button submit;
 static JSONArray array;
    MyBroadcast broadcast;
    ArrayList<UserContacts> mycontacts=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        otp_img=findViewById(R.id.otp_image);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Please Wait..");
        otp_img.setImageResource(R.drawable.my_daac_sms);
text=findViewById(R.id.otp_verification_msg);


          edit=findViewById(R.id.otp_edittext);
        submit=findViewById(R.id.otp_submit);
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            List<String> no = new ArrayList<>();
             c = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME}, null, null, null);
        }Intent i=getIntent();
        text.setText("Verification Code Has Been Succesfully Sent To Mobile:"+i.getStringExtra("mobile"));

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Handler handler=new Handler();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Intent i=getIntent();
                        id=i.getStringExtra("id");
                        username=i.getStringExtra("user_name");
                        mobile=i.getStringExtra("mobile");
                        imeino=i.getStringExtra("imei_no");
                        progressDialog.show();
                        progressDialog.setCancelable(false);
                        Retrofit retrofit= RetrofitMethod.RetrofitMethod(RetrofitApi.Login_url);
                        RetrofitApi api = retrofit.create(RetrofitApi.class);
                        Call<Login2> list = api.putin(id,username,mobile,edit.getText().toString(),imeino);
                        list.enqueue(new Callback<Login2>() {
                            @Override
                            public void onResponse(Call<Login2> call, final Response<Login2> response) {
                                no=response.body().getSuccess();
                                String userid=response.body().getOutput().get(0).getuserid();
                                if(no==1)
                                {
                                    //ProgressClass.showProgress(getApplicationContext(),"Loading...");
                                    if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {

                                        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
                                        {
                                        c.moveToFirst();

                                        int i=0;
                                        while(c.moveToNext())
                                        {mycontacts.add(new UserContacts(c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)),c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))));


                                            i++;
                                        }
                                        c.close();

for(int j=0;j<mycontacts.size();j++)
{
    JSONObject object=new JSONObject();
    try {
        object.put("name", mycontacts.get(j).name);
        object.put("mobile", mycontacts.get(j).number);
        jsonArray.put(object);
    }
    catch (Exception e)
    {Log.e("errr",e.getMessage());}

}




                                        //Toast.makeText(getApplicationContext(),no.get(0)+"",Toast.LENGTH_LONG).show();
                                        Retrofit retrofit = RetrofitMethod.RetrofitMethod(RetrofitApi.Login_url);
                                        RetrofitApi api = retrofit.create(RetrofitApi.class);
                                        Observable<ContactList> getSlider=api.sendcontact(userid,jsonArray);
                                        getSlider.subscribeOn(Schedulers.io())
                                                .observeOn(AndroidSchedulers.mainThread())
                                                .subscribe(new Observer<ContactList>() {
                                                    @Override
                                                    public void onSubscribe(Disposable d) {

                                                    }

                                                    @Override
                                                    public void onNext(ContactList slideList) {

                                                    }

                                                    @Override
                                                    public void onError(Throwable e) {

                                                    }

                                                    @Override
                                                    public void onComplete() {


                                                        Welcome.u_id=response.body().getOutput().get(0).getuserid();
                                                        finish();
                                                        SharedPreferences pref=getApplicationContext().getSharedPreferences("My Application",MODE_PRIVATE);
                                                        SharedPreferences.Editor edit=pref.edit();
                                                        edit.putInt("login",121);
                                                        edit.putString("userid",Welcome.u_id);
                                                        edit.commit();

                                                        if(progressDialog.isShowing())
                                                        {
                                                            progressDialog.dismiss();
                                                        }

                                                        j=new Intent(getApplicationContext(), MainActivity.class);
                                                        startActivity(j);
                                                    }
                                                });

                                    }}
                                    else
                                    {
                                        if(progressDialog.isShowing())
                                        {
                                            progressDialog.dismiss();
                                        }

                                        Welcome.u_id=response.body().getOutput().get(0).getuserid();
                                        finish();
                                        SharedPreferences pref=getApplicationContext().getSharedPreferences("My Application",MODE_PRIVATE);
                                        SharedPreferences.Editor edit=pref.edit();
                                        edit.putInt("login",121);
                                        edit.putString("userid",Welcome.u_id);
                                        edit.commit();

                                        j=new Intent(getApplicationContext(), MainActivity.class);
                                        startActivity(j);
                                    }

                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(),"Invalid OTP",Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Login2> call, Throwable t) {
Toast.makeText(getApplicationContext(),"Something Went Wrong Please Try Later",Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
       /* SmsReciever.bindListener(new OtpListener() {
            @Override
            public void onOtpRecieved(String otp) {

                edit.setText(otp);
            }
        });*/
        broadcast=new MyBroadcast();
        IntentFilter filter=new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(broadcast,filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
//SmsReciever.unbindListener();
        unregisterReceiver(broadcast);

    }
}
