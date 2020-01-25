package in.daaconline.doomshell.rahul.login;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.provider.ContactsContract;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;

import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.daac.R;
import in.daaconline.doomshell.rahul.broadcast.MyBroadcast;
import in.daaconline.doomshell.rahul.mainscreen.MainActivity;

import in.daaconline.doomshell.rahul.mainscreen.Welcome;
import in.daaconline.doomshell.rahul.retrofit.RetrofitApi;
import in.daaconline.doomshell.rahul.retrofit.RetrofitMethod;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;


import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class Login extends AppCompatActivity {
    private EditText mobile,name;
    private Button b;
    int no=0;
    ProgressDialog progressDialog;
    static String myotp=null;
    MyBroadcast broadcast;
   static String imei=null;
JSONArray jsonArray=new JSONArray();
   final int REQUEST_CODE=101;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        name=findViewById(R.id.name);
        mobile = findViewById(R.id.mobile);
        b = findViewById(R.id.button);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Please Wait..");
        final String MobilePattern = "[0-9]{10}";



        mobile.addTextChangedListener(loginWatcher);

        Window window=getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.status_welcome));

        ImageView imageView = findViewById(R.id.imageView2);
        imageView.setImageResource(R.drawable.daac);



        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
if(name.getText().toString().trim().length()==0)
{
    name.setError("Name is not entered");
    name.requestFocus();
}
                if(mobile.getText().toString().trim().length()==0){
                    mobile.setError("Mobile No. is not entered");
                    mobile.requestFocus();
                }
                if(!mobile.getText().toString().matches(MobilePattern)) {
                    Toast.makeText(Login.this, "Enter valid 10 digit mobile number", Toast.LENGTH_SHORT).show();
                    mobile.requestFocus();
                }
                else if(mobile.getText().toString().matches(MobilePattern)&&name.getText().toString().trim().length()!=0&&mobile.getText().toString().trim().length()!=0){
ConfirmPermission();
                }

                }

        });




    }
    private TextWatcher loginWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
           // String user = name.getText().toString().trim();
            String mob = mobile.getText().toString().trim();
            b.setEnabled( !mob.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    public void ConfirmPermission()
    {if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
    {
        if(ContextCompat.checkSelfPermission(Login.this,Manifest.permission.READ_PHONE_STATE)== PackageManager.PERMISSION_GRANTED)
        {
                Data();
        }
        else if(ActivityCompat.shouldShowRequestPermissionRationale(Login.this,Manifest.permission.READ_PHONE_STATE))
        {
            AlertDialog.Builder alert=new AlertDialog.Builder(Login.this);
            alert.setTitle("Permission is Required to run this!");
            alert.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();

                    ActivityCompat.requestPermissions(Login.this,new String[]{Manifest.permission.READ_PHONE_STATE},REQUEST_CODE);
                }
            });
            alert.setNegativeButton("Ignore", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            alert.create().show();
        }
        else
        {
           ActivityCompat.requestPermissions(Login.this,new String[]{Manifest.permission.READ_PHONE_STATE,Manifest.permission.READ_CONTACTS},REQUEST_CODE);
        }
    }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {

            case REQUEST_CODE:
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(Login.this,Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {

                        Data();

                    }
                }
                break;
            case 147:
                Toast.makeText(getApplicationContext(),myotp,Toast.LENGTH_LONG).show();
                myotp="yes";
        }
    }
    public void Data()
    {
        progressDialog.show();
        progressDialog.setCancelable(false);
        TelephonyManager manager =(TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        try {
            imei = manager.getDeviceId();

        }
        catch (SecurityException e)
        {

        }
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {

                Retrofit retrofit= RetrofitMethod.RetrofitMethod(RetrofitApi.Login_url);
                RetrofitApi api = retrofit.create(RetrofitApi.class);
                Call<Login1> list = api.getloginget(name.getText().toString(), mobile.getText().toString(), imei);
                list.enqueue(new Callback<Login1>() {
                    @Override
                    public void onResponse(Call<Login1> call,final Response<Login1> response) {
                        no = response.body().getSuccess();


                                               if (no == 1) {

                            Welcome.u_id=response.body().output.get(0).getuserid();



                                                if(progressDialog.isShowing())
                                            {
                                                progressDialog.dismiss();
                                            }
                                                SharedPreferences pref=getApplicationContext().getSharedPreferences("My Application",MODE_PRIVATE);
                                                SharedPreferences.Editor edit=pref.edit();
                                                edit.putInt("login",121);
                                                edit.putString("userid", Welcome.u_id);
                                                edit.commit();
                                                finish();
                                                intent = new Intent(getApplicationContext(), MainActivity.class);
                                                startActivity(intent);}
                        else
                        {
                            /*if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) {
                                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECEIVE_SMS) == PackageManager.PERMISSION_GRANTED)
                                    myotp = "yes";
                                else
                                    requestPermissions(new String[]{Manifest.permission.RECEIVE_SMS},147);


                            }*/

                            if(progressDialog.isShowing())
                            {
                                progressDialog.dismiss();
                            }
                            finish();
                            intent=new Intent(getApplicationContext(), Otp.class);
                            intent.putExtra("id",response.body().getId());
                            intent.putExtra("user_name",response.body().getUser_name());
                            intent.putExtra("mobile",response.body().getMobile());
                            intent.putExtra("imei_no",response.body().getImei());
                            startActivity(intent);

                        }




                            }










                    @Override
                    public void onFailure(Call<Login1> call, Throwable t) {

                    }
                });

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        broadcast=new MyBroadcast();
        IntentFilter filter=new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(broadcast,filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcast);
    }
}
class MyNewContacts
{
   public String name,mobile;
    public MyNewContacts(String n,String m)
    {
        name=n;
        mobile=m;

    }
}

