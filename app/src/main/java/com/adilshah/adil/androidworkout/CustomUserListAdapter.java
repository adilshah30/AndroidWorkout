package com.adilshah.adil.androidworkout;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

/**
 * Created by adil on 17/02/2018.
 */

public class CustomUserListAdapter extends ArrayAdapter<User> {

    //private Activity context;
    private List<User> userList;
    private ImageLoader mImageLoader;

    private Activity activity;
    private LayoutInflater inflater;

    public CustomUserListAdapter(Activity activity, int resource, List<User> userList) {
        super(activity, R.layout.custom_user_list_view_row, userList);
        this.activity = activity;
        this.userList = userList;

    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater customUserListviewInflater = LayoutInflater.from( getContext() );

        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //View customuserlistviewrow =  customUserListviewInflater.inflate(R.layout.custom_user_list_view_row,parent,false);
        View customuserlistviewrow = inflater.inflate(R.layout.custom_user_list_view_row, null);
        // Instantiate the RequestQueue.


        //Load text views and image view
        TextView tvFullname = (TextView) customuserlistviewrow.findViewById( R.id.tvFullname );
        TextView tvUsername = (TextView) customuserlistviewrow.findViewById( R.id.tvUsername );
        NetworkImageView imageViewUser = (NetworkImageView) customuserlistviewrow.findViewById(R.id.imageViewUser);
        final TextView tvUserID = (TextView) customuserlistviewrow.findViewById( R.id.tvUserID );

        mImageLoader = CustomVolleyRequestQueue.getInstance(activity.getApplicationContext())
                .getImageLoader();


        User user_row = userList.get( position );

        tvFullname.setText( user_row.getFullname() );
        tvUsername.setText( user_row.getUsername() );
        tvUserID.setText( String.valueOf( user_row.getID() ));
        tvUserID.setVisibility( View.INVISIBLE );

        mImageLoader.get(user_row.getProfile_img(), ImageLoader.getImageListener(imageViewUser,
                R.drawable.ic_launcher_background, android.R.drawable
                        .ic_dialog_alert));

        imageViewUser.setImageUrl( user_row.getProfile_img() , mImageLoader);
        //tvEditUser.isClickable();

//        tvUserID.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Toast editToast = Toast.makeText( getContext(), "clicked "+ String.valueOf( tvUserID.getText() ), Toast.LENGTH_LONG );
//                editToast.show();
//            }
//        } );

        return customuserlistviewrow ;
    }
}
