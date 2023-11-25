package model;

public class Order {
    private int id;
    private int petId;
    private int quantity;
    private String shipDate;
    private boolean complete;
    public static Order.Builder newEntity() {return new Order().new Builder();}
    public int getId() {return id;}
    public int getPetId() {return petId;}
    public int getQuantity() {return quantity;}
    public String getShipDate() {return shipDate;}
    public boolean getComplete() {return complete;}
    public class Builder{
        private Builder() {}
        public Order.Builder withId(int id) {Order.this.id = id; return this;}
        public Order.Builder withPetId(int petId) {Order.this.petId = petId; return this;}
        public Order.Builder withQuantity(int quantity) {Order.this.quantity = quantity; return this;}
        public Order.Builder withShipDate(String shipDate) {Order.this.shipDate = shipDate; return this;}
        public Order.Builder withComplete(boolean complete) {Order.this.complete = complete; return this;}
        public Order build() {return Order.this;}
    }
}
