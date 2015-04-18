package app.nevvea.weclean;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.internal.ImageDownloader;
import com.facebook.internal.ImageRequest;
import com.facebook.internal.ImageResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by Anna on 4/17/15.
 */
public class AccountFragment extends Fragment {

    private static final String NAME = "name";
    private static final String ID = "id";
    private static final String PICTURE = "picture";
    private static final String EMAIL = "email";
    private static final String FIELDS = "fields";

    private static final String REQUEST_FIELDS =
            TextUtils.join(",", new String[]{ID, NAME, PICTURE, EMAIL});

    private AccessTokenTracker accessTokenTracker;
    private CallbackManager callbackManager;
    private JSONObject user;

    private Drawable userProfilePic;
    private TextView accountUserName, accountUserEmail;
    private ImageView accountUserPic;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_account, container, false);

        Button button = (Button) view.findViewById(R.id.jumptologin_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

        accountUserName = (TextView) view.findViewById(R.id.account_user_name);
        accountUserEmail = (TextView) view.findViewById(R.id.account_user_email);
        accountUserPic = (ImageView) view.findViewById(R.id.account_user_profilepic);

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken,
                                                       AccessToken currentAccessToken) {
                fetchUserInfo();

            }
        };
        callbackManager = CallbackManager.Factory.create();

        return view;
    }


    private void fetchUserInfo() {
        final AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken != null) {
            GraphRequest request = GraphRequest.newMeRequest(
                    accessToken, new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(JSONObject me, GraphResponse response) {
                            user = me;
                            updateUI();
                        }
                    });
            Bundle parameters = new Bundle();
            parameters.putString(FIELDS, REQUEST_FIELDS);
            request.setParameters(parameters);
            GraphRequest.executeBatchAsync(request);
        } else user = null;
    }

    private void updateUI(){
        if (!isAdded()) {
            return;
        }

        if (user != null) {
            accountUserName.setText(user.optString("name"));
            accountUserEmail.setText(user.optString("email"));

            ImageRequest request = getImageRequest();
            if (request != null){
                Uri imageUri = request.getImageUri();
                try {
                    InputStream inputStream = getActivity().getContentResolver().openInputStream(imageUri);
                    userProfilePic = Drawable.createFromStream(inputStream, imageUri.toString() );
                } catch (FileNotFoundException e) {
                    userProfilePic = getResources().getDrawable(R.drawable.pic_anna);
                }
                accountUserPic.setImageDrawable(userProfilePic);
            }






        }

        try {
            Log.d("TAAAAAAAAAG", user.toString(4));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private ImageRequest getImageRequest() {
        ImageRequest request = null;
        ImageRequest.Builder requestBuilder = new ImageRequest.Builder(
                getActivity(),
                ImageRequest.getProfilePictureUri(
                        user.optString("id"),
                        getResources().getDimensionPixelSize(
                                R.dimen.user_profile_picture_width),
                        getResources().getDimensionPixelSize(
                                R.dimen.user_profile_picture_height)));

        request = requestBuilder.setCallerTag(this)
                .setCallback(
                        new ImageRequest.Callback() {
                            @Override
                            public void onCompleted(ImageResponse response) {
                                processImageResponse(user.optString("id"), response);
                            }
                        })
                .build();
        return request;
    }

    private void processImageResponse(String id, ImageResponse response) {
        if (response != null) {
            Bitmap bitmap = response.getBitmap();
            if (bitmap != null) {
                BitmapDrawable drawable = new BitmapDrawable(
                        AccountFragment.this.getResources(), bitmap);
                drawable.setBounds(0, 0,
                        getResources().getDimensionPixelSize(
                                R.dimen.user_profile_picture_width),
                        getResources().getDimensionPixelSize(
                                R.dimen.user_profile_picture_height));
                userProfilePic = drawable;

            }
        }
    }
}
