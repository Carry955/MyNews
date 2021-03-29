package com.example.mynews;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder> {
    private Context context;
    private List<News.Data> list;
    private View inflater;
    private OnItemClickListener listener;

    public RecycleAdapter(Context context, List<News.Data> list){
        this.context = context;
        this.list = list;
    }

    public void addNews(List<News.Data> list){
        this.list.addAll(list);
        notifyItemInserted(list.size());
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(context).inflate(R.layout.item_demo,  parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(inflater);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.titleView.setText(list.get(position).title);
        holder.authorView.setText(list.get(position).author_name);

        Glide.with(context)
                .load(list.get(position).thumbnail_pic_s)
                .into(holder.imageView);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("url", list.get(position).url);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView titleView;
        TextView authorView;
        ImageView imageView;
        LinearLayout linearLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titleView = (TextView)itemView.findViewById(R.id.tv_title);
            authorView = (TextView)itemView.findViewById(R.id.tv_author);
            imageView = (ImageView)itemView.findViewById(R.id.iv_img);
            linearLayout = (LinearLayout)itemView.findViewById(R.id.ll_box);
        }
    }
    public interface OnItemClickListener{
        void onClick();
    }
    void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
