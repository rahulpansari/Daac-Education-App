package in.daaconline.doomshell.rahul.courses;

import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.daac.R;


import static in.daaconline.doomshell.rahul.courses.CourseActivity.advance;


public class CourseAdapterAdvance extends RecyclerView.Adapter<CourseHolderAdvance> {
    Context c;

    public CourseAdapterAdvance(Context context)
    {
        c=context;

    }
    @NonNull
    @Override
    public CourseHolderAdvance onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(c).inflate(R.layout.activity_advance,null);
        CourseHolderAdvance holder1=new CourseHolderAdvance(v,c);
        return holder1;
    }

    @Override
    public void onBindViewHolder(final CourseHolderAdvance courseActivityHolder, int i) {
        CardView cardView=courseActivityHolder.cardView;
        TextView textView=courseActivityHolder.textView1;
        if(i>0)
        {
            cardView.setVisibility(View.GONE);
            textView.setVisibility(View.GONE);

        }
        else
        {
            cardView.setVisibility(View.VISIBLE);
            textView.setVisibility(View.VISIBLE);
        }
        Glide.with(c).asDrawable().load(advance.get(i).getImage()).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                courseActivityHolder.progressBar.setVisibility(View.GONE);
                return false;
            }
        }).into(courseActivityHolder.imageView);

       // courseActivityHolder.textView2.setText(advance.get(i).cname);
    }

    @Override
    public int getItemCount() {
        return advance.size();
    }
}

