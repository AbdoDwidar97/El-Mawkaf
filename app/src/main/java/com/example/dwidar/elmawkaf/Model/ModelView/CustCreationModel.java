package com.example.dwidar.elmawkaf.Model.ModelView;

import com.example.dwidar.elmawkaf.Model.Components.Customer;
import com.example.dwidar.elmawkaf.Model.Contracts.CustCreationContract;
import com.example.dwidar.elmawkaf.Presenter.CustCreationPresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CustCreationModel implements CustCreationContract.IModel
{

    FirebaseAuth mAuth;
    DatabaseReference dbRef;

    public CustCreationModel()
    {
        mAuth = FirebaseAuth.getInstance();
        dbRef = FirebaseDatabase.getInstance().getReference("Database").child("Customers");
    }

    public void AddNew(final Customer actor , final CustCreationPresenter prese)
    {
        String email = actor.getEmail();
        String pwd = actor.getUPassword();

        //dbRef.push().setValue(actor);

        mAuth.createUserWithEmailAndPassword(email , pwd)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task)
                    {
                        if(task.isSuccessful())
                        {
                            String custid = mAuth.getCurrentUser().getUid();
                            actor.setCustID(custid);
                            dbRef.push().setValue(actor);
                            prese.OnSuccessCreation();
                        }
                        else prese.OnFailCreation();
                    }
                });
    }

}
