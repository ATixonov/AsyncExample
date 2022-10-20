package ru.synergy.asyncexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class AsyncTaskExample extends AppCompatActivity {
    private TextView mInfoTextView;
    private ProgressBar mProgressBar;
    private Button mStartButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task_example);
        mInfoTextView = (TextView) findViewById(R.id.textView);
        mStartButton = (Button) findViewById(R.id.button);
        mProgressBar = findViewById(R.id.progressBar);
    }
    public void onClick(View v) {
        CourierTask courierTask = new CourierTask();
        courierTask.execute();
    }
    class CourierTask extends AsyncTask<Void, Integer, Void> {
        @Override
        protected void onPreExecute() {
            mInfoTextView.setText("Courier in da house");
            mStartButton.setVisibility(View.INVISIBLE);
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            mInfoTextView.setText("Floor " + values[0]);
            mProgressBar.setProgress(values[0]);
        }
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                int numOfFloors = 14;
                int counter = 0;
                for (int i = 0; i < numOfFloors; i++) {
                    getFloor(counter);
                    publishProgress(++counter);
                }
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void unused) {
            mInfoTextView.setText("Ding-dong, take your pizza.");
            mStartButton.setVisibility(View.VISIBLE);
            mProgressBar.setProgress(0);
        }
        private void getFloor(int counter) throws InterruptedException {
            TimeUnit.SECONDS.sleep(1);
        }
    }
}