package com.example.dwidar.elmawkaf.Model.ModelView;

import com.example.dwidar.elmawkaf.Model.Components.Cab;
import com.example.dwidar.elmawkaf.Model.Components.Customer;
import com.example.dwidar.elmawkaf.Model.Contracts.CabCreationContract;
import com.example.dwidar.elmawkaf.Presenter.CabCreationPresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CabCreationModel implements CabCreationContract.IModel
{

    private FirebaseAuth mAuth;
    private DatabaseReference dbRef;

    public CabCreationModel()
    {
        mAuth = FirebaseAuth.getInstance();
        dbRef = FirebaseDatabase.getInstance().getReference("Database").child("Cabs");
    }

    @Override
    public void AddNew(Cab actor, final CabCreationPresenter prese)
    {
        String empId = actor.getEmpID();
        String pwd = actor.getUPassword();

        dbRef.push().setValue(actor);

        mAuth.createUserWithEmailAndPassword(empId , pwd)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task)
                    {
                        if(task.isSuccessful()) prese.OnSuccessCreation();
                        else prese.OnFailCreation();
                    }
                });

    }
}
