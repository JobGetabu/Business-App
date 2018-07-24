package com.job.businessapp.model;

/**
 * Created by JOB on 8/24/2017.
 */

public class ProductEntry {


    private String product_name;
    private String product_image_url;
    private String header_top_right_text;
    private String header_top_left_text;
    private String product_url;
    private String header_top_right_url;
    private String product_cta_text;
    private String price;


    public ProductEntry() {
    }

    public String getProductName() {
        return product_name;
    }

    public void setProductName(String productName) {
        this.product_name = productName;
    }

    public String getImageUrl() {
        return product_image_url;
    }

    public void setImageUrl(String imageUrl) {
        this.product_image_url = imageUrl;
    }

    public String getTxtShopAll() {
        return header_top_right_text;
    }

    public void setTxtShopAll(String txtShopAll) {
        this.header_top_right_text = txtShopAll;
    }

    public String getHeader_top_left_text() {
        return header_top_left_text;
    }

    public void setHeader_top_left_text(String header_top_left_text) {
        this.header_top_left_text = header_top_left_text;
    }

    public String getProduct_url() {
        return product_url;
    }

    public void setProduct_url(String product_url) {
        this.product_url = product_url;
    }

    public String getHeader_top_right_url() {
        return header_top_right_url;
    }

    public void setHeader_top_right_url(String header_top_right_url) {
        this.header_top_right_url = header_top_right_url;
    }

    public String getProduct_cta_text() {
        return product_cta_text;
    }

    public void setProduct_cta_text(String product_cta_text) {
        this.product_cta_text = product_cta_text;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ProductEntry{" +
                "productName='" + product_name + '\'' +
                ", imageUrl='" + product_image_url + '\'' +
                ", txtShopAll='" + header_top_right_text + '\'' +
                ", txtTagMen='" + header_top_left_text + '\'' +
                ", productUrl='" + product_url + '\'' +
                ", menProductsPage='" + header_top_right_url + '\'' +
                ", txtShopNow='" + product_cta_text + '\'' +
                ", price='" + price + '\'' +
                '}';
    }

    //    "product-name":"MEN'S BETTER THAN NAKED&trade; JACKET",
//            "product-image-url":"http://images.thenorthface.com/is/image/TheNorthFace/236x204_CLR/mens-better-than-naked-jacket-AVMH_LC9_hero.png",
//            "header-top-right-text":"Shop All",
//            "header-top-left-text":"Men's",
//            "product-url":"http://www.thenorthface.com/catalog/sc-gear/men-39-s-better-than-naked-8482-jacket.html",
//            "header-top-right-url":"http://www.thenorthface.com/en_US/shop-mens/",
//            "product-cta-text":"Shop Now",
//            "price":"$60.50"
}
