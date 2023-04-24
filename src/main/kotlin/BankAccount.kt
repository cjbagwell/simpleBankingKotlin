class BankAccount(
    val id: Int,
    val accountHolder: String,
    val accountType: String,
    private var balance:Int){
    fun withdrawal(amount: Int):Int{
        if(amount<=0) throw IllegalArgumentException("amount must be positive")
        val withdrawn = when(accountType){
            "checking" -> checkingWithdrawal(amount)
            "debit"    -> debitWithdrawal(amount)
            "credit"   -> creditWithdrawal(amount)
            else -> throw IllegalStateException("accountType must be 'checking'," +
                    " 'debit', or 'credit'. accountType was '$accountType'")
        }
        println("You withdrew $$withdrawn from a $accountType account with id $id.  Balance is now $$balance.")
        return withdrawn;
    }

    private fun checkingWithdrawal(amount: Int):Int{
        balance -= amount;
        return amount;
    }

    private fun debitWithdrawal(amount:Int):Int{
        if(amount > balance) {
            println("You can't withdrawal more money than you have in your debit account")
            return 0
        }
        balance -= amount;
        return amount
    }

    private fun creditWithdrawal(amount:Int):Int{
        balance += amount;
        return amount
    }

    fun deposit(amount: Int):Int{
        if(amount<=0) throw IllegalArgumentException("amount must be positive")
        val deposited = when(accountType){
            "debit", "checking" -> debitCheckingDeposit(amount);
            "credit" -> creditDeposit(amount)
            else -> throw IllegalStateException("accountType must be 'checking'," +
                    " 'debit', or 'credit'. accountType was '$accountType'")
        }
        println("You deposited $$deposited to a $accountType account with id $id.  Balance is now $$balance.")
        return deposited
    }

    private fun debitCheckingDeposit(amount:Int):Int{
        balance += amount;
        return amount;
    }

    private fun creditDeposit(amount:Int):Int{
        if(balance == 0) {
            println("You don't need to deposit anything in order to pay off" +
                    " your account because it already paid off.")
            return 0
        }
        if(balance < amount){
            println("Deposit failed.  You tried to pay off an amount greater than the account balance.")
            return 0;
        }
        balance -= amount;
        return amount;
    }

    fun getBalance():Int = this.balance
}