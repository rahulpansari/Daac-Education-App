package in.daaconline.doomshell.rahul.quiz;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.daac.R;


public class QuizCategoryAdapter extends RecyclerView.Adapter<QuizCategoryHolder> {
Context c;
    public QuizCategoryAdapter(Context context)
{
    c=context;
}
    @NonNull
    @Override
    public QuizCategoryHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(c).inflate(R.layout.grid_onlyimage,viewGroup,false);
        return new QuizCategoryHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull final QuizCategoryHolder quizCategoryHolder, int i) {

//quizCategoryHolder.textView.setText(output.get(i).getCourse_name());

        Glide.with(c).asDrawable()
                .load(QuizCategoryFragment.output.get(i).getImage())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        quizCategoryHolder.imageView.setImageResource(R.drawable.loader);
                        quizCategoryHolder.imageView.setVisibility(View.VISIBLE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        quizCategoryHolder.imageView.setVisibility(View.VISIBLE);
                        return false;
                    }
                }).into(quizCategoryHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return QuizCategoryFragment.output.size();
    }
}
