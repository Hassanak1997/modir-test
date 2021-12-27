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


public class RestFragment extends Fragment implements SetConfirm {

    View view;
    //    List<Rest> items;
    RestAdapter adapter;
    ProgressBar prg_main;
    TextView txt_empty;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_request_fragment, container, false);

        RecyclerView rv = view.findViewById(R.id.request_recyclerview);
        adapter = new RestAdapter(getContext(),this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
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
//            Rest item = new Rest();
//            item.setName("محمدحسن آکوچکیان");
//            item.setDatecreated("1400/11/"+i);
//            item.setDaterest("1400/10/"+(i+5));
////            item.setExplain(""1400/10/"+(i+5)");
//            items.add(item);
//        }

        ApiClient.getInstance().getApi().Api_get_rests().enqueue(new Callback<List<Rest>>() {
            @Override
            public void onResponse(Call<List<Rest>> call, Response<List<Rest>> response) {
                prg_main.setVisibility(View.GONE);
                if (response.isSuccessful() && !response.body().isEmpty()){
                    adapter.setItems(response.body());
                    Log.d("testttt", "set_list: " + response.body().get(0).getUser().getPhone());
                }
                else {
                    txt_empty.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<Rest>> call, Throwable t) {
                prg_main.setVisibility(View.GONE);
                txt_empty.setVisibility(View.VISIBLE);
                Toast.makeText(getContext(), "خطا در برقراری ارتباط با سرور", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void setDeny(String id) {
        prg_main.setVisibility(View.VISIBLE);
        ApiClient.getInstance().getApi().Api_confirm_rest("refuse",id).enqueue(new Callback<StatusModel>() {
            @Override
            public void onResponse(Call<StatusModel> call, Response<StatusModel> response) {
                prg_main.setVisibility(View.GONE);
                if (response.isSuccessful() && response.body().getStatus().equals("ok")){
                    set_list();
                    Toast.makeText(getContext(), "با موفقیت رد شد", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getContext(), "خطای غیر منتظره", Toast.LENGTH_SHORT).show();

                }
                
            }

            @Override
            public void onFailure(Call<StatusModel> call, Throwable t) {
                prg_main.setVisibility(View.GONE);
                Toast.makeText(getContext(), "خطا در برقراری ارتباط با سرور", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void setAccept(String id) {
        prg_main.setVisibility(View.VISIBLE);
        ApiClient.getInstance().getApi().Api_confirm_rest("confirm",id).enqueue(new Callback<StatusModel>() {
            @Override
            public void onResponse(Call<StatusModel> call, Response<StatusModel> response) {
                prg_main.setVisibility(View.GONE);
                if (response.isSuccessful() && response.body().getStatus().equals("ok")){
                    set_list();
                    Toast.makeText(getContext(), "با موفقیت تایید شد", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getContext(), "خطای غیر منتظره", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<StatusModel> call, Throwable t) {
                prg_main.setVisibility(View.GONE);
                Toast.makeText(getContext(), "خطا در برقراری ارتباط با سرور", Toast.LENGTH_SHORT).show();
            }
        });
    }
}