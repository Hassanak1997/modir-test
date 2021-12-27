package com.example.modir;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RestAdapter extends RecyclerView.Adapter<RestAdapter.ViewHolder> {

    private Context context;
    private List<Rest> items;
    private SetConfirm setConfirm;

    public void setItems(List<Rest> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public RestAdapter(Context context,SetConfirm setConfirm) {
        this.context = context;
        items = new ArrayList<>();
        this.setConfirm = setConfirm;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.requests_card_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RestAdapter.ViewHolder holder, int position) {
        holder.Bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,datecreated,daterest,explain,txt_status;
        RelativeLayout accept,deny;
        LinearLayout ly_controller;
        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_request);
            datecreated = itemView.findViewById(R.id.datecreated);
            daterest = itemView.findViewById(R.id.daterest);
            explain = itemView.findViewById(R.id.request_text);
            accept = itemView.findViewById(R.id.txt_accept);
            deny = itemView.findViewById(R.id.txt_deny);
            ly_controller = itemView.findViewById(R.id.linear1);
            txt_status = itemView.findViewById(R.id.txt_status);


        }


        public void Bind(Rest item){
            name.setText(item.getUser().getName());
            datecreated.setText(item.getRequest_time());
            daterest.setText(item.getRest_time());
            explain.setText(item.getDescription());
            if (!item.getStatus().equals("1")){
                ly_controller.setVisibility(View.GONE);
            }else {
                if (item.getStatus().equals("2")){
                    txt_status.setVisibility(View.VISIBLE);
                    txt_status.setText("تایید شده");
                }
                if (item.getStatus().equals("3")){
                    txt_status.setVisibility(View.VISIBLE);
                    txt_status.setText("رد شده");
                }
            }
            accept.setOnClickListener(v -> {
                setConfirm.setAccept(item.getId());
            });

            deny.setOnClickListener(v -> {
                setConfirm.setDeny(item.getId());
            });
        }

    }

}
