import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static java.util.Arrays.sort;

class AviaSoulsTest {

    Ticket ticket1 = new Ticket(
            "Аэропорт 1",
            "Аэропорт 2",
            20000,
            8,
            12);

    Ticket ticket2 = new Ticket(
            "Аэропорт 1",
            "Аэропорт 2",
            15000,
            9,
            13);

    Ticket ticket3 = new Ticket(
            "Аэропорт 1",
            "Аэропорт 2",
            35000,
            11,
            17);

    Ticket ticket4 = new Ticket(
            "Аэропорт 1",
            "Аэропорт 2",
            17000,
            10,
            15);

    Ticket ticket5 = new Ticket(
            "Аэропорт 1",
            "Аэропорт 2",
            19000,
            12,
            16);

    Ticket ticket6 = new Ticket(
            "Аэропорт 2",
            "Аэропорт 1",
            20000,
            14,
            18);

    @Test
    void compareToTicketPrice() {

        AviaSouls souls = new AviaSouls();

        souls.add(ticket1);
        souls.add(ticket2);

        int expected1 = -1;
        int expected2 = 1;
        int expected3 = 0;

        int actual1 = ticket2.compareTo(ticket1);
        int actual2 = ticket1.compareTo(ticket2);
        int actual3 = ticket1.compareTo(ticket6);

        Assertions.assertEquals(expected1, actual1);
        Assertions.assertEquals(expected2, actual2);
        Assertions.assertEquals(expected3, actual3);
    }

    @Test
    void shouldSearchWithSort() {

        AviaSouls souls = new AviaSouls();

        souls.add(ticket1);
        souls.add(ticket2);
        souls.add(ticket3);
        souls.add(ticket4);
        souls.add(ticket5);
        souls.add(ticket6);

        Ticket[] expected = {ticket2, ticket4, ticket5, ticket1, ticket3};

        Ticket[] actual = souls.search("Аэропорт 1", "Аэропорт 2");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void shouldCompareByTime() {

        TicketTimeComparator timeComparator = new TicketTimeComparator();
        AviaSouls souls = new AviaSouls();

        souls.add(ticket1);
        souls.add(ticket2);
        souls.add(ticket3);
        souls.add(ticket4);
        souls.add(ticket5);

        Ticket[] actual = {ticket1, ticket2, ticket5, ticket4, ticket3};

        Ticket[] tickets = souls.findAll();
        sort(tickets, timeComparator);
        Ticket[] expected = tickets;

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void shouldCompareWithComparator() {
        TicketTimeComparator timeComparator = new TicketTimeComparator();

        AviaSouls souls = new AviaSouls();

        souls.add(ticket1);
        souls.add(ticket2);
        souls.add(ticket3);
        souls.add(ticket4);
        souls.add(ticket5);
        souls.add(ticket6);

        Ticket[] actual = {ticket1, ticket2, ticket5, ticket4, ticket3};

        Ticket[] expected = souls.searchAndSortBy("Аэропорт 1", "Аэропорт 2", timeComparator);

        Assertions.assertArrayEquals(expected, actual);
    }
}