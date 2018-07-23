package pe.gob.inei.dmorales.salonesapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity {
    int salon;
    Button btnAgregar;
    EditText edtDni;
    EditText edtNombre;
    RecyclerView recyclerView;
    Query query;
    FirestoreRecyclerAdapter adapter;
    FirestoreRecyclerOptions<Persona> options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        salon = getIntent().getExtras().getInt("user");

        btnAgregar = (Button) findViewById(R.id.btnAgregar);
        edtDni = (EditText) findViewById(R.id.edtDni);
        edtNombre = (EditText) findViewById(R.id.edtNombre);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);

        query = FirebaseFirestore.getInstance().collection("personas").whereEqualTo("salon",salon);
//        query.get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (DocumentSnapshot document : task.getResult()) {
//                                Log.d("FIRESTORE", document.getId() + " => " + document.getData());
//                            }
//                        } else {
//                            Log.d("FIRESTORE", "Error getting documents: ", task.getException());
//                        }
//                    }
//                });
        options = new FirestoreRecyclerOptions.Builder<Persona>()
                .setQuery(query, Persona.class)
                .build();
        adapter = new FirestoreRecyclerAdapter<Persona, PersonaHolder>(options) {
            @Override
            public void onBindViewHolder(PersonaHolder holder, int position, Persona model) {
                holder.setTxtDni(model.getDni());
                holder.setTxtNombre(model.getNombre());
                holder.setTxtSalon(model.getSalon());
            }

            @Override
            public PersonaHolder onCreateViewHolder(ViewGroup group, int i) {
                // Create a new instance of the ViewHolder, in this case we are using a custom
                // layout called R.layout.message for each item
                View view = LayoutInflater.from(group.getContext()).inflate(R.layout.item_layout, group, false);
                return new PersonaHolder(view);
            }
        };
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!edtDni.getText().toString().equals("") && !edtNombre.getText().toString().equals("") ){
                    Persona persona = new Persona(edtDni.getText().toString(),edtNombre.getText().toString(),salon);
                    FirebaseFirestore.getInstance().collection("personas").document(edtDni.getText().toString())
                            .set(persona)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d("FIRESTORE", "DocumentSnapshot successfully written!");
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w("FIRESTORE", "Error writing document", e);
                                }
                            });
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
