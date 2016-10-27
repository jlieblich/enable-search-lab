package ly.generalassemb.drewmahrt.shoppinglistver2;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by jonlieblich on 10/25/16.
 */

public class GroceryListViewHolder extends RecyclerView.ViewHolder {
    TextView mName, mDescription, mType, mPrice;

    public GroceryListViewHolder(View itemView) {
        super(itemView);

        mName = (TextView)itemView.findViewById(R.id.name);
        mDescription = (TextView)itemView.findViewById(R.id.description);
        mType = (TextView)itemView.findViewById(R.id.type);
        mPrice = (TextView)itemView.findViewById(R.id.price);
    }
}
