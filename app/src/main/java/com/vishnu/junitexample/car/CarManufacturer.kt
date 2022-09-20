package com.vishnu.junitexample.car

class CarManufacturer(private val carManufacturerHelper: CarManufacturerHelper) {
    private val colorProducer = ColorProducer()
    private val myCar = Car()
    private val engine = Engine()

    private var myCarName =""

    var myCarNameByGetter = ""
        get() = field
        private set

    fun getCar(): Car {
        val car = Car()
        car.name = "maruti"
        car.color = colorProducer.color
        return car
    }
    fun getCarWithEngine(): Car {
        val car = Car()
        engine.power = 120
        car.engine = engine
        return car
    }


    fun maxSpeed(car: Int) {
        myCar.speed = car
    }

    fun initializeCarNameOnPrivateVariable(name: String) {
        myCarName = name
    }
    fun initializeCarNameOnPrivateVariablePublicGetter(name: String) {
        myCarNameByGetter = name
    }


    fun getCarName() = carManufacturerHelper.getCarName()

    /***********************************************************************************************/

    fun divide(a: Int, b: Int) = a / b

    fun returnList(list: ArrayList<String>): ArrayList<String> {
        list.add("")
        return list
    }

//    fun redirectReturnMessage() {
//        returnMessage()
//    }


}