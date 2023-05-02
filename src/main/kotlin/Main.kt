fun main() {
//val atm = Atm()
//    atm.enterYourCard()

//    val bird = Duck()
//    bird.fly()
//    bird.eat()
    var value = sum(10,11,12)
    println(value)
   var value2 = sum(12f,10f)
    println(value2)

}


fun sum(a:Int, b:Int) :Int{
    return a+b
}
fun sum(a:Int, b:Int,c:Int):Int{
    return a+b+c
}
fun sum(a:Float, b:Float):Float{
    return a+b
}

//var a = 1
//    var b = 10
////    println(a+b)
////    println(a-b)
////    println(a*b)
////    println(a/b)
////    println(a%b)
//
//
////        if (a > b) {
////            println("A is greater than B")
////        } else if (a == b) {
////            println("A is equal B")
////
////        } else {
////            println("A is less than B")
////
////        }
////var age = 20
//
//
////    var isboy = true
////
////if (isboy && age> 12){
////    println("allowed")
////}else{
////    println("reject")
////
////}
//print("Please enter your name: ")
//    val name = readLine()
//    println("Your name is " + name)
//
//    print("Please enter your age: ")
//    val age:Int = Integer.valueOf(readLine())
//    println("Your age is: " + age)

//    print("Please enter number from 1 to 10")
//    val number:Int = Integer.valueOf(readLine())
//
//    when(number){
//        in 1..5 -> println("less than 5")
//        in 6..10 -> println("greater than 5")
//        else -> println("invalid")
//    }
//val username = 'A'