package com.example.aditya.gvpattendance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AttendanceReport extends AppCompatActivity {

    RecyclerView recyclerView;
    List<MainPageContents> contentsList;
    com.example.aditya.gvpattendance.ContentsAdapter contentsAdapter;
    com.example.aditya.gvpattendance.DataTimeHandler dataTimeHandler;
    public void PageOne(View view){

        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        if(!SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
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
                new MainPageContents(
                        "My Subjects",
                        "Attendance report for your subjects.",
                        R.drawable.mysubjects));

        contentsList.add(
                new MainPageContents(
                        "Overall Report",
                        "Overall attendance of students.",
                        R.drawable.overall));


        contentsAdapter = new com.example.aditya.gvpattendance.ContentsAdapter(this, contentsList,0);

        //setting adapter to recyclerview
        recyclerView.setAdapter(contentsAdapter);

        contentsAdapter.setItemClickListener(new com.example.aditya.gvpattendance.ContentsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if(position == 0) {
                    Intent intent = new Intent(getApplicationContext(), com.example.aditya.gvpattendance.MySubject.class);
                    intent.putExtra("value","two");
                    startActivity(intent);
                    finish();

                }
                else if(position == 1){
                    Toast.makeText(getApplicationContext(), contentsList.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), com.example.aditya.gvpattendance.AttendancePercent.class));
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
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                break;
            case R.id.backto:
                startActivity(new Intent(getApplicationContext(), AttendanceMenu.class));
                break;

        }
        return  true;
    }
}
