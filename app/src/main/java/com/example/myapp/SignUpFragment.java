package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

public class SignUpFragment extends Fragment {
    public interface SignUpListener{
        void changeFragmentToLogIn();
    }
    SignUpFragment.SignUpListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // if (getActivity() instanceof LogInListener) {
        //  listener = (LogInListener) getActivity();
        // } else {
        // throw new RuntimeException(getActivity().toString()
        //     + " must implement LogInListener");
        // }
        return inflater.inflate(R.layout.sign_up_layout,container,false);

    }
    private LiveData<List<User>> currentLiveData;
    @Override
    public void onViewCreated( View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button signUp = view.findViewById(R.id.button);
        EditText addName = view.findViewById(R.id.editTextText);
        EditText addPassword = view.findViewById(R.id.editTextTextPassword);
        EditText confirmPassword = view.findViewById(R.id.editTextTextPassword3);
        EditText addEmail = view.findViewById(R.id.editTextTextEmailAddress);
        Button moveToLogIn = view.findViewById(R.id.button2);
        DB db =DB.getDatabase(this.getContext());

        UserDao userDao = db.userDao();

        moveToLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WelcomeActivity welcomeActivity = (WelcomeActivity) getActivity();
                welcomeActivity.changeFragmentToLogIn();
            }

        });
            signUp.setOnClickListener(new View.OnClickListener() {
             @Override
              public void onClick(View view) {
                 String lowerCase = addName.getText().toString().toLowerCase();
                 User user1= userDao.getFromName(lowerCase);
                 boolean flag=false;
                    String addPasswordString = addPassword.getText().toString();
                    String passwordConfirmation = confirmPassword.getText().toString();
                    String addNameString = addName.getText().toString();
                    boolean flag2 = false;
                    boolean flag3= false;
                 char character;
                 for(int i=0;i<addPassword.length();i++){
                     character = addPasswordString.charAt(i);
                     if(Character.isUpperCase(character)){
                        flag=true;
                     }
                     if(Character.isDigit(character)){
                         flag2=true;
                     }
                 }
                 for(int i=0;i<addNameString.length();i++){
                     character = addNameString.charAt(i);
                     if(Character.isDigit(character)){
                         flag3=true;
                     }
                 }
                 if(lowerCase.length()<2){
                     Toast.makeText(getContext(),"שם משתמש צריך להיות באורך של לפחות שני תווים",Toast.LENGTH_SHORT).show();
                 } else if (flag3) {
                     Toast.makeText(getContext(),"שם משתמש אינו יכול להכיל ספרות",Toast.LENGTH_SHORT).show();
                 } else if (lowerCase.contains(" ")) {
                     Toast.makeText(getContext()," שם משתמש אינו יכול להכיל רווח",Toast.LENGTH_SHORT).show();
                 } else if (user1!=null) {
                     Toast.makeText(getContext(),"שם משתמש זה כבר קיים, תבחר אחד אחר", Toast.LENGTH_SHORT).show();
                 } else if (addPasswordString.length()<8) {
                     Toast.makeText(getContext(),"הסיסמא חייבת להכיל לפחות 8 תויים",Toast.LENGTH_SHORT).show();
                 } else if (!flag){
                     Toast.makeText(getContext(),"הסיסמא חייבת להכיל לפחות אות גדולה אחת",Toast.LENGTH_SHORT).show();
                 } else if (!flag2) {
                     Toast.makeText(getContext(),"הסיסמא חייבת להכיל לפחות מספר אחד",Toast.LENGTH_SHORT).show();
                 } else if (!addPasswordString.equals(passwordConfirmation)) {
                     Toast.makeText(getContext(),"הסיסמא שכתבת באימות סיסמא אינה מתאימה לסיסמא",Toast.LENGTH_SHORT).show();
                 }
                 else{
                     db.dataBaseWriteExecutor.execute(new Runnable() {
                         @Override
                         public void run() {
                             User user = new User(addPassword.getText().toString(),lowerCase,addEmail.getText().toString());
                             userDao.Insert(user);

                         }
                 });
                     Intent intent = new Intent(getContext(),Splash.class);
                     intent.putExtra("userName",lowerCase);
                     startActivity(intent);
             }
             }
});
    }
    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }


}
