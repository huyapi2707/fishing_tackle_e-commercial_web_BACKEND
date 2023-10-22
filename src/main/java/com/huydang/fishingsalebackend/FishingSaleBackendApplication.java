package com.huydang.fishingsalebackend;
import com.huydang.fishingsalebackend.jwt.JwtService;
import com.huydang.fishingsalebackend.jwt.JwtToken;
import com.huydang.fishingsalebackend.jwt.TokenRepository;
import com.huydang.fishingsalebackend.product.Product;
import com.huydang.fishingsalebackend.product.ProductRepository;
import com.huydang.fishingsalebackend.product.brand.Brand;
import com.huydang.fishingsalebackend.product.brand.BrandRepository;
import com.huydang.fishingsalebackend.product.cart.Cart;
import com.huydang.fishingsalebackend.product.cart.CartRepository;
import com.huydang.fishingsalebackend.product.categories.Category;
import com.huydang.fishingsalebackend.product.categories.CategoryRepository;
import com.huydang.fishingsalebackend.product.description.Description;
import com.huydang.fishingsalebackend.product.description.DescriptionRepository;
import com.huydang.fishingsalebackend.product.imageSrc.ImageSrc;
import com.huydang.fishingsalebackend.product.imageSrc.ImageSrcRepository;
import com.huydang.fishingsalebackend.product.subCategory.SubCategory;
import com.huydang.fishingsalebackend.product.subCategory.SubCategoryRepository;
import com.huydang.fishingsalebackend.product.type.Type;
import com.huydang.fishingsalebackend.product.type.TypeRepository;
import com.huydang.fishingsalebackend.user.User;
import com.huydang.fishingsalebackend.user.UserRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@SpringBootApplication
public class FishingSaleBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(FishingSaleBackendApplication.class, args);
    }
