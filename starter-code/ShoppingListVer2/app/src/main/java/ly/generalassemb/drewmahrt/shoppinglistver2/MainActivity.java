package ly.generalassemb.drewmahrt.shoppinglistver2;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.List;

import ly.generalassemb.drewmahrt.shoppinglistver2.setup.DBAssetHelper;

public class MainActivity extends AppCompatActivity {
    private GroceryListViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ignore the two lines below, they are for setup
        DBAssetHelper dbSetup = new DBAssetHelper(MainActivity.this);
        dbSetup.getReadableDatabase();

        RecyclerView groceries = (RecyclerView)findViewById(R.id.grocery_list);

        List<GroceryItem> groceryList = GrocerListDatabaseHelper.getInstance(this).getItemNames();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        groceries.setLayoutManager(layoutManager);
        mAdapter = new GroceryListViewAdapter(groceryList);
        groceries.setAdapter(mAdapter);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if(Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            List<GroceryItem> resultItems =
            GrocerListDatabaseHelper.getInstance(this).getItemsByName(query);
            mAdapter.replaceData(resultItems);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();

        ComponentName componentName = new ComponentName(this, MainActivity.class);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName));
        return true;
    }
}
