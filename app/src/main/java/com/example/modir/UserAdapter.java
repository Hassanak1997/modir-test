package com.example.modir;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    Context context;
    List<User> items;

    public void setItems(List<User> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public UserAdapter(Context context) {
        items = new ArrayList<>();
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.users_card_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {
        holder.Bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,phone,countrest;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.user_name);
            phone = itemView.findViewById(R.id.phone_number);
//            countrest = itemView.findViewById(R.id.request_in_mounth);
        }

        public void Bind(User item){
            name.setText(item.getName());
            phone.setText(item.getPhone());
//            countrest.setText(item.getCountrest());
        }

    }
}
