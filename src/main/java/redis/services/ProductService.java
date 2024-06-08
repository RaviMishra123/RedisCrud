package redis.services;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import redis.entity.Product;
import redis.repository.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@EnableCaching
public class ProductService {

    public static String item_deleted = "successfully deleted";
    @Autowired
    private ProductDao dao;

    public Product save(Product product){
        return dao.save(product);
    }

    public List<Product> getProducts(){
        return dao.findAll();
    }

    @Cacheable(key = "#id", value = ProductDao.hash_key)
    public Product getProductByIds(int id){
        return dao.findById(id);
    }

    @CacheEvict(key = "#id", value = ProductDao.hash_key)
    public String deleteByIds(int id){
        dao.deleteById(id);
        return item_deleted + id;
    }
}
