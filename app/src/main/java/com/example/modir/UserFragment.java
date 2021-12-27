package com.example.modir;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserFragment extends Fragment {

    View view;
    UserAdapter adapter;
    ProgressBar prg_main;
    TextView txt_empty;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_users_fragment, container, false);

        RecyclerView rv = view.findViewById(R.id.user_recyclerview);
        adapter = new UserAdapter(getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rv.setAdapter(adapter);
        rv.setLayoutManager(layoutManager);

        prg_main = view.findViewById(R.id.prd_main);
        txt_empty = view.findViewById(R.id.txt_empty);
        // Inflate the layout for this fragment

        set_list();
        return view;
    }

    private void set_list() {
//        items = new ArrayList<>();
//        for (int i = 0 ; i < 10 ; i++ ){
//            User item = new User();
//            item.setName("محمدحسن آکوچکیان");
//            item.setPhone("0913123456" + i);
//            item.setCountrest(String.valueOf(i));
//            items.add(item);
//        }



        ApiClient.getInstance().getApi().Api_get_users().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                prg_main.setVisibility(View.GONE);
                if (response.isSuccessful() && !response.body().isEmpty()){
                    adapter.setItems(response.body());
//                    Log.d("testttt", "set_list: " + response.body().get(0).getName());
                }
                else {
                    txt_empty.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                prg_main.setVisibility(View.GONE);
                txt_empty.setVisibility(View.VISIBLE);
                Toast.makeText(getContext(), "خطا در برقراری ارتباط با سرور", Toast.LENGTH_SHORT).show();
            }
        });
    }
}