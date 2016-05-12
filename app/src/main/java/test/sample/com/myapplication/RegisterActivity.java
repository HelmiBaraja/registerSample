package test.sample.com.myapplication;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, RequestSubmission.RequestSubmissionInterface {

    private EditText ETDob;
    private EditText ETName;
    private EditText ETPassword;
    private EditText ETReEnterPassword;
    private EditText ETEmailAddress;
    private EditText ETHandphoneNo;
    private RadioGroup RGGender;
    private ProgressDialog progressRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        ETDob = (EditText) findViewById(R.id.ETDateBirth) ;
        ETName = (EditText) findViewById(R.id.ETName) ;
        ETPassword = (EditText) findViewById(R.id.ETPassword) ;
        ETReEnterPassword = (EditText) findViewById(R.id.ETRePassword) ;
        ETEmailAddress = (EditText) findViewById(R.id.ETEmailAddress) ;
        ETHandphoneNo = (EditText) findViewById(R.id.ETHandphoneNo) ;
        RGGender = (RadioGroup) findViewById(R.id.RGGender);

        progressRegister = new ProgressDialog(this);
        progressRegister.setTitle("Registering");
        progressRegister.setMessage("Please wait while we register your account");
    }

    //onClick
    public void openDOB(View view)
    {
        DialogFragment dialog = new DateDialog(this);
        dialog.show(getFragmentManager(), "tag");
    }

    public void register(View view)
    {
        //show progress bar

        if (isInputsVerified())
        {
            //this can proceed
        }
        else{
            //do not proceed.
        }

        if (isInputsVerified())
        {
            progressRegister.show();

            UserInfo userInfo = new UserInfo(ETName.getText().toString(),ETEmailAddress.getText().toString(),
                    ETPassword.getText().toString(),ETHandphoneNo.getText().toString(),
                    ETDob.getText().toString(),getGender());

            RequestSubmission submission=  new RequestSubmission(userInfo);
            submission.requestInterface = this;
            submission.submit();
        }
    }

    private boolean isInputsVerified()
    {
        boolean isVerify = false;

        if (ETName.getText().toString().length()==0)
        {
            ETName.setError("please enter your name");
        }

        if (isEditTextEmpty(ETName))
        {
            //you may choose to show error as pop up or show error ballon in edit text
            ETName.setError("Please enter your name"); //ballon tips
        }
        else if (isEditTextEmpty(ETEmailAddress))
        {
            ETEmailAddress.setError("Please enter your email address");
        }
        else if (!isValidEmail(ETEmailAddress.getText().toString()))
        {
            ETEmailAddress.setError("Your email address is not valid, please check");
        }
        else if (isEditTextEmpty(ETHandphoneNo))
        {
            ETHandphoneNo.setError("Please enter your handphone no");
        }
        else if (isEditTextEmpty(ETPassword))
        {
            ETPassword.setError("Please enter your password");
        }
        else if (isEditTextEmpty(ETReEnterPassword))
        {
            ETReEnterPassword.setError("Please re-enter your password");
        }
        else if (!areSameValues(ETPassword,ETReEnterPassword))
        {
            Util.ShowDismissDialog("","Your password doesn't not match, please enter correctly",this);
        }
        else if (isEditTextEmpty(ETDob))
        {
            ETDob.setError("Please enter your date of birth");
        }
        else if (getGender() == null)
        {
            Util.ShowDismissDialog("","Please select your gender",this);
        }
        else{
            isVerify = true;
        }

        return isVerify;
    }

    //get if edit text is empty
    private boolean isEditTextEmpty(EditText editText)
    {
        if (editText.getText().toString().trim().length() == 0)
        {
            return true;
        }
        else
            return false;
    }

    private boolean areSameValues (EditText editText1, EditText editText2)
    {
        if (editText1.getText().toString().equals(editText2.getText().toString()))
        {
            return true;
        }
        else{
            return false;
        }
    }

    private boolean isValidEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    public String getGender()
    {
        int gSelected = RGGender.getCheckedRadioButtonId();
        if (gSelected ==-1)
        {
            new AlertDialog.Builder(this)
                    .setTitle("")
                    .setMessage("Please enter gender")
                    .setNegativeButton("Dismiss", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            dialog.dismiss();
                        }
                    })
                    .show();

            return null;
        }

        return  gSelected == R.id.RBFemale ? "Female" : "Male";

    }

    //Request Submission Interface
    @Override
    public void requestSuccess() {
        Util.ShowDismissDialog("Success","Your account succesfully registered",RegisterActivity.this);
        progressRegister.dismiss();

    }

    @Override
    public void requestFailed() {
        Util.ShowDismissDialog("Failed","We failed to register your account, please retry",RegisterActivity.this);
        progressRegister.dismiss();

    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        String month =  new DateFormatSymbols().getMonths()[monthOfYear];
        EditText dob = (EditText) findViewById(R.id.ETDateBirth) ;
        dob.setText(dayOfMonth + " " + month + " " + year);
    }


    //Date Dialog Class
    public class DateDialog extends DialogFragment {

        private DatePickerDialog.OnDateSetListener listener;

        public DateDialog(DatePickerDialog.OnDateSetListener listener) {
            this.listener=listener;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of TimePickerDialog and return it
            return new DatePickerDialog(getActivity(), listener, year,month,day);
        }
    }
}
