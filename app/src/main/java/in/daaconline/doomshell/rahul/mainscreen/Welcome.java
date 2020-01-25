package in.daaconline.doomshell.rahul.mainscreen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.daac.R;
import in.daaconline.doomshell.rahul.login.Login;

import java.util.ArrayList;

public class Welcome extends AppCompatActivity {
public static String u_id=null;
public static ArrayList<SliderGET.Body> slider=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
       window.setStatusBarColor(getResources().getColor(R.color.status_welcome));
        setContentView(R.layout.activity_welcome);
       // checkInternet();
        ImageView i = findViewById(R.id.imageView);
        i.setImageResource(R.drawable.daac);
        Animation a = AnimationUtils.loadAnimation(this, R.anim.splash);
        i.setAnimation(a);





        a.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                finish();
                SharedPreferences pref = getSharedPreferences("My Application", MODE_PRIVATE);
                u_id=pref.getString("userid",null);
                if (pref.getInt("login", -121) == -121)
                    startActivity(new Intent(getApplicationContext(), Login.class));
                else
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
       /* public final void checkInternet () {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(getBaseContext().CONNECTIVITY_SERVICE);
            if (connectivityManager.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTED ||
                    connectivityManager.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                    connectivityManager.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                    connectivityManager.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED) {

            } else if (connectivityManager.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                    connectivityManager.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED) {
                finish();
                startActivity(new Intent(getApplicationContext(), NoInternet.class));


            }


        }*/

}
