package com.avans.easypaykassa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import com.avans.easypaykassa.DomainModel.Product;

import java.util.ArrayList;


public class AlterProductAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<Product> productsList;
    private ArrayList<ArrayList<Product>> products = new ArrayList<>();
    //protected ArrayList<Product> chosenProducts = new ArrayList<>();

    private ProductsTotal.OnTotalChanged listener;

    private ProductsTotal total;

    public AlterProductAdapter(ProductsTotal.OnTotalChanged listener, Context context, LayoutInflater layoutInflater, ArrayList<Product> productsList) {
        this.context = context;
        this.layoutInflater = layoutInflater;
        this.productsList = productsList;
        this.listener = listener;
    System.out.println("prodList size: "+productsList.size());
        for (int i = 0; i < productsList.size(); i++) {

            products.add(new ArrayList<Product>());
        }
    }
    public AlterProductAdapter(Context context, LayoutInflater layoutInflater, ArrayList<Product> productsList) {
        this.context = context;
        this.layoutInflater = layoutInflater;
        this.productsList = productsList;

    }

    @Override
    public int getCount() {
        int size = productsList.size();
        return size;
    }

    @Override
    public Object getItem(int position) {
        return productsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;

        if(convertView == null) {
            convertView = layoutInflater.inflate(R.layout.listview_product_row, null);

            viewHolder = new ViewHolder();
            viewHolder.productImage = (ImageView) convertView.findViewById(R.id.product_image);
            viewHolder.productName = (TextView) convertView.findViewById(R.id.product_name);
            viewHolder.productPrice = (TextView) convertView.findViewById(R.id.product_price);

            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //placeholder code
        final Product p = productsList.get(position);
        viewHolder.productImage.setImageResource(R.drawable.ic_local_dining_black_24dp);


            viewHolder.productName.setText(p.getProductName());
            viewHolder.productPrice.setText("€" + p.getProductPrice());

//        viewHolder.productName.setText("Product Name123");
//        viewHolder.productPrice.setText("€1,200");

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context,
                R.array.spinner_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        viewHolder.productSpinner.setAdapter(adapter);
        viewHolder.productSpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position2, long id) {

                int spinnerValue = Integer.parseInt(viewHolder.productSpinner.getSelectedItem().toString());

                ArrayList<Product> chosenProducts = new ArrayList<Product>();

                for (int i = 0; i < spinnerValue; i++) {

                    chosenProducts.add(p);

                }
                if(products.size() > position)
                    products.set(position, chosenProducts);
//                System.out.println(" " + chosenProducts.size());
//                Log.i("TAG","total products " + chosenProducts.size() +" "+products.size()+ " "+total.getPriceTotal()+" "+total.getTotal());
//                listener.onTotalChanged(total.getPriceTotal(), total.getTotal(), products);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return convertView;
    }


    private static class ViewHolder {
       private ImageView productImage;
        private TextView productName, productPrice;
        private Spinner productSpinner;

    }
}


