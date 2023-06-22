package com.example.laundryiitj

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Text
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class book: Fragment(R.layout.activity_booking){
}

class Profile : AppCompatActivity() {
    private var db=Firebase.firestore


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        val name=intent.getStringExtra("Name").toString()
        val roll=intent.getStringExtra("Roll").toString()
        val laundry=intent.getStringExtra("laundry").toString()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        findViewById<TextView>(R.id.name1).text=name
        findViewById<TextView>(R.id.roll1).text=roll
        findViewById<TextView>(R.id.laundry1).text=laundry
        findViewById<Button>(R.id.book).setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.frame1, book()).commit()

        }

    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun heroheralal(view: View) {
        val roll=intent.getStringExtra("Roll").toString()
        val butt = findViewById<Button>(R.id.realbook)
            val prog = findViewById<ProgressBar>(R.id.progressBar3)
            butt.setOnClickListener {
                prog.visibility = View.VISIBLE
                val x = listOf<String>(
                    findViewById<EditText>(R.id.editTextNumberSigned).text.toString(),
                    findViewById<EditText>(R.id.editTextNumberSigned1).text.toString(),
                    findViewById<EditText>(R.id.editTextNumberSigned2).text.toString(),
                    findViewById<EditText>(R.id.editTextNumberSigned3).text.toString(),
                    findViewById<EditText>(R.id.editTextNumberSigned4).text.toString(),
                    findViewById<EditText>(R.id.editTextNumberSigned5).text.toString(),
                    findViewById<EditText>(R.id.editTextNumberSigned6).text.toString(),
                    findViewById<EditText>(R.id.editTextNumberSigned7).text.toString(),
                    findViewById<EditText>(R.id.editTextNumberSigned8).text.toString(),
                    findViewById<EditText>(R.id.editTextNumberSigned9).text.toString(),
                    findViewById<EditText>(R.id.editTextNumberSigned10).text.toString(),
                    findViewById<EditText>(R.id.editTextNumberSigned11).text.toString()
                )
                val data = hashMapOf(
                    "Shirt" to x[0],
                    "T-Shirt" to x[1],
                    "Pant" to x[2],
                    "Jeans" to x[3],
                    "Shorts" to x[4],
                    "Dupatta" to x[5],
                    "Kurta" to x[6],
                    "Pyjama" to x[7],
                    "Skirt" to x[8],
                    "Bed Sheet" to x[9],
                    "Pillow Cover" to x[10],
                    "Towel" to x[11],
                )
//                val name=intent.getStringExtra("Name").toString()
//                val roll=intent.getStringExtra("Roll").toString()
//                val laundry=intent.getStringExtra("laundry").toString()
                val current = LocalDateTime.now()
                val formatter = DateTimeFormatter.ISO_DATE_TIME
                val jhu = current.format(formatter)
                db.collection("Customers").document(roll).collection("Bookings").document(jhu)
                    .set(data).addOnCompleteListener {
                        if (it.isSuccessful) {
                            Toast.makeText(this, "Booking Success", Toast.LENGTH_SHORT).show()
                            prog.visibility=View.GONE

                            val intent=Intent(this,MainPage::class.java)
//                            intent.putExtra("Name",name)
//                            intent.putExtra("Roll",roll)
//                            intent.putExtra("laundry",laundry)
                            startActivity(intent);
                        }
                        else {
                            prog.visibility = View.GONE
                            Toast.makeText(
                                this,
                                "Booking Failed. Please try after some time",
                                Toast.LENGTH_SHORT
                            ).show()
                            prog.visibility=View.GONE
                        }

                    }
            }
    }
}