package com.example.demoapp.sale;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.demoapp.R;
import com.example.demoapp.professional.ActivityPriceList;

public class TablePriceContainer extends AppCompatActivity {
    String[] items = {"Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7",
            "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"};

    AutoCompleteTextView autoCompleteTextView;

    ArrayAdapter<String> adapterItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tableprice_container);

        autoCompleteTextView = findViewById(R.id.auto_complete_txt);

        adapterItems = new ArrayAdapter<String>(getApplicationContext(), R.layout.dropdown_item, items);

        autoCompleteTextView.setAdapter(adapterItems);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                if (item.equals("Tháng 1")) {
                    Intent intent = new Intent(view.getContext(), ActivityPriceList.class);
                    startActivity(intent);
                }
            }
        });

    }
}
