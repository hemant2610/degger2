package com.example.hemant_infyom.myapplication;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hemant_infyom.myapplication.model.Address;
import com.example.hemant_infyom.myapplication.model.Users;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Hemant-Infyom on 9/1/2016.
 */
public class UserAdapterClass extends RecyclerView.Adapter<UserAdapterClass.ListViewHolder> {

    Context context;
    ArrayList<Users> usersArrayList;


    UserAdapterClass(Context context, ArrayList<Users> usersArrayList) {
        this.context = context;
        this.usersArrayList = usersArrayList;
    }


    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_card, parent, false);
        ListViewHolder listViewHolder = new ListViewHolder(v);
        return listViewHolder;
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, final int position) {
        Users users = usersArrayList.get(position);
        // Log.e("AAdddddddddddddd", "" + users.getAddress().getCity());


        holder.mId.setText("" + users.getId());
        holder.mName.setText("" + users.getName());
        holder.mUserName.setText("" + users.getUsername());
        holder.mEmail.setText("" + users.getEmail());

        holder.mId.setOnClickListener(h -> {


            Log.e("kjsakjdsa", "asdklajskdjas");
            Toast.makeText(context, "hello" + position, Toast.LENGTH_LONG).show();

        });

        holder.cardView.setOnClickListener(h -> Toast.makeText(context, "hello" + position, Toast.LENGTH_LONG).show());


        holder.cardView.setOnCreateContextMenuListener(
                new View.OnCreateContextMenuListener() {
                    @Override
                    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                        menu.setHeaderTitle("Delete UserData");
                        menu.add("Delete").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {

                                usersArrayList.remove(position);
                                notifyDataSetChanged();
                                Toast.makeText(context, "Hello" + position, Toast.LENGTH_SHORT).show();
                                return true;
                            }
                        });
                    }
                });
    }

    @Override
    public int getItemCount() {
        return usersArrayList.size();
    }

    public static class ListViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.card_view)
        CardView cardView;

        @Bind(R.id.Id)
        TextView mId;


        @Bind(R.id.txtName)
        TextView mName;


        @Bind(R.id.txtUserName)
        TextView mUserName;


        @Bind(R.id.txtEmail)
        TextView mEmail;


        public ListViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}
