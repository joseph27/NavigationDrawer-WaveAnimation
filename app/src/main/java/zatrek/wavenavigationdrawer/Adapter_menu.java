/*
 * *
 *  * Created by Youssef Assad on 5/12/18 8:41 PM
 *  * Copyright (c) 2018 . All rights reserved.
 *  * Last modified 5/12/18 1:12 PM
 *
 */

package zatrek.wavenavigationdrawer;

import android.app.Activity;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by Joseph27 on 5/11/17.
 */

public class Adapter_menu extends RecyclerView.Adapter<Adapter_menu.ViewHolder_Menu> {


    private final TypedArray img;
    private final TypedArray titles;

    private final int space = 2;
    private final int item = 3;
    private static final String KEY_SPACE = "SPACE";
    ListenerOnMenuItemClick MenuItemClickListener;



    public Adapter_menu(Activity activity ,ListenerOnMenuItemClick menuItemClick){
         img = activity.getResources().obtainTypedArray(R.array.menu_array);
         titles = activity.getResources().obtainTypedArray(R.array.MenuTitles);
         this.MenuItemClickListener = menuItemClick;

    }

    @Override
    public int getItemViewType(int position) {

       if (titles.getString(position).equals(KEY_SPACE)){
            return  space;
        }else {
            return  item;
        }

    }

    @Override
    public ViewHolder_Menu onCreateViewHolder(ViewGroup parent, int viewType) {
        //Log.d(TAG, "onCreateViewHolder: ");
        View v =  null;
       if (viewType == space){
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.menu_space, parent, false);

        }else if (viewType == item){
           v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.menu_item, parent, false);
        }

        return new ViewHolder_Menu(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder_Menu holder, final int position) {


        if (getItemViewType(position) != space) {

            holder.SetIcon();
            if (holder.im_icon != null) {
                holder.im_icon.setImageResource(img.getResourceId(position, 0));
            }

            if (holder.im_title != null) {
                holder.im_title.setText(titles.getString(position));
            }
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MenuItemClickListener.Item(position);
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return img.length();
    }



    public class ViewHolder_Menu extends RecyclerView.ViewHolder {

        final View mView;
        ImageView im_icon;
        TextView im_title;
        public ViewHolder_Menu(View itemView) {
            super(itemView);
            this.mView = itemView;

        }
        public void SetIcon(){
            im_icon = itemView.findViewById(R.id.iv_icon);
            im_title = itemView.findViewById(R.id.tv_title);

        }
    }


    public interface ListenerOnMenuItemClick {
        void Item(int Position);
    }


}

