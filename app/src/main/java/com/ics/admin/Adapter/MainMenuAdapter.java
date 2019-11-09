package com.ics.admin.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.ics.admin.Model.MenuPermisssion;
import com.ics.admin.Model.SubMenuPermissions;
import com.ics.admin.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainMenuAdapter extends RecyclerView.Adapter<MainMenuAdapter.MyViewHolder> {
    public static ArrayList<String> Myproducttiles = new ArrayList<>();
    public List<MenuPermisssion> menuPermisssionList;
    public List<SubMenuPermissions> subMenuPermisssionsList = new ArrayList<>();
    SubPermissionAdapter subPermissionAdapter;
    int pos_try;
    StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
    private Context mContext;
    private MenuPermisssion menuPermisssion;
    private RecyclerView subrec;

    public MainMenuAdapter(Context context, List<MenuPermisssion> menuPermisssion) {
        mContext = context;
        this.menuPermisssionList = menuPermisssion;
        setHasStableIds(true);
    }

    @Override
    public MainMenuAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menuinfogroup, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MainMenuAdapter.MyViewHolder holder, final int position) {
        menuPermisssion = menuPermisssionList.get(position);
        this.pos_try = position;
        holder.namestff.setText(menuPermisssionList.get(position).getMenu_name());
//        holder.namestff.setText("jhsgdjsydg");

//        holder.emailstff.setText(menuPermisssion.getEmail());
//        holder.stffid.setText(menuPermisssion.getDesignation());
//        Glide.with(mContext).load("http://neareststore.in/uploads/staff/" + addNews141.getImage()).addListener(new RequestListener<Drawable>() {
//            @Override
//            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                Toast.makeText(mContext, "Failed to load image", Toast.LENGTH_SHORT).show();
//                return false;
//            }
//
//            @Override
//            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                Toast.makeText(mContext, "Image laod success", Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        }).into(holder.stffimg);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subMenuPermisssionsList.clear();
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                LayoutInflater li = (LayoutInflater) v.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        LayoutInflater inflater = getLayoutInflater();
                View dialogLayout = li.inflate(R.layout.facultypermissiondialog, null);
                TextView roles = dialogLayout.findViewById(R.id.roles);
                ToggleButton dialogtoggel = dialogLayout.findViewById(R.id.dialogtoggel);
                subrec = dialogLayout.findViewById(R.id.subrec);

                roles.setText(menuPermisssionList.get(position).getMenu_name());
//                roles.setText(menuPermisssionList.get(position).getMenu_name());
                if(!menuPermisssionList.get(position).getStatus().equals("null"))
                {
                    dialogtoggel.setChecked(true);
                    new GETAllSubMEnues(menuPermisssionList.get(position).getStatus(), menuPermisssionList.get(position).getMenu_name() ,menuPermisssionList.get(position).getMenu_id(),2).execute();
                    subrec.setVisibility(View.VISIBLE);
                }else {
                    Toast.makeText(mContext, "Nothing has assigned here", Toast.LENGTH_SHORT).show();
                }
                dialogtoggel.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked)
                        {
                            new POSt_AppPErmissions("2",menuPermisssionList.get(position).getMenu_id(),"1",position).execute();
                            subrec.setVisibility(View.VISIBLE);

                        }else {
                            new POSt_AppPErmissions("2",menuPermisssionList.get(position).getMenu_id(),"NI KRna",position).execute();
                            subMenuPermisssionsList.clear();
                            subPermissionAdapter.notifyDataSetChanged();
                            subrec.setVisibility(View.GONE);
                        }
                    }
                });
//        rolesexpand.setonc
                builder.setPositiveButton("OK", null);
                builder.setView(dialogLayout);
                builder.show();
            }
        });
//        searching_manufacturers_data = menuPermisssionList.get(pos_try);
//        Log.e("Position","is "+pos_try);
//        document = searching_manufacturers_data.getBrandName();
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
            namestff = itemView.findViewById(R.id.lblListHeader);
            maintoggel = itemView.findViewById(R.id.maintoggel);

            //     adddes =  itemView.findViewById(R.id.adddes);


        }
    }

    private class GETAllSubMEnues extends AsyncTask<String, Void, String> {
        String menu_id;
        int user_id;
        String menu_name;
        String mainstatus;
        int position;

        public GETAllSubMEnues(String status, String menu_name, String menu_id, int user_id) {
            this.menu_id = menu_id;
            this.menu_name = menu_name;
            this.user_id = user_id;
            this.mainstatus = status;

        }


        @Override
        protected String doInBackground(String... arg0) {

            try {

                URL url = new URL("http://ihisaab.in/school_lms/Adminapi/getsubmenulist");

                JSONObject postDataParams = new JSONObject();

                postDataParams.put("user_id", user_id);
                postDataParams.put("menu_id", menu_id);
                postDataParams.put("teacher_id", ((Activity) mContext).getIntent().getStringExtra("teacher_id"));
                Log.e("postDataParams", postDataParams.toString());

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000 /*milliseconds*/);
                conn.setConnectTimeout(15000 /*milliseconds*/);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));

                writer.flush();
                writer.close();
                os.close();

                int responseCode = conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {

                    BufferedReader in = new BufferedReader(new
                            InputStreamReader(
                            conn.getInputStream()));

                    StringBuffer sb = new StringBuffer("");
                    String line = "";

                    while ((line = in.readLine()) != null) {

                        StringBuffer Ss = sb.append(line);
                        Log.e("Ss", Ss.toString());
                        sb.append(line);
                        break;
                    }

                    in.close();
                    return sb.toString();

                } else {
                    return new String("false : " + responseCode);
                }
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }

        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
