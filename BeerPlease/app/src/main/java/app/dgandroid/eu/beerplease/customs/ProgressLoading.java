package app.dgandroid.eu.beerplease.customs;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by Duilio on 23/05/2017.
 */

public class ProgressLoading {

    private Context context;
    private ProgressDialog progressDialog;

    public ProgressLoading(Context context){
        this.context = context;
        this.progressDialog = new ProgressDialog(context);
    }

    public void onShow(){
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public void dismiss(){
        progressDialog.dismiss();
    }
}