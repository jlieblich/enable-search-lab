package ly.generalassemb.drewmahrt.shoppinglistver2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonlieblich on 10/25/16.
 */

public class GrocerListDatabaseHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "SHOPPING_LIST";
    public static final String DB_NAME = "SHOPPING_DB";
    public static final int VERSION_NO = 7;

    public static final String COL_ID = "id";
    public static final String COL_NAME = "ITEM_NAME";
    public static final String COL_DESC = "DESCRIPTION";
    public static final String COL_PRICE = "PRICE";
    public static final String COL_TYPE = "TYPE";

    public static final String CREATE_GROCERY_TABLE =
            "CREATE TABLE "+TABLE_NAME+" ("
            +COL_ID+" INTEGER PRIMARY KEY,"
            +COL_NAME+" TEXT,"
            +COL_DESC+" TEXT,"
            +COL_PRICE+" TEXT,"
            +COL_TYPE+" TEXT)";

    private static GrocerListDatabaseHelper mInstance;



    public static GrocerListDatabaseHelper getInstance(Context context) {
        if(mInstance == null) {
            mInstance = new GrocerListDatabaseHelper(context.getApplicationContext());
        }
        return mInstance;
    }

    private GrocerListDatabaseHelper(Context context) {
        super(context, DB_NAME, null, VERSION_NO);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_GROCERY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    public List<GroceryItem> getItemNames() {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME,
                new String[]{COL_NAME,COL_DESC,COL_PRICE,COL_TYPE},
                null,null,null,null,null);

        List<GroceryItem> groceryList = new ArrayList<>();

        if(cursor.moveToFirst()) {
            while(!cursor.isAfterLast()) {
                String name = cursor.getString(cursor.getColumnIndex(COL_NAME));
                String description = cursor.getString(cursor.getColumnIndex(COL_DESC));
                String price = cursor.getString(cursor.getColumnIndex(COL_PRICE));
                String type = cursor.getString(cursor.getColumnIndex(COL_TYPE));
                GroceryItem item = new GroceryItem(name, description, type, price);
                groceryList.add(item);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return groceryList;
    }

    public List<GroceryItem> getItemsByName(String query) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,
                null,
                COL_NAME+" LIKE ?",
                new String[]{"%"+query+"%"},
                null,
                null,
                null);

        if(query.equalsIgnoreCase("all")) {
            return getItemNames();
        } else {
            List<GroceryItem> groceryList = new ArrayList<>();
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    String name = cursor.getString(cursor.getColumnIndex(COL_NAME));
                    String desc = cursor.getString(cursor.getColumnIndex(COL_DESC));
                    String price = cursor.getString(cursor.getColumnIndex(COL_PRICE));
                    String type = cursor.getString(cursor.getColumnIndex(COL_TYPE));
                    GroceryItem g = new GroceryItem(name, desc, type, price);
                    groceryList.add(g);

                    cursor.moveToNext();
                }
            }
            cursor.close();
            return groceryList;
        }
    }
}
