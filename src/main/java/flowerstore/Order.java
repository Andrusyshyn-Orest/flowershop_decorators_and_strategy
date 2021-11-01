package flowerstore;

import delivery.Delivery;
import lombok.Getter;
import payment.Payment;

import java.util.LinkedList;
import java.util.List;

@Getter
public class Order {
    private List<Item> items;
    private Payment payment;
    private Delivery delivery;

    public Order() {
        items = new LinkedList<>();
    }

    public void setPaymentStrategy(Payment paymentStrategy) {
        this.payment = paymentStrategy;
    }

    public void setDeliveryStrategy(Delivery deliveryStrategy){
        this.delivery = deliveryStrategy;
    }

    public double calculateTotalPrice() {
        double totalPrice = 0;
        for (Item item: this.items) {
            totalPrice += item.getPrice();
        }
        return totalPrice;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public void removeItem(Item item) {
        this.items.remove(item);
    }

    public void processOrder() {
        this.payment.pay(this.calculateTotalPrice());
        this.delivery.deliver(this.items);
    }
}
