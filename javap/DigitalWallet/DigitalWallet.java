import java.util.Date;
import java.util.ArrayList;
public class DigitalWallet {
  
   /* Store all the Add Money, Pay and Reward transactions as transaction objects */

   /* implement all the methods given below */

   /* Default constructor */
    Date date;
  private String userName;
  private String typeName;
  private double amount;
   private String desc;
  private String merchant;
  double balance = 0; 
  int k = 0;
  ArrayList<Transaction> txArray = new ArrayList<Transaction>();
  public DigitalWallet(String userName ) {
    this.userName = userName;
  }

   /* Add money to the wallet */
  public boolean addMoney(Transaction tx) { 
    txArray.add(tx);
    if((tx.getAmount() > 0) && (tx.getAmount() < 5000)) {
      balance = balance + tx.getAmount();
      return true; 
    }
    else {
      return false;
    }
  }

  /* Return the balance in the wallet */
  public double getBalance() {
    if(balance > 0 ) {
      return balance;
    }
    else
     return 0.0;
  }

  /* Make a payment */
  public boolean pay(Transaction tx) { 
    double amt;
    Transaction trans;
    if(balance > tx.getAmount()) {
      txArray.add(tx);
      balance = balance - tx.getAmount();
      amt = tx.getAmount();
      amt = (((amt - (amt%100))/100)*10);
      trans = new Transaction();
      trans.setType("reward");
      trans.setMerchant(tx.getMerchant());
      trans.setAmount(amt);
      txArray.add(trans);
      balance = balance + amt;
      return true;
    }
    return false; 
  }

//   /* print statement */
  public void printStatement() {
    System.out.println("balance is "+balance);
  }

//   /* Return the list of reward transactions */
 
  /* Return all the transactions */
  public Transaction[] getTransactions(String name) {
    int j = 0;
    Transaction[] trname = new Transaction[txArray.size()];
    if(txArray.size()==0) {
      return null;
    }
    for(int i = 0; i < txArray.size(); i++) {
      if(txArray.get(i).getMerchant().equals(name)) {
      trname[j] = txArray.get(i);
      j++;
      }
    }
    return trname;
  }

//  /* Return the transactions that match the merchant name */ 
  public Transaction[] getAllTransactions() {
    
    Transaction[] trname1 = new Transaction[txArray.size()];
    if(txArray.size()==0) {
      return null;
    }
    for(int i = 0; i < txArray.size(); i++) {
      trname1[i] = txArray.get(i);
    }
    return trname1;
  }
  public String toString() {
    return userName;
  }
}