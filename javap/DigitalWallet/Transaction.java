import java.util.Date;
import java.util.ArrayList;

public class Transaction {
  Date date;
  private String typeName;
  private double amount;
  private String desc;
  private String merchant;
  Transaction() {
    ArrayList<Transaction> txArray = new ArrayList<Transaction>();
  }
  public void setType(String type) {
    this.typeName = type;
  }
  public void setAmount(double amount) {
    this.amount = amount;
  }
  public void setDate(Date date) {
    this.date = date;
  }
  public void setDescription(String desc) {
    this.desc = desc;
  }
  public void setMerchant(String merchant) {
    this.merchant = merchant;
  }
  public double getAmount() {
  	return amount;
  }
  public String getType() {
  	return typeName;
  }
  public String getMerchant() {
  	return merchant;
  }
  public String toString() {
  	return typeName+" "+amount+desc+" "+merchant;
  }
}