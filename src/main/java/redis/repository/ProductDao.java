package redis.repository;

import redis.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDao {

    public static final String hash_key = "Product";
    public static final String Deleted_Item = "deleted successfully!";
    @Autowired
    RedisTemplate template;

    public Product save(Product product){
        template.opsForHash().put(hash_key, product.getId(), product);
        return product;
    }

    public List<Product> findAll(){
        return template.opsForHash().values(hash_key);
    }

    public Product findById(int id){
        System.out.println("DB call");
        return (Product) template.opsForHash().get(hash_key,id);
    }

    public String deleteById(int id){
        template.opsForHash().delete(hash_key,id);
        return Deleted_Item +" : "+id;
    }
}
