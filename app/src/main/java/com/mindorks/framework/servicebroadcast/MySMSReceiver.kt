package com.mindorks.framework.servicebroadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import android.telephony.SmsMessage
import android.widget.Toast

class MySMSReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
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