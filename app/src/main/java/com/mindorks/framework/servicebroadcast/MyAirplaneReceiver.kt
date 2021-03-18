package com.mindorks.framework.servicebroadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.provider.Telephony
import android.telephony.SmsMessage
import android.widget.Toast

class MyAirplaneReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if(Settings.System.getInt(context.contentResolver, Settings.Global.AIRPLANE_MODE_ON,0) == 0){
            Toast.makeText(context, "Fligh Mode : OFF",Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(context, "Fligh Mode : ON", Toast.LENGTH_SHORT).show()
        }


        if(intent.action.equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)){

            val pdu = (intent.extras!!.get("pdus") as Array<*>).get(0)
            val myBundle = intent.extras
            val format = myBundle!!.getString("format")


            pdu.let {

                val message = SmsMessage.createFromPdu(it as ByteArray,format)
                val pesan = message.displayMessageBody
                val no_pengirim = message.displayOriginatingAddress

                Toast.makeText(context,"Phone : $no_pengirim \n" +
                        "Message : $pesan", Toast.LENGTH_SHORT).show()
            }
        }
    }
}