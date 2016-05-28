import java.util.*;
import java.lang.*;
class ShoppingCart {
	Item[] arrayItems;
	int i;
	int count;
	double total;
	double taxAmount;
	double discAmount;
	double totAmount;
	double disc;
	ShoppingCart() {
		arrayItems = new Item[10];
		count = 0;
		total = 0.00;
		taxAmount = 0.00;
		disc = 0.0;
		totAmount = 0.00;
	}
	public void all() {
		total = 0.00;
		taxAmount = 0.00;
		disc = 0.0;
		totAmount = 0.00;
	}
	public void addToCart(Item temp) {
		arrayItems[count] = temp;
		//i++;
		count++;
	}
	public void showCart() {
		for(int x = 0; x < count; x++) {
			System.out.println(arrayItems[x].prodName + ": " + arrayItems[x].prodQuant);
		}
	}
	public void removeFromCart(Item temp) {
      for (int x = 0; x < count; x++) {
         if (arrayItems[x].prodName.compareTo(temp.prodName) == 0) {
            count--;
            for (int k = x; k < count; k++) {
              arrayItems[k] = arrayItems[k + 1];
            }
          }
      }
   }
   public double getTotalAmount() {
   	for(int x = 0; x < count; x++) {
  		total = total + ((arrayItems[x].prodPrice)*(arrayItems[x].prodQuant));
   	}
   	return total;
   }
   public double getPayableAmount() {
      //all();
      //total= getTotalAmount();
 
   	discAmount = (((disc)*(total))/100);
   	totAmount = total - discAmount;
   	taxAmount = ((14*(totAmount))/100);
   	totAmount = totAmount + taxAmount;
   	//System.out.println("total"+total+"discAmount"+discAmount+"taxAmount"+taxAmount+"getTotalAmount"+totAmount);

   	return totAmount;
   	}
   	public void applyCoupon(String coupon) {
   		if(coupon.compareTo("IND10") == 0) {
   			disc = 10.0;
   			//System.out.println("disc="+disc);

   		}
   	}
   	public void printInvoice() {
   		int space;
   		for(int x = 0; x < count; x++) {
   			space = 20 - arrayItems[x].prodName.length();
   			System.out.print(arrayItems[x].prodName);
   			for(int y = 0; y < space; y++) {
   				System.out.print(" ");
   			}
   			space = 5;
   			System.out.print(arrayItems[x].prodQuant);
   			for(int y = 0; y < space; y++) {
   				System.out.print(" ");
   			}
   			space = 10;
   			System.out.print(arrayItems[x].prodPrice);
   			for(int y = 0; y < space; y++) {
   				System.out.print(" ");
   			}
   			space = 10;
   			System.out.print(arrayItems[x].prodPrice*arrayItems[x].prodQuant);
   			for(int y = 0;y < space;y++) {
   				System.out.print(" ");
   			}

   			System.out.println();
   		}
   		System.out.println();
   		for(int y = 0; y < 34; y++) {
   			System.out.print(" ");
   		}
   		System.out.print("total: "+total);
   		System.out.println();
   		for(int y=0;y<34;y++) {
   			System.out.print(" ");
   		}
   		System.out.print("Disc%: "+discAmount);
   		System.out.println();
   		for(int y=0;y<34;y++) {
   			System.out.print(" ");
   		}
   		System.out.print("Tax: "+taxAmount);
   		System.out.println();
   		for(int y=0;y<34;y++) {
   			System.out.print(" ");
   		}
   		System.out.print("Total: "+totAmount);
   		System.out.println("\n");

   	}

}