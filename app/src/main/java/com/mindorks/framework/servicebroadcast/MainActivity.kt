package com.mindorks.framework.servicebroadcast

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Telephony
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //service
//        var myService = Intent(this, SendMyService::class.java)
//        startMyService.setOnClickListener {
//            startService(myService)
//        }
//        stopMyService.setOnClickListener {
//            stopService(myService)
//        }


        //Intent Service
        var myService = Intent(this, MyIntentService::class.java)
        startMyService.setOnClickListener {
            myService.putExtra(EXTRA_TIME,1000L)
            MyIntentService.enqueueWork(this,myService)
        }
        stopMyService.setOnClickListener {

        }


        //broadcast receiver part 1
        //Contoh 1
        var AirplaneReceiver = MyAirplaneReceiver()
        var filter = IntentFilter()
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        filter.addAction(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)
        registerReceiver(AirplaneReceiver, filter)

        //Contoh 2
//        var SMSReceiver = MySMSReceiver()
//        var filter2 = IntentFilter()
//        filter2.addAction(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)
//        registerReceiver(SMSReceiver, filter2)
    }
}