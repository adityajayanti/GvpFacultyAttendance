package com.example.aditya.gvpfacultyattendance;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ChangePassword extends AppCompatActivity implements View.OnClickListener {

    Button btn;
    EditText username, mob;
    ProgressDialog progressDialog;
    String user ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        getSupportActionBar().setTitle("Change Password");

        btn = (Button)findViewById(R.id.button2);
        username = (EditText) findViewById(R.id.editUser);



        progressDialog = new ProgressDialog(this);
        btn.setOnClickListener(this);

    }

    public void Search(){

        user = username.getText().toString().trim();


        progressDialog.setMessage("Searching User...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constansts.ScrhURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            if(!jsonObject.getBoolean("error")){
                                Log.i("username",SharedPrefManager.getInstance(ChangePassword.this).getUsername().toString());
                                if(user.equals(SharedPrefManager.getInstance(ChangePassword.this).getUsername().toString())) {
                                    Toast.makeText(getApplicationContext(), "Please change your password !", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), NewPassword.class);
                                    intent.putExtra("user", user);
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(getApplicationContext(), "Please enter correct username", Toast.LENGTH_SHORT).show();
                                }

                            }else{
                                Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.hide();
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<>();
                params.put("user_name",user);
                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.features_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){

            case R.id.Logout:
                SharedPrefManager.getInstance(this).islogout();
                finish();
                startActivity(new Intent(getApplicationContext(), Login.class));
                break;
            case R.id.backto:
                startActivity(new Intent(getApplicationContext(), HomeMenu.class));
                break;

            case R.id.hometo:
                startActivity(new Intent(getApplicationContext(), HomeMenu.class));
                finish();


        }
        return  true;
    }

    @Override
    public void onClick(View v) {
        if(v== btn){
            Search();
        }
    }
}
