package com.example.task7p;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.task7p.Item;



public class ItemDetails extends AppCompatActivity {

    TextView ItemName,Phone,Description,Location,Date;
    int id;
    Database Database;
    Item item;
    Button Delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        ItemName = findViewById(R.id.Name);
        Date = findViewById(R.id.Date);
        Description = findViewById(R.id.Description);
        Location = findViewById(R.id.Location);
        Phone = findViewById(R.id.Phone);

        Intent i = getIntent();
        id = i.getIntExtra("item id",0);

        Database = new Database(this);
        item = Database.fetchItem(id);

        ItemName.setText(item.getName());
        Phone.setText(item.getPhone());
        Description.setText(item.getDescription());
        Date.setText(item.getDate());
        Location.setText(item.getLocation());

        Delete = findViewById(R.id.Delete);
        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Database.deleteItem(id);
                Intent i = new Intent(getApplicationContext(),ShowItems.class);
                startActivity(i);
                finish();
            }
        });

    }
}