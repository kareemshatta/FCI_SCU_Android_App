package com.example.kareem.fci_scu_project.activities;

import android.app.Activity;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.OpenableColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kareem.fci_scu_project.R;
import com.example.kareem.fci_scu_project.Retrofit.ApiInterface;
import com.example.kareem.fci_scu_project.Retrofit.RetrofitClient;
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Multipart;
import retrofit2.http.Part;

import static com.example.kareem.fci_scu_project.Helpers.Constants.TASK_DATA;

public class CourseQuizDetailsStudentActivity extends AppCompatActivity {

    private ProgressBar progressBar;

    private static final int REQUEST_CODE = 11;
    private Button selectSolButton;
    private Button downloadButton;
    private Button sendButton;
    private TextView quizType;
    private TextView solutionName;
    private TextView quizDeadline;
    private String solName;

    private DownloadManager downloadManager;
    private String filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_quiz_details_student);

        getSupportActionBar().setTitle(TASK_DATA.getTaskName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        selectSolButton = findViewById(R.id.course_quiz_det_stud_selectBtn);
        downloadButton = findViewById(R.id.course_quiz_det_stud_downloadBtn);
        sendButton = findViewById(R.id.course_quiz_det_stud_sendBtn);
        solutionName = findViewById(R.id.course_quiz_det_stud_solutionName);

        quizDeadline = findViewById(R.id.course_quiz_det_stud_deadline);
        quizType = findViewById(R.id.course_quiz_det_stud_quizType);
        progressBar = findViewById(R.id.QuizProgressBar);

        quizDeadline.setText(TASK_DATA.getDeadlineDate() +" at " + TASK_DATA.getDeadlineTime());
        quizType.setText(TASK_DATA.getProvider());

        //loading dialog
        //initDialog();

        selectSolButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //fileChooserAction();
                launchPicker();
            }
        });

        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String taskName = TASK_DATA.getTaskName();
                downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                Uri uri = Uri.parse("https://matehub.azurewebsites.net/Data/Tasks/" + taskName);
                DownloadManager.Request request = new DownloadManager.Request(uri);
                request.setVisibleInDownloadsUi(true);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, uri.getLastPathSegment());
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                long reference = downloadManager.enqueue(request);
            }
        });
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //handle send btn here ....
                uploadFile();
                //new UploadFileAsync(getBaseContext()).execute("");
            }
        });

    }


    private void launchPicker() {
        new MaterialFilePicker()
                .withActivity(this)
                .withRequestCode(REQUEST_CODE)
                .withHiddenFiles(true)
                .withFilter(Pattern.compile(".*\\.pdf$"))
                .withTitle("Select PDF file")
                .start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            String path = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
            File file = new File(path);

            if (path != null) {
                Log.d("Path: ", path);
                filePath = path;
                Uri uri = Uri.fromFile(new File(file.getAbsolutePath()));
                solName = getFileName(uri);
                solutionName.setText(solName);
                //Toast.makeText(this, "Picked file: " + path, Toast.LENGTH_LONG).show();
            }
        }

    }

    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
        if (result == null) {
            result = uri.getLastPathSegment();
        }
        return result;
    }

    private void uploadFile() {
        if (filePath == null) {
            Toast.makeText(this, "please select an file ", Toast.LENGTH_LONG).show();
            return;
        } else {

            progressBar.setVisibility(View.VISIBLE);

            // Map is used to multipart the file using okhttp3.RequestBody
            Map<String, RequestBody> map = new HashMap<>();
            File f = new File(filePath);
            // Parsing any Media type file

            RequestBody file = RequestBody.create(MediaType.parse("application/*"), f);

            map.put("file", file);
            ApiInterface getResponse = RetrofitClient.getClient().create(ApiInterface.class);
            Call<String> call = getResponse.upload(TASK_DATA.getTaskId(), map);
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {

                    if (response.isSuccessful()){

                        String message = response.body();

                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

                        onBackPressed();


                    }else {
                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(getApplicationContext(), "problem file", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Log.v("Response gotten is", t.getMessage());
                    Toast.makeText(getApplicationContext(), "problem uploading file " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }


            });
        }
    }


/////////////////////////////////////////////////////////////////////////////

//    public void fileChooserAction(){
//        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
//        intent.setType("application/*");
//        intent.addCategory(Intent.CATEGORY_OPENABLE);
//        startActivityForResult(intent,REQUEST_CODE);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK){
//
//            if (data != null){
//
//                Uri uri = data.getData();
//                Toast.makeText(this, data.getDataString() +"\n\n"+ uri.getPath(), Toast.LENGTH_LONG).show();
//                solName = uri.getPath().substring(uri.getPath().lastIndexOf("/")+1);
//                //Toast.makeText(this, solName, Toast.LENGTH_SHORT).show();
//                solutionName.setText(solName);
//                filePath = uri.getPath();
//
//
//            }
//        }
//    }




//
//    private class UploadFileAsync extends AsyncTask<String, Void, String> {
//
//        private Context context;
//        private int serverResponseCode = 0;
//
//        public UploadFileAsync(Context context) {
//            this.context = context;
//        }
//
//        @Override
//        protected String doInBackground(String... params) {
//
//            try {
//
//                Toast.makeText(context, "executing", Toast.LENGTH_SHORT).show();
//                String sourceFileUri = filePath;
//
//                HttpURLConnection conn = null;
//                DataOutputStream dos = null;
//                String lineEnd = "\r\n";
//                String twoHyphens = "--";
//                String boundary = "*****";
//                int bytesRead, bytesAvailable, bufferSize;
//                byte[] buffer;
//                int maxBufferSize = 1 * 1024 * 1024;
//                File sourceFile = new File(sourceFileUri);
//
//                if (sourceFile.isFile()) {
//
//                    try {
//                        String upLoadServerUri = "https://matehub.azurewebsites.net/api/uploadtasksolution?taskid="+TASK_DATA.getTaskId();
//
//                        // open a URL connection to the Servlet
//                        FileInputStream fileInputStream = new FileInputStream(
//                                sourceFile);
//                        URL url = new URL(upLoadServerUri);
//
//                        // Open a HTTP connection to the URL
//                        conn = (HttpURLConnection) url.openConnection();
//                        conn.setDoInput(true); // Allow Inputs
//                        conn.setDoOutput(true); // Allow Outputs
//                        conn.setUseCaches(false); // Don't use a Cached Copy
//                        conn.setRequestMethod("POST");
//                        conn.setRequestProperty("Connection", "Keep-Alive");
//                        conn.setRequestProperty("ENCTYPE",
//                                "multipart/form-data");
//                        conn.setRequestProperty("Content-Type",
//                                "multipart/form-data;boundary=" + boundary);
//                        conn.setRequestProperty("file", sourceFileUri);
//
//                        dos = new DataOutputStream(conn.getOutputStream());
//
//                        dos.writeBytes(twoHyphens + boundary + lineEnd);
//                        dos.writeBytes("Content-Disposition: form-data; name=\"file\";filename=\""
//                                + sourceFileUri + "\"" + lineEnd);
//
//                        dos.writeBytes(lineEnd);
//
//                        // create a buffer of maximum size
//                        bytesAvailable = fileInputStream.available();
//
//                        bufferSize = Math.min(bytesAvailable, maxBufferSize);
//                        buffer = new byte[bufferSize];
//
//                        // read file and write it into form...
//                        bytesRead = fileInputStream.read(buffer, 0, bufferSize);
//
//                        while (bytesRead > 0) {
//
//                            dos.write(buffer, 0, bufferSize);
//                            bytesAvailable = fileInputStream.available();
//                            bufferSize = Math
//                                    .min(bytesAvailable, maxBufferSize);
//                            bytesRead = fileInputStream.read(buffer, 0,
//                                    bufferSize);
//
//                        }
//
//                        // send multipart form data necesssary after file
//                        // data...
//                        dos.writeBytes(lineEnd);
//                        dos.writeBytes(twoHyphens + boundary + twoHyphens
//                                + lineEnd);
//
//                        // Responses from the server (code and message)
//                        serverResponseCode = conn.getResponseCode();
//                        String serverResponseMessage = conn
//                                .getResponseMessage();
//
//                        if (serverResponseCode == 200) {
//
//                            // messageText.setText(msg);
//                            Toast.makeText(context, "File Upload Complete.",
//                                  Toast.LENGTH_SHORT).show();
//
//                            // recursiveDelete(mDirectory1);
//
//                        }
//
//                        // close the streams //
//                        fileInputStream.close();
//                        dos.flush();
//                        dos.close();
//
//                    } catch (Exception e) {
//
//                        // dialog.dismiss();
//                        e.printStackTrace();
//
//                    }
//                    // dialog.dismiss();
//
//                } // End else block
//
//
//            } catch (Exception ex) {
//                // dialog.dismiss();
//
//                ex.printStackTrace();
//            }
//            return "Executed";
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//            Toast.makeText(context, "post executing", Toast.LENGTH_SHORT).show();
//
//        }
//
//        @Override
//        protected void onPreExecute() {
//        }
//
//        @Override
//        protected void onProgressUpdate(Void... values) {
//        }
//    }

}