//
//    @Bean
//    CommandLineRunner runner(CategoryRepository categoryRepository,
//                             SubCategoryRepository subCategoryRepository,
//                             ProductRepository productRepository,
//                             DescriptionRepository descriptionRepository,
//                             ImageSrcRepository imageSrcRepository,
//                             TypeRepository typeRepository,
//                             BrandRepository brandRepository
//    ) {
//        return args -> {
//            JSONParser parser = new JSONParser();
//            try (FileReader reader = new FileReader("D:\\Code\\FishingSale\\FishingSale_BE\\fishing-sale-backend\\src\\main\\resources\\static\\fishing_data\\data.json")) {
//                JSONArray productList = (JSONArray) parser.parse(reader);
//                for (Object o :  productList) {
//
//                    JSONObject product = (JSONObject) o;
//                    String category = (String) product.get("category");
//                    String subCategory = (String) product.get("sub_category");
//                    String name =  (String) product.get("name");
//                    String brand = (String) product.get("brand");
//                    JSONArray prices = (JSONArray) product.get("prices");
//                    JSONArray types = (JSONArray) product.get("types");
//                    JSONArray imagesSrc = (JSONArray) product.get("image_src");
//                    JSONArray descriptions = (JSONArray) product.get("descriptions");
//
//                    Category thisCategory;
//                    Optional<Category> optionalCategory = categoryRepository.findByName(category.toLowerCase().trim());
//                    if (optionalCategory.isPresent()) {
//                        thisCategory = optionalCategory.get();
//                    }
//                    else {
//                        thisCategory = new Category();
//                        thisCategory.setName(category.toLowerCase().trim());
//                        categoryRepository.save(thisCategory);
//                    }
//
//                    SubCategory thisSubCategory;
//                    Optional<SubCategory> optionalSubCategory = null;
//                    if (subCategory.equals("")) {
//                        optionalSubCategory = subCategoryRepository.findByName("none");
//                    }
//                    else {
//                        optionalSubCategory = subCategoryRepository.findByName(subCategory.toLowerCase().trim());
//                    }
//
//                    if (optionalSubCategory.isPresent()) {
//                        thisSubCategory = optionalSubCategory.get();
//                    }
//                    else {
//                        thisSubCategory = new SubCategory();
//                        if (subCategory.equals("")) {
//                            thisSubCategory.setName("none");
//                        }
//                        else {
//                            thisSubCategory.setName(subCategory.toLowerCase().trim());
//                        }
//
//                        thisSubCategory.setCategory(thisCategory);
//                        subCategoryRepository.save(thisSubCategory);
//                    }
//
//                    Brand thisBrand;
//                    Optional<Brand> optionalBrand = null;
//                    if (brand.equals("")) {
//                        optionalBrand = brandRepository.findByName("none");
//                    }
//                    else {
//                        optionalBrand = brandRepository.findByName(brand.toLowerCase().trim());
//                    }
//                    if (optionalBrand.isPresent()) {
//                        thisBrand = optionalBrand.get();
//                    }
//                    else {
//                            thisBrand = new Brand();
//                            if (brand.equals(""))
//                            {
//                                thisBrand.setName("none");
//                            }
//                            else {
//                                thisBrand.setName(brand.toLowerCase().trim());
//                            }
//                            brandRepository.save(thisBrand);
//                    }
//                    Product thisProduct = new Product();
//                    thisProduct.setName(name.toLowerCase().trim());
//                    thisProduct.setSubtype(thisSubCategory);
//                        thisProduct.setBrand(thisBrand);
//
//
//                    productRepository.save(thisProduct);
//
//                    for (Object src : imagesSrc) {
//                        ImageSrc newSrc = new ImageSrc();
//                        newSrc.setSrc("fishing_data\\" + (String) src);
//                        newSrc.setProduct(thisProduct);
//                        imageSrcRepository.save(newSrc);
//                    }
//
//                    for (Object desc : descriptions) {
//                        String description = (String) desc;
//                        Description newDescription = new Description();
//                        newDescription.setContent(description.toLowerCase().trim());
//                        newDescription.setProduct(thisProduct);
//                        descriptionRepository.save(newDescription);
//                    }
//
//                    List<Long> longPrices = new ArrayList<>();
//                    for (Object price : prices) {
//                        String thisPrice = ((String) price).trim();
//                        thisPrice = thisPrice.substring(0, thisPrice.indexOf("V"));
//                        while (thisPrice.indexOf(',') > 0) {
//                            thisPrice = thisPrice.substring(0, thisPrice.indexOf(',')) + thisPrice.substring(thisPrice.indexOf(',') + 1);
//                        }
//                        longPrices.add(Long.valueOf(thisPrice));
//                    }
//                    if (types.size() != 0) {
//                        int step = 0;
//                        if (longPrices.size() > 1) {
//                            step = (longPrices.get(1).intValue() - longPrices.get(0).intValue()) / (types.size() - 1);
//                        }
//                            for (int i = 0; i < types.size(); i++) {
//                                Type newType = new Type();
//                                newType.setType(((String)types.get(i)).toLowerCase());
//                                newType.setPrice(Long.valueOf(longPrices.get(0).longValue() + (step * i)));
//                                newType.setQuantity(Long.valueOf(7));
//                                newType.setProduct(thisProduct);
//                                typeRepository.save(newType);
//                            }
//                    }
//                    else {
//                        Type newType = new Type();
//                        newType.setType(name);
//                        newType.setPrice(longPrices.get(0));
//                        newType.setQuantity(Long.valueOf(7));
//                        newType.setProduct(thisProduct);
//                        typeRepository.save(newType);
//                    }
//                }
//                System.out.println("Done");
//            }
//            catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//            catch (ParseException e) {
//                e.printStackTrace();
//            }
//        };
//
//    }

    @Bean
    CommandLineRunner jwtCleanRunner(TokenRepository repository, JwtService service) {
        return args -> {
            System.out.println("Cleaning all expired tokens...");
            List<JwtToken> tokens = repository.findAll();
            tokens.stream().forEach(token -> {
                if (token.isExpired()) {
                    repository.delete(token);
                }
                else if (service.isTokenExpired(token.getToken())) {
                    repository.delete(token);
                }
            });
            System.out.println("Done...");
        };
    }

}
