package aradom.insa.et.lambadina;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Camera;
import android.os.IBinder;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

public class LambadinaService extends Service {
    public static Camera myCamera = null;
    private ToggleButton myToggleBtn;

    public LambadinaService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        myCamera = Camera.open();
        Camera.Parameters p = myCamera.getParameters();
        p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        myCamera.setParameters(p);
        myCamera.startPreview();

        return START_STICKY;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        myCamera.stopPreview();
        myCamera.release();
        myCamera = null;
    }
}
