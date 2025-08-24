package com.bankingapp.service;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import com.bankingapp.dao.CustomerDao;
import com.bankingapp.dao.TransactionDao;
import com.bankingapp.model.Customer;
import com.bankingapp.model.Transaction;

//public class TransactionService {
//    private final TransactionDao txDao = new TransactionDao();
//    private final CustomerDao custDao = new CustomerDao();
//
//    public void credit(String toAccount, double amount) {
//        if (amount <= 0) throw new IllegalArgumentException("Amount must be > 0");
//        try (Connection conn = com.bankingapp.dao.DbUtil.getConnection()) {
//            try {
//                conn.setAutoCommit(false);
//                Customer dest = custDao.findByAccount(toAccount);
//                dest.setBalance(dest.getBalance() + amount);
//                custDao.update(dest);
//
//                Transaction t = new Transaction();
//                t.setFromAccount(null);
//                t.setToAccount(toAccount);
//                t.setType("CREDIT");
//                t.setAmount(amount);
//                txDao.add(t);
//
//                conn.commit();
//            } catch (Exception ex) {
//                conn.rollback();
//                throw ex;
//            } finally {
//                conn.setAutoCommit(true);
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public void transfer(String fromAccount, String toAccount, double amount) {
//        if (amount <= 0) throw new IllegalArgumentException("Amount must be > 0");
//        if (fromAccount.equals(toAccount)) throw new IllegalArgumentException("Cannot transfer to same account");
//
//        try (Connection conn = com.bankingapp.dao.DbUtil.getConnection()) {
//            try {
//                conn.setAutoCommit(false);
//
//                Customer src = custDao.findByAccount(fromAccount);
//                Customer dst = custDao.findByAccount(toAccount);
//
//                if (dst == null) throw new IllegalArgumentException("Destination account not found");
//                if (src.getBalance() < amount) throw new IllegalArgumentException("Insufficient balance");
//
//                src.setBalance(src.getBalance() - amount);
//                dst.setBalance(dst.getBalance() + amount);
//                custDao.update(src);
//                custDao.update(dst);
//
//                Transaction t = new Transaction();
//                t.setFromAccount(fromAccount);
//                t.setToAccount(toAccount);
//                t.setType("TRANSFER");
//                t.setAmount(amount);
//                txDao.add(t);
//
//                conn.commit();
//            } catch (Exception ex) {
//                conn.rollback();
//                throw ex;
//            } finally {
//                conn.setAutoCommit(true);
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public List<Transaction> filter(String account, Date from, Date to, String type) {
//        try {
//            return txDao.findByFilters(account, from, to, type);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//}



//public class TransactionService {
//    private final TransactionDao txDao = new TransactionDao();
//    private final CustomerDao custDao = new CustomerDao();
//
//    public void credit(String toAccount, double amount) {
//        if (amount <= 0) throw new IllegalArgumentException("Amount must be > 0");
//        try (Connection conn = com.bankingapp.dao.DbUtil.getConnection()) {
//            try {
//                conn.setAutoCommit(false);
//                Customer dest = custDao.findByAccount(toAccount);
//                dest.setBalance(dest.getBalance() + amount);
//                custDao.update(dest);
//
//                Transaction t = new Transaction();
//                t.setFromAccount(null);
//                t.setToAccount(toAccount);
//                t.setType("CREDIT");
//                t.setAmount(amount);
//                txDao.add(t, conn);
//
//                conn.commit();
//            } catch (Exception ex) {
//                conn.rollback();
//                throw ex;
//            } finally {
//                conn.setAutoCommit(true);
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public void transfer(String fromAccount, String toAccount, double amount) {
//        if (amount <= 0) throw new IllegalArgumentException("Amount must be > 0");
//        if (fromAccount.equals(toAccount)) throw new IllegalArgumentException("Cannot transfer to same account");
//
//        try (Connection conn = com.bankingapp.dao.DbUtil.getConnection()) {
//            try {
//                conn.setAutoCommit(false);
//
//                Customer src = custDao.findByAccount(fromAccount);
//                Customer dst = custDao.findByAccount(toAccount);
//
//                if (dst == null) throw new IllegalArgumentException("Destination account not found");
//                if (src.getBalance() < amount) throw new IllegalArgumentException("Insufficient balance");
//
//                src.setBalance(src.getBalance() - amount);
//                dst.setBalance(dst.getBalance() + amount);
//                custDao.update(src);
//                custDao.update(dst);
//
//                Transaction t = new Transaction();
//                t.setFromAccount(fromAccount);
//                t.setToAccount(toAccount);
//                t.setType("TRANSFER");
//                t.setAmount(amount);
//                txDao.add(t, conn);
//
//                conn.commit();
//            } catch (Exception ex) {
//                conn.rollback();
//                throw ex;
//            } finally {
//                conn.setAutoCommit(true);
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public List<Transaction> filter(String account, Date from, Date to, String type) {
//        try {
//            return txDao.findByFilters(account, from, to, type);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//}



//--description added transfer type done 


//public class TransactionService {
//    private final TransactionDao txDao = new TransactionDao();
//    private final CustomerDao custDao = new CustomerDao();
//
//    // ✅ Self Credit (Deposit)
//    public void credit(String toAccount, double amount) {
//        if (amount <= 0) throw new IllegalArgumentException("Amount must be > 0");
//        try (Connection conn = com.bankingapp.dao.DbUtil.getConnection()) {
//            try {
//                conn.setAutoCommit(false);
//                Customer dest = custDao.findByAccount(toAccount);
//                if (dest == null) throw new IllegalArgumentException("Destination account not found");
//
//                double oldBalance = dest.getBalance();
//                dest.setBalance(oldBalance + amount);
//                custDao.update(dest);
//
//                Transaction t = new Transaction();
//                t.setFromAccount(toAccount);  // self-credit from same account
//                t.setToAccount(toAccount);
//                t.setType("CREDIT");
//                t.setAmount(amount);
//                t.setDescription("Self deposit of " + amount +
//                        " | Balance: " + oldBalance + " → " + dest.getBalance());
//                txDao.add(t);
//
//                conn.commit();
//            } catch (Exception ex) {
//                conn.rollback();
//                throw ex;
//            } finally {
//                conn.setAutoCommit(true);
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    // ✅ Transfer (both records use TRANSFER type, description distinguishes IN/OUT)
//    public void transfer(String fromAccount, String toAccount, double amount) {
//        if (amount <= 0) throw new IllegalArgumentException("Amount must be > 0");
//        if (fromAccount.equals(toAccount)) throw new IllegalArgumentException("Cannot transfer to same account");
//
//        try (Connection conn = com.bankingapp.dao.DbUtil.getConnection()) {
//            try {
//                conn.setAutoCommit(false);
//
//                Customer src = custDao.findByAccount(fromAccount);
//                Customer dst = custDao.findByAccount(toAccount);
//
//                if (dst == null) throw new IllegalArgumentException("Destination account not found");
//                if (src.getBalance() < amount) throw new IllegalArgumentException("Insufficient balance");
//
//                double oldSrcBal = src.getBalance();
//                double oldDstBal = dst.getBalance();
//
//                // update balances
//                src.setBalance(oldSrcBal - amount);
//                dst.setBalance(oldDstBal + amount);
//                custDao.update(src);
//                custDao.update(dst);
//
//                // Record OUT transaction
//                Transaction tOut = new Transaction();
//                tOut.setFromAccount(fromAccount);
//                tOut.setToAccount(toAccount);
//                tOut.setType("TRANSFER");   // ✅ always TRANSFER
//                tOut.setAmount(amount);
//                tOut.setDescription("Sent " + amount + " to " + toAccount +
//                        " | Balance: " + oldSrcBal + " → " + src.getBalance());
//                txDao.add(tOut);
//
//                // Record IN transaction
//                Transaction tIn = new Transaction();
//                tIn.setFromAccount(fromAccount);
//                tIn.setToAccount(toAccount);
//                tIn.setType("TRANSFER");   // ✅ always TRANSFER
//                tIn.setAmount(amount);
//                tIn.setDescription("Received " + amount + " from " + fromAccount +
//                        " | Balance: " + oldDstBal + " → " + dst.getBalance());
//                txDao.add(tIn);
//
//                conn.commit();
//            } catch (Exception ex) {
//                conn.rollback();
//                throw ex;
//            } finally {
//                conn.setAutoCommit(true);
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    // ✅ Filtering for passbook/admin
//    public List<Transaction> filter(String account, Date from, Date to, String type, boolean isAdmin) {
//        try {
//            return txDao.findByFilters(account, from, to, type, isAdmin);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//}







//public class TransactionService {
//    private final TransactionDao txDao = new TransactionDao();
//    private final CustomerDao custDao = new CustomerDao();
//
//    // ✅ Self Credit (Deposit)
//    public void credit(String toAccount, double amount) {
//        ValidationUtil.validateAmount(amount, "Credit amount");
//
//        try (Connection conn = com.bankingapp.dao.DbUtil.getConnection()) {
//            try {
//                conn.setAutoCommit(false);
////                Customer dest = custDao.findByAccount(toAccount);
////                if (dest == null) throw new IllegalArgumentException("Destination account not found");
//                
//                Customer dest = custDao.findByAccount(toAccount);
//                if (dest == null || !dest.isActive()) {
//                    throw new IllegalArgumentException("Destination account not found");
//                }
//
//                double oldBalance = dest.getBalance();
//                dest.setBalance(oldBalance + amount);
//                custDao.update(dest);
//
//                Transaction t = new Transaction();
//                t.setFromAccount(toAccount);  // self-credit
//                t.setToAccount(toAccount);
//                t.setType("CREDIT");
//                t.setAmount(amount);
//                t.setDescription("Self deposit of " + amount +
//                        " | Balance: " + oldBalance + " → " + dest.getBalance());
//                txDao.add(t);
//
//                conn.commit();
//            } catch (Exception ex) {
//                conn.rollback();
//                throw ex;
//            } finally {
//                conn.setAutoCommit(true);
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

    // ✅ Transfer
//    public void transfer(String fromAccount, String toAccount, double amount) {
//        ValidationUtil.validateAmount(amount, "Transfer amount");
//        if (fromAccount.equals(toAccount)) throw new IllegalArgumentException("Cannot transfer to same account");
//
//        try (Connection conn = com.bankingapp.dao.DbUtil.getConnection()) {
//            try {
//                conn.setAutoCommit(false);
//
//                Customer src = custDao.findByAccount(fromAccount);
//                Customer dst = custDao.findByAccount(toAccount);
//                System.out.println(+src.getBalance());
//
//                if (dst == null) throw new IllegalArgumentException("Destination account not found");
//                if (src.getBalance() < amount) throw new IllegalArgumentException("Insufficient balance");
//
//                double oldSrcBal = src.getBalance();
//                double oldDstBal = dst.getBalance();
//
//                src.setBalance(oldSrcBal - amount);
//                dst.setBalance(oldDstBal + amount);
//                custDao.update(src);
//                custDao.update(dst);
//
//                // OUT record
//                Transaction tOut = new Transaction();
//                tOut.setFromAccount(fromAccount);
//                tOut.setToAccount(toAccount);
//                tOut.setType("TRANSFER");
//                tOut.setAmount(amount);
//                tOut.setDescription("Sent " + amount + " to " + toAccount +
//                        " | Balance: " + oldSrcBal + " → " + src.getBalance());
//                txDao.add(tOut);
//
//                // IN record
//                Transaction tIn = new Transaction();
//                tIn.setFromAccount(fromAccount);
//                tIn.setToAccount(toAccount);
//                tIn.setType("TRANSFER");
//                tIn.setAmount(amount);
//                tIn.setDescription("Received " + amount + " from " + fromAccount +
//                        " | Balance: " + oldDstBal + " → " + dst.getBalance());
//                txDao.add(tIn);
//
//                conn.commit();
//            } catch (Exception ex) {
//                conn.rollback();
//                throw ex;
//            } finally {
//                conn.setAutoCommit(true);
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
// // ✅ Transfer
//    public void transfer(String fromAccount, String toAccount, double amount) {
//        ValidationUtil.validateAmount(amount, "Transfer amount");
//        if (fromAccount.equals(toAccount)) 
//            throw new IllegalArgumentException("Cannot transfer to same account");
//
//        try (Connection conn = com.bankingapp.dao.DbUtil.getConnection()) {
//            try {
//                conn.setAutoCommit(false);
//
//                Customer src = custDao.findByAccount(fromAccount);
////                Customer dst = custDao.findByAccount(toAccount);
////
////                if (dst == null) throw new IllegalArgumentException("Destination account not found");
//                
//                Customer dst = custDao.findByAccount(toAccount);
//                if (dst == null || !dst.isActive()) {
//                    throw new IllegalArgumentException("Destination account not found");
//                }
//
//                if (src.getBalance() < amount) throw new IllegalArgumentException("Insufficient balance");
//
//                double oldSrcBal = src.getBalance();
//                double oldDstBal = dst.getBalance();
//
//                src.setBalance(oldSrcBal - amount);
//                dst.setBalance(oldDstBal + amount);
//                custDao.update(src);
//                custDao.update(dst);
//
//                // OUT record
//                Transaction tOut = new Transaction();
//                tOut.setFromAccount(fromAccount);
//                tOut.setToAccount(toAccount);
//                tOut.setType("TRANSFER");
//                tOut.setAmount(amount);
//                tOut.setDescription("Sent " + amount + " to " + toAccount +
//                        " | Balance: " + oldSrcBal + " → " + src.getBalance());
//                txDao.add(tOut);
//
//                // IN record
//                Transaction tIn = new Transaction();
//                tIn.setFromAccount(fromAccount);
//                tIn.setToAccount(toAccount);
//                tIn.setType("TRANSFER");
//                tIn.setAmount(amount);
//                tIn.setDescription("Received " + amount + " from " + fromAccount +
//                        " | Balance: " + oldDstBal + " → " + dst.getBalance());
//                txDao.add(tIn);
//
//                conn.commit();
//            } catch (Exception ex) {
//                conn.rollback();
//                throw ex; // ⚠️ don’t wrap, rethrow original
//            } finally {
//                conn.setAutoCommit(true);
//            }
//        } catch (IllegalArgumentException e) {
//            throw e; // ⚠️ bubble up validation errors
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    
//    // ✅ Filtering
//    public List<Transaction> filter(String account, Date from, Date to, String type, boolean isAdmin) {
//        try {
//            return txDao.findByFilters(account, from, to, type, isAdmin);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//}


public class TransactionService {

    private final TransactionDao txDao = new TransactionDao();
    private final CustomerDao custDao = new CustomerDao();

    // ✅ Self Credit (Deposit)
    public void credit(String toAccount, double amount) {
        ValidationUtil.validateAmount(amount, "Credit amount");

        try (Connection conn = com.bankingapp.dao.DbUtil.getConnection()) {
            try {
                conn.setAutoCommit(false);

                Customer dest = custDao.findByAccount(toAccount);

                // 🚨 Block inactive or non-existent accounts
                if (dest == null || !dest.isActive()) {
                    throw new IllegalArgumentException("Destination account not found or inactive");
                }

                double oldBalance = dest.getBalance();
                dest.setBalance(oldBalance + amount);
                custDao.update(dest);

                Transaction t = new Transaction();
                t.setFromAccount(toAccount); // self-credit
                t.setToAccount(toAccount);
                t.setType("CREDIT");
                t.setAmount(amount);
                t.setDescription("Self deposit of " + amount +
                        " | Balance: " + oldBalance + " → " + dest.getBalance());
                txDao.add(t);

                conn.commit();
            } catch (Exception ex) {
                conn.rollback();
                throw ex;
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // ✅ Transfer with all validations
    public void transfer(String fromAccount, String toAccount, double amount) {
        ValidationUtil.validateAmount(amount, "Transfer amount");

        // 🚨 Prevent self-transfer
        if (fromAccount.equals(toAccount)) {
            throw new IllegalArgumentException("Cannot transfer to the same account");
        }

        try (Connection conn = com.bankingapp.dao.DbUtil.getConnection()) {
            try {
                conn.setAutoCommit(false);

                Customer src = custDao.findByAccount(fromAccount);
                Customer dst = custDao.findByAccount(toAccount);

                // 🚨 Source account checks
                if (src == null || !src.isActive()) {
                    throw new IllegalArgumentException("Source account not found or inactive");
                }

                // 🚨 Destination account checks
                if (dst == null || !dst.isActive()) {
                    throw new IllegalArgumentException("Destination account not found or inactive");
                }

                // 🚨 Balance check
                if (src.getBalance() < amount) {
                    throw new IllegalArgumentException("Insufficient balance");
                }

                double oldSrcBal = src.getBalance();
                double oldDstBal = dst.getBalance();

                src.setBalance(oldSrcBal - amount);
                dst.setBalance(oldDstBal + amount);
                custDao.update(src);
                custDao.update(dst);

                // OUT transaction record
                Transaction tOut = new Transaction();
                tOut.setFromAccount(fromAccount);
                tOut.setToAccount(toAccount);
                tOut.setType("TRANSFER");
                tOut.setAmount(amount);
                tOut.setDescription("Sent " + amount + " to " + toAccount +
                        " | Balance: " + oldSrcBal + " → " + src.getBalance());
                txDao.add(tOut);

                // IN transaction record
                Transaction tIn = new Transaction();
                tIn.setFromAccount(fromAccount);
                tIn.setToAccount(toAccount);
                tIn.setType("TRANSFER");
                tIn.setAmount(amount);
                tIn.setDescription("Received " + amount + " from " + fromAccount +
                        " | Balance: " + oldDstBal + " → " + dst.getBalance());
                txDao.add(tIn);

                conn.commit();
            } catch (Exception ex) {
                conn.rollback();
                throw ex; // ⚠️ keep original exception
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (IllegalArgumentException e) {
            throw e; // validation errors bubble up
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // ✅ Filtering
    public List<Transaction> filter(String account, Date from, Date to, String type, boolean isAdmin) {
        try {
            return txDao.findByFilters(account, from, to, type, isAdmin);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}



