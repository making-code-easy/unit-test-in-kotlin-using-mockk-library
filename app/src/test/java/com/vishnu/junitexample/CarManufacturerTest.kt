package com.vishnu.junitexample

import com.vishnu.junitexample.car.Car
import com.vishnu.junitexample.car.CarManufacturer
import com.vishnu.junitexample.car.Engine
import io.mockk.*
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class CarManufacturerTest {

    private lateinit var carManufacturer: CarManufacturer

    @Before
    fun setUp() {
        carManufacturer = spyk(CarManufacturer())
    }

    @After
    fun tearDown() {

    }

    /*********io.mockk.MockKException: Can't instantiate proxy for class kotlin.String*************/
    @Test
    fun `test returnCarName`() {
        val cm = mockk<CarManufacturer>()
        every { cm.returnCarName() } returns mockk()
        cm.returnCarName()
    }
    /**********************************************************************************************/


    /**********************************HANDLING ABOVE EXCEPTION************************************/
    @Test
    fun `test returnCarName2`() {
        val cm = mockk<CarManufacturer>()
        every { cm.returnCarName() } returns "audi"
        val result = cm.returnCarName()
        assertEquals("audi", result)
    }

    /**********************************************************************************************/


    /*******************************USE OF mockkConstructor*****************************************/
    @Test
    fun `test getCar`() {
        mockkConstructor(Car::class)
        every {
            anyConstructed<Car>().color
        } returns "blue"

        assertEquals("blue", carManufacturer.getCar().color)
    }
    /**********************************************************************************************/


    /*********************WILL THROW io.mockk.MockKException: no answer found *********************/
    @Test
    fun `test getCarWithEngine1`() {
        mockkConstructor(Car::class)
        val engine = mockk<Engine>()

        engine.power = 200

        every { anyConstructed<Car>().engine } returns engine

        val result = carManufacturer.getCar()

        assertEquals(200, result.engine.power)

    }
    /**********************************************************************************************/


    /**************************ONE WAY TO HANDLE ABOVE EXCEPTION************************************/
    @Test
    fun `test getCarWithEngine2`() {
        mockkConstructor(Car::class)
        val engine = Engine()

        engine.power = 200

        every { anyConstructed<Car>().engine } returns engine

        val result = carManufacturer.getCar()

        assertEquals(200, result.engine.power)

    }
    /**********************************************************************************************/


    /**************************SECOND WAY TO HANDLE ABOVE EXCEPTION********************************/
    @Test
    fun `test getCarWithEngine3`() {
        mockkConstructor(Car::class)

        val engine = mockk<Engine>()

        every { engine.power } returns 200

        every {
            anyConstructed<Car>().engine
        } returns engine

        val result = carManufacturer.getCar()

        assertEquals(200, result.engine.power)

    }
    /**********************************************************************************************/


    /**********************************USE OF CHAINED CALLS****************************************/
    @Test
    fun `test getCarWithEngine4`() {
        mockkConstructor(Car::class)
        every {
            anyConstructed<Car>().engine.power
        } returns 200

        val result = carManufacturer.getCar()

        assertEquals(200, result.engine.power)
    }
    /**********************************************************************************************/


    /****************************TEST PRIVATE VARIABLE BY MAKING GETTER****************************/
    @Test
    fun `test myCarName`() {
        carManufacturer.showCarName("BMW")
        assertEquals("BMW", carManufacturer.myCarNameByGetter)

    }
    /**********************************************************************************************/


    /****************************TEST PRIVATE VARIABLE BY CAPTURING SLOT****************************/
    @Test
    fun `tes myCarName2`() {
        val slot = slot<String>()
        var myCarNme = ""

        every { carManufacturer.showCarName2(capture(slot)) } answers { myCarNme = slot.captured }
        carManufacturer.showCarName2("AUDI")
        assertEquals("AUDI", myCarNme)

    }

    @Test
    fun `test myCarNam3`() {
        val slot = slot<String>()
        every { carManufacturer.showCarName2(capture(slot)) } just runs
        carManufacturer.showCarName2("AUDI")
        assertEquals("AUDI", slot.captured)

    }

    /*********************************************************************************************/

}