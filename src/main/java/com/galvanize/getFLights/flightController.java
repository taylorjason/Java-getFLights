package com.galvanize.getFLights;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.*;

@RestController
@RequestMapping("/flights")
public class flightController {

    static class Flight {
        public Flight(Date departs, List<Ticket> tickets) {
            this.id = id;
            this.departs = departs;
            this.tickets = tickets;
        }

        @JsonIgnore
        private int id;
        @JsonProperty("Departs")
        private Date departs;
        @JsonProperty("Tickets")
        private List<Ticket> tickets;


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<Ticket> getTickets() {
            return tickets;
        }

        public void setTickets(List<Ticket> tickets) {
            this.tickets = tickets;
        }

        @JsonFormat(pattern="yyyy-MM-dd hh:mm")
        public Date getDeparts() {
            return departs;
        }

        public void setDeparts(Date departs) {
            this.departs = departs;
        }
    }

    static class Ticket {
        public Ticket(Passenger passenger, int price) {
            this.id = id;
            this.passenger = passenger;
            this.price = price;
        }

        @JsonIgnore
        private int id;
        @JsonProperty("Passenger")
        private Passenger passenger;
        @JsonProperty("Price")
        private int price;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Passenger getPassenger() {
            return this.passenger;
        }

        public void setPassenger(Passenger passenger) {
            this.passenger = passenger;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    static class Passenger {
        public Passenger(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.id = id;
        }

        @JsonIgnore
        private int id;
        @JsonProperty("First Name")
        private String firstName;
        @JsonProperty("Last Name")
        private String lastName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }
    @GetMapping("/flight")
    public Flight getFlight(){
        Passenger pass1 = new Passenger("some name", "some last name");
        Passenger pass2 = new Passenger("a first name", null);
        Ticket tick1 = new Ticket(pass1, 200);
        Ticket tick2 = new Ticket(pass2, 170);

        Flight flt1 = new Flight(new Date(), Arrays.asList(tick1,tick2));
        return flt1;
    }

    @GetMapping("/")
    public List getFlights(){
        Passenger pass1 = new Passenger("some name", "some last name");
        Passenger pass2 = new Passenger("a first name", null);
        Ticket tick1 = new Ticket(pass1, 200);
        Ticket tick2 = new Ticket(pass2, 170);
        Flight flt1 = new Flight(new Date(), Collections.singletonList(tick1));
        Flight flt2 = new Flight(new Date(), Collections.singletonList(tick1));
        return Arrays.asList(flt1,flt2);
    }

    @PostMapping("/tickets/total")
    public Object postTickTotal(@RequestBody Flight flt){
        HashMap output= new HashMap();
        int sum = 0;
        for (Ticket tick : flt.getTickets()) {
            sum += tick.getPrice();
        }
        output.put("result",sum);
        return output;
    }
}
