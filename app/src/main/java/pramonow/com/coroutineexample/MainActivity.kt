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

class MainActivity : AppCompatActivity(),View.OnClickListener {

    lateinit var buttonOne:Button

    override fun onClick(p0: View?) {

        toast()
    }

    fun simpleCoroutine(){
        Log.d("CoroutineExample","Start")

        // Start a coroutine
        GlobalScope.launch {
            delay(500)
            Log.d("CoroutineExample","global scope launch")
        }
        Log.d("CoroutineExample","Finish")
    }

    fun toast(){
        Log.d("CoroutineExample","Start")

        val deferred = GlobalScope.async { delay(1000)
            "Message here" }


        GlobalScope.launch {
            val sum = deferred.await()
            //async(UI){
                Toast.makeText(this@MainActivity,"Abc",Toast.LENGTH_SHORT).show()
            //}
        }

        Log.d("CoroutineExample","Finish")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonOne = findViewById<Button>(R.id.test_button)

        buttonOne.setOnClickListener(this)

    }

   /* private fun startUpdate() {
        //resetRun()

        greenJob = launch(Android) {
            startRunning(progressBarGreen)
        }

        redJob = launch(Android) {
            startRunning(progressBarRed)
        }

        blueJob =launch(Android) {
            startRunning(progressBarBlue)
        }
    }

    private suspend fun startRunning(
            progressBar: RoundCornerProgressBar) {
        progressBar.progress = 0f
        while (progressBar.progress < 1000 && !raceEnd) {
            delay(10)
            progressBar.progress += (1..10).random()
        }
        if (!raceEnd) {
            raceEnd = true
            Toast.makeText(this, "${progressBar.tooltipText} won!",
                    Toast.LENGTH_SHORT).show()
        }
    }*/
}
