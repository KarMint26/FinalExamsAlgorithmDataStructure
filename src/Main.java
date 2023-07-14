import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    public static void selectionSort(ModelProduct[] x){
        int n = x.length;
        for (int i = 0; i < n-1; i++) {
            int minIndex = i;
            for (int j = i+1; j < n; j++) {
                if(x[j].getRating() < x[minIndex].getRating()){
                    minIndex = j;
                }
            }
            ModelProduct temp = x[minIndex];
            x[minIndex] = x[i];
            x[i] = temp;
        }
    }
    public static void main(String[] args) throws IOException {
        ModelProduct[] arrProduct;
        GetDataFromURL getDataFromURL = new GetDataFromURL();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(getDataFromURL.getData());
        JsonNode jsonArray = jsonNode.get("products");

        ArrayList<ModelProduct> modelProducts = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            ModelProduct modelProduct = new ModelProduct();
            modelProduct.setId(jsonArray.get(i).get("id").intValue());
            modelProduct.setTitle(String.valueOf(jsonArray.get(i).get("title")));
            modelProduct.setDescription(String.valueOf(jsonArray.get(i).get("description")));
            modelProduct.setPrice(jsonArray.get(i).get("price").intValue());
            modelProduct.setDiscountPercentage(jsonArray.get(i).get("discountPercentage").doubleValue());
            modelProduct.setRating(jsonArray.get(i).get("rating").doubleValue());
            modelProduct.setStock(jsonArray.get(i).get("stock").intValue());
            modelProduct.setBrand(String.valueOf(jsonArray.get(i).get("brand")));
            modelProduct.setCategory(String.valueOf(jsonArray.get(i).get("category")));
            modelProduct.setThumbnail(String.valueOf(jsonArray.get(i).get("thumbnail")));
            modelProduct.setImages(new String[]{String.valueOf(jsonArray.get(i).get("images"))});
            modelProducts.add(modelProduct);
        }

        arrProduct = new ModelProduct[modelProducts.size()];
        for (int i = 0; i < modelProducts.size(); i++) {
            arrProduct[i] = modelProducts.get(i);
        }

        selectionSort(arrProduct);
        for(ModelProduct res : arrProduct){
            System.out.println("Id : " + res.getId());
            System.out.println("Title : " + res.getTitle());
            System.out.println("Description : " + res.getDescription());
            System.out.println("Price : " + res.getPrice());
            System.out.println("Discount Percentage : " + res.getDiscountPercentage());
            System.out.println("Rating : " + res.getRating());
            System.out.println("Stock : " + res.getStock());
            System.out.println("Brand : " + res.getBrand());
            System.out.println("Category : " + res.getCategory());
            System.out.println("Thumbnail : " + res.getThumbnail());
            System.out.println("Images : " + Arrays.toString(res.getImages()));
            System.out.println(" ");
        }
    }
}
