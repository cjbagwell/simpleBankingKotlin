import BankAccount;

var lastId:Int = 0;
fun createBankRandomAccount():BankAccount{
    while(true) {
        val o = "Welcome to your banking system.\n" +
                "What type of account would you like to create?\n" +
                "1. Debit Account\n" +
                "2. Credit Account\n" +
                "3. Checking Account\n" +
                "Choose and option (1, 2, or 3)"
        println(o);
        val response = IntRange(1, 3).random().toString(); // Simulate Getting User Input
        if (response !in setOf("1", "2", "3")) {
            println("Invalid Selection")
            continue
        } else println("You selected option $response.")

        val accountType: String = when (response) {
            "1" -> "debit"
            "2" -> "credit"
            "3" -> "checking"
            else -> throw IllegalStateException("$response is not a valid option");
        }
        val initBalance = IntRange(0, 1000).random();
        val newAccount = BankAccount(lastId++ +1, "newUser", accountType, initBalance);
        println("You have created a $accountType account with a balance of $$initBalance")
        return newAccount
    }
}

fun createBankAccountFromUI():BankAccount {
    var accountType:String;
    while (true) {
        val o = "Welcome to your banking system.\n" +
                "What type of account would you like to create?\n" +
                "1. Debit Account\n" +
                "2. Credit Account\n" +
                "3. Checking Account\n" +
                "Choose and option (1, 2, or 3)"
        println(o);
        val response = readln()
        if (response !in setOf("1", "2", "3")) {
            println("Invalid Selection")
            continue
        } else println("You selected option $response.")

        accountType = when (response) {
            "1" -> "debit"
            "2" -> "credit"
            "3" -> "checking"
            else -> throw IllegalStateException("$response is not a valid option");
        }
        println("You are creating a $accountType account.")
        break;
    }

    // get initial account balance
    var initialBalance = 0;
    while(true) {
        if (accountType == "credit") break
        println("How much would you like to initially deposit? (enter only numbers)")
        val response = readln();
        try {
            initialBalance = response.toInt();
            break;
        } catch (e: Exception) {
            println(
                "Whoops, $response is not a valid ammount.  Make sure" +
                        " you only provide numbers (no commas or other characters)"
            )
        }
    }

    // Get Users Name
    println("Please enter the name of the account holder.")
    val accountholder = readln();
    val newAccount = BankAccount(lastId++ + 1, accountholder, accountType, initialBalance);
    println("You have created a $accountType account with a balance of $$initialBalance")
    return newAccount
}

fun performRandomWithdrawal(account:BankAccount){
    val amountToWithdrawal = IntRange(1, 100).random()
    val withdrawalSuccessful = account.withdrawal(amountToWithdrawal)
}

fun performRandomDeposit(account:BankAccount){
    val toDeposit = IntRange(1, 100).random();
    account.deposit(toDeposit);
}

fun withdrawalApplication(account:BankAccount){
    while(true){
        println("How much would you like to withdrawal?(only use numbers," +
                "or enter c to cancel)")
        val response = readln();
        if(response == "c") return
        var toWithdrawal:Int = 0;
        try{
            toWithdrawal = response.toInt();
        }
        catch(e: java.lang.Exception){
            println("Whoops, $response is not a valid amount.  Try again using only numbers.")
            continue
        }
        if(toWithdrawal <= 0){
            println("You must enter a positive value to withdrawal. Please enter another value.")
            continue
        }
        account.withdrawal(toWithdrawal)
        return
    }
}

fun depositApplication(account:BankAccount){
    while(true){
        println("\nHow much would you like to deposit?(only use numbers," +
                "or enter c to cancel)")
        val response = readln();
        if(response == "c") return
        var toDeposit:Int = 0;
        try{
            toDeposit = response.toInt();
        }
        catch(e: java.lang.Exception){
            println("Whoops, $response is not a valid amount.  Try again using only numbers.")
            continue
        }
        if(toDeposit <= 0){
            println("You must enter a positive value to deposit. Please enter another value.")
            continue
        }
        account.deposit(toDeposit)
        return
    }
}

fun runBankApplication(account:BankAccount){
    println("Hello ${account.accountHolder}, welcome back!")
    while(true){
        println("\nWhat would you like to do with your ${account.accountType} account?\n" +
                "1. Check account balance\n" +
                "2. Withdrawal money\n" +
                "3. Deposit money\n" +
                "4. Close the application\n" +
                "Which option do you choose? (1, 2, 3, or 4)")
        val response = readln();
        when(response){
            "1" -> println("Your account balance is $${account.getBalance()}")
            "2" -> withdrawalApplication(account)
            "3" -> depositApplication(account)
            "4" -> {println("Thanks for banking with us!"); return;}
            else -> println("Invalid input.  Try again.")
        }
    }
}
fun main(args: Array<String>){
//    var createNewAccount = true;
//    val listOfAccounts = mutableListOf<BankAccount>()
//    while(true) {
//        println("Create a new account?(y or n)")
//        when(readln()){
//            "y" -> println("Creating a new account")
//            "n" -> break
//            else -> {println("Invalid Response"); continue}
//        }
//        val account = createBankAccountFromUI();
//        listOfAccounts.add(account);
//    }

//    while(true){
//        println("Perform a random withdrawal? (y or n)")
//        when(readln()){
//            "y" -> performRandomWithdrawal(listOfAccounts.random())
//            "n" -> break;
//            else -> println("Invalid Response.")
//        }
//    }
//
//    while(true){
//        println("Perform a random Deposit? (y or n)")
//        when(readln()){
//            "y" -> performRandomDeposit(listOfAccounts.random())
//            "n" -> break;
//            else -> println("Invalid Response.")
//        }
//    }

    val bankAccount = createBankAccountFromUI();
    runBankApplication(bankAccount);
}


