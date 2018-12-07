package pramonow.com.coroutineexample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.Contacts
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    lateinit var buttonOne:Button
    lateinit var buttonTwo:Button
    lateinit var buttonThree:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonOne = findViewById<Button>(R.id.button_one)
        buttonOne.setOnClickListener { v -> simpleCoroutine() }

        buttonTwo = findViewById<Button>(R.id.button_two)
        buttonTwo.setOnClickListener { v -> coroutineDeferAndModifyUI() }

        buttonThree = findViewById<Button>(R.id.button_three)
        buttonThree.setOnClickListener { v -> testJob() }

    }

    fun simpleCoroutine(){
        Log.d("CoroutineExample","Start")

        // Start a coroutine
        GlobalScope.launch {
            suspendFunction()
            //delay(500)
            //Log.d("CoroutineExample","global scope launch")
        }
        Log.d("CoroutineExample","Finish")

        //Start
        //Finish
        //Global Scope launch
    }

    fun coroutineDeferAndModifyUI(){
        Log.d("CoroutineExample","Start")

        val deferred = GlobalScope.async { delay(1000)
            "Message here" }

        GlobalScope.launch(Dispatchers.Main) {
            val sum = deferred.await()
            Toast.makeText(this@MainActivity,"Abc",Toast.LENGTH_SHORT).show()
        }

        Log.d("CoroutineExample","Finish")

        //Start
        //Finish
        //Toast comes out after delay
    }

    fun testJob(){
        Log.d("CoroutineExample","Start")

        val job = GlobalScope.launch { // launch new coroutine and keep a reference to its Job
            Log.d("CoroutineExample","Start Coroutine")
            delay(1000L)
            Log.d("CoroutineExample","Finish Coroutine")
        }

        GlobalScope.launch {

            Log.d("CoroutineExample","Before Job")
            job.join()
            Log.d("CoroutineExample","After Job")
        }

        //Start
        //Start Coroutine
        //Before Job
        //Finish Coroutine
        //After Job
    }

    suspend fun suspendFunction()
    {
        delay(500)
        Log.d("CoroutineExample","global scope launch")
    }

}
