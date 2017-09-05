package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ilijaangeleski.egym.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import interfaces.ApiConstants;
import model.Login;
import model.Picture;
import model.User;
import util.CircleTransform;

/**
 * Created by Ilija Angeleski on 12/13/2016.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private List<User> items;
    private Context context;
    private int layoutResourceId;

    private OnUserItemClick listener;

    /**
     * On recyclerview item click listener
     */
    public interface OnUserItemClick {
        void onUserClick(User user, ImageView profileImage);
    }

    public RecyclerAdapter(List<User> items, Context context, int layoutResourceId) {
        this.items = items;
        this.context = context;
        this.layoutResourceId = layoutResourceId;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(layoutResourceId, parent, false);
        MyViewHolder holder = new MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final User current = items.get(position);
        holder.phone.setText(current.getPhone());
        holder.username.setText(current.getLogin().getUsername());
        Picasso.with(context).load(current.getPicture().getLarge()).transform(new CircleTransform()).placeholder(R.mipmap.ic_profile).error(R.mipmap.ic_profile).into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onUserClick(current, holder.image);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView username, phone;
        ImageView image;

        public MyViewHolder(View itemView) {
            super(itemView);
            username = (TextView) itemView.findViewById(R.id.username);
            phone = (TextView) itemView.findViewById(R.id.phoneNumber);
            image = (ImageView) itemView.findViewById(R.id.profileImage);

        }
    }

    public void setOnUserItemClick(OnUserItemClick listener) {
        this.listener = listener;
    }
}
