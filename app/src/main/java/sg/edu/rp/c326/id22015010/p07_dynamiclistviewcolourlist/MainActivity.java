package sg.edu.rp.c326.id22015010.p07_dynamiclistviewcolourlist;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText etElement;
    EditText etIndexElement;
    Button btnAdd;
    Button btnRemove;
    Button btnUpdate;
    ListView lvColour;
    ArrayList<String> alColours;
    ArrayAdapter<String> aaColour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etElement = findViewById(R.id.editTextColour);
        etIndexElement = findViewById(R.id.editTextIndex);
        btnAdd = findViewById(R.id.buttonAddItem);
        lvColour = findViewById(R.id.listViewColour);
        btnRemove = findViewById(R.id.buttonRemoveItem);
        btnUpdate = findViewById(R.id.buttonUpdateItem);

        alColours = new ArrayList<>();
        alColours.add("Red");
        alColours.add("Orange");
        aaColour = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alColours);
        lvColour.setAdapter(aaColour);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String colourName = etElement.getText().toString();
                String indexText = etIndexElement.getText().toString();

                if (!indexText.isEmpty()) {
                    if (isNumeric(indexText)) {
                        int pos = Integer.parseInt(indexText) - 1; // Convert 1-based to 0-based
                        if (pos >= 0 && pos <= alColours.size()) {
                            alColours.add(pos, colourName);
                            aaColour.notifyDataSetChanged();
                            showToast("Added color: " + colourName);
                        } else {
                            Toast.makeText(MainActivity.this, "Invalid position", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Please enter a valid numeric position", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a position", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String indexText = etIndexElement.getText().toString();

                if (!indexText.isEmpty()) {
                    if (isNumeric(indexText)) {
                        int pos = Integer.parseInt(indexText) - 1; // Convert 1-based to 0-based
                        if (pos >= 0 && pos < alColours.size()) {
                            alColours.remove(pos);
                            aaColour.notifyDataSetChanged();
                            showToast("Removed color at position: " + (pos + 1));
                        } else {
                            Toast.makeText(MainActivity.this, "Invalid position", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Please enter a valid numeric position", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a position", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String colourName = etElement.getText().toString();
                String indexText = etIndexElement.getText().toString();

                if (!indexText.isEmpty()) {
                    if (isNumeric(indexText)) {
                        int pos = Integer.parseInt(indexText) - 1; // Convert 1-based to 0-based
                        if (pos >= 0 && pos < alColours.size()) {
                            alColours.set(pos, colourName);
                            aaColour.notifyDataSetChanged();
                            showToast("Updated color at position: " + (pos + 1));
                        } else {
                            Toast.makeText(MainActivity.this, "Invalid position", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Please enter a valid numeric position", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a position", Toast.LENGTH_SHORT).show();
                }
            }
        });

        lvColour.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String colour = alColours.get(position);
                Toast.makeText(MainActivity.this, "Clicked color: " + colour, Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Method to check if a string is numeric
    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Method to show toast messages
    private void showToast(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
