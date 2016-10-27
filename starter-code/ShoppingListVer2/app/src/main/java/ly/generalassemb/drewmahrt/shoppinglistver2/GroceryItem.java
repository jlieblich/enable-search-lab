package ly.generalassemb.drewmahrt.shoppinglistver2;

/**
 * Created by jonlieblich on 10/25/16.
 */

public class GroceryItem {
    private String mName, mDescription, mType, mPrice;

    public GroceryItem(String name, String description, String type, String price) {
        mName = name;
        mDescription = description;
        mType = type;
        mPrice = price;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public String getPrice() {
        return mPrice;
    }

    public void setmPrice(String price) {
        mPrice = price;
    }
}
