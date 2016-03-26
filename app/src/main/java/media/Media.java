package media;

import android.content.Context;
import android.media.MediaRecorder;
import android.widget.Toast;

/**
 * Created by mzalih on 26/03/16.
 */
public class Media {

    private static Media instance;
    private static MediaRecorder recorder;
    private Context ctx;

    private static  Media instance(Context ctx){
        // JUST RETURN A STATIC INSTANCE
        // ITS A SINGLETON CLASS
        if(instance == null){
            instance = new Media();
        }
        // SET UP CONTEXT IF NEED
        if(ctx != null){
            instance.ctx = ctx;
        }
        return instance;
    }

    /*
     * PUBLIC  API
     * STARTS  A AUDIO RECORDS TO A FILE PATH
     */
    public static Media startRecordAudio(Context ctx,String outPutFile){
        // THIS METHOD INITIATE A FREE RECORD
        if(instance(ctx).recordAudio(outPutFile)){
            return instance(ctx);
        }
        return  null;
    }
    /*
     * PUBLIC  API
     * STOPS  CURRENT  AUDIO RECORDS
     */
    public static Media stopRecordAudio(Context ctx,String outPutFile){
        // THIS METHOD INITIATE  STP RECORD
        if(instance(ctx).stopRecording()){
            return instance(ctx);
        }
        return  null;
    }

    /*
     * PRIVATE  API
     * STOPS  AUDIO RECORDS
     */

    private Boolean stopRecording() {
        if (recorder == null) {
            try {
                recorder.stop();
                recorder.release();
                recorder = null;
                return  true;


            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }
    /*
     * PRIVATE  API
     * STARTS A AUDIO RECORDS TO A FILE PATH
     */
    private Boolean recordAudio(String outputFile){
        // CHECK AN RECORD ALREADY EXISTS
    if(recorder == null) {
        try {
            // CREATE A NEW RECORD
            recorder = new MediaRecorder();
            recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            recorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
            recorder.setOutputFile(outputFile);
            recorder.prepare();
            // START RECORDING
            recorder.start();

        }catch (Exception e){
            // SOME EXCEPTION
            return false;
        }

    }else{
        // A recording is on progress plese stop it before a new record
        showToast("A recording is on progress plese stop it before a new record");
        return false;
    }
        // SUCCESS OF RECORD START
        return  true;
    }
    /*
     * PRIVATE  API
     * SHOW A TOAST
     */
    private  void showToast(String message){
        if(ctx != null){
            Toast.makeText(ctx, message,
                    Toast.LENGTH_LONG).show();
            
        }
    }
}
