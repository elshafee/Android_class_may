import kotlin.system.exitProcess

class Atm {
    var name:String = ""
    private var pinCode:Int = 0
    var balance:Float = 0f
    var selectedoperation:Int = 0
    var money:Int = 0

    fun enterYourCard(){
        print("Welcome to our Bank Atm\n")
        print("Please enter your name: ")
        name = readln()
        println("Welcome $name")
        print("Please enter your PinCode: ")
        pinCode = Integer.valueOf(readln())
        print("Please enter your Balance: ")
        balance = readln().toFloat()
        selectOperation()

    }

    fun selectOperation(){
        print("Please Select your Operation to proceed \n")
        print(" 1 => Deposit \n 2=> Withdraw \n 3=> Check the Balance \n 4=> Quit\n")
        selectedoperation = Integer.valueOf(readln())
        when(selectedoperation){
            1->deposit()
                2->withdraw()
            3->checkBalance()
            4->quit()
            else -> {
                print("invalid input")
                selectOperation()
            }
        }

    }
    fun deposit(){
        print("Please enter the amount of money you need to deposit: ")
       money = Integer.valueOf(readln())
        balance += money
        println("Deposit done Successfully")
        checkBalance()
    }

    fun withdraw(){
        println("Please enter amount of money you need to withdraw: ")
        money = Integer.valueOf(readln())
        if (money <= balance){
            balance -=money // balance = balance - money
            println("Withdraw done Successfully")
            checkBalance()
        }else{
            println("Sorry, you don't have enough money")
           checkBalance()
        }
    }

    fun checkBalance(){
     println("Your Balance is : $balance")
        doyouNeedanythingelse()
    }


    fun doyouNeedanythingelse(){
        println("Do you need anything else?")
        print("1 => Yes \n 2=> No\n")
        selectedoperation = Integer.valueOf(readln())
        when(selectedoperation){
            1-> selectOperation()
            2-> quit()
            else -> {println(
                "invalid number"
            )
            doyouNeedanythingelse()
            }
        }
    }
    fun quit(){
        println("Thanks for using our Atm, See you again")
        exitProcess(0)
    }


}