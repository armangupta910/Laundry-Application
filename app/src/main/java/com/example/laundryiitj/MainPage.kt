package com.example.laundryiitj

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class signin: Fragment(R.layout.sigin) {
}

class signup: Fragment(R.layout.signup){

}
class MainPage : AppCompatActivity() {
    private var db=Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_page)
        supportFragmentManager.beginTransaction().replace(R.id.frame,signin()).commit()


    }

    fun go1(view: View) {
        supportFragmentManager.beginTransaction().replace(R.id.frame,signin()).commit()
    }

    fun go2(view: View) {
//        val x=findViewById<ProgressBar>(R.id.progressBar)
//        x.visibility=View.GONE
        supportFragmentManager.beginTransaction().replace(R.id.frame,signup()).commit()


    }
    fun signin(view: View){

        val roll=findViewById<EditText>(R.id.emailsignin).text.toString().uppercase()
        val pass=findViewById<EditText>(R.id.passsignin).text.toString()
        val x=findViewById<ProgressBar>(R.id.progressBar2)
        x.visibility=View.VISIBLE
        if(roll!="" && pass!=""){
            db.collection("Customers").document(roll).collection("Profile").document(roll).get().addOnSuccessListener {
                if (it!=null) {
                    x.visibility=View.GONE
                    val name = it.data?.get("Name")?.toString()
                    val roll= it.data?.get("Roll Number")?.toString()
                    val pass1=it.data?.get("Password")?.toString()
                    val email= it.data?.get("Email")?.toString()
                    val altemail= it.data?.get("Alternative Email")?.toString()
                    val phone= it.data?.get("Phone Number")?.toString()
                    val hostel= it.data?.get("Hostel")?.toString()
                    val room= it.data?.get("Room Number")?.toString()
                    val laundry= it.data?.get("Laundry Number")?.toString()
                    if(pass==pass1){
                        Toast.makeText(this,"Logged In successfully",Toast.LENGTH_SHORT).show()
                        val intent=Intent(this,Profile::class.java)
                        intent.putExtra("Name",name)
                        intent.putExtra("Roll",roll)
                        intent.putExtra("laundry",laundry)
                        startActivity(intent)
                    }
                    else{
                        Toast.makeText(this,"Wrong Data Entered",Toast.LENGTH_SHORT).show()
                    }
//                    findViewById<Button>(R.id.login).text=roll


                }
                else{
                    Toast.makeText(this,"Wrong data entered",Toast.LENGTH_SHORT).show()
                    x.visibility=View.GONE
                }
            }
        }
    }
    fun register(view: View) {
        val y=findViewById<ProgressBar>(R.id.progressBar)
        y.visibility=View.VISIBLE
        val email=findViewById<EditText>(R.id.email).text.toString()
        val altemail=findViewById<EditText>(R.id.altemail).text.toString()
        val name=findViewById<EditText>(R.id.name).text.toString()
        val roll=findViewById<EditText>(R.id.roll).text.toString()
        val phone=findViewById<EditText>(R.id.phone).text.toString()
        val hostel=findViewById<EditText>(R.id.hostel).text.toString()
        val room=findViewById<EditText>(R.id.room).text.toString()
        val laundry=findViewById<EditText>(R.id.laundry).text.toString()
        val pass=findViewById<EditText>(R.id.passsignup).text.toString()
        val confpass=findViewById<EditText>(R.id.confpass).text.toString()
        val data= hashMapOf("Password" to pass,"Email" to email,"Alternative Email" to altemail,"Name" to name,"Roll Number" to roll,"Phone Number" to phone,"Hostel" to hostel,"Room Number" to room,"Laundry Number" to laundry)
        if(pass==confpass){
            db.collection("Customers").document(roll).collection("Profile").document(roll).set(data).addOnCompleteListener({
                if(it.isSuccessful){
                    Toast.makeText(this,"Your Registration is Successfully Done!!!",Toast.LENGTH_SHORT).show()
                    y.visibility=View.GONE
                }
                else{
                    Toast.makeText(this,"Some Error occured",Toast.LENGTH_SHORT).show()
                    y.visibility=View.GONE
                }
            })
            supportFragmentManager.beginTransaction().replace(R.id.frame,signin()).commit()
        }
        else{
            Toast.makeText(this,"Passwords donot match",Toast.LENGTH_SHORT).show()
        }

    }
}