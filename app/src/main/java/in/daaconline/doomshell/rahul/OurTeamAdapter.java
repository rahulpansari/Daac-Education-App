package in.daaconline.doomshell.rahul;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.daac.R;



public class OurTeamAdapter extends RecyclerView.Adapter<OurTeamHolder> {
Context context;
    public OurTeamAdapter(Context c) {
        context=c;
    }

    @NonNull
    @Override
    public OurTeamHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.our_team_recycler,parent,false);
        return new OurTeamHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OurTeamHolder holder, int position) {
        Glide.with(context).asDrawable().load(OurTeam.output.get(position).getImage()).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                return false;
            }
        }).into(holder.image);

        holder.exp.setText(OurTeam.output.get(position).getExperience());
        holder.name.setText(OurTeam.output.get(position).getName());
        String area="";
        for(int i=0;i<OurTeam.output.get(position).getArea().size();i++) {
            if(i==OurTeam.output.get(position).getArea().size()-1)
            {area=area+"* "+OurTeam.output.get(position).getArea().get(i);

            }
            else
                area=area+"* "+OurTeam.output.get(position).getArea().get(i)+"\n";

        }
        holder.area.setText(area);
    }

    @Override
    public int getItemCount() {
        return OurTeam.output.size();
    }
}
