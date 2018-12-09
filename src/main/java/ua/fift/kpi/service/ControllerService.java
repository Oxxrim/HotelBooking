package ua.fift.kpi.service;

import ua.fift.kpi.domain.Order;
import ua.fift.kpi.domain.Room;
import ua.fift.kpi.repository.OrderRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class ControllerService {


    public List<Room> checkForUnavailable(Order order, OrderRepository orderRepository) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

        Iterable<Order> orders = orderRepository.findAll();

        List<Room> unavailable = new ArrayList<>();

        for (Order ordr : orders) {
            Date dateSince1 = sdf.parse(order.getSince());
            Date dateSince2 = sdf.parse(ordr.getSince());
            Date dateTo1 = sdf.parse(order.getTo());
            Date dateTo2 = sdf.parse(ordr.getTo());

            if (dateSince1.after(dateSince2) && dateSince1.before(dateTo2) || dateSince1.equals(dateSince2)){
                unavailable.add(ordr.getRoom());
            } else if (dateTo1.after(dateSince2) && dateTo1.before(dateTo2) || dateTo1.equals(dateTo2)){
                unavailable.add(ordr.getRoom());
            }
        }

        return unavailable;
    }

    public long countTheTotalPrice(Order order, Room room) throws ParseException {
        Calendar since = new GregorianCalendar();
        Calendar to = new GregorianCalendar();

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

        Date date = sdf.parse(order.getSince());
        since.setTime(date);
        date = sdf.parse(order.getTo());
        to.setTime(date);

        int days = daysBetween(since.getTime(), to.getTime());
        long totalPrice = room.getPrice() * days;

        if(order.isBreakfast()){
            totalPrice += room.getBreakfastCost() * days;
        }
        if(order.isCleaning()){
            totalPrice += room.getCleaningCost() * days;
        }

        return totalPrice;
    }

    public int daysBetween(Date d1, Date d2){
        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }
}
