package ly.generalassemb.drewmahrt.shoppinglistver2;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by jonlieblich on 10/25/16.
 */

public class GroceryListViewAdapter extends RecyclerView.Adapter<GroceryListViewHolder>{
    List<GroceryItem> mGroceryList;

    public GroceryListViewAdapter(List<GroceryItem> list) {
        mGroceryList = list;
    }

    @Override
    public GroceryListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View parentView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grocery_item,parent, false);
        GroceryListViewHolder viewHolder = new GroceryListViewHolder(parentView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GroceryListViewHolder holder, int position) {
        holder.mName.setText(mGroceryList.get(position).getName());
        holder.mDescription.setText(mGroceryList.get(position).getDescription());
        holder.mPrice.setText(String.valueOf(mGroceryList.get(position).getPrice()));
        holder.mType.setText(mGroceryList.get(position).getType());
    }

    @Override
    public int getItemCount() {
        return mGroceryList.size();
    }

    public void replaceData(List<GroceryItem> newList) {
        mGroceryList = newList;
        notifyDataSetChanged();
    }
}
