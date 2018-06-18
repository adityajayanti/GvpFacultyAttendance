package com.example.aditya.gvpattendance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class AttendanceMenu extends AppCompatActivity {

    RecyclerView recyclerView;
    List<com.example.aditya.gvpattendance.MainPageContents> contentsList;
    com.example.aditya.gvpattendance.ContentsAdapter contentsAdapter;
    com.example.aditya.gvpattendance.DataTimeHandler dataTimeHandler;
    public void PageOne(View view){

        Intent intent = new Intent(getApplicationContext(), com.example.aditya.gvpattendance.MainActivity.class);
        startActivity(intent);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        if(!SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(getApplicationContext(), com.example.aditya.gvpattendance.MainActivity.class));
        }
        dataTimeHandler = new com.example.aditya.gvpattendance.DataTimeHandler();
        getSupportActionBar().setTitle(dataTimeHandler.getActionBarDate());
        //Toast.makeText(getApplicationContext(),"Welcome "+SharedPrefManager.getInstance(this).getUsername(),Toast.LENGTH_LONG).show();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        contentsList = new ArrayList<>();
        //adding some items to our list
        contentsList.add(
                new com.example.aditya.gvpattendance.MainPageContents(
                        "Take Attendance",
                        "Click to take attendance of your classes.",
                        R.drawable.take));

        contentsList.add(
                new com.example.aditya.gvpattendance.MainPageContents(
                        "CTV Attendance",
                        "Click to take attendance of CTV.",
                        R.drawable.ctv));

        contentsList.add(
                new com.example.aditya.gvpattendance.MainPageContents(
                        "Adjust Attendance ",
                        "Click to Adjust class with other faculty.",
                        R.drawable.swap));

        contentsList.add(
                new com.example.aditya.gvpattendance.MainPageContents(
                        "Attendance Report",
                        "Click to get the report of student attendance.",
                        R.drawable.report));

        contentsList.add(
                new com.example.aditya.gvpattendance.MainPageContents(
                        "Update Attendance ",
                        "Click to update student attendance.",
                        R.drawable.update));

        contentsList.add(
                new com.example.aditya.gvpattendance.MainPageContents(
                        "Delete Attendance ",
                        "Click to delete student attendance.",
                        R.drawable.delete));


        contentsAdapter = new com.example.aditya.gvpattendance.ContentsAdapter(this, contentsList,0);

        //setting adapter to recyclerview
        recyclerView.setAdapter(contentsAdapter);

        contentsAdapter.setItemClickListener(new com.example.aditya.gvpattendance.ContentsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if(position == 0) {

                    Intent intent = new Intent(getApplicationContext(), com.example.aditya.gvpattendance.SubjectActivity.class);
                    intent.putExtra("weekname",dataTimeHandler.getWeekDay().toString());
                    intent.putExtra("activityno","Main2");
                    startActivity(intent);
                    finish();

                }else if(position == 1){
                    Intent intent = new Intent(getApplicationContext(), com.example.aditya.gvpattendance.MySubject.class);
                    intent.putExtra("value","one");
                    startActivity(intent);
                    finish();
                }

                else if(position == 2){
                    startActivity(new Intent(getApplicationContext(), com.example.aditya.gvpattendance.Adjust_Attendance.class));
                    finish();

                }else if (position == 3){
                    startActivity(new Intent(getApplicationContext(), com.example.aditya.gvpattendance.AttendanceReport.class));
                    finish();
                }else if(position == 4){
                    Intent intent = new Intent(getApplicationContext(), com.example.aditya.gvpattendance.Manipulate_Attendance.class);
                    intent.putExtra("ManiSelect","1");
                    startActivity(intent);
                    finish();

                }else if(position == 5){
                    Intent intent = new Intent(getApplicationContext(), com.example.aditya.gvpattendance.Manipulate_Attendance.class);
                    intent.putExtra("ManiSelect","2");
                    startActivity(intent);
                    finish();
                }
            }
        });


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
                startActivity(new Intent(getApplicationContext(), com.example.aditya.gvpattendance.MainActivity.class));
                break;
            case R.id.backto:
                startActivity(new Intent(getApplicationContext(), com.example.aditya.gvpattendance.Main2Activity.class));
                break;

        }
        return  true;
    }
}
