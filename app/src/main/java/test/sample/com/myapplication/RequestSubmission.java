package test.sample.com.myapplication;

import android.os.Handler;
import android.util.Log;

import java.util.Random;

/**
 * Created by HelmiHasan on 12/05/2016.
 */
public class RequestSubmission {

    public interface RequestSubmissionInterface{
        void requestSuccess();
        void requestFailed();
    }

    private UserInfo userInfo;
   public RequestSubmissionInterface requestInterface;



    public RequestSubmission(UserInfo userInfo)
    {
        this.userInfo = userInfo;
        Log.d("verified values", userInfo.toString());
    }

    public void submit()
    {
        //Runnable methods.
        Runnable progressRunnable = new Runnable() {
            @Override
            public void run() {
                randomGeneration();
            }
        };

        //to post delay 3 seconds.
        Handler pdCanceller = new Handler();
        pdCanceller.postDelayed(progressRunnable, 3000);


    }

    private void randomGeneration()
    {
        Random rand = new Random();

        //generate random boolean, either true or false
        if (rand.nextBoolean())
        {
            requestInterface.requestSuccess();
        }
        else{
            requestInterface.requestFailed();
        }
    }
}
