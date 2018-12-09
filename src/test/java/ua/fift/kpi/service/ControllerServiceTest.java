package ua.fift.kpi.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ua.fift.kpi.domain.Order;
import ua.fift.kpi.domain.Room;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ControllerServiceTest {

    ControllerService service = new ControllerService();


    @Test
    public void countTheTotalPrice() throws ParseException {

        Order order = new Order(true, true, "09.12.2018", "12.12.2018", 0, null, null);
        Room room = new Room(30000, 15000, 10000);

        long totalPrice = service.countTheTotalPrice(order, room);

        Assert.assertEquals(165000, totalPrice);
    }

    @Test
    public void daysBetween() throws ParseException {
        Calendar since = new GregorianCalendar();
        Calendar to = new GregorianCalendar();

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

        Date date = sdf.parse("09.12.2018");
        since.setTime(date);
        date = sdf.parse("12.12.2018");
        to.setTime(date);

        int days = service.daysBetween(since.getTime(), to.getTime());

        Assert.assertEquals(3, days);
    }
}