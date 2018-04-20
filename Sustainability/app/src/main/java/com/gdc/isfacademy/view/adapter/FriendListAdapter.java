package com.gdc.isfacademy.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.gdc.isfacademy.R;
import com.gdc.isfacademy.model.FriendsBeanList;
import com.gdc.isfacademy.view.customs.customfonts.OpenSansLightTextview;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by akshaydashore on 3/4/18
 */
public class FriendListAdapter extends RecyclerView.Adapter<FriendListAdapter.Holder> implements Filterable{

    Context context;
    ArrayList<FriendsBeanList>friendsBeanLists;
    ArrayList<FriendsBeanList>fillterdFriendsBeanLists;
    ArrayList<FriendsBeanList> filterList;

    OnItemClick onItemClick;


    UserFilter valueFilter;
    private String searchText = "";

    public FriendListAdapter(Context context,ArrayList<FriendsBeanList>friendsBeanLists) {
        this.context = context;
        this.friendsBeanLists=friendsBeanLists;
        fillterdFriendsBeanLists=friendsBeanLists;
    }
    public interface OnItemClick{
        public void onClick(int id,int pos);
    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.add_friend_item, null);
//        View view1 =LayoutInflater.from(context).inflate(R.layout.add_friend_item,null);
        Holder holder = new Holder(view);
        return holder;
    }

    public ArrayList<FriendsBeanList> getData() {
        return friendsBeanLists;
    }




    @Override
    public void onBindViewHolder(Holder holder, final int position) {
        holder.profile_name.setText(friendsBeanLists.get(position).getName());

       /* if (position % 4 == 0) {
            holder.action_tv.setBackgroundResource(R.drawable.gray_rect_crop_bg);
            holder.action_tv.setText("try latter");
        }*/
        holder.action_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onClick(v.getId(),position);
            }
        });

      /*  if(friendsBeanLists.get(position).isRequestSend()){
            holder.action_tv.setBackgroundResource(R.drawable.gray_rect_crop_bg);

        }
        else {
            holder.action_tv.setBackgroundResource(R.drawable.blue_rect_crop_bg);

        }*/
    }

    @Override
    public int getItemCount() {
        return friendsBeanLists.size();
    }

    @Override
    public Filter getFilter() {
        if (valueFilter == null) {
            valueFilter = new UserFilter();
        }
        return valueFilter;    }


    class Holder extends RecyclerView.ViewHolder {

        CircleImageView profile_image;
        OpenSansLightTextview action_tv, profile_name;

        public Holder(View itemView) {
            super(itemView);
            profile_image = (CircleImageView) itemView.findViewById(R.id.profile_image);
            action_tv = (OpenSansLightTextview) itemView.findViewById(R.id.action_tv);
            profile_name = (OpenSansLightTextview) itemView.findViewById(R.id.profile_name);
        }
    }
    private class UserFilter extends Filter {
        FilterResults results = new FilterResults();

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            CharSequence newConstraint= constraint.toString().trim();
            if (newConstraint != null && newConstraint.length() > 0) {
                searchText = newConstraint.toString().trim();
                filterList = new ArrayList();
                for (int i = 0; i < fillterdFriendsBeanLists.size(); i++) {
                    if (fillterdFriendsBeanLists.get(i).getName() != null) {
                        if (fillterdFriendsBeanLists.get(i).getName().toUpperCase()
                                .contains(newConstraint.toString().toUpperCase())
                                || fillterdFriendsBeanLists.get(i).getName()
                                .contains(newConstraint.toString().toUpperCase())) {
                            filterList.add(fillterdFriendsBeanLists.get(i));
                        }
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                searchText = "";
                results.count = fillterdFriendsBeanLists.size();
                results.values = fillterdFriendsBeanLists;
            }
            return results;
        }




        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            friendsBeanLists = (ArrayList<FriendsBeanList>) filterResults.values;
            notifyDataSetChanged();
        }
    }

}
