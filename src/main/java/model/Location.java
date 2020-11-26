package model;

import model.people.Customer;
import model.people.Employee;
import model.people.State;
import model.vehicle.Vehicle;

import javax.persistence.*;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

@Entity
@Table
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;

    @OneToOne(targetEntity = Vehicle.class, orphanRemoval = true, cascade = CascadeType.MERGE)
    private Vehicle vehicle;

    @OneToOne(targetEntity = Customer.class, orphanRemoval = true, cascade = CascadeType.MERGE)
    private Customer customer;

    @OneToOne(targetEntity = Employee.class, orphanRemoval = true, cascade = CascadeType.MERGE)
    private Employee employee;

    @Column
    @Enumerated(value = EnumType.STRING)
    private State status;

    @Column
    @Temporal(TemporalType.DATE)
    private Calendar startDate;

    @Column
    @Temporal(TemporalType.DATE)
    private Calendar endDate;

    @Column
    private boolean discount;

    @Column
    private int estimatedKm;

    @Column
    private Integer actualKm;

    public final static Float DISCOUNT_RATE = 0.1f;

    /* Constructors */
    /**
     * Constructor without an actualKm
     * @param vehicle
     * @param customer
     * @param employee
     * @param status
     * @param startDate
     * @param endDate
     * @param discount
     * @param estimatedKm
     */
    public Location(Vehicle vehicle, Customer customer, Employee employee, State status, Calendar startDate, Calendar endDate,
                    boolean discount, int estimatedKm) {
        this.vehicle = vehicle;
        this.customer = customer;
        this.employee = employee;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.discount = discount;
        this.estimatedKm = estimatedKm;
        this.actualKm = null;
    }

    /**
     * Constructor with an actualKm
     * @param vehicle
     * @param customer
     * @param employee
     * @param startDate
     * @param endDate
     * @param discount
     * @param estimatedKm
     * @param actualKm
     */
    public Location(Vehicle vehicle, Customer customer, Employee employee, State status, Calendar startDate, Calendar endDate,
                    boolean discount, int estimatedKm, int actualKm) {
        this.vehicle = vehicle;
        this.customer = customer;
        this.employee = employee;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.discount = discount;
        this.estimatedKm = estimatedKm;
        this.actualKm = actualKm;
    }

    public Location() {}


    /* Static methods */
    /**
     * Returns the extra price if the kilometers exceeds the flat rate
     * @param km
     * @return the extra price
     */
    private static float getExtraPriceForKm(int km) {
        final float extraCentsPerKm;

        if      (km <= 50)  extraCentsPerKm = 0.0f;
        else if (km <= 100) extraCentsPerKm = 0.5f;
        else if (km <= 200) extraCentsPerKm = 0.3f;
        else if (km <= 300) extraCentsPerKm = 0.2f;
        else                extraCentsPerKm = 0.1f;

        return extraCentsPerKm * (km-50);
    }


    /* Private methods */
    /**
     * Returns the offset between the beginning and the end of the Rent
     * @return the number of days between two Calendar
     */
    @Transient
    private int getNumberOfDays() {
        final long millisDebut = startDate.getTimeInMillis();
        final long millisFin = endDate.getTimeInMillis();
        final long offset = Math.abs(millisFin - millisDebut);
        return (int) TimeUnit.MILLISECONDS.toDays(offset);
    }

    /* Computed Properties */
    /**
     * Returns the estimated price
     * @return the estimated price
     */
    @Transient
    public float getEstimatedPrice() {
        return (vehicle.getPrixLocJour() * (float) getNumberOfDays()/30 + getExtraPriceForKm(estimatedKm)) * (discount ? 1-DISCOUNT_RATE : 1);
    }

    /**
     * Returns the actual price
     * @return the actual price or -1 if there is no actualKm
     */
    @Transient
    public Float getActualPrice() {
        if (actualKm == null) return 0f;
        return (vehicle.getPrixLocJour() * (float) getNumberOfDays()/30 + getExtraPriceForKm(actualKm)) * (discount ? 1-DISCOUNT_RATE : 1);
    }

    @Transient
    public Float getPrice(){
        float res = 0;
        if(this.status == State.Completed) res = getActualPrice();
        if(this.status == State.Booked || this.status == State.InProgress) res = getEstimatedPrice();
        return res;
    }


    /* Getter / Setters */

    public Integer getId() {
        return this.id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Customer getClient() {
        return customer;
    }

    public void setClient(Customer customer) {
        this.customer = customer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public State getStatus() {
        return status;
    }

    public void setStatus(State status) {
        this.status = status;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }

    public boolean isDiscount() {
        return discount;
    }

    public void setDiscount(boolean discount) {
        this.discount = discount;
    }

    public int getEstimatedKm() {
        return estimatedKm;
    }

    public void setEstimatedKm(int estimatedKm) {
        this.estimatedKm = estimatedKm;
    }

    public int getKmReel() {
        return actualKm;
    }

    public void setKmReel(int actualKm) {
        this.actualKm = actualKm;
    }
}
