package com.example.task7p;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import com.google.android.material.radiobutton.MaterialRadioButton;

public class Advert extends AppCompatActivity {

    EditText NameItem, PhoneItem, DescItem, DateItem, LocationItem;
    Button SaveItem;
    MaterialRadioButton Lost, Found;
    Database Database;
    String checkedStatus = "", item_name, item_phone, item_desc, item_date, item_location;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advert);

        NameItem = findViewById(R.id.itemname);
        PhoneItem = findViewById(R.id.phone);
        DescItem = findViewById(R.id.description);
        DateItem = findViewById(R.id.date);
        LocationItem = findViewById(R.id.location);
        SaveItem = findViewById(R.id.save);
        Lost = findViewById(R.id.lost);
        Found = findViewById(R.id.found);
        Database = new Database(this);

        SaveItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item_name = NameItem.getText().toString();
                item_location = LocationItem.getText().toString();
                item_date = DateItem.getText().toString();
                item_desc = DescItem.getText().toString();
                item_phone = PhoneItem.getText().toString();

                Item i = new Item();
                i.setDate(item_date);
                i.setDescription(item_desc);
                i.setLocation(item_location);
                i.setName(item_name);
                i.setPhone(item_phone);
                i.setLost(checkedStatus);

                Database.addItem(i);
                finish();
            }
        });
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        if (view.getId() == R.id.lost) {
            if (checked)
                checkedStatus = "lost";
        } else if (view.getId() == R.id.found) {
            if (checked)
                checkedStatus = "found";
        }
    }
}
