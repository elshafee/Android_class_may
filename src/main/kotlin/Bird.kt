open class Bird {

    open fun fly() {
        println("Bird Can Fly")
    }
}

class Duck : Bird(),animal{

    override fun fly(){
        println("Duck can't fly")
    }
}
interface animal  {
    fun eat() {
        println("can eat anything")
    }
}