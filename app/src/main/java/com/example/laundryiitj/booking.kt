package com.example.laundryiitj

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.DateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar

class booking : AppCompatActivity() {
    private var db=Firebase.firestore
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)

        val roll=intent.getStringExtra("roll").toString().uppercase()
        val name=intent.getStringExtra("name").toString().uppercase()
        val laundry=intent.getStringExtra("laundry").toString().uppercase()
        val butt=findViewById<Button>(R.id.realbook)
//        val shirt = findViewById<>()
//        val date=Calendar.getInstance().time
        val prog=findViewById<ProgressBar>(R.id.progressBar3)
        butt.setOnClickListener {
            prog.visibility = View.VISIBLE
            val x= listOf<String>(
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
//            val shirt=findViewById<EditText>(R.id.shirt).text.toString()
//                val tshirt=findViewById<EditText>(R.id.tshirt).text.toString()
//                    val pant=findViewById<EditText>(R.id.pant).text.toString()
//                        val jeans=findViewById<EditText>(R.id.jeans).text.toString()
//                            val shorts=findViewById<EditText>(R.id.shorts).text.toString()
//                                val dupatta=findViewById<EditText>(R.id.dupatta).text.toString()
//                                    val kurta=findViewById<EditText>(R.id.kurta).text.toString()
//                                        val pyjama=findViewById<EditText>(R.id.pyjama).text.toString()
//                                            val skirt=findViewById<EditText>(R.id.skirt).text.toString()
//                                                val bed=findViewById<EditText>(R.id.bed).text.toString()
//                                                    val pillow=findViewById<EditText>(R.id.pillow).text.toString()
//                                                        val towel=findViewById<EditText>(R.id.towel).text.toString()
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
            val current = LocalDateTime.now()
            val formatter=DateTimeFormatter.ISO_DATE_TIME
            val jhu=current.format(formatter)
            db.collection("Customers").document(roll).collection("Bookings").document(jhu).set(data).addOnCompleteListener {
                if (it.isSuccessful) {
                    prog.visibility = View.GONE
                    Toast.makeText(this, "Booking Success", Toast.LENGTH_SHORT).show()
                    supportFragmentManager.beginTransaction().remove(book()).commit()
//                    val intent=Intent(this,Profile::class.java)
//                    intent.putExtra("Name",name)
//                    intent.putExtra("Roll",roll)
//                    intent.putExtra("laundry",laundry)
//                    startActivity(intent)
                } else {
                    prog.visibility = View.GONE
                    Toast.makeText(
                        this,
                        "Booking Failed. Please try after some time",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
        }

    }


}