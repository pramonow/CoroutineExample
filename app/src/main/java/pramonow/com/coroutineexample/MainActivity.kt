package pramonow.com.coroutineexample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

        buttonOne = findViewById(R.id.button_one)
        buttonOne.setOnClickListener { simpleCoroutine() }

        buttonTwo = findViewById(R.id.button_two)
        buttonTwo.setOnClickListener { testJob() }

        buttonThree = findViewById(R.id.button_three)
        buttonThree.setOnClickListener { coroutineDeferAndModifyUI() }
    }

    fun simpleCoroutine(){
        Log.d("CoroutineExample","Start")

        // Start a coroutine
        GlobalScope.launch {
            suspendFunction()
        }
        Log.d("CoroutineExample","Finish")

        /*
        WILL PRINT IN SEQUENCE:
        - Start
        - Finish
        - Global Scope launch
        */
    }

    fun coroutineDeferAndModifyUI(){
        Log.d("CoroutineExample","Start")

        val deferred = GlobalScope.async { delay(1000)
            "Waiting for me" }

        GlobalScope.launch(Dispatchers.Main) {
            //it will wait until the deferred task finish
            val msg = deferred.await()
            Toast.makeText(this@MainActivity,msg,Toast.LENGTH_SHORT).show()
        }

        Log.d("CoroutineExample","Finish")

        /*
            ACTION IN SEQUENCE
            - Log Start
            - Log Finish
            - Toast comes out after delay
         */
    }

    fun testJob(){

        Log.d("CoroutineExample","Start")

        val job = GlobalScope.launch { // launch new coroutine and keep a reference to its Job
            Log.d("CoroutineExample","Doing a job")
            delay(1000L)
            Log.d("CoroutineExample","Finish Job")
        }

        GlobalScope.launch {
            Log.d("CoroutineExample","Before Job finish")
            job.join()
            Log.d("CoroutineExample","After Job finish")
        }

        /*
            WILL PRINT IN SEQUENCE:
            - Start
            - Doing a Job
            - Before Job finish
            - Finish Job
            - After Job finish
         */
    }

    suspend fun suspendFunction() {
        delay(500)
        Log.d("CoroutineExample","global scope launch")
    }

}
