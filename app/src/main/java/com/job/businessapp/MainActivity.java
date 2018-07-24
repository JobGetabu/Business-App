package com.job.businessapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.google.gson.reflect.TypeToken;
import com.job.businessapp.model.ProductEntry;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.job.businessapp.AnimActivity.IMGURL;
import static com.job.businessapp.ProductWebActivity.PRODUCTURL;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    private RecyclerView recyclerView;
    private ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycleview_product);

        List<ProductEntry> productEntries = readProductList();
        Log.d(TAG, "onCreate: List"+productEntries.toString());
        ImageRequester imageRequester = ImageRequester.getInstance(this);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,getResources().getInteger(R.integer.grid_count_2));
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ProductAdapter(productEntries, imageRequester);
        recyclerView.setAdapter(adapter);
    }

    public ArrayList<ProductEntry> readProductList(){
        ArrayList<ProductEntry> test = null;
        InputStream stream = getApplicationContext().getResources().openRawResource(R.raw.product);
        Type productList = new TypeToken<ArrayList<ProductEntry>>(){}.getType();

        try {
            test = JsonReader.readJsonStream(stream,productList);
            Log.d(TAG, "readProductList: List is "+test);
            return test;
        } catch (IOException e) {
            Log.d(TAG, "readProductList: Error parsing json");
            e.printStackTrace();
            return new ArrayList<>();
        } catch (NullPointerException ex){
            Log.d(TAG, "readProductList: null :(");
            return test;
        }
    }

    public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

        private List<ProductEntry> productList;
        private ImageRequester imageRequester;

        public ProductAdapter(List<ProductEntry> productEntries, ImageRequester imageRequester) {
            this.productList = productEntries;
            this.imageRequester = imageRequester;
            Log.d(TAG, "ProductAdapter: list"+productEntries.toString());
        }

        @Override
        public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());
            View view = layoutInflater.inflate(R.layout.product_entry,parent, false);
            ProductViewHolder productViewHolder = new ProductViewHolder(view);
            return productViewHolder;
        }
        public String getProductImageRequester(List<ProductEntry>  productEntries, int position){
            if (productEntries.size() == 0){
                throw new IllegalArgumentException("Error no products opps");
            }
            for (int i = 0; i < productEntries.size(); i++) {
                if(i ==  position){
                    return productEntries.get(position).getImageUrl();
                }

            }
            return productEntries.get(position).getImageUrl();
        }

        @Override
        public void onBindViewHolder(ProductViewHolder holder, final int position) {
            //This is a much cleaner way of popularizing the recycle
            // holder.bind(productEntries.get(position),imageRequester);

            Log.d(TAG, "onBindViewHolder: value of product name +"+String.valueOf(productList.get(position).getProductName()));
            holder.textViewProductName.setText(String.valueOf(productList.get(position).getProductName()));
            holder.textViewPrice.setText(productList.get(position).getPrice());
            final String url = getProductImageRequester(productList, position);
            ImageRequester.setImagFromUrlr(holder.imageViewProduct, url);

            holder.imageViewProduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, ProductWebActivity.class);
                    intent.putExtra(PRODUCTURL, productList.get(position).getProduct_url());
                    startActivity(intent);
                }
            });

            holder.getTextViewProductMore().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(MainActivity.this, AnimActivity.class);
                    intent.putExtra(IMGURL, url);
                    startActivity(intent);

                }
            });
        }

        @Override
        public int getItemCount() {
            return productList.size();
        }

        public class ProductViewHolder extends RecyclerView.ViewHolder {

            TextView textViewPrice ;
            NetworkImageView imageViewProduct ;
            TextView textViewProductName ;
            TextView textViewProductMore ;

            public TextView getTextViewProductMore() {
                return textViewProductMore;
            }

            ImageButton imageButtonFav ;

            public ProductViewHolder(View itemView) {
                super(itemView);

                textViewPrice = (TextView) itemView.findViewById(R.id.price);
                imageViewProduct = (NetworkImageView) itemView.findViewById(R.id.product_image);
                textViewProductName = (TextView) itemView.findViewById(R.id.product_name);
                textViewProductMore = (TextView) itemView.findViewById(R.id.more);
                imageButtonFav = (ImageButton) itemView.findViewById(R.id.favorite_button);


            }

            //currently not implemented
            void bind(ProductEntry productEntry,ImageRequester imageRequester){

                //itemView.setTag(R.id.tag_product_entry,productEntry);
                imageViewProduct.setImageURI(Uri.parse(productEntry.getImageUrl()));
                textViewPrice.setText(productEntry.getPrice());
                textViewProductName.setText(productEntry.getProductName());

            }
        }
    }

}
