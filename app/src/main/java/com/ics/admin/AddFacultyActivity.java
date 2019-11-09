package com.ics.admin;

import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

public class AddFacultyActivity extends AppCompatActivity {
    EditText edt_name,edt_mobile,edt_email,edt_password,edt_address;
    Button btn_save;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_faculty);

        edt_name=(EditText)findViewById(R.id.edt_name);
        edt_mobile=(EditText)findViewById(R.id.edt_mobile);
        edt_email=(EditText)findViewById(R.id.edt_email);
        edt_password=(EditText)findViewById(R.id.edt_password);
        edt_address=(EditText)findViewById(R.id.edt_address);
        btn_save=(Button) findViewById(R.id.btn_save);

         btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Verifyotp(edt_name.getText().toString(),edt_mobile.getText().toString(),edt_email.getText().toString(),edt_password.getText().toString(),edt_address.getText().toString()).execute();


                Intent intent=new Intent(AddFacultyActivity.this,AdminActivity.class);
                startActivity(intent);
                Toast.makeText(getApplication(),"Registation Sucessful"+btn_save, Toast.LENGTH_SHORT).show();

            }
        });

        edt_mobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() == 10) {
                    // new RegGetOtp(s.toString()).execute();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



    }

//    private class RegGetOtp extends AsyncTask<String, Void, String> {
//        String Mobile_Number;
//        private Dialog dialog;
//
//        public RegGetOtp(String toString) {
//
//            this.Mobile_Number = toString;
//        }
//
//        @Override
//        protected String doInBackground(String... arg0) {
//
//            try {
//
//                URL url = new URL("http://ihisaab.in/school_lms/Adminapi/registration");
//
//                JSONObject postDataParams = new JSONObject();
//                postDataParams.put("mobile", Mobile_Number);
//
//
//                Log.e("postDataParams", postDataParams.toString());
//
//                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                conn.setReadTimeout(15000 /*milliseconds*/);
//                conn.setConnectTimeout(15000 /*milliseconds*/);
//                conn.setRequestMethod("POST");
//                conn.setDoInput(true);
//                conn.setDoOutput(true);
//
//                OutputStream os = conn.getOutputStream();
//                BufferedWriter writer = new BufferedWriter(
//                        new OutputStreamWriter(os, "UTF-8"));
//                writer.write(getPostDataString(postDataParams));
//
//                writer.flush();
//                writer.close();
//                os.close();
//
//                int responseCode = conn.getResponseCode();
//
//                if (responseCode == HttpsURLConnection.HTTP_OK) {
//
//                    BufferedReader in = new BufferedReader(new
//                            InputStreamReader(
//                            conn.getInputStream()));
//
//                    StringBuffer sb = new StringBuffer("");
//                    String line = "";
//
//                    while ((line = in.readLine()) != null) {
//
//                        StringBuffer Ss = sb.append(line);
//                        Log.e("Ss", Ss.toString());
//                        sb.append(line);
//                        break;
//                    }
//
//                    in.close();
//                    return sb.toString();
//
//                } else {
//                    return new String("false : " + responseCode);
//                }
//            } catch (Exception e) {
//                return new String("Exception: " + e.getMessage());
//            }
//
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//            if (result != null) {
////                dialog.dismiss();
//
//                JSONObject jsonObject = null;
//                Log.e("PostRegistration", result.toString());
//                try {
//
//                    jsonObject = new JSONObject(result);
//                    if(!jsonObject.getBoolean("responce")){
//                        Toast.makeText(getApplication(),"Are u alreay registred Please Login"+result, Toast.LENGTH_SHORT).show();
//                      //  Intent intent = new Intent(RegistrationActivity.this ,OTPActivity.class);
//                        //startActivity(intent);
//                        finish();
////                        Intent
//                    }else {
//                        String mobile= jsonObject.getString("data");
//                        edt_mobile.setText(mobile);
////                        edt_otp.setVisibility(View.VISIBLE);
////                        Intent
//                    }
//
//
//                } catch (JSONException e) {
//
//                    e.printStackTrace();
//                }
//
//            }
//        }
//
//        public String getPostDataString(JSONObject params) throws Exception {
//
//            StringBuilder result = new StringBuilder();
//            boolean first = true;
//
//            Iterator<String> itr = params.keys();
//
//            while (itr.hasNext()) {
//
//                String key = itr.next();
//                Object value = params.get(key);
//
//                if (first)
//                    first = false;
//                else
//                    result.append("&");
//
//                result.append(URLEncoder.encode(key, "UTF-8"));
//                result.append("=");
//                result.append(URLEncoder.encode(value.toString(), "UTF-8"));
//
//            }
//            return result.toString();
//      }
//    }

    private class Verifyotp extends AsyncTask<String, Void, String> {
        String Name;
        String Phone_Number;
        String Email;
        String Password;
        String Address;
        private Dialog dialog;


        public Verifyotp(String name, String phone_number, String email,String Password,String Address) {
            this.Name = name;
            this.Phone_Number =phone_number ;
            this.Email = email;
            this.Password=Password;
            this.Address=Address;
        }

        @Override
        protected String doInBackground(String... arg0) {

            try {

                URL url = new URL("http://ihisaab.in/school_lms/Adminapi/registration");

                JSONObject postDataParams = new JSONObject();

                postDataParams.put("name", Name);
                postDataParams.put("mobile", Phone_Number);
                postDataParams.put("email", Email);
                postDataParams.put("password", Password);
                postDataParams.put("address", Address);

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
                    if(!jsonObject.getBoolean("responce")){
                        //    getotp.setVisibility(View.VISIBLE);
//                        Intent
                    }
                    else
                    {
                        Toast.makeText(AddFacultyActivity.this, "Faculty Added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AddFacultyActivity.this , AdminActivity.class);
                        startActivity(intent);
//                        JSONObject massage=jsonObject.getJSONObject("massage");
//
////                        String mobile=jsonObject.getString("mobile");
//
////                        Toast.makeText(getApplication(),"Sorry You are not Registerd"+name, Toast.LENGTH_SHORT).show();
//
//                        //Intent intent=new Intent(RegistrationActivity.this, HomePageActivity.class);
//                        //startActivity(intent);
//                        //finish();
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
