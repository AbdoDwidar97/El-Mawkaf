package com.example.dwidar.elmawkaf.Model.ModelView;

import com.example.dwidar.elmawkaf.Model.Components.Cab;
import com.example.dwidar.elmawkaf.Model.Contracts.CabLoginContract;
import com.example.dwidar.elmawkaf.Presenter.CabLoginPresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CabLoginModel implements CabLoginContract.IModel
{

    private FirebaseAuth mAuth;
    FirebaseDatabase db;
    DatabaseReference dbRef;

    public CabLoginModel()
    {
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        dbRef = db.getReference("Database").child("Cabs");
    }

    @Override
    public void ULogin(Cab actor, final CabLoginPresenter prese)
    {
        mAuth.signInWithEmailAndPassword(actor.getEmpID() , actor.getUPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if (task.isSuccessful()) prese.OnSuccessLogin();
                        else prese.OnFailLogin();
                    }
                });
    }
}
