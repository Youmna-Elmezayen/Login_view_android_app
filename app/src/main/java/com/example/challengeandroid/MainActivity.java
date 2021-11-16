package com.example.challengeandroid;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    // declare all the views that will be used in code
    Button pickImageBtn, registerBtn;
    Spinner countrySpinner;
    EditText editTextName, editTextEmail, editTextPassword, editTextReenter;
    View context;
    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews(); // initialize views

        pickImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Pick image button clicked", Toast.LENGTH_SHORT).show(); // show toast message whenever button is clicked
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEmpty() == 0) {
                    if (validateName() == 0 && validateEmail() == 0 && validatePassword() == 0 && validateAgreement() == 0) {
                        createSnackBar(); // in case all data is valid, show the user snack bar
                    }
                }
            }
        });

    }


    private void initViews() {
        //initializing views by ID given in XML
        pickImageBtn = findViewById(R.id.pickImageBtn);
        registerBtn = findViewById(R.id.registerBtn);
        countrySpinner = findViewById(R.id.spinnerCountry);
        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextReenter = findViewById(R.id.editTextReenter);
        context = findViewById(R.id.parent);
        checkBox = findViewById(R.id.checkBox);
    }

    private int isEmpty() {
        String empty = "";
        if (editTextName.getText().toString().compareTo(empty) == 0 || editTextEmail.getText().toString().compareTo(empty) == 0
                || editTextPassword.getText().toString().compareTo(empty) == 0 || editTextReenter.getText().toString().compareTo(empty) == 0) {
            Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show(); //in case that a field is left empty, warn user
            return 1;
        }
        return 0;
    }

    private int validateName() {
        // make sure name is made only of alphabets
        for (int i = 0; i < editTextName.getText().length(); i++) {
            if (Character.isDigit(editTextName.getText().charAt(i))) {
                Toast.makeText(MainActivity.this, "This name is invalid", Toast.LENGTH_SHORT).show();
                return 1;
            }
        }
        return 0;
    }

    private int validateEmail() {
        int flag = 0;
        // make sure the email contains @ symbol
        for (int i = 0; i < editTextEmail.getText().length(); i++) {
            if (editTextEmail.getText().charAt(i) == '@') {
                flag = 1;
            }
        }
        if (flag != 1) {
            Toast.makeText(MainActivity.this, "This email is invalid", Toast.LENGTH_SHORT).show(); // if email is missing @ symbol, warn user
            return 1;
        }
        return 0;
    }

    private int validatePassword() {
        String empty = "";
        // check to see if password and re enter password contain same password
        if (editTextPassword.getText().toString().compareTo(editTextReenter.getText().toString()) != 0 && editTextPassword.getText().toString().compareTo(empty) != 0) {
            Toast.makeText(MainActivity.this, "The passwords don't match", Toast.LENGTH_SHORT).show(); //if they don't match, warn user
            return 1;
        }
        return 0;
    }

    private int validateAgreement() {
        // check to see if the checkbox to agree on license is checked
        if (!checkBox.isChecked()) {
            Toast.makeText(MainActivity.this, "You need to agree on licence agreement", Toast.LENGTH_SHORT).show(); // if the box is not checked, warn user
            return 1;
        }
        return 0;
    }

    private void createSnackBar() {
        // create snack bar to tell user they registered and then clear edit texts for new data
        Snackbar.make(context, "You registered successfully.", Snackbar.LENGTH_INDEFINITE).setAction("Dismiss", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextName.getText().clear();
                editTextEmail.getText().clear();
                editTextPassword.getText().clear();
                editTextReenter.getText().clear();
            }
        }).show();
    }
}