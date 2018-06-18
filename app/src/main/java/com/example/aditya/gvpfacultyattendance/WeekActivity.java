package com.example.aditya.gvpfacultyattendance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class WeekActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<MainPageContents> contentsList;
    ContentsAdapter contentsAdapter;
    DataTimeHandler dataTimeHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week);

        dataTimeHandler = new DataTimeHandler();
        getSupportActionBar().setTitle(dataTimeHandler.getActionBarDate());
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        contentsList = new ArrayList<>();
        //adding some items to our list
        contentsList.add(
                new MainPageContents(
                        "Monday",
                        "Today is Monday. Don’t forget to be awesome.",
                        R.drawable.mon));

        contentsList.add(
                new MainPageContents(
                        "Tuesday",
                        "This Tuesday, choose to make a difference. Every little action counts.",
                        R.drawable.tue));

        contentsList.add(
                new MainPageContents(
                        "Wednesday",
                        "Today is Wednesday, which means that we are halfway to the weekend.",
                        R.drawable.wed));

        contentsList.add(
                new MainPageContents(
                        "Thusrday",
                        "May your Thursday be as sweet as you are.",
                        R.drawable.thu));

        contentsList.add(
                new MainPageContents(
                        "Friday",
                        " Today is Friday. That means that Sunday is almost here!.",
                        R.drawable.fri));

        contentsList.add(
                new MainPageContents(
                        "Saturday",
                        "Have a Wacky Saturday! Don’t forget to smile and laugh once in a while..",
                        R.drawable.sat));



        contentsAdapter = new ContentsAdapter(this, contentsList,0);

        //setting adapter to recyclerview
        recyclerView.setAdapter(contentsAdapter);
        contentsAdapter.setItemClickListener(new ContentsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //Toast.makeText(getApplicationContext(),contentsList.get(position).getTitle(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), SubjectActivity.class);
                intent.putExtra("weekname",contentsList.get(position).getTitle().substring(0,3));
                intent.putExtra("activityno","Week");
                startActivity(intent);
                finish();
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
                startActivity(new Intent(getApplicationContext(), Login.class));
                break;
            case R.id.backto:
                startActivity(new Intent(getApplicationContext(), HomeMenu.class));
                finish();

            case R.id.hometo:
                startActivity(new Intent(getApplicationContext(), HomeMenu.class));
                finish();

        }
        return  true;

    }
}
