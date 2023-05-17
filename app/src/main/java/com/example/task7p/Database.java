package com.example.task7p;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.task7p.Item;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "LostAndFound";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_ITEMS = "Items";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DESC = "description";
    private static final String KEY_DATE = "date";
    private static final String KEY_LOCATION = "location";
    private static final String KEY_PHONE = "phone";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ITEMS_TABLE = "CREATE TABLE " + TABLE_ITEMS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_DESC + " TEXT," + KEY_DATE + " TEXT,"
                + KEY_LOCATION + " TEXT," + KEY_PHONE + " TEXT" + ")";
        db.execSQL(CREATE_ITEMS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS);
        onCreate(db);
    }

    // Add an Item to the database
    public void addItem(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, item.getName());
        values.put(KEY_DESC, item.getDescription());
        values.put(KEY_DATE, item.getDate());
        values.put(KEY_LOCATION, item.getLocation());
        values.put(KEY_PHONE, item.getPhone());
        db.insert(TABLE_ITEMS, null, values);
        db.close();
    }

    public void deleteItem(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ITEMS, KEY_ID + " = ?", new String[] { String.valueOf(id) });
        db.close();
    }


    // Fetch an Item from the database
    public Item fetchItem(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_ITEMS, new String[] { KEY_ID, KEY_NAME, KEY_DESC, KEY_DATE, KEY_LOCATION, KEY_PHONE }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Item item = new Item();
        item.setId(cursor.getInt(0));
        item.setName(cursor.getString(1));
        item.setDescription(cursor.getString(2));
        item.setDate(cursor.getString(3));
        item.setLocation(cursor.getString(4));
        item.setPhone(cursor.getString(5));

        cursor.close();
        return item;
    }

    // Fetch all Items from the database
    public List<Item> fetchAllItems() {
        List<Item> itemList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_ITEMS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Item item = new Item();
                item.setId(cursor.getInt(0));
                item.setName(cursor.getString(1));
                item.setDescription(cursor.getString(2));
                item.setDate(cursor.getString(3));
                item.setLocation(cursor.getString(4));
                item.setPhone(cursor.getString(5));
                itemList.add(item);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return itemList;
    }
}
