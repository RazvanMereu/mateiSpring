package com.example.matei;

import com.example.matei.entity.Order;
import com.example.matei.repository.OrderRepository;
import com.icoderman.woocommerce.ApiVersionType;
import com.icoderman.woocommerce.EndpointBaseType;
import com.icoderman.woocommerce.WooCommerce;
import com.icoderman.woocommerce.WooCommerceAPI;
import com.icoderman.woocommerce.oauth.OAuthConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class MateiApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MateiApplication.class, args);
    }

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void run(String... args) throws Exception {

         Order order1 = new Order("Ramesh", "Fadatare", "ramesh@gmail.com");
         orderRepository.save(order1);

         Order order2 = new Order("Sanjay", "Jadhav", "sanjay@gmail.com");
         orderRepository.save(order2);

         Order order3 = new Order("tony", "stark", "tony@gmail.com");
         orderRepository.save(order3);

         getAllOrders();


        System.out.println(orderRepository.findAll());
    }

    private void getAllOrders() {

        // Setup client
        OAuthConfig config = new OAuthConfig("https://petitmatei.ro", "ck_5e1a7eb311d125ed6ff558d9322b75d02b4054aa", "cs_c10198ba58c0d9450313e412a8bc143205d8ff4d");
        WooCommerce wooCommerce = new WooCommerceAPI(config, ApiVersionType.V3);
//
//        // Prepare object for request
//        Map<String, Object> productInfo = new HashMap<>();
//        productInfo.put("name", "Premium Quality");
//        productInfo.put("type", "simple");
//        productInfo.put("regular_price", "21.99");
//        productInfo.put("description", "Pellentesque habitant morbi tristique senectus et netus");
//
//        System.out.println(product.get("id"));

        // Get all with request parameters
        Map<String, String> params = new HashMap<>();
        params.put("per_page","1");
        params.put("offset","0");
        List orders = wooCommerce.getAll(EndpointBaseType.ORDERS.getValue(), params);

        System.out.println(orders.size());
        System.out.println(orders);
    }
}
