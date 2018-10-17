package com.wzx.javaapp;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.wzx.javaapp.databinding.ItemUserBinding;

import java.util.ArrayList;

/**
 * 描述：
 * <p>
 * 创建人： Administrator
 * 创建时间： 2018/10/17
 * 更新时间：
 * 更新内容：
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private ArrayList<User> data;

    public UserAdapter() {
        data = new ArrayList<>();
    }

    public void addData(User user) {
        data.add(user);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserViewHolder(
                (ItemUserBinding) DataBindingUtil.inflate(
                        LayoutInflater.from(parent.getContext()), R.layout.item_user, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = data.get(position);
        user.setColorId(position % 2 == 0 ?
                ContextCompat.getColor(holder.itemView.getContext(), R.color.colorAccent) :
                ContextCompat.getColor(holder.itemView.getContext(), R.color.colorPrimary));
        holder.dataBinding.setUser(user);
        holder.dataBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {

        private ItemUserBinding dataBinding;

        public UserViewHolder(ItemUserBinding dataBinding) {
            super(dataBinding.getRoot());
            this.dataBinding = dataBinding;
        }


    }
}
