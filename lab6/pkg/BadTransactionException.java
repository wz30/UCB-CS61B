/* BadAccountException.java */
package pkg;
/**
 *  Implements an exception that should be thrown for nonexistent accounts.
 **/
public class BadTransactionException extends Exception {

  public int transactionNumber;  // The invalid account number.

  /**
   *  Creates an exception object for nonexistent account "badAcctNumber".
   **/
  public BadTransactionException(int transactionNumber) {
    super("Invalid account number: " + transactionNumber);

    this.transactionNumber = transactionNumber;
  }
}