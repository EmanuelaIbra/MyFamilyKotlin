package com.emanuelaibra.secondkotlin

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.security.Permission

class MainActivity : AppCompatActivity() {

    val permissions= arrayOf(
        android.Manifest.permission.ACCESS_FINE_LOCATION,
        android.Manifest.permission.READ_CONTACTS,
    )
    val permissionCode=78

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        askPermission()
         val bottomBar=findViewById<BottomNavigationView>(R.id.bottom_bar)
        bottomBar.setOnItemSelectedListener { menuItem ->

            if (menuItem.itemId==R.id.menu_guard){

                inflateFragment(SecurityFragment.newInstance())

            }else if(menuItem.itemId==R.id.menu_home){

                inflateFragment(HomeFragment.newInstance())
            }
            else if(menuItem.itemId==R.id.menu_dashboard){

                inflateFragment(MapsFragment())
            }
            else if(menuItem.itemId==R.id.menu_profile){

                inflateFragment(ProfileFragment.newInstance())
            }
            true
        }
        bottomBar.selectedItemId=R.id.menu_home
    }

    private fun askPermission() {
       ActivityCompat.requestPermissions(this,permissions,permissionCode)
    }


    private fun inflateFragment(newInstance: Fragment) {
       val transaction=supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,newInstance)
        transaction.commit()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        
        if(requestCode==permissionCode){
            if(allPermission()){
              //  openCamera()
            }else{
                
            }
        }
    }

    private fun openCamera() {
        val intent= Intent("android.media.action.IMAGE_CAPTURE")
        startActivity(intent)

    }

    private fun allPermission(): Boolean {
        for(item in permissions){
            if(ContextCompat.checkSelfPermission(this,item)
                !=PackageManager.PERMISSION_GRANTED){
                return false
        }

    }
    return true
}
}