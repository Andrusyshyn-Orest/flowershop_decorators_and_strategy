import decorators.BasketDecorator;
import decorators.PaperDecorator;
import decorators.RibbonDecorator;
import delivery.DHLDeliveryStrategy;
import delivery.Delivery;
import flowerstore.*;
import payment.PayPalPaymentStrategy;
import payment.Payment;


public class Main {
    public static void main(String[] args) {
        Item flower_for_remove = new Flower(FlowerType.ROSE);
        byte color_for_remove[] = {127, 127, 0};
        ((Flower) flower_for_remove).setPrice(300);
        ((Flower) flower_for_remove).setSepalLength(10);
        ((Flower) flower_for_remove).setColor(color_for_remove);


        Item flower = new Flower(FlowerType.ROSE);
        byte color[] = {127, 0, 0};
        ((Flower) flower).setPrice(10);
        ((Flower) flower).setSepalLength(10);
        ((Flower) flower).setColor(color);


        Item flower_dec = new PaperDecorator(flower);


        Item pack = new FlowerPack();
        ((FlowerPack) pack).setFlower((Flower)flower);
        ((FlowerPack) pack).setAmount(10);

        Item pack_dec = new BasketDecorator(pack);

        Item bucket = new FlowerBucket();
        ((FlowerBucket)bucket).addFlowerPack((FlowerPack) pack);

        Item bucket_dec = new RibbonDecorator(bucket);

        Order order  = new Order();
        order.addItem(flower);                          // 10
        order.addItem(flower_dec);                      // 10 + 23
        order.addItem(flower_for_remove);
        order.removeItem(flower_for_remove);
        order.addItem(pack);                            // 10 + 23 + 100
        order.addItem(pack_dec);                        // 10 + 23 + 100 + 104
        order.addItem(bucket);                          // 10 + 23 + 100 + 104 + 100
        order.addItem(bucket_dec);                      // 10 + 23 + 100 + 104 + 100 + 140 = 477

        System.out.println(order.calculateTotalPrice());

        Delivery delivery = new DHLDeliveryStrategy();
        Payment payment = new PayPalPaymentStrategy();

        order.setDeliveryStrategy(delivery);
        order.setPaymentStrategy(payment);

        order.processOrder();


    }
}