package kwan.org.webloginchatdemo;

import kwan.org.webloginchatdemo.repo.custom.CustomUserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

/**
 * Created by https://github.com/kwanpham
 */
@SpringBootTest
public class TestMapperNativeQuerry {
   @Autowired
    CustomUserRepo customUserRepo;
    @Test
    public void testJpaMappingNativeQuery() {
        customUserRepo.selectQuerryNativeMapDto().forEach(System.out::println);
    }
}
