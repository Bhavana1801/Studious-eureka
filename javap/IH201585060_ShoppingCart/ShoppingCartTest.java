/**
 *
 * ShoppingCartTest has a main method and you cannot make changes to it.
 * You have to implement Item and ShoppingCart classes for the main method to work
 * Item class should have a constructor with 3 arguments. (product name, quantity, unit price)
 * Shopping Cart should hold all the items the user wants to buy
 * It should implement addToCart, removeFromCart, printInvoice, applyCoupon methods.
 * It should also implement getTotalAmount, getPayableAmount
 * Sales tax is 14% of the total amount and it should be printed on the invoice
 * On applying coupon IND10, 10% discount should be applied to the cart
 * No other copon codes are allowed
 * 
 */

public class ShoppingCartTest
{
  public static void main(String[] args)
  {
    // creates new items with product name, quantity and unit price
    Item i1 = new Item("Olive Oil 1l", 3, 100.0);
    Item i2 = new Item("Cheese Slices", 2, 50.0);
    Item i3 = new Item("Bread", 1, 75.0);
    Item i4 = new Item("Eggs", 50, 10.0);
    Item i5 = new Item("Chicken Salami", 2, 100.0);
    
    ShoppingCart cart = new ShoppingCart();
    
    /* add the item to the cart */
    cart.addToCart(i1);
    cart.addToCart(i2);
    cart.addToCart(i3);
    cart.addToCart(i4);
    cart.addToCart(i5);
    
    /*
     * shows the list of items in the cart with quantity
     * Expected Output to the console:
     * Olive Oil 1l: 3
     * Cheese Slices: 2
     * Bread: 1
     * Eggs: 50
     * Chicken Salami: 2
     */
    cart.showCart();
    cart.all();
    
    /* remove the item sent as argument from the cart */
    cart.removeFromCart(i3);   
   // cart.showCart();
    cart.all(); 
    double totalAmount = cart.getTotalAmount();
    //cart.getTotalAmount();
    double payableAmount =cart.getPayableAmount();
    /*
     * shows the list of items in the cart with quantity
     * Expected Output to the console:
     * Olive Oil 1l: 3
     * Cheese Slices: 2
     * Eggs: 50
     * Chicken Salami: 2
     */
    cart.showCart();
    cart.all();

    /*
     * gets the total amount for the items in the cart
     * Expected Output:
     * 1100
     */
    totalAmount = cart.getTotalAmount();
    System.out.println(totalAmount);

    /*
     * Gets the payable amount,
     * it deduct discount and add tax to the total amount of items in cart
     * Expected Output:
     * 1254
     */
    payableAmount = cart.getPayableAmount();
    System.out.println(payableAmount);    

    /* apply a coupon code */

    cart.applyCoupon("IND50");
    
    /* 
     * print the items with the quanity, unit price, total amount
     * apply coupon if valid 
     * add tax to the total amount after discount
     * Expected Output to the console:
     * Olive Oil 1l      3    100.00    300.00 
     * Cheese Slices     2     50.00    100.00
     * Eggs             50     10.00    500.00
     * Chicken Salami    2    100.00    200.00
     *                          Total: 1100.00
     *                          Disc%:    0.00
     *                          Tax:    154.00
     *                          Total: 1254.00
     *
     */

    cart.printInvoice();
   cart.all();
    cart.applyCoupon("IND10");
    totalAmount = cart.getTotalAmount();
    payableAmount = cart.getPayableAmount();
   
    /* 
     * print the items with the quanity, unit price, total amount
     * apply coupon if valid 
     * add tax to the total amount after discount
     * Expected Output to the console:
     * Olive Oil 1l      3    100.00    300.00 
     * Cheese Slices     2     50.00    100.00
     * Eggs             50     10.00    500.00
     * Chicken Salami    2    100.00    200.00
     *                          Total: 1100.00
     *                          Disc%:  110.00
     *                          Tax:    14.00
     *                          Total: 1128.60
     *
     */
    cart.printInvoice();
    cart.all();
    cart.addToCart(new Item("Milk", 5, 30.00));
    cart.applyCoupon("IND10");
    totalAmount = cart.getTotalAmount();
    payableAmount = cart.getPayableAmount();
    /* 
     * print the items with the quanity, unit price, total amount
     * apply coupon if valid 
     * add tax to the total amount after discount
     * Expected Output to the console:
     * Olive Oil 1l      3    100.00    300.00 
     * Cheese Slices     2     50.00    100.00
     * Eggs             50     10.00    500.00
     * Chicken Salami    2    100.00    200.00
     * Milk              5     30.00    150.00
     *                          Total: 1250.00
     *                          Disc%:  125.00
     *                          Tax:    157.50
     *                          Total: 1282.50
     *
     */
    cart.printInvoice();
    cart.all();
  }
}