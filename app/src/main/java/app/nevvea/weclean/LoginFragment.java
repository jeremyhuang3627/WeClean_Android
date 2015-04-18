package app.nevvea.weclean;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;
import java.util.zip.Inflater;

/**
 * Created by Anna on 4/14/15.
 *
 * This class is the login fragment that pops out when the user need to login.
 */
public class LoginFragment extends Fragment {

    private LoginButton loginButton;                             // the Facebook login button
    private TextView skipLoginButton;                            // the "Continue as guest" button
    private SkipLoginCallback skipLoginCallback;
    private CallbackManager callbackManager;


    public interface SkipLoginCallback {
        void onSkipLoginPressed();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // inflate the fragment with layout
        View view = inflater.inflate(R.layout.login_fragment, container, false);

        // set up callbackmanager, login button and cancel button
        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) view.findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList("email"));
        loginButton.setFragment(this);


        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(getActivity(), "Login successful", Toast.LENGTH_SHORT).show();
                getActivity().finish(); // after
            }

            @Override
            public void onCancel() {
                Toast.makeText(getActivity(), "Login canceled", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(getActivity(), "Login error", Toast.LENGTH_SHORT).show();
            }
        });

        skipLoginButton = (TextView) view.findViewById(R.id.skip_login_button);
        skipLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (skipLoginCallback != null) {
                    skipLoginCallback.onSkipLoginPressed();
                }
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void setSkipLoginCallback(SkipLoginCallback callback) {
        skipLoginCallback = callback;
    }


}
