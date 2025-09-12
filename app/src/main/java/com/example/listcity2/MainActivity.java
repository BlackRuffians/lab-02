package com.example.listcity2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> dataList;
    ArrayAdapter<String> cityAdapter;
    ListView cityList;
    EditText inputtext;

    int selectedcity= -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.cityList);
        inputtext= findViewById(R.id.inputCity);

        // Start with some default cities
        dataList = new ArrayList<>();
        dataList.add("Edmonton");
        dataList.add("Calgary");

        // Adapter connects data → content.xml
        cityAdapter = new ArrayAdapter<>(this, R.layout.content, R.id.cityText, dataList);
        cityList.setAdapter(cityAdapter);

        Button addCityBtn = findViewById(R.id.addCityButton);
        Button deleteCityBtn = findViewById(R.id.deleteCityButton);

        // Add city
        addCityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String useraddedcity= inputtext.getText().toString().trim();
                if (!useraddedcity.isEmpty()){
                dataList.add(useraddedcity);
                cityAdapter.notifyDataSetChanged();
                inputtext.setText("");
                Toast.makeText(MainActivity.this, "City Added!", Toast.LENGTH_SHORT).show();
                }
                else {Toast.makeText(MainActivity.this, "City not added!", Toast.LENGTH_SHORT).show();}
            }
        });

        cityList.setOnItemClickListener((android.widget.AdapterView<?> parent, View view, int position, long id) -> {
            selectedcity = position;
            Toast.makeText(MainActivity.this, "Selected: " + dataList.get(position), Toast.LENGTH_SHORT).show();
        });


        // Delete city
        deleteCityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!dataList.isEmpty()) {
                    dataList.remove(selectedcity);
                    cityAdapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "City Deleted!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
