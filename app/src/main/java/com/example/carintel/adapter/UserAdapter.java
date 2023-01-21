package com.example.carintel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.carintel.R;
import com.example.carintel.RecyclerViewInterface;
import com.example.carintel.model.User;
import de.hdodenhof.circleimageview.CircleImageView;
import java.util.List;
import kotlin.jvm.internal.PropertyReference0Impl;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private RecyclerViewInterface recyclerViewInterface;

    private Context context;
    private List<User> userList;

    public UserAdapter(final Context context, final List<User> userList, final RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.userList = userList;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(
                R.layout.user_displayed_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final User user = userList.get(position);

        holder.type.setText(user.getType());
        /*if (user.getType().equals("auctioneer")){
            holder.emailNow.setVisibility(View.VISIBLE);
        }*/
        holder.userEmail.setText(user.getEmail());
        holder.userName.setText(user.getName());
        holder.phoneNumber.setText(user.getPhonenumber());

        /*Glide.with(context).load(user.getProfilepictureurl()).into(holder.userProfileImage)*/

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public CircleImageView userProfileImage;
        public TextView type, userName, userEmail, phoneNumber;
        public Button emailNow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            userProfileImage = itemView.findViewById(R.id.userProfileImage);
            type = itemView.findViewById(R.id.type);
            userName = itemView.findViewById(R.id.userName);
            userEmail = itemView.findViewById(R.id.userEmail);
            phoneNumber = itemView.findViewById(R.id.phoneNumber);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (recyclerViewInterface !=null){
                        int pos = getAdapterPosition();
                         if (pos != RecyclerView.NO_POSITION){
                             recyclerViewInterface.onItemClick(pos);
                         }
                    }
                }
            });
        }
    }
}
