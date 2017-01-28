package com.fm.dev.exercises.palindromo;

import com.fm.dev.exercises.palindromo.utils.DefaultCheckedException;
import com.fm.dev.exercises.palindromo.utils.Validation;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Activity de Inicio
 */
public class MainActivity extends AppCompatActivity {

    private Button buttonValidatePalindrome;
    private EditText editTextPalindrome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Load an ad into the AdMob banner view.
        AdView adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("2C49329C3440F4E9E3F427831CC6CDC")  // An example device ID
                .setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);

        buttonValidatePalindrome = (Button) findViewById(R.id.buttonValidatePalindrome);
        buttonValidatePalindrome.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                editTextPalindrome = (EditText) findViewById(R.id.editTextPalindrome);
                Editable editableEditTextPalindrome = editTextPalindrome.getText();
                String strEditTextPalindrome = editableEditTextPalindrome.toString();

                //String android_id = Settings.Secure.getString(PalindromoActivity.this.getContentResolver(), Settings.Secure.ANDROID_ID);
                //String deviceId = md5(android_id).toUpperCase();
                //Log.i("device id=",deviceId);

                try{
                    if(Validation.isPalindrome(strEditTextPalindrome)){
                        Toast.makeText(MainActivity.this,"Es palindromo",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(MainActivity.this,"No es palindromo",Toast.LENGTH_SHORT).show();
                    }
                }catch(DefaultCheckedException dce){
                    Toast.makeText(MainActivity.this,dce.getMessage(),Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private String md5(String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i=0; i<messageDigest.length; i++)
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

}
