package com.ics.admin.Adapter;

import android.content.Context;
import android.os.StrictMode;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.ics.admin.Model.SubMenuPermissions;
import com.ics.admin.R;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class SubMenuAdapter extends RecyclerView.Adapter<SubMenuAdapter.MyViewHolder> {
    public static ArrayList<String> Myproducttiles = new ArrayList<>();
    public List<SubMenuPermissions> menuPermisssionList;
    //    public List<SubMenuPermissions> subMenuPermisssionsList = new ArrayList<>();
    SubPermissionAdapter subPermissionAdapter;
    int pos_try;
    StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
    private Context mContext;
    private SubMenuPermissions menuPermisssion;
    private RecyclerView subrec;

    public SubMenuAdapter(Context context, ArrayList<SubMenuPermissions> menuPermisssion) {
        mContext = context;
        this.menuPermisssionList = menuPermisssion;
        setHasStableIds(true);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.subpermissionexpand, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        menuPermisssion = menuPermisssionList.get(position);
        this.pos_try = position;
        holder.namestff.setText(menuPermisssionList.get(position).getSubmenu());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        StrictMode.setVmPolicy(builder.build());

    }

    @Override
    public int getItemCount() {
        return menuPermisssionList.size();
    }

    @Override
    public long getItemId(int position) {
//        return super.getItemId(position);
        return position;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView stffimg;
        TextView namestff, emailstff, stffid;
        ToggleButton maintoggel;

        public MyViewHolder(View itemView) {
            super(itemView);
            //   mname = (TextView) itemView.findViewById(R.id.mname);
            // manu_img =  itemView.findViewById(R.id.manu_img);
            namestff = itemView.findViewById(R.id.names);
            maintoggel = itemView.findViewById(R.id.maintoggel);

            //     adddes =  itemView.findViewById(R.id.adddes);


        }
    }
}



  
