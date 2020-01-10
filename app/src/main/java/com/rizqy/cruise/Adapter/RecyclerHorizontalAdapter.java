package com.rizqy.cruise.Adapter;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rizqy.cruise.Activity.DestinationDetailActivity;
import com.rizqy.cruise.Model.Destination;
import com.rizqy.cruise.R;
import com.rizqy.cruise.Util.Constant;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerHorizontalAdapter extends RecyclerView.Adapter<RecyclerHorizontalAdapter.MyViewHolder> {

    List<Destination> mDestination;

    public RecyclerHorizontalAdapter(List<Destination> DestinationList){
        mDestination = DestinationList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate( R.layout.destination_card, parent, false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder( MyViewHolder holder,final int position) {
        holder.price.setText(mDestination.get(position).getPrice());
        holder.title.setText(mDestination.get(position).getTitle());
        holder.ship.setText(mDestination.get(position).getShip());
        holder.date.setText(mDestination.get(position).getDate());
        holder.visiting.setText(mDestination.get(position).getVisiting());
        Picasso.get()
//                .load("http://172.16.10.66/cruise/"+mDestination.get(position).getPhoto())
                .load( mDestination.get( position ).getPhoto() )
                .resize(200,200)
                .centerCrop()
                .into(holder.photo);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = mDestination.get( position ).getId();
                String photo = mDestination.get(position).getPhoto();
                String price = mDestination.get(position).getPrice();
                String title = mDestination.get(position).getTitle();
                String ship = mDestination.get(position).getShip();
                String date = mDestination.get(position).getDate();
                String visiting = mDestination.get(position).getVisiting();

                Intent intent = new Intent(view.getContext(), DestinationDetailActivity.class);
                intent.putExtra( Constant.KEY_ID_DESTINATION, id );
                intent.putExtra( Constant.KEY_PHOTO, photo );
                intent.putExtra( Constant.KEY_PRICE, price );
                intent.putExtra( Constant.KEY_TITLE, title );
                intent.putExtra( Constant.KEY_SHIP, ship );
                intent.putExtra( Constant.KEY_DATE, date );
                intent.putExtra( Constant.KEY_VISITING, visiting );
                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDestination.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout item_card;
        public TextView price, title, ship, date, visiting;
        public ImageView photo;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            item_card = (LinearLayout) itemView.findViewById(R.id.item_card);
            price = (TextView) itemView.findViewById(R.id.price);
            title = (TextView) itemView.findViewById(R.id.title);
            ship = (TextView) itemView.findViewById(R.id.ship);
            date = (TextView) itemView.findViewById(R.id.date);
            visiting = (TextView) itemView.findViewById(R.id.visiting);
            photo = (ImageView) itemView.findViewById(R.id.photo);
        }
    }

}