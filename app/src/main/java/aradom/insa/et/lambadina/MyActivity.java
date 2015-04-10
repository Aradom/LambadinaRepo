package aradom.insa.et.lambadina;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.Camera;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;


public class MyActivity extends ActionBarActivity {
    public static Camera myCamera = null;
    private ToggleButton myToggleBtn;
    private boolean toggleBtnState;
    private static Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        myToggleBtn = (ToggleButton)findViewById(R.id.switchLambadina);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }


    @Override
    protected void onPause(){
        super.onPause();
        bundle.putBoolean("ToggleButtonState", myToggleBtn.isChecked());
    }

    @Override
    protected void onResume(){
        super.onResume();
        myToggleBtn.setChecked(bundle.getBoolean("ToggleButtonState", true));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
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

    public void startService(View view) {
        toggleBtnState = ((ToggleButton)view).isChecked();
        startService(new Intent(getBaseContext(), LambadinaService.class));
    }

    // Method to stop the service
    public void stopService(View view) {
        toggleBtnState = ((ToggleButton)view).isChecked();
        stopService(new Intent(getBaseContext(), LambadinaService.class));
    }


    boolean cameraHasFlash(){
        if(getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH))
            return true;
        return false;
    }

    public void onLambadinaClick(View view){
        boolean isCameraOn = ((ToggleButton)view).isChecked();

        if(cameraHasFlash())
        {
            boolean isCameraStateOn = false;
            if(!isCameraOn){
                startService(view);
            }
            else {
                stopService(view);
            }
        }

        else{
            Toast.makeText(getBaseContext(), "The camera doesn't have Flash light", Toast.LENGTH_SHORT).show();
        }
    }
}
