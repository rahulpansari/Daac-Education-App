package in.daaconline.doomshell.rahul;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.daac.R;

public class OurTeamHolder extends RecyclerView.ViewHolder {
ImageView image;
TextView name,exp,area;
    public OurTeamHolder(@NonNull View itemView) {
        super(itemView);
        image=itemView.findViewById(R.id.ourteamimg);
        name=itemView.findViewById(R.id.ourteamname);
        exp=itemView.findViewById(R.id.ourteamexp);
        area=itemView.findViewById(R.id.ourteamarea);


    }
}
