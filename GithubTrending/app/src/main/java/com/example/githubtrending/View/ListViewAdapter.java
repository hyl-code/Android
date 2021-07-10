package com.example.githubtrending.View;

import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.service.autofill.DateValueSanitizer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.ActivityChooserView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.githubtrending.Bean;
import com.example.githubtrending.R;
import com.facebook.drawee.view.SimpleDraweeView;

import org.w3c.dom.Text;

import java.util.List;
import java.util.SimpleTimeZone;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.InnerHolder>{

    private List<Bean.Items> mData;
    private OnItemClickListener mOnItemClickListener;
    private int Opened = -1;

    public ListViewAdapter(List<Bean.Items> items){
        this.mData = items;
    }

    public void setList(List<Bean.Items> itemBeans){
        this.mData = itemBeans;
        notifyDataSetChanged();
    }

    @Override
    public InnerHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = (View) layoutInflater.inflate(R.layout.list_item, parent, false);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        if(mData != null){
            holder.onBindView(mData.get(position),position);
        }
    }

    public class InnerHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private SimpleDraweeView avatar;
        private ImageView mColor;
        private TextView fork;
        private TextView star;
        private TextView language;
        private TextView repo;
        public LinearLayout ChildLayout;
        public LinearLayout ParentLayout;
        private TextView Username;
        private TextView desc;
        private ImageView color;
        private Uri uri;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);

            avatar = itemView.findViewById(R.id.avatar);
            Username = itemView.findViewById(R.id.username);
            desc = itemView.findViewById(R.id.desc);
            language = itemView.findViewById(R.id.language_textview);
            repo = itemView.findViewById(R.id.reponame);
            star = itemView.findViewById(R.id.star_textview);
            fork = itemView.findViewById(R.id.fork_textview);
            mColor = itemView.findViewById(R.id.language);

            ParentLayout = itemView.findViewById(R.id.ParentLayout);
            ChildLayout = itemView.findViewById(R.id.invisible);
            ParentLayout.setOnClickListener(this);
        }

        public void onBindView(Bean.Items items, int position){
            List<String> str = items.getAvatars();
            Uri url = Uri.parse(str.get(0));
            avatar.setImageURI(url);
            Username.setText(items.getRepo());
            repo.setText(items.getRepo_link());
            star.setText(String.valueOf(items.getStar()));
            fork.setText(String.valueOf(items.getFork()));

            if(items.getDesc() != null){
                desc.setText(items.getDesc());
            }
            if(position == Opened){
                ChildLayout.setVisibility(VISIBLE);
            }else{
                ChildLayout.setVisibility(GONE);
            }
        }

        public void onClick(View v){
            if(Opened == getAdapterPosition()){
                Opened = -1;
                notifyItemChanged(getAdapterPosition());
            }else{
                int temp = 0;
                Opened = getAdapterPosition();
                notifyItemChanged(temp);
                notifyItemChanged(Opened);
            }
        }
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mOnItemClickListener = listener;
    }

    @Override
    public int getItemCount(){
        return mData.size();
    }
}