//                dialog.dismiss();

                JSONObject jsonObject = null;
                Log.e("PostRegistration", result.toString());
                try {

                    jsonObject = new JSONObject(result);
                    if (!jsonObject.getBoolean("responce")) {
                        //    getotp.setVisibility(View.VISIBLE);
//                        Intent
                    } else {
                        int i = 0;
                        for (; i < jsonObject.getJSONArray("data").length(); i++) {
                            JSONObject jsonObject1 = jsonObject.getJSONArray("data").getJSONObject(i);
                            String permission_id = jsonObject1.getString("permission_id");
                            String submenu = jsonObject1.getString("submenu");
                            String status = jsonObject1.getString("status");
                            String sub_id = jsonObject1.getString("sub_id");
//                            String menu_name = jsonObject1.getString("menu_name");
                            Log.e("submenu name",""+submenu);
                            subMenuPermisssionsList.add(new SubMenuPermissions(permission_id,submenu ,status,mainstatus,sub_id));
//                            menuPermissionsSubList.add(position , new SubMenuPermissions(permission_id, submenu));
                        }
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext );
                        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                        subrec.setLayoutManager(linearLayoutManager);
                        subPermissionAdapter = new SubPermissionAdapter(mContext,subMenuPermisssionsList);
                        subrec.setAdapter(subPermissionAdapter);
                        subPermissionAdapter.notifyDataSetChanged();

//
                    }


                } catch (JSONException e) {

                    e.printStackTrace();
                }

            }
        }

        public String getPostDataString(JSONObject params) throws Exception {

            StringBuilder result = new StringBuilder();
            boolean first = true;

            Iterator<String> itr = params.keys();

            while (itr.hasNext()) {

                String key = itr.next();
                Object value = params.get(key);

                if (first)
                    first = false;
                else
                    result.append("&");

                result.append(URLEncoder.encode(key, "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(value.toString(), "UTF-8"));

            }
            return result.toString();
        }
    }

    private class POSt_AppPErmissions extends AsyncTask<String, Void, String> {
        String menu_id;
        String user_id;
        String menu_name;
        int position;
        String status;

        public POSt_AppPErmissions(String user_id, String menu_id, String status, int position) {
            this.user_id = user_id;
            this.menu_id = menu_id;
            this.status = status;
            this.position = position;

        }


        @Override
        protected String doInBackground(String... arg0) {

            try {

                URL url = new URL("http://ihisaab.in/school_lms/Adminapi/add_menupermission");

                JSONObject postDataParams = new JSONObject();

                postDataParams.put("user_id", ((Activity)mContext).getIntent().getStringExtra("teacher_id"));
                postDataParams.put("menu_id", menu_id);
                postDataParams.put("status", status);
                Log.e("postDataParams", postDataParams.toString());

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000 /*milliseconds*/);
                conn.setConnectTimeout(15000 /*milliseconds*/);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));

                writer.flush();
                writer.close();
                os.close();

                int responseCode = conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {

                    BufferedReader in = new BufferedReader(new
                            InputStreamReader(
                            conn.getInputStream()));

                    StringBuffer sb = new StringBuffer("");
                    String line = "";

                    while ((line = in.readLine()) != null) {

                        StringBuffer Ss = sb.append(line);
                        Log.e("Ss", Ss.toString());
                        sb.append(line);
                        break;
                    }

                    in.close();
                    return sb.toString();

                } else {
                    return new String("false : " + responseCode);
                }
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }

        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
//                dialog.dismiss();

                JSONObject jsonObject = null;
                Log.e("PostRegistration", result.toString());
                try {

                    jsonObject = new JSONObject(result);
                    if (!jsonObject.getBoolean("responce")) {
                        //    getotp.setVisibility(View.VISIBLE);
//                        Intent
                    } else {
                        subrec.setVisibility(View.VISIBLE);
                        menuPermisssionList.get(position).setStatus("xyz");
                        new GETAllSubMEnues(menuPermisssionList.get(this.position).getStatus(), menuPermisssionList.get(this.position).getMenu_name() ,menuPermisssionList.get(this.position).getMenu_id(),2).execute();
                    }


                } catch (JSONException e) {

                    e.printStackTrace();
                }

            }
        }

        public String getPostDataString(JSONObject params) throws Exception {

            StringBuilder result = new StringBuilder();
            boolean first = true;

            Iterator<String> itr = params.keys();

            while (itr.hasNext()) {

                String key = itr.next();
                Object value = params.get(key);

                if (first)
                    first = false;
                else
                    result.append("&");

                result.append(URLEncoder.encode(key, "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(value.toString(), "UTF-8"));

            }
            return result.toString();
        }
    }
}
