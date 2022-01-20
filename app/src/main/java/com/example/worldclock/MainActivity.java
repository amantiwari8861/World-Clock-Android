package com.example.worldclock;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> arr=new ArrayList<>();
        Set<String> timezones=ZoneId.getAvailableZoneIds();
        timezones
                .stream().sorted()
                .forEach(tz->{
            ZonedDateTime obj= ZonedDateTime.now(ZoneId.of(tz));
            arr.add(tz+" :"+obj.getHour()+":"+obj.getMinute()+":"+obj.getSecond()+" "+obj.getDayOfMonth()+"/"+obj.getMonthValue()+"/"+obj.getYear());
        });
        ArrayList<MyListData> myListData=new ArrayList<>();
        for (int i = 0; i < arr.size(); i++)
        {
            myListData.add(new MyListData(arr.get(i), android.R.drawable.ic_dialog_email));
        }

//                new MyListData("Info", android.R.drawable.ic_dialog_info),
//                new MyListData("Delete", android.R.drawable.ic_delete),
//                new MyListData("Dialer", android.R.drawable.ic_dialog_dialer),
//                new MyListData("Alert", android.R.drawable.ic_dialog_alert),
//                new MyListData("Map", android.R.drawable.ic_dialog_map),
//                new MyListData("Email", android.R.drawable.ic_dialog_email),
//                new MyListData("Info", android.R.drawable.ic_dialog_info),
//                new MyListData("Delete", android.R.drawable.ic_delete),
//                new MyListData("Dialer", android.R.drawable.ic_dialog_dialer),
//                new MyListData("Alert", android.R.drawable.ic_dialog_alert),
//                new MyListData("Map", android.R.drawable.ic_dialog_map),

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        MyListAdapter adapter = new MyListAdapter(myListData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}