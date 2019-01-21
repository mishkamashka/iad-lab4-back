package se.ifmo.ru;

import com.github.javafaker.Faker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import se.ifmo.ru.domain.dao.PointRepository;
import se.ifmo.ru.domain.model.Point;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class is designed for filling the database
 *
 * @author Raila Martin
 * @version 0.1
 * @since 0.1
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringTestApplication.class)
public class FillPointsDatabase {

    private static final int RECORDS_COUNT = 10;

    @Autowired
    private PointRepository pointRepository;

    @Test
    @Transactional
    @Commit
    public void fillDb() {
        for (int i = 0; i < RECORDS_COUNT; i++) {
            Faker faker = new Faker();
            pointRepository.save(new Point(
                    Double.valueOf(faker.commerce().price(-2, 2)),
                    Double.valueOf(faker.commerce().price(-3, 5)),
                    Double.valueOf(faker.commerce().price(0.5, 2))
            ));
        }
    }

    public static String generateUsername() {
        ArrayList<Character> characters = new ArrayList<>();
        for (char ch : "FbzRvAt9oH2Zv5NO8WL7zk6J1Bbe4UwP13".toCharArray()) {
            characters.add(ch);
        }
        Collections.shuffle(characters);

        StringBuilder builder = new StringBuilder(characters.size());
        for (Character ch : characters) {
            builder.append(ch);
        }
        return builder.toString().substring(1, 29);
    }

}
