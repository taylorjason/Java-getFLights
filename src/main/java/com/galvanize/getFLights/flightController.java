package com.galvanize.getFLights;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/flights")
public class flightController {

    static class Flight {
        public Flight(int id, Date departs, List<Ticket> tickets) {
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
        public Ticket(int id, List<Passenger> passengers, int price) {
            this.id = id;

            this.passengers = passengers;

            this.price = price;
        }

        @JsonIgnore
        private int id;
        @JsonProperty("Passengers")
        private List<Passenger> passengers;
        @JsonProperty("Price")
        private int price;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<Passenger> getPassengers() {
            return passengers;
        }

        public void setPassengers(List<Passenger> passengers) {
            this.passengers = passengers;
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
        public Passenger(int id, String firstName, String lastName) {
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
        Passenger pass1 = new Passenger(1,"some name", "some last name");
        Passenger pass2 = new Passenger(2,"a first name", null);
        Ticket tick1 = new Ticket(1, Arrays.asList(pass1, pass2), 200);
        Flight flt1 = new Flight(1,new Date(), Collections.singletonList(tick1));
        return flt1;
    }

    @GetMapping("/")
    public List getFlights(){
        Passenger pass1 = new Passenger(1,"some name", "some last name");
        Passenger pass2 = new Passenger(2,"a first name", null);
        Ticket tick1 = new Ticket(1, Arrays.asList(pass1, pass2), 200);
        Flight flt1 = new Flight(1,new Date(), Collections.singletonList(tick1));
        Flight flt2 = new Flight(2,new Date(), Collections.singletonList(tick1));
        return Arrays.asList(flt1,flt2);
    }
}
