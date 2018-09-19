package info.ravneetpunia.wordpaging1;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import info.ravneetpunia.wordpaging1.Adapter.WordAdapter;
import info.ravneetpunia.wordpaging1.Database.Word;
import info.ravneetpunia.wordpaging1.ViewModel.WordViewModel;

public class MainActivity extends AppCompatActivity {

    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    private WordViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.fab_mainActivity);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AddWordActivity.class);
                startActivity(i);
            }
        });

        RecyclerView rv = findViewById(R.id.rv_mainActivity);

        viewModel = ViewModelProviders.of(this,
                new WordViewModel.WordViewModelFactory(this.getApplication())).get(WordViewModel.class);

        final WordAdapter adapter = new WordAdapter();
        rv.setAdapter(adapter);

        // Listen to The Changes and Pass it to adapter for displaying in recyclerView
        viewModel.wordList.observe(this,pagedList ->{
            adapter.submitList(pagedList);
        });

        rv.setLayoutManager(new LinearLayoutManager(this));
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Word word = new Word(data.getStringExtra(AddWordActivity.EXTRA_REPLY));
            viewModel.insert(word);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
}
