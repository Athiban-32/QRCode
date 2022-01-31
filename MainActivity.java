package com.example.qrcode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class MainActivity extends AppCompatActivity {

    EditText etInput;
    Button btGenerate;
    ImageView ivOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etInput=findViewById(R.id.et_input);
        btGenerate=findViewById(R.id.bt_generate);
        ivOutput=findViewById(R.id.iv_output);

        btGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get the input values from the edittext
                String sText=etInput.getText().toString().trim();

                //Initialize the multiformat writer
                MultiFormatWriter writer=new MultiFormatWriter();
                try {
                    //Intialize the bit Matrix
                    BitMatrix matrix = writer.encode(sText, BarcodeFormat.QR_CODE,
                            350, 350);
                    //Initialize the barcode enocoder
                    BarcodeEncoder encoder=new BarcodeEncoder();

                    //Initialize the Bitmap
                    Bitmap bitmap=encoder.createBitmap(matrix);

                    //set bitmap on imageview
                    ivOutput.setImageBitmap(bitmap);

                    //Initialize input manager
                    InputMethodManager manager=(InputMethodManager) getSystemService(
                            Context.INPUT_METHOD_SERVICE
                    );
                    //Hide soft Keyboard
                    manager.hideSoftInputFromWindow(etInput.getApplicationWindowToken()
                    ,0);

                }catch (WriterException e){
                    e.printStackTrace();
                }
            }
        });
    }
}