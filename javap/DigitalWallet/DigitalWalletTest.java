import java.util.Date;
/*
 *
 * A digital wallet is a software application that allows
 * an individual to make online payments.
 * 
 * A user can do the following:
 *  - create a new digital wallet
 *  - add money to their wallet
 *  - check balance in wallet
 *  - pay a merchant from their wallet
 *  - earn Rs. 10 for every Rs. 100 spent from the wallet
 *  - see the statement of all the transactions
 *
 */

public class DigitalWalletTest {
  public static void main(String[] args) {

    boolean flag = false;
    double availableBalance = 0;
    Transaction tx = null;
    Date txDate = null;
    
    /* Create an object of digital wallet for Praveen */
    DigitalWallet wallet = new DigitalWallet("Praveen");
    /* 
     * Add money to wallet
     * Cannot add less than 0 or more than 5000
     */

    /* Create an Transaction object and set the details using setter methods */
    txDate = new Date();
    tx = new Transaction();
    
    // /* Based on the nature of transaction its type should be set to Add Money or Payment or Reward */
    tx.setType("Add Money");
    tx.setAmount(1000);
    tx.setDate(txDate);
    tx.setDescription("First deposit");
    tx.setMerchant("NA");
    flag = wallet.addMoney(tx);
    if(flag) {
      System.out.println("Transaction Approved");
      System.out.println("Testcase Passed!");
    } else {
      System.out.println("Transaction Declined");
      System.out.println("Testcase Failed!");
    }

    /*
     *  Check the balance in wallet
     */
    availableBalance = wallet.getBalance();
    System.out.println("You have " + availableBalance + "in your wallet");
    System.out.println("Expected: 1000 - Actual: " + availableBalance);

    /*
     *  Pay a merchant from wallet
     *  Don't approve transaction if balance is less than amount
     */

     /* Create an Transaction object and set the details using setter methods */
    txDate = new Date();
    tx = new Transaction();
    tx.setType("Payment");
    tx.setAmount(455);
    tx.setDate(txDate);
    tx.setDescription("Mobile Recharge");
    tx.setMerchant("Airtel");
    flag = wallet.pay(tx);
    if(flag) {
      System.out.println("Transaction Approved");
      System.out.println("Testcase Passed!");
    } else {
      System.out.println("Transaction Declined");
      System.out.println("Testcase Failed!");
    }

    /*
     *  Check the balance in wallet
     *  Prev balance = 1000
     *  Paid 455, so balance - paid = 1000 - 455 = 545
     *  Add Rs. 40 for spending 455, so balance 545 + 40 = 585
     */
    availableBalance = wallet.getBalance();
    System.out.println("You have " + availableBalance + "in your wallet");
    System.out.println("Expected: 585 - Actual: " + availableBalance);

    /*
     *  Pay a merchant from wallet
     *  Don't approve transaction if balance is less than amount
     */
    /* Create an Transaction object and set the details using setter methods */
    txDate = new Date();
    tx = new Transaction();
    tx.setType("Payment");
    tx.setAmount(280.50);
    tx.setDate(txDate);
    tx.setDescription("Taxi Ride");
    tx.setMerchant("Uber");
    flag = wallet.pay(tx);
    if(flag) {
      System.out.println("Transaction Approved");
      System.out.println("Testcase Passed!");
    } else {
      System.out.println("Transaction Declined");
      System.out.println("Testcase Failed!");
    }

    /*
     *  Check the balance in wallet
     *  Prev balance = 585
     *  Paid 280.50, so balance - paid = 585 - 280.50 = 304.50
     *  Add Rs. 20 for spending 280.50, so balance 304.50 + 20 = 324.50
     */
    availableBalance = wallet.getBalance();
    System.out.println("You have " + availableBalance + "in your wallet");
    System.out.println("Expected: 324.50 - Actual: " + availableBalance);

    /*
     *  Pay a merchant from wallet
     *  Don't approve transaction if balance is less than amount
     */
    /* Create an Transaction object and set the details using setter methods */
    txDate = new Date();
    tx = new Transaction();
    tx.setType("Payment");
    tx.setAmount(680);
    tx.setDate(txDate);
    tx.setDescription("Icecream time!");
    tx.setMerchant("Cream Stone");
    flag = wallet.pay(tx);
    if(flag) {
      System.out.println("Transaction Approved");
      System.out.println("Testcase Failed!");
    } else {
      System.out.println("Transaction Declined");
      System.out.println("Testcase Passed!");
    }

    
    //  *  Check the balance in wallet
    //  *  Prev balance = 585
    //  *  Paid 280.50, so balance - paid = 585 - 280.50 = 304.50
    //  *  Add Rs. 20 for spending 280.50, so balance 304.50 + 20 = 324.50
     
    availableBalance = wallet.getBalance();
    System.out.println("You have " + availableBalance + "in your wallet");
    System.out.println("Expected: 324.50 - Actual: " + availableBalance);

    /* 
     * Add money to wallet
     * Cannot add less than 0 or more than 5000
     */
    /* Create an Transaction object and set the details using setter methods */
    txDate = new Date();
    tx.setType("Add Money");
    tx.setAmount(500);
    tx.setDate(txDate);
    tx.setDescription("First deposit");
    tx.setMerchant("NA");
    wallet.addMoney(tx);

    // /*
    //  *  Check the balance in wallet
    //  *  Prev balance = 324.50
    //  *  Add 500, so balance + deposit = 324.50 + 500 = 824.50
    //  */
    availableBalance = wallet.getBalance();
    System.out.println("You have " + availableBalance + "in your wallet");
    System.out.println("Expected: 824.50 - Actual: " + availableBalance);

    /*
     *  Pay a merchant from wallet
     *  Don't approve transaction if balance is less than amount
     */
    /* Create an Transaction object and set the details using setter methods */
    txDate = new Date();
    tx = new Transaction();
    tx.setType("Payment");
    tx.setAmount(680);
    tx.setDate(txDate);
    tx.setDescription("Icecream time!");
    tx.setMerchant("Cream Stone");
    flag = wallet.pay(tx);
    if(flag) {
      System.out.println("Transaction Approved");
      System.out.println("Testcase Passed!");
    } else {
      System.out.println("Transaction Declined");
      System.out.println("Testcase Failed!");
    }

    // /*
    //  *  Check the balance in wallet
    //  *  Prev balance = 824.50
    //  *  Paid 680, so balance - paid = 824.50 - 680 = 144.50
    //  *  Add Rs. 60 for spending 680, so balance 144.50 + 60 = 204.50
    //  */
    availableBalance = wallet.getBalance();
    System.out.println("You have " + availableBalance + "in your wallet");
    System.out.println("Expected: 204.50 - Actual: " + availableBalance);

    // /*
    //  *  Pay a merchant from wallet
    //  *  Don't approve transaction if balance is less than amount
    //  */
    // /* Create an Transaction object and set the details using setter methods */
    txDate = new Date();
    tx = new Transaction();
    tx.setType("Payment");
    tx.setAmount(120.50);
    tx.setDate(txDate);
    tx.setDescription("Taxi Ride");
    tx.setMerchant("Uber");
    flag = wallet.pay(tx);
    if(flag) {
      System.out.println("Transaction Approved");
      System.out.println("Testcase Passed!");
    } else {
      System.out.println("Transaction Declined");
      System.out.println("Testcase Failed!");
    }

    // /*
    //  *  Check the balance in wallet
    //  *  Prev balance = 204.50
    //  *  Paid 120.50, so balance - paid = 204.50 - 120.50 = 84
    //  *  Add Rs. 10 for spending 120.50, so balance 84 + 10 = 94
    //  */
    availableBalance = wallet.getBalance();
    System.out.println("You have " + availableBalance + "in your wallet");
    System.out.println("Expected: 94 - Actual: " + availableBalance);

    // /*
    //  * Expected Output: Print it for your convenience. Not graded.
    //  */
    wallet.printStatement();

    // /* Return the transactions for a given merchant */
    Transaction[] txs = wallet.getTransactions("Uber");
    for(int i = 0; txs[i]!=null ; i++) {
      System.out.println(txs[i]);
    }
    // /* Expected output - 2 Uber trasactions objects should be array */

    // /* Return all the transactions */
    txs = wallet.getAllTransactions();
    // /* Expected 10 objects of the type transaction */
    for(Transaction t:txs) {
      System.out.println(t);
    }
    //txs = wallet.getRewardTransactions();
  }
}