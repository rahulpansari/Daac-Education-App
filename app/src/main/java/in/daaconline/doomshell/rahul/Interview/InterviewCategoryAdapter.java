package in.daaconline.doomshell.rahul.Interview;

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


public class InterviewCategoryAdapter extends RecyclerView.Adapter<InterviewCategoryHolder> {
    Context c;
    public InterviewCategoryAdapter(Context context)
    {
        c=context;
    }
    @NonNull
    @Override
    public InterviewCategoryHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(c).inflate(R.layout.grid_onlyimage,viewGroup,false);
        return new InterviewCategoryHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull final InterviewCategoryHolder interviewCategoryHolder, int i) {
        //interviewCategoryHolder.textView.setText(output1.get(i).getCourse_name());
        Glide.with(c).asDrawable()
                .load(InterviewCategoryFragment.output1.get(i).getImage())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        interviewCategoryHolder.imageView.setImageResource(R.drawable.loader);
                        interviewCategoryHolder.imageView.setVisibility(View.VISIBLE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        interviewCategoryHolder.imageView.setVisibility(View.VISIBLE);
                        return false;
                    }
                }).into(interviewCategoryHolder.imageView);

    }

    @Override
    public int getItemCount() {
        return InterviewCategoryFragment.output1.size();
    }
}