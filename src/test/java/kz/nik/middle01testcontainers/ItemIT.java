package kz.nik.middle01testcontainers;

import static org.junit.jupiter.api.Assertions.*;

import kz.nik.middle01testcontainers.api.ItemController;
import kz.nik.middle01testcontainers.model.Item;
import kz.nik.middle01testcontainers.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@Sql(scripts = {"classpath:/item/insert.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = {"classpath:/item/clean.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_CLASS)
public class ItemIT extends AbstractTestIT {

    @Autowired
    private ItemController itemController;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void testItemCreate() {

        Item item = new Item();
        item.setName("XIAOMI REDMI NOTE 11");
        item.setAmount(40);
        item.setPrice(200);

        ResponseEntity<Item> response = itemController.createItem(item);
        assertNotNull(response.getBody());
        assertEquals(response.getBody().getAmount(), 40);
        assertEquals(response.getBody().getPrice(), 200);
        assertEquals(response.getBody().getName(), "XIAOMI REDMI NOTE 11");

    }

    @Test
    public void testGetAll() {
        Item item = new Item();
        item.setName("XIAOMI REDMI NOTE 11");
        item.setAmount(40);
        item.setPrice(200);
        itemController.createItem(item);

        Item item2 = new Item();
        item2.setName("XIAOMI REDMI NOTE 12");
        item2.setAmount(405);
        item2.setPrice(205);
        itemController.createItem(item2);

        List<Item> items = itemController.getItems();
        assertTrue(items.size() > 0);
    }
}