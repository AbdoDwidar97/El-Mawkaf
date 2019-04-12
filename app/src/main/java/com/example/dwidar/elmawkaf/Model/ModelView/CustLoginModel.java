package com.example.dwidar.elmawkaf.Model.ModelView;

import com.example.dwidar.elmawkaf.Model.Components.Customer;
import com.example.dwidar.elmawkaf.Model.Contracts.CustLoginContract;
import com.example.dwidar.elmawkaf.Presenter.ActorPresenter;
import com.example.dwidar.elmawkaf.Presenter.CustLoginPresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CustLoginModel implements CustLoginContract.IModel
{

    FirebaseAuth mAuth;
    DatabaseReference dbRef;

    public CustLoginModel()
    {
        mAuth = FirebaseAuth.getInstance();
        dbRef = FirebaseDatabase.getInstance().getReference("Database").child("Customers");
    }

    public void ULogin(Customer actor, final CustLoginPresenter prese)
    {
        String email = actor.getEmail();
        String pwd = actor.getUPassword();

        mAuth.signInWithEmailAndPassword(email , pwd)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if (task.isSuccessful()) prese.OnSuccessLogin();
                        else prese.OnFailLogin();
                    }
                });
    }
}
