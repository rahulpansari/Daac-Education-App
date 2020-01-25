package in.daaconline.doomshell.rahul.videos;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.daac.R;

public class YoutubeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView textView;
    Context c;
    public YoutubeHolder(@NonNull View itemView,Context context) {
        super(itemView);
        c=context;
        textView=itemView.findViewById(R.id.quiz_layout_category_text);
        itemView.setOnClickListener(this);
    }
    public void onClick(View v)
    {
        Intent i=new Intent(c,Youtube.class);
        i.putExtra("playlist", YoutubeCategory.playlists.get(getAdapterPosition()).getPlaylistid());
        c.startActivity(i);
    }
}
